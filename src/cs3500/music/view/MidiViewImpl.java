package cs3500.music.view;

import cs3500.music.controller.MusicController;
import cs3500.music.model.SongPart;

import javax.sound.midi.Receiver;
import javax.sound.midi.Track;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

/**
 * View to provide sound for notes.
 */
public class MidiViewImpl implements SongView {

  private final Receiver receiver;
  private MusicController controller;
  private Track track;

  /**
   * Implementation of midi synth.
   * @throws MidiUnavailableException if midi not available throw exception.
   */
  public MidiViewImpl() throws MidiUnavailableException {
    Synthesizer synth = MidiSystem.getSynthesizer();
    this.receiver = synth.getReceiver();
    synth.open();
    this.controller = null;
  }
  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */


  /**
   * Play midi note with synth.
   * @param pitch pitch to play.
   * @param begin time to begin playing.
   * @param end time to stop playing.
   * @param volume volume to play at.
   * @param instrument instrument to play note with.
   * @throws InvalidMidiDataException throw exception if midi not available.
   */
  public void playNote(int pitch, long begin, long end,
                       int volume, int instrument) throws InvalidMidiDataException {
    MidiMessage setInstrument  = new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, instrument ,0);
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, pitch, volume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0,  pitch, volume);
    this.receiver.send(setInstrument, begin);
    this.receiver.send(start, begin);
    this.receiver.send(stop, end);
  }

  /**
   * Closes this classes receiver.
   */
  public void closeReceiver() {
    this.receiver.close();
  }

  /**
   * Intiializes midi view with music controller.
   * @param c the controller calling the view's methods
   */
  @Override
  public void initialize(MusicController c) {
    if (this.controller != null) {
      throw new IllegalArgumentException("Existing controller already init!");
    }
    this.controller = c;
  }

  /**
   * Drawbeat method for midi, plays set of sounds for given beat.
   * @param beat the beat number to play.
   */
  @Override
  public void drawBeat(int beat) {

    long startMiliBeat = controller.getTempo() * beat;
    long endMiliBeat = startMiliBeat + controller.getTempo();

    for (SongPart n :controller.getNotes()) {
      if (n.atEnd(beat) || n.atStart(beat) || n.atMiddle(beat)) {
        try {
          playNote(n.getNote().toInt(),
                  startMiliBeat,
                  endMiliBeat,
                  n.getVolume(),
                  n.getInstrument());
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
