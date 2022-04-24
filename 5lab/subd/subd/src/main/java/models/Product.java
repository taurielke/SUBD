package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "product", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_product")
    private int id;

    @Column(name = "name_product")
    private String productName;

    @Column(name = "warehouse_quantity")
    private int warehouse_quantity;

    /*@ManyToMany
    @JoinTable(name = "product_material",
            joinColumns =  @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<Material> materials = new ArrayList<>();*/


    /*public void setMaterial(Material material) {
        materials.add(material);
    }*/

    public Product(String productName, int quantity) {
        this.productName = productName;
        warehouse_quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Изделие: %s || Кол-во на складе: %d" , id, productName, warehouse_quantity);
    }
}