

package cok.PVP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.FireworkExplodeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class pvp18 extends JavaPlugin implements Listener{

	public String cmdgive;

	public ItemMeta meta;

	public HumanEntity Anviler;

	public ArrayList<String> credits;


	public ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	public boolean FirstTime = true;

	public double SWfix;

	public String link = "https://github.com"; 


	public String player ;
	public String item;	
	public String itemStack;

	// BukkitRunnable

	public int taskID0;
	public int taskID;
	public int taskID2;
	public int taskID3;
	public int taskID4;
	public int taskID5;
	public int taskID6;
	public int taskID7;

	public Entity damageSource;
	public Player Killer = null;

	java.util.Random random = new java.util.Random();

	public String un1[] = {ChatColor.RED + "Hunger", ChatColor.BLUE + "Mining Fatigue",ChatColor.YELLOW +"Glowing"};
	public String un2[] = {ChatColor.LIGHT_PURPLE+ "Weakness", ChatColor.DARK_AQUA + "Slowness",ChatColor.RED +"Hunger II"};
	public String un3[] = {ChatColor.DARK_RED + "Wither II", ChatColor.GREEN + "Poison II"};

	public String secretLore;


	// Panel
	public boolean Axe ;
	public boolean Shield;
	public boolean PVP18 ;
	public boolean regen;
	public boolean rod;
	public boolean PotionEnchant ;
	public boolean Kenobi ;
	public boolean KaBoom;
	public boolean Mouv;

	public boolean bug = false;

	public Inventory panel = Bukkit.createInventory(null,InventoryType.WORKBENCH, ChatColor.RED+"  RustedSword control panel");
	public Inventory panelinfo = Bukkit.createInventory(null,InventoryType.DISPENSER, ChatColor.RED+"  RustedSword control panel");


	public ItemStack ShieldPanel = new ItemStack(Material.SHIELD);
	public ItemStack AxePanel = new ItemStack(Material.IRON_AXE);
	public ItemStack RegenPanel = new ItemStack(Material.GOLDEN_CARROT);
	public ItemStack RodPanel = new ItemStack(Material.FISHING_ROD);
	public ItemStack ForcePanel = new ItemStack(Material.BREWING_STAND);
	public ItemStack PotEnchPanel = new ItemStack(Material.GLASS_BOTTLE);
	public ItemStack KenobiPanel = new ItemStack(Material.DIAMOND_SWORD);
	public ItemStack KaBoomPanel = new ItemStack(Material.FIREWORK_ROCKET);
	public ItemStack MouvPanel = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);

	public ItemStack CreditPanel = new ItemStack(Material.WRITTEN_BOOK);

	public ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);


	//banner EN/FR/ES
	public ItemStack ENbanner = new ItemStack(Material.WHITE_BANNER); 
	public ItemStack FRbanner = new ItemStack(Material.WHITE_BANNER);
	public ItemStack ESbanner = new ItemStack(Material.YELLOW_BANNER);

	public ItemStack Lbanner;
	public int IndexL;
	public String languageActive;

	public HashMap<String,ItemStack> Maplanguage = new HashMap<String,ItemStack>();
	public HashMap<String,String> switchL = new HashMap<String,String>();
	public List<String> ListLanguage = Arrays.asList("English","Français","Espanol");

	public String activated;
	public String disabled;
	public String Ad;
	public BannerMeta BanMeta;

	//dash & db jump

	public HashMap<UUID,Long> cooldowns = new HashMap<UUID,Long>();
	public int cooldowntime = 8;
	public List<Material> AntiDash = Arrays.asList(Material.BOW,Material.FISHING_ROD, Material.CROSSBOW,Material.TRIDENT);
	public HashMap<UUID,Boolean> dbJumper = new HashMap<UUID,Boolean>();

	@Override
	public void onEnable(){

		this.saveDefaultConfig();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this, this);

		for(Object ob : Bukkit.getOnlinePlayers().toArray()) {

			Player inter = (Player) ob;
			Player Player = Bukkit.getPlayer(inter.getName());

			for(AttributeModifier n : Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getModifiers()) {

				Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(n);

			}

		}

		//Banner for language

		//English

		Pattern english = new Pattern(DyeColor.RED, PatternType.STRAIGHT_CROSS);
		BanMeta = (BannerMeta) ENbanner.getItemMeta();
		BanMeta.addPattern(english);
		BanMeta.setDisplayName(ChatColor.BLUE +"English");
		BanMeta.setLore(Arrays.asList(ChatColor.GOLD + "Click to change language then reload"));

		ENbanner.setItemMeta(BanMeta);

		//French

		Pattern french1 = new Pattern(DyeColor.BLUE, PatternType.STRIPE_LEFT);
		Pattern french2 = new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT);
		BanMeta = (BannerMeta) FRbanner.getItemMeta();
		BanMeta.addPattern(french1);
		BanMeta.addPattern(french2);
		BanMeta.setDisplayName(ChatColor.BLUE +"Français");
		BanMeta.setLore(Arrays.asList(ChatColor.GOLD + "Click to change language then reload"));

		FRbanner.setItemMeta(BanMeta);

		//Spanish

		Pattern spain1 = new Pattern(DyeColor.RED, PatternType.STRIPE_LEFT);
		Pattern spain2 = new Pattern(DyeColor.RED, PatternType.STRIPE_RIGHT);
		Pattern spain3 = new Pattern(DyeColor.ORANGE, PatternType.CIRCLE_MIDDLE);

		BanMeta = (BannerMeta) ESbanner.getItemMeta();
		BanMeta.addPattern(spain3);
		BanMeta.addPattern(spain1);
		BanMeta.addPattern(spain2);
		BanMeta.setDisplayName(ChatColor.BLUE +"Espanol");
		BanMeta.setLore(Arrays.asList(ChatColor.GOLD + "Click to change language then reload"));

		ESbanner.setItemMeta(BanMeta);

		// creation of Hash Map

		Maplanguage.putIfAbsent("English", ENbanner);
		Maplanguage.putIfAbsent("Français", FRbanner);
		Maplanguage.putIfAbsent("Espanol", ESbanner);

		languageActive = this.getConfig().getString("Language");	
		Lbanner = Maplanguage.get(languageActive);
		IndexL = ListLanguage.indexOf(languageActive);

		// 1e use of switchL

		switchL.clear();
		switchL.put("English", "activated");
		switchL.put("Français", "activé");
		switchL.put("Espanol", "activado");

		activated = switchL.get(languageActive);

		switchL.clear();
		switchL.put("English", "disabled");
		switchL.put("Français", "désactivé");
		switchL.put("Espanol", "desactivado");

		disabled = switchL.get(languageActive);




		if(this.getConfig().getBoolean("PVP18")) {

			BukkitRunnable SweeP = new SweeP();
			SweeP.runTaskTimer(this, 20, 20);

			BukkitRunnable CheckWeakII = new CheckWeakII();
			CheckWeakII.runTaskTimer(this, 20, 20);

			for (Object ob : Bukkit.getOnlinePlayers().toArray()) {

				Player inter = (Player) ob;
				Player Player = Bukkit.getPlayer(inter.getName());
				AttributeInstance p = Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
				p.addModifier(new AttributeModifier("add", 100, AttributeModifier.Operation.ADD_SCALAR));
			}

			PVP18 = true;	
			FirstTime = false;

		}

		if(this.getConfig().getBoolean("Slower Regeneration")) {

			BukkitRunnable RegenFix = new RegenFix();
			RegenFix.runTaskTimer(this, 80, 80);

			taskID3 = RegenFix.getTaskId();

			for(World wrld : Bukkit.getWorlds()) {
				wrld.setGameRule(GameRule.NATURAL_REGENERATION, false);
			}

			regen = true;


			meta = RegenPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			RegenPanel.setItemMeta(meta);


		}else if (this.getConfig().getBoolean("Slower Regeneration") == false) {

			meta = RegenPanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED + disabled));
			RegenPanel.setItemMeta(meta);




		}
		if(this.getConfig().getBoolean("Knockback Fishing_rod")) {

			BukkitRunnable RodFix = new RodFIx();
			RodFix.runTaskTimer(this, 1, 1);

			taskID2 = RodFix.getTaskId();
			rod = true;

			meta = RodPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			RodPanel.setItemMeta(meta);


		}else if(this.getConfig().getBoolean("Knockback Fishing_rod") == false) {

			meta = RodPanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED + disabled));
			RodPanel.setItemMeta(meta);


		}

		if(this.getConfig().getBoolean("Forbiding Shield owning and crafting")) {

			meta = ShieldPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			ShieldPanel.setItemMeta(meta);

			BukkitRunnable ShieldFix = new ShieldFix();
			ShieldFix.runTaskTimer(this, 1, 1);

			taskID4 = ShieldFix.getTaskId();
			Shield = true;

		}else if(this.getConfig().getBoolean("Forbiding Shield owning and crafting")== false) {

			meta = ShieldPanel.getItemMeta();	
			meta.setLore(Arrays.asList(ChatColor.RED +disabled));
			ShieldPanel.setItemMeta(meta);
			Shield = false;
		}

		if(this.getConfig().getBoolean("Axe s Damage reduction")) {

			meta = AxePanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			AxePanel.setItemMeta(meta);

			Axe = true;

		}else if(this.getConfig().getBoolean("Axe s Damage reduction") == false) {

			meta = AxePanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED +disabled));

			AxePanel.setItemMeta(meta);
			Axe = false;


		}

		if(this.getConfig().getBoolean("Add Potion Effect to unbreaking")) {

			meta = PotEnchPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			PotEnchPanel.setItemMeta(meta);

			PotionEnchant = true;


		}else if(this.getConfig().getBoolean("Add Potion Effect to unbreaking") == false) {

			meta = PotEnchPanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED +disabled));

			PotEnchPanel.setItemMeta(meta);
			PotionEnchant = false;

		}


		if(this.getConfig().getBoolean("Kenobi s style")) {

			meta = KenobiPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			KenobiPanel.setItemMeta(meta);

			Kenobi = true;


		}else if(this.getConfig().getBoolean("Kenobi s style") == false) {

			meta = KenobiPanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED +disabled));

			KenobiPanel.setItemMeta(meta);
			Kenobi = false;

		}

		if(this.getConfig().getBoolean("Make fireworks explose when shoted by a crossbow")) {

			meta = KaBoomPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			KaBoomPanel.setItemMeta(meta);

			KaBoom = true;


		}else if(this.getConfig().getBoolean("Make fireworks explose when shoted by a crossbow") == false) {

			meta = KaBoomPanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED +disabled));

			KaBoomPanel.setItemMeta(meta);
			KaBoom = false;

		}


		if(this.getConfig().getBoolean("Allow double jump and dash")) {

			meta = MouvPanel.getItemMeta();
			meta.addEnchant(Enchantment.MENDING, 1, true);
			meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

			MouvPanel.setItemMeta(meta);

			Mouv = true;

			BukkitRunnable isOnGround = new isOnGround();
			isOnGround.runTaskTimer(this, 1, 1);


		}else if(this.getConfig().getBoolean("Allow double jump and dash") == false) {

			meta = MouvPanel.getItemMeta();
			meta.setLore(Arrays.asList(ChatColor.RED +disabled));

			MouvPanel.setItemMeta(meta);
			Mouv = false;

		}



		SWfix = this.getConfig().getDouble("Strenght/Weakness effect reduction factor");

		// Language being applied

		switchL.clear();
		switchL.put("English", "Good old days regeneration");
		switchL.put("Français", "La régénération à l'ancienne");
		switchL.put("Espanol", "Buenos viejos tiempos regeneracion");

		String regen1234 = switchL.get(languageActive);

		meta = RegenPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+regen1234);
		RegenPanel.setItemMeta(meta);


		switchL.clear();
		switchL.put("English", "Good old days fishing_rod");
		switchL.put("Français", "La canne à pêche à l'ancienne");
		switchL.put("Espanol", "Buenos viejos tiempos cana de pescar");

		String fish_rod1234 = switchL.get(languageActive);

		meta = RodPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+fish_rod1234);
		RodPanel.setItemMeta(meta);

		switchL.clear();
		switchL.put("English", "Shield 're for cowards");
		switchL.put("Français", "Vraiment un truc de lâche le bouclier...");
		switchL.put("Espanol", "Solo los cobardes usan un escudo");

		String shield1234 = switchL.get(languageActive);



		meta = ShieldPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+shield1234);
		ShieldPanel.setItemMeta(meta);

		switchL.clear();
		switchL.put("English", "Not Viking-Friendly");
		switchL.put("Français", "Laissons les haches aux bucherons");
		switchL.put("Espanol", "Hachas, un arma tan barbara");

		String axe1234 = switchL.get(languageActive);

		meta = AxePanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+axe1234);
		AxePanel.setItemMeta(meta);


		switchL.clear();
		switchL.put("English", "Dark Magic");
		switchL.put("Français", "Magie noire");
		switchL.put("Espanol", "Magia negra");

		String pot1234 = switchL.get(languageActive);

		meta = PotEnchPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+pot1234);
		PotEnchPanel.setItemMeta(meta);


		switchL.clear();
		switchL.put("English", "Master Kenobi's style");
		switchL.put("Français", "Obiwan serait fier");
		switchL.put("Espanol", "estilo de Obi Wan");

		String kenobi1234 = switchL.get(languageActive);

		meta = KenobiPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+kenobi1234);
		KenobiPanel.setItemMeta(meta);

		switchL.clear();
		switchL.put("English", "PiouPiou BoumBoum");
		switchL.put("Français", "Ka...Boom");
		switchL.put("Espanol", "Pssshit Boom");

		String boom1234 = switchL.get(languageActive);


		meta = KaBoomPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+boom1234);
		KaBoomPanel.setItemMeta(meta);

		switchL.clear();
		switchL.put("English", "Why walk when you can dance");
		switchL.put("Français", "Tortues ninja > tortues");
		switchL.put("Espanol", "Tango > ajedrez");

		String move = switchL.get(languageActive);

		meta = MouvPanel.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+move);
		MouvPanel.setItemMeta(meta);

		ArrayList<String> lore = new ArrayList<String>();
		BookMeta bm = (BookMeta)book.getItemMeta();
		switch(languageActive) {		

		case "English":

			meta = CreditPanel.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE+"Credits");

			lore.add(ChatColor.GOLD +"------Cokktail's RustedSword------");
			lore.add(ChatColor.LIGHT_PURPLE +"If you found this plugin useful");
			lore.add(ChatColor.LIGHT_PURPLE + "or if you have any issues");
			lore.add(ChatColor.LIGHT_PURPLE +"please leave a review on ...");
			lore.add(ChatColor.RED+ "Thanks to"+ ChatColor.BOLD+ " Eskys" + ChatColor.RED +" for");
			lore.add(ChatColor.RED + "the presentation video");
			lore.add(ChatColor.RED+"Check out his ytb channel ...");
			lore.add(ChatColor.GOLD +"-------------------------------");
			lore.add(ChatColor.BLUE +""+ ChatColor.ITALIC + "click to get the links");

			meta.setLore(lore);
			CreditPanel.setItemMeta(meta);

			bm.setAuthor("Cokktail");
			bm.setTitle("Info");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Not Viking-Friendly \n\n" + ChatColor.BLUE + "Divide by 2 the damages made by axes.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Good old days regenration \n\n" + ChatColor.BLUE + "Slow down regeneration, equals 1 half-heart/4 seconds.\n\n"+ ChatColor.RED +"Disclaimer \n" +ChatColor.BLUE +"Uses the /gamerule naturalRegeneration");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Good old days fishing_rod \n\n" + ChatColor.BLUE + "Adds damages and knockback to fishing_rods (like in pre-1.9).");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Shield 're for cowards \n\n" + ChatColor.BLUE + "Forbids shield crafting and transforms the shield into iron_ingots if obtained another way.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Dark Magic \n\n" + ChatColor.BLUE + "Adds random potion effects with unbreaking to melee weapons, depending on the level of unbreaking. Effects detailled next page. Can't be combined with Fire_aspect");

			bm.addPage(ChatColor.GOLD + "------ I (12s) ------\n\n "+ChatColor.RED +"Hunger "+ChatColor.BLUE +"Mining Fatigue\n "+ChatColor.GRAY +"Glowing \n\n"
					+ChatColor.GOLD +"------ II (6s) -----\n\n" + ChatColor.LIGHT_PURPLE +" Weakness  "+ChatColor.DARK_AQUA +"Slowness\n "+ChatColor.RED +"Hunger II\n\n"
					+ChatColor.GOLD +"------ III (3s) -----\n\n" +ChatColor.DARK_RED +" Wither II  "+ChatColor.GREEN +"Poison II ");

			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Master Kenobi's style \n\n" + ChatColor.BLUE + "Allow to send back arrows with diamond_sword. Arrows sent back deal half the damage of the one initialy shot.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"PiouPiou BoumBoum \n\n" + ChatColor.BLUE + "Make fireworks create an explosion when they hit an Entity. Explosion size depends on the flight_duration of the firework. Flight duration 3 fireworks spread fire around the explosion (same power as flight duration 2).");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Why walk when you can dance \n\n" + ChatColor.BLUE + "Allow (when not in creative) to double jump. Also allows to dash by performing a right clik with a sword, 8 cooldown between 2 dashes (if you dash on someone, deals damages). Fall damages are disabled.");
			bm.addPage(ChatColor.GOLD +"\n\n\n\n\n    For more infos \n\n" + link);

			book.setItemMeta(bm);
			break;

		case "Français":
			meta = CreditPanel.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE+"Credits");

			lore.add(ChatColor.GOLD +"------Cokktail's RustedSword------");
			lore.add(ChatColor.LIGHT_PURPLE +"Si vous avez trouvé ce plugin utile");
			lore.add(ChatColor.LIGHT_PURPLE + "Ou si vous faites face à des erreurs");
			lore.add(ChatColor.LIGHT_PURPLE +"merci de laisser un petit mot à ...");
			lore.add(ChatColor.RED+ "Merci à"+ ChatColor.BOLD+ " Eskys" + ChatColor.RED +" pour");
			lore.add(ChatColor.RED + "la vidéo de présentation");
			lore.add(ChatColor.RED+"Allez checker sa chaîne ytb ...");
			lore.add(ChatColor.GOLD +"-------------------------------");
			lore.add(ChatColor.BLUE +""+ ChatColor.ITALIC + "cliquer pour les liens");

			meta.setLore(lore);
			CreditPanel.setItemMeta(meta);

			bm.setAuthor("Cokktail");
			bm.setTitle("Info");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Laissons les haches aux bucherons \n\n" + ChatColor.BLUE + "Divise par 2 les dégats des haches.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"La régénération à l'ancienne \n\n" + ChatColor.BLUE + "Réduit la régération, 1 demi-coeur / 4 secondes.\n\n"+ ChatColor.RED +"Disclaimer \n" +ChatColor.BLUE +"Utilise /gamerule naturalRegeneration");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"La canne à pêche à l'ancienne \n\n" + ChatColor.BLUE + "Rajoute des dégats et du recul à la canne à pêche  (équivalent à la canne à pêche pre-1.9).");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Vraiment un truc de lâche le bouclier... \n\n" + ChatColor.BLUE + "Empêche le craft du bouclier et le transforme en lingot de fer si obtenu autrement .");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Magie noire \n\n" + ChatColor.BLUE + "Ajoute un effet de potion aléatoire aux armes de mêlée avec unbreaking. Les différents effets en fonction du niveau de l'enchantement et leurs durées sont détaillés sur la page suivante. S'annule si l'arme posséde Aura_de_feu.");

			bm.addPage(ChatColor.GOLD + "------ I (12s) ------\n\n "+ChatColor.RED +"Hunger "+ChatColor.BLUE +"Mining Fatigue\n "+ChatColor.GRAY +"Glowing \n\n"
					+ChatColor.GOLD +"------ II (6s) -----\n\n" + ChatColor.LIGHT_PURPLE +" Weakness  "+ChatColor.DARK_AQUA +"Slowness\n "+ChatColor.RED +"Hunger II\n\n"
					+ChatColor.GOLD +"------ III (3s) -----\n\n" +ChatColor.DARK_RED +" Wither II  "+ChatColor.GREEN +"Poison II ");

			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Obiwan serait fier \n\n" + ChatColor.BLUE + "Permet de renvoyer les fléches avec une épée en diamant. Les fléches renvoyées infligent la moitié de leurs dégats initiaux.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Ka...Boom \n\n" + ChatColor.BLUE + "Fait exploser les feux d'artifices lorsqu'ils touchent une entité. L'explosion depend de la durée de vol du feu d'artifices. Si la durée de vol est de 3, du feux est ajouté à l'explosion (même explosion que 2).");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Tortues ninja > tortues \n\n" + ChatColor.BLUE + "Permet (lorsque l'on est pas en créatif) de faire un double saut. Permet aussi de dash avec un clic droit sur une épée (8s de cooldown), Inflige des dégâts par pércussion. Les dégâts de chute sont désactivés.");
			bm.addPage(ChatColor.GOLD +"\n\n\n\n\n   Pour plus d'infos \n\n" + link);
			book.setItemMeta(bm);
			break;

		case "Espanol":

			meta = CreditPanel.getItemMeta();
			meta.setDisplayName(ChatColor.BLUE+"Credits");

			lore.add(ChatColor.GOLD +"------Cokktail's RustedSword------");
			lore.add(ChatColor.LIGHT_PURPLE +"Si encuentra útil este plugin");
			lore.add(ChatColor.LIGHT_PURPLE + "o si tienes algún problema");
			lore.add(ChatColor.LIGHT_PURPLE +"por favor deja un comentario en ...");
			lore.add(ChatColor.RED+ "Gracias a "+ ChatColor.BOLD+ " Eskys" + ChatColor.RED +" para");
			lore.add(ChatColor.RED + "el video de presentación");
			lore.add(ChatColor.RED+"Mira su canal de youtube ...");
			lore.add(ChatColor.GOLD + "------------------------------");
			lore.add(ChatColor.BLUE +""+ ChatColor.ITALIC + "haga clic para enlaces");

			meta.setLore(lore);
			CreditPanel.setItemMeta(meta);

			bm.setAuthor("Cokktail");
			bm.setTitle("Info");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Hachas, un arma tan bárbara \n\n" + ChatColor.BLUE + "Divide el daño causado por los ejes por 2.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Buenos viejos tiempos regeneración \n\n" + ChatColor.BLUE + "Retrasa la regeneración, es igual a 1 medio corazón / 4 segundos.\n\n"+ ChatColor.RED +"Disclaimer \n" +ChatColor.BLUE +"Usa /gamerule naturalRegeneration");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Buenos viejos tiempos caña de pescar \n\n" + ChatColor.BLUE + "Agregue daño y retroceso a las cañas de pescar (como en pre-1.9).");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Sólo los cobardes usan un escudo \n\n" + ChatColor.BLUE + "Prohíbe la fabricación de escudos y transforma el escudo en lingotes de hierro si se obtiene lo contrario.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Magia negra \n\n" + ChatColor.BLUE + "Tuvo efectos de poción aleatorios con Unbreaking de armas cuerpo a cuerpo, dependiendo del nivel de Unbreaking. Efectos detallados en la página siguiente. No se puede combinar con aspecto de fuego.");

			bm.addPage(ChatColor.GOLD + "------ I (12s) ------\n\n "+ChatColor.RED +"Hunger "+ChatColor.BLUE +"Mining Fatigue\n "+ChatColor.GRAY +"Glowing \n\n"
					+ChatColor.GOLD +"------ II (6s) -----\n\n" + ChatColor.LIGHT_PURPLE +" Weakness  "+ChatColor.DARK_AQUA +"Slowness\n "+ChatColor.RED +"Hunger II\n\n"
					+ChatColor.GOLD +"------ III (3s) -----\n\n" +ChatColor.DARK_RED +" Wither II  "+ChatColor.GREEN +"Poison II ");

			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Obi Wan estaría orgulloso \n\n" + ChatColor.BLUE + "Permitir el envío de flechas con diamond_sword. Las flechas enviadas devuelven la mitad del daño del lanzamiento inicial.");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Pssshit Boom \n\n" + ChatColor.BLUE + "Hacer que los fuegos artificiales creen una explosión cuando golpean una entidad. El tamaño de la explosión depende de la duración del vuelo. La duración del vuelo 3 agrega fuego a la explosión (el mismo poder que 2).");
			bm.addPage(ChatColor.GOLD +""+ChatColor.BOLD +"Tango > ajedrez \n" + ChatColor.BLUE + "\n" +
					"Permite (no en creativo) hacer doble salto. También permite dash con un clic derecho con una espada (8s cooldown), Si golpeas a alguien con el dash, recibe daño . Los daños por caída están desactivados.");
			bm.addPage(ChatColor.GOLD +"\n\n\n\n\n    Para más infos \n\n" +link);

			book.setItemMeta(bm);
			break;
		}




	}






	//-----------------------------------------------------------------------------------------------------------------------------




	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		if(PVP18) {

			for(AttributeModifier n : e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).getModifiers()) {

				e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(n);
			}

			AttributeInstance p = e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED);
			p.addModifier(new AttributeModifier("add", 100, AttributeModifier.Operation.ADD_SCALAR));

		}


	}


	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args) {

		boolean SenderIsPlayer;

		try {
			Bukkit.getPlayer(sender.getName()).isEmpty();
			SenderIsPlayer = true;
		}catch(Exception ex) {
			SenderIsPlayer = false;
		}


		if(cmd.getName().equalsIgnoreCase("rustedsword") | cmd.getName().equalsIgnoreCase("rs")){
			bug = false;

			try {
				if(args[0].contains("on")==true & PVP18 == false) {

					if(FirstTime == true) { 

						for (Object ob : Bukkit.getOnlinePlayers().toArray()) {

							Player inter = (Player) ob;
							Player Player = Bukkit.getPlayer(inter.getName());

							for(AttributeModifier n : Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getModifiers()) {

								Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(n);
							}
							FirstTime = false;

						}

					}
					for (Object ob : Bukkit.getOnlinePlayers().toArray()) {

						Player inter = (Player) ob;
						Player Player = Bukkit.getPlayer(inter.getName());

						AttributeInstance p = Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
						p.addModifier(new AttributeModifier("add", 100, AttributeModifier.Operation.ADD_SCALAR));

					}


					PVP18 = true;	

					BukkitRunnable SweeP = new SweeP();
					SweeP.runTaskTimer(this, 20, 20);

					BukkitRunnable CheckWeakII = new CheckWeakII();
					CheckWeakII.runTaskTimer(this, 20, 20);

					switchL.clear();
					switchL.put("English", "is activated");
					switchL.put("Français", "est activé");
					switchL.put("Espanol", "es activado");

					Ad = switchL.get(languageActive);

					Bukkit.broadcastMessage(ChatColor.GREEN +"[RustedSword] " + Ad);

				}else if(args[0].contains("on")==true & PVP18 == true) {

					switchL.clear();
					switchL.put("English", "is already activated");
					switchL.put("Français", "est déjà activé");
					switchL.put("Espanol", "ya está activado");

					Ad = switchL.get(languageActive);
					sender.sendMessage(ChatColor.BLUE +"[RustedSword] " + Ad);
				}	

				if(args[0].contains("off")==true & PVP18 == true) {

					PVP18 = false;

					for (Object ob : Bukkit.getOnlinePlayers().toArray()) {

						Player inter = (Player) ob;
						Player Player = Bukkit.getPlayer(inter.getName());

						AttributeInstance pit = Player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
						pit.setBaseValue(pit.getDefaultValue() -1);

						for(AttributeModifier att : Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getModifiers()) {
							Player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(att);
						}

						if(Player.getGameMode().equals(GameMode.CREATIVE)==false) {

							Player.setAllowFlight(false);
						}
					}

					Bukkit.getScheduler().cancelTasks(this);

					for(World wrld : Bukkit.getWorlds()) {
						wrld.setGameRule(GameRule.NATURAL_REGENERATION, true);
					}

					switchL.put("English", "is now disabled");
					switchL.put("Français", "est maintenant désactivé");
					switchL.put("Espanol", "ahora está deshabilitado");
					Ad = switchL.get(languageActive);

					Bukkit.broadcastMessage(ChatColor.RED +"[RustedSword] "+ Ad);


				}else if(args[0].contains("off")==true & PVP18 == false) {

					switchL.put("English", "is already disabled");
					switchL.put("Français", "est déjà desactivé");
					switchL.put("Espanol", "ya está deshabilitado");
					Ad = switchL.get(languageActive);

					sender.sendMessage(ChatColor.BLUE+"[RustedSword] " + Ad);

				}

				if(args[0].contains("on") ==false & args[0].contains("off") ==false & PVP18 == false) {

					switchL.put("English", "You should activate RustedSword first");
					switchL.put("Français", "Vous devriez activer RustedSword d'abord");
					switchL.put("Espanol", "Debes habilitar RustedSword primero");

					Ad = switchL.get(languageActive);

					sender.sendMessage(ChatColor.BLUE+"[RustedSword] " + Ad);
				}

			}catch(ArrayIndexOutOfBoundsException exception) {

				switchL.put("English", "You must add 'on', 'off' or 'panel' ");
				switchL.put("Français", "Vous devez  ajouter 'on', 'off' ou 'panel'");
				switchL.put("Espanol", "Debe agregar 'on', 'off' o 'panel'");

				Ad = switchL.get(languageActive);

				sender.sendMessage(ChatColor.GOLD + "[RustedSword] " + Ad);
				bug = true;
			}
		}

		if(bug == false & cmd.getName().equalsIgnoreCase("rustedsword") & PVP18 == true){

			if(args[0].contains("panel") & SenderIsPlayer) {


				panel.setItem(0, Lbanner);
				panel.setItem(1, AxePanel);
				panel.setItem(2, RegenPanel);
				panel.setItem(3, RodPanel);
				panel.setItem(4, ShieldPanel);
				panel.setItem(5, CreditPanel);
				panel.setItem(6, PotEnchPanel);
				panel.setItem(7, KenobiPanel);
				panel.setItem(8, KaBoomPanel);
				panel.setItem(9, MouvPanel);

				Bukkit.getPlayer(sender.getName()).openInventory(panel);
			}
			if(args[0].contains("panelinfo") & SenderIsPlayer) {

				panelinfo.setItem(0, AxePanel);
				panelinfo.setItem(1, RegenPanel);
				panelinfo.setItem(2, RodPanel);
				panelinfo.setItem(3, ShieldPanel);
				panelinfo.setItem(4, CreditPanel);
				panelinfo.setItem(5, PotEnchPanel);
				panelinfo.setItem(6, KenobiPanel);
				panelinfo.setItem(7, KaBoomPanel);
				panelinfo.setItem(8, MouvPanel);

				Bukkit.getPlayer(sender.getName()).openInventory(panelinfo);
			}
			else if(args[0].contains("info") & SenderIsPlayer) {

				Bukkit.getPlayer(sender.getName()).openBook(book);
			}

			if(args[0].contains("setdefault")) {

				this.getConfig().set("Axe s Damage reduction", Axe);
				this.getConfig().set("Forbiding Shield owning and crafting", Shield);
				this.getConfig().set("Slower Regeneraion", regen);
				this.getConfig().set("Knockback Fishing_rod", rod);
				this.getConfig().set("Add Potion Effect to unbreaking", PotionEnchant);
				this.getConfig().set("Kenobi s style", Kenobi);
				this.getConfig().set("Make fireworks explose when shoted by a crossbow", KaBoom);
				this.getConfig().set("Allow double jump and dash", Mouv);
				this.saveConfig();

				switchL.put("English", "This configuration has been set to default");
				switchL.put("Français", "Cette configuration est maintenant celle par défault");
				switchL.put("Espanol", "Esta configuración se configura de manera predeterminada");

				Ad = switchL.get(languageActive);

				sender.sendMessage(ChatColor.GOLD +Ad);



			}

			if(args[0].contains("toggle")) {

				if(this.getConfig().getBoolean("PVP18")) {

					this.getConfig().set("PVP18", false);

					switchL.put("English", "RustedSword is now disabled by default");
					switchL.put("Français", "RustedSword est maintenant désactivé par défault");
					switchL.put("Espanol", "RustedSword ahora está deshabilitado por defecto");

					Ad = switchL.get(languageActive);

					sender.sendMessage(ChatColor.GOLD +Ad);
				}
				else if(this.getConfig().getBoolean("PVP18") == false) {

					this.getConfig().set("PVP18", true);

					switchL.put("English", "RustedSword is now activated by default");
					switchL.put("Français", "RustedSword est maintenant activé par défault");
					switchL.put("Espanol", "RustedSword ahora está activado por defecto");

					Ad = switchL.get(languageActive);

					sender.sendMessage(ChatColor.GOLD +Ad);
				}
				this.saveConfig();

			}


		}	



		return true;
	}



	@EventHandler
	public boolean Panel(InventoryClickEvent e) {


		try {
			Bukkit.getPlayer(e.getWhoClicked().getName());
			e.getClickedInventory().getType();
		}catch(Exception ex) {
			return false;
		}

		if(e.getClickedInventory().equals(panelinfo) & e.getCurrentItem() != null) {

			Player pe = Bukkit.getPlayer(e.getWhoClicked().getName());

			ItemStack item = e.getCurrentItem();
			meta = item.getItemMeta();	

			if(item.getType()==Material.WRITTEN_BOOK) {

				List<String> links = this.getConfig().getStringList("links");
				for(String link : links) {
					pe.sendMessage(ChatColor.ITALIC + link);
				}
			}
		}




		if(e.getClickedInventory().equals(panel) & e.getCurrentItem() != null) {

			Player pe = Bukkit.getPlayer(e.getWhoClicked().getName());


			ItemStack item = e.getCurrentItem();
			meta = item.getItemMeta();		
			boolean OpOk = pe.isOp();

			if(item.getType() == Material.GOLDEN_CARROT & regen == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				RegenPanel.setItemMeta(meta);
				panel.setItem(2, RegenPanel);

				BukkitRunnable RegenFix = new RegenFix();
				RegenFix.runTaskTimer(this, 80, 80);

				taskID2 = RegenFix.getTaskId();

				for(World wrld : Bukkit.getWorlds()) {
					wrld.setGameRule(GameRule.NATURAL_REGENERATION, false);
				}
				regen = true;

			}
			else if(item.getType() == Material.GOLDEN_CARROT & regen == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				RegenPanel.setItemMeta(meta);
				panel.setItem(2, RegenPanel);

				Bukkit.getScheduler().cancelTask(taskID2);
				for(World wrld : Bukkit.getWorlds()) {
					wrld.setGameRule(GameRule.NATURAL_REGENERATION, true);
				}
				regen = false;
			}




			if(item.getType() == Material.FISHING_ROD & rod == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				RodPanel.setItemMeta(meta);
				panel.setItem(3, RodPanel);

				BukkitRunnable RodFix = new RodFIx();
				RodFix.runTaskTimer(this, 1, 1);

				taskID3 = RodFix.getTaskId();
				rod = true;
			}
			else if(item.getType() == Material.FISHING_ROD & rod == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				RodPanel.setItemMeta(meta);
				panel.setItem(3, RodPanel);
				rod = false;

				Bukkit.getScheduler().cancelTask(taskID3);
				rod = false;


			}

			if(item.getType() == Material.SHIELD & Shield == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				ShieldPanel.setItemMeta(meta);
				panel.setItem(4, ShieldPanel);

				BukkitRunnable ShieldFix = new ShieldFix();
				ShieldFix.runTaskTimer(this, 20, 20);

				taskID4 = ShieldFix.getTaskId();
				Shield = true;


			}else if(item.getType() == Material.SHIELD & Shield == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				ShieldPanel.setItemMeta(meta);
				panel.setItem(4, ShieldPanel);
				Shield = false;

				Bukkit.getScheduler().cancelTask(taskID4);
				Shield = false;


			}

			if(item.getType() == Material.IRON_AXE & Axe == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				AxePanel.setItemMeta(meta);
				panel.setItem(1, AxePanel);

				Axe = true;


			}else if(item.getType() == Material.IRON_AXE & Axe == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				AxePanel.setItemMeta(meta);
				panel.setItem(1, AxePanel);

				Axe = false;


			}

			if(item.getType() == Material.GLASS_BOTTLE & PotionEnchant == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				PotEnchPanel.setItemMeta(meta);
				panel.setItem(6, PotEnchPanel);


				PotionEnchant = true;


			}else if(item.getType() == Material.GLASS_BOTTLE & PotionEnchant == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				PotEnchPanel.setItemMeta(meta);
				panel.setItem(6, PotEnchPanel);

				PotionEnchant = false;


			}

			if(item.getType() == Material.DIAMOND_SWORD & Kenobi == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				KenobiPanel.setItemMeta(meta);
				panel.setItem(7, KenobiPanel);


				Kenobi = true;


			}else if(item.getType() == Material.DIAMOND_SWORD & Kenobi == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				KenobiPanel.setItemMeta(meta);
				panel.setItem(7, KenobiPanel);

				Kenobi = false;


			}

			if(item.getType() == Material.FIREWORK_ROCKET & KaBoom == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				KaBoomPanel.setItemMeta(meta);
				panel.setItem(8, KaBoomPanel);


				KaBoom = true;


			}else if(item.getType() == Material.FIREWORK_ROCKET & KaBoom == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				KaBoomPanel.setItemMeta(meta);
				panel.setItem(8, KaBoomPanel);

				KaBoom = false;


			}



			if(item.getType() == Material.MAGENTA_GLAZED_TERRACOTTA & Mouv == false & OpOk) {

				meta.addEnchant(Enchantment.MENDING, 1, true);
				meta.setLore(Arrays.asList(ChatColor.GREEN + activated));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				MouvPanel.setItemMeta(meta);
				panel.setItem(9, MouvPanel);		

				Mouv = true;

				BukkitRunnable isOnGround = new isOnGround();
				isOnGround.runTaskTimer(this, 1, 1);

				taskID7 = isOnGround.getTaskId();

				for(Object ob : Bukkit.getOnlinePlayers().toArray()) {

					Player inter = (Player) ob;
					Player pl = Bukkit.getPlayer(inter.getName());

					pl.setAllowFlight(true);
				}

			}else if(item.getType() == Material.MAGENTA_GLAZED_TERRACOTTA & Mouv  == true & OpOk) {

				meta.removeEnchant(Enchantment.MENDING);
				meta.setLore(Arrays.asList(ChatColor.RED +disabled));
				meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

				MouvPanel.setItemMeta(meta);
				panel.setItem(9, MouvPanel);

				Mouv = false;

				Bukkit.getScheduler().cancelTask(taskID7);

				for(Object ob : Bukkit.getOnlinePlayers().toArray()) {

					Player inter = (Player) ob;
					Player pl = Bukkit.getPlayer(inter.getName());

					if(pl.getGameMode().equals(GameMode.CREATIVE)==false) {
						pl.setAllowFlight(false);
					}
				}



			}




			if(item.getType()==Material.WRITTEN_BOOK) {

				List<String> links = this.getConfig().getStringList("links");
				for(String link : links) {
					pe.sendMessage(ChatColor.ITALIC + link);
				}
			}

			if(item.getType().toString().contains("BANNER") & OpOk ){

				try {
					IndexL +=1;
					languageActive = ListLanguage.get(IndexL);	
					Lbanner = Maplanguage.get(languageActive);

				}catch(ArrayIndexOutOfBoundsException ex) {
					IndexL = 0;
					languageActive = ListLanguage.get(IndexL);	
					Lbanner = Maplanguage.get(languageActive);

				}
				panel.setItem(0, Lbanner);
				this.getConfig().set("Language", languageActive);
				saveConfig();

			}



		}
		return true;

	}

	@EventHandler
	public void InvDrag(InventoryClickEvent e) {

		try {
			if(PVP18 & e.getInventory().equals(panel) | e.getInventory().equals(panelinfo) ) {

				e.setCancelled(true);
			}

			if(PVP18 & e.getInventory().getType().equals(InventoryType.ANVIL) & e.getViewers().contains(Anviler) & e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GOLD + ""+ChatColor.MAGIC+"123456789") & e.getCurrentItem().getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT)==false) {

				meta  = e.getCurrentItem().getItemMeta();
				meta.setLore(Arrays.asList(secretLore));
				e.getCurrentItem().setItemMeta(meta);

				Anviler = null;
			}


		}catch(Exception ex) {

		}


	}


	@EventHandler
	public void CraftFixounet(CraftItemEvent e) {

		if(Shield & PVP18) {

			if(e.getRecipe().getResult().getType() == Material.SHIELD) {
				e.setCancelled(true);
			}
		}



	}


	@EventHandler
	public void d(EntityDamageByEntityEvent e) {

		if(PVP18) {
			damageSource = e.getDamager();
			if(damageSource.getType().equals(EntityType.PLAYER)) {
				Killer = Bukkit.getPlayer(damageSource.getUniqueId());

				if(Killer.getInventory().getItemInMainHand().getType().toString().contains("AXE") & Axe & PVP18) {
					e.setDamage(e.getDamage() * 0.5);
				}
			}
			else{
				Killer = null;
			}
		}

	}

	@EventHandler
	public void e(EntityDamageEvent e) {

		if(e.getCause().equals(DamageCause.FALL) & Mouv & PVP18) {

			e.setCancelled(true);

		}

		if(Killer != null & PVP18){

			double dam = e.getDamage();
			boolean smp = true;
			double TrueSWfix = 0;

			if(Killer.hasPotionEffect(PotionEffectType.WEAKNESS)) {

				if(Killer.getPotionEffect(PotionEffectType.WEAKNESS).getAmplifier() > 0) {
					dam = e.getDamage()-9;
				}
			}

			if(Killer.hasPotionEffect(PotionEffectType.WEAKNESS) & Killer.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE) ) {
				smp = false;
				TrueSWfix = (Killer.getPotionEffect(PotionEffectType.WEAKNESS).getAmplifier() + 1)*-SWfix + (Killer.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + 1)*SWfix;

				dam = (dam +(Killer.getPotionEffect(PotionEffectType.WEAKNESS).getAmplifier() + 1)*4 - (Killer.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() + 1)*3)*(1+TrueSWfix)    ;
			}


			for(PotionEffect ef : Killer.getActivePotionEffects()) {



				if(ef.getType().equals(PotionEffectType.INCREASE_DAMAGE) & smp) {

					dam = (dam - (ef.getAmplifier()+1)*3)*(1 + (ef.getAmplifier()+1)*SWfix);
				}

				if(ef.getType().equals(PotionEffectType.WEAKNESS) & smp) {

					dam = (dam + (ef.getAmplifier()+1)*4)*(1 - (ef.getAmplifier()+1)*SWfix);


				}
				e.setDamage(dam);

			}

		}

		if(PVP18 & PotionEnchant & Killer != null) {

			ItemStack it = Killer.getInventory().getItemInMainHand();

			if(it.getItemMeta().hasLore() & it.getItemMeta().hasEnchant(Enchantment.DURABILITY) & e.getCause() != EntityDamageEvent.DamageCause.POISON & e.getCause() != EntityDamageEvent.DamageCause.WITHER & PVP18) {

				LivingEntity ent = null;

				if(e.getEntity() instanceof LivingEntity) {
					ent = (LivingEntity) e.getEntity();
				}


				for(String str : Killer.getEquipment().getItemInMainHand().getItemMeta().getLore()) {

					int amplifier = 0;
					int dur = 12;

					if(str.contains("II")) {
						amplifier = 1;
					}

					if(str.contains("Weakness") | str.contains("Slowness") | str.contains("Hunger II") ) {
						dur = 6;
					}
					if(str.contains("Wither II") | str.contains("Poison II")) {
						dur = 3;
					}


					PotionEffect hunger = new PotionEffect(PotionEffectType.HUNGER,dur*20,amplifier,false,true);
					PotionEffect weakness = new PotionEffect(PotionEffectType.WEAKNESS,dur*20,amplifier,false,true);
					PotionEffect glowing = new PotionEffect(PotionEffectType.GLOWING,dur*20,amplifier,false,true);
					PotionEffect poison = new PotionEffect(PotionEffectType.POISON,dur*20,amplifier,false,true);
					PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW,dur*20,amplifier,false,true);
					PotionEffect wither = new PotionEffect(PotionEffectType.WITHER,dur*20,amplifier,false,true);
					PotionEffect miningfatigue = new PotionEffect(PotionEffectType.SLOW_DIGGING,dur*20,amplifier,false,true);

					if(str.contains("Hunger")) {
						ent.addPotionEffect(hunger);
					}
					if(str.contains("Weakness")) {
						ent.addPotionEffect(weakness);
					}
					if(str.contains("Glowing")) {
						ent.addPotionEffect(glowing);
					}
					if(str.contains("Poison")) {
						ent.addPotionEffect(poison);
					}
					if(str.contains("Slowness")) {
						ent.addPotionEffect(slowness);
					}
					if(str.contains("Wither")) {
						ent.addPotionEffect(wither);
					}
					if(str.contains("Mining Fatigue")) {
						ent.addPotionEffect(miningfatigue);
					}
				}


			}
		}

		Killer = null;
	}

	@EventHandler 

	public void WeakII(EntityPotionEffectEvent e) {

		if(e.getAction().equals(EntityPotionEffectEvent.Action.ADDED) == false & e.getModifiedType().equals(PotionEffectType.WEAKNESS)) {

			if(e.getOldEffect().getAmplifier() > 0 & e.getEntityType().equals(EntityType.PLAYER)) {

				Player p = Bukkit.getPlayer(e.getEntity().getUniqueId());
				AttributeInstance pit = p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
				pit.setBaseValue(pit.getDefaultValue() -1);

			}

		}
	}


	@EventHandler

	public void OnClick(PlayerInteractEvent e) {


		if(e.getAction().equals(Action.LEFT_CLICK_AIR) & e.getMaterial().equals(Material.DIAMOND_SWORD) & Kenobi & PVP18) {

			for(Entity ent : e.getPlayer().getNearbyEntities(2, 2, 2)) {

				if(ent.getType().equals(EntityType.ARROW) & ent.isOnGround() == false) {
					Arrow arr = (Arrow) ent;
					ent.remove();

					Arrow Sb = (Arrow) e.getPlayer().launchProjectile(Arrow.class);
					Sb.setDamage(arr.getDamage()*0.5);
					e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 3, 3);
				}

			}	

		}


		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) & e.getMaterial().toString().contains("SWORD") & Mouv & PVP18 & AntiDash.contains(e.getPlayer().getInventory().getItemInOffHand().getType())==false & e.getPlayer().isSneaking() == false) {

			Vector dash = e.getPlayer().getLocation().getDirection();
			dash.setY(0);
			dash.setX(dash.getX()*1.2);
			dash.setZ(dash.getZ()*1.2);

			if(cooldowns.containsKey(e.getPlayer().getUniqueId())) {

				long secondLeft = (cooldowns.get(e.getPlayer().getUniqueId()) /1000) + cooldowntime - System.currentTimeMillis()/1000;

				if(secondLeft > 0) {

					switchL.put("English", "You can't dash, wait another ");
					switchL.put("Français", "Vous ne pouvez pas dash, attendez encore ");
					switchL.put("Espanol", "No puedes dash, espera otro ");

					Ad = switchL.get(languageActive);

					e.getPlayer().sendMessage(ChatColor.RED + Ad +ChatColor.BOLD + Long.toString(secondLeft) +"s");
					e.setCancelled(true);


				}else {
					cooldowns.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());

					e.getPlayer().setVelocity(dash);

					Location loco = e.getPlayer().getLocation();
					loco.setY(loco.getY()+1);

					e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ITEM_TRIDENT_THROW, 3, 3);
					e.getPlayer().getWorld().spawnParticle(Particle.SWEEP_ATTACK, loco, 50);
				}

			}else {

				cooldowns.put(e.getPlayer().getUniqueId(), System.currentTimeMillis());

				e.getPlayer().setVelocity(dash);
				e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ITEM_TRIDENT_THROW, 3, 3);
				e.getPlayer().getWorld().spawnParticle(Particle.SWEEP_ATTACK, e.getPlayer().getLocation(), 50);

			}



		}
	}


	@EventHandler
	public void DbJump(PlayerToggleFlightEvent e) {

		if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE) == false) {

			e.setCancelled(true);
		}

		if(dbJumper.containsKey(e.getPlayer().getUniqueId()) & e.getPlayer().getGameMode().equals(GameMode.CREATIVE) == false & Mouv & PVP18) {

			if(dbJumper.get(e.getPlayer().getUniqueId())) {

				int jumpHeight;

				if(e.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
					jumpHeight = e.getPlayer().getPotionEffect(PotionEffectType.JUMP).getAmplifier();
				}else {
					jumpHeight = 0;
				}

				Vector dbJump = e.getPlayer().getLocation().getDirection();

				dbJump.setY(0.5 + 0.2 *jumpHeight);

				dbJump.setX(dbJump.getX() /2);
				dbJump.setZ(dbJump.getZ() /2);

				e.getPlayer().setVelocity(dbJump);

				Location loca = e.getPlayer().getLocation();
				loca.setY(loca.getY()+1);

				e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_BAT_TAKEOFF, SoundCategory.AMBIENT, (float) 1, (float) 0.5);
				e.getPlayer().getWorld().spawnParticle(Particle.SPELL_INSTANT, loca, 50);

				dbJumper.put(e.getPlayer().getUniqueId(), false);
				e.getPlayer().setAllowFlight(false);

			}

		}




	}

	@EventHandler
	public void AllowDbjump_DamageDash(PlayerMoveEvent e) {

		if(e.getPlayer().isOnGround() & Mouv & PVP18) {
			dbJumper.put(e.getPlayer().getUniqueId(), true);
		}

		if(cooldowns.containsKey(e.getPlayer().getUniqueId())) {

			long secondLeft = (cooldowns.get(e.getPlayer().getUniqueId()) /1000) + cooldowntime - System.currentTimeMillis()/1000;

			if(secondLeft > 7){

				for(Entity enti : e.getPlayer().getNearbyEntities(1, 1, 1)) {

					if(enti instanceof LivingEntity) {
						LivingEntity po = (LivingEntity) enti;
						po.damage(3);

						Location loca = po.getLocation();
						loca.setY(loca.getY()+1);

						po.getWorld().spawnParticle(Particle.CRIT,loca, 60);
					}

				}

			}
		}

	}


	@EventHandler
	public void testEnchant(EnchantItemEvent e) {

		if(e.getEnchantsToAdd().containsKey(Enchantment.DURABILITY) & e.getEnchantsToAdd().containsKey(Enchantment.FIRE_ASPECT) == false & PotionEnchant & PVP18){

			int amp = e.getEnchantsToAdd().get(Enchantment.DURABILITY);

			String effect;

			switch(amp) {

			case 1:
				meta = e.getItem().getItemMeta();
				effect = un1[random.nextInt(un1.length)];
				meta.setLore(Arrays.asList(effect));
				e.getItem().setItemMeta(meta);
				break;

			case 2:
				meta = e.getItem().getItemMeta();
				effect = un2[random.nextInt(un2.length)];
				meta.setLore(Arrays.asList(effect));
				e.getItem().setItemMeta(meta);
				break;

			case 3:
				meta = e.getItem().getItemMeta();
				effect = un3[random.nextInt(un3.length)];
				meta.setLore(Arrays.asList(effect));
				e.getItem().setItemMeta(meta);
				break;

			}

		}
	}

	@EventHandler
	public void testAnvil(PrepareAnvilEvent e) {

		try {
			if(e.getResult().getItemMeta().hasEnchant(Enchantment.DURABILITY) & e.getResult().getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT) == false & PotionEnchant & PVP18) {


				int amp = e.getResult().getItemMeta().getEnchantLevel(Enchantment.DURABILITY);
				for(HumanEntity p : e.getViewers()) {

					Anviler = p;
				}


				switch(amp) {

				case 1:
					meta = e.getResult().getItemMeta();
					secretLore = un1[random.nextInt(un1.length)];
					meta.setLore(Arrays.asList(ChatColor.GOLD + ""+ChatColor.MAGIC+"123456789"));
					e.getResult().setItemMeta(meta);
					break;

				case 2:
					meta = e.getResult().getItemMeta();
					secretLore = un2[random.nextInt(un2.length)];
					meta.setLore(Arrays.asList(ChatColor.GOLD + ""+ChatColor.MAGIC+"123456789"));
					e.getResult().setItemMeta(meta);
					break;

				case 3:
					meta = e.getResult().getItemMeta();
					secretLore = un3[random.nextInt(un3.length)];
					meta.setLore(Arrays.asList(ChatColor.GOLD + ""+ChatColor.MAGIC+"123456789"));
					e.getResult().setItemMeta(meta);
					break;

				}
			}

			if(e.getInventory().getItem(0).containsEnchantment(Enchantment.DURABILITY)) {

				if(e.getResult().getEnchantmentLevel(Enchantment.DURABILITY) <= e.getInventory().getItem(0).getEnchantmentLevel(Enchantment.DURABILITY)){

					meta = e.getResult().getItemMeta();
					meta.setLore(e.getInventory().getItem(0).getItemMeta().getLore());
					e.getResult().setItemMeta(meta);
				}
			}
			if(e.getInventory().getItem(1).containsEnchantment(Enchantment.DURABILITY)) {

				if(e.getResult().getEnchantmentLevel(Enchantment.DURABILITY) <= e.getInventory().getItem(1).getEnchantmentLevel(Enchantment.DURABILITY)){

					meta = e.getResult().getItemMeta();
					meta.setLore(e.getInventory().getItem(1).getItemMeta().getLore());
					e.getResult().setItemMeta(meta);
				}
			}
			if(e.getResult().getItemMeta().hasEnchant(Enchantment.FIRE_ASPECT)) {
				meta = e.getResult().getItemMeta();
				meta.setLore(null);
				e.getResult().setItemMeta(meta);
			}

		}catch(Exception ex) {}

	}


	@EventHandler
	public void FireWorks(FireworkExplodeEvent e) {


		if(KaBoom & PVP18) {
			boolean boom = false;

			Firework fw = (Firework) e.getEntity();
			int range = e.getEntity().getFireworkMeta().getPower();



			for(Entity ent : fw.getNearbyEntities(range+1, range+1, range+1)) {

				if(ent.getTicksLived() > 0) {
					boom = true;
				}
			}


			if(boom) {

				fw.detonate();

				if(range == 3) {

					e.getEntity().getWorld().createExplosion(fw.getLocation(), range-1, true);

				}else{

					e.getEntity().getWorld().createExplosion(fw.getLocation(), range);
				}

			}	
		}

	}



	@Override
	public void onDisable() {

		if(Bukkit.getScheduler().getPendingTasks().isEmpty() == false) {
			Bukkit.getScheduler().cancelTasks(this);
		}
		try {

			for(World wrld : Bukkit.getWorlds()) {
				wrld.setGameRule(GameRule.NATURAL_REGENERATION, true);
			}

		}catch(Exception e) {}

		for (Object ob : Bukkit.getOnlinePlayers().toArray()) { 

			Player inter = (Player) ob;
			Player pl = Bukkit.getPlayer(inter.getName());

			AttributeInstance pit = pl.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
			pit.setBaseValue(pit.getDefaultValue()-1);

			for(AttributeModifier att : pl.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getModifiers()) {
				pl.getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(att);
			}

			if(pl.getGameMode().equals(GameMode.CREATIVE)==false) {
				pl.setAllowFlight(false);
			
			}
			
		}

	}

}






