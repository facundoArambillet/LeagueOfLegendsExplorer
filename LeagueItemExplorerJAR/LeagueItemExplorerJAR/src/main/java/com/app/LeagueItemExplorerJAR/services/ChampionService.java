package com.app.LeagueItemExplorerJAR.services;

import com.app.LeagueItemExplorerJAR.errors.ErrorNotFound;
import com.app.LeagueItemExplorerJAR.errors.ErrorResponse;
import com.app.LeagueItemExplorerJAR.models.*;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChampionService {
    private static final Logger logger =  LogManager.getLogger(ChampionService.class);
    @Value("${championsUrl}")
    private String url;

    public Object getAll() {
        try {
            String urlAll = this.url + "champion.json";
            RestTemplate restTemplate = new RestTemplate();
            ChampionInfo response = restTemplate.getForEntity(urlAll,ChampionInfo.class).getBody();

            return  response.getData();
        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            throw new ErrorResponse(e.getStatusCode(),e.getMessage());
        }

    }

    public Object getByName(String name) {
        try {
            String urlByName = this.url + "champion/" + name + ".json";
            RestTemplate restTemplate = new RestTemplate();
            ChampionInfoExtended response = restTemplate.getForEntity(urlByName,ChampionInfoExtended.class).getBody();

            return response.getData();

        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            throw new ErrorNotFound(e.getMessage());
        }

    }

    public Object getAllTags() {
        try {
            String urlAll = this.url + "champion.json";
            RestTemplate restTemplate = new RestTemplate();
            ChampionInfo response = restTemplate.getForEntity(urlAll,ChampionInfo.class).getBody();
            Map<String, ChampionData> champions = response.getData();
            Set<String> tags = new HashSet<String>();
            for(ChampionData champion : champions.values()) {
                for (String tag : champion.getTags()) {
                    tags.add(tag);
                }
            }
            return tags;
        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            return new ErrorResponse(e.getStatusCode(),e.getMessage());
        }

    }
    public Object getChampionsByTag(String tag) {
        try {
            String urlAll = this.url + "champion.json";
            RestTemplate restTemplate = new RestTemplate();
            ChampionInfo response = restTemplate.getForEntity(urlAll,ChampionInfo.class).getBody();
            Map<String, ChampionData> champions = response.getData();
            List<ChampionData> championsByTag = new ArrayList<>();
            for(ChampionData champion : champions.values()) {
                for (String tagChampion : champion.getTags()) {
                    if(tag.equals(tagChampion)) {
                        championsByTag.add(champion);
                    }
                }
            }
            return championsByTag;
        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            return new ErrorResponse(e.getStatusCode(),e.getMessage());
        }

    }
}
