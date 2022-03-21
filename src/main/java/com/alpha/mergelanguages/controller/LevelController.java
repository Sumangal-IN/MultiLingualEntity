package com.alpha.mergelanguages.controller;

import com.alpha.mergelanguages.entity.LevelOne;
import com.alpha.mergelanguages.service.LevelLanguageService;
import com.alpha.mergelanguages.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/levels")
public class LevelController {

    @Autowired
    LevelService levelService;

    @Autowired
    LevelLanguageService levelLanguageService;


    @GetMapping
    public List<LevelOne> getAllLevels() {
        return levelService.getAllLevels();
    }

    @GetMapping("/{id}")
    public LevelOne getLevel(@PathVariable String id) {
        return levelService.getLevel(id);
    }

    @GetMapping("/{id}/language/{languageCode}")
    public LevelOne getLanguage(@PathVariable String id,
                                @PathVariable String languageCode) {
        return levelLanguageService.extractDescriptions(levelService.getLevel(id), languageCode);
    }

    @DeleteMapping("/{id}/language/{languageCode}")
    public LevelOne deleteLanguage(@PathVariable String id,
                                   @PathVariable String languageCode) {
        return levelLanguageService.removeDescriptions(levelService.getLevel(id), languageCode);
    }

    @PatchMapping("/{id}/language")
    public LevelOne patchLanguage(@PathVariable String id,
                                  @RequestBody LevelOne incomingLevel) {
        return levelLanguageService.patchDescriptions(levelService.getLevel(id), incomingLevel);
    }
}
