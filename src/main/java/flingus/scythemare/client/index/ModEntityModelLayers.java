
package flingus.scythemare.client.index;

import flingus.scythemare.Scythemare;
import flingus.scythemare.client.model.entity.SoulSpiritEntityModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ModEntityModelLayers {
    public static final ModelLayerLocation SOUL_SPIRIT = new ModelLayerLocation(Scythemare.id("soul_spirit"), "main");

    public static void init() {
        EntityModelLayerRegistry.registerModelLayer(SOUL_SPIRIT, SoulSpiritEntityModel::getTexturedModelData);
    }
}