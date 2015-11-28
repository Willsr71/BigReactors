package erogenousbeef.bigreactors.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import erogenousbeef.bigreactors.common.BigReactors;
import erogenousbeef.bigreactors.common.item.ItemIngot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class BlockBRMetal extends Block {

    public static final int METADATA_YELLORIUM = 0;
    public static final int METADATA_CYANITE = 1;
    public static final int METADATA_GRAPHITE = 2;
    public static final int METADATA_BLUTONIUM = 3;
    public static final int METADATA_LUDICRITE = 4;

    private static final String[] _subBlocks = new String[]{"blockYellorium", "blockCyanite", "blockGraphite", "blockBlutonium", "blockLudicrite"};
    private static final String[] _materials = new String[]{"Yellorium", "Cyanite", "Graphite", "Blutonium", "Ludicrite"};
    private static final int NUM_BLOCKS = _subBlocks.length;
    private IIcon[] _icons = new IIcon[_subBlocks.length];

    public BlockBRMetal() {
        super(Material.iron);
        this.setCreativeTab(BigReactors.TAB);
        this.setBlockName("brMetal");
        this.setHardness(2f);
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        metadata = Math.max(0, Math.min(NUM_BLOCKS - 1, metadata));
        return _icons[metadata];
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (int i = 0; i < NUM_BLOCKS; i++) {
            _icons[i] = iconRegister.registerIcon(BigReactors.TEXTURE_NAME_PREFIX + getUnlocalizedName() + "." + _subBlocks[i]);
        }
    }

    @Override
    public int damageDropped(int metadata) {
        return metadata;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < NUM_BLOCKS; i++) {
            par3List.add(new ItemStack(item, 1, i));
        }
    }

    public ItemStack getItemStackForMaterial(String name) {
        int i = 0;

        for (i = 0; i < NUM_BLOCKS; i++) {
            if (name.equals(_materials[i])) {
                break;
            }
        }

        return new ItemStack(this, 1, i);
    }

    public void registerOreDictEntries() {
        for (int i = 0; i < NUM_BLOCKS; i++) {
            OreDictionary.registerOre(_subBlocks[i], new ItemStack(this, 1, i));
        }
    }

    public void registerIngotRecipes(ItemIngot ingotItem) {
        for (int i = 0; i < NUM_BLOCKS; i++) {
            ItemStack block = new ItemStack(this, 1, i);
            ItemStack ingot = ingotItem.getIngotItem(_materials[i]);
            GameRegistry.addShapelessRecipe(block, ingot, ingot, ingot, ingot, ingot, ingot, ingot, ingot, ingot);
            ingot.stackSize = 9;
            GameRegistry.addShapelessRecipe(ingot, block);
        }
    }
}
