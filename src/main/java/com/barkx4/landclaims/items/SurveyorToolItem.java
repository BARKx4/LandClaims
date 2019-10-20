package com.barkx4.landclaims.items;

import java.util.UUID;

import com.barkx4.economy.gui.BankChestGui;
import com.barkx4.economy.gui.BankChestScreen;
import com.barkx4.landclaims.claims.Claims;
import com.barkx4.landclaims.gui.LandManagementGui;
import com.barkx4.landclaims.gui.LandManagementScreen;

import net.minecraft.client.MinecraftClient;
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

public class SurveyorToolItem extends Item
{

	public SurveyorToolItem(Settings settings) 
	{
		super(settings);
		// TODO Auto-generated constructor stub
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
				playerEntity.sendMessage(new LiteralText("You own this land."));
				
				MinecraftClient.getInstance().openScreen(new LandManagementScreen(new LandManagementGui(playerEntity, world, chLoc)));
			}
			else if (UUIDowner.compareTo(new UUID(0, 0)) == 0)
			{
				playerEntity.sendMessage(new LiteralText("Nobody owns this land."));
			}
			else
			{
				playerEntity.sendMessage(new LiteralText("Someone else owns this land."));
			}
			
		}
		
	    return new TypedActionResult<ItemStack>(ActionResult.PASS, itemStack);
	}

}
