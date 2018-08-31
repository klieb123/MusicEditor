import cs3500.music.controller.MusicController;
import cs3500.music.controller.StandardController;
import cs3500.music.model.SongComp;
import cs3500.music.util.MusicReader;
import cs3500.music.model.MusicOperations;
import cs3500.music.view.SongView;
import cs3500.music.view.ViewFactory;

import java.io.FileReader;
import javax.sound.midi.MidiUnavailableException;


/**
 * Class to combine Controller and GUI / Midi views.
 */
public class MusicEditor {

  /**
   * Main method to execute graphical interface.
   * @param args text file to generate songComp from and view option.
   * @throws InterruptedException if beat out of sync throw exception.
   * @throws MidiUnavailableException if no midi throw exception.
   */
  public static void main(String[] args) throws InterruptedException, MidiUnavailableException {
    MusicOperations song = null;
    if (args.length < 2) {
      throw new IllegalArgumentException("Either the song path or view option were not provided!");
    }
    try {
      song = MusicReader.parseFile(new FileReader(args[0]),
              new SongComp.SongBuilder());
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    SongView view = ViewFactory.createView(args[1]);
    MusicController controller = new StandardController(song, view);
    controller.render();
  }
}
