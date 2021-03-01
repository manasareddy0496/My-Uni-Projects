package uni.bamberg.dsam.group1.assignment01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long OrderItemId;
    @Pattern(regexp = "[0-9]*", message = "Must contain only numbers")
    private String position;
    @NotNull
    @NotEmpty
    private int price;
}
