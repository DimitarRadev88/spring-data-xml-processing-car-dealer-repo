package bg.softuni.springDataXmlProcessingPartTwo.services;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.part.PartWrapperDto;
import bg.softuni.springDataXmlProcessingPartTwo.models.Part;
import bg.softuni.springDataXmlProcessingPartTwo.models.Supplier;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.PartRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.SupplierRepository;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.PartService;
import bg.softuni.springDataXmlProcessingPartTwo.utils.Parser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Parser parser;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper, Parser parser) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.parser = parser;
    }

    private Supplier getRandomSupplier() {
        long id = ThreadLocalRandom.current().nextLong(supplierRepository.count()) + 1;

        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void importData() throws JAXBException, FileNotFoundException {
        PartWrapperDto wrapperDto = parser.fromFile("src/main/resources/files/input/xml/parts.xml", PartWrapperDto.class);

        List<Part> list = wrapperDto
                .getParts()
                .stream()
                .map(dto -> {
                    Part part = modelMapper.map(dto, Part.class);
                    part.setSupplier(getRandomSupplier());
                    return part;
                })
                .toList();

        partRepository.saveAll(list);
    }

}
