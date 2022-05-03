package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "material", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_material")
    private int id;
    @Column(name = "name_material")
    private String materialName;

    @Column(name = "warehouse_quantity")
    private int warehouse_quantity;

    public Material(String materialName, int warehouse_quantity) {
        this.materialName = materialName;
        this.warehouse_quantity = warehouse_quantity;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || material name: %s || warehouse quantity: %d", id, materialName, warehouse_quantity);
    }
}