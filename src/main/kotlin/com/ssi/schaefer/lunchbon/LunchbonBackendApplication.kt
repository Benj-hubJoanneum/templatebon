package com.ssi.schaefer.lunchbon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RestController
import java.io.File

@SpringBootApplication
@RestController
@ComponentScan(basePackages = [
	"com.ssi.schaefer.lunchbon",
	"com.ssi.schaefer.lunchbon.service.api.controller"
])
class LunchbonBackendApplication {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<LunchbonBackendApplication>(*args)
		}
	}
}


