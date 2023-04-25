package dev.blockingbad.item;

import dev.blockingbad.BlockingBad;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BlockingBad.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab BLOCKING_BAD_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        BLOCKING_BAD_TAB = event.registerCreativeModeTab(
                new ResourceLocation(BlockingBad.MODID, "blockingbad_tab"),
                builder -> builder.icon(() ->
                        new ItemStack(ModItems.COFFEE_FILTER.get())
                ).title(Component.translatable("creativemodetab.blockingbad_tab"))
        );
    }
}
