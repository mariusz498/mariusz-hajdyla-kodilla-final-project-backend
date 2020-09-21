package com.kodilla.backend.hereApi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HereItem {

    @JsonProperty("items")
    private List<HereApiLocation> locations;
}
