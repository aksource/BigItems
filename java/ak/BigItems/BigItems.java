package ak.BigItems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid=BigItems.MOD_ID,
        name=BigItems.MOD_NAME,
        version=BigItems.MOD_MC_VERSION,
        dependencies=BigItems.MOD_DEPENDENCIES,
        useMetadata = true,
        acceptedMinecraftVersions = BigItems.MOD_MC_VERSION)
public class BigItems {

    public static final String MOD_ID = "BigItems";
    public static final String MOD_NAME = "BigItems";
    public static final String MOD_VERSION = "@VERSION@";
    public static final String MOD_DEPENDENCIES = "required-after:FML";
    public static final String MOD_MC_VERSION = "[1.8,1.8.9]";

	@SidedProxy(clientSide = "ak.BigItems.Client.ClientProxy", serverSide = "ak.BigItems.CommonProxy")
	public static CommonProxy proxy;


	public static String[] ItemIDs;
	public static float Scale;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ItemIDs = config.get(Configuration.CATEGORY_GENERAL, "Big Item Ids", new String[]{"minecraft:wooden_sword","minecraft:stone_sword","minecraft:iron_sword","minecraft:diamond_sword","minecraft:golden_sword"}, "Put Item Names which you want to make it big.").getStringList();
		Scale = (float)config.get(Configuration.CATEGORY_GENERAL, "Item Scale", 2.0d, "Item Scale").getDouble();
		config.save();
	}
	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerClientInformation();
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
//		for(int i = 0;i<ItemIDs.length;i++)
//			System.out.println(ItemIDs[i]);
	}
}