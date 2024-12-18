package bg.softuni.springDataXmlProcessingPartTwo.dtos.car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsExportWrapperDto {

    @XmlElement(name = "car")
    private List<CarWithPartsExportDto> cars;

    public List<CarWithPartsExportDto> getCars() {
        return cars;
    }
    public void setCars(List<CarWithPartsExportDto> cars) {
        this.cars = cars;
    }

}
