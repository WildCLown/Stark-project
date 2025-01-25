package com.stark.paymentforward.config

import com.starkbank.Project
import com.starkbank.Settings
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

@Component
class StarkBankInitializer {

    @PostConstruct
    fun initializeStarkBank() {
        val privateKey = System.getenv("STARKBANK_PRIVATE_KEY_PATH")
        val projectId = System.getenv("STARKBANK_PROJECT_ID")
        val environment = System.getenv("STARKBANK_ENVIRONMENT")

        val project = Project(
            environment,
            projectId,
            privateKey
        )

        Settings.user = project

        println("Stark Bank initialized with project ID: $projectId and environment: $environment")
    }
}
