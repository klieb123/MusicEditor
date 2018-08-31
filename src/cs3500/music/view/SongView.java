package cs3500.music.view;

import cs3500.music.controller.MusicController;

/**
 * Interface for classes capable of displaying a song.
 */
public interface SongView {
  /**
   * Initializes the view to display a piece of music.
   * @param c the controller calling the view's methods
   */
  void initialize(MusicController c);

  /**
   * Displays/draws/plays the song.
   * @param beat the beat number
   */
  void drawBeat(int beat) throws InterruptedException;
}
