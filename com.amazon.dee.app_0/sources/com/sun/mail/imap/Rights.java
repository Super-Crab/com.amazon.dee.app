package com.sun.mail.imap;

import java.util.Vector;
/* loaded from: classes3.dex */
public class Rights implements Cloneable {
    private boolean[] rights;

    /* loaded from: classes3.dex */
    public static final class Right {
        char right;
        private static Right[] cache = new Right[128];
        public static final Right LOOKUP = getInstance('l');
        public static final Right READ = getInstance('r');
        public static final Right KEEP_SEEN = getInstance('s');
        public static final Right WRITE = getInstance('w');
        public static final Right INSERT = getInstance('i');
        public static final Right POST = getInstance('p');
        public static final Right CREATE = getInstance('c');
        public static final Right DELETE = getInstance('d');
        public static final Right ADMINISTER = getInstance('a');

        private Right(char c) {
            if (c < 128) {
                this.right = c;
                return;
            }
            throw new IllegalArgumentException("Right must be ASCII");
        }

        public static synchronized Right getInstance(char c) {
            Right right;
            synchronized (Right.class) {
                if (c < 128) {
                    if (cache[c] == null) {
                        cache[c] = new Right(c);
                    }
                    right = cache[c];
                } else {
                    throw new IllegalArgumentException("Right must be ASCII");
                }
            }
            return right;
        }

        public String toString() {
            return String.valueOf(this.right);
        }
    }

    public Rights() {
        this.rights = new boolean[128];
    }

    public void add(Right right) {
        this.rights[right.right] = true;
    }

    public Object clone() {
        try {
            Rights rights = (Rights) super.clone();
            try {
                rights.rights = new boolean[128];
                System.arraycopy(this.rights, 0, rights.rights, 0, this.rights.length);
                return rights;
            } catch (CloneNotSupportedException unused) {
                return rights;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public boolean contains(Right right) {
        return this.rights[right.right];
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rights)) {
            return false;
        }
        Rights rights = (Rights) obj;
        int i = 0;
        while (true) {
            boolean[] zArr = rights.rights;
            if (i >= zArr.length) {
                return true;
            }
            if (zArr[i] != this.rights[i]) {
                return false;
            }
            i++;
        }
    }

    public Right[] getRights() {
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            boolean[] zArr = this.rights;
            if (i < zArr.length) {
                if (zArr[i]) {
                    vector.addElement(Right.getInstance((char) i));
                }
                i++;
            } else {
                Right[] rightArr = new Right[vector.size()];
                vector.copyInto(rightArr);
                return rightArr;
            }
        }
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean[] zArr = this.rights;
            if (i < zArr.length) {
                if (zArr[i]) {
                    i2++;
                }
                i++;
            } else {
                return i2;
            }
        }
    }

    public void remove(Right right) {
        this.rights[right.right] = false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            boolean[] zArr = this.rights;
            if (i < zArr.length) {
                if (zArr[i]) {
                    stringBuffer.append((char) i);
                }
                i++;
            } else {
                return stringBuffer.toString();
            }
        }
    }

    public void add(Rights rights) {
        int i = 0;
        while (true) {
            boolean[] zArr = rights.rights;
            if (i < zArr.length) {
                if (zArr[i]) {
                    this.rights[i] = true;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public boolean contains(Rights rights) {
        int i = 0;
        while (true) {
            boolean[] zArr = rights.rights;
            if (i < zArr.length) {
                if (zArr[i] && !this.rights[i]) {
                    return false;
                }
                i++;
            } else {
                return true;
            }
        }
    }

    public void remove(Rights rights) {
        int i = 0;
        while (true) {
            boolean[] zArr = rights.rights;
            if (i < zArr.length) {
                if (zArr[i]) {
                    this.rights[i] = false;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public Rights(Rights rights) {
        this.rights = new boolean[128];
        boolean[] zArr = rights.rights;
        boolean[] zArr2 = this.rights;
        System.arraycopy(zArr, 0, zArr2, 0, zArr2.length);
    }

    public Rights(String str) {
        this.rights = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            add(Right.getInstance(str.charAt(i)));
        }
    }

    public Rights(Right right) {
        this.rights = new boolean[128];
        this.rights[right.right] = true;
    }
}
