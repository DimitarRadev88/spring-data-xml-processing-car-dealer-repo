package bg.softuni.springDataXmlProcessingPartTwo.dtos.car;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.part.PartExportWrapperDto;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsExportDto {

    @XmlAttribute(name = "make")
    private String make;
    @XmlAttribute(name = "model")
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;
    @XmlElement(name = "parts")
    private PartExportWrapperDto parts;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartExportWrapperDto getParts() {
        return parts;
    }

    public void setParts(PartExportWrapperDto parts) {
        this.parts = parts;
    }

}
