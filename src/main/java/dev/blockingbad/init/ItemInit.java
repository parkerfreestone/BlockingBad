package dev.blockingbad.init;

import dev.blockingbad.BlockingBad;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BlockingBad.MODID);

    // Basic Items
    public static final RegistryObject<Item> COFFEE_FILTER = ITEMS.register("coffee_filter", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COLD_MEDICINE_POWDER = ITEMS.register("cold_medicine_powder", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COLD_MEDICINE = ITEMS.register("cold_medicine", () -> new Item(new Item.Properties()));
}
