package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDictionary;
import com.drew.metadata.mov.media.QuickTimeVideoDirectory;
import java.io.IOException;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes2.dex */
public class VideoSampleDescriptionAtom extends SampleDescriptionAtom<VideoSampleDescription> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class VideoSampleDescription extends SampleDescription {
        int colorTableID;
        String compressorName;
        long dataSize;
        int depth;
        int frameCount;
        int height;
        long horizontalResolution;
        int revisionLevel;
        long spatialQuality;
        long temporalQuality;
        String vendor;
        int version;
        long verticalResolution;
        int width;

        public VideoSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.version = sequentialReader.getUInt16();
            this.revisionLevel = sequentialReader.getUInt16();
            this.vendor = sequentialReader.getString(4);
            this.temporalQuality = sequentialReader.getUInt32();
            this.spatialQuality = sequentialReader.getUInt32();
            this.width = sequentialReader.getUInt16();
            this.height = sequentialReader.getUInt16();
            this.horizontalResolution = sequentialReader.getUInt32();
            this.verticalResolution = sequentialReader.getUInt32();
            this.dataSize = sequentialReader.getUInt32();
            this.frameCount = sequentialReader.getUInt16();
            this.compressorName = sequentialReader.getString(sequentialReader.getUInt8());
            this.depth = sequentialReader.getUInt16();
            this.colorTableID = sequentialReader.getInt16();
        }
    }

    public VideoSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    public void addMetadata(QuickTimeVideoDirectory quickTimeVideoDirectory) {
        VideoSampleDescription videoSampleDescription = (VideoSampleDescription) this.sampleDescriptions.get(0);
        QuickTimeDictionary.setLookup(1, videoSampleDescription.vendor, quickTimeVideoDirectory);
        QuickTimeDictionary.setLookup(10, videoSampleDescription.dataFormat, quickTimeVideoDirectory);
        quickTimeVideoDirectory.setLong(2, videoSampleDescription.temporalQuality);
        quickTimeVideoDirectory.setLong(3, videoSampleDescription.spatialQuality);
        quickTimeVideoDirectory.setInt(4, videoSampleDescription.width);
        quickTimeVideoDirectory.setInt(5, videoSampleDescription.height);
        quickTimeVideoDirectory.setString(8, videoSampleDescription.compressorName.trim());
        quickTimeVideoDirectory.setInt(9, videoSampleDescription.depth);
        quickTimeVideoDirectory.setInt(13, videoSampleDescription.colorTableID);
        long j = videoSampleDescription.horizontalResolution;
        quickTimeVideoDirectory.setDouble(6, ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) / Math.pow(2.0d, 4.0d)) + ((j & (-65536)) >> 16));
        long j2 = videoSampleDescription.verticalResolution;
        quickTimeVideoDirectory.setDouble(7, ((j2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) / Math.pow(2.0d, 4.0d)) + ((j2 & (-65536)) >> 16));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.drew.metadata.mov.atoms.SampleDescriptionAtom
    /* renamed from: getSampleDescription  reason: collision with other method in class */
    public VideoSampleDescription mo6811getSampleDescription(SequentialReader sequentialReader) throws IOException {
        return new VideoSampleDescription(sequentialReader);
    }
}
