package com.barkx4.landclaims.components;

import com.barkx4.landclaims.Main;
import com.barkx4.landclaims.interfaces.LevelClaimComponent;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.util.sync.LevelSyncedComponent;
import net.minecraft.nbt.CompoundTag;

public class BaseLevelClaimComponent implements LevelClaimComponent, LevelSyncedComponent
{
	private CompoundTag value = new CompoundTag();
	
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
}
