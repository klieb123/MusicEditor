package cs3500.music.model.test;

import cs3500.music.model.MusicOperations;

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
 * Created by alex on 6/10/17.
 */
public class BuilderTest {

  // create notes
  Note noteE1 = new Note(Pitch.E, 1);
  Note noteE2 = new Note(Pitch.E, 2);
  Note noteF2 = new Note(Pitch.F, 2);
  Note noteFs3 = new Note(Pitch.F_, 3);
  Note noteG1 = new Note(Pitch.G, 1);
  Note noteA2 = new Note(Pitch.A, 2);
  Note noteB1 = new Note(Pitch.B, 1);

  // create SongParts
  SongPart p1 = new SongPart(noteE1, 1, 1,0,0);
  SongPart p2 = new SongPart(noteE2, 3, 5,0,0);
  SongPart p3 = new SongPart(noteFs3, 8, 4,0,0);
  SongPart p4 = new SongPart(noteG1, 10, 5,0,0);
  SongPart p5 = new SongPart(noteA2, 10, 6,0,0);
  SongPart p6 = new SongPart(noteB1, 16, 5,0,0);

  MusicOperations mod;

  /**
   * Init before testing.
   */
  @Before
  public void exampleSetup() {
    List<SongPart> testSC = new ArrayList<>();
    mod = new SongComp(testSC, 20, 4);
    mod.addNote(p1);
    mod.addNote(p2);
    mod.addNote(p3);
    mod.addNote(p4);
    mod.addNote(p5);
  }

  @Test
  public void checkAdd() {
    assertEquals(5, mod.getNotes().size());
    assertEquals(16, mod.getLength());
  }

  // check remove
  @Test
  public void checkRemove() {
    mod.removeNote(noteE1,1);
    assertEquals(4, mod.getNotes().size());
  }

  // check highest
  @Test

  public void checkHighest() {
    assertEquals(5, mod.getNotes().size());
    assertEquals(noteFs3, mod.getMaxNote());
  }

  // check lowest
  @Test
  public void checkLowest() {
    assertEquals(5, mod.getNotes().size());
    assertEquals(noteE1, mod.getMinNote());
  }

}
