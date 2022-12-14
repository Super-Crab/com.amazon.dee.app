package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4SoundDirectory;
import java.io.IOException;
/* loaded from: classes2.dex */
public class AudioSampleEntry extends SampleEntry {
    int channelcount;
    long samplerate;
    int samplesize;

    public AudioSampleEntry(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        sequentialReader.skip(8L);
        this.channelcount = sequentialReader.getUInt16();
        this.samplesize = sequentialReader.getInt16();
        sequentialReader.skip(2L);
        sequentialReader.skip(2L);
        this.samplerate = sequentialReader.getUInt32();
    }

    public void addMetadata(Mp4SoundDirectory mp4SoundDirectory) {
        mp4SoundDirectory.setInt(102, this.channelcount);
        mp4SoundDirectory.setInt(103, this.samplesize);
        mp4SoundDirectory.setLong(104, this.samplerate);
    }
}
