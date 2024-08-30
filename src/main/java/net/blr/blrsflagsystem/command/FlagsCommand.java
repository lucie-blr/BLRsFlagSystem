package net.blr.blrsflagsystem.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.minecraft.commands.Commands.literal;

public class FlagsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                literal("flags")
                        .then(Commands.argument("flag", StringArgumentType.string())
                        .executes(context -> {
                            final ServerPlayer player = context.getSource().getPlayerOrException();

                            String flag = context.getArgument("flag", String.class);

                            CommandSourceStack source = context.getSource();

                            //list of flags

                            final Map<String, String> flags = new HashMap<>();

                            flags.put("France", "ҁ");
                            flags.put("USA", ":usa:");
                            flags.put("UK", ":uk:");

                            if (flag == null) {
                                source.sendFailure(Component.literal("You must specify a flag to test."));
                                return 0;
                            }

                            else if (flags.containsKey(flag)) {
                                player.getPrefixes().add(Component.literal(flags.get(flag) + " "));

                                source.sendSuccess(Component.literal("set your flag to " + flags.get(flag)), true);
                                return 1;
                            }


                            source.sendSuccess(Component.literal("Flag " + flag + " does not exist."), true);

                            return 1;
                        }))
        );
    }
}
