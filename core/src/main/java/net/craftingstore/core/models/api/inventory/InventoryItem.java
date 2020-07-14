package net.craftingstore.core.models.api.inventory;

import java.util.Arrays;
import java.util.List;

public class InventoryItem {

    private String name;
    private String[] description;
    private InventoryItemType type;
    private InventoryItemIcon icon;
    private int index;

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public InventoryItemType getType() {
        return type;
    }

    public InventoryItemIcon getIcon() {
        return icon;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String... description) {
        this.description = description;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
