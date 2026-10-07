package flingus.scythemare.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

public record ScytheSkin(Identifier name, Ingredient material) {
    public static final Codec<ScytheSkin> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("name").forGetter(ScytheSkin::name),
            Ingredient.CODEC.fieldOf("material").forGetter(ScytheSkin::material)
    ).apply(instance, ScytheSkin::new));
}