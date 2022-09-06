package com.drew.metadata.mp4.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mp4.Mp4MediaHandler;
import com.drew.metadata.mp4.boxes.Box;
import java.io.IOException;
/* loaded from: classes2.dex */
public class Mp4TextHandler extends Mp4MediaHandler<Mp4TextDirectory> {
    public Mp4TextHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.mp4.Mp4Handler
    @NotNull
    /* renamed from: getDirectory  reason: collision with other method in class */
    public Mp4TextDirectory mo6822getDirectory() {
        return (Mp4TextDirectory) this.directory;
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected String getMediaInformation() {
        return "text";
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }

    @Override // com.drew.metadata.mp4.Mp4MediaHandler
    protected void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Box box) throws IOException {
    }
}
