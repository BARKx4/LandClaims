package com.barkx4.landclaims.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.chunk.Chunk;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.barkx4.landclaims.claims.Claims;
import com.barkx4.landclaims.init.ModItems;
import com.mojang.blaze3d.platform.GlStateManager;

@Mixin(GameRenderer.class)
public class RenderCenterMixin 
{
	@Inject(method = "renderCenter",
			at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;debugRenderer:Lnet/minecraft/client/render/debug/DebugRenderer;",
                     ordinal = 0))
    public void renderCenter(float delta, long long_1, CallbackInfo callbackInfo) 
	{
		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		
		if (player.getMainHandStack().getItem() == ModItems.LAND_DEED)
		{
			Camera camera_1 = MinecraftClient.getInstance().gameRenderer.getCamera();
			Tessellator tessellator_1 = Tessellator.getInstance();
			BufferBuilder bufferBuilder_1 = tessellator_1.getBufferBuilder();
			
			double double_1 = camera_1.getPos().x;
			double double_2 = camera_1.getPos().y;
			double double_3 = camera_1.getPos().z;
			
			GlStateManager.disableTexture();
			GlStateManager.disableBlend();
			
			double double_6 = (double)(camera_1.getFocusedEntity().chunkX << 4) - double_1;
			double double_7 = (double)(camera_1.getFocusedEntity().chunkZ << 4) - double_3;
			
			Chunk chLoc = player.world.getChunk(camera_1.getFocusedEntity().chunkX, camera_1.getFocusedEntity().chunkZ);
			
			try
			{
				
				CompoundTag claimData = Claims.get(chLoc);
				UUID UUIDowner = claimData.getUuid("owner");
			
				float fRed = 1.0F;
				float fGreen = 0.0F;
				float fBlue = 0.0F;
				
				if (player.getUuid().compareTo(UUIDowner) == 0)
				{
					fRed = 0.0F; 
					fGreen = 0.0F; 
					fBlue = 1.0F;
				}
				else if (UUIDowner.compareTo(new UUID(0, 0)) == 0)
				{
					fRed = 0.0F; 
					fGreen = 1.0F; 
					fBlue = 0.0F;
				}
			
				GlStateManager.lineWidth(3.0F);
				bufferBuilder_1.begin(3, VertexFormats.POSITION_COLOR);
			
				int int_8;
				double double_9;
				
				for(int_8 = 0; int_8 <= 256; int_8 += 2) 
				{
					double_9 = (double)int_8 - double_2;
					bufferBuilder_1.vertex(double_6, double_9, double_7).color(fRed, fGreen, fBlue, 0.0F).next();
					bufferBuilder_1.vertex(double_6, double_9, double_7).color(fRed, fGreen, fBlue, 1.0F).next();
					bufferBuilder_1.vertex(double_6, double_9, double_7 + 16.0D).color(fRed, fGreen, fBlue, 1.0F).next();
					bufferBuilder_1.vertex(double_6 + 16.0D, double_9, double_7 + 16.0D).color(fRed, fGreen, fBlue, 1.0F).next();
					bufferBuilder_1.vertex(double_6 + 16.0D, double_9, double_7).color(fRed, fGreen, fBlue, 1.0F).next();
					bufferBuilder_1.vertex(double_6, double_9, double_7).color(fRed, fGreen, fBlue, 1.0F).next();
					bufferBuilder_1.vertex(double_6, double_9, double_7).color(fRed, fGreen, fBlue, 0.0F).next();
				}
	
				tessellator_1.draw();
			}
			catch (NoSuchElementException e)
			{
				
			}
			
			GlStateManager.enableBlend();
			GlStateManager.enableTexture();
	    }
	}
}
