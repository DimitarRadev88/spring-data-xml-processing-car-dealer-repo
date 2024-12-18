package bg.softuni.springDataXmlProcessingPartTwo.dtos.customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerWithBoughtCarsSpentMoneyWrapperDto {

    @XmlElement(name = "customer")
    private List<CustomerWithBoughtCarsAndSpentMoneyDto> customers;

    public List<CustomerWithBoughtCarsAndSpentMoneyDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerWithBoughtCarsAndSpentMoneyDto> customers) {
        this.customers = customers;
    }

}
