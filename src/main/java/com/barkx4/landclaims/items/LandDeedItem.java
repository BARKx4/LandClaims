package com.barkx4.landclaims.items;

import java.util.UUID;

import com.barkx4.landclaims.claims.Claims;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class LandDeedItem extends Item 
{

	public LandDeedItem(Settings settings) 
	{
		super(settings);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) 
	{
		ItemStack itemStack = playerEntity.getStackInHand(hand);
		
		if (!world.isClient)
		{
			Chunk chLoc = world.getChunk(playerEntity.chunkX, playerEntity.chunkZ);
			
			CompoundTag claimData = Claims.get(chLoc);
			UUID UUIDowner = claimData.getUuid("owner");
			
			if (playerEntity.getUuid().compareTo(UUIDowner) == 0)
			{
				playerEntity.sendMessage(new LiteralText("You own this land claim."));
			}
			else if (UUIDowner.compareTo(new UUID(0, 0)) == 0)
			{
				playerEntity.sendMessage(new LiteralText("Nobody owns this land. Claiming it."));
				claimData.putUuid("owner", playerEntity.getUuid());
				Claims.set(chLoc, claimData);
				itemStack.setCount(itemStack.getCount() - 1);
			}
			else
			{
				playerEntity.sendMessage(new LiteralText("Someone else owns this land. Cannot claim it."));
			}
			
		}
		
	    return new TypedActionResult<ItemStack>(ActionResult.PASS, itemStack);
	}

}
