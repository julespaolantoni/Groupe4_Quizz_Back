package com.takima.backskeleton.config;

import com.takima.backskeleton.DTO.QuestionDTO;
import com.takima.backskeleton.DTO.SondageDTO;
import com.takima.backskeleton.models.Question;
import com.takima.backskeleton.models.Sondage;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);

        // Ignorer les listes imbriquées pour éviter les cycles
        modelMapper.typeMap(Sondage.class, SondageDTO.class)
                .addMappings(mapper -> mapper.skip(SondageDTO::setQuestions));

        modelMapper.typeMap(Question.class, QuestionDTO.class)
                .addMappings(mapper -> mapper.skip(QuestionDTO::setOptions));

        return modelMapper;
    }
}
