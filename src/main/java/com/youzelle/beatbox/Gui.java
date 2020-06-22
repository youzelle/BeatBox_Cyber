package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Component
public class Gui {

  JPanel mainPanel;
  JPanel background;
  JFrame theFrame = new JFrame("Cyber Beatbox");
  BorderLayout layout = new BorderLayout();
  Box buttonBox;
  ArrayList<JCheckBox> checkboxList = new ArrayList<>();

  Instruments instruments;

  public void build() {

    instruments = new Instruments();
    instruments.setDefaultInstrumentNames();
    instruments.setDefaultInstruments();

    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    background = new JPanel(layout);
    background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    ControlButtons controlButtons = new ControlButtons(instruments, checkboxList);
    controlButtons.setButtonBox();
    buttonBox = controlButtons.getButtonBox();

    InstrumentButtons instrumentButtons = new InstrumentButtons();
    instrumentButtons.addLabels();


    background.add(BorderLayout.EAST, controlButtons.getButtonBox());
    background.add(BorderLayout.WEST, instrumentButtons.getNameBox());

    theFrame.getContentPane().add(background);

    GridLayoutBuilder grid = new GridLayoutBuilder(background, checkboxList);
    grid.createGrid();

    theFrame.setBounds(50, 50, 300, 300);
    theFrame.pack();
    theFrame.setVisible(true);
  }

}
