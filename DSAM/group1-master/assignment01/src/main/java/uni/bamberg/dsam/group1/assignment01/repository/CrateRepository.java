package uni.bamberg.dsam.group1.assignment01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.bamberg.dsam.group1.assignment01.model.Crate;

public interface CrateRepository extends JpaRepository<Crate, Long> {
}
