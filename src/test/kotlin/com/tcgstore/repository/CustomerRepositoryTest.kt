package com.tcgstore.repository

import com.tcgstore.helper.ModelBuilderHelper
import com.tcgstore.model.CustomerModel
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)//class do mockk

class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    var help = ModelBuilderHelper()

    @BeforeEach
    fun setup() = customerRepository.deleteAll()


    @Test
    fun `should return name containing`() {
        val alexa = customerRepository.save(help.buildCustomer(name = "Alexa"))
        val alexandre = customerRepository.save(help.buildCustomer(name = "Alexandre"))
        customerRepository.save(help.buildCustomer(name = "Cortana"))

        val customers = customerRepository.findByNameContaining("Ale")

        assertEquals(listOf(alexa, alexandre), customers)
    }

    @Nested
    inner class `exists by email` {
        @Test
        fun `should return true when email exists`() {
            val email = "teste@gmail.com"
            customerRepository.save(help.buildCustomer(email = email))

            val res = customerRepository.existsByEmail(email);

            assertTrue(res)
        }

        @Test
        fun `should return false when email doenst exists`() {
            val email = "teste@gmail.com"
            customerRepository.save(help.buildCustomer(email = email))

            val res = customerRepository.existsByEmail("a$email");

            assertFalse(res)
        }


    }

    @Nested
    inner class `find by email` {
        @Test
        fun `should return customer by email`() {
            val email = "${UUID.randomUUID()}@email.com"

            val customer = customerRepository.save(help.buildCustomer(email = email))

            val findByEmail = customerRepository.findByEmail(email)

            assertEquals(customer, findByEmail)

        }

        @Test
        fun `should not return customer by email`() {
            val email = "${UUID.randomUUID()}@email.com"
            val findByEmail = customerRepository.findByEmail(email)

            assertEquals(null, findByEmail)

        }
    }

    @Nested
    inner class `find by name starts with` {
        @Test
        fun `should return customer`() {

            customerRepository.save(help.buildCustomer(name = "Teste"))
            val expected = customerRepository.save(help.buildCustomer(name = "Nomedeteste"))

            val customer = customerRepository.findByNameStartsWith("Nom")

            assertEquals(listOf(expected), customer)

        }

        @Test
        fun `should return nothing`() {
            customerRepository.save(help.buildCustomer(name = "Teste"))

            val customer = customerRepository.findByNameStartsWith("Nom")

            assertTrue(customer.isEmpty())

        }
    }
}