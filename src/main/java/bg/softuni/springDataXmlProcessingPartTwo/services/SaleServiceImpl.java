package bg.softuni.springDataXmlProcessingPartTwo.services;


import bg.softuni.springDataXmlProcessingPartTwo.dtos.sale.SaleExportWrapperDto;
import bg.softuni.springDataXmlProcessingPartTwo.dtos.sale.SaleWithCarCustomerNameDiscountPriceDto;
import bg.softuni.springDataXmlProcessingPartTwo.models.Car;
import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
import bg.softuni.springDataXmlProcessingPartTwo.models.Discount;
import bg.softuni.springDataXmlProcessingPartTwo.models.Sale;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.CarRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.CustomerRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.SaleRepository;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.SaleService;
import bg.softuni.springDataXmlProcessingPartTwo.utils.Parser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Parser parser;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper, Parser parser) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.parser = parser;
    }

    @Override
    public void generateSales() {
        long salesCount = customerRepository.count() * 3;

        List<Sale> sales = new ArrayList<>();

        for (int i = 0; i < salesCount; i++) {
            sales.add(createSale());
        }

        saleRepository.saveAll(sales);
    }

    @Override
    public void exportSalesWithDiscountInfo() throws JAXBException {
        List<Sale> all = saleRepository.findAll();

        List<SaleWithCarCustomerNameDiscountPriceDto> list = all
                .stream()
                .map(s -> modelMapper.map(s, SaleWithCarCustomerNameDiscountPriceDto.class))
                .toList();

        SaleExportWrapperDto wrapperDto = new SaleExportWrapperDto();
        wrapperDto.setSales(list);

        parser.toFile("src/main/resources/files/output/xml/sales-discounts.xml", wrapperDto);
    }

    private Sale createSale() {
        long customerId = ThreadLocalRandom.current().nextLong(customerRepository.count()) + 1;
        Customer customer = customerRepository.findById(customerId).get();
        long carId = ThreadLocalRandom.current().nextLong(carRepository.count()) + 1;
        Car car = carRepository.findById(carId).get();
        int discountIndex = ThreadLocalRandom.current().nextInt(Discount.values().length);
        Discount discount = Discount.values()[discountIndex];

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setCar(car);
        sale.setDiscount(discount);
        return sale;
    }
}
