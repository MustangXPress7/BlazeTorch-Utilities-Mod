package com.blazetorchutilities.blazetorchutilitiesmod.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBlazeBow extends ItemBow
{
    private ToolMaterial toolMaterial;
    /*
    private Icon icon;
    private Icon iconIndex;
    
    public static final String[] bowPullIconNameArray = new String[] {"BlazeTorchUtilities:BlazeBow_1", "BlazeTorchUtilities:BlazeBow_2"};
*/
	/*
	public static final String[] bowPullIconNameArray = new String[] {"bow_pulling_0", "bow_pulling_1", "bow_pulling_2"};
	@SideOnly(Side.CLIENT)
    private Icon[] iconArray;
    */
    public ItemBlazeBow(int par1, ToolMaterial par2EnumToolMaterial)
    {
        super();
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.toolMaterial = par2EnumToolMaterial;
    }

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{  
	        this.itemIcon = ir.registerIcon("BlazeTorchUtilities:BlazeBow");
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
 
        if (flag || par3EntityPlayer.inventory.hasItem(Items.arrow))
        {
            float f = (float)j / 7.5F;
            f = (f * f + f * 2.0F) / 1.5F;

            if ((double)f < 0.1D)
            {
                return;
            }

            EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);

            if (f >= 0.5F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (f >= 1.5F)
            {
                entityarrow.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(entityarrow);
            }
        }
        
        
    }

    private void setRotation(double par7, double par8) {
		// TODO Auto-generated method stub
		
	}
    
    public boolean isFull3D()
    {
        return true;
    }

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        return par1ItemStack;
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
        	par2World.playSoundAtEntity(par3EntityPlayer, "fire.ignite", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
        	par2World.playSoundAtEntity(par3EntityPlayer, "fire.fire", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1 * 0.5F);
        	par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
     /*
    @SideOnly(Side.CLIENT)

    private Icon[] Texture = new Icon[3];
    public void registerIcons(IconRegister iconRegister)
    {

        iconIndex = iconRegister.registerIcon("BlazeBow" + this.getUnlocalizedName().substring(4) + "");
        for (int N = 0; N < 3; N++)
        {
            this.Texture[N] = iconRegister.registerIcon("BlazeBow" + this.getUnlocalizedName().substring(4) + "_" + N);
        }
    }
    
    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if(player.getItemInUse() == null) return this.iconIndex;
        int Pulling = stack.getMaxItemUseDuration() - useRemaining;
        if (Pulling >= 10)
        {
            return Texture[2];
        }
        else if (Pulling > 5)
        {
            return Texture[1];
        }
        return Texture[0];
    }
*/
    /*
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.func_111208_A() + "_standby");
        this.iconArray = new Icon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(this.func_111208_A() + "_" + bowPullIconNameArray[i]);
        }
    }
    */

    /*
    @SideOnly(Side.CLIENT)

    /**
     * used to cycle through icons based on their used duration, i.e. for the bow
     */
    /*
    public Icon getItemIconForUseDuration(int par1)
    {
        return this.iconArray[par1];
    }
    */
}
