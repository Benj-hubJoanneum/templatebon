package com.ssi.schaefer.lunchbon

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@Tag("integration")
@SpringBootTest
class LunchbonTestIntegration {

    @Test
    fun `franz should run some dings`() {

        // checks
        assert(true)
    }
}
