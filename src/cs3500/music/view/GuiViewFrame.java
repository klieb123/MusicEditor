package cs3500.music.view;

import cs3500.music.model.Note;
import cs3500.music.model.SongPart;
import java.awt.Dimension;

import cs3500.music.controller.MusicController;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

/**
 * View frame for GUI view.
 */
public class GuiViewFrame extends JFrame implements SongView {

  private ScaleViewPanel scalePanel;
  private KeyboardComponent keyboardComponent;
  private MusicController controller;

  /**
   * Constructor for GUI view.
   */
  public GuiViewFrame() {
    // show beatline?
  }

  /**
   * Initialize GUI view, includes scalePanel and Keyboard component.
   * @param controller controller to initialize gui with.
   */
  @Override
  public void initialize(MusicController controller) {
    // initial jframe config
    this.setTitle("Music Editor");
    this.controller = controller;
    //this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(1200, 600));
    this.setPreferredSize(new Dimension(1200, 600));


    // layout config to stack new panels / components vertically
    BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
    this.setLayout(boxLayout);

    // define panels / components and add to jframe.
    this.scalePanel = new ScaleViewPanel(controller, true);
    this.scalePanel.setNotes(controller.getNotes());
    JPanel displayPanel = this.scalePanel;
    this.add(displayPanel);
    this.keyboardComponent = new KeyboardComponent();
    this.add(this.keyboardComponent);

    // pack and display
    this.pack();
    this.setVisible(true);
  }

  /**
   * Draw given beat for GUI view
   * @param beat beat to be rendered.
   */
  @Override
  public void drawBeat(int beat) throws InterruptedException {
    ArrayList<Note> notes = new ArrayList<>();
    for ( SongPart n : this.controller.getNotes() ) {
      if ( n.atStart(beat) || n.atMiddle(beat) || n.atEnd(beat) ) {
        notes.add(n.getNote());
      }
    }
    // update notes for keyboard and beat to draw beatline on scaleviewpanel
    this.keyboardComponent.clearAndAddNotes(notes);
    this.scalePanel.setBeat(beat);
    this.repaint();
    Thread.sleep(500);
  }
}