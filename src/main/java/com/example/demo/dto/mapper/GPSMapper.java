package com.example.demo.dto.mapper;

import com.example.demo.domain.Gps;
import com.example.demo.dto.GPSDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GPSMapper {

    GPSDto toDto(Gps gps);

    List<GPSDto> toDtos(List<Gps> gpsList);

}
