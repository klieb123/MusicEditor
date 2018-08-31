package cs3500.music.view.test;



import static org.junit.Assert.assertEquals;

import cs3500.music.controller.StandardController;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SongComp;
import cs3500.music.model.SongPart;
import cs3500.music.view.MidiViewImpl;
import java.util.ArrayList;
import java.util.List;
import javax.sound.midi.MidiUnavailableException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for standard controller.
 */
public class StandardControllerTest {
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

  MidiViewImpl midi = new MidiViewImpl();

  StandardController c1 = new StandardController(sc1, midi);
  StandardController c2 = new StandardController(sc2, midi);
  StandardController cChord = new StandardController(chord, midi);
  StandardController cMelody = new StandardController(melody, midi);
  StandardController cEmpty = new StandardController(empty, midi);

  /**
   * Instance of Midi for testing.
   * @throws MidiUnavailableException when midi not evailable throw exception.
   */
  public StandardControllerTest() throws MidiUnavailableException {
    // empty for testing since it's just here to establish inst of midi.
  }


  /**
   * Init before tests.
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

  // invalid model
  @Test(expected = IllegalArgumentException.class)
  public void invalidModel() {
    StandardController nullModel = new StandardController(null,midi);
  }

  // invalid view
  @Test(expected = IllegalArgumentException.class)
  public void invalidView() {
    StandardController nullView = new StandardController(sc1,null);
  }

  // get tempo
  @Test
  public void getTempo() {
    assertEquals(20, this.c1.getTempo());
  }

  // get time signature
  @Test
  public void getTimeSignature() {
    assertEquals(4, this.c1.getTimeSig());
  }

  // ensure all notes actually added
  @Test
  public void checkSize() {
    assertEquals(3, this.c1.getNotes().size());
    assertEquals(3, this.cChord.getNotes().size());
    assertEquals(3, this.cMelody.getNotes().size());
    assertEquals(0, this.cEmpty.getNotes().size());
  }

  @Test
  public void checkContents() {
    assertEquals("G1 A1 B1 ", this.cChord.toString());
    assertEquals("G1 A1 B1 ", this.cMelody.toString());
    assertEquals("", this.cEmpty.toString());
  }

  // get length
  @Test
  public void getLength() {
    assertEquals(18, c1.getLength());
  }


  // add note
  // assertEquals(3, this.sc1.getNotes().size());
  @Test
  public void addNote() {
    this.c1.addNote(noteE1,1,1,0,0);
    assertEquals(4, this.c1.getNotes().size());
    assertEquals("E1 E2 F#3 F#3 ", this.c1.toString());
  }

  // add note parallel
  @Test
  public void addNoteParallel() {
    this.sc1.addNote(p4);
    this.sc1.addNote(p5);
    assertEquals(5, this.c1.getNotes().size());
    assertEquals("E1 E2 F#3 G1 A2 ", this.c1.toString());
  }

  // add note consecutive
  @Test
  public void addNoteCons() {
    this.c1.addNote(noteG1,1,1,0,0);
    this.c1.addNote(noteA2,1,1,0,0);
    assertEquals(5, this.c1.getNotes().size());
    assertEquals("E1 E2 F#3 A2 B1 ", this.c1.toString());
  }


  // remove note
  @Test
  public void removeNote() {
    this.c1.removeNote(noteE1,1);
    assertEquals(2, this.c1.getNotes().size());
    assertEquals("E2 F#3 ", this.c1.toString());
  }

  // test toString
  @Test
  public void testToString() {
    assertEquals("E1 E2 F#3 ", this.c1.toString());
  }

  // test miniNote
  @Test
  public void testMiniNote() {
    assertEquals(noteE1, this.c1.minNote());
  }

  // test miniNote
  @Test
  public void testMaxNote() {
    assertEquals(noteFs3, this.c1.maxNote());
  }

  // test miniNote
  @Test
  public void testLength() {
    assertEquals(this.c1.getNotes().size(), this.c1.getLength());
  }
}
