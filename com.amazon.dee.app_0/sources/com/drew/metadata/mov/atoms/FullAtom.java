package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;
/* loaded from: classes2.dex */
public class FullAtom extends Atom {
    byte[] flags;
    int version;

    public FullAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(atom);
        this.version = sequentialReader.getUInt8();
        this.flags = sequentialReader.getBytes(3);
    }
}
