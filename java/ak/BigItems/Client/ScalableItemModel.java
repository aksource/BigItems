package ak.BigItems.Client;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.client.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.List;

/**
 * Created by A.K. on 15/02/21.
 */
public class ScalableItemModel implements IPerspectiveAwareModel {
    public static final Vector3f translate = new Vector3f(0.0F, 0.0F, 0.0F);
    public static final Quat4f rotateL = new Quat4f(0.0f, 0.0F, 0.0F, 0.0F);
    public static final Quat4f rotateR = new Quat4f(0.0f, 0.0F, 0.0F, 0.0F);
    public IBakedModel originalModel;
    public float scale;
    public Vector3f scaleVec;

    public ScalableItemModel(IBakedModel model, float f){
        this.originalModel = model;
        this.scale = f;
        scaleVec = new Vector3f(scale, scale, scale);
    }
    @Override
    public Pair<IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        switch (cameraTransformType) {
            case FIRST_PERSON:
                RenderItem.applyVanillaTransform(this.originalModel.getItemCameraTransforms().firstPerson);
                break;
            case GUI:
                RenderItem.applyVanillaTransform(this.originalModel.getItemCameraTransforms().gui);
                break;
            case HEAD:
                RenderItem.applyVanillaTransform(this.originalModel.getItemCameraTransforms().head);
                break;
            case THIRD_PERSON:
                RenderItem.applyVanillaTransform(this.originalModel.getItemCameraTransforms().thirdPerson);
                break;
        }

        Matrix4f matrix4f = TRSRTransformation.mul(null, null, scaleVec, null);
        return Pair.of(this.originalModel, matrix4f);
    }

    @Override
    public List getFaceQuads(EnumFacing facing) {
        return originalModel.getFaceQuads(facing);
    }

    @Override
    public List getGeneralQuads() {
        return originalModel.getGeneralQuads();
    }

    @Override
    public boolean isGui3d() {
        return originalModel.isGui3d();
    }

    @Override
    public boolean isAmbientOcclusion() {
        return originalModel.isAmbientOcclusion();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return originalModel.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getTexture() {
        return originalModel.getTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return originalModel.getItemCameraTransforms();
    }
}
