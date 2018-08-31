package cs3500.music.model;

import java.util.Iterator;

/**
 * Iterator for note class.
 */
public class NoteIterator implements Iterator<Note> {
  Note current;
  Note stop;

  /**
   * Contructs NoteIterator object.
   * @param begin start note for iterator.
   * @param end end note for iterator.
   */
  public NoteIterator(Note begin, Note end) {
    this.current = begin;
    this.stop = end;
  }

  /**
   * Custom hasNext method for NoteIterator.
   * @return whether another note exists in current iterator object.
   */
  @Override
  public boolean hasNext() {
    return current.compareTo(stop) <= 0;
  }

  /**
   * Custom next method for NoteIterator.
   * @return whether the next note is identical to the current note.
   */
  @Override
  public Note next() {
    Note res = this.current;
    this.current = Note.toNote(this.current.toInt() + 1);
    return res;
  }
}