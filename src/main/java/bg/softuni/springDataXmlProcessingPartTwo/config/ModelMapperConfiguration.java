package bg.softuni.springDataXmlProcessingPartTwo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    }

}
