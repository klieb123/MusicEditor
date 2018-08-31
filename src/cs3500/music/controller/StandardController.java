package cs3500.music.controller;

import java.util.List;

import cs3500.music.model.MusicOperations;
import cs3500.music.model.Note;
import cs3500.music.model.SongPart;
import cs3500.music.view.SongView;

/**
 * Standard implementation of MusicController.
 */
public class StandardController implements MusicController {

  //current beat
  private int curBeat;
  // model
  private MusicOperations model;
  // view
  // this can also be a "stacked" view i.e. midi and gui view in one view
  private SongView view;

  /**
   * Consctructor for Standard Controller.
   * @param model model to use with controller.
   * @param view view to use with controller.
   */
  public StandardController(MusicOperations model, SongView view) {
    this.curBeat = 0;
    this.model = model;
    this.view = view;
  }


  /**
   * Add note to controller model.
   * @param note note to add to model.
   * @param startBeat beat where note to be added starts.
   * @param endBeat beat where note to be added ends.
   * @param volume volume of note.
   * @param instrument instrument that plays note.
   */
  @Override
  public void addNote(Note note, int startBeat, int endBeat, int volume, int instrument) {
    model.addNote(new SongPart(note, startBeat, endBeat - startBeat,volume, instrument));
    // need method to update note set at beat?
  }

  /**
   * Remove note from controller model.
   * @param note note to be removed.
   * @param beat beat where note to be removed occurs.
   */
  @Override
  public void removeNote(Note note, int beat) {
    model.removeNote(note, beat);
  }

  /**
   * Fetch tempo from model.
   * @return tempo as integer from model.
   */
  @Override
  public int getTempo() {
    return model.getTempo();
  }

  /**
   * Fetch time signature (measure length) from model.
   * @return time signature as integer from model.
   */
  @Override
  public int getTimeSig() {
    return model.getTimeSig();
  }

  /**
   * Fetch length of entire song (in beats).
   * @return length of entire song in beats as integer.
   */
  @Override
  public int getLength() {
    return model.getLength();
  }

  /**
   * Fetch lowest note from model.
   * @return lowest note from model as Note (not songPart).
   */
  @Override
  public Note minNote() {
    return model.getMinNote();
  }

  /**
   * Fetch highest note from model.
   * @return highest note from model as Note (not songPart).
   */
  @Override
  public Note maxNote() {
    return model.getMaxNote();
  }


  /**
   * Fetch list of all SongParts from song.
   * @return all songParts present in song.
   */
  @Override
  public List<SongPart> getNotes() {
    return model.getNotes();
  }

  /**
   * Render views provided to controller.
   * @throws InterruptedException if beat falls out of sync.
   */
  @Override
  public void render() throws InterruptedException {
    // walk through notes and sync with length of generated song
    view.initialize(this);
    while (curBeat < model.getLength()) {
      curBeat++;
      view.drawBeat(this.curBeat - 1);
    }
  }
}
