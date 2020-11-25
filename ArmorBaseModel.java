package armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.ResourceLocation;

/*
* Hey so basically this is the simplest possible tutorial for Forge 1.15 custom armor models
* I will explain this in basic java terms and even english terms making this tutorial virtually stupid proof
*
* Okay so this code is just coming from the BlockBench tutorial on custom armor models
*
* Here we are literally just copying and pasting the class provided from the BlockBench discord, meaning none of this is original code
*/

// Just import everything and that should be alright

/* This is an abstract class which is usually just a 'shell'
* We typically ue this in java when we want to use similar methods for multiple classes but don't want to write it out a bunch of times
* Here's another way to think of this: an abstract class is a skeleton that we can use over and over again. Just because the skeleton is the same, doesn't mean the entire body is the same
* The body can still be skinny, fat, etc.
* Of course, there are more in depth reasons for using abstract classes, but for now that's what is important to know
*/

public abstract class ArmorBaseModel extends BipedModel {
    protected final ModelRenderer armorHead;
    protected final ModelRenderer armorBody;
    protected final ModelRenderer armorRightArm;
    protected final ModelRenderer armorLeftArm;
    protected final ModelRenderer armorRightLeg;
    protected final ModelRenderer armorLeftLeg;
    protected final ModelRenderer armorRightBoot;
    protected final ModelRenderer armorLeftBoot;

    private String texture;

    // below is what we call a constructor

    /* When we call this class we do all that junk inside the constructor (by call I mean creating a new instance of the class or extending it)
    *  The parameters inside the constructor (textureWidth, textureHeight, texture) are going to be important when we extend the class
    *  When we extend this class in our MyCustomArmorModel class, we will need to define what our texture width, height, and resourcelocation
    *  We do this with what is called a super constructor: you will see this in our custom model class
    */


    // What does this constructor do?

    /* This is going in-depth and if you read it you will be able to understand java as well as forge a little bit clearer
    * If you go into documentation within the BipedModel.class, you will see that we are basically just creating new instances of the stuff that is necessary
    * More specifically - armorHead, armorBody, armorLeftArm etc
    * Because this is our abstract class and we already defined them, we DO NOT need them in our custom armor model class
    *
    */

    public ArmorBaseModel(int textureWidth, int textureHeight, ResourceLocation texture){
        super(1F);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture.toString();

        armorHead = new ModelRenderer(this);
        armorHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedHead.addChild(armorHead);

        armorBody = new ModelRenderer(this);
        armorBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody.addChild(armorBody);
        armorRightArm = new ModelRenderer(this);
        armorRightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightArm.addChild(armorRightArm);

        armorLeftArm = new ModelRenderer(this);
        armorLeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftArm.addChild(armorLeftArm);

        armorRightLeg = new ModelRenderer(this);
        armorRightLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightLeg.addChild(armorRightLeg);

        armorLeftLeg = new ModelRenderer(this);
        armorLeftLeg.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftLeg.addChild(armorLeftLeg);


        armorRightBoot = new ModelRenderer(this);
        armorRightBoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightLeg.addChild(armorRightBoot);

        armorLeftBoot = new ModelRenderer(this);
        armorLeftBoot.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedLeftLeg.addChild(armorLeftBoot);

        setupArmorParts();
    }
    /* As we can see right above we call the setupArmorParts which is seen below
    * This is where we will be setting up OUR custom armor parts
    * I'll explain more in depth in our other class
    */

    public abstract void setupArmorParts();

    public final String getTexture(){
        return this.texture;
    }

    /* BlockBench says:
     * Feel free to override this method as needed.
     * It's just required to hide armor parts depending on the equipment slot
     */

    // we don't really need to touch this or really any of the methods in below

    public BipedModel applySlot(EquipmentSlotType slot){
        armorHead.showModel = false;
        armorBody.showModel = false;
        armorRightArm.showModel = false;
        armorLeftArm.showModel = false;
        armorRightLeg.showModel = false;
        armorLeftLeg.showModel = false;
        armorRightBoot.showModel = false;
        armorLeftBoot.showModel = false;

        switch(slot){
            case HEAD:
                armorHead.showModel = true;
                break;
            case CHEST:
                armorBody.showModel = true;
                armorRightArm.showModel = true;
                armorLeftArm.showModel = true;
                break;
            case LEGS:
                armorRightLeg.showModel = true;
                armorLeftLeg.showModel = true;
                break;
            case FEET:
                armorRightBoot.showModel = true;
                armorLeftBoot.showModel = true;
                break;
            default:
                break;
        }

        return this;
    }

    public final ArmorBaseModel applyEntityStats(BipedModel defaultArmor){
        this.isChild = defaultArmor.isChild;
        this.isSneak = defaultArmor.isSneak;
        this.isSitting = defaultArmor.isSitting;
        this.rightArmPose = defaultArmor.rightArmPose;
        this.leftArmPose = defaultArmor.leftArmPose;

        return this;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        copyModelAngles(this.bipedHead, this.armorHead);
        copyModelAngles(this.bipedBody, this.armorBody);
        copyModelAngles(this.bipedRightArm, this.armorRightArm);
        copyModelAngles(this.bipedLeftArm, this.armorLeftArm);
        copyModelAngles(this.bipedRightLeg, this.armorRightLeg);
        copyModelAngles(this.bipedLeftLeg, this.armorLeftLeg);
        copyModelAngles(this.bipedRightLeg, this.armorRightBoot);
        copyModelAngles(this.bipedLeftLeg, this.armorLeftBoot);

        matrixStack.push();
        if(isSneak) matrixStack.translate(0, 0.2, 0);
        this.armorHead.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorBody.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorRightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorLeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorRightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorRightBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.armorLeftBoot.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);

        matrixStack.pop();
    }

    public final void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

    private final void copyModelAngles(ModelRenderer in, ModelRenderer out){
        out.rotateAngleX = in.rotateAngleX;
        out.rotateAngleY = in.rotateAngleY;
        out.rotateAngleZ = in.rotateAngleZ;
    }
}