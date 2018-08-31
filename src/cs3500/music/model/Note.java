package cs3500.music.model;

import java.util.Objects;

/**
 * Class to represent a note.
 */
public class Note implements Comparable<Note> {
  // components of a note
  private final Pitch pitch;
  private final int octave;

  /**
   * Represents a musical note.
   * @param pitch tone that note produces.
   * @param octave octave tone resides in.
   */
  public Note(Pitch pitch, int octave) {
    // Pitch sanity check
    if (pitch == null) {
      throw new IllegalArgumentException("Input pitch is null!");
    }
    // Octave sanity check
    if (octave < 0 || octave >= 100) {
      throw new IllegalArgumentException("Input octave must be > 0");
    }

    this.pitch = pitch;
    this.octave = octave;
  }


  /**
   * More specific contructor for note, provides options for volume and instrument.
   * @param pitch pitch of note.
   * @param octave octave of note.
   * @param volume volume of note when played.
   * @param instrument instrument of note when played.
   */
  public Note(Pitch pitch, int octave, int volume, int instrument) {
    if (pitch == null) {
      throw new IllegalArgumentException("Pitch cannot be null.");
    }
    if (octave < -2) {
      throw new IllegalArgumentException("Octave cannot be negative.");
    }
    if (volume < 0 || volume > 127) {
      throw new IllegalArgumentException("Volume outside range [0, 127]");
    }
    if (instrument < 1 || instrument > 16) {
      throw new IllegalArgumentException("Volume outside range [1, 16]");
    }
    this.pitch = pitch;
    this.octave = octave;
  }

  // override toString logic
  /**
   * New toString method for note.
   * @return Pitch string + octave of note.
   */
  @Override
  public String toString() {
    return this.pitch.toString() + this.octave;
  }

  // override equality / comparison logic
  /**
   * Equals operator for Note class.
   * @param other Note to compare current note to.
   * @return Whether the notes are equal.
   */
  @Override
  public boolean equals(Object other) {
    return (other instanceof Note)
            && (this.pitch == ((Note) other).pitch)
            && (this.octave == ((Note) other).octave);
  }

  /**
   * Hashcode for equals operator
   * @return hash code for note comparison.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.pitch, this.octave);
  }

  // Logic for note to note comparison
  /**
   * New compareTo method for notes class.
   * @param other  Note to compare to this note.
   * @return Ordinal difference between this note and other note.
   */
  @Override
  public int compareTo(Note other) {
    return this.toInt() - other.toInt();
  }

  /**
   * Convert note into integer representaion.
   * @return integer that represents a note (pitch & octave).
   */
  public int toInt() {
    return (this.octave + 1) * 12 + this.pitch.ordinal();
  }

  /**
   * Convert integer into note class.
   * @param i integer representation of note (pitch & octave).
   * @return Note representation from integer input.
   */
  public static Note toNote(int i) {
    return new Note(Pitch.fetchOrdinal(i % 12), i / 12 - 1);
  }

  /**
   * Specialty toNote that adds options for volume and instrument.
   * @param i integer representation of note (pitch & octave).
   * @param vol volume for generated note.
   * @param instr instrument for generated note.
   * @return Note representation (with volume and instrument) from integer input.
   */
  public static Note toNote(int i, int vol, int instr) {
    return new Note(Pitch.fetchOrdinal(i % 12), i / 12 - 1, vol, instr);
  }

  /**
   * Fetch pitch of this note.
   * @return pitch of note.
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * Fetch octave of this note.
   * @return octave of note.
   */
  public int getOctave() {
    return this.octave;
  }
}
