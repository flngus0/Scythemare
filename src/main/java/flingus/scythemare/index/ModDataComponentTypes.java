package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.registry.ScytheSkin;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class ModDataComponentTypes {
    public static final ComponentType<Identifier> SKIN_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Scythemare.id("skin"),
            ComponentType.<Identifier>builder().codec(Identifier.CODEC).packetCodec(Identifier.PACKET_CODEC).build()
    );

    public static ScytheSkin skin(Identifier id, RegistryWrapper.WrapperLookup registries) {
        return registries.getOrThrow(ScytheSkins.REGISTRY).getOrThrow(RegistryKey.of(ScytheSkins.REGISTRY, id)).value();
    }

    public static String getTranslatedSkinName(Identifier id) {
        return "skin.scythemare." + id.getPath();
    }

    public static void init() {}
}