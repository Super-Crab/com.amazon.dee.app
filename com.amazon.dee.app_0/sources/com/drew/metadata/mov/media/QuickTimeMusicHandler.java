package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.MusicSampleDescriptionAtom;
import java.io.IOException;
/* loaded from: classes2.dex */
public class QuickTimeMusicHandler extends QuickTimeMediaHandler<QuickTimeMusicDirectory> {
    public QuickTimeMusicHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    @NotNull
    /* renamed from: getDirectory  reason: collision with other method in class */
    public QuickTimeMusicDirectory mo6817getDirectory() {
        return (QuickTimeMusicDirectory) this.directory;
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return null;
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new MusicSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeMusicDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
    }
}
