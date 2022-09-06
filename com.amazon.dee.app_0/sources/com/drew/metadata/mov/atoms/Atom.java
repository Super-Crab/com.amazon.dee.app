package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;
/* loaded from: classes2.dex */
public class Atom {
    public long size;
    public String type;

    public Atom(SequentialReader sequentialReader) throws IOException {
        long j;
        this.size = sequentialReader.getUInt32();
        this.type = sequentialReader.getString(4);
        long j2 = this.size;
        if (j2 == 1) {
            j = sequentialReader.getInt64();
        } else if (j2 != 0) {
            return;
        } else {
            j = -1;
        }
        this.size = j;
    }

    public Atom(Atom atom) {
        this.size = atom.size;
        this.type = atom.type;
    }
}
