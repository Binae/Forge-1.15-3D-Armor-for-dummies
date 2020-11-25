/* This is our ArmorRenderer class which is pretty self explanatory and simple if you know even a tiny bit of java
*  But I'll still explain stuff here and there
*  We need this class to reigster our custom armor in our CustomArmorInit class
*/

// We extend ArmorItem because we still want all the good parts of a normal armor item but we want to tweak some stuff
// More specifically we want to tell the game how we want to render our custom armor

public class ArmorRenderer extends ArmorItem {

// Here we create a variable for the ArmorBaseModel class

/* In java we can use abstract classes or really classes in general in a really helpful way
*  If we create a method that takes ArmorBaseModel in as a parameter, we can actually pass any class that implements or extends that class
*  If you don't understand this, just look up a java tutorial on hierarchies and stuff
*/

    private ArmorBaseModel armorModel;

    public ArmorRenderer(IArmorMaterial material, EquipmentSlotType equipmentSlot, Item.Properties properties, ArmorBaseModel armorModel) {

        super(material, equipmentSlot, properties);

        this.armorModel = armorModel;

    }

    @Override
    public final BipedModel getArmorModel(LivingEntity entity, ItemStack itemStack, EqipmentSlotType armorSlot, BipedModel defaultArmor) {
        return armorModel.applyEntityStats(defaultArmor).applySlot(armorSlot);
    }

    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return armorModel.getTexture();
    }

}
