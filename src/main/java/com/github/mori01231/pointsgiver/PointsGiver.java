package com.github.mori01231.pointsgiver;

import org.bukkit.plugin.java.JavaPlugin;

public final class PointsGiver extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("PointsGiver has been enabled.");
        this.getCommand("givepoints").setExecutor(new PointsGiverCommand());

    }

    @Override
    public void onDisable() {
        getLogger().info("PointsGiver has been disabled.");
    }
}
