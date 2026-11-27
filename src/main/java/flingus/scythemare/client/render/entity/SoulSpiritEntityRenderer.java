package flingus.scythemare.client.render.entity;

import flingus.scythemare.Scythemare;
import flingus.scythemare.client.index.ModEntityModelLayers;
import flingus.scythemare.client.model.entity.SoulSpiritEntityModel;
import flingus.scythemare.client.render.entity.state.SoulSpiritEntityRenderState;
import flingus.scythemare.entity.SoulSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public class SoulSpiritEntityRenderer extends MobRenderer<SoulSpiritEntity, SoulSpiritEntityRenderState, SoulSpiritEntityModel> {
    private static final Identifier TEXTURE = Scythemare.id("textures/entity/soul_spirit.png");

    public SoulSpiritEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SoulSpiritEntityModel(context.bakeLayer(ModEntityModelLayers.SOUL_SPIRIT)), 0.0F);
    }

    @Override
    public void extractRenderState(SoulSpiritEntity livingEntity, SoulSpiritEntityRenderState livingEntityRenderState, float f) {
        super.extractRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleState.copyFrom(livingEntity.idleState);
        livingEntityRenderState.disappearState.copyFrom(livingEntity.disappearState);
    }

    @Override
    protected int getBlockLightLevel(SoulSpiritEntity entity, BlockPos pos) {
        return 15;
    }

    @Override
    public SoulSpiritEntityRenderState createRenderState() {
        return new SoulSpiritEntityRenderState();
    }

    @Override
    public Identifier getTextureLocation(SoulSpiritEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    protected @Nullable RenderType getRenderType(SoulSpiritEntityRenderState state, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderTypes.entityTranslucent(getTextureLocation(state), true);
    }
}