package com.app.LeagueItemExplorerJAR.services;

import com.app.LeagueItemExplorerJAR.errors.ErrorNotFound;
import com.app.LeagueItemExplorerJAR.models.ItemData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ItemDataServiceTest {
    @Autowired
    private ItemDataService itemDataService;

    @Test
    public void givenItemsData_whenGetAllItems_thenMapShouldNotBeEmptyOrNull() {
        Map<String,ItemData> items = (Map<String, ItemData>) this.itemDataService.getAll();

        assertNotNull(items, "El map items no deberia ser nulo");
        assertFalse(items.isEmpty(), "El map items no debería estar vacio");
    }

    @Test
    public void givenValidName_whenGetByName_thenItemDataShouldNotBeNull() {
        String itemName = "dark-seal";

        Map.Entry<String, ItemData> item = (Map.Entry<String, ItemData>) this.itemDataService.getByName(itemName);

        assertNotNull(item, "El Item no debería ser nulo");
        assertTrue(item.getValue().getName().equals("Dark Seal"),"El nombre del objeto deberia ser verdadero");
    }

    @Test
    public void givenInvalidName_whenGetByName_thenItemDataShouldBeThrown() {
        String invalidItemName = "Item";

        assertThrows(ErrorNotFound.class, () -> {
            this.itemDataService.getByName(invalidItemName);
        });
    }
}
