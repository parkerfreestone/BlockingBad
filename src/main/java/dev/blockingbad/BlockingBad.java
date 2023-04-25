package dev.blockingbad;

import com.mojang.logging.LogUtils;
import dev.blockingbad.common.item.ModCreativeModeTabs;
import dev.blockingbad.init.BlockEntityInit;
import dev.blockingbad.init.BlockInit;
import dev.blockingbad.init.ItemInit;
import dev.blockingbad.init.MenuInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BlockingBad.MODID)
public class BlockingBad
{
    public static final String MODID = "blockingbad";
    private static final Logger LOGGER = LogUtils.getLogger();

    public BlockingBad() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Init custom content
        BlockInit.BLOCKS.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        MenuInit.MENUS.register(modEventBus);

        modEventBus.addListener(this::onCommonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::onAddCreative);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {

    }

    private void onAddCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == ModCreativeModeTabs.BLOCKING_BAD_TAB) {
            //ITEMS
            event.accept(ItemInit.COFFEE_FILTER);
            event.accept(ItemInit.COLD_MEDICINE);
            event.accept(ItemInit.COLD_MEDICINE_POWDER);

            // BLOCKS
            event.accept(BlockInit.MORTAR_AND_PESTLE);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
