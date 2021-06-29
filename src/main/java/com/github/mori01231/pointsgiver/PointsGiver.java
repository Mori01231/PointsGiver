package com.github.mori01231.pointsgiver;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PointsGiver extends JavaPlugin {

    private static PointsGiver instance;

    public PointsGiver (){
        instance = this;
    }

    public static PointsGiver getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        getLogger().info("PointsGiver has been enabled.");
        this.getCommand("pointsgive").setExecutor(new PointsGiverCommand());

        int PointsAmount = getConfig().getInt("points_amount");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                sendCommand("pointsgive " + PointsAmount);
            }
        }, 0L, 24000L); //0 Tick initial delay, 24000 Tick (20 minutes) between repeats
    }

    @Override
    public void onDisable() {
        getLogger().info("PointsGiver has been disabled.");
    }

    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }
}
