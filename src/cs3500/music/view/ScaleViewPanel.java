package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.SongPart;
import cs3500.music.controller.MusicController;
import cs3500.music.model.NoteSpan;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.List;

/**
 * Panel for GUI view that shows notes drawn onto musical scale.
 */
public class ScaleViewPanel extends JPanel {

  // padding / render values
  private final int measureLength;
  private final int x_padd;
  private final int y_padd;
  private final int y_note;
  private final int x_note;
  private final int barWidthSize;
  private final int scrollWidth;
  private final int noteBounds;
  private final int endBounds;


  // note data
  private int maxNoteBeat;
  private int beat;
  private int viewHeight;
  private int barHeight;
  private boolean beatLine;
  private List<SongPart> notes;
  private Note highestNote;
  private Note lowestNote;
  private NoteSpan span;

  /**
   * Panel that displays notes on top of scale, only takes controller.
   * @param controller provides note and model data to gui.
   */
  public ScaleViewPanel(MusicController controller) {
    this.measureLength = controller.getTimeSig();
    this.span = new NoteSpan(controller.minNote(), controller.maxNote());

    this.y_padd = 15;
    this.x_padd = 45;
    this.y_note = 15;
    this.x_note = 15;
    this.barWidthSize = 2;
    // 2x the width of a bar
    this.scrollWidth = 4;

    this.notes = new ArrayList<>();
    this.highestNote = controller.maxNote();
    this.lowestNote = controller.minNote();
    this.maxNoteBeat = controller.getLength();
    this.noteBounds = 2;
    this.endBounds = 2;


    // helps establish sizing between measures
    this.updateView();

    // determines whether beat line should be displayed
    this.beatLine = true;
  }

  /**
   * Constructor for scaleViewPanel that adds option for displaying beatline or not.
   * @param controller provides note and model data.
   * @param beatLine whether to show beatline or not.
   */
  public ScaleViewPanel(MusicController controller, boolean beatLine) {
    this(controller);
    // whether to render beatline or not.
    this.beatLine = beatLine;
  }

  /**
   * Paint graphical interface.
   * @param g graphic to add to.
   */
  @Override
  public void paintComponent(Graphics g) {
    g.setFont(new Font("Arial", Font.BOLD, 13));
    super.paintComponent(g);
    this.paintScaleNotes(g);
    this.drawScaleLines(g);
    this.fillBeats(g);
    this.paintBeatLabels(g);
    this.drawBeatLine(g);
  }

  /**
   * Updates the size of the view.
   */
  private void updateView() {
    int noteCount = this.highestNote.compareTo(this.lowestNote) + 1;
    this.viewHeight = y_padd + (noteCount * y_note) + ((noteCount + 1) * barWidthSize);
    this.barHeight = this.viewHeight - y_padd;
  }

  /**
   * Draws the blocks representing the beats.
   * @param g graphics to be added to.
   */
  private void fillBeats(Graphics g) {
    g.setColor(Color.green);
    for (SongPart n : this.notes) {
      drawNote(g, n);
    }
  }

  /**
   * Draws one provided note.
   * @param g Graphic to add to.
   * @param n note data to be drawn.
   */
  private void drawNote(Graphics g, SongPart n) {
    Graphics2D gNote = (Graphics2D)g;
    int spacing = this.highestNote.compareTo(n.getNote());
    int yPos = y_padd + spacing  * this.y_note + spacing * this.barWidthSize + this.barWidthSize;
    gNote.fillRect(n.getStartBeat() * this.x_note + this.x_padd,
            yPos, n.getDuration() * this.x_note - this.noteBounds,
            this.y_note);
  }

  /**
   * Draws large vertical red line representing beatline.
   * @param g Graphic to add to.
   */
  private void drawBeatLine(Graphics g) {
    if (this.beatLine) {
      g.setColor(Color.red);
      g.fillRect(this.x_padd + (this.beat * this.x_note), this.y_padd,
              this.scrollWidth, this.barHeight);
    }
  }

  /**
   * Draws lines separating notes and measures.
   * @param g graphic to add to.
   */
  private void drawScaleLines(Graphics g) {
    g.setColor(Color.black);
    // vertical
    for (int i = 0; i <= this.maxNoteBeat; i++) {
      if (i % this.measureLength == 0) {
        g.fillRect(this.x_padd + (i * this.x_note), this.y_padd,
                this.barWidthSize, this.barHeight);
      }
    }
    // horizontal
    int flatBarWidth = (this.maxNoteBeat * this.x_note);
    int curY = this.y_padd;
    for (Note n : this.span) {
      g.fillRect(this.x_padd, curY, flatBarWidth, this.barWidthSize);
      curY += this.y_note + this.barWidthSize;
    }
    g.fillRect(this.x_padd, curY,
            flatBarWidth,
            this.barWidthSize);
    // make sure view size is big enough
    this.setPreferredSize(new Dimension(flatBarWidth + this.x_padd
            + this.endBounds, this.viewHeight));
  }

  /**
   * Render scale of notes on the left side of panel.
   * @param g graphic to add to.
   */
  private void paintScaleNotes(Graphics g) {
    int curY = this.viewHeight;
    for (Note n : span) {
      g.drawString(n.toString(), 0, curY);
      curY -= (this.y_note + this.barWidthSize);
    }
  }

  /**
   * Draw beat label across top of scale view.
   * @param g graphic to add to.
   */
  private void paintBeatLabels(Graphics g) {
    g.setColor(Color.black);
    for (int i = 0; i <= this.maxNoteBeat; i++) {
      if (i % this.measureLength == 0) {
        g.drawString("" + i, this.x_padd + i * this.x_note,
                this.y_padd - (Math.round(this.y_padd / 6)));
      }
    }
  }

  /**
   * Set value of current beat.
   * @param beat value for new current beat.
   */
  public void setBeat(int beat) {
    this.beat = beat;
  }

  /**
   * Updates list of notes to most recent set.
   * @param notes notes to be set.
   */
  public void setNotes(List<SongPart> notes) {
    this.notes = notes;
  }

}
