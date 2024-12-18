package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

    @Query("""
            FROM Car c
            JOIN FETCH c.parts
            """)
    List<Car> findAllWithParts();

}
