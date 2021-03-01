package uni.bamberg.dsam.group1.assignment01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderBeverage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long OrderId;
    private int price;

    @ManyToMany(mappedBy = "orders")
    private Set<User_Beverage> users = new HashSet<>();
}
