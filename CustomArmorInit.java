/* This is where we will register our armor into the game
*  Inside our method we are using the RegistryEvent.Register<Item> event
*  This is basically just saying that we want to throw our armor items into the game when the game is registering all other items
*/

// I add the this line below just because of habit but if I remember correctly you don't have to use it but I'm not 100% sure
@Mod.EventBusSubscribe(modid = yourmodid, bus = Bus.MOD)
public class CustomArmorInit {
    
// In 1.15 we can use what is called an annotation (SubscribeEvent) to automatically register our armor into the game
// We only use this annotation to automatically watch/manipulate forge events

/* For this tutorial I'm not going to be using DeferredRegistries just because I prefer this way of registering armor
*  Both work completely fine though
*/


/* I'm not going to take the time to explain the armor material section of this because many people already have
*  I recommend this video by TurtyWurty: https://youtu.be/MGt11dr0f2o?t=216
*  I believe he also explains how to do item groups which are very simple
*/

    @SubscribeEvent
    public static void registerArmor(final RegistryEvent.Register<Item> event) {

        event.getRegistry().register(new ArmorRenderer(mycustomarmormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(mycustomgroup), new MyCustomArmorModel()).setRegistryName("example_hat"));

    }

// And just like that you have your custom armor model added into the game
// That concludes this custom armor models for dummies tutorial



}
