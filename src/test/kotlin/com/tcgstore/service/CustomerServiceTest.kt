package com.tcgstore.service

import com.tcgstore.enums.CustomerStatus
import com.tcgstore.enums.Errors
import com.tcgstore.enums.Role
import com.tcgstore.exception.NotFoundExeption
import com.tcgstore.model.CustomerModel
import com.tcgstore.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {


    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var cardsService: CardsService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMockKs
    @SpyK
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {

        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findAll() } returns fakeCustomers

        val customers = customerService.getAll(null)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) { customerRepository.findAll() }

        verify(exactly = 0) { customerRepository.findByNameContaining(any()) }
    }

    @Test
    fun `should return customer when name is passed with first word in upper case`() {
        val name = "Tes"//UUID.randomUUID().toString()

        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameStartsWith(name) } returns fakeCustomers

        val customers = customerService.getAll(name)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameContaining(name) }
        verify(exactly = 1) { customerRepository.findByNameStartsWith(name) }
    }

    @Test
    fun `should return only customer with name`() {
        val name = "ste"

        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContaining(name) } returns fakeCustomers

        val customers = customerService.getAll(name)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameStartsWith(name) }
        verify(exactly = 1) { customerRepository.findByNameContaining(name) }
    }

    @Test
    fun `should create customer and encrypt password`() {
        val initialPassword = Math.random().toString()
        val fakeCustomer = buildCustomer(password = initialPassword)
        val fakePassword = UUID.randomUUID().toString()
        val fakeCustomerEncrypted = fakeCustomer.copy(password = fakePassword)


        every { customerRepository.save(fakeCustomerEncrypted) } returns fakeCustomer
        every { bCrypt.encode(initialPassword) } returns fakePassword

        customerService.createCustomer(fakeCustomer)

        verify(exactly = 1) { customerRepository.save(fakeCustomerEncrypted) }
        verify(exactly = 1) { bCrypt.encode(initialPassword) }
    }

    @Test
    fun `should return customer by id`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

        val customer = customerService.findById(id)

        assertEquals(fakeCustomer, customer)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should throw error when customer not found`() {
        val id = Random().nextInt()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundExeption> { customerService.findById(id) }

        assertEquals("Customer [${id}] does not exists", error.message)
        assertEquals("TCGS-202", error.errorCode)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should update customer`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.existsById(id) } returns true
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer

        customerService.update(fakeCustomer)

        verify(exactly = 1) { customerRepository.existsById(id) }
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should throw Not Found exception when update customer`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.existsById(id) } returns false
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer

        val error = assertThrows<NotFoundExeption> { customerService.update(fakeCustomer) }

        assertEquals("Customer [${id}] does not exists", error.message)
        assertEquals("TCGS-202", error.errorCode)

        verify(exactly = 1) { customerRepository.existsById(id) }
        verify(exactly = 0) { customerRepository.save(any()) }
    }

    @Test
    fun `should delete customer`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)
        val expectedCustomer = fakeCustomer.copy(status = CustomerStatus.INATIVO)

        every { customerService.findById(id) } returns fakeCustomer
        every { customerRepository.save(expectedCustomer) } returns expectedCustomer
        every { cardsService.deleteByCustomer(fakeCustomer) } just runs

        customerService.delete(id)

        verify(exactly = 1) { customerService.findById(id) }
        verify(exactly = 1) { cardsService.deleteByCustomer(fakeCustomer) }
        verify(exactly = 1) { customerRepository.save(expectedCustomer) }
    }

    @Test
    fun `should throw not found exception when delete customer`() {
        val id = Random().nextInt()

        every { customerService.findById(id) } throws NotFoundExeption(
            Errors.TCGS201.message.format(id),
            Errors.TCGS201.code
        )

        val errror = assertThrows<NotFoundExeption> {
            customerService.delete(id)
        }


        assertEquals("TCGS-202", errror.errorCode)

        verify(exactly = 1) { customerService.findById(any()) }
        verify(exactly = 0) { customerRepository.delete(any()) }
        verify(exactly = 0) { customerRepository.save(any()) }
    }

    @Test
    fun `should return email available`() {
        val email = "${UUID.randomUUID()}@email.com"

        every { customerRepository.existsByEmail(email) } returns false
        val retorno = customerService.emailAvailable(email)

        assertEquals(true, retorno)

        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }

    @Test
    fun `should not return email available`() {
        val email = "${UUID.randomUUID()}@email.com"

        every { customerRepository.existsByEmail(email) } returns true
        val retorno = customerService.emailAvailable(email)

        assertEquals(false, retorno)

        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }

    @Test
    fun `should return true when customer status Active`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id)

        every { customerService.findById(id = id) } returns fakeCustomer
        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

        val customerActive = customerService.customerActive(id)
        println(customerActive)

        assertEquals(true, customerActive)

        verify(exactly = 1){ customerRepository.findById(any())}
    }

    @Test
    fun `should return false when customer status isn't Active`() {
        val id = Random().nextInt()
        val fakeCustomer = buildCustomer(id = id, status = CustomerStatus.INATIVO)

        every { customerService.findById(id = id) } returns fakeCustomer
        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

        val customerActive = customerService.customerActive(id)
        println(customerActive)

        assertEquals(false, customerActive)

        verify(exactly = 1){ customerRepository.findById(any())}
    }

    fun buildCustomer(
        id: Int? = null,
        name: String = "customer name",
        email: String = "${UUID.randomUUID()}@email.com",
        password: String = "password",
        status: CustomerStatus? = CustomerStatus.ATIVO
    ) = CustomerModel(
        id = id,
        name = name,
        email = email,
        status = status!!,
        password = password,
        roles = setOf(Role.CUSTOMER)
    )

}