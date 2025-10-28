package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.extension.nullUUID
import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import io.github.michael_bailey.spring_blog.model.factories.DomainNameAnalyticsModelFactory
import io.github.michael_bailey.spring_blog.model.factories.RequestAnalyticsModelFactory
import io.github.michael_bailey.spring_blog.repository.old.DomainNameAnalyticsRepository
import io.github.michael_bailey.spring_blog.repository.old.RequestAnalyticsRepository
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.toJavaInstant

@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
@OptIn(ExperimentalTime::class)
class AnalyticsServiceTest {

	private val requestId = nullUUID()
	private val remoteAddr = "8.8.8.8"
	private val instant = Instant.DISTANT_PAST
	private val instantJava = instant.toJavaInstant()

	private val domainNameAnalyticsModel = DomainNameAnalyticsModel(
		id = nullUUID(),
		requestId = requestId,
		domainName = "wj-in-f99.1e100.net",
		instant = instantJava
	)

	private val reqMethod = "GET"
	private val requestUri = "/"

	private val requestAnalyticsModel = RequestAnalyticsModel(
		id = nullUUID(),
		requestId = requestId,
		method = reqMethod,
		requestURI = requestUri
	)


	@Mock
	private lateinit var clock: Clock

	@Mock
	private lateinit var domainNameAnalyticsRepository: DomainNameAnalyticsRepository

	@Mock
	private lateinit var requestAnalyticsRepository: RequestAnalyticsRepository

	@Mock
	private lateinit var domainNameAnalyticsModelFactory: DomainNameAnalyticsModelFactory

	@Mock
	private lateinit var requestAnalyticsModelFactory: RequestAnalyticsModelFactory

	val domainCaptor: ArgumentCaptor<DomainNameAnalyticsRepository> =
		ArgumentCaptor.forClass(DomainNameAnalyticsRepository::class.java)
	val requestCaptor: ArgumentCaptor<RequestAnalyticsRepository> =
		ArgumentCaptor.forClass(RequestAnalyticsRepository::class.java);

	@Test
	fun `address with domain logged as name`() {

		`when`(clock.now()).thenReturn(Instant.DISTANT_PAST)
		`when`(domainNameAnalyticsRepository.save(domainNameAnalyticsModel)).thenReturn(
			domainNameAnalyticsModel
		)
		`when`(
			domainNameAnalyticsModelFactory.create(
				requestId, remoteAddr,
				instant = Instant.DISTANT_PAST
			)
		).thenReturn(domainNameAnalyticsModel)

		val analyticsService = AnalyticsService(
			clock,
			domainNameAnalyticsRepository,
			requestAnalyticsRepository,
			domainNameAnalyticsModelFactory = domainNameAnalyticsModelFactory,
			requestAnalyticsModelFactory = requestAnalyticsModelFactory,
		)

		analyticsService.logRequestDomain(
			requestId = nullUUID(),
			remoteAddr = remoteAddr
		)

		verify(domainNameAnalyticsRepository, times(1)).save(
			domainNameAnalyticsModel
		)

	}

	@Test
	fun `request analytics logged`() {

		`when`(requestAnalyticsRepository.save(requestAnalyticsModel)).thenReturn(
			requestAnalyticsModel
		)
		`when`(
			requestAnalyticsModelFactory.create(
				requestId = requestId,
				method = reqMethod,
				requestURI = requestUri
			)
		).thenReturn(requestAnalyticsModel)

		val analyticsService = AnalyticsService(
			clock,
			domainNameAnalyticsRepository,
			requestAnalyticsRepository,
			domainNameAnalyticsModelFactory = domainNameAnalyticsModelFactory,
			requestAnalyticsModelFactory = requestAnalyticsModelFactory,
		)

		analyticsService.logRequest(
			requestId = requestId,
			method = reqMethod,
			requestURI = requestUri
		)

		verify(requestAnalyticsRepository, times(1)).save(requestAnalyticsModel)
		verify(requestAnalyticsModelFactory, times(1)).create(
			requestId = requestId,
			method = reqMethod,
			requestURI = requestUri
		)
	}

	@Test
	fun `getViewersAnalytics handles missing data`() {
		val viewerContext =
			mock(IViewerContext::class.java)
		val requestId = nullUUID()
		`when`(viewerContext.requestId).thenReturn(requestId)

		`when`(domainNameAnalyticsRepository.findByRequestId(requestId)).thenReturn(
			null
		)
		`when`(requestAnalyticsRepository.findByRequestId(requestId)).thenReturn(null)

		val service = AnalyticsService(
			clock,
			domainNameAnalyticsRepository,
			requestAnalyticsRepository,
			domainNameAnalyticsModelFactory,
			requestAnalyticsModelFactory
		)

		val result = service.getViewersAnalytics(viewerContext)

		assert(result["domain"] == "null")
		assert(result["request"] == "null")
	}

	@Test
	fun `getViewersAnalytics returns correct data`() {
		val viewerContext = mock(IViewerContext::class.java)
		val requestId = nullUUID()
		`when`(viewerContext.requestId).thenReturn(requestId)

		val domainModel = domainNameAnalyticsModel.copy(
			id = java.util.UUID.randomUUID()
		)
		val requestModel = requestAnalyticsModel.copy(
			id = java.util.UUID.randomUUID()
		)

		// These models are returned from the repository
		`when`(domainNameAnalyticsRepository.findByRequestId(requestId)).thenReturn(domainModel)
		`when`(requestAnalyticsRepository.findByRequestId(requestId)).thenReturn(requestModel)

		val service = AnalyticsService(
			clock,
			domainNameAnalyticsRepository,
			requestAnalyticsRepository,
			domainNameAnalyticsModelFactory,
			requestAnalyticsModelFactory
		)

		val result = service.getViewersAnalytics(viewerContext)

		assert(result["domain"]!!.startsWith(domainModel.id.toString()))
		assert(result["request"]!!.startsWith(requestModel.id.toString()))
	}

}
