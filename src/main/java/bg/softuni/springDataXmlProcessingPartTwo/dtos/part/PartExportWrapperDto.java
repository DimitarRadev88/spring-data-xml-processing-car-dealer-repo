package bg.softuni.springDataXmlProcessingPartTwo.dtos.part;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartExportWrapperDto {

    @XmlElement(name = "part")
    private List<PartExportDto> parts;

    public List<PartExportDto> getParts() {
        return parts;
    }

    public void setParts(List<PartExportDto> parts) {
        this.parts = parts;
    }

}
