package com.youzelle.beatbox;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

public class SequenceEvents {

  Sequencer sequencer;
  Sequence sequence;
  Track track;

  public Sequencer getSequencer() {
    return sequencer;
  }

  public Sequence getSequence() {
    return sequence;
  }

  public Track getTrack() {
    return track;
  }

  public void setUpMidi() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequence = new Sequence(Sequence.PPQ, 4);
      track = sequence.createTrack();
      sequencer.setTempoInBPM(120);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
