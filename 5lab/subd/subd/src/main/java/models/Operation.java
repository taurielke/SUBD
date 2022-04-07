package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "expenses", schema = "public", catalog = "lab5")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Operation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_product")
    private Product product;

    @Column(name = "quantity_product")
    private int quantityProduct;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_status")
    private Status_Product status_product;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_order")
    private Orders order;

    public Operation (Date date, Product product, int quantityProduct, Status_Product status_product, Orders order) {
        this.date = date;
        this.product = product;
        this.quantityProduct = quantityProduct;
        this.status_product = status_product;
        this.order = order;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Дата: %s  || ProductID: %s  || Количество изделий: %s  || Status_ProductID: %s  || OrderID: %s",
                id, date, product.getId(), quantityProduct, status_product.getId(), order.getId());
    }
}