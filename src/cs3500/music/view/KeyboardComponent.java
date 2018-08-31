package cs3500.music.view;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComponent;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

/**
 * Represents keyboard visualization in gui view.
 */
public class KeyboardComponent extends JComponent {
  /**
   * * Notes that should be highligthed since they're currently being played.
   */
  private ArrayList<Note> displayNotes = new ArrayList<Note>();

  /**
   * Empty note list for next beat.
   */
  public void clearNotes() {
    this.displayNotes.clear();
  }

  /**
   * Adds a single note to be displayed.
   * @param note note to be displayed.
   */
  public void addNote(Note note) {
    if (note == null) {
      throw  new IllegalArgumentException("Note can't be null");
    }
    this.displayNotes.add(note);
  }

  /**
   * Clears the old notes out Canvas and replaces them with a new set.
   * @param notes the notes to be displayed.
   */
  public void clearAndAddNotes(ArrayList<Note> notes) {
    if (notes == null) {
      throw new IllegalArgumentException("Notes can't be null");
    }

    if (notes.contains(null)) {
      throw new IllegalArgumentException("Notes to be added can't be null");
    }
    clearNotes();
    ArrayList<Note> b = new ArrayList<Note>(notes);
    this.displayNotes = b;
  }

  /**
   * Draws a solid filled rectangle at a point on the graphic.
   * @param g the graphic that is to be painted on.
   * @param c the rectangle color.
   * @param xPos the x pos of the rectangle.
   * @param xSize the horizontal size of the rectangle.
   * @param ySize the vertical size of the rectangle.
   */
  private void drawFillRec(Graphics g, Color c, int xPos, int xSize, int ySize) {
    g.setColor(c);
    g.fillRect(xPos, 0, xSize, ySize);
  }

  /**
   * Draws border only rectangle at a point on the graphic.
   * @param g the graphic that is to be painted on.
   * @param c the rectangle color.
   * @param xPos the x pos of the rectangle.
   * @param xSize the horizontal size of the rectangle.
   * @param ySize the vertical size of the rectangle.
   */
  private void drawRec(Graphics g, Color c, int xPos, int xSize, int ySize) {
    g.setColor(c);
    g.drawRect(xPos, 0, xSize, ySize);
  }

  /**
   * Render keyboard with non-highlighted and highlighed keys.
   * @param g graphic to add to.
   */
  @Override
  public void paint(Graphics g) {
    drawFillRec(g, Color.gray, 0, 1200, 150);
    List<Integer> rightPush = Arrays.asList(28, 44, 76, 92, 108);

    for (int i = 0; i < 10; i++) {
      int j = 0;
      for (Pitch p : Pitch.values()) {
        if (p.toString().contains("#")) {
          j++;
        } else {
          Color c = Color.white;
          for (Note n: this.displayNotes) {
            if (n.getOctave() == i && n.getPitch() == p) {
              c = Color.yellow;
            }
          }
          drawFillRec(g, c, ((p.ordinal() - j) * 16) + (112 * i) + 16, 16, 130);
          drawRec(g, Color.black, ((p.ordinal() - j) * 16) + (112 * i) + 16, 16, 130);
        }
      }
    }
    for (int i = 0; i < 10; i++) {
      int j = 0;
      for (Pitch p : Pitch.values()) {
        if (p.toString().contains("#")) {
          Color c = Color.black;
          for (Note n: this.displayNotes) {
            if (n.getOctave() == i && n.getPitch() == p ) {
              c = Color.yellow;
            }
          }
          drawFillRec(g, c, rightPush.get(j) + (112 * i), 8, 75 );
          j++;
        }
      }
    }
  }
}