package com.alpha.mergelanguages.service;

import com.alpha.mergelanguages.entity.Description;
import com.alpha.mergelanguages.entity.LevelOne;
import com.alpha.mergelanguages.entity.LevelThree;
import com.alpha.mergelanguages.entity.LevelTwo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LevelService {

    private static final List<LevelOne> levels = Arrays.asList(LevelOne.builder()
            .id("l1")
            .descriptions(Arrays.asList
                    (Description.builder().content("level one").language("en").build(),
                            Description.builder().content("niveau une").language("fr").build()))
            .levelTwo(LevelTwo.builder()
                    .id("l11")
                    .descriptions(Arrays.asList(
                            Description.builder().content("level two").language("en").build(),
                            Description.builder().content("niveau deux").language("fr").build()))
                    .levelThrees(Arrays.asList(
                            LevelThree.builder()
                                    .id("l111")
                                    .descriptions(Arrays.asList(
                                            Description.builder().content("level three 1").language("en").build(),
                                            Description.builder().content("niveau trois 2").language("fr").build()))
                                    .build(),
                            LevelThree.builder()
                                    .id("l112")
                                    .descriptions(Arrays.asList(
                                            Description.builder().content("level three 2").language("en").build(),
                                            Description.builder().content("niveau trois 2").language("fr").build()))
                                    .build()))
                    .build())
            .build());

    public List<LevelOne> getAllLevels() {
        return levels;
    }

    public LevelOne getLevel(String id) {
        return levels.stream().filter(levelOne -> levelOne.getId().equals(id)).findFirst().get();
    }
}
