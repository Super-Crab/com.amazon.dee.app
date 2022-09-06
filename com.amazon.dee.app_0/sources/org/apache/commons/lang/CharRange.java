package org.apache.commons.lang;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public final class CharRange implements Serializable {
    private static final long serialVersionUID = 8270183163158333422L;
    private final char end;
    private transient String iToString;
    private final boolean negated;
    private final char start;

    /* loaded from: classes4.dex */
    private static class CharacterIterator implements Iterator {
        private char current;
        private boolean hasNext;
        private final CharRange range;

        private void prepareNext() {
            if (!this.range.negated) {
                if (this.current < this.range.end) {
                    this.current = (char) (this.current + 1);
                    return;
                } else {
                    this.hasNext = false;
                    return;
                }
            }
            char c = this.current;
            if (c != 65535) {
                if (c + 1 == this.range.start) {
                    if (this.range.end != 65535) {
                        this.current = (char) (this.range.end + 1);
                        return;
                    } else {
                        this.hasNext = false;
                        return;
                    }
                }
                this.current = (char) (this.current + 1);
                return;
            }
            this.hasNext = false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (this.hasNext) {
                char c = this.current;
                prepareNext();
                return new Character(c);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private CharacterIterator(CharRange charRange) {
            this.range = charRange;
            this.hasNext = true;
            if (this.range.negated) {
                if (this.range.start == 0) {
                    if (this.range.end != 65535) {
                        this.current = (char) (this.range.end + 1);
                        return;
                    } else {
                        this.hasNext = false;
                        return;
                    }
                }
                this.current = (char) 0;
                return;
            }
            this.current = this.range.start;
        }
    }

    public CharRange(char c) {
        this(c, c, false);
    }

    public static CharRange is(char c) {
        return new CharRange(c, c, false);
    }

    public static CharRange isIn(char c, char c2) {
        return new CharRange(c, c2, false);
    }

    public static CharRange isNot(char c) {
        return new CharRange(c, c, true);
    }

    public static CharRange isNotIn(char c, char c2) {
        return new CharRange(c, c2, true);
    }

    public boolean contains(char c) {
        return (c >= this.start && c <= this.end) != this.negated;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharRange)) {
            return false;
        }
        CharRange charRange = (CharRange) obj;
        return this.start == charRange.start && this.end == charRange.end && this.negated == charRange.negated;
    }

    public char getEnd() {
        return this.end;
    }

    public char getStart() {
        return this.start;
    }

    public int hashCode() {
        return (this.end * 7) + this.start + 'S' + (this.negated ? 1 : 0);
    }

    public boolean isNegated() {
        return this.negated;
    }

    public Iterator iterator() {
        return new CharacterIterator();
    }

    public String toString() {
        if (this.iToString == null) {
            StrBuilder strBuilder = new StrBuilder(4);
            if (isNegated()) {
                strBuilder.append('^');
            }
            strBuilder.append(this.start);
            if (this.start != this.end) {
                strBuilder.append('-');
                strBuilder.append(this.end);
            }
            this.iToString = strBuilder.toString();
        }
        return this.iToString;
    }

    public CharRange(char c, boolean z) {
        this(c, c, z);
    }

    public boolean contains(CharRange charRange) {
        if (charRange != null) {
            return this.negated ? charRange.negated ? this.start >= charRange.start && this.end <= charRange.end : charRange.end < this.start || charRange.start > this.end : charRange.negated ? this.start == 0 && this.end == 65535 : this.start <= charRange.start && this.end >= charRange.end;
        }
        throw new IllegalArgumentException("The Range must not be null");
    }

    public CharRange(char c, char c2) {
        this(c, c2, false);
    }

    public CharRange(char c, char c2, boolean z) {
        if (c > c2) {
            c2 = c;
            c = c2;
        }
        this.start = c;
        this.end = c2;
        this.negated = z;
    }
}
