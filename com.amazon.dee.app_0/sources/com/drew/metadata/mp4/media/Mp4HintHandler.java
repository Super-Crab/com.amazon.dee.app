package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4BoxTypes;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import com.drew.metadata.mp4.boxes.HintMediaHeaderBox;
import java.io.IOException;
/* loaded from: classes2.dex */
public class Mp4HintHandler extends Mp4MediaHandler<Mp4HintDirectory> {
    public Mp4HintHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.mp4.Mp4Handler
    @NotNull
    /* renamed from: getDirectory  reason: collision with other method in class */
    public Mp4HintDirectory mo6822getDirectory() {
        return new Mp4HintDirectory();
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected String getMediaInformation() {
        return Mp4BoxTypes.BOX_HINT_MEDIA_INFO;
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
        new HintMediaHeaderBox(sequentialReader, box).addMetadata((Mp4HintDirectory) this.directory);
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }
}
