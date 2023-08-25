package com.app.LeagueItemExplorerJAR.services;

import com.app.LeagueItemExplorerJAR.errors.ErrorNotFound;
import com.app.LeagueItemExplorerJAR.models.ItemData;
import com.app.LeagueItemExplorerJAR.models.ItemTree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemTreeServiceTest {
    @Autowired
    private ItemTreeService itemTreeService;

    @Test
    public void givenItemsTree_whenGetAllItems_thenMapShouldNotBeEmptyOrNull() {
        List<ItemTree> items = (List<ItemTree>) this.itemTreeService.getAll();

        assertNotNull(items, "La lista de items no deberia ser nula");
        assertFalse(items.isEmpty(), "La lista de items no debería estar vacia");
    }


//    @Test
//    public void givenItemsTreeTag_whenGetAllTags_thenListShouldNotBeEmptyOrNull() {
//        List<String> tags = (List<String>) this.itemTreeService.getAllTags();
//
//        assertNotNull(tags, "La lista de items no deberia ser nula");
//        assertFalse(tags.isEmpty(), "La lista de items no debería estar vacía");
//    }
}
