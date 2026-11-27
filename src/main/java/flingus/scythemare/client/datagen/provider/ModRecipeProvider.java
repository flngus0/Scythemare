package flingus.scythemare.client.datagen.provider;

import flingus.scythemare.index.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new RecipeProvider(registries, exporter) {
            @Override
            public void buildRecipes() {
                createScythe(this, output, ModItems.WOODEN_SCYTHE, ItemTags.PLANKS);
                createScythe(this, output, ModItems.STONE_SCYTHE, ItemTags.STONE_TOOL_MATERIALS);
                createScythe(this, output, ModItems.COPPER_SCYTHE, ItemTags.COPPER_TOOL_MATERIALS);
                createScythe(this, output, ModItems.IRON_SCYTHE, ItemTags.IRON_TOOL_MATERIALS);
                createScythe(this, output, ModItems.GOLDEN_SCYTHE, ItemTags.GOLD_TOOL_MATERIALS);
                createScythe(this, output, ModItems.DIAMOND_SCYTHE, ItemTags.DIAMOND_TOOL_MATERIALS);

                netheriteSmithing(ModItems.DIAMOND_SCYTHE, RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE);

                copySmithingTemplate(ModItems.SCYTHE_SKIN_SMITHING_TEMPLATE, Items.TUFF);
            }
        };
    }

    public void createScythe(RecipeProvider generator, RecipeOutput exporter, ItemLike output, TagKey<Item> material) {
        generator.shaped(RecipeCategory.COMBAT, output)
                .define('#', material)
                .define('/', Items.STICK)
                .pattern("##/")
                .pattern("#/#")
                .pattern(" / ")
                .unlockedBy("has_material", generator.has(material))
                .unlockedBy(RecipeProvider.getHasName(Items.STICK), generator.has(Items.STICK))
                .save(exporter);
    }

    @Override
    public String getName() {
        return "Scythemare Recipes";
    }
}
