package com.alpha.mergelanguages.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LevelOne {

    String id;
    List<Description> descriptions;
    LevelTwo levelTwo;
}
