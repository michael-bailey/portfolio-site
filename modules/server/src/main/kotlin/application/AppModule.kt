package net.michael_bailey.application

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("net.michael_bailey.application")
class AppModule {

	@Single(binds = [MeterRegistry::class])
	fun micrometerMetrics(): PrometheusMeterRegistry =
		PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

}