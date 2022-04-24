package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "product_material", schema = "public", catalog = "test")
@NoArgsConstructor
@Getter
@Setter
public class Product_Material {

    @Id
    @Column(name = "prodictid_product")
    private int productID;

    @Id
    @Column(name = "materialid_material")
    private int materialID;

    @Column(name = "material_quantity_for_product")
    private int quantity;

    public Product_Material(int productID, int materialID, int quantity) {
        this.productID = productID;
        this.materialID = materialID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("\nИзделие: %d || Материал: %d || Кол-во на складе: %d", productID, materialID, quantity);
    }
}