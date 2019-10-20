package com.barkx4.landclaims.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class LandManagementGui extends LightweightGuiDescription 
{
    public LandManagementGui(PlayerEntity playerEntity, World world, Chunk chunk) 
    {  	
    	if (world.isClient) return;
    	
        
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(140, 140);
        
        WLabel lblTitle = new WLabel("Bank Balance:");
        root.add(lblTitle, 0, 0, 1, 1);
        
        root.validate(this);
    }
    
    @Override
    public void addPainters() {
        getRootPanel().setBackgroundPainter(BackgroundPainter.VANILLA); //This is done automatically though
    }
}