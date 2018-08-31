package cs3500.music.view;

import javax.sound.midi.MidiUnavailableException;

/**
 * Class to create different types of views via controller.
*/
public final class ViewFactory {

  /**
   * Create view with given viewType string.
   * @param viewType code that corresponds to given view.
   * @return new instance of viewType.
   * @throws MidiUnavailableException throw exception if midi is not available to model.
   */
  public static SongView createView(String viewType) throws MidiUnavailableException {
    switch (viewType) {
      case "midi":
        return new MidiViewImpl();
      case "gui":
        return new GuiViewFrame();
      case "stacked":
        return new StackView();
      default:
        throw new IllegalArgumentException("View type provided is invalid!");
    }
  }
}
