package net.bricn.noticePlugin

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable


class NoticePlugin: JavaPlugin() {
    override fun onEnable() {
        startAutoChat()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun startAutoChat() {
        val interval = config.getInt("chat-interval") * 20
        object : BukkitRunnable() {
            override fun run() {
                sendHyperlink()
            }
        }.runTaskTimer(this, 0L, interval.toLong())
    }

    private fun sendHyperlink(){
        val message = TextComponent("프리파이 야생 서버입니다.")
        message.color = ChatColor.GRAY

        val link = TextComponent("디스코드 링크")
        link.color = ChatColor.BLUE
        link.isBold = true
        link.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/2KYBXtWK59")

        message.addExtra(link)

        for (player in Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(message)
        }
    }
}
