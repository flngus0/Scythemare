package flingus.scythemare.client.model.entity;

import flingus.scythemare.client.render.entity.state.SoulSpiritEntityRenderState;
import net.minecraft.client.animation.*;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Mth;
import org.joml.Vector3fc;

public class SoulSpiritEntityModel extends EntityModel<SoulSpiritEntityRenderState> {
    private final ModelPart Torso;
    private final ModelPart Tail;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;

    // nabbed from this random 1.16.5 mod lmao https://github.com/StandoByte/Ripples-of-the-Past/blob/1.16.5/src/main/java/com/github/standobyte/jojo/client/render/entity/animnew/Interpolations.java
    public static final AnimationChannel.Interpolation CUBIC = (dest, delta, keyframes, start, end, scale) -> {
        Vector3fc p0Vec = keyframes[Math.max(0, start - 1)].postTarget();
        Vector3fc startVec = keyframes[start].postTarget();
        Vector3fc endVec = keyframes[end].postTarget();
        Vector3fc p3Vec = keyframes[Math.min(keyframes.length - 1, end + 1)].postTarget();
        dest.set(
                Mth.catmullrom(delta, p0Vec.x(), startVec.x(), endVec.x(), p3Vec.x()) * scale,
                Mth.catmullrom(delta, p0Vec.y(), startVec.y(), endVec.y(), p3Vec.y()) * scale,
                Mth.catmullrom(delta, p0Vec.z(), startVec.z(), endVec.z(), p3Vec.z()) * scale);
        return dest;
    };

    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(2.0f).looping()
            .addAnimation("Torso", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                        new Keyframe(0.00f, KeyframeAnimations.degreeVec(10.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(8.0f, 0.0f, 3.5f), CUBIC),
                    new Keyframe(0.50f, KeyframeAnimations.degreeVec(7.0f, 0.0f, 5.0f), CUBIC),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(8.0f, 0.0f, 3.5f), CUBIC),
                    new Keyframe(1.00f, KeyframeAnimations.degreeVec(10.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(12.0f, 0.0f, -3.5f), CUBIC),
                    new Keyframe(1.50f, KeyframeAnimations.degreeVec(13.0f, 0.0f, -5.0f), CUBIC),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(12.0f, 0.0f, -3.5f), CUBIC),
                    new Keyframe(2.00f, KeyframeAnimations.degreeVec(10.0f, 0.0f, 0.0f), CUBIC)
            ))
            .addAnimation("Tail", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(-7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.25f, KeyframeAnimations.degreeVec(-7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.75f, KeyframeAnimations.degreeVec(7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), CUBIC)
            ))
            .addAnimation("RightArm", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, 5.73f), CUBIC),
                    new Keyframe(1.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(2.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, 5.73f), CUBIC)
            ))
            .addAnimation("LeftArm", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, -5.73f), CUBIC),
                    new Keyframe(1.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(2.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, -5.73f), CUBIC)
            ))
            .build();
    
    public static final AnimationDefinition DISAPPEAR = AnimationDefinition.Builder.withLength(0.375f)
            .addAnimation("RightArm", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, KeyframeAnimations.degreeVec(-48.658f, -13.3179f, -11.4569f), CUBIC),
                    new Keyframe(0.375f, KeyframeAnimations.degreeVec(-162.4726f, 40.7358f, -13.342f), CUBIC)
            ))
            .addAnimation("LeftArm", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, KeyframeAnimations.degreeVec(-48.2367f, 15.1889f, 13.1678f), CUBIC),
                    new Keyframe(0.375f, KeyframeAnimations.degreeVec(-162.4726f, -40.7358f, 13.342f), CUBIC)
            ))
            .addAnimation("Torso", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(10.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, KeyframeAnimations.degreeVec(-5.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(-25.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.375f, KeyframeAnimations.degreeVec(-202.5f, 0.0f, 0.0f), CUBIC)
            ))
            .addAnimation("Head", new AnimationChannel(
                    AnimationChannel.Targets.ROTATION,
                    new Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, KeyframeAnimations.degreeVec(27.5f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(25.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.375f, KeyframeAnimations.degreeVec(-232.5115f, 2.4407f, -0.5414f), CUBIC)
            ))
            .build();

    private final KeyframeAnimation idle;
    private final KeyframeAnimation disappear;

    public SoulSpiritEntityModel(ModelPart root) {
        super(root, RenderTypes::entityTranslucentEmissive);
        this.Torso = root.getChild("Torso");
        this.Tail = this.Torso.getChild("Tail");
        this.RightArm = this.Torso.getChild("RightArm");
        this.LeftArm = this.Torso.getChild("LeftArm");
        this.Head = root.getChild("Head");

        this.idle = IDLE.bake(root);
        this.disappear = DISAPPEAR.bake(root);
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition Torso = modelPartData.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(8, 0).addBox(-3.0F, 5.0F, -2.0F, 6.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

        PartDefinition Tail = Torso.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(20, 12).addBox(0.0F, -2.0F, -2.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 3.0F));

        PartDefinition RightArm = Torso.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 23).addBox(-2.025F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(14, 1).mirror().addBox(-2.025F, 5.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 0.0F));

        PartDefinition LeftArm = Torso.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(0.025F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(14, 1).addBox(0.025F, 5.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

        PartDefinition Head = modelPartData.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(SoulSpiritEntityRenderState state) {
        super.setupAnim(state);

        this.Head.xRot = (float) Math.toRadians(state.xRot);
        this.Head.yRot = (float) Math.toRadians(state.yRot);

        idle.apply(state.idleState, state.ageInTicks);
        disappear.apply(state.disappearState, state.ageInTicks);
    }
}