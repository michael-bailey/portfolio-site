//package net.michael_bailey.home.controller
//
//import io.ktor.server.routing.*
//import io.micrometer.core.instrument.Counter
//import io.micrometer.core.instrument.MeterRegistry
//import io.micrometer.core.instrument.Tags
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import kotlinx.coroutines.test.runTest
//import net.michael_bailey.home.service.HomeContentService
//import kotlin.test.Test
//
//class HomeControllerTest {
//	@Test
//	fun `calling index returns index content, and increments counter`() = runTest {
//
//		val homeContentService = mockk<HomeContentService>()
//		val counter = mockk<Counter>()
//		val meterRegistry = mockk<MeterRegistry> {
//			every { counter(HOME_REQUEST_KEY, eq(meter_tags)) } returns counter
//		}
//
//		val call = mockk<RoutingCall> {
//			every { request } returns
//		}
//
//		val underTest = HomeController(
//			homeContentService = homeContentService,
//			meterRegistry = meterRegistry
//		)
//
//		val result = underTest.index(
//			call = call
//		)
//
//		verify(exactly = 1) {
//			counter.increment()
//		}
//
//
//	}
//
//
//	companion object {
//
//		private const val HOME_REQUEST_KEY = "home.request.counter"
//		private const val HOST_TAG_NAME = "host"
//		private const val HOST_NAME = "test_host"
//
//		private val meter_tags = Tags.of(HOST_TAG_NAME, HOST_NAME)
//
//		private val request = RoutingRequest(
//			pathVariables = TODO(),
//			request = TODO(),
//			call = TODO()
//		)
//	}
//}