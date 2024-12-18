package bg.softuni.springDataXmlProcessingPartTwo.config;

import bg.softuni.springDataXmlProcessingPartTwo.dtos.supplier.SupplierExportDto;
import bg.softuni.springDataXmlProcessingPartTwo.models.Part;
import bg.softuni.springDataXmlProcessingPartTwo.models.Supplier;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfiguration {

    private ModelMapper modelMapper;

    @Bean
    public ModelMapper getInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            configure();
        }

        return modelMapper;
    }

    private void configure() {
        Converter<List<Part>, Integer> partToCountConverter = ctx -> ctx.getSource() == null ? null : ctx.getSource().size();

        TypeMap<Supplier, SupplierExportDto> typeMap = modelMapper.createTypeMap(Supplier.class, SupplierExportDto.class);

        typeMap.addMappings(mapper -> mapper.using(partToCountConverter).map(Supplier::getParts, SupplierExportDto::setPartsCount));
    }

}
