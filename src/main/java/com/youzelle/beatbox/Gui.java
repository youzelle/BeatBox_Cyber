package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;

import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Gui {

  JPanel mainPanel;
  JPanel background;
  JFrame theFrame = new JFrame("Cyber Beatbox");
  BorderLayout layout = new BorderLayout();
  Box buttonBox;
  ArrayList<JCheckBox> checkboxList = new ArrayList<>();

  Instruments instruments;
  Sequencer sequencer;
  Sequence sequence;
  Track track;

  public void build() {
    SequenceEvents sequenceEvents = new SequenceEvents();
    sequenceEvents.setUpMidi();
    sequencer = sequenceEvents.getSequencer();
    sequence = sequenceEvents.getSequence();
    track = sequenceEvents.getTrack();

    instruments = new Instruments();
    instruments.setDefaultInstrumentNames();
    instruments.setDefaultInstruments();

    theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    background = new JPanel(layout);
    background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


    ControlButtons controlButtons = new ControlButtons(sequencer, sequence, track, instruments, checkboxList);
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
