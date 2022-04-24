package models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_order")
    private int id;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_phone_number")
    private int buyerPhoneNumber;

    public Orders(Date deadline, String buyerName, int buyerPhoneNumber) {
        this.deadline = deadline;
        this.buyerName = buyerName;
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    @Override
    public String toString() {
        return String.format("\"\\nid: %d || Дата: %s  || Дедлайн: %s  || Имя покупателя: %s  || Телефонный номер покупателя: %s", id, deadline, buyerName, buyerPhoneNumber);
    }
}