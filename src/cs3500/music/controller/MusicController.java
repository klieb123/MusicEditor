package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.Note;
import cs3500.music.model.SongPart;

/**
 * Interface that represents functionality necessary for music controllers.
 */
public interface MusicController {
  // contains model and view

  /**
   * Add note to controller model.
   * @param note note to be added to controller model.
   * @param startBeat start beat of note to be added.
   * @param endBeat end beat of  note to be added.
   * @param volume volume of note to be added.
   * @param instrument instrument of note to be added.
   */
  void addNote(Note note,int startBeat, int endBeat, int volume, int instrument);

  /**
   * Remove note from controller model.
   * @param note note to be removed.
   * @param beat beat where note to be removed occurs.
   */
  void removeNote(Note note, int beat);

  /**
   * Fetch tempo from controller model.
   * @return tempo from controller model as integer.
   */
  int getTempo();

  /**
   * Fetch time signature (measure length) from controller model.
   * @return time signature as integer from model.
   */
  int getTimeSig();

  /**
   * Fetch length of song from model.
   * @return length of song in beats from model.
   */
  int getLength();

  /**
   * Fetch lowest note from controller model.
   * @return lowest note (not songpart) from model.
   */
  Note minNote();

  /**
   * Fetch highest note from controller model.
   * @return highest note (not songpart) from model.
   */
  Note maxNote();

  /**
   * Fetch all song parts from model.
   * @return list of song parts from controller model.
   */
  List<SongPart> getNotes();

  /**
   * Render views provided to controller.
   * @throws InterruptedException if beat gets out of sync.
   */
  void render() throws InterruptedException;
}
