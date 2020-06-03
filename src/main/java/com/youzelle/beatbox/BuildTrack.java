package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;

import javax.sound.midi.*;
import javax.swing.*;
import java.util.ArrayList;

public class BuildTrack {

  private Sequence sequence;
  private Sequencer sequencer;
  private Track track;
  private Instruments instruments;
  private ArrayList<JCheckBox> checkboxList;

  public BuildTrack(Sequencer sequencer, Sequence sequence, Track track, Instruments instruments, ArrayList<JCheckBox> checkboxList) {
    this.sequence = sequence;
    this.track = track;
    this.instruments = instruments;
    this.checkboxList = checkboxList;
    this.sequencer = sequencer;
  }


  public void create() {
    int[] trackList;

    sequence.deleteTrack(track);
    track = sequence.createTrack();
    System.out.println("createTrack");

    for (int i = 0; i < 16; i++) {
      trackList = new int[16];
      int key = instruments.getInstruments().get(i);

      for (int j = 0; j < 16; j++) {
        JCheckBox jc = checkboxList.get(j + 16*i);
        if (jc.isSelected()) {
          trackList[j] = key;
        } else {
          trackList[j] = 0;
        }
      }

      makeTracks(trackList);
      track.add(makeEvent(176, 1, 127, 0, 16));

      try {
        sequencer.setSequence(sequence);
        sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
        sequencer.start();
        sequencer.setTempoInBPM(120);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void makeTracks(int[] list) {
    for (int i = 0; i < 16; i++) {
      int key = list[i];
      if (key !=0 ) {
        track.add(makeEvent(144, 9, key, 100, i));
        track.add(makeEvent(128, 9, key, 100, i + 1));
      }
    }
  }

  private MidiEvent makeEvent(int command, int channel, int key, int level, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage a = new ShortMessage();
      a.setMessage(command, channel, key, level);
      event = new MidiEvent(a, tick);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return event;
  }
}
