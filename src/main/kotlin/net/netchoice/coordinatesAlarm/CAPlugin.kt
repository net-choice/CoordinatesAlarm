package net.netchoice.coordinatesAlarm

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.*

import kotlin.concurrent.timer
import kotlin.concurrent.schedule
import java.util.Timer




class CAPlugin: JavaPlugin(){

    override fun onEnable() {
        logger.info("Coordinates Alarm plugin enabled")
        val worlds = Bukkit.getWorlds()
        time(worlds=worlds)
    }

    override fun onDisable() {
        logger.info("Coordinates Alarm plugin disabled")
    }

    private fun enableDebugInfo(worlds: MutableList<World>){
        server.broadcastMessage("좌표 비활성화 되었습니다.")
        for (world in worlds) {
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true)
        }

    }

    private fun disableDebugInfo(worlds: MutableList<World>){
        server.broadcastMessage("좌표 활성화 되었습니다.")
        for (world in worlds){
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO, false)
        }
        var second : Int = 0
        timer(period=1000, initialDelay=1000) {
            second++
            println(second)
            if (second==5) {
                enableDebugInfo(worlds = worlds)
                cancel(); time(worlds=worlds)

            }
        }
    }

    private fun time(worlds: MutableList<World>) {
        val timer = Timer("schedule", true)
        timer.schedule(10000) {
            disableDebugInfo(worlds=worlds)
        }
    }
}