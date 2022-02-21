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
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: CardStatus? = null
        set(value) {
            if (field == CardStatus.CANCELADO || field == CardStatus.DELETADO)
                throw Exception("Change of status not allowed, ${field} to ${value}")

            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        status: CardStatus?,
        description: String,
        customer: CustomerModel? = null,
        ) : this(id, name, description,price, customer)
    {
        this.status = status

    }

}