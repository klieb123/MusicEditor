package cs3500.music.model;

import java.util.List;

/**
 * Interface to represent operations in Music Model.
 */
public interface MusicOperations {

  /**
   * Retrieve time signature (length of measure).
   * @return time signature.
   */
  int getTimeSig();

  /**
   * Length of song.
   * @return number of beats in song.
   */
  int getLength();

  /**
   * Retrieve tempo of current song.
   * @return int of tempo from this song.
   */
  int getTempo();

  /**
   * Remove song part from song.
   * @param note note to be removed.
   * @param beat beat where note(s) reside.
   */
  void removeNote(Note note, int beat);

  /**
   * Add a note to song.
   * @param p songPart to add (represents note and position).
   */
  void addNote(SongPart p);

  /**
   * Get lowest note in note list.
   * @return lowest note.
   */
  Note getMinNote();

  /**
   * Get highest note in note list.
   * @return highest note.
   */
  Note getMaxNote();

  /**
   * Get all notes in song.
   * @return list of all notes in song.
   */
  List<SongPart> getNotes();

  /**
   * Get all notes of specific note.
   * @param aNote specific note to retrieve instances of.
   * @return list of specified note instances.
   */
  List<SongPart> getNoteInstance(Note aNote);

  /**
   * Get notes in song at specific beat.
   * @param beat beat to fetch notes from.
   * @return list of notes at specified beat.
   */
  List<SongPart> getNotesAtBeat(int beat);
}
