package dev.blockingbad.init;

import dev.blockingbad.BlockingBad;
import dev.blockingbad.menus.MortarAndPestleMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, BlockingBad.MODID);

    public static final RegistryObject<MenuType<MortarAndPestleMenu>> MORTAR_AND_PESTLE = MENUS.register("mortar_and_pestle", () ->
            new MenuType<>(MortarAndPestleMenu::getClientMenu));
}
