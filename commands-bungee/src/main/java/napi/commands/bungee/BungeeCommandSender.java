package napi.commands.bungee;

import napi.commands.parsed.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Optional;
import java.util.UUID;

public final class BungeeCommandSender implements CommandSender {

    private final net.md_5.bungee.api.CommandSender sender;

    public BungeeCommandSender(net.md_5.bungee.api.CommandSender sender){
        this.sender = sender;
    }

    @Override
    public <T> T getSender() {
        return (T) sender;
    }

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public Optional<UUID> getUuid() {
        return Optional.ofNullable(sender instanceof ProxiedPlayer ? ((ProxiedPlayer)sender).getUniqueId() : null);
    }

    @Override
    public void sendMessage(String... message) {
        ComponentBuilder builder = new ComponentBuilder();

        for (String line : message){
            builder.append(line);
            builder.append("\n");
        }

        sender.sendMessage(builder.create());
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
