package com.youzelle.beatbox;

import org.springframework.stereotype.Service;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Track;

@Service
public class MidiEventsService {

  private Sequencer sequencer;
  private Sequence sequence;
  private Track track;

  public MidiEventsService() {
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

  public Sequencer getBBSequencer() {
    return sequencer;
  }

  public Sequence getBBSequence() {
    return sequence;
  }

  public Track getBBTrack() {
    return track;
  }

  public void setBBTrack(Track track) { this.track = track; }

  public void setSequencer(Sequencer sequencer) { this.sequencer = sequencer; }

  public void setSequence(Sequence sequence ) { this.sequence = sequence; }

}
