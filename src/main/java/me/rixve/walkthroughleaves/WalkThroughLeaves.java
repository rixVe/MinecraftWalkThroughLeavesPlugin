package me.rixve.walkthroughleaves;

import me.rixve.walkthroughleaves.listeners.PlayerMoveListener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public final class WalkThroughLeaves extends JavaPlugin {

    public HashMap<Location, Material> changedBlocks = new HashMap<>();
    public HashMap<Location, FallingBlock> fallingBlocks = new HashMap<>();

    public List<Material> leaves = new ArrayList<>();

    @Override
    public void onEnable() {
        this.leaves.add(Material.OAK_LEAVES);
        this.leaves.add(Material.ACACIA_LEAVES);
        this.leaves.add(Material.AZALEA_LEAVES);
        this.leaves.add(Material.BIRCH_LEAVES);
        this.leaves.add(Material.DARK_OAK_LEAVES);
        this.leaves.add(Material.FLOWERING_AZALEA_LEAVES);
        this.leaves.add(Material.JUNGLE_LEAVES);
        this.leaves.add(Material.SPRUCE_LEAVES);

        this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);

        this.getLogger().log(Level.INFO, "WalkThroughLeaves Enabled!");
    }

}
