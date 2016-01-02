package com.blazetorchutilities.blazetorchutilitiesmod.Items;

import com.blazetorchutilities.blazetorchutilitiesmod.BlazeTorchUtilitiesMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class ItemCapsuleBlindness extends Item{

	private float weaponDamage;
	private ToolMaterial toolMaterial;
	
	public ItemCapsuleBlindness(int par1, ToolMaterial par2EnumToolMaterial) {
		super();
		this.toolMaterial = par2EnumToolMaterial;
		this.setMaxDamage(2000);
		this.setMaxStackSize(16);
	}
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{  
	        this.itemIcon = ir.registerIcon("BlazeTorchUtilities:CapsuleBlindness");
	}

public ItemStack onItemUse(ItemStack itemstack, World world, EntityPlayer entity){
float var4 = 1.0F;
int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62D - (double)entity.yOffset);
int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);
return itemstack;
}
public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entity){
float var4 = 1.0F;
int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62D - (double)entity.yOffset);
int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);

if (entity.isPotionActive(Potion.blindness)){
	entity.removePotionEffect(Potion.blindness.id);
	entity.inventory.consumeInventoryItem(BlazeTorchUtilitiesMod.CapsuleBlindness);
	if (itemstack.stackSize <= 0)
    {
        return new ItemStack(BlazeTorchUtilitiesMod.CapsuleEmpty);
    }
	entity.inventory.addItemStackToInventory(new ItemStack(BlazeTorchUtilitiesMod.CapsuleEmpty));
}

return itemstack;
}

public int getMaxItemUseDuration(ItemStack par1ItemStack)
{
    return 8;
}
public EnumAction getItemUseAction(ItemStack par1ItemStack)
{
    return EnumAction.none;
}
public void onCreated(ItemStack itemstack, World world, EntityPlayer entity){
float var4 = 1.0F;
int i = (int)(entity.prevPosX + (entity.posX - entity.prevPosX) * (double)var4);
int j = (int)(entity.prevPosY + (entity.posY - entity.prevPosY) * (double)var4 + 1.62D - (double)entity.yOffset);
int k = (int)(entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)var4);
}

public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
{
    return true;
}
public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return true;
    }

}