package com.tcgstore.controller

import com.tcgstore.controller.request.PostCardsRequest
import com.tcgstore.controller.request.PutCardsRequest
import com.tcgstore.extension.toCardsModel
import com.tcgstore.model.CardsModel
import com.tcgstore.service.CardsService
import com.tcgstore.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("cards")
class CardsController(
    val cardsService: CardsService,
    val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostCardsRequest) {
        val customer = customerService.findById(request.customerId)
        cardsService.create(request.toCardsModel(customer))
    }

    @GetMapping
    fun findAll(): List<CardsModel> =
        cardsService.findAll()


    @RequestMapping("/active")
    @GetMapping
    fun findActives(): List<CardsModel> =
        cardsService.findActives()

    @RequestMapping("/{id}")
    @GetMapping
    fun findById(@PathVariable id: Int): CardsModel = cardsService.findById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        cardsService.deleteById(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody card: PutCardsRequest) {
        val cardSaved = cardsService.findById(id)
        cardsService.update(card.toCardsModel(cardSaved))
    }

    @GetMapping("/customer/{id}")
    fun cardsByCustomer(@PathVariable id: Int): List<CardsModel> =
        cardsService.findCardsByCustomer(id);

}