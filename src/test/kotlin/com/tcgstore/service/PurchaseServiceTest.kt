package com.tcgstore.service

import com.tcgstore.events.PurchaseEvent
import com.tcgstore.helper.ModelBuilderHelper
import com.tcgstore.repository.PurchaseRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher
import java.util.*

@ExtendWith(MockKExtension::class)//class do mockk
class PurchaseServiceTest {



    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var customerService: CustomerService

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    var purchaseEventSlot = slot<PurchaseEvent>()

    var helper = ModelBuilderHelper()
    @Test
    fun  `should create purchase and publish event`(){
//        val customer = helper.buildCustomer()
        val purchase = helper.buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        every { applicationEventPublisher.publishEvent(any()) } just runs

        purchaseService.create(purchase)

        verify(exactly = 1){ purchaseRepository.save(purchase)}
        verify(exactly = 1){ applicationEventPublisher.publishEvent(capture(purchaseEventSlot))}

        assertEquals(purchase, purchaseEventSlot.captured.purchaseModel)
    }

    @Test
    fun `should update card status`(){

        val purchase = helper.buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        purchaseService.update(purchase)

        verify(exactly = 1){ purchaseRepository.save(purchase)}
    }

    @Test
    fun `should return purchase by id`(){

        val id = Random().nextInt()

        var customer = helper.buildCustomer(id = id)

        var purchase = helper.buildPurchase(customer = customer)

        every { customerService.findById(id = id) } returns customer
        every { purchaseRepository.findByCustomer(customer) } returns listOf(purchase)

        val res = purchaseService.findPurchasesByUserID(id = id)

        verify(exactly = 1){ customerService.findById(id)}
        verify(exactly = 1){ purchaseRepository.findByCustomer(customer)}

        assertEquals(listOf(purchase), res)
    }

}