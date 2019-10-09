package com.barkx4.landclaims.events;

import java.util.UUID;

import com.barkx4.landclaims.claims.Claims;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.world.chunk.Chunk;

public class ModBlockEvents
{
	public static void init()
	{
		AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) ->
		{
			Chunk chLoc = world.getChunk(player.chunkX, player.chunkZ);
			
			CompoundTag claimData = Claims.get(chLoc);
			UUID UUIDowner = claimData.getUuid("owner");
			
			if(UUIDowner.compareTo(new UUID(0, 0)) == 0 || player.getUuid().compareTo(UUIDowner) == 0)
			{
				return ActionResult.PASS;
			}
			
			player.sendMessage(new LiteralText("Someone else owns this land. You may not build here."));
			return ActionResult.FAIL;
		});
		
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) ->
		{
			Chunk chLoc = world.getChunk(player.chunkX, player.chunkZ);
			
			CompoundTag claimData = Claims.get(chLoc);
			UUID UUIDowner = claimData.getUuid("owner");
			
			if(UUIDowner.compareTo(new UUID(0, 0)) == 0 || player.getUuid().compareTo(UUIDowner) == 0)
			{
				return ActionResult.PASS;
			}
			
			player.sendMessage(new LiteralText("Someone else owns this land. You may not use blocks here."));
			return ActionResult.FAIL;
		});
	}

}
