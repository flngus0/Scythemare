package flingus.scythemare.client.render.entity;

import flingus.scythemare.Scythemare;
import flingus.scythemare.client.index.ModEntityModelLayers;
import flingus.scythemare.client.model.entity.SoulSpiritEntityModel;
import flingus.scythemare.client.render.entity.state.SoulSpiritEntityRenderState;
import flingus.scythemare.entity.SoulSpiritEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jspecify.annotations.Nullable;

public class SoulSpiritEntityRenderer extends MobEntityRenderer<SoulSpiritEntity, SoulSpiritEntityRenderState, SoulSpiritEntityModel> {
    private static final Identifier TEXTURE = Scythemare.id("textures/entity/soul_spirit.png");

    public SoulSpiritEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new SoulSpiritEntityModel(context.getPart(ModEntityModelLayers.SOUL_SPIRIT)), 0.0F);
    }

    @Override
    public void updateRenderState(SoulSpiritEntity livingEntity, SoulSpiritEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleState.copyFrom(livingEntity.idleState);
        livingEntityRenderState.disappearState.copyFrom(livingEntity.disappearState);
    }

    @Override
    protected int getBlockLight(SoulSpiritEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public SoulSpiritEntityRenderState createRenderState() {
        return new SoulSpiritEntityRenderState();
    }

    @Override
    public Identifier getTexture(SoulSpiritEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    protected @Nullable RenderLayer getRenderLayer(SoulSpiritEntityRenderState state, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayers.entityTranslucent(getTexture(state), true);
    }
}