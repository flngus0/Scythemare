package flingus.scythemare.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.registry.ScytheSkin;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ScytheSkins {
    public static final RegistryKey<Registry<ScytheSkin>> REGISTRY = RegistryKey.ofRegistry(Scythemare.id("scythe_skin"));

    public static final RegistryKey<ScytheSkin> OSCURA = of("oscura");
    public static final RegistryKey<ScytheSkin> ENBY = of("enby");
    public static final RegistryKey<ScytheSkin> DEVILSKNIFE = of("devilsknife");
    public static final RegistryKey<ScytheSkin> SCYTHEMARE = of("scythemare");
    public static final RegistryKey<ScytheSkin> BREAD = of("bread");
    public static final RegistryKey<ScytheSkin> PADDLE = of("paddle");
    public static final RegistryKey<ScytheSkin> GILDED = of("gilded");
    public static final RegistryKey<ScytheSkin> BANANA = of("banana");
    public static final RegistryKey<ScytheSkin> HOTDOG = of("hotdog");
    public static final RegistryKey<ScytheSkin> REDSTONE = of("redstone");
    public static final RegistryKey<ScytheSkin> AMETHYST = of("amethyst");
    public static final RegistryKey<ScytheSkin> RESIN = of("resin");
    public static final RegistryKey<ScytheSkin> CHORUS = of("chorus");
    public static final RegistryKey<ScytheSkin> ECHO = of("echo");
    public static final RegistryKey<ScytheSkin> ESOTERIC = of("esoteric");
    public static final RegistryKey<ScytheSkin> DRACONIC = of("draconic");
    public static final RegistryKey<ScytheSkin> CAPILLARY = of("capillary");
    public static final RegistryKey<ScytheSkin> CONTRACTUAL = of("contractual");
    public static final RegistryKey<ScytheSkin> VIRTUE = of("virtue");
    public static final RegistryKey<ScytheSkin> TECHNOPATH = of("technopath");
    public static final RegistryKey<ScytheSkin> CLEAVED = of("cleaved");
    public static final RegistryKey<ScytheSkin> CRYSTALLINE = of("crystalline");
    public static final RegistryKey<ScytheSkin> ONSLAUGHT = of("onslaught");
    public static final RegistryKey<ScytheSkin> SCYTHE = of("scythe");
    public static final RegistryKey<ScytheSkin> WAR = of("war");
    public static final RegistryKey<ScytheSkin> SOULSTAINED = of("soulstained");

    private static RegistryKey<ScytheSkin> of(String name) {
        return RegistryKey.of(REGISTRY, Scythemare.id(name));
    }

    public static void bootstrap(Registerable<ScytheSkin> registry) {
        register(registry, OSCURA, Scythemare.id("oscura"), Ingredient.ofItems(Items.ENDER_EYE));
        register(registry, ENBY, Scythemare.id("enby"), Ingredient.ofItems(Items.GOLD_INGOT));
        register(registry, DEVILSKNIFE, Scythemare.id("devilsknife"), Ingredient.ofItems(Items.ENDER_PEARL));
        register(registry, SCYTHEMARE, Scythemare.id("scythemare"), Ingredient.ofItems(Items.WARPED_NYLIUM));
        register(registry, BREAD, Scythemare.id("bread"), Ingredient.ofItems(Items.BREAD));
        register(registry, PADDLE, Scythemare.id("paddle"), Ingredient.ofItems(Items.WOODEN_SHOVEL));
        register(registry, GILDED, Scythemare.id("gilded"), Ingredient.ofItems(Items.GOLDEN_CARROT));
        register(registry, BANANA, Scythemare.id("banana"), Ingredient.ofItems(Items.YELLOW_DYE));
        register(registry, HOTDOG, Scythemare.id("hotdog"), Ingredient.ofItems(Items.PORKCHOP));
        register(registry, REDSTONE, Scythemare.id("redstone"), Ingredient.ofItems(Items.REDSTONE));
        register(registry, AMETHYST, Scythemare.id("amethyst"), Ingredient.ofItems(Items.AMETHYST_SHARD));
        register(registry, RESIN, Scythemare.id("resin"), Ingredient.ofItems(Items.RESIN_BRICK));
        register(registry, CHORUS, Scythemare.id("chorus"), Ingredient.ofItems(Items.CHORUS_FRUIT));
        register(registry, ECHO, Scythemare.id("echo"), Ingredient.ofItems(Items.ECHO_SHARD));
        register(registry, ESOTERIC, Scythemare.id("esoteric"), Ingredient.ofItems(Items.POPPED_CHORUS_FRUIT));
        register(registry, DRACONIC, Scythemare.id("draconic"), Ingredient.ofItems(Items.DRAGON_BREATH));
        register(registry, CAPILLARY, Scythemare.id("capillary"), Ingredient.ofItems(Items.PLAYER_HEAD, Items.ZOMBIE_HEAD));
        register(registry, CONTRACTUAL, Scythemare.id("contractual"), Ingredient.ofItems(Items.TOTEM_OF_UNDYING));
        register(registry, VIRTUE, Scythemare.id("virtue"), Ingredient.ofItems(Items.IRON_CHAIN));
        register(registry, TECHNOPATH, Scythemare.id("technopath"), Ingredient.ofItems(Items.COMPARATOR));
        register(registry, CLEAVED, Scythemare.id("cleaved"), Ingredient.ofItems(Items.CRACKED_STONE_BRICKS));
        register(registry, CRYSTALLINE, Scythemare.id("crystalline"), Ingredient.ofItems(Items.BASALT));
        register(registry, ONSLAUGHT, Scythemare.id("onslaught"), Ingredient.ofItems(Items.ENCHANTED_GOLDEN_APPLE));
        register(registry, SCYTHE, Scythemare.id("scythe"), Ingredient.ofItems(ModItems.IRON_SCYTHE));
        register(registry, WAR, Scythemare.id("war"), Ingredient.ofItems(Items.BREEZE_ROD));
        register(registry, SOULSTAINED, Scythemare.id("soulstained"), Ingredient.ofItems(Items.SOUL_SAND));
    }

    private static void register(Registerable<ScytheSkin> registry, RegistryKey<ScytheSkin> key, Identifier name, Ingredient material) {
        registry.register(key, new ScytheSkin(name, material));
    }

    public static void init() {
        DynamicRegistries.registerSynced(REGISTRY, ScytheSkin.CODEC);
    }
}