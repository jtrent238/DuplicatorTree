package com.jtrent238.duplicatortree;

import com.jtrent238.duplicatortree.blocks.BlockDuplicatorTreeLog;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class BlockLoader {

	public static Block BlockDuplicatorTreeLog;
	
	/**
	 * Load Blocks.
	 */
	public static void loadBlocks() {
		
		BlockDuplicatorTreeLog = new BlockDuplicatorTreeLog(0).setBlockName("BlockDuplicatorTreeLog");
		
		registerBlocks();
	}

	/**
	 * Register Blocks.
	 */
	private static void registerBlocks() {
	
		GameRegistry.registerBlock(BlockDuplicatorTreeLog, "BlockDuplicatorTreeLog");
	}
}
