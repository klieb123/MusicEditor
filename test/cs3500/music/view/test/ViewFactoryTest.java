package cs3500.music.view.test;

import org.junit.Test;

import javax.sound.midi.MidiUnavailableException;

import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.SongView;
import cs3500.music.view.StackView;
import cs3500.music.view.ViewFactory;

import static org.junit.Assert.assertTrue;

/**
 * Does the factory method for making views work.
 */
public class ViewFactoryTest {

  @Test
  public void GuiViewTest() throws MidiUnavailableException {
    SongView view = ViewFactory.createView("gui");
    assertTrue(view instanceof GuiViewFrame);
  }

  @Test
  public void MakeMidiViewTest() throws MidiUnavailableException {
    SongView view = ViewFactory.createView("midi");
    assertTrue(view instanceof MidiViewImpl);
  }

  @Test
  public void MakeStackedViewTest() throws MidiUnavailableException {
    SongView view = ViewFactory.createView("stacked");
    assertTrue(view instanceof StackView);
  }
}