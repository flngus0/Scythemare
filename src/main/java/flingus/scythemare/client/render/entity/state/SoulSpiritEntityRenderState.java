package flingus.scythemare.client.render.entity.state;

import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.entity.AnimationState;

public class SoulSpiritEntityRenderState extends ArmedEntityRenderState {
    public final AnimationState idleState = new AnimationState();
    public final AnimationState disappearState = new AnimationState();
}
