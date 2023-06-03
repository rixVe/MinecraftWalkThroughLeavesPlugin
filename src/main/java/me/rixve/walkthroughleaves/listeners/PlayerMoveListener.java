package me.rixve.walkthroughleaves.listeners;

import me.rixve.walkthroughleaves.WalkThroughLeaves;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListener implements Listener {

    private final WalkThroughLeaves plugin;

    public PlayerMoveListener(WalkThroughLeaves plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event) {
        Block block = null;
        Player player = event.getPlayer();

        Location locFrom = event.getFrom();
        Location locTo = event.getTo();

        //Checking for leaves nearby

        Block tempBlock = new Location(locTo.getWorld(), locTo.getX() + 0.31, locTo.getY(), locTo.getZ()).getBlock();
        if(plugin.leaves.contains(tempBlock.getType()) && player.getFacing() == BlockFace.EAST) {
            block = tempBlock;
        }

        tempBlock = new Location(locTo.getWorld(), locTo.getX() - 0.31, locTo.getY(), locTo.getZ()).getBlock();
        if(plugin.leaves.contains(tempBlock.getType()) && player.getFacing() == BlockFace.WEST) {
            block = tempBlock;
        }

        tempBlock = new Location(locTo.getWorld(), locTo.getX(), locTo.getY(), locTo.getZ() +0.31).getBlock();
        if(plugin.leaves.contains(tempBlock.getType()) && player.getFacing() == BlockFace.SOUTH) {
            block = tempBlock;
        }

        tempBlock = new Location(locTo.getWorld(), locTo.getX(), locTo.getY(), locTo.getZ() -0.31).getBlock();
        if(plugin.leaves.contains(tempBlock.getType()) && player.getFacing() == BlockFace.NORTH) {
            block = tempBlock;
        }


        //If player was in leaves and is about to walk out of them

        if(plugin.changedBlocks.containsKey(locFrom.getBlock().getLocation()) && (!plugin.changedBlocks.containsKey(locTo.getBlock().getLocation()) || block != null)) {
            locFrom.getBlock().setType(plugin.changedBlocks.get(locFrom.getBlock().getLocation()));
            plugin.changedBlocks.remove(locFrom.getBlock().getLocation());

            plugin.fallingBlocks.get(locFrom.getBlock().getLocation().add(0.5, 0, 0.5)).remove();
            plugin.fallingBlocks.remove(locFrom.getBlock().getLocation().add(0.5, 0, 0.5));

            player.removePotionEffect(PotionEffectType.SLOW);
        }

        //If player goes into leaves

        if(block != null) {
            plugin.changedBlocks.put(block.getLocation(), block.getType());

            FallingBlock fallingBlock = block.getWorld().spawnFallingBlock(block.getLocation().add(0.5, 0, 0.5), plugin.changedBlocks.get(block.getLocation()).createBlockData());
            fallingBlock.setGravity(false);
            fallingBlock.setTicksLived(Integer.MAX_VALUE);
            plugin.fallingBlocks.put(block.getLocation().add(0.5, 0, 0.5), fallingBlock);

            block.setType(Material.AIR);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3, true, false, false));
        }

        //The same thing, just one block above
        Location locTo1 = new Location(event.getTo().getWorld(), event.getTo().getX(), event.getTo().getY() + 1, event.getTo().getZ());
        Location locFrom1 = new Location(event.getFrom().getWorld(), event.getFrom().getX(), event.getFrom().getY() + 1, event.getFrom().getZ());

        Block block1 = null;
        Block tempBlock1 = new Location(locTo1.getWorld(), locTo1.getX() + 0.31, locTo1.getY(), locTo1.getZ()).getBlock();
        if(plugin.leaves.contains(tempBlock1.getType()) && player.getFacing() == BlockFace.EAST) {
            block1 = tempBlock1;
        }

        tempBlock1 = new Location(locTo1.getWorld(), locTo1.getX() - 0.31, locTo1.getY(), locTo1.getZ()).getBlock();
        if(plugin.leaves.contains(tempBlock1.getType()) && player.getFacing() == BlockFace.WEST) {
            block1 = tempBlock1;
        }

        tempBlock1 = new Location(locTo1.getWorld(), locTo1.getX(), locTo1.getY(), locTo1.getZ() +0.31).getBlock();
        if(plugin.leaves.contains(tempBlock1.getType()) && player.getFacing() == BlockFace.SOUTH) {
            block1 = tempBlock1;
        }

        tempBlock1 = new Location(locTo1.getWorld(), locTo1.getX(), locTo1.getY(), locTo1.getZ() -0.31).getBlock();
        if(plugin.leaves.contains(tempBlock1.getType()) && player.getFacing() == BlockFace.NORTH) {
            block1 = tempBlock1;
        }

        if(plugin.changedBlocks.containsKey(locFrom1.getBlock().getLocation()) && (!plugin.changedBlocks.containsKey(locTo1.getBlock().getLocation()) || block1 != null)) {
            locFrom1.getBlock().setType(plugin.changedBlocks.get(locFrom1.getBlock().getLocation()));
            plugin.changedBlocks.remove(locFrom1.getBlock().getLocation());

            plugin.fallingBlocks.get(locFrom1.getBlock().getLocation().add(0.5, 0, 0.5)).remove();
            plugin.fallingBlocks.remove(locFrom1.getBlock().getLocation().add(0.5, 0, 0.5));

            player.removePotionEffect(PotionEffectType.SLOW);
        }

        if(block != null && block1 != null) {
            plugin.changedBlocks.put(block1.getLocation(), block1.getType());

            FallingBlock fallingBlock = block1.getWorld().spawnFallingBlock(block1.getLocation().add(0.5, 0, 0.5), plugin.changedBlocks.get(block1.getLocation()).createBlockData());
            fallingBlock.setGravity(false);
            fallingBlock.setTicksLived(Integer.MAX_VALUE);
            plugin.fallingBlocks.put(block1.getLocation().add(0.5, 0, 0.5), fallingBlock);

            block1.setType(Material.AIR);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 3, true, false, false));
        }

    }

}
