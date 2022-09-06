package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeHandlerFactory;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class TimeToSampleAtom extends FullAtom {
    ArrayList<Entry> entries;
    long numberOfEntries;
    long sampleCount;
    long sampleDuration;

    /* loaded from: classes2.dex */
    class Entry {
        long sampleCount;
        long sampleDuration;

        public Entry(SequentialReader sequentialReader) throws IOException {
            this.sampleCount = sequentialReader.getUInt32();
            this.sampleDuration = sequentialReader.getUInt32();
        }
    }

    public TimeToSampleAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.numberOfEntries = sequentialReader.getUInt32();
        this.entries = new ArrayList<>();
        for (int i = 0; i < this.numberOfEntries; i++) {
            this.entries.add(new Entry(sequentialReader));
        }
        this.sampleCount = sequentialReader.getUInt32();
        this.sampleDuration = sequentialReader.getUInt32();
    }

    public void addMetadata(QuickTimeVideoDirectory quickTimeVideoDirectory) {
        quickTimeVideoDirectory.setFloat(14, ((float) QuickTimeHandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue()) / ((float) this.sampleDuration));
    }
}
