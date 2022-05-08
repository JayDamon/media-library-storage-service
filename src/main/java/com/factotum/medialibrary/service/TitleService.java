package com.factotum.medialibrary.service;

import java.util.Collection;

import com.factotum.medialibrary.dto.TitleDto;
import com.factotum.medialibrary.entity.Title;

public interface TitleService {

    Title saveTitle(TitleDto title);

    Collection<Title> getRequestedTitles();

    void deleteTitle(int id);

}
