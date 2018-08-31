package cs3500.music.model.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import cs3500.music.model.Pitch;
import cs3500.music.model.Note;
import cs3500.music.model.NoteSpan;


/**
 * Test note class & functions.
 */
public class NoteTest {

  Note noteE1 = new Note(Pitch.E, 1);
  Note noteE2 = new Note(Pitch.E, 2);
  Note noteF2 = new Note(Pitch.F, 2);
  Note noteFs3 = new Note(Pitch.F_, 3);
  Note noteF1 = new Note(Pitch.F, 1);
  Note noteFs2 = new Note(Pitch.F_, 2);

  // try to make note with invalid pitch
  /*
  @Test(expected = IllegalArgumentException.class)
  public void invalidPitch(){
    Note testNote = new Note(Pitch.A , 1, 1, 1);
  }
  */

  // try to make not with invalid octave
  @Test(expected = IllegalArgumentException.class)
  public void invalidOctave() {
    Note testNote = new Note(Pitch.A , -1);
  }

  // test get pitch
  @Test
  public void getPitch() {
    assertEquals(Pitch.F, this.noteF2.getPitch());
  }

  // test get octave
  @Test
  public void getOctave() {
    assertEquals(3, this.noteFs3.getOctave());
  }

  // test to string non-sharp
  @Test
  public void toStringNonSharp() {
    assertEquals("F2",this.noteF2.toString());
  }

  // test to string sharp
  @Test
  public void toStringSharp() {
    assertEquals("F#3",this.noteFs3.toString());
  }

  // test toInt
  @Test
  public void toInt() {
    assertEquals(41, this.noteF2.toInt());
    assertEquals(54, this.noteFs3.toInt());
  }

  // test compareTo same
  @Test
  public void compareToSame() {
    assertEquals(0, this.noteF2.compareTo(this.noteF2));
  }

  // test compareTo greater
  @Test
  public void compareToGreater() {
    assertEquals(-13, this.noteF2.compareTo(this.noteFs3));
  }

  // test compareTo less
  @Test
  public void compareToLess() {
    assertEquals(13, this.noteF2.compareTo(this.noteE1));
  }

  // test equals valid
  @Test
  public void equalsValid() {
    assertEquals(true, this.noteE1.equals(this.noteE1));
  }

  // test equals invalid pitch
  @Test
  public void equalisInvalidPitch() {
    assertEquals(false, this.noteE1.equals(this.noteF2));
  }

  // test equals invalid octave
  @Test
  public void equalsInvalidOctave() {
    assertEquals(false, this.noteE1.equals(this.noteE2));
  }

  // test from note (used for rendering view)
  @Test
  public void fromNote() {
    assertEquals("D3", Note.toNote(50).toString());
    assertEquals("D#3", Note.toNote(51).toString());
    assertEquals("E3", Note.toNote(52).toString());
    assertEquals("F3", Note.toNote(53).toString());
  }

  // test new noteSpan methods
  @Test
  public void noteRangeAndIterTest() {
    NoteSpan r = new NoteSpan(new Note(Pitch.C, 1), new Note(Pitch.C, 3));
    String str = "";
    for (Note n : r) {
      str += n.toString() + " ";
    }
    assertEquals(str, "C1 C#1 D1 D#1 E1 F1 F#1 " +
            "G1 G#1 A1 A#1 B1 C2 C#2 D2" +
            " D#2 E2 F2 F#2 G2 G#2 A2 A#2 B2 C3 ");
  }
}
