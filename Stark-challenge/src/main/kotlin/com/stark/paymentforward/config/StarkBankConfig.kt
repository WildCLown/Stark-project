package com.stark.paymentforward.config

import com.stark.paymentforward.model.StarkBankInfo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StarkBankConfig {
    @Bean
    fun starkBankInfo(): StarkBankInfo {
        val bank_name = System.getenv("STARK_BANK_INFO_BANK_NAME")
        val branch_code = System.getenv("STARK_BANK_INFO_BRANCH_CODE")
        val account = System.getenv("STARK_BANK_INFO_BRANCH_CODE")
        val code = System.getenv("STARK_BANK_INFO_BANK_CODE")
        val tax_id = System.getenv("STARK_BANK_INFO_BANK_TAX_ID")
        val account_type = System.getenv("STARK_BANK_INFO_ACCOUNT_NUMBER")

        val bankObject = StarkBankInfo(
            name = bank_name,
            branchCode = branch_code,
            account = account,
            code = code,
            taxId = tax_id,
            accountType = account_type
        )

        return bankObject
    }
}
