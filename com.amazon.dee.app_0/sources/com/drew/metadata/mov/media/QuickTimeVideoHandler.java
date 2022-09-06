package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.TimeToSampleAtom;
import com.drew.metadata.mov.atoms.VideoInformationMediaHeaderAtom;
import com.drew.metadata.mov.atoms.VideoSampleDescriptionAtom;
import java.io.IOException;
/* loaded from: classes2.dex */
public class QuickTimeVideoHandler extends QuickTimeMediaHandler<QuickTimeVideoDirectory> {
    public QuickTimeVideoHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    @NotNull
    /* renamed from: getDirectory  reason: collision with other method in class */
    public QuickTimeVideoDirectory mo6817getDirectory() {
        return new QuickTimeVideoDirectory();
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return "vmhd";
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new VideoInformationMediaHeaderAtom(sequentialReader, atom).addMetadata((QuickTimeVideoDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new VideoSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeVideoDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new TimeToSampleAtom(sequentialReader, atom).addMetadata((QuickTimeVideoDirectory) this.directory);
    }
}
