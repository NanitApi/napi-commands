package napi.commands.test;

import napi.commands.Command;
import napi.commands.manager.CommandManager;
import napi.commands.test.impl.TestCommandManager;

import java.awt.*;

public class TestCommand {

    public static void main(String[] a){
        CommandManager manager = new TestCommandManager();

        Command child1 = Command.builder()
                .executor((sender, args)->{
                    Window.Type type = args.<Window.Type>get("type").get();
                    System.out.println("This is child command 1");
                })
                .build();

        Command child2 = Command.builder()
                .executor((sender, args)->{
                    System.out.println("This is child command 2");
                })
                .build();

        Command command = Command.builder()
                .child(child1, "sub")
                .child(child2, "sub2")
                .executor((sender, args) -> {})
                .build();

        manager.register(command, "test", "t");
    }

}