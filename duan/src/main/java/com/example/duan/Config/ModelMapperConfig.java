package com.example.duan.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public static <T, D> Collection<D> mapCollection(Collection<T> collection, Class<D> myClass, Collector<D, ?, ? extends Collection<D>> collector) {
        ModelMapper modelMapper = new ModelMapper();
        return collection.stream()
                .map(p -> modelMapper.map(p, myClass))
                .collect(collector);
    }


}
