package com.nhlstenden.command;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
  private KeyCommandMapper keyCommandMapper;

  public KeyController(KeyCommandMapper keyCommandMapper) {
    this.keyCommandMapper = keyCommandMapper;
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    Command command = keyCommandMapper.getCommand(keyEvent.getKeyCode());
    if (command != null) {
      command.execute();
    }
  }
}