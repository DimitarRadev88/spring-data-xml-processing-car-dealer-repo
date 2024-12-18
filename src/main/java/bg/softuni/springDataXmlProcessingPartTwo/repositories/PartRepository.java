package bg.softuni.springDataXmlProcessingPartTwo.repositories;

import bg.softuni.springDataXmlProcessingPartTwo.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

}
