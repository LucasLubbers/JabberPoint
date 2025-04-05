package com.nhlstenden.command;

import com.nhlstenden.Presentation;
import com.nhlstenden.slide_viewer.SlideViewerFrame;
import com.nhlstenden.memento.CareTaker;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CommandRegistry {
    private KeyCommandMapper keyCommandMapper;

    public CommandRegistry(Presentation presentation, Frame parent, SlideViewerFrame slideViewerFrame, CareTaker careTaker) {
        keyCommandMapper = new KeyCommandMapper();
        initializeCommands(presentation, parent, slideViewerFrame, careTaker);
    }

    private void initializeCommands(Presentation presentation, Frame parent, SlideViewerFrame slideViewerFrame, CareTaker careTaker) {
        keyCommandMapper.addCommand(KeyEvent.VK_PAGE_DOWN, presentation::nextSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_DOWN, presentation::nextSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_ENTER, presentation::nextSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_PLUS, presentation::nextSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_PAGE_UP, presentation::prevSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_UP, presentation::prevSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_MINUS, presentation::prevSlide);
        keyCommandMapper.addCommand(KeyEvent.VK_Q, () -> System.exit(0));
        keyCommandMapper.addCommand(KeyEvent.VK_O, new OpenFileCommand(presentation, parent, slideViewerFrame));
        keyCommandMapper.addCommand(KeyEvent.VK_N, new NewPresentationCommand(presentation, parent, slideViewerFrame));
        keyCommandMapper.addCommand(KeyEvent.VK_S, new SaveFileCommand(presentation, parent));
        keyCommandMapper.addCommand(KeyEvent.VK_T, new AddTextItemCommand(presentation, parent));
        keyCommandMapper.addCommand(KeyEvent.VK_I, new AddBitmapItemCommand(presentation, parent));
        keyCommandMapper.addCommand(KeyEvent.VK_F5, new SaveStateCommand(presentation, parent, careTaker));
        keyCommandMapper.addCommand(KeyEvent.VK_F9, new RestoreStateCommand(presentation, parent, careTaker));
        keyCommandMapper.addCommand(KeyEvent.VK_D, new DeleteSlideCommand(presentation, parent));
    }

    public KeyCommandMapper getKeyCommandMapper() {
        return keyCommandMapper;
    }
}