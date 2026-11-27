package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.registry.ScytheSkin;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class ModDataComponentTypes {
    public static final DataComponentType<Identifier> SKIN_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Scythemare.id("skin"),
            DataComponentType.<Identifier>builder().persistent(Identifier.CODEC).networkSynchronized(Identifier.STREAM_CODEC).build()
    );

    public static ScytheSkin skin(Identifier id, HolderLookup.Provider registries) {
        return registries.lookupOrThrow(ScytheSkins.REGISTRY).getOrThrow(ResourceKey.create(ScytheSkins.REGISTRY, id)).value();
    }

    public static String getTranslatedSkinName(Identifier id) {
        return "skin.scythemare." + id.getPath();
    }

    public static void init() {}
}