package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {


}
