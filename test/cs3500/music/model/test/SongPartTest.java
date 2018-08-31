package cs3500.music.model.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SongPart;

/**
 * Tests for SongPart.
 */
public class SongPartTest {
  // create notes
  Note noteE1 = new Note(Pitch.E, 1);
  Note noteE2 = new Note(Pitch.E, 2);
  Note noteF2 = new Note(Pitch.F, 2);
  Note noteFs3 = new Note(Pitch.F_, 3);

  // create SongParts
  SongPart p1 = new SongPart(noteE1, 1, 1,0, 0);
  SongPart p2 = new SongPart(noteE1, 2, 5, 0, 0);

  // test invalid start beat
  @Test(expected = IllegalArgumentException.class)
  public void invalidStartBeat() {
    SongPart badP1 = new SongPart(noteE1, -1, 1, 0,0);
  }

  // test invalid duration
  @Test(expected = IllegalArgumentException.class)
  public void invalidDuration() {
    SongPart badP2 = new SongPart(noteE1, 1, -1, 0, 0);
  }

  // test query note
  @Test
  public void queryNote() {
    assertEquals(this.noteE1, this.p1.getNote());
  }

  // test query start beat
  @Test
  public void queryStartBeat() {
    assertEquals(1, this.p1.getStartBeat());
  }

  // test query duration
  @Test
  public void queryDuration() {
    assertEquals(1, this.p1.getDuration());
  }

  // test query note pitch
  @Test
  public void queryNPitch() {
    assertEquals(Pitch.E, this.p1.getNotePitch());
  }

  // test query note octave
  @Test
  public void queryNOctave() {
    assertEquals(1, this.p1.getNoteOctave());
  }

  // at start
  @Test
  public void startBeatValid() {
    assertEquals(true, this.p1.atStart(1));
  }

  @Test
  public void startBeatInvalidBefore() {
    assertEquals(false, this.p1.atStart(0));
  }

  @Test
  public void startBeatInvalidAfter() {
    assertEquals(false, this.p1.atStart(2));
  }

  // at end
  @Test
  public void endBeatValid() {
    assertEquals(true, this.p2.atEnd(7));
  }

  @Test
  public void endBeatInvalidBefore() {
    assertEquals(false, this.p1.atEnd(1));
  }

  @Test
  public void endBeatInvalidAfter() {
    assertEquals(false, this.p1.atEnd(8));
  }


  // at middle
  @Test
  public void middleBeatValid() {
    assertEquals(true, this.p2.atMiddle(3));
  }

  @Test
  public void middleBeatInvalidBefore() {
    assertEquals(false, this.p1.atMiddle(2));
  }

  @Test
  public void middleBeatInvalidAfter() {
    assertEquals(false, this.p1.atMiddle(8));
  }

  // equal
  @Test
  public void equalValid() {
    assertEquals(true, this.p1.equals(this.p1));
  }

  // not equal
  @Test
  public void equalInvalid() {
    assertEquals(false, this.p1.equals(this.p2));
  }
}
