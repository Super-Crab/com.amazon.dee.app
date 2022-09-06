package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.Mp4HandlerFactory;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class TimeToSampleBox extends FullBox {
    ArrayList<EntryCount> entries;
    long entryCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class EntryCount {
        long sampleCount;
        long sampleDelta;

        public EntryCount(long j, long j2) {
            this.sampleCount = j;
            this.sampleDelta = j2;
        }
    }

    public TimeToSampleBox(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.entryCount = sequentialReader.getUInt32();
        this.entries = new ArrayList<>();
        for (int i = 0; i < this.entryCount; i++) {
            this.entries.add(new EntryCount(sequentialReader.getUInt32(), sequentialReader.getUInt32()));
        }
    }

    public void addMetadata(Mp4SoundDirectory mp4SoundDirectory) {
        mp4SoundDirectory.setDouble(104, Mp4HandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue());
    }

    public void addMetadata(Mp4VideoDirectory mp4VideoDirectory) {
        mp4VideoDirectory.setFloat(114, ((float) Mp4HandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue()) / ((float) this.entries.get(0).sampleDelta));
    }
}
