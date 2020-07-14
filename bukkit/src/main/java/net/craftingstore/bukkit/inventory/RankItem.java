package net.craftingstore.bukkit.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@Getter
@AllArgsConstructor
public class RankItem {

    private String rank;
    private String[] lore;
    private Material material;
}
