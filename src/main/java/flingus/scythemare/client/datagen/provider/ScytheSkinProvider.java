package flingus.scythemare.client.datagen.provider;

import flingus.scythemare.index.ScytheSkins;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ScytheSkinProvider extends FabricDynamicRegistryProvider {
    public ScytheSkinProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        if(registries.getOrThrow(ScytheSkins.REGISTRY).getKey().getValue().getPath().equals("scythe") || registries.getOrThrow(ScytheSkins.REGISTRY).getKey().getValue().getPath().equals("paddle")) return;

        entries.addAll(registries.getOrThrow(ScytheSkins.REGISTRY));
    }

    @Override
    public String getName() {
        return "Scythe Skins";
    }
}