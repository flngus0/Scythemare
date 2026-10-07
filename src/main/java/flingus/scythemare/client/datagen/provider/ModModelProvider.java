package flingus.scythemare.client.datagen.provider;

import flingus.scythemare.Scythemare;
import flingus.scythemare.index.ModItems;
import flingus.scythemare.index.ScytheSkins;
import flingus.scythemare.registry.ScytheSkin;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModModelProvider extends FabricModelProvider {
    public static final Model SCYTHE_IN_HAND = openBundle("template_scythe_in_hand", "scythe_in_hand", TextureKey.LAYER0);
    public static final Model SCYTHE = openBundle("template_scythe", "scythe", TextureKey.LAYER0);

    private static Model openBundle(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Scythemare.id("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private final CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture;

    public ModModelProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output);
        this.registriesFuture = registriesFuture;
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        RegistryWrapper.WrapperLookup registries = registriesFuture.join();
        RegistryWrapper<ScytheSkin> skins = registries.getOrThrow(ScytheSkins.REGISTRY);

        skins.streamEntries().forEach((RegistryEntry.Reference<ScytheSkin> entry) -> {
            registerScytheSkin(entry.value(), itemModelGenerator);
        });

        itemModelGenerator.register(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE, Models.GENERATED);
    }

    public final void registerScytheSkin(ScytheSkin skin, ItemModelGenerator generator) {
        Identifier id = skin.name();
        String name = id.getPath();

        if(name.equals("scythe") || name.equals("paddle")) return;

        SCYTHE.upload(
                Scythemare.id("item/scythe_skin/" + name + "/" + name),
                TextureMap.layer0(Scythemare.id("item/scythe_skin/" + name)),
                generator.modelCollector
        );

        SCYTHE_IN_HAND.upload(
                Scythemare.id("item/scythe_skin/" + name + "/" + name + "_in_hand"),
                TextureMap.layer0(Scythemare.id("item/scythe_skin/" + name + "_in_hand")),
                generator.modelCollector
        );
    }
}