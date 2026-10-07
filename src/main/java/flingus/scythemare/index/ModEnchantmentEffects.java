package flingus.scythemare.index;

import com.mojang.serialization.MapCodec;
import flingus.scythemare.Scythemare;
import flingus.scythemare.enchantment.effect.ReinforceEffect;
import flingus.scythemare.enchantment.effect.SoulReapEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEnchantmentEffects {
    public static final MapCodec<SoulReapEffect> SOUL_REAP_EFFECT = Registry.register(
            Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            Scythemare.id("soul_reap"),
            SoulReapEffect.CODEC
    );

    public static final MapCodec<ReinforceEffect> REINFORCE_EFFECT = Registry.register(
            Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
            Scythemare.id("reinforce"),
            ReinforceEffect.CODEC
    );

    public static void init(){}
}
