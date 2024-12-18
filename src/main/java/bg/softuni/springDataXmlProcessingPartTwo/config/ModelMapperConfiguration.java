package bg.softuni.springDataXmlProcessingPartTwo.config;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.customer.CustomerWithBoughtCarsAndSpentMoneyDto;
import bg.softuni.springDataXmlProcessingPartTwo.dtos.sale.SaleWithCarCustomerNameDiscountPriceDto;
import bg.softuni.springDataXmlProcessingPartTwo.dtos.supplier.SupplierExportDto;
import bg.softuni.springDataXmlProcessingPartTwo.models.Customer;
import bg.softuni.springDataXmlProcessingPartTwo.models.Part;
import bg.softuni.springDataXmlProcessingPartTwo.models.Sale;
import bg.softuni.springDataXmlProcessingPartTwo.models.Supplier;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.SaleRepository;
import jakarta.persistence.Id;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfiguration {

    private ModelMapper modelMapper;
    private final SaleRepository saleRepository;

    @Autowired
    public ModelMapperConfiguration(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Bean
    public ModelMapper getInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            configure();
        }

        return modelMapper;
    }

    private void configure() {
        supplierToSupplierExportDto();
        customerToCustomerWithBoughtCarsAndspentMoneyDto();

        Converter<Long, BigDecimal> idToPriceConverter = ctx -> ctx.getSource() == null
                ? null
                : saleRepository.getSalePriceById(ctx.getSource());

        Converter<Long, BigDecimal> idToDiscountedPriceConverter = ctx -> ctx.getSource() == null
                ? null
                : saleRepository
                .findById(ctx.getSource())
                .get()
                .getDiscount()
                .apply(saleRepository.getSalePriceById(ctx.getSource())) ;

        TypeMap<Sale, SaleWithCarCustomerNameDiscountPriceDto> typeMap = modelMapper.createTypeMap(Sale.class, SaleWithCarCustomerNameDiscountPriceDto.class);

        typeMap.addMappings(mapper -> {
            mapper.using(idToPriceConverter).map(Sale::getId, SaleWithCarCustomerNameDiscountPriceDto::setPrice);
            mapper.using(idToDiscountedPriceConverter).map(Sale::getId, SaleWithCarCustomerNameDiscountPriceDto::setPriceWithDiscount);
        });

    }

    private void customerToCustomerWithBoughtCarsAndspentMoneyDto() {
        Converter<List<Sale>, Integer> boughtCarsConverter = ctx -> ctx.getSource() == null
                ? null
                : ctx.getSource().size();

        Converter<Long, BigDecimal> idToSpentMoneyConverter = ctx -> ctx.getSource() == null
                ? null
                : saleRepository.getSpentMoneyByCustomerId(ctx.getSource());

        TypeMap<Customer, CustomerWithBoughtCarsAndSpentMoneyDto> typeMap = modelMapper.createTypeMap(Customer.class, CustomerWithBoughtCarsAndSpentMoneyDto.class);

        typeMap.addMappings(mapper -> {
            mapper.using(boughtCarsConverter).map(Customer::getSales, CustomerWithBoughtCarsAndSpentMoneyDto::setBoughtCars);
            mapper.using(idToSpentMoneyConverter).map(Customer::getId, CustomerWithBoughtCarsAndSpentMoneyDto::setSpentMoney);
        });
    }

    private void supplierToSupplierExportDto() {
        Converter<List<Part>, Integer> partToCountConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().size();

        TypeMap<Supplier, SupplierExportDto> typeMap = modelMapper.createTypeMap(Supplier.class, SupplierExportDto.class);

        typeMap.addMappings(mapper -> mapper.using(partToCountConverter).map(Supplier::getParts, SupplierExportDto::setPartsCount));
    }

}
