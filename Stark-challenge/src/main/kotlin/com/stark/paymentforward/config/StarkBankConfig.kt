import com.stark.paymentforward.model.StarkBankInfo
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StarkBankConfig {
    @Bean
    fun starkBankInfo(): StarkBankInfo {
        return StarkBankInfo(
            name = System.getenv("STARKBANK_NAME"),
            branchCode = System.getenv("STARKBANK_BRANCH_CODE"),
            account = System.getenv("STARKBANK_ACCOUNT"),
            code = System.getenv("STARKBANK_CODE"),
            taxId = System.getenv("STARKBANK_TAX_ID"),
            accountType = System.getenv("STARKBANK_ACCOUNT_TYPE")
        )
    }
}
