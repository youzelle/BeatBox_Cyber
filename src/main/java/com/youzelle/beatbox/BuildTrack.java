package com.youzelle.beatbox;

import com.youzelle.beatbox.instruments.Instruments;
import org.springframework.beans.factory.annotation.Autowired;
import com.youzelle.beatbox.MidiEventsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sound.midi.*;
import javax.swing.*;
import java.util.ArrayList;

@Component
public class BuildTrack {

  MidiEventsService midiEventsService;

  private Instruments instruments;
  private ArrayList<JCheckBox> checkboxList;

//  @Autowired
  public BuildTrack(MidiEventsService midiEventsService, Instruments instruments, ArrayList<JCheckBox> checkboxList) {
    this.instruments = instruments;
    this.checkboxList = checkboxList;
    this.midiEventsService = midiEventsService;
  }

  public void create() {
    int[] trackList;
    System.out.println("MIDISERVICE ----------------" + midiEventsService);
    midiEventsService.getBBSequence().deleteTrack(midiEventsService.getBBTrack());
    midiEventsService.setBBTrack(midiEventsService.getBBSequence().createTrack());

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
      midiEventsService.getBBTrack().add(makeEvent(176, 1, 127, 0, 16));

      try {
        midiEventsService.getBBSequencer().setSequence(midiEventsService.getBBSequence());
        midiEventsService.getBBSequencer().setLoopCount(midiEventsService.getBBSequencer().LOOP_CONTINUOUSLY);
        midiEventsService.getBBSequencer().start();
        midiEventsService.getBBSequencer().setTempoInBPM(120);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void makeTracks(int[] list) {
    for (int i = 0; i < 16; i++) {
      int key = list[i];
      if (key !=0 ) {
        midiEventsService.getBBTrack().add(makeEvent(144, 9, key, 100, i));
        midiEventsService.getBBTrack().add(makeEvent(128, 9, key, 100, i + 1));
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
