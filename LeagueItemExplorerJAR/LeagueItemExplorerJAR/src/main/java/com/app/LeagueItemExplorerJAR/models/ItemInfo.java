package com.app.LeagueItemExplorerJAR.models;

import lombok.Data;

import java.util.Map;

@Data
public class ItemInfo {
    private String type;
    private String version;
    private Map<String,ItemData> data;
    private ItemTree[] tree;
}
