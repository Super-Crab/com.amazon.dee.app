package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.mov.atoms.SampleDescription;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public abstract class SampleDescriptionAtom<T extends SampleDescription> extends FullAtom {
    long numberOfEntries;
    ArrayList<T> sampleDescriptions;

    public SampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.numberOfEntries = sequentialReader.getUInt32();
        this.sampleDescriptions = new ArrayList<>((int) this.numberOfEntries);
        for (int i = 0; i < this.numberOfEntries; i++) {
            this.sampleDescriptions.add(mo6811getSampleDescription(sequentialReader));
        }
    }

    @Nullable
    /* renamed from: getSampleDescription */
    abstract T mo6811getSampleDescription(SequentialReader sequentialReader) throws IOException;
}
