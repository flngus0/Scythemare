package flingus.scythemare.client.datagen.provider;

import flingus.scythemare.Scythemare;
import flingus.scythemare.index.ModItems;
import flingus.scythemare.index.ScytheSkins;
import flingus.scythemare.registry.ScytheSkin;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ModModelProvider extends FabricModelProvider {
    public static final ModelTemplate SCYTHE_IN_HAND = openBundle("template_scythe_in_hand", "scythe_in_hand", TextureSlot.LAYER0);
    public static final ModelTemplate SCYTHE = openBundle("template_scythe", "scythe", TextureSlot.LAYER0);

    private static ModelTemplate openBundle(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(Scythemare.id("item/" + parent)), Optional.of(variant), requiredTextureKeys);
    }

    private final CompletableFuture<HolderLookup.Provider> registriesFuture;

    public ModModelProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output);
        this.registriesFuture = registriesFuture;
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {}

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        HolderLookup.Provider registries = registriesFuture.join();
        HolderLookup<ScytheSkin> skins = registries.lookupOrThrow(ScytheSkins.REGISTRY);

        skins.listElements().forEach((Holder.Reference<ScytheSkin> entry) -> {
            registerScytheSkin(entry.value(), itemModelGenerator);
        });

        itemModelGenerator.generateFlatItem(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
    }

    public final void registerScytheSkin(ScytheSkin skin, ItemModelGenerators generator) {
        Identifier id = skin.name();
        String name = id.getPath();

        if(name.equals("scythe") || name.equals("paddle")) return;

        SCYTHE.create(
                Scythemare.id("item/scythe_skin/" + name + "/" + name),
                TextureMapping.layer0(Scythemare.id("item/scythe_skin/" + name)),
                generator.modelOutput
        );

        SCYTHE_IN_HAND.create(
                Scythemare.id("item/scythe_skin/" + name + "/" + name + "_in_hand"),
                TextureMapping.layer0(Scythemare.id("item/scythe_skin/" + name + "_in_hand")),
                generator.modelOutput
        );
    }
}