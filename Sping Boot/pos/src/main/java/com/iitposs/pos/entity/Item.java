package com.iitposs.pos.entity;

import com.iitposs.pos.util.enums.MeasuringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private int itemID;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "measuring_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MeasuringType measuringType;

    @Column(name = "supplier_price", nullable = false)
    private double supplierPrice;

    @Column(name = "display_price", nullable = false)
    private double displayPrice;

    @Column(name = "selling_price", nullable = false)
    private double sellingPrice;

    @Column(name = "qty_on_hand", nullable = false)
    private int qtyOnHand;

    @Column(name = "active_state",columnDefinition = "TINYINT default 1", nullable = false)
    private boolean activeState;

    public Item(String itemName, MeasuringType measuringType, double supplierPrice, double displayPrice, double sellingPrice, int qtyOnHand, boolean activeState) {
        this.itemName = itemName;
        this.measuringType = measuringType;
        this.supplierPrice = supplierPrice;
        this.displayPrice = displayPrice;
        this.sellingPrice = sellingPrice;
        this.qtyOnHand = qtyOnHand;
        this.activeState = activeState;
    }

    @OneToMany(mappedBy = "items")
    private Set<OrderDetails> orderDetails;

}
