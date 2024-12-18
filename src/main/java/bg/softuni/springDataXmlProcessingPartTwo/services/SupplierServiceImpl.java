package bg.softuni.springDataXmlProcessingPartTwo.services;


import bg.softuni.springDataXmlProcessingPartTwo.dtos.SupplierImportDto;
import bg.softuni.springDataXmlProcessingPartTwo.dtos.SupplierWrapperDto;
import bg.softuni.springDataXmlProcessingPartTwo.models.Supplier;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.SupplierRepository;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.SupplierService;
import bg.softuni.springDataXmlProcessingPartTwo.utils.Parser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final Parser parser;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(Parser parser, SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.parser = parser;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importData() throws JAXBException, FileNotFoundException {
        SupplierWrapperDto supplierWrapperDto = parser.fromFile("src/main/resources/files/input/xml/suppliers.xml", SupplierWrapperDto.class);

        List<Supplier> list = supplierWrapperDto
                .getSuppliers()
                .stream()
                .map(s -> modelMapper.map(s, Supplier.class))
                .toList();

        supplierRepository.saveAll(list);
    }

}
