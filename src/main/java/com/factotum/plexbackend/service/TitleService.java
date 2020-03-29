package com.factotum.plexbackend.service;

import java.util.Collection;

import com.factotum.plexbackend.dto.TitleDto;
import com.factotum.plexbackend.entity.Title;

public interface TitleService {

    Title saveTitle(TitleDto title);

    Collection<Title> getRequestedTitles();
}
