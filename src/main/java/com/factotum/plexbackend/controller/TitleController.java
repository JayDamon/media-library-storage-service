package com.factotum.plexbackend.controller;

import static org.springframework.http.ResponseEntity.ok;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factotum.plexbackend.dto.TitleDto;
import com.factotum.plexbackend.service.TitleApiService;
import com.factotum.plexbackend.service.TitleService;
import com.factotum.plexbackend.util.TitleMappingUtil;

@RestController
@Validated
@RequestMapping("/api/v1/titles")
public class TitleController {

    private TitleApiService titleApiService;
    private TitleService titleService;

    public TitleController(TitleApiService titleApiService, TitleService titleService) {
        this.titleApiService = titleApiService;
        this.titleService = titleService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchTitles(@RequestParam(name = "title") String title) {

        return ok(this.titleApiService.searchByTitle(title));

    }

    @GetMapping("")
    public ResponseEntity<?> getRequestedTitles() {

        return ok(TitleMappingUtil.mapEntitesToDto(this.titleService.getRequestedTitles()));
    }

    @PostMapping("")
    public ResponseEntity<?> addNewTitle(@Valid @RequestBody TitleDto title) {

        return ok(TitleMappingUtil.mapEntityToDto(this.titleService.saveTitle(title)));

    }

}