package uni.bamberg.dsam.group1.assignment01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Crate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long CrateId;
    @NotNull(message = " Bottle name should not be null")
    @NotEmpty(message = "Crate Name should not be empty")
    private String name;
    @Pattern(regexp = "(https:\\/\\/).*\\.(?:jpg|gif|png)", message = "Must be a valid URL to a picture.")
    @NotNull(message = " Crate picture should not be null")
    @NotEmpty(message = "Crate picture should not be empty")
    private String cratePic;
    @Min(value = 1, message = " required bottles cannot be zero")
    private int noOfBottles;
    @Min(value = 1, message = " Crate price cannot be zero")
    private int price;
    @Min(value = 0)
    private int cratesInStock;
    @Min(value = 1, message =" Value cannot be less than 1")
    @Column(columnDefinition = "integer default 1")
    private int noOfRequiredCrates;
}
