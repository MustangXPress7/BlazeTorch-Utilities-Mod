package com.blazetorchutilities.blazetorchutilitiesmod.Items;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class ItemBlazeTrainer extends Item{

	public ItemBlazeTrainer(int par1, ToolMaterial par2EnumToolMaterial) {
		super();
		this.setMaxDamage(1000);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{  
	        this.itemIcon = ir.registerIcon("BlazeTorchUtilities:PortalFinder");
	}
	
	
	public boolean isFull3D()
    {
        return true;
    }
public int getItemEnchantability()
{
    return 20;
}
public int getMaxItemUseDuration(ItemStack par1ItemStack)
{
    return 100000;
}
public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
{
    return 1.5F;
}
public int getDamageVsEntity(Entity par1Entity)
{
    return 5;
}

public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){

	if (!par2World.isRemote)
	{
	EntityGhast fireball2 = new EntityGhast(par2World);
	}
{
float closest = Float.MAX_VALUE;
Entity thisOne=null;
for (int i = 0; i < par2World.loadedEntityList.size(); i++)
{
if (((Entity)par2World.loadedEntityList.get(i)).getDistanceToEntity(par3EntityPlayer)<closest)
{	
if (par2World.loadedEntityList.get(i) instanceof Entity) //if it is a mob...
{
closest = ((Entity)par2World.loadedEntityList.get(i)).getDistanceToEntity(par3EntityPlayer);
thisOne = ((Entity)par2World.loadedEntityList.get(i));
}
}
}
if (thisOne!=null)
{
	/*
	par3EntityPlayer.addVelocity(thisOne.posX - par3EntityPlayer.posX, thisOne.posY - par3EntityPlayer.posY, thisOne.posZ - par3EntityPlayer.posZ);
	*/
	/*
	thisOne.tasks.addTask(6, new EntityAIMate(this, 1.0D));
	thisOne.hitByEntity.
	par2World.spawnParticle("fireworksSpark", (double)par3EntityPlayer.posX, (double)par3EntityPlayer.posY, (double)par3EntityPlayer.posZ, thisOne.posX - par3EntityPlayer.posX, thisOne.posY - par3EntityPlayer.posY, thisOne.posZ - par3EntityPlayer.posZ);
*/
}

/*
{
float closest = Float.MAX_VALUE;
Entity thisOne=null;
for (int i = 0; i < par2World.loadedEntityList.size(); i++)
{
if (((Entity)par2World.loadedEntityList.get(i)).getDistanceToEntity(par3EntityPlayer)<closest)
{
if (par2World.loadedEntityList.get(i) instanceof EntityMob) //if it is a mob...
{
closest = ((Entity)par2World.loadedEntityList.get(i)).getDistanceToEntity(par3EntityPlayer);
thisOne = ((Entity)par2World.loadedEntityList.get(i));
}
}
}
if (thisOne!=null)
{
par2World.addWeatherEffect(new EntityLightningBolt(par2World,thisOne.posX,thisOne.posY, thisOne.posZ));
}
*/
return par1ItemStack;
}
}

public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
{
	par1ItemStack.damageItem(1, par3EntityLivingBase);
    return true;
}

public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack)
{
    return true;
}

}
