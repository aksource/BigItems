package ak.BigItems.Client;

import ak.BigItems.BigItems;
import ak.BigItems.CommonProxy;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerClientInformation()
	{
		Item item = null;
		for(int i= 0;i<BigItems.ItemIDs.length;i++){
			item = GameData.getItemRegistry().getObject(BigItems.ItemIDs[i]);
			if(item != null)
				MinecraftForgeClient.registerItemRenderer(item, new BigItemRenderer());
		}
	}
	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}
}