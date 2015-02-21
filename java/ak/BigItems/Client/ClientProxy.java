package ak.BigItems.Client;

import ak.BigItems.BigItems;
import ak.BigItems.CommonProxy;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.IRegistry;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerClientInformation() {
        MinecraftForge.EVENT_BUS.register(this);
	}
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

    @SubscribeEvent
    public void bakedModelRegister(ModelBakeEvent event) {
        Item item;
        String[] split;
        for (String string : BigItems.ItemIDs) {
            split = string.split(":");
            if (split.length == 2) {
                if (split[0].equals("minecraft")) {
                    changeModel(event.modelRegistry, split[1], BigItems.Scale);
                } else {
                    changeModel(event.modelRegistry, string, BigItems.Scale);
                }
            } else {
                changeModel(event.modelRegistry, string, BigItems.Scale);
            }
        }
    }

    private void changeModel(IRegistry modelRegistry, String name, float scale) {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(name, "inventory");
        IBakedModel originalModel = (IBakedModel)modelRegistry.getObject(modelResourceLocation);
        modelRegistry.putObject(modelResourceLocation, new ScalableItemModel(originalModel, scale));
    }
}