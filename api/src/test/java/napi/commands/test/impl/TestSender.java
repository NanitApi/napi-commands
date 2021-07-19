package napi.commands.test.impl;

import napi.commands.parsed.CommandSender;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class TestSender implements CommandSender {

    @Override
    public <T> T getSender() {
        return null;
    }

    @Override
    public String getName() {
        return "TestSender";
    }

    @Override
    public Optional<UUID> getUuid() {
        return Optional.of(UUID.randomUUID());
    }

    @Override
    public void sendMessage(String... message) {
        System.out.println("Sent message: " + Arrays.toString(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }
}
