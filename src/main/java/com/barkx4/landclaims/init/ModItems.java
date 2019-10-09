package com.barkx4.landclaims.init;

import com.barkx4.landclaims.items.LandDeedItem;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems 
{
    public static final LandDeedItem LAND_DEED = new LandDeedItem(new Item.Settings().group(ItemGroup.MISC).maxCount(64));
    
    public static void init()
    {
	    Registry.register(Registry.ITEM, new Identifier("landclaims", "land_deed"), LAND_DEED);
    }
}
