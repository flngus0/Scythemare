
package flingus.scythemare.index;

import flingus.scythemare.entity.SoulSpiritEntity;
import moriyashiine.strawberrylib.api.module.SLibRegistries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class ModEntities {
    public static final EntityType<SoulSpiritEntity> SOUL_SPIRIT = SLibRegistries.registerEntityType("soul_spirit",
            EntityType.Builder.create(SoulSpiritEntity::new, SpawnGroup.MISC)
                    .dimensions(0.6F, 1.0F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(3)
    );

    public static void init() {
        FabricDefaultAttributeRegistry.register(SOUL_SPIRIT, SoulSpiritEntity.createAttributes());
    }
}