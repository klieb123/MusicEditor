package cs3500.music.model;

/**
 * Enum to represent 13 possible pitches that occupy a given octave.
 */
public enum Pitch {
  /**
   * C.
   */
  C,
  /**
   * C Sharp.
   */
  C_,
  /**
   * D.
   */
  D,
  /**
   * D Sharp.
   */
  D_,
  /**
   * E.
   */
  E,
  /**
   * F.
   */
  F,
  /**
   * F Sharp.
   */
  F_,
  /**
   * G.
   */
  G,
  /**
   * G Sharp.
   */
  G_,
  /**
   * A.
   */
  A,
  /**
   * A Sharp.
   */
  A_,
  /**
   * B.
   */
  B;

  /**
   * Fetch ordinal values of given pitch in enum.
   * @param i integer representation of pitch within enum.
   * @return ordinal pitch value for pitch.
   */
  public static Pitch fetchOrdinal(int i) {
    return Pitch.values()[i];
  }


  /**
   * New toString method for Note class.
   * @return String representing note attributes.
   */
  @Override
  public String toString() {
    switch (this) {
      case C:
        return "C";
      case C_:
        return "C#";
      case D:
        return "D";
      case D_:
        return "D#";
      case E:
        return "E";
      case F:
        return "F";
      case F_:
        return "F#";
      case G:
        return "G";
      case G_:
        return "G#";
      case A:
        return "A";
      case A_:
        return "A#";
      case B:
        return "B";
      default:
        throw new RuntimeException("Note toString failure");
    }
  }
}
