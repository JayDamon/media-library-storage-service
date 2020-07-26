package com.factotum.plexbackend.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.factotum.plexbackend.dto.TitleDto;
import com.factotum.plexbackend.entity.Title;
import com.factotum.plexbackend.repository.TitleRepository;
import com.factotum.plexbackend.util.TitleMappingUtil;

@Service
public class TitleServiceImpl implements TitleService {

    private TitleRepository titleRepository;

    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public Title saveTitle(TitleDto title) {
        Title newTitle = TitleMappingUtil.mapDtoToEntity(title);
        Integer orderAdded = this.titleRepository.findMaxOrderAdded();
        if (orderAdded == null) {
            newTitle.setOrderAdded(1);
        } else {
            newTitle.setOrderAdded(orderAdded + 1);
        }
        return this.titleRepository.save(newTitle);
    }

    @Override
    public Collection<Title> getRequestedTitles() {
        return this.titleRepository.findAll();
    }

    @Override
    public void deleteTitle(int id) {
        System.out.println("Deleting title: " + id);
        titleRepository.deleteById(id);
    }
}
