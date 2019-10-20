package com.barkx4.landclaims.events;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.ActionResult;

public class ModItemEvents 
{
	public static void init()
	{
		UseItemCallback.EVENT.register((player, world, hand) -> {
				
			return ActionResult.FAIL;
		});
	}
}
