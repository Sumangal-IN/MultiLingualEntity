package com.alpha.mergelanguages.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Description {

    String content;
    String language;
}
