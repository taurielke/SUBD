package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "status_product", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status_Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name_status")
    private String statusName;

    public Status_Product(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Статус изделия: %s", id, statusName);
    }
}