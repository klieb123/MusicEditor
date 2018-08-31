package cs3500.music.model;

import java.util.Iterator;

/**
 * Class to represent all valid notes between two arbitrary notes.
 * Follows valid chromatic scale.
 */
public class NoteSpan implements Iterable<Note> {
  Note begin;
  Note end;

  /**
   * Constructs a NoteSpan object.
   * @param begin beginning note of NoteSpan.
   * @param end ending note of NoteSpan.
   */
  public NoteSpan(Note begin, Note end) {
    this.begin = begin;
    this.end = end;
  }

  /**
   * Custom iterator method for NoteSpan, using NoteIterator.
   * @return Instance of NoteIterator for NoteSpan.
   */
  @Override
  public Iterator<Note> iterator() {
    return new NoteIterator(this.begin, this.end);
  }
}
