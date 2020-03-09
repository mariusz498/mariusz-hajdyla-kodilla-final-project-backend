package com.kodilla.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class HereItem {

    @JsonProperty("items")
    private List<HereApiLocation> locations;
}
