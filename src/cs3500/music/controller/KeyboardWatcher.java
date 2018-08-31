package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to handle physical keyboard input.
 */
public class KeyboardWatcher implements KeyListener {

  // map of key events
  private Map<Integer, Runnable> pressed;

  public KeyboardWatcher(MusicController controller) {
    this.pressed = new HashMap<>();
    this.initKeys(controller);
  }


  // config map of key events
  private void initKeys(MusicController controller) {
    // progress beat one to right (+)
    // add 1 to current beat in controller
    //this.Pressed.put(KeyEvent.VK_LEFT, () -> controller.progressBeatLeft());
    // progress beat one to left (-)
    // subtract 1 from current beat in controller
    //this.Pressed.put(KeyEvent.VK_RIGHT, () -> controller.progressBeatRight());
  }

  /**
   * Method to handle key when pressed.
   * @param event event to handle.
   */
  @Override
  public void keyPressed(KeyEvent event) {
    Runnable action = this.pressed.get(event.getKeyCode());
    if (action != null) {
      action.run();
    }
  }

  /**
   * Method to handle key held down.
   * @param event event to handle
   */
  @Override
  public void keyTyped(KeyEvent event) {
    System.out.println("here for keyListener");
  }

  /**
   * Method to handle key when released
   * @param event event to handle.
   */
  @Override
  public void keyReleased(KeyEvent event) {
    System.out.println("here for keyListener");
  }
}
