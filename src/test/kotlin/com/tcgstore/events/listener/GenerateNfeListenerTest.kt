package com.tcgstore.events.listener

import com.tcgstore.events.PurchaseEvent
import com.tcgstore.helper.ModelBuilderHelper
import com.tcgstore.service.PurchaseService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)//class do mockk
class GenerateNfeListenerTest {
    @MockK
    private lateinit var purchaseService: PurchaseService

    @InjectMockKs
    private lateinit var generateNfeListener: GenerateNfeListener

    var help = ModelBuilderHelper()

    @Test
    fun `should genarete nfe`() {
        val purchase = help.buildPurchase(nfe = null)
        val fakeNfe = UUID.randomUUID()
        val purchaseExpected = purchase.copy(nfe = fakeNfe.toString())
        mockkStatic(UUID::class)

        every { UUID.randomUUID() } returns fakeNfe
        every { purchaseService.update(purchaseExpected) } just runs

        generateNfeListener.listen(PurchaseEvent(this, purchase))

        verify(exactly = 1) { purchaseService.update(purchaseExpected) }
    }
}