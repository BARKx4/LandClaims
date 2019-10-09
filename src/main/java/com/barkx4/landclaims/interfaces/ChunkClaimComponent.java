package com.barkx4.landclaims.interfaces;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;

public interface ChunkClaimComponent extends Component
{
	void set(CompoundTag claimData);
	CompoundTag get();
}
