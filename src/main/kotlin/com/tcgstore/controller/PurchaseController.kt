package com.tcgstore.controller

import com.tcgstore.controller.mapper.PurchaseMapper
import com.tcgstore.controller.request.PostPurchaseRequest
import com.tcgstore.model.PurchaseModel
import com.tcgstore.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/purchase")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@Valid @RequestBody request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))
    }

    @GetMapping("/user/{id}")
    fun purchasesByUsersId(@PathVariable id: Int): List<PurchaseModel> {
        return purchaseService.findPurchasesByUserID(id)
    }


}