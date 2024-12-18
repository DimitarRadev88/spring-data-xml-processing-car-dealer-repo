package bg.softuni.springDataXmlProcessingPartTwo.services;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.CarImportWrapper;
import bg.softuni.springDataXmlProcessingPartTwo.models.Car;
import bg.softuni.springDataXmlProcessingPartTwo.models.Part;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.CarRepository;
import bg.softuni.springDataXmlProcessingPartTwo.repositories.PartRepository;
import bg.softuni.springDataXmlProcessingPartTwo.services.interfaces.CarService;
import bg.softuni.springDataXmlProcessingPartTwo.utils.Parser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private Parser parser;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper, Parser parser) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.parser = parser;
    }

    private List<Part> getRandomParts() {
        int count = ThreadLocalRandom.current().nextInt(10) + 10;

        Set<Part> parts = new HashSet<>();

        while (parts.size() < count) {
            long id = ThreadLocalRandom.current().nextLong(partRepository.count()) + 1;
            parts.add(partRepository.findById(id).orElse(null));
        }

        return parts.stream().toList();
    }


    @Override
    public void importData() throws JAXBException, FileNotFoundException {
        CarImportWrapper importWrapper = parser.fromFile("src/main/resources/files/input/xml/cars.xml", CarImportWrapper.class);

        List<Car> list = importWrapper.getCars().stream().map(dto -> {
            Car car = modelMapper.map(dto, Car.class);
            car.setParts(getRandomParts());
            return car;
        }).toList();

        carRepository.saveAll(list);
    }

}
