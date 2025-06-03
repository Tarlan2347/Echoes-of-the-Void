package net.tarlan.echoes.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tarlan.echoes.Echoes;
import net.tarlan.echoes.worldgen.feature.voidIslands.ChunkStreamingVoidIslandGen;
import net.tarlan.echoes.worldgen.feature.voidIslands.VoidIslandBiomes;

import java.util.Collection;

@Mod.EventBusSubscriber(modid = Echoes.MOD_ID)
public class EchoesCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("voidisland")
                .requires(source -> source.hasPermission(2))
                // Subcommand: listbiomes
                .then(Commands.literal("listbiomes")
                        .executes(ctx -> {
                            listBiomes(ctx.getSource());
                            return 1;
                        })
                )

                // Main island gen subcommand
                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                        .executes(ctx -> {
                            BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "pos");
                            return spawnIsland(ctx.getSource(), pos, null, null);
                        })
                        .then(Commands.argument("radius", IntegerArgumentType.integer(1, 1024))
                                .executes(ctx -> {
                                    BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "pos");
                                    int radius = IntegerArgumentType.getInteger(ctx, "radius");
                                    return spawnIsland(ctx.getSource(), pos, radius, null);
                                })
                                .then(Commands.argument("biome", StringArgumentType.string())
                                        .suggests((ctx, builder) -> {
                                            for (String biomeId : VoidIslandBiomes.getBiomeIds()) {
                                                builder.suggest(biomeId);
                                            }
                                            return builder.buildFuture();
                                        })
                                        .executes(ctx -> {
                                            BlockPos pos = BlockPosArgument.getLoadedBlockPos(ctx, "pos");
                                            int radius = IntegerArgumentType.getInteger(ctx, "radius");
                                            String biome = StringArgumentType.getString(ctx, "biome");
                                            return spawnIsland(ctx.getSource(), pos, radius, biome);
                                        })
                                )
                        )
                )
        );
    }
    private static void listBiomes(CommandSourceStack source) {
        Collection<String> biomeIds = VoidIslandBiomes.getBiomeIds();
        if (biomeIds.isEmpty()) {
            source.sendSuccess(() -> Component.literal("No void island biomes are currently registered."), false);
            return;
        }

        StringBuilder builder = new StringBuilder("Registered void island biomes:\n");
        for (String id : biomeIds) {
            builder.append(" - ").append(id).append("\n");
        }

        source.sendSuccess(() -> Component.literal(builder.toString().trim()), false);
    }
    private static int spawnIsland(CommandSourceStack source, BlockPos pos, Integer radius, String biomeIdInput) {
        ServerLevel level = source.getLevel();
        RandomSource random = level.getRandom();

        String radiusText = (radius != null) ? String.valueOf(radius) : "<random>";
        String biomeText = (biomeIdInput != null) ? biomeIdInput : "<none>";
        source.sendSuccess(() -> Component.literal("Generating void island at " + pos + " with radius " + radiusText + " and biome " + biomeText + "..."), true);

        boolean biomeWasNull = biomeIdInput == null || biomeIdInput.trim().isEmpty();
        VoidIslandBiomes.VoidBiome resolvedBiome = VoidIslandBiomes.resolve(biomeIdInput, random);

        if (biomeWasNull) {
            source.sendSuccess(() -> Component.literal("No biome specified, selected random biome: '" + resolvedBiome.id + "'"), true);
        } else if (!resolvedBiome.id.equals(biomeIdInput)) {
            source.sendSuccess(() -> Component.literal("Unknown biome ID '" + biomeIdInput + "', defaulted to '" + resolvedBiome.id + "'"), true);
        }

        //VoidIslandGenerator.generateVoidIsland(level, pos, random, resolvedBiome.id, radius);
        ChunkStreamingVoidIslandGen.generate(level, pos, radius, random);

        source.sendSuccess(() -> Component.literal("Void island generation started using biome '" + resolvedBiome.id + "'."), true);
        return 1;
    }
}
