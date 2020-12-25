package com.jtrent238.duplicatortree.blocks;

import java.awt.List;
import java.util.Random;

import com.jtrent238.duplicatortree.BlockLoader;
import com.jtrent238.duplicatortree.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDuplicatorTreeLog extends Block {
	
	  public BlockDuplicatorTreeLog(int par1) {
		    super(Material.wood);
		    setCreativeTab(CreativeTabs.tabBlock);
		    setTickRandomly(true);
		  }
		  
		  public int tickRate() {
		    return 1;
		  }
		  
		  public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		    if (par1World.isRemote)
		      return; 
		      DuplicatorTree(par1World, par2, par3, par4); 
		  }
		  

		  
		  public void DuplicatorTree(World world, int x, int y, int z) {
			    int realy = y;
			    Block bid = world.getBlock(x, y - 1, z);
			    if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.farmland) {
			      bid = world.getBlock(x, y - 2, z);
			      if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.farmland) {
			        bid = world.getBlock(x, y - 3, z);
			        if (bid != Blocks.grass && bid != Blocks.dirt && bid != Blocks.farmland)
			          return; 
			        realy = y - 3;
			      } else {
			        realy = y - 2;
			      } 
			      return;
			    } 
			    realy = y - 1;
			    bid = world.getBlock(x, realy + 1, z);
			    if (bid != BlockLoader.BlockDuplicatorTreeLog) {
			      Main.setBlockFast(world, x, realy + 1, z, BlockLoader.BlockDuplicatorTreeLog, 0, 2);
			      return;
			    } 
			    bid = world.getBlock(x, realy + 2, z);
			    if (bid != BlockLoader.BlockDuplicatorTreeLog) {
			      Main.setBlockFast(world, x, realy + 2, z, BlockLoader.BlockDuplicatorTreeLog, 0, 2);
			      return;
			    } 
			    bid = world.getBlock(x, realy + 3, z);
			    if (bid != BlockLoader.BlockDuplicatorTreeLog) {
			      Main.setBlockFast(world, x, realy + 3, z, BlockLoader.BlockDuplicatorTreeLog, 0, 2);
			      return;
			    } 
			    bid = world.getBlock(x, realy + 4, z);
			    if (bid != Blocks.leaves) {
			      Main.setBlockFast(world, x, realy + 4, z, Blocks.leaves, 0, 2);
			      return;
			    } 
			    int i;
			    for (i = -1; i <= 1; i++) {
			      for (int j = -1; j <= 1; j++) {
			        if (j != 0 || i != 0) {
			          bid = world.getBlock(x + i, realy + 3, z + j);
			          if (bid != Blocks.leaves) {
			            Main.setBlockFast(world, x + i, realy + 3, z + j, Blocks.leaves, 0, 2);
			            return;
			          } 
			        } 
			      } 
			    } 
			    Block bidm = Blocks.air;
			    for (int tries = 0; tries < 20 && (bidm == Blocks.air || bidm == BlockLoader.BlockDuplicatorTreeLog); tries++) {
			      i = world.rand.nextInt(5) - 2;
			      int j = world.rand.nextInt(5) - 2;
			      bidm = world.getBlock(x + i, realy + 1, z + j);
			      int meta = world.getBlockMetadata(x + i, realy + 1, z + j);
			      if (bidm != Blocks.air && bidm != BlockLoader.BlockDuplicatorTreeLog)
			        for (int k = 0; k < 20; k++) {
			          i = world.rand.nextInt(5) - 2;
			          j = world.rand.nextInt(5) - 2;
			          bid = world.getBlock(x + i, realy + 1, z + j);
			          if (bid == Blocks.air) {
			            world.setBlock(x + i, realy + 1, z + j, bidm, meta, 2);
			            return;
			          } 
			        }  
			    } 
			  }

		protected ItemStack createStackedBlock(int par1) {
		    return new ItemStack(this, 1, 0);
		  }
		  
		  public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
		    return true;
		  }
		  
		  public boolean isWood(IBlockAccess world, int x, int y, int z) {
		    return true;
		  }
		  
		  public int quantityDropped(Random par1Random) {
		    return 1;
		  }
		  
		  @SideOnly(Side.CLIENT)
		  public void registerBlockIcons(IIconRegister iconRegister) {
		    this.blockIcon = iconRegister.registerIcon(Main.MODID + ":" + getUnlocalizedName().substring(5));
		  }
		}