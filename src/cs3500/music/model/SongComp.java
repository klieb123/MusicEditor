package cs3500.music.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.ArrayList;

import cs3500.music.util.CompositionBuilder;


/**
 * Class to represent a song with set of notes.
 */
public class SongComp implements MusicOperations {

  // init variables
  private final List<SongPart> parts;
  private int tempo;
  private int timeSig;

  /**
   * Represents a song with a set of notes.
   * @param parts notes that make up the song composition.
   * @param tempo tempo of the song composition.
   * @param timeSig time signature (measure length) of the song composition.
   */
  public SongComp(List<SongPart> parts, int tempo, int timeSig) {
    // sanity checks for input
    if (tempo < 1) {
      throw new IllegalArgumentException("tempo must be > 0");
    }
    if (timeSig < 1) {
      throw new IllegalArgumentException("time signature must be > 0");
    }
    this.parts = parts;
    this.tempo = tempo;
    this.timeSig = timeSig;
  }

  /**
   * Fetch tempo of song composition.
   * @return tempo of song composition.
   */
  @Override
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Fetch time signature (measure length) of song composition.
   * @return time signature of song composition.
   */
  @Override
  public int getTimeSig() {
    return this.timeSig;
  }

  /**
   * Fetch length of song composition.
   * @return integer length of song composition (largest start beat + duration).
   */
  @Override
  public int getLength() {
    // init map
    Map<SongPart, Integer> map = new HashMap<SongPart, Integer>();
    // populate map
    for (SongPart i : parts) {
      map.put(i, i.getStartBeat());
    }
    SongPart key = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

    return key.getStartBeat() + key.getDuration();
  }

  /**
   * Fetch list of all notes present in song composition.
   * @return list of notes (song part) in song composition.
   */
  @Override
  public List<SongPart> getNotes() {
    return this.parts.stream().collect(Collectors.toList());
  }

  /**
   * Get lowest note in song composition.
   * @return lowest note in song composition.
   */
  @Override
  public Note getMinNote() {
    // init map
    Map<Note, Integer> map = new HashMap<Note, Integer>();
    // populate map
    for (SongPart i : parts) {
      map.put(i.getNote(), i.getNote().toInt());
    }
    Note key = Collections.min(map.entrySet(), Map.Entry.comparingByValue()).getKey();

    return key;
  }

  /**
   * Get highest note in song composition.
   * @return highest note in song composition.
   */
  @Override
  public Note getMaxNote() {
    Map<Note, Integer> map = new HashMap<Note, Integer>();
    // populate map
    for (SongPart i : parts) {
      map.put(i.getNote(),i.getNote().toInt());
    }
    Note key = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

    return key;
  }

  /**
   * Get notes at given beat in song composition.
   * @param beat beat to fetch notes from.
   * @return list of notes (song part) present at given beat.
   */
  @Override
  public List<SongPart> getNotesAtBeat(int beat) {
    return this.parts.stream()
            .filter((SongPart sp) ->
                    (sp.getStartBeat() + sp.getDuration() > beat &&
                            sp.getStartBeat() <= beat))
            .collect(Collectors.toList());
  }

  /**
   * Get all instances of given note.
   * @param note to retrieve instances of.
   * @return list of song part where given note is represented.
   */
  @Override
  public List<SongPart> getNoteInstance(Note note) {
    return this.parts.stream()
            .filter((SongPart p) -> p.getNote()
                    .equals(note))
            .collect(Collectors.toList());
  }

  /**
   * Add note with time info (song part) to song composition.
   * @param p songPart to add (represents note and position).
   */
  @Override
  public void addNote(SongPart p) {
    this.parts.add(p);
  }

  /**
   * Remove note with time info (song part) from song composition.
   * @param p songPart to remove (represents note and position).
   */
  public void removeNote(SongPart p) {
    parts.remove(p);
  }

  // remove a songpart with note and beat
  @Override
  public void removeNote(Note n, int beat) {
    this.parts.stream().filter(p ->
            (p.getStartBeat() == beat) &&
                    (p.getNote() == n))
            .forEach(this.parts::remove);
  }

  /**
   * Return current notes in song composition in string form.
   * @return string with all notes in song composition.
   */
  @Override
  public String toString() {
    String res = "";
    for (SongPart sp: parts) {
      res += sp.getNote().toString() + " ";
    }
    return res;
  }

  /**
   * Song data in string form.
   * @return song data in formatted string form.
   */
  public String getSongData() {
    String res = "";
    String tab = "   ";

    int beatRange = this.getLength();
    int minNote = this.getMinNote().toInt();
    int maxNote = this.getMaxNote().toInt();

    List<Integer> noteRange = new ArrayList<>();

    for (int i = minNote; i < maxNote + 1; i++) {
      noteRange.add(i);
    }

    // render first row
    res += tab;
    for (Integer i: noteRange) {
      res += Note.toNote(i) + tab;
    }
    res += "\n";

    // render beat rows
    for (int i = 0; i < beatRange + 1; i++) {
      res += i;
      // render notes
      List<SongPart> notesAtBeat = getNotesAtBeat(i);

      List<Integer> activeNotes = new ArrayList<>();
      for (SongPart sp: notesAtBeat) {
        activeNotes.add(sp.getNote().toInt());
      }
      res += "   ";
      for (Integer j: noteRange) {
        if (activeNotes.contains(j)) {
          // if note exists
          res += "|    ";
        } else {
          //res += "    " + " " * j.toString().length();
          res += "  ";
          for (int space = 0; space < j.toString().length() + 1; space++) {
            res += " ";
          }
        }
      }
      res += "\n";
    }
    return res;
  }

  /**
   * Builder for SongComp class so the model is compatible with CompositionBuilder.
   */
  public static class SongBuilder implements CompositionBuilder<MusicOperations> {
    private int tempo;
    private int measureLength;
    private List<SongPart> notes;

    /**
     * Makes a new SongBuilder.
     */
    public SongBuilder() {
      // default tempo
      this.tempo = 200000;
      // default measure length
      this.measureLength = 4;
      this.notes = new ArrayList<>();
    }

    /**
     * BUild a song with notes provided.
     * @return song object created by builder.
     */
    @Override
    public MusicOperations build() {
      return new SongComp(this.notes, this.tempo, this.measureLength);
    }

    /**
     * Set tempo of given CompositionBUilder.
     * @param tempo speed of song playback.
     * @return SongBuilder with new tempo.
     */
    @Override
    public CompositionBuilder<MusicOperations> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    /**
     * Add note to Composition.
     * @param start Start beat of note.
     * @param end End beat of note.
     * @param instrument Midi instrument id.
     * @param pitch Note pitch from enum.
     * @param volume volume for note playback.
     * @return new composition builder with new note added.
     */
    @Override
    public CompositionBuilder<MusicOperations> addNote(int start,
                                                       int end,
                                                       int instrument,
                                                       int pitch,
                                                       int volume) {
      this.notes.add(new SongPart(Note.toNote(pitch,
              volume,
              instrument),
              start,
              end - start,
              volume,
              instrument));
      return this;
    }

    /**
     * Set measure length based on time signature.
     * @param len length of measures
     * @return new songbuilder object.
     */
    public SongBuilder measureLength(int len) {
      this.measureLength = len;
      return this;
    }
  }
}
