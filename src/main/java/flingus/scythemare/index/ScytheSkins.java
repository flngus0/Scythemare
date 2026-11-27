package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.registry.ScytheSkin;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public class ScytheSkins {
    public static final ResourceKey<Registry<ScytheSkin>> REGISTRY = ResourceKey.createRegistryKey(Scythemare.id("scythe_skin"));

    public static final ResourceKey<ScytheSkin> OSCURA = of("oscura");
    public static final ResourceKey<ScytheSkin> ENBY = of("enby");
    public static final ResourceKey<ScytheSkin> DEVILSKNIFE = of("devilsknife");
    public static final ResourceKey<ScytheSkin> SCYTHEMARE = of("scythemare");
    public static final ResourceKey<ScytheSkin> BREAD = of("bread");
    public static final ResourceKey<ScytheSkin> PADDLE = of("paddle");
    public static final ResourceKey<ScytheSkin> GILDED = of("gilded");
    public static final ResourceKey<ScytheSkin> BANANA = of("banana");
    public static final ResourceKey<ScytheSkin> HOTDOG = of("hotdog");
    public static final ResourceKey<ScytheSkin> REDSTONE = of("redstone");
    public static final ResourceKey<ScytheSkin> AMETHYST = of("amethyst");
    public static final ResourceKey<ScytheSkin> RESIN = of("resin");
    public static final ResourceKey<ScytheSkin> CHORUS = of("chorus");
    public static final ResourceKey<ScytheSkin> ECHO = of("echo");
    public static final ResourceKey<ScytheSkin> ESOTERIC = of("esoteric");
    public static final ResourceKey<ScytheSkin> DRACONIC = of("draconic");
    public static final ResourceKey<ScytheSkin> CAPILLARY = of("capillary");
    public static final ResourceKey<ScytheSkin> CONTRACTUAL = of("contractual");
    public static final ResourceKey<ScytheSkin> VIRTUE = of("virtue");
    public static final ResourceKey<ScytheSkin> TECHNOPATH = of("technopath");
    public static final ResourceKey<ScytheSkin> CLEAVED = of("cleaved");
    public static final ResourceKey<ScytheSkin> CRYSTALLINE = of("crystalline");
    public static final ResourceKey<ScytheSkin> ONSLAUGHT = of("onslaught");
    public static final ResourceKey<ScytheSkin> SCYTHE = of("scythe");
    public static final ResourceKey<ScytheSkin> WAR = of("war");
    public static final ResourceKey<ScytheSkin> SOULSTAINED = of("soulstained");
    public static final ResourceKey<ScytheSkin> PRECISION = of("precision");
    public static final ResourceKey<ScytheSkin> FRIGID = of("frigid");
    public static final ResourceKey<ScytheSkin> ROSETTE = of("rosette");
    public static final ResourceKey<ScytheSkin> AUREATE = of("aureate");
    public static final ResourceKey<ScytheSkin> LUCID = of("lucid");
    public static final ResourceKey<ScytheSkin> VOID = of("void");
    public static final ResourceKey<ScytheSkin> WONKY = of("wonky");

    private static ResourceKey<ScytheSkin> of(String name) {
        return ResourceKey.create(REGISTRY, Scythemare.id(name));
    }

    public static void bootstrap(BootstrapContext<ScytheSkin> registry) {
        register(registry, OSCURA, Scythemare.id("oscura"), Ingredient.of(Items.ENDER_EYE));
        register(registry, ENBY, Scythemare.id("enby"), Ingredient.of(Items.PURPLE_DYE));
        register(registry, DEVILSKNIFE, Scythemare.id("devilsknife"), Ingredient.of(Items.ENDER_PEARL));
        register(registry, SCYTHEMARE, Scythemare.id("scythemare"), Ingredient.of(Items.WARPED_NYLIUM));
        register(registry, BREAD, Scythemare.id("bread"), Ingredient.of(Items.BREAD));
        register(registry, PADDLE, Scythemare.id("paddle"), Ingredient.of(Items.WOODEN_SHOVEL));
        register(registry, GILDED, Scythemare.id("gilded"), Ingredient.of(Items.GOLDEN_CARROT));
        register(registry, BANANA, Scythemare.id("banana"), Ingredient.of(Items.YELLOW_DYE));
        register(registry, HOTDOG, Scythemare.id("hotdog"), Ingredient.of(Items.PORKCHOP));
        register(registry, REDSTONE, Scythemare.id("redstone"), Ingredient.of(Items.REDSTONE));
        register(registry, AMETHYST, Scythemare.id("amethyst"), Ingredient.of(Items.AMETHYST_SHARD));
        register(registry, RESIN, Scythemare.id("resin"), Ingredient.of(Items.RESIN_BRICK));
        register(registry, CHORUS, Scythemare.id("chorus"), Ingredient.of(Items.CHORUS_FRUIT));
        register(registry, ECHO, Scythemare.id("echo"), Ingredient.of(Items.ECHO_SHARD));
        register(registry, ESOTERIC, Scythemare.id("esoteric"), Ingredient.of(Items.POPPED_CHORUS_FRUIT));
        register(registry, DRACONIC, Scythemare.id("draconic"), Ingredient.of(Items.DRAGON_BREATH));
        register(registry, CAPILLARY, Scythemare.id("capillary"), Ingredient.of(Items.PLAYER_HEAD, Items.ZOMBIE_HEAD));
        register(registry, CONTRACTUAL, Scythemare.id("contractual"), Ingredient.of(Items.TOTEM_OF_UNDYING));
        register(registry, VIRTUE, Scythemare.id("virtue"), Ingredient.of(Items.IRON_CHAIN));
        register(registry, TECHNOPATH, Scythemare.id("technopath"), Ingredient.of(Items.COMPARATOR));
        register(registry, CLEAVED, Scythemare.id("cleaved"), Ingredient.of(Items.CRACKED_STONE_BRICKS));
        register(registry, CRYSTALLINE, Scythemare.id("crystalline"), Ingredient.of(Items.BASALT));
        register(registry, ONSLAUGHT, Scythemare.id("onslaught"), Ingredient.of(Items.ENCHANTED_GOLDEN_APPLE));
        register(registry, SCYTHE, Scythemare.id("scythe"), Ingredient.of(ModItems.IRON_SCYTHE));
        register(registry, WAR, Scythemare.id("war"), Ingredient.of(Items.BREEZE_ROD));
        register(registry, SOULSTAINED, Scythemare.id("soulstained"), Ingredient.of(Items.SOUL_SAND));
        register(registry, PRECISION, Scythemare.id("precision"), Ingredient.of(Items.SPYGLASS));
        register(registry, FRIGID, Scythemare.id("frigid"), Ingredient.of(Items.PACKED_ICE));
        register(registry, ROSETTE, Scythemare.id("rosette"), Ingredient.of(Items.ROSE_BUSH));
        register(registry, AUREATE, Scythemare.id("aureate"), Ingredient.of(Items.GOLD_INGOT));
        register(registry, LUCID, Scythemare.id("lucid"), Ingredient.of(Items.AMETHYST_CLUSTER));
        register(registry, VOID, Scythemare.id("void"), Ingredient.of(Items.OBSIDIAN));
        register(registry, WONKY, Scythemare.id("wonky"), Ingredient.of(Items.RED_DYE));
    }

    private static void register(BootstrapContext<ScytheSkin> registry, ResourceKey<ScytheSkin> key, Identifier name, Ingredient material) {
        registry.register(key, new ScytheSkin(name, material));
    }

    public static void init() {
        DynamicRegistries.registerSynced(REGISTRY, ScytheSkin.CODEC);
    }
}