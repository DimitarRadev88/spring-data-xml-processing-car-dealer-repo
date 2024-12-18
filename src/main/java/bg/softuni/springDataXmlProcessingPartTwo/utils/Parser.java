package bg.softuni.springDataXmlProcessingPartTwo.utils;


import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface Parser {

    <T> T fromFile(String path, Class<T> tclass) throws JAXBException, FileNotFoundException, JAXBException;

}
