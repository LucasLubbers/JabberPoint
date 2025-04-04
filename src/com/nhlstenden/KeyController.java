package com.nhlstenden;

import com.nhlstenden.command.*;
import com.nhlstenden.memento.CareTaker;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyController extends KeyAdapter {

  private Presentation presentation;
  private Frame parent;
  private CareTaker careTaker;
  private Map<Integer, Command> keyCommandMap;

  public KeyController(Presentation presentation, Frame parent, CareTaker careTaker) {
    this.presentation = presentation;
    this.parent = parent;
    this.careTaker = careTaker;
    initializeKeyCommandMap();
  }

  private void initializeKeyCommandMap() {
    keyCommandMap = new HashMap<>();
    keyCommandMap.put(KeyEvent.VK_PAGE_DOWN, presentation::nextSlide);
    keyCommandMap.put(KeyEvent.VK_DOWN, presentation::nextSlide);
    keyCommandMap.put(KeyEvent.VK_ENTER, presentation::nextSlide);
    keyCommandMap.put(KeyEvent.VK_PLUS, presentation::nextSlide);
    keyCommandMap.put(KeyEvent.VK_PAGE_UP, presentation::prevSlide);
    keyCommandMap.put(KeyEvent.VK_UP, presentation::prevSlide);
    keyCommandMap.put(KeyEvent.VK_MINUS, presentation::prevSlide);
    keyCommandMap.put(KeyEvent.VK_Q, () -> System.exit(0));
    keyCommandMap.put(KeyEvent.VK_O, new OpenFileCommand(presentation, parent));
    keyCommandMap.put(KeyEvent.VK_N, new NewPresentationCommand(presentation, parent));
    keyCommandMap.put(KeyEvent.VK_S, new SaveFileCommand(presentation, parent));
    keyCommandMap.put(KeyEvent.VK_T, new AddTextItemCommand(presentation, parent));
    keyCommandMap.put(KeyEvent.VK_I, new AddBitmapItemCommand(presentation, parent));
    keyCommandMap.put(KeyEvent.VK_F5, new SaveStateCommand(presentation, parent, careTaker));
    keyCommandMap.put(KeyEvent.VK_F9, new RestoreStateCommand(presentation, parent, careTaker));
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    Command command = keyCommandMap.get(keyEvent.getKeyCode());
    if (command != null) {
      command.execute();
    }
  }
}