package com.ssi.schaefer.lunchbon.businesslogic

import com.google.cloud.firestore.Firestore
import com.ssi.schaefer.lunchbon.acceslogic.LunchbonRepository
import com.ssi.schaefer.lunchbon.acceslogic.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessLogicConfig {

    @Bean
    fun userRepositoryBean(firestore: Firestore): UserRepository {
        return UserRepository(firestore)
    }

    @Bean
    fun lunchbonRepositoryBean(firestore: Firestore): LunchbonRepository {
        return LunchbonRepository(firestore)
    }
}