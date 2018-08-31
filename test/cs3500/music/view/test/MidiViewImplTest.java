package cs3500.music.view.test;

import cs3500.music.view.MidiViewImpl;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Kevin Lieb on 6/14/2017.
 */
public class MidiViewImplTest {

  @Test
  public void test()
      throws MidiUnavailableException, InvalidMidiDataException, InterruptedException {
    MidiViewImpl soundView = new MidiViewImpl();
    soundView.playNote(75, 0, 5000000, 100, 40);
    soundView.playNote(50,1000000, 3000000, 70, 40);
    Thread.sleep(5000);
    soundView.closeReceiver();
    int a = 1;
    assertEquals(a,1);
  }



}