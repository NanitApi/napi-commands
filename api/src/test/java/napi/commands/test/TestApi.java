package napi.commands.test;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class TestApi {

    @Test
    public void testArguments(){
        List<String> list = Arrays.asList("arg1", "arg2", "arg3", "arg4", "arg5");
        CommandArguments args = new CommandArguments(list);

        System.out.println(args);

        try {
            args.next();
            args.next();
            args.next();
            args.next();
            System.out.println("Next is " + args.peek());
        } catch (ArgumentParseException e){
            e.printStackTrace();
        }

        args.remove(2);

        System.out.println(args);
        System.out.println("Next is " + args.peek());
    }

    @Test
    public void testArgsParsing(){
        String line = "test sub vasya ";
        List<String> args = parse(line);

        System.out.println(args);
    }

    public List<String> parse(String arguments) {
        List<String> ret = new ArrayList<>();
        int lastIndex = 0;
        int spaceIndex;

        while ((spaceIndex = arguments.indexOf(" ")) != -1) {
            if (spaceIndex != 0) {
                ret.add(arguments.substring(0, spaceIndex));
                arguments = arguments.substring(spaceIndex);
            } else {
                arguments = arguments.substring(1);
            }
            lastIndex += spaceIndex + 1;
        }

        ret.add(arguments);
        return ret;
    }

    @Test
    public void test1(){
        TreeMap<String, String> map = new TreeMap<>();

        map.put("name", "vasya");
        System.out.println(map.get("age"));
    }
}
