package cs3500.music.model.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SongComp;
import cs3500.music.model.SongPart;

/**
 * Test Music Model.
 */
public class ModelTest {
  // create notes
  Note noteE1 = new Note(Pitch.E, 1);
  Note noteE2 = new Note(Pitch.E, 2);
  Note noteF2 = new Note(Pitch.F, 2);
  Note noteFs3 = new Note(Pitch.F_, 3);
  Note noteG1 = new Note(Pitch.G, 1);
  Note noteA2 = new Note(Pitch.A, 2);
  Note noteB1 = new Note(Pitch.B, 1);

  Note cn1 = new Note(Pitch.G, 1);
  Note cn2 = new Note(Pitch.A, 1);
  Note cn3 = new Note(Pitch.B, 1);

  // create SongParts
  SongPart p1 = new SongPart(noteE1, 1, 1,0,0);
  SongPart p2 = new SongPart(noteE2, 3, 5,0,0);
  SongPart p3 = new SongPart(noteFs3, 8, 10,0,0);
  SongPart p4 = new SongPart(noteG1, 10, 8,0,0);
  SongPart p5 = new SongPart(noteA2, 10, 6,0,0);
  SongPart p6 = new SongPart(noteB1, 16, 5,0,0);

  SongPart pc1 = new SongPart(cn1, 1, 8,0,0);
  SongPart pc2 = new SongPart(cn2, 1, 8,0,0);
  SongPart pc3 = new SongPart(cn3, 1, 8,0,0);

  SongPart mc1 = new SongPart(cn1, 1, 8,0,0);
  SongPart mc2 = new SongPart(cn2, 8, 10,0,0);
  SongPart mc3 = new SongPart(cn3, 10, 12,0,0);

  // list of songPart
  List lsp1 = new ArrayList<SongPart>();
  List lsp2 = new ArrayList<SongPart>();

  List clist = new ArrayList<SongPart>();
  List mlist = new ArrayList<SongPart>();
  List emptList = new ArrayList<SongPart>();

  SongComp sc1 = new SongComp(lsp1, 20, 4);

  SongComp sc2 = new SongComp(lsp2, 20, 4);

  SongComp chord = new SongComp(clist, 20, 4);
  SongComp melody = new SongComp(mlist, 20, 4);

  SongComp empty = new SongComp(emptList, 20, 4);

  /**
   * Init before running tests.
   */
  @Before
  public void init() {
    lsp1.add(p1);
    lsp1.add(p2);
    lsp1.add(p3);

    lsp2.add(p1);
    lsp2.add(p4);
    lsp2.add(p6);

    clist.add(pc1);
    clist.add(pc2);
    clist.add(pc3);

    mlist.add(mc1);
    mlist.add(mc2);
    mlist.add(mc3);

    SongComp sc1 = new SongComp(lsp1, 20, 4);

    SongComp sc2 = new SongComp(lsp2, 20, 4);

    SongComp chord = new SongComp(clist, 20, 4);
    SongComp melody = new SongComp(mlist, 20, 4);
  }

  // invalid tempo
  @Test(expected = IllegalArgumentException.class)
  public void invalidTempo() {
    SongComp scTest = new SongComp(lsp1, -1, 4);
  }

  // invalid time signature
  @Test(expected = IllegalArgumentException.class)
  public void invalidTimeSig() {
    SongComp scTest = new SongComp(lsp1, 20, -1);
  }

  // get tempo
  @Test
  public void getTempo() {
    assertEquals(20, this.sc1.getTempo());
  }

  // get time signature
  @Test
  public void getTimeSignature() {
    assertEquals(4, this.sc1.getTimeSig());
  }

  // ensure all notes actually added
  @Test
  public void checkSize() {
    assertEquals(3, this.sc1.getNotes().size());
    assertEquals(3, this.chord.getNotes().size());
    assertEquals(3, this.melody.getNotes().size());
    assertEquals(0, this.empty.getNotes().size());
  }

  @Test
  public void checkContents() {
    assertEquals("G1 A1 B1 ", this.chord.toString());
    assertEquals("G1 A1 B1 ", this.melody.toString());
    assertEquals("", this.empty.toString());
  }

  // get length
  @Test
  public void getLength() {
    assertEquals(18, sc1.getLength());
  }

  // get lowest note
  @Test
  public void getMinNote() {
    assertEquals(noteE1, sc1.getMinNote());
  }

  // get highest note
  @Test
  public void getMaxNote() {
    assertEquals(noteFs3, sc1.getMaxNote());
  }

  // add note
  // assertEquals(3, this.sc1.getNotes().size());
  @Test
  public void addNote() {
    this.sc1.addNote(p3);
    assertEquals(4, this.sc1.getNotes().size());
    assertEquals("E1 E2 F#3 F#3 ", this.sc1.toString());
  }

  // add note parallel
  @Test
  public void addNoteParallel() {
    this.sc1.addNote(p4);
    this.sc1.addNote(p5);
    assertEquals(5, this.sc1.getNotes().size());
    assertEquals("E1 E2 F#3 G1 A2 ", this.sc1.toString());
  }

  // add note consecutive
  @Test
  public void addNoteCons() {
    this.sc1.addNote(p5);
    this.sc1.addNote(p6);
    assertEquals(5, this.sc1.getNotes().size());
    assertEquals("E1 E2 F#3 A2 B1 ", this.sc1.toString());
  }


  // remove note
  @Test
  public void removeNote() {
    this.sc1.removeNote(p1);
    assertEquals(2, this.sc1.getNotes().size());
    assertEquals("E2 F#3 ", this.sc1.toString());
  }

  // test toString
  @Test
  public void testToString() {
    assertEquals("E1 E2 F#3 ", this.sc1.toString());
  }

  @Test
  public void checkView() {
    assertEquals("   E1   F1   F#1   G1   G#1   A1   A#1   B1   \n" +
            "0                                           \n" +
            "1   |                                       \n" +
            "2                                           \n" +
            "3                                           \n" +
            "4                                           \n" +
            "5                                           \n" +
            "6                                           \n" +
            "7                                           \n" +
            "8                                           \n" +
            "9                                           \n" +
            "10                  |                        \n" +
            "11                  |                        \n" +
            "12                  |                        \n" +
            "13                  |                        \n" +
            "14                  |                        \n" +
            "15                  |                        \n" +
            "16                  |                   |    \n" +
            "17                  |                   |    \n" +
            "18                                      |    \n" +
            "19                                      |    \n" +
            "20                                      |    \n" +
            "21                                           \n", sc2.getSongData());
  }
}
