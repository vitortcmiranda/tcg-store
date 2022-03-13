package com.tcgstore.service

import com.tcgstore.exception.AuthenticationException
import com.tcgstore.repository.CustomerRepository
import com.tcgstore.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt()).orElseThrow {
            AuthenticationException("User not found", "000")
        }
        return UserCustomDetails(customer)
    }
}