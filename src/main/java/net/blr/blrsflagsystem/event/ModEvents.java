package net.blr.blrsflagsystem.event;

import net.blr.blrsflagsystem.blrsflagsystem;
import net.blr.blrsflagsystem.command.FlagsCommand;
import net.blr.blrsflagsystem.flag.FlagEnum;
import net.blr.blrsflagsystem.flag.PlayerFlag;
import net.blr.blrsflagsystem.flag.PlayerFlagProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = blrsflagsystem.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerFlagProvider.PLAYER_FLAG).isPresent()) {
                event.addCapability(new ResourceLocation(blrsflagsystem.MODID, "properties"), new PlayerFlagProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerFlagProvider.PLAYER_FLAG).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerFlagProvider.PLAYER_FLAG).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerFlag.class);
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        event.getEntity().getCapability(PlayerFlagProvider.PLAYER_FLAG).ifPresent(flagStore -> {
            if (flagStore.getFlagName() != null) {

                FlagEnum.Flag flag = FlagEnum.Flag.valueOf(flagStore.getFlagName());

                event.getEntity().getPrefixes().add(Component.literal( flag.getCharEmoji() + " "));
            }
        });
    }
}
