package uni.bamberg.dsam.group1.assignment01.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bottle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long BottleId;
    @NotNull(message = " Bottle name should not be null")
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Pattern(regexp = "(https:\\/\\/).*\\.(?:jpg|gif|png)", message = "Must be a valid URL to a picture.")
    private String bottlePic;
    @Min(value = 1,message =" Value cannot be less than 1")
    private double volume;
    private Boolean isAlcoholic;
    @Min(value = 1, message =" Value cannot be less than 1")
    private double volumePercent;
    @Min(value = 1, message = " Value cannot be less than 1")
    private int price;
    @NotNull(message = " Supplier name should not be null")
    @NotEmpty(message = "Supplier Name should not be empty")
    private String supplier;
    @Min(value = 0)
    private int inStock;
    @Min(value = 1, message =" Value cannot be less than 1")
    @Column(columnDefinition = "integer default 1")
    private int noOfRequiredBottles;
}
