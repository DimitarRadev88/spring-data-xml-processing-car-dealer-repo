package bg.softuni.springDataXmlProcessingPartTwo.dtos.supplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportWrapperDto {

    @XmlElement(name = "supplier")
    private List<SupplierExportDto> suppliers;

    public List<SupplierExportDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierExportDto> suppliers) {
        this.suppliers = suppliers;
    }
}
