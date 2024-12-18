package bg.softuni.springDataXmlProcessingPartTwo.utils;


import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class XmlParser implements Parser {

    private JAXBContext jaxbContext;

    @Override
    public <T> T fromFile(String path, Class<T> tclass) throws FileNotFoundException, JAXBException {
        jaxbContext = JAXBContext.newInstance(tclass);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


        return (T) unmarshaller.unmarshal(new FileInputStream(path));
    }

}
