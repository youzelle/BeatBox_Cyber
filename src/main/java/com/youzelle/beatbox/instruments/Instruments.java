package com.youzelle.beatbox.instruments;

import java.util.ArrayList;

public class Instruments {

  private ArrayList<String> instrumentNames = new ArrayList<String>();
  private ArrayList<Integer> instruments = new ArrayList<Integer>();

  public ArrayList<String> getInstrumentNames() {
    return instrumentNames;
  }

  public ArrayList<Integer> getInstruments() {
    return instruments;
  }

  public void setDefaultInstrumentNames() {
    instrumentNames.add("Bass Drum");
    instrumentNames.add("Closed Hi-Hat");
    instrumentNames.add("Open Hi-Hat");
    instrumentNames.add("Acoustic Snare");
    instrumentNames.add("Crash Symbol");
    instrumentNames.add("Hand Clap");
    instrumentNames.add("High Tom");
    instrumentNames.add("Hi Bongo");
    instrumentNames.add("Maracas");
    instrumentNames.add("Whistle");
    instrumentNames.add("Low Bongo");
    instrumentNames.add("Cowbell");
    instrumentNames.add("Vibraslap");
    instrumentNames.add("Low-mid Tom");
    instrumentNames.add("High Agogo");
    instrumentNames.add("Open High Congo");
  }

  public void setDefaultInstruments() {
    instruments.add(35);
    instruments.add(42);
    instruments.add(46);
    instruments.add(38);
    instruments.add(49);
    instruments.add(39);
    instruments.add(50);
    instruments.add(60);
    instruments.add(70);
    instruments.add(72);
    instruments.add(64);
    instruments.add(56);
    instruments.add(58);
    instruments.add(47);
    instruments.add(67);
    instruments.add(63);
  }
}
