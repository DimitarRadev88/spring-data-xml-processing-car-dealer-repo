package bg.softuni.springDataXmlProcessingPartTwo.services;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.CustomerWrapperDto;
import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
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

        List<Customer> list = wrapperDto.getCustomers().stream().map(dto -> modelMapper.map(dto, Customer.class)).toList();

        customerRepository.saveAll(list);
    }

}
