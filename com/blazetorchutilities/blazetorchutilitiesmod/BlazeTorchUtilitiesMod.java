package com.blazetorchutilities.blazetorchutilitiesmod;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;

import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemBlazeBomber;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemBlazeBow;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemCapsuleBlindness;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemCapsuleEmpty;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemCapsuleFire;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemCapsuleRegeneration;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemCapsuleSaturation;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemCapsuleWither;
import com.blazetorchutilities.blazetorchutilitiesmod.Items.ItemPortalFinder;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Metadata;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = BlazeTorchUtilitiesMod.MODID, version = BlazeTorchUtilitiesMod.VERSION)
public class BlazeTorchUtilitiesMod
{
    public static final String MODID = "BlazeTorch Utilities Mod";
    public static final String VERSION = "0.4 Beta";
    
    @Metadata
    public static ModMetadata meta;
    
    public static Item blazeBow;
	public int blazeBowID = 1440;
	public static Item CapsuleEmpty;
	public int CapsuleEmptyID = 1445;
	public static Item CapsuleWither;
	public int CapsuleWitherID = 1446;
	public static Item CapsuleFire;
	public int CapsuleFireID = 1447;
	public static Item CapsuleBlindness;
	public int CapsuleBlindnessID = 1448;
	public static Item CapsuleRegeneration;
	public int CapsuleRegenerationID = 1449;
	public static Item CapsuleSaturation;
	public int CapsuleSaturationID = 1450;
	public static Item BlazeBomber;
	public int BlazeBomberID = 1451;
	public static Item PortalFinder;
	public int PortalFinderID = 1452;
    
    public static CreativeTabs BlazeTorchTab = new CreativeTabs("BlazeTorch_Utilities_Mod")
    {
      public Item getTabIconItem()
      {
        return blazeBow;
      }
    };
    
	/*Materials*/
	
	public static ToolMaterial Dam5 = EnumHelper.addToolMaterial("Dam5", 1, 3000, 10.0F, 1, 20);
	public static ToolMaterial Bow = EnumHelper.addToolMaterial("Bow", 3, 1500, 0.0F, 0, 8);
	public static ToolMaterial Dam23 = EnumHelper.addToolMaterial("Dam23", 1, 3000, 10.0F, 19.0F, 20);
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {

    	blazeBow = new ItemBlazeBow(blazeBowID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("BlazeBow").setCreativeTab(BlazeTorchTab);
		CapsuleEmpty = new ItemCapsuleEmpty(CapsuleEmptyID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("CapsuleEmpty").setCreativeTab(BlazeTorchTab);
		CapsuleWither = new ItemCapsuleWither(CapsuleWitherID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("CapsuleWither").setCreativeTab(BlazeTorchTab);
		CapsuleFire = new ItemCapsuleFire(CapsuleFireID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("CapsuleFire").setCreativeTab(BlazeTorchTab);
		CapsuleBlindness = new ItemCapsuleBlindness(CapsuleBlindnessID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("CapsuleBlindness").setCreativeTab(BlazeTorchTab);
		CapsuleRegeneration = new ItemCapsuleRegeneration(CapsuleRegenerationID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("CapsuleRegeneration").setCreativeTab(BlazeTorchTab);
		CapsuleSaturation = new ItemCapsuleSaturation(CapsuleSaturationID).setUnlocalizedName("CapsuleSaturation").setCreativeTab(BlazeTorchTab);
		BlazeBomber = new ItemBlazeBomber(BlazeBomberID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("BlazeBomber").setCreativeTab(BlazeTorchTab);
		PortalFinder = new ItemPortalFinder(PortalFinderID, BlazeTorchUtilitiesMod.Bow).setUnlocalizedName("PortalFinder").setCreativeTab(BlazeTorchTab);
		

		ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST, new WeightedRandomChestContent(new ItemStack(PortalFinder), 1, 1, 2));
		
		
        gameRegisters();
		
		languageRegisters();
    }
    
    private static void gameRegisters(){
		/*Item Registring*/
    	GameRegistry.registerItem(blazeBow, "Blaze Bow");
    	GameRegistry.registerItem(CapsuleEmpty, "Capsule Empty");
    	GameRegistry.registerItem(CapsuleWither, "Capsule Wither");
    	GameRegistry.registerItem(CapsuleFire, "Capsule Fire");
    	GameRegistry.registerItem(CapsuleBlindness, "Capsule Blindness");
    	GameRegistry.registerItem(CapsuleRegeneration, "Capsule Regeneration");
    	GameRegistry.registerItem(CapsuleSaturation, "Capsule Saturation");
    	GameRegistry.registerItem(BlazeBomber, "Blaze Bomber");
    	GameRegistry.registerItem(PortalFinder, "Portal Finder");
  
		
    	/*Recipes*/
		
    	GameRegistry.addRecipe(new ItemStack(CapsuleEmpty, 1), 
				new Object[]{" G ", "GSG", " G ", 'G', Blocks.glass, 'S', Items.glowstone_dust});
		GameRegistry.addRecipe(new ItemStack(PortalFinder, 1), 
				new Object[]{"FGF", " S ", " S ", 'G', Blocks.glowstone, 'S', Items.stick, 'F', Items.firework_charge});
		GameRegistry.addShapelessRecipe(new ItemStack(CapsuleWither, 1), 
				new Object[]{ Items.coal,  Items.bone,  new ItemStack(CapsuleEmpty)});
		GameRegistry.addShapelessRecipe(new ItemStack(CapsuleFire, 1), 
				new Object[]{ Items.lava_bucket,  Items.glowstone_dust,  new ItemStack(CapsuleEmpty)});
		GameRegistry.addShapelessRecipe(new ItemStack(CapsuleBlindness, 1), 
				new Object[]{ Items.carrot,  Items.gold_nugget,  new ItemStack(CapsuleEmpty)});
		GameRegistry.addShapelessRecipe(new ItemStack(CapsuleRegeneration, 1), 
				new Object[]{ Items.apple,  Items.glowstone_dust,  new ItemStack(CapsuleEmpty)});
		GameRegistry.addShapelessRecipe(new ItemStack(CapsuleSaturation, 1), 
				new Object[]{ Items.rotten_flesh,  Items.glowstone_dust,  new ItemStack(CapsuleEmpty)});
		GameRegistry.addRecipe(new ItemStack(blazeBow, 1), 
				new Object[]{" BS", " S ", "FQQ", 'B', Items.bow, 'S', Items.stick, 'F', Items.flint_and_steel, 'Q', Items.quartz});
		GameRegistry.addRecipe(new ItemStack(BlazeBomber, 1), 
				new Object[]{"BSS", "SS ", "FQQ", 'B', Items.bow, 'S', Blocks.netherrack, 'F', Items.flint_and_steel, 'Q', Blocks.nether_brick});
	
	}
	
	private static void languageRegisters(){
		
		
		LanguageRegistry.addName(blazeBow, "BlazeTorch's Torch-Gun");
		LanguageRegistry.addName(CapsuleEmpty, "Empty Capsule");
		LanguageRegistry.addName(CapsuleWither, "Anti-Wither Capsule");
		LanguageRegistry.addName(CapsuleFire, "Anti-Burning Capsule");
		LanguageRegistry.addName(CapsuleBlindness, "Anti-Blindness Capsule");
		LanguageRegistry.addName(CapsuleRegeneration, "One-More-Chance Capsule");
		LanguageRegistry.addName(CapsuleSaturation, "Less-Hunger Capsule");
		LanguageRegistry.addName(BlazeBomber, "BlazeTorch's Charge-Thrower");
		LanguageRegistry.addName(PortalFinder, "Hostile Mob Finder");
		/*CreativeTab*/
		LanguageRegistry.instance().addStringLocalization("itemGroup.BlazeTorch_Utilities_Mod", "BlazeTorch Utilities Mod");
		
	}
	
    
}
    

