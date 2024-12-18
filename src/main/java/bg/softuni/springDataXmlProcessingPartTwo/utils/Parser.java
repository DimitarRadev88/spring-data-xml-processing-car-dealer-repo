package bg.softuni.springDataXmlProcessingPartTwo.utils;


import bg.softuni.springDataXmlProcessingPartTwo.dtos.customer.CustomerExportDto;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface Parser {

    <T> T fromFile(String path, Class<T> tclass) throws JAXBException, FileNotFoundException, JAXBException;

    <T> void toFile(String path, T object) throws JAXBException;

}
