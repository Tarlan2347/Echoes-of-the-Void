//package net.tarlan.echoes.world.level.levelgen.feature.trunkplacers;
//
//import com.mojang.serialization.Codec;
//import net.minecraft.core.Registry;
//import net.minecraft.core.registries.BuiltInRegistries;
//import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
//
//public class TrunkPlacerType<P extends TrunkPlacer> {
//   public static final TrunkPlacerType<StraightTrunkPlacer> STRAIGHT_TRUNK_PLACER = register("straight_trunk_placer", StraightTrunkPlacer.CODEC);
//   private final Codec<P> codec;
//
//   private static <P extends TrunkPlacer> TrunkPlacerType<P> register(String pKey, Codec<P> pCodec) {
//      return Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, pKey, new TrunkPlacerType<>(pCodec));
//   }
//
//   public TrunkPlacerType(Codec<P> pCodec) {
//      this.codec = pCodec;
//   }
//
//   public Codec<P> codec() {
//      return this.codec;
//   }
//}