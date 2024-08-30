package net.blr.blrsflagsystem.item;

import net.blr.blrsflagsystem.blrsflagsystem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, blrsflagsystem.MODID);

    public static void register(IEventBus eventBus) {
         ITEMS.register(eventBus);
    }
}
