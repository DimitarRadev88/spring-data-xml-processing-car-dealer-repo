package bg.softuni.springDataXmlProcessingPartTwo.services;


import bg.softuni.springDataXmlProcessingPartTwo.models.Car;
import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
import bg.softuni.springDataXmlProcessingPartTwo.models.Discount;
import bg.softuni.springDataXmlProcessingPartTwo.models.Sale;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.CarRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.CustomerRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.SaleRepository;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void generateSales() {
        long salesCount = carRepository.count() * 2;

        List<Sale> sales = new ArrayList<>();

        for (int i = 0; i < salesCount; i++) {
            sales.add(createSale());
        }

        saleRepository.saveAll(sales);
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
