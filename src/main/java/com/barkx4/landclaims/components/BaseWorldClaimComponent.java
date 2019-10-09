package com.barkx4.landclaims.components;

import com.barkx4.landclaims.Main;
import com.barkx4.landclaims.interfaces.WorldClaimComponent;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.WorldSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public class BaseWorldClaimComponent implements WorldClaimComponent, WorldSyncedComponent
{
	private CompoundTag value = new CompoundTag();
	private World world;
	
	public BaseWorldClaimComponent(World world) 
	{
		this.world = world;
	}
	
	@Override
	public void set(CompoundTag claimData) 
	{
		value = claimData;
		this.markDirty();
	}

	@Override
	public CompoundTag get() 
	{
		return value;
	}

	@Override public void fromTag(CompoundTag tag) { this.value = tag.getCompound("claimData"); }
    @Override public CompoundTag toTag(CompoundTag tag) { tag.put("claimData", this.value); return tag; }

	@Override
	public ComponentType<?> getComponentType() 
	{
		return Main.LEVEL_CLAIMS;
	}

	@Override
	public World getWorld() 
	{
		return this.world;
	}
}
