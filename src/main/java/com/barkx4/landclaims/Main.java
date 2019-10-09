package com.barkx4.landclaims;

import com.barkx4.landclaims.components.BaseChunkClaimComponent;
import com.barkx4.landclaims.components.BaseLevelClaimComponent;
import com.barkx4.landclaims.components.BaseWorldClaimComponent;
import com.barkx4.landclaims.events.ModBlockEvents;
import com.barkx4.landclaims.init.ModItems;
import com.barkx4.landclaims.interfaces.ChunkClaimComponent;
import com.barkx4.landclaims.interfaces.LevelClaimComponent;
import com.barkx4.landclaims.interfaces.WorldClaimComponent;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.ChunkComponentCallback;
import nerdhub.cardinal.components.api.event.LevelComponentCallback;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer 
{
	public static final String MOD_ID = "landclaims";

	public static final ComponentType<ChunkClaimComponent> CHUNK_CLAIMS = 
	        ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("landclaims:chunk_claim_component"), ChunkClaimComponent.class);
	
	public static final ComponentType<WorldClaimComponent> WORLD_CLAIMS = 
	        ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("landclaims:world_claim_component"), WorldClaimComponent.class);
	
	public static final ComponentType<LevelClaimComponent> LEVEL_CLAIMS = 
	        ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("landclaims:level_claim_component"), LevelClaimComponent.class);
	
	@Override
    public void onInitialize()
    {       
		ModItems.init();
		ModBlockEvents.init();
		
    	// Add the component to every chunk in every world
    	ChunkComponentCallback.EVENT.register((chunk, components) -> components.put(CHUNK_CLAIMS, new BaseChunkClaimComponent(chunk)));
    	
    	// Add the component to every world
    	WorldComponentCallback.EVENT.register((world, components) -> components.put(WORLD_CLAIMS, new BaseWorldClaimComponent(world)));
    	
    	// Add the component to level properties
    	LevelComponentCallback.EVENT.register((levelProperties, components) -> components.put(LEVEL_CLAIMS, new BaseLevelClaimComponent()));
    }
}
