package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {



}
