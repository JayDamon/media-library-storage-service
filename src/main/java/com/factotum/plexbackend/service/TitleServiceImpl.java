package com.factotum.plexbackend.service;

import static com.factotum.plexbackend.util.TitleMappingUtil.mapDtoToEntity;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.factotum.plexbackend.dto.TitleDto;
import com.factotum.plexbackend.entity.Title;
import com.factotum.plexbackend.repository.TitleRepository;

@Service
public class TitleServiceImpl implements TitleService {

    private TitleRepository titleRepository;

    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public Title saveTitle(TitleDto title) {

        validateTitleDto(title);

        Title newTitle = mapDtoToEntity(title);

        incrementOrderAdded(newTitle);

        return this.titleRepository.save(newTitle);
    }

    @Override
    public Collection<Title> getRequestedTitles() {
        return this.titleRepository.findAll();
    }

    private void incrementOrderAdded(Title newTitle) {

        Integer orderAdded = this.titleRepository.findMaxOrderAdded();

        if (orderAdded == null) {
            newTitle.setOrderAdded(1);
        } else {
            newTitle.setOrderAdded(orderAdded + 1);
        }

    }

    private void validateTitleDto(TitleDto title) {

        if (title.getTitle() == null || title.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }

    }
}
