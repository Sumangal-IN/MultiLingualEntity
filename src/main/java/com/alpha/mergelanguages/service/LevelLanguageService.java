package com.alpha.mergelanguages.service;

import com.alpha.mergelanguages.entity.LevelOne;
import com.alpha.mergelanguages.util.EntityLanguageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelLanguageService {

    @Autowired
    EntityLanguageUtil entityLanguageUtil;

    public LevelOne extractDescriptions(LevelOne level, String languageCode) {
        return (LevelOne) entityLanguageUtil.extract(level, languageCode);
    }

    public LevelOne removeDescriptions(LevelOne level, String languageCode) {
        return (LevelOne) entityLanguageUtil.remove(level, languageCode);
    }

    public LevelOne patchDescriptions(LevelOne srcLevel, LevelOne incomingLevel) {
        return (LevelOne) entityLanguageUtil.upsert(srcLevel, incomingLevel);
    }
}
