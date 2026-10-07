package flingus.scythemare.client.datagen;

import flingus.scythemare.client.datagen.provider.ModModelProvider;
import flingus.scythemare.client.datagen.provider.ModRecipeProvider;
import flingus.scythemare.client.datagen.provider.ScytheSkinProvider;
import flingus.scythemare.index.ScytheSkins;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class ScythemareDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ScytheSkinProvider::new);
	}

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(ScytheSkins.REGISTRY, ScytheSkins::bootstrap);
    }
}
