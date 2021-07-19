package napi.commands.test.impl;

import napi.commands.Command;
import napi.commands.manager.AbstractCommandManager;
import napi.commands.parsed.CommandSender;

import java.util.Scanner;

public class TestCommandManager extends AbstractCommandManager {

    public TestCommandManager(){
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()){
                String next = scanner.nextLine();
                CommandSender sender = new TestSender();

                System.out.println("Processing line '" + next + "'...\n");
                process(sender, next);
            }
        }).start();
    }

    @Override
    public void register(Command command, String... aliases) {
        addCommand(command, aliases);
    }

}
