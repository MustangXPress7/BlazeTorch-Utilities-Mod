package com.blazetorchutilities.blazetorchutilitiesmod.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBlazeBomber extends ItemBow
{
    private ToolMaterial toolMaterial;

	/*
	public static final String[] bowPullIconNameArray = new String[] {"bow_pulling_0", "bow_pulling_1", "bow_pulling_2"};
	@SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    */
    public ItemBlazeBomber(int par1, ToolMaterial par2EnumToolMaterial)
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(384);
    }
    
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{  
	        this.itemIcon = ir.registerIcon("BlazeTorchUtilities:BlazeBomber");
	}
    
    
    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
    	int j = this.getMaxItemUseDuration(par1ItemStack) - par4;

        ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Items.fire_charge))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityLargeFireball entityarrow = new EntityLargeFireball(par2World, par3EntityPlayer, 1, 1, 1);

            if (f == 1.0F)
            {
                entityarrow.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par3EntityPlayer.inventory.consumeInventoryItem(Items.fire_charge);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.explode", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (!par2World.isRemote)
            {
            	Vec3 look = par3EntityPlayer.getLookVec();
            	entityarrow.setPosition(par3EntityPlayer.posX + look.xCoord, par3EntityPlayer.posY + look.yCoord, par3EntityPlayer.posZ + look.zCoord);
            	entityarrow.accelerationX = look.xCoord * f;
            	entityarrow.accelerationY = look.yCoord * f;
            	entityarrow.accelerationZ = look.zCoord * f;
            	par2World.spawnEntityInWorld(entityarrow);
            }
        }
    }

    private void setRotation(double par7, double par8) {
		// TODO Auto-generated method stub
		
	}

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Items.arrow))
        {
        	par2World.playSoundAtEntity(par3EntityPlayer, "random.fizz", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
        	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    
    public boolean isFull3D()
    {
        return true;
    }
    
    public int getItemEnchantability()
    {
        return 1;
    }
   
        
}
