package com.jtrent238.duplicatortree;

import com.jtrent238.duplicatortree.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandManager;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

@Mod(modid=Main.MODID, name=Main.MODNAME, version=(Main.MODVERSION)/*, dependencies="required-after:jtrent238core@[" + YouTubers.COREVERSION + "]"*/)
//@MeddleMod(id=Main.MODID, name=Main.MODNAME, version=(Main.MODVERSION), author=Main.MODAUTHOR)
public class Main 
{

	
	@SidedProxy(clientSide="com.jtrent238.duplicatortree.proxy.ClientProxy", serverSide="com.jtrent238.duplicatortree.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	
	public static final String MODID = "duplicatortree";


	@Instance(MODID)
  public static Main instance;
	public static final String MODVERSION = "1.0.0.0";
	public final static String COREVERSION = "1.0.0.3";
	public static final String MODNAME = "Duplicator Tree";
	public static final String MODAUTHOR = "jtrent238";
	public static final String MC = "1.7.10";

	@ForgeSubscribe(priority = EventPriority.NORMAL)
  public void eventHandler(RenderGameOverlayEvent event) {

	}
	
	
@Mod.EventHandler
public void preInit(FMLPreInitializationEvent event)
{
	
}



@Mod.EventHandler
public void init(FMLInitializationEvent event)
{
	proxy.init(event);
	
	BlockLoader.loadBlocks();
}


@Mod.EventHandler
public void postInit(FMLPostInitializationEvent event) {
	{

		
	}
}


@EventHandler
public void serverStart(FMLServerStartingEvent event)
{
   MinecraftServer server = MinecraftServer.getServer();
   // Get's the current server instance
   
   ICommandManager command = server.getCommandManager();
   //ServerCommandManager manager = (ServerCommandManager) command;
   
   //manager.registerCommand(new CommandModInfo());
   //manager.registerCommand(new CommandChangelog());
}


public static boolean setBlockFast(World world, int par1, int par2, int par3, Block par4, int par5, int par6) {
    if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
      if (par2 < 0)
        return false; 
      if (par2 >= 256)
        return false; 
      Chunk chunk = world.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
      Block k1 = Blocks.air;
      if ((par6 & 0x1) != 0)
        k1 = chunk.getBlock(par1 & 0xF, par2, par3 & 0xF); 
      boolean flag = setBlockIDWithMetadataFast(chunk, par1 & 0xF, par2, par3 & 0xF, par4, par5);
      if (flag) {
        if ((par6 & 0x2) != 0 && (!world.isRemote || (par6 & 0x4) == 0))
          world.markBlockForUpdate(par1, par2, par3); 
        if (!world.isRemote && (par6 & 0x1) != 0)
          world.notifyBlockChange(par1, par2, par3, k1); 
      } 
      return flag;
    } 
    return false;
  }


public static boolean setBlockSuperFast(World world, int par1, int par2, int par3, Block par4, int par5, int par6, Chunk refChunk) {
    if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
      if (par2 < 0)
        return false; 
      if (par2 >= 256)
        return false; 
      Chunk chunk = world.getChunkFromChunkCoords(par1 >> 4, par3 >> 4);
      boolean flag = true;
      if (chunk != refChunk) {
        Block k1 = Blocks.air;
        if ((par6 & 0x1) != 0)
          k1 = chunk.getBlock(par1 & 0xF, par2, par3 & 0xF); 
        flag = setBlockIDWithMetadataFast(chunk, par1 & 0xF, par2, par3 & 0xF, par4, par5);
        if (flag) {
          if ((par6 & 0x2) != 0 && (!world.isRemote || (par6 & 0x4) == 0))
            world.markBlockForUpdate(par1, par2, par3); 
          if (!world.isRemote && (par6 & 0x1) != 0)
            world.notifyBlockChange(par1, par2, par3, k1); 
        } 
      } else {
        setBlockIDWithMetadataFast(chunk, par1 & 0xF, par2, par3 & 0xF, par4, par5);
      } 
      return flag;
    } 
    return false;
  }
  
  public static boolean setBlockIDWithMetadataFast(Chunk chunk, int par1, int par2, int par3, Block par4, int par5) {
    if (par1 >= -30000000 && par3 >= -30000000 && par1 < 30000000 && par3 < 30000000) {
      if (par2 < 0 || par2 > 255)
        return false; 
      ExtendedBlockStorage[] mystorage = chunk.getBlockStorageArray();
      ExtendedBlockStorage extendedblockstorage = mystorage[par2 >> 4];
      if (extendedblockstorage == null) {
        if (par4 == Blocks.air)
          return false; 
        extendedblockstorage = mystorage[par2 >> 4] = new ExtendedBlockStorage(par2 >> 4 << 4, !chunk.worldObj.provider.hasNoSky);
        chunk.setStorageArrays(mystorage);
      } 
      extendedblockstorage.func_150818_a(par1, par2 & 0xF, par3, par4);
      extendedblockstorage.setExtBlockMetadata(par1, par2 & 0xF, par3, par5);
      return true;
    } 
    return false;
  }

					
}