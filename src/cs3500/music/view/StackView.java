package cs3500.music.view;

import javax.sound.midi.MidiUnavailableException;

import cs3500.music.controller.MusicController;


/**
 * Class to manage stacking views for controller.
 */
public class StackView implements SongView {
  private SongView view1;
  private SongView view2;

  /**
   * Make default StackView.
   */
  public StackView() throws MidiUnavailableException {
    this.view1 = new MidiViewImpl();
    this.view2 = new GuiViewFrame();
  }

  /**
   * Initialize data in both views with unified controller.
   * @param c controller to intialize views with.
   */
  @Override
  public void initialize(MusicController c) {
    this.view1.initialize(c);
    this.view2.initialize(c);
  }

  /**
   * Draw beat for both views.
   * @param beat beat to draw for both views.
   * @throws InterruptedException throw if beat is out of sync.
   */
  @Override
  public void drawBeat(int beat) throws InterruptedException {
    this.view1.drawBeat(beat);
    this.view2.drawBeat(beat);
  }
}

