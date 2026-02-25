package com.example.Pranay.controller;

import com.example.Pranay.dto.GlobalSearchResponseDto;
import com.example.Pranay.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping("")
    public GlobalSearchResponseDto search(@RequestParam String search , @RequestParam int page , @RequestParam int size , @RequestParam String type){
        return searchService.searchGlobalSearch(search , page , size , type );
    }
}
