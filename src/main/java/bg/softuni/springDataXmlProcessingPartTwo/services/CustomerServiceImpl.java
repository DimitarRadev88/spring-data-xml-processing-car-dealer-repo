package bg.softuni.springDataXmlProcessingPartTwo.services;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.customer.*;
import bg.softuni.springDataXmlProcessingPartTwo.models.Car;
import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
import bg.softuni.springDataXmlProcessingPartTwo.models.Part;
import bg.softuni.springDataXmlProcessingPartTwo.models.Sale;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.CustomerRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.SaleRepository;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.CustomerService;
import bg.softuni.springDataXmlProcessingPartTwo.utils.Parser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final SaleRepository saleRepository;
    private final Parser parser;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
                               SaleRepository saleRepository, Parser parser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.saleRepository = saleRepository;
        this.parser = parser;
    }

    @Override
    public void importData() throws JAXBException, FileNotFoundException {
        CustomerWrapperDto wrapperDto = parser.fromFile("src/main/resources/files/input/xml/customers.xml", CustomerWrapperDto.class);

        List<Customer> list = wrapperDto
                .getCustomers()
                .stream()
                .map(dto -> modelMapper.map(dto, Customer.class))
                .toList();

        customerRepository.saveAll(list);
    }

    @Override
    public void exportOrderedCustomers() throws JAXBException {
        List<Customer> customers = customerRepository.findAllByOrderByBirthDateAscYoungDriverAsc();

        List<CustomerExportDto> list = customers
                .stream()
                .map(c -> modelMapper.map(c, CustomerExportDto.class))
                .toList();

        CustomerExportWrapperDto wrapper = new CustomerExportWrapperDto();

        wrapper.setCustomers(list);

        parser.toFile("src/main/resources/files/output/xml/ordered-customers.xml", wrapper);

    }

    @Override
    public void exportSalesByCustomer() throws JAXBException {
        List<Customer> customers = customerRepository.findAllWithSalesBySalesNotEmpty();

        List<CustomerWithBoughtCarsAndSpentMoneyDto> list = customers
                .stream()
                .map(c -> modelMapper.map(c, CustomerWithBoughtCarsAndSpentMoneyDto.class))
                .toList();

        CustomerWithBoughtCarsSpentMoneyWrapperDto wrapper = new CustomerWithBoughtCarsSpentMoneyWrapperDto();
        wrapper.setCustomers(list);

        parser.toFile("src/main/resources/files/output/xml/customers-total-sales.xml", wrapper);
    }

}
