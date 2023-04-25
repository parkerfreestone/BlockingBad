package dev.blockingbad.init;

import dev.blockingbad.BlockingBad;
import dev.blockingbad.common.block.MortarAndPestleBlock;
import dev.blockingbad.common.block.PseudoephedrineFilterBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BlockingBad.MODID);

    public static final RegistryObject<Block> MORTAR_AND_PESTLE = register("mortar_and_pestle",
            () -> new MortarAndPestleBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1f, 20).dynamicShape().noOcclusion()),
            new Item.Properties());
//    public static final RegistryObject<Block> PSEUDOEPHEDRINE_FILTER = registerBlock("pseudoephedrine_filter", () -> new PseudoephedrineFilterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(1f).dynamicShape().noOcclusion()));

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        return block;
    }
}
