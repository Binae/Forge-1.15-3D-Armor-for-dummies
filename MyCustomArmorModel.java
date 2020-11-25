// Here we are extending ArmorBaseModel class that we just got from BlockBench

/* Basically this is the class you get when you export your BlockBench model as a .java
*  Change the class name to whatever you want
*  Note: constructors must have the same name as their class so we will have to change both names
*/

public class MyCustomArmorModel extends ArmorBaseModel {

// Keep in mind that this is just a model I made up off the top of my head, this won't work in game
// As long as you put your actual model exported from BlockBench in, it will be fine

/* What we are doing below is creating parts of our model, but right now we haven't defined them
*  In the setupArmorParts method we will define them
*  Now, we just have to change some stuff up
*/

/* This is how BlockBench gives it to us:
*   private final ModelRenderer myHatPart1;
*   private final ModelRenderer myHatPart2;
*/

/* Below is what we need to change it to
*  Why do we do this though?
*  This is because we aren't done toying with the model parts yet so we can't make them final
*  As you may have guessed, final means final in java (in other words not to be changed)
*  More importantly, we haven't even defined these parts, we shouldn't make them final right now
*/

/* One important thing to note is that you must delete all of the ModelRenderer variables that we already defined in our extended class
*  This is gonna be our armorHead, armorBody, armorRightArm, etc.
*  If you don't know which variable we need to delete, go back and reference our ArmorBaseModel class
*  Every variable defined there is what we need to delete, and we need to delete the entire line
*  We will fix all the errors your IDE is giving you in a second so just be patient
*/

    private ModelRenderer myHatPart1;
    private ModelRenderer myHatPart2;

/* Here we are simply telling the game where the texture for our custom armor model is
*  This is an example of defining a variable
*  We are saying the the variable ourTexture is of the type ResourceLocation
*  We then create a new instance of the ResourceLocation and inside defining our path, each slash means going into a folder
*  If you're clever you would have guessed that one of the ResourceLocation constructors take a string
*  You can look into this by opening documentation
*/
    ResourceLocation ourTexture = new ResourceLocation("modid:textures/yourpath/mycustomarmormodel.png");

/* Remember the constructor from our ArmorBaseModel class? Well this is where it comes into play
*  Notice that the super constructor below takes the same parameters as the constructor of the ArmorBaseModel class
*  This is generally what happens with the extension of class in java
*  We define our texture width, height, and texture as a resourcelocation below
*/

/* I know what you're thinking, your class probably doesn't look like this, but it will
*  First things first, we have to copy ALL the stuff from the constructor and paste it into the setupArmorParts method
*  Then we are saying that when our class (therefore constructor) is called, we want to setupArmorParts
*/

// Notice how small our constructor is here as opposed to the constructor BlockBench gave us
// This is because we will be doing most of the beefy stuff in the setupArmorParts method
// This is just the way I like to do it as opposed to using the getTexture method from ArmorBaseModel.java
    public MyCustomArmorModel() {
        super(64, 64, ourTexture);
        setupArmorParts();
    }

// This is where the actual model is created in code
/* IMPORTANT: Remember how we deleted all those variables we already had in the ArmorBaseModel class?
*  Now we are going to delete them as they appear here, this should be easy because all the stuff you need to delete should already be giving you errors
*  If you see something like this:
*       armorHead = new ModelRenderer();
*       armorHead.setTextureOffset(...);
*       armorBody.ANYTHING
*  Then just delete the whole line
*/

    public void setupArmorParts() {
        myHatPart1 = new ModelRenderer();
        // texture Offsets, rotation, etc...
        myHatPart2 = new ModelRenderer();
        // texture Offsets, rotation, etc...
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

// Here we are going to change the return type of this to that name of our class if it's not changed already

// How do we do that?
/* In java you can find a method return type by looking at whatever is before the method name. Here are some examples
*  public String getTexture() {} will return a String
*  public void doStuff() {} will return nothing, hence the name void
*  public boolean isFlying() {} will return either true or false
*  You get it, there are a ton of options for this
*  Below we are returning an instance of our class. Our class is what we are referring to when we say return 'this'
*/

    public final MyCustomArmorModel applyEntityStats(BipedModel defaultArmor){
        this.isChild = defaultArmor.isChild;
        this.isSneak = defaultArmor.isSneak;
        this.isSitting = defaultArmor.isSitting;
        this.rightArmPose = defaultArmor.rightArmPose;
        this.leftArmPose = defaultArmor.leftArmPose;

        return this;
    }


// We usually don't have to touch this method or any of the ones below
// Just use what BlockBench gives you for the render method
    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        myHatPart1.render(...);
        myHatPart2.render(...);
    }

// I'm not 100% sure because I'm doing this off the top of my head but you might get an error because your IDE wants to use a setRotationAngles method instead of setRotationAngle
// If you aren't having this problem just ignore this.

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