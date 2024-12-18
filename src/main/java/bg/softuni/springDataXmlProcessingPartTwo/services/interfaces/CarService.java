package bg.softuni.springDataXmlProcessingPartTwo.services.interfaces;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CarService {
    void importData() throws JAXBException, FileNotFoundException;

}
