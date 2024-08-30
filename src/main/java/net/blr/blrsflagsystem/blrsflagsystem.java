package net.blr.blrsflagsystem;

import com.mojang.logging.LogUtils;
import net.blr.blrsflagsystem.command.FlagsCommand;
import net.blr.blrsflagsystem.event.ModEvents;
import net.blr.blrsflagsystem.flag.PlayerFlag;
import net.blr.blrsflagsystem.flag.PlayerFlagProvider;
import net.blr.blrsflagsystem.item.Items;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(blrsflagsystem.MODID)
public class blrsflagsystem
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "blrsflagsystem";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace

    public blrsflagsystem()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(FlagsCommand.class);
        MinecraftForge.EVENT_BUS.register(Items.class);
        MinecraftForge.EVENT_BUS.register(ModEvents.class);
        MinecraftForge.EVENT_BUS.register(PlayerFlag.class);
        MinecraftForge.EVENT_BUS.register(PlayerFlagProvider.class);

        // Register the items
        Items.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        FlagsCommand.register(event.getDispatcher());
    }

}
