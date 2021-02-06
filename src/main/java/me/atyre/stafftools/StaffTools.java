package me.atyre.stafftools;

import me.atyre.stafftools.commands.AdminChatCommand;
import me.atyre.stafftools.commands.ReportCommand;
import me.atyre.stafftools.commands.RequestCommand;
import me.atyre.stafftools.commands.StaffChatCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class StaffTools extends JavaPlugin {

    private static StaffTools instance;

    @Override
    public void onEnable() {
        instance = this;

        this.getCommand("adminchat").setExecutor(new AdminChatCommand());
        this.getCommand("report").setExecutor(new ReportCommand());
        this.getCommand("request").setExecutor(new RequestCommand());
        this.getCommand("staffchat").setExecutor(new StaffChatCommand());
    }

    @Override
    public void onDisable() {
        instance = null;

    }

    public static StaffTools getInstance() {
        return instance;
    }



}
