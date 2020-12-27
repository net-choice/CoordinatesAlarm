package net.netchoice.coordinatesAlarm

import org.bukkit.plugin.java.JavaPlugin


class CAPlugin: JavaPlugin(){
    override fun onEnable() {
        logger.info("Coordinates Alarm plugin enabled")
    }

    override fun onDisable() {
        logger.info("Coordinates Alarm plugin disabled")
    }
}