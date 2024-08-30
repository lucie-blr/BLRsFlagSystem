package net.blr.blrsflagsystem.command;

import com.mojang.brigadier.CommandDispatcher;
import net.blr.blrsflagsystem.flag.FlagEnum.Flag;
import net.blr.blrsflagsystem.flag.PlayerFlagProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.command.EnumArgument;

import java.util.HashMap;
import java.util.Map;

import static net.minecraft.commands.Commands.literal;

public class FlagsCommand {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("flags")
                        .then(Commands.argument("flag", EnumArgument.enumArgument(Flag.class))
                        .executes(context -> {
                            final ServerPlayer player = context.getSource().getPlayerOrException();

                            Flag flag = context.getArgument("flag", Flag.class);

                            CommandSourceStack source = context.getSource();

                            if (flag.getName() == null) {
                                source.sendFailure(Component.literal("You must specify a flag to set."));
                                return 0;
                            }

                            else {

                                if (!player.getPrefixes().isEmpty()) {
                                    player.getPrefixes().clear();
                                }

                                player.getPrefixes().add(Component.literal(flag.getCharEmoji() + " "));

                                player.getCapability(PlayerFlagProvider.PLAYER_FLAG).ifPresent(flagStore -> {
                                    flagStore.setFlagName(flag.getName());
                                });

                                source.sendSuccess(Component.literal("set your flag to " + flag.getCharEmoji()), true);
                                return 1;
                            }

                        }))
        );
    }
}
