package cs3500.music.model.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.music.model.Pitch;

/**
 * Test Pitch enum.
 */
public class PitchTest {
  // test to string
  @Test
  public void testToString() {
    assertEquals("C", Pitch.C.toString());
    assertEquals("C#", Pitch.C_.toString());
    assertEquals("D", Pitch.D.toString());
    assertEquals("D#", Pitch.D_.toString());
    assertEquals("E", Pitch.E.toString());
    assertEquals("F", Pitch.F.toString());
    assertEquals("F#", Pitch.F_.toString());
    assertEquals("G", Pitch.G.toString());
    assertEquals("G#", Pitch.G_.toString());
    assertEquals("A", Pitch.A.toString());
    assertEquals("A#", Pitch.A_.toString());
    assertEquals("B", Pitch.B.toString());
  }
}
