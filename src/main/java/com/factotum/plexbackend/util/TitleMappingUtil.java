package com.factotum.plexbackend.util;

import com.factotum.plexbackend.dto.TitleDto;
import com.factotum.plexbackend.entity.Title;
import org.modelmapper.ModelMapper;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class TitleMappingUtil {

    private static ModelMapper modelMapper = new ModelMapper();

    private TitleMappingUtil() {}

    public static TitleDto mapEntityToDto(Title title) {
        return modelMapper.map(title, TitleDto.class);
    }

    public static Title mapDtoToEntity(TitleDto title) {
        return modelMapper.map(title, Title.class);
    }

    public static Set<TitleDto> mapEntitesToDto(Collection<Title> titles) {
        Set<TitleDto> dtos = new LinkedHashSet<>();

        for (Title t : titles) {
            dtos.add(mapEntityToDto(t));
        }

        return dtos;
    }
}
