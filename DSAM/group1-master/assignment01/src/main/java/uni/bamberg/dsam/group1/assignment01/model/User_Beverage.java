package uni.bamberg.dsam.group1.assignment01.model;

import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User_Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotNull(message = " User name should not be null")
    @NotEmpty(message = "User Name should not be empty")
    @Column(unique = true)
    private String username;
    @NotNull(message = " Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    //Minimum length = 7
    //Maximun length = 15
    //Must contain atleast one number, one special character
    @Length(min = 7, max = 15, message = "Length should be of minimum 7 or maximum 15")
    private String password;
    //Date format
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Past
    //Date should not be less than
    //Date should not be greater than
    private LocalDate birthday;
    @OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
    private Set<Address> address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_order" , joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "order_id")})
    private Set<OrderBeverage> orders = new HashSet<OrderBeverage>();
}
