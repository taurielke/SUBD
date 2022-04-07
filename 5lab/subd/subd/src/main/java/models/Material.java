package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "material", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name_material")
    private String materialName;

    @Column(name = "warehouse_quantity")
    private int warehouse_quantity;

    @ManyToMany(mappedBy = "materials")
    private List<Product> products = new ArrayList<>();

    public Material(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Материал: %s", id, materialName);
    }
}