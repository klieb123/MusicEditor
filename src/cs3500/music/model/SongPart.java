package cs3500.music.model;

import java.util.Objects;

/**
 * Represents a note with timing and volume / instrument info in SongComp.
 */
public class SongPart {

  // song part note
  private Note note;

  // song part start beat
  // previously a part of note, more modular now
  private int startBeat;

  // song part duration
  // previously a part of note, more modular now
  private int duration;

  // song part volume
  private int volume;

  // song part instrument
  private int instrument;

  /**
   * Represents part of a song with timing data.
   * @param note musical note part represents.
   * @param startBeat beat where tone starts.
   * @param duration number of beats tone is sustained for.
   */
  public SongPart(Note note, int startBeat, int duration, int volume, int instrument) {
    if (note == null) {
      throw new IllegalArgumentException("Input note not found");
    }
    if (startBeat < 0 || duration < 0) {
      throw new IllegalArgumentException("startBeat and duration must be > 0");
    }

    this.note = note;
    this.startBeat = startBeat;
    this.duration = duration;
    this.volume = volume;
    this.instrument = instrument;
  }


  /**
   * Fetch note for songpart.
   * @return note represented by songpart.
   */
  public Note getNote() {
    return this.note;
  }

  /**
   * Get start beat from songpart.
   * @return start beat from songpart.
   */
  public int getStartBeat() {
    return this.startBeat;
  }

  /**
   * Get duration from song part.
   * @return duration from songpart.
   */
  public int getDuration() {
    return this.duration;
  }

  // query note components

  /**
   * Get note pitch.
   * @return pitch of note represented by song part.
   */
  public Pitch getNotePitch() {
    return this.note.getPitch();
  }

  /**
   * Get volume from song part.
   * @return volumn from song part.
   */
  public int getVolume() {
    return this.volume;
  }

  /**
   * Get the instrument from song part.
   * @return instrument from song part.
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Get note octave.
   * @return octave of note represented by song part.
   */
  public int getNoteOctave() {
    return this.note.getOctave();
  }

  /**
   * Determine if given beat is the start of note.
   * @param beat beat to compare.
   * @return Whether given beat is the beginning of current note.
   */
  public boolean atStart(int beat) {
    return this.startBeat == beat;
  }

  /**
   * Determine if beat is at the end of note.
   * @param beat beat to compare to.
   * @return Wheter given beat is at the end of current note.
   */
  public boolean atEnd(int beat) {
    return beat == this.startBeat + this.duration ;
  }

  /**
   * Determine if given beat is in the middle of note.
   * @param beat beat to compare.
   * @return Whether given beat is in the middle of current note.
   */
  public boolean atMiddle(int beat) {
    return beat > this.startBeat && beat < this.startBeat + this.duration ;
  }

  /**
   * Custom equals method for SongPart.
   * @param other SongPart to compare to.
   * @return whether other songPart is identical to this songPart.
   */
  @Override
  public boolean equals(Object other) {
    return (other instanceof SongPart)
            && this.note.equals(((SongPart) other).note)
            && (this.startBeat == ((SongPart) other).startBeat)
            && (this.duration == ((SongPart) other).duration);
  }

  /**
   * Custom hashcode method for songPart so equals for songPart functions correctly.
   * @return hashcode for songPart.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.note, this.startBeat, this.duration);
  }

}
