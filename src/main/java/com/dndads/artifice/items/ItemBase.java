package com.dndads.artifice.items;

import com.dndads.artifice.Artifice;
import net.minecraft.item.Item;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().tab(Artifice.ARTIFICE_GROUP));
    }
}
