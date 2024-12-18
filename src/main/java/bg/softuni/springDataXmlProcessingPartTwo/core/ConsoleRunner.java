package bg.softuni.springDataXmlProcessingPartTwo.core;

import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public ConsoleRunner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
        importData();
    }

    private void importData() throws JAXBException, FileNotFoundException {
        supplierService.importData();
        partService.importData();
        carService.importData();
        customerService.importData();
        saleService.generateSales();
    }
}
