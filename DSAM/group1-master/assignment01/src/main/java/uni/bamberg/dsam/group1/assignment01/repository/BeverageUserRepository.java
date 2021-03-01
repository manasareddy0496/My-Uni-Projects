package uni.bamberg.dsam.group1.assignment01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.bamberg.dsam.group1.assignment01.model.User_Beverage;

public interface BeverageUserRepository extends JpaRepository<User_Beverage, String> {
}
