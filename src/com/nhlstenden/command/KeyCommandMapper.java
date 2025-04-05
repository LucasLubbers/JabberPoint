package com.nhlstenden.command;


import java.util.HashMap;
import java.util.Map;

public class KeyCommandMapper {
    private Map<Integer, Command> keyCommandMap;

    public KeyCommandMapper() {
        keyCommandMap = new HashMap<>();
    }

    public void addCommand(int keyCode, Command command) {
        keyCommandMap.put(keyCode, command);
    }

    public Command getCommand(int keyCode) {
        return keyCommandMap.get(keyCode);
    }
}