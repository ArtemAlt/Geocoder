package com.example.geocoder.entitys;

import lombok.Data;
import java.util.List;
@Data
public class Graphhopper {
    private List<Hit> hits;
    private String locale;
}
