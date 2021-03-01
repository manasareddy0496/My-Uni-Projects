package uni.bamberg.dsam.group1.assignment01.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private long addressId;
    @NotNull(message = " Street name should not be null")
    @NotEmpty(message = "Street Name should not be empty")
   // @Column(name = "Street_Name")
    private String street;
    @NotNull(message = " House number should not be null")
    @NotEmpty(message = "House number should not be empty")
    //@Column(name = "House_Number")
    @Pattern(regexp = "[0-9]*", message = "must not contain special characters")
    private String number;
    @NotNull(message = " Postal code should not be null")
    @NotEmpty(message = "Postal code should not be empty")
    @Size(max = 5, min = 5 , message = "Postal code should be always a five digit number")
    @Pattern(regexp = "[0-9]*", message = "Must contain only numbers")
    private String postalCode;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[B]|[D]", message = "Must contain only B or D")
    @Size(max = 1, min = 1)
    private String addressType;
    @NotNull(message = "Id cannot be empty")
    @ManyToOne
    @JoinColumn(name = "USER_ID",referencedColumnName = "ID")
    private User_Beverage userBeverage;
}
