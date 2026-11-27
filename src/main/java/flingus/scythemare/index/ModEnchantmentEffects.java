package flingus.scythemare.index;

import com.mojang.serialization.MapCodec;
import flingus.scythemare.Scythemare;
import flingus.scythemare.enchantment.effect.ReinforceEffect;
import flingus.scythemare.enchantment.effect.SoulReapEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class ModEnchantmentEffects {
    public static final MapCodec<SoulReapEffect> SOUL_REAP_EFFECT = Registry.register(
            BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            Scythemare.id("soul_reap"),
            SoulReapEffect.CODEC
    );

    public static final MapCodec<ReinforceEffect> REINFORCE_EFFECT = Registry.register(
            BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            Scythemare.id("reinforce"),
            ReinforceEffect.CODEC
    );

    public static void init(){}
}
