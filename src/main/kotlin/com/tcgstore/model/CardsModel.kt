package com.tcgstore.model

import com.tcgstore.enums.CardStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "cards")
data class CardsModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CardStatus? = null,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

)