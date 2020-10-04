package com.github.mori01231.pointsgiver;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class PointsGiverCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        int givePoints = 0;
        try{
            givePoints = Integer.parseInt(args[0]);
        }catch(Exception e){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe argument must be an integer."));
            return true;
        }

        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            String playerName = player.getDisplayName();
            getServer().dispatchCommand(getServer().getConsoleSender(), "points give " + playerName + " " + givePoints);
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Gave all players in server " + givePoints + " points."));
        return true;
    }
}
