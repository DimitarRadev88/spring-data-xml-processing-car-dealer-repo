package bg.softuni.springDataXmlProcessingPartTwo.services.interfaces;

import javax.xml.bind.JAXBException;

public interface SaleService {

    void generateSales();

    void exportSalesWithDiscountInfo() throws JAXBException;

}
