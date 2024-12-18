package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {


}
