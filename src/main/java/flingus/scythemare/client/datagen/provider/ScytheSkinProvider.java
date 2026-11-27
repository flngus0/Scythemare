package flingus.scythemare.client.datagen.provider;

import flingus.scythemare.index.ScytheSkins;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ScytheSkinProvider extends FabricDynamicRegistryProvider {
    public ScytheSkinProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        if(registries.lookupOrThrow(ScytheSkins.REGISTRY).key().identifier().getPath().equals("scythe") || registries.lookupOrThrow(ScytheSkins.REGISTRY).key().identifier().getPath().equals("paddle")) return;

        entries.addAll(registries.lookupOrThrow(ScytheSkins.REGISTRY));
    }

    @Override
    public String getName() {
        return "Scythe Skins";
    }
}