package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Component
public class ControlButtons {

  private MidiEventsService midiEventsService = new MidiEventsService();

  Box buttonBox = new Box(BoxLayout.Y_AXIS);
  Instruments instruments;
  ArrayList<JCheckBox> checkboxList;

  public ControlButtons(Instruments instruments, ArrayList<JCheckBox> checkboxList) {
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
    System.out.println("MIDISERVICE---------------" + midiEventsService);
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
      BuildTrack buildTrack = new BuildTrack(midiEventsService, instruments, checkboxList);
      buildTrack.create();
    }
  }

  private class MyStopListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      midiEventsService.getBBSequencer().stop();
    }
  }

  private class MyUpTempoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      float tempoFactor = midiEventsService.getBBSequencer().getTempoFactor();
      midiEventsService.getBBSequencer().setTempoFactor((float) (tempoFactor * 1.03));
    }
  }

  private class MyDownTempoListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      float tempoFactor = midiEventsService.getBBSequencer().getTempoFactor();
      midiEventsService.getBBSequencer().setTempoFactor((float) (tempoFactor * 0.97));
    }
  }
}
