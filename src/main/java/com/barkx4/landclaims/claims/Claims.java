package com.barkx4.landclaims.claims;

import java.util.UUID;

import com.barkx4.landclaims.Main;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.chunk.Chunk;

public class Claims 
{
	public static void set(Chunk chunk, CompoundTag claimData)
	{
		Main.CHUNK_CLAIMS.get(chunk).set(claimData);
	}
	
	public static CompoundTag get(Chunk chunk)
	{
		return Main.CHUNK_CLAIMS.get(chunk).get();
	}
	
	public static UUID getOwner(Chunk chunk)
	{
		return Main.CHUNK_CLAIMS.get(chunk).get().getUuid("owner");
	}
	
	public static boolean isFriend(Chunk chunk, PlayerEntity player)
	{
		CompoundTag tagFriends = Main.CHUNK_CLAIMS.get(chunk).get().getCompound("friends");
		
		if (tagFriends.containsKey(player.getUuidAsString())) return true;
		else return false;
	}
	
	public static boolean canInteract(Chunk chunk, PlayerEntity player)
	{
		return true;
	}
	
	public static boolean canBuild(Chunk chunk, PlayerEntity player)
	{
		return true;
	}
}