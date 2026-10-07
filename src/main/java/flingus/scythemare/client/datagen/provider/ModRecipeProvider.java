package flingus.scythemare.client.datagen.provider;

import flingus.scythemare.index.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                createScythe(this, exporter, ModItems.WOODEN_SCYTHE, ItemTags.PLANKS);
                createScythe(this, exporter, ModItems.STONE_SCYTHE, ItemTags.STONE_TOOL_MATERIALS);
                createScythe(this, exporter, ModItems.COPPER_SCYTHE, ItemTags.COPPER_TOOL_MATERIALS);
                createScythe(this, exporter, ModItems.IRON_SCYTHE, ItemTags.IRON_TOOL_MATERIALS);
                createScythe(this, exporter, ModItems.GOLDEN_SCYTHE, ItemTags.GOLD_TOOL_MATERIALS);
                createScythe(this, exporter, ModItems.DIAMOND_SCYTHE, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_SCYTHE, RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE);

                offerSmithingTemplateCopyingRecipe(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE, Items.TUFF);
            }
        };
    }

    public void createScythe(RecipeGenerator generator, RecipeExporter exporter, ItemConvertible output, TagKey<Item> material) {
        generator.createShaped(RecipeCategory.COMBAT, output)
                .input('#', material)
                .input('/', Items.STICK)
                .pattern("##/")
                .pattern("#/#")
                .pattern(" / ")
                .criterion("has_material", generator.conditionsFromTag(material))
                .criterion(RecipeGenerator.hasItem(Items.STICK), generator.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }

    @Override
    public String getName() {
        return "Scythemare Recipes";
    }
}
