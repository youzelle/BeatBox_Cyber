package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;

import javax.swing.*;
import java.awt.*;

public class InstrumentButtons {

  private Box nameBox = new Box(BoxLayout.Y_AXIS);
  private Instruments instruments = new Instruments();

  public Box getNameBox() {
    return nameBox;
  }

  public void addLabels() {
    instruments.setDefaultInstrumentNames();
    for (int i = 0; i < 16; i++) {
      nameBox.add(new Label(instruments.getInstrumentNames().get(i)));
    }
  }
}
