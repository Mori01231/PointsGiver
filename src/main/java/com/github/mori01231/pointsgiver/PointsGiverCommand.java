package com.github.mori01231.pointsgiver;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class PointsGiverCommand implements CommandExecutor {

    private final PointsGiver plugin = PointsGiver.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // check for permissions
        try{
            Player p = (Player) sender;
            if(p instanceof Player){
                getLogger().info("Is player");
                if(!sender.hasPermission("pointsgive.give")){
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c権限不足"));
                    return true;
                }
            }
        }catch(Exception e){
            // sender is console
        }


        // determine the amount of points to give
        int givePointsTemp;
        getLogger().info("Sending PlayerPoints.");
        try{
            givePointsTemp = Integer.parseInt(args[0]);
        }catch(Exception e){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe first argument must be an integer."));
            return true;
        }
        final int givePoints = givePointsTemp;

        // actually give the points
        getLogger().info("Running bukkit runnable");
        int i = 1;
        for(Player player : getServer().getOnlinePlayers()){
            try{
                String playerName = player.getName();
                // 1 second delay in between each player getting their points
                i+= 20;
                if(playerName != null){
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            PlayerPoints.getInstance().getAPI().giveAsync(player.getUniqueId(), givePoints).thenAccept(result -> {
                                if (result) {
                                    getLogger().info("Gave " + playerName + " " + givePoints + " PlayerPoints.");
                                } else {
                                    getLogger().warning("Unable to give " + playerName + " " + givePoints + " PlayerPoints.");
                                }
                            });
                        }
                    }.runTaskLater(plugin, i);

                }
            }catch(Exception e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cException"));
            }
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Gave all players in server " + givePoints + " PlayerPoints."));

        return true;
    }
}
