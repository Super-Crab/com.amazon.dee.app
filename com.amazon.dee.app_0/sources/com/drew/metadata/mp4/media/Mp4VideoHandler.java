package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.TimeToSampleBox;
import com.drew.metadata.mp4.boxes.VideoMediaHeaderBox;
import com.drew.metadata.mp4.boxes.VisualSampleEntry;
import java.io.IOException;
/* loaded from: classes2.dex */
public class Mp4VideoHandler extends Mp4MediaHandler<Mp4VideoDirectory> {
    public Mp4VideoHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.mp4.Mp4Handler
    @NotNull
    /* renamed from: getDirectory  reason: collision with other method in class */
    public Mp4VideoDirectory mo6822getDirectory() {
        return new Mp4VideoDirectory();
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected String getMediaInformation() {
        return "vmhd";
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    public void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new VideoMediaHeaderBox(sequentialReader, box).addMetadata((Mp4VideoDirectory) this.directory);
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    public void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new VisualSampleEntry(sequentialReader, box).addMetadata((Mp4VideoDirectory) this.directory);
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    public void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new TimeToSampleBox(sequentialReader, box).addMetadata((Mp4VideoDirectory) this.directory);
    }
}
