package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByOrderByBirthDateAscYoungDriverAsc();

    @Query("""
            FROM Customer c
            JOIN FETCH c.sales s
            WHERE size(s) > 0
            """)
    List<Customer> findAllWithSalesBySalesNotEmpty();

}
