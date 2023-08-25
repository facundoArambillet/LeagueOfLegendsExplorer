package com.app.LeagueItemExplorerJAR.services;

import com.app.LeagueItemExplorerJAR.errors.ErrorResponse;
import com.app.LeagueItemExplorerJAR.models.ItemInfo;
import com.app.LeagueItemExplorerJAR.models.ItemTree;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ItemTreeService {
    private static final Logger logger =  LogManager.getLogger(ChampionService.class);
    @Value("${itemsUrl}")
    private String url;
    public Object getAll() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            ItemInfo response = restTemplate.getForEntity(url,ItemInfo.class).getBody();
//            List<ItemTree> itemsTree = new ArrayList<>();
//            for(ItemTree item : response.getTree()) {
//                itemsTree.add(item);
//            }
//            return itemsTree;

            return  Stream.of(response)
                    .flatMap(itemInfo -> Arrays.stream(itemInfo.getTree()))
                    .collect(Collectors.toList());
        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            return new ErrorResponse(e.getStatusCode(),e.getMessage());
        }
    }

    public Object getAllTags() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            ItemInfo response = restTemplate.getForEntity(url,ItemInfo.class).getBody();
//            List<String> tags = new ArrayList<String>();
//            for(ItemTree item : response.getTree()) {
//                for(String tag : item.getTags()) {
//                    tags.add(tag);
//                }
//            }
//          return tags;

//          return Stream.of(response)
//              .map(itemInfo -> Arrays.stream(itemInfo.getTree())
//                      .map(itemTree -> itemTree.getTags())
//                              .toList());
//            return
//                Stream.of(response)
//                 .map(itemInfo -> Arrays.stream(itemInfo.getTree())
//                         .map(itemTree -> Arrays.stream(itemTree.getTags())
//                                 .collect(Collectors.toList())));
            return
                Stream.of(response)
                    .flatMap(itemInfo -> Arrays.stream(itemInfo.getTree())
                            .flatMap(itemTree -> Arrays.stream(itemTree.getTags())))
                    .collect(Collectors.toList());
        } catch (HttpClientErrorException e) {
            logger.info(e.getMessage());
            return new ErrorResponse(e.getStatusCode(),e.getMessage());
        }
    }
}
