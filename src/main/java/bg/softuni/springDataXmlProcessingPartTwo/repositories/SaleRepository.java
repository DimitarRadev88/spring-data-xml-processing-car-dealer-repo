package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT SUM(p.price) FROM Sale s
            JOIN s.car c
            JOIN c.parts p
            WHERE s.customer.id = ?1
            """)
    BigDecimal getSpentMoneyByCustomerId(long id);

    @Query("""
            SELECT SUM(p.price) FROM Sale s
            JOIN s.car c
            JOIN c.parts p
            WHERE s.id = ?1
            """)
    BigDecimal getSalePriceById(long id);

}
