package com.factotum.medialibrary.unit.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.factotum.medialibrary.dto.TitleDto;
import com.factotum.medialibrary.entity.Title;
import com.factotum.medialibrary.repository.TitleRepository;
import com.factotum.medialibrary.service.TitleService;
import com.factotum.medialibrary.service.TitleServiceImpl;

@ExtendWith(MockitoExtension.class)
class TitleServiceImpleUnitTest {

    @Mock
    private TitleRepository titleRepository;

    private TitleService titleService;

    @BeforeEach
    void setUp() {
        this.titleService = new TitleServiceImpl(this.titleRepository);
    }

    @Test
    void saveTitle_GivenTitleHasAllRequiredFields_ThenSaveAndReturnTitleWithId() {

        // Arrange
        TitleDto dto = new TitleDto();
        dto.setImdb("Some Imdb ID");
        dto.setPosterUrl("I am in fact a url");
        dto.setTitle("New Title!");
        dto.setType("Movie"); //TODO what are the possible types? Can this me an Enum?
        dto.setYear("2350");

        when(this.titleRepository.findMaxOrderAdded()).thenReturn(2);

        when(this.titleRepository.save(any())).thenAnswer(i -> {
                    Title savedTitle = (Title) i.getArguments()[0];
                    savedTitle.setId(57L);
                    return savedTitle;
                });

        // Act
        Title savedTitle = this.titleService.saveTitle(dto);

        // Assert
        assertThat(savedTitle, is(not(nullValue())));
        assertThat(savedTitle.getId(), is(equalTo(57L)));
        assertThat(savedTitle.getImdb(), is(equalTo("Some Imdb ID")));
        assertThat(savedTitle.getPosterUrl(), is(equalTo("I am in fact a url")));
        assertThat(savedTitle.getTitle(), is(equalTo("New Title!")));
        assertThat(savedTitle.getType(), is(equalTo("Movie")));
        assertThat(savedTitle.getYear(), is(equalTo("2350")));
        assertThat(savedTitle.getOrderAdded(), is(equalTo(3)));

    }

    @Test
    void saveTitle_GivenNullTitle_ThenThrowIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> this.titleService.saveTitle(new TitleDto()));

    }

}
