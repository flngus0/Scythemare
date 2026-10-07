package flingus.scythemare.client.model.entity;

import flingus.scythemare.client.render.entity.state.SoulSpiritEntityRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.animation.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import org.joml.Vector3fc;

public class SoulSpiritEntityModel extends EntityModel<SoulSpiritEntityRenderState> {
    private final ModelPart Torso;
    private final ModelPart Tail;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart Head;

    // nabbed from this random 1.16.5 mod lmao https://github.com/StandoByte/Ripples-of-the-Past/blob/1.16.5/src/main/java/com/github/standobyte/jojo/client/render/entity/animnew/Interpolations.java
    public static final Transformation.Interpolation CUBIC = (dest, delta, keyframes, start, end, scale) -> {
        Vector3fc p0Vec = keyframes[Math.max(0, start - 1)].postTarget();
        Vector3fc startVec = keyframes[start].postTarget();
        Vector3fc endVec = keyframes[end].postTarget();
        Vector3fc p3Vec = keyframes[Math.min(keyframes.length - 1, end + 1)].postTarget();
        dest.set(
                MathHelper.catmullRom(delta, p0Vec.x(), startVec.x(), endVec.x(), p3Vec.x()) * scale,
                MathHelper.catmullRom(delta, p0Vec.y(), startVec.y(), endVec.y(), p3Vec.y()) * scale,
                MathHelper.catmullRom(delta, p0Vec.z(), startVec.z(), endVec.z(), p3Vec.z()) * scale);
        return dest;
    };

    public static final AnimationDefinition IDLE = AnimationDefinition.Builder.create(2.0f).looping()
            .addBoneAnimation("Torso", new Transformation(
                    Transformation.Targets.ROTATE,
                        new Keyframe(0.00f, AnimationHelper.createRotationalVector(10.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, AnimationHelper.createRotationalVector(8.0f, 0.0f, 3.5f), CUBIC),
                    new Keyframe(0.50f, AnimationHelper.createRotationalVector(7.0f, 0.0f, 5.0f), CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(8.0f, 0.0f, 3.5f), CUBIC),
                    new Keyframe(1.00f, AnimationHelper.createRotationalVector(10.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(12.0f, 0.0f, -3.5f), CUBIC),
                    new Keyframe(1.50f, AnimationHelper.createRotationalVector(13.0f, 0.0f, -5.0f), CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(12.0f, 0.0f, -3.5f), CUBIC),
                    new Keyframe(2.00f, AnimationHelper.createRotationalVector(10.0f, 0.0f, 0.0f), CUBIC)
            ))
            .addBoneAnimation("Tail", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, AnimationHelper.createRotationalVector(-7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.5f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.75f, AnimationHelper.createRotationalVector(7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.25f, AnimationHelper.createRotationalVector(-7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.5f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(1.75f, AnimationHelper.createRotationalVector(7.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(2.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), CUBIC)
            ))
            .addBoneAnimation("RightArm", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, 5.73f), CUBIC),
                    new Keyframe(1.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(2.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, 5.73f), CUBIC)
            ))
            .addBoneAnimation("LeftArm", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, -5.73f), CUBIC),
                    new Keyframe(1.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(2.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, -5.73f), CUBIC)
            ))
            .build();
    
    public static final AnimationDefinition DISAPPEAR = AnimationDefinition.Builder.create(0.375f)
            .addBoneAnimation("RightArm", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, AnimationHelper.createRotationalVector(-48.658f, -13.3179f, -11.4569f), CUBIC),
                    new Keyframe(0.375f, AnimationHelper.createRotationalVector(-162.4726f, 40.7358f, -13.342f), CUBIC)
            ))
            .addBoneAnimation("LeftArm", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(-105.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, AnimationHelper.createRotationalVector(-48.2367f, 15.1889f, 13.1678f), CUBIC),
                    new Keyframe(0.375f, AnimationHelper.createRotationalVector(-162.4726f, -40.7358f, 13.342f), CUBIC)
            ))
            .addBoneAnimation("Torso", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(10.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, AnimationHelper.createRotationalVector(-5.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, AnimationHelper.createRotationalVector(-25.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.375f, AnimationHelper.createRotationalVector(-202.5f, 0.0f, 0.0f), CUBIC)
            ))
            .addBoneAnimation("Head", new Transformation(
                    Transformation.Targets.ROTATE,
                    new Keyframe(0.0f, AnimationHelper.createRotationalVector(0.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.125f, AnimationHelper.createRotationalVector(27.5f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.25f, AnimationHelper.createRotationalVector(25.0f, 0.0f, 0.0f), CUBIC),
                    new Keyframe(0.375f, AnimationHelper.createRotationalVector(-232.5115f, 2.4407f, -0.5414f), CUBIC)
            ))
            .build();

    private final Animation idle;
    private final Animation disappear;

    public SoulSpiritEntityModel(ModelPart root) {
        super(root, RenderLayers::entityTranslucentEmissive);
        this.Torso = root.getChild("Torso");
        this.Tail = this.Torso.getChild("Tail");
        this.RightArm = this.Torso.getChild("RightArm");
        this.LeftArm = this.Torso.getChild("LeftArm");
        this.Head = root.getChild("Head");

        this.idle = IDLE.createAnimation(root);
        this.disappear = DISAPPEAR.createAnimation(root);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData Torso = modelPartData.addChild("Torso", ModelPartBuilder.create().uv(0, 12).cuboid(-3.0F, 0.0F, -2.0F, 6.0F, 7.0F, 4.0F, new Dilation(0.0F)).uv(8, 0).cuboid(-3.0F, 5.0F, -2.0F, 6.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 17.0F, 0.0F));

        ModelPartData Tail = Torso.addChild("Tail", ModelPartBuilder.create().uv(20, 12).cuboid(0.0F, -2.0F, -2.0F, 0.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 4.0F, 3.0F));

        ModelPartData RightArm = Torso.addChild("RightArm", ModelPartBuilder.create().uv(0, 23).cuboid(-2.025F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).uv(14, 1).mirrored().cuboid(-2.025F, 5.0F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.origin(-3.0F, 0.0F, 0.0F));

        ModelPartData LeftArm = Torso.addChild("LeftArm", ModelPartBuilder.create().uv(0, 23).mirrored().cuboid(0.025F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)).mirrored(false).uv(14, 1).cuboid(0.025F, 5.0F, -1.0F, 2.0F, 0.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(3.0F, 0.0F, 0.0F));

        ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 17.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(SoulSpiritEntityRenderState state) {
        super.setAngles(state);

        this.Head.pitch = (float) Math.toRadians(state.pitch);
        this.Head.yaw = (float) Math.toRadians(state.relativeHeadYaw);
        
        idle.apply(state.idleState, state.age);
        disappear.apply(state.disappearState, state.age);
    }
}