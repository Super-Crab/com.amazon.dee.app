package com.drew.metadata.mp4.boxes;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mp4.media.Mp4VideoDirectory;
import java.io.IOException;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes2.dex */
public class VisualSampleEntry extends SampleEntry {
    String compressorname;
    int depth;
    int frameCount;
    int height;
    long horizresolution;
    int revisionLevel;
    int spatialQuality;
    int temporalQuality;
    String vendor;
    int version;
    long vertresolution;
    int width;

    public VisualSampleEntry(SequentialReader sequentialReader, Box box) throws IOException {
        super(sequentialReader, box);
        this.version = sequentialReader.getInt16();
        this.revisionLevel = sequentialReader.getInt16();
        this.vendor = sequentialReader.getString(4);
        this.temporalQuality = sequentialReader.getInt32();
        this.spatialQuality = sequentialReader.getInt32();
        this.width = sequentialReader.getUInt16();
        this.height = sequentialReader.getUInt16();
        this.horizresolution = sequentialReader.getUInt32();
        this.vertresolution = sequentialReader.getUInt32();
        sequentialReader.skip(4L);
        this.frameCount = sequentialReader.getUInt16();
        this.compressorname = sequentialReader.getString(32);
        this.depth = sequentialReader.getUInt16();
        sequentialReader.skip(2L);
    }

    public void addMetadata(Mp4VideoDirectory mp4VideoDirectory) {
        mp4VideoDirectory.setInt(104, this.width);
        mp4VideoDirectory.setInt(105, this.height);
        mp4VideoDirectory.setString(110, this.compressorname.trim());
        mp4VideoDirectory.setInt(109, this.depth);
        long j = this.horizresolution;
        mp4VideoDirectory.setDouble(106, ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) / Math.pow(2.0d, 4.0d)) + ((j & (-65536)) >> 16));
        long j2 = this.vertresolution;
        mp4VideoDirectory.setDouble(107, ((j2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) / Math.pow(2.0d, 4.0d)) + (((-65536) & j2) >> 16));
    }
}
