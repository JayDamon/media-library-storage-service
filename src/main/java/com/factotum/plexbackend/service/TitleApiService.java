package com.factotum.plexbackend.service;

import com.factotum.plexbackend.dto.TitleDto;

public interface TitleApiService {

    TitleDto[] searchByTitle(String title);

}
