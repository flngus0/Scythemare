
package flingus.scythemare.index;

import flingus.scythemare.entity.SoulSpiritEntity;
import moriyashiine.strawberrylib.api.module.SLibRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final EntityType<SoulSpiritEntity> SOUL_SPIRIT = SLibRegistries.registerEntityType("soul_spirit",
            EntityType.Builder.of(SoulSpiritEntity::new, MobCategory.MISC)
                    .sized(0.6F, 1.0F)
                    .clientTrackingRange(8)
                    .updateInterval(3)
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(SOUL_SPIRIT, SoulSpiritEntity.createAttributes());
    }
}