package dev.blockingbad.init;

import dev.blockingbad.BlockingBad;
import dev.blockingbad.common.blockentities.MortarAndPestleBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BlockingBad.MODID);

    public static final RegistryObject<BlockEntityType<MortarAndPestleBlockEntity>> MORTAR_AND_PESTLE =
            BLOCK_ENTITIES.register("mortar_and_pestle",
                    () -> BlockEntityType.Builder.of(MortarAndPestleBlockEntity::new, BlockInit.MORTAR_AND_PESTLE.get()).build(null));
}
