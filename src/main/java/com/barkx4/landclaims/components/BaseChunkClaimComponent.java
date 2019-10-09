package com.barkx4.landclaims.components;

import com.barkx4.landclaims.Main;
import com.barkx4.landclaims.interfaces.ChunkClaimComponent;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.extension.CloneableComponent;
import nerdhub.cardinal.components.api.util.sync.ChunkSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.chunk.Chunk;

public class BaseChunkClaimComponent implements ChunkClaimComponent, ChunkSyncedComponent
{
	private CompoundTag value = new CompoundTag();
	private Chunk chunk;
	
	public BaseChunkClaimComponent(Chunk chunk) 
	{
		this.chunk = chunk;
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
	public CloneableComponent newInstance() 
	{
		return new BaseChunkClaimComponent(this.chunk);
	}

	@Override
	public ComponentType<?> getComponentType() 
	{
		return Main.CHUNK_CLAIMS;
	}

	@Override
	public Chunk getChunk() 
	{
		return this.chunk;
	}

}