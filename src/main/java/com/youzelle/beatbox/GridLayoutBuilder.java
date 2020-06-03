package com.youzelle.beatbox;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GridLayoutBuilder {

  GridLayout grid;
  JPanel mainPanel;
  JPanel background;
  ArrayList<JCheckBox> checkboxList;

  public GridLayoutBuilder(JPanel background, ArrayList<JCheckBox> checkboxList) {
    this.background = background;
    this.checkboxList = checkboxList;
  }

  public void createGrid() {
    grid = new GridLayout(16, 16);
    grid.setVgap(1);
    grid.setHgap(2);
    mainPanel = new JPanel(grid);
    background.add(BorderLayout.CENTER, mainPanel);

    for (int i = 0; i < 256; i++) {
      JCheckBox c = new JCheckBox();
      c.setSelected(false);
      checkboxList.add(c);
      mainPanel.add(c);
    }
  }

}

