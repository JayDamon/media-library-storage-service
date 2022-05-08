package com.factotum.medialibrary.service;

import com.factotum.medialibrary.dto.TitleDto;

public interface TitleApiService {

    TitleDto[] searchByTitle(String title);

}
