package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;

import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ControlButtons {


  Box buttonBox = new Box(BoxLayout.Y_AXIS);
  Sequencer sequencer;
  Sequence sequence;
  Track track;
  Instruments instruments;
  ArrayList<JCheckBox> checkboxList;

  public ControlButtons(Sequencer sequencer, Sequence sequence, Track track, Instruments instruments, ArrayList<JCheckBox> checkboxList) {
    this.sequencer = sequencer;
    this.sequence = sequence;
    this.track = track;
    this.instruments = instruments;
    this.checkboxList = checkboxList;
  }

  public Box getButtonBox() {
    return buttonBox;
  }

  public void setButtonBox() {
    buttonBox.add(start());
    buttonBox.add(stop());
    buttonBox.add(upTempo());
    buttonBox.add(downTempo());
  }

  public JButton start() {
    JButton start = new JButton("start");
    start.addActionListener(new MyStartListener());
    return start;
  }

  public JButton stop() {
    JButton stop = new JButton("stop");
    stop.addActionListener(new MyStopListener());
    return stop;
  }

  public JButton upTempo() {
    JButton upTempo = new JButton("Tempo Up");
    upTempo.addActionListener(new MyUpTempoListener());
    return upTempo;
  }


  public JButton downTempo() {
    JButton downTempo = new JButton("Tempo Down");
    downTempo.addActionListener(new MyDownTempoListener());
    return downTempo;
  }

  private class MyStartListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      BuildTrack buildTrack = new BuildTrack(sequencer, sequence, track, instruments, checkboxList);
      buildTrack.create();
    }
  }

  private class MyStopListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      sequencer.stop();
    }
  }

  private class MyUpTempoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      float tempoFactor = sequencer.getTempoFactor();
      sequencer.setTempoFactor((float) (tempoFactor * 1.03));
    }
  }

  private class MyDownTempoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      float tempoFactor = sequencer.getTempoFactor();
      sequencer.setTempoFactor((float) (tempoFactor * 0.97));
    }
  }
}
