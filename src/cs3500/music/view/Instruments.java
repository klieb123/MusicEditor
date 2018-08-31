package cs3500.music.view;

//import javax.sound.midi.*;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Instrument;

//import javax.swing.;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Represents instruments available to midi.
 */
class Instruments {

  /**
   * Initialize instruments for use by midi view.
   * @param args args of instrument vals.
   * @throws MidiUnavailableException throw exception if midi is unavailable.
   */
  public static void main(String[] args) throws MidiUnavailableException {
    Synthesizer synthesizer = MidiSystem.getSynthesizer();
    synthesizer.open();
    Instrument[] orchestra = synthesizer.getAvailableInstruments();

    final StringBuilder SB = new StringBuilder();
    String eol = System.getProperty("line.separator");
    SB.append("The orchestra has ");
    SB.append(orchestra.length);
    SB.append(" instruments.");
    SB.append(eol);
    for (Instrument instrument : orchestra) {
      SB.append(instrument.toString());
      SB.append(eol);
    }
    synthesizer.close();

    Runnable r = new Runnable() {

      /**
       * Create sound from midi command.
       */
      @Override
      public void run() {
        JOptionPane.showMessageDialog(null,
            new JScrollPane(new JTextArea(SB.toString(), 20, 30)));
      }
    };
    SwingUtilities.invokeLater(r);
  }
}
