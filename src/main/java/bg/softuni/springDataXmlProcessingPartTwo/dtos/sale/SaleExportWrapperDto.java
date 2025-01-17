package bg.softuni.springDataXmlProcessingPartTwo.dtos.sale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportWrapperDto {

    @XmlElement(name = "sale")
    private List<SaleWithCarCustomerNameDiscountPriceDto> sales;

    public List<SaleWithCarCustomerNameDiscountPriceDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleWithCarCustomerNameDiscountPriceDto> sales) {
        this.sales = sales;
    }

}
