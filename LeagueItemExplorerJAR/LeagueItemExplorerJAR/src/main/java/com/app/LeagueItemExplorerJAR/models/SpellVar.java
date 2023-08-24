package com.app.LeagueItemExplorerJAR.models;

import lombok.Data;
import java.util.List;

@Data
public class SpellVar {
    private String link;
    private List<Double> coeff;
    private String key;
}
