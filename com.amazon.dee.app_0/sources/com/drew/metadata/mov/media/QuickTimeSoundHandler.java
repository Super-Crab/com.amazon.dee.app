package com.drew.metadata.mov.media;

import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.QuickTimeHandlerFactory;
import com.drew.metadata.mov.QuickTimeMediaHandler;
import com.drew.metadata.mov.atoms.Atom;
import com.drew.metadata.mov.atoms.SoundInformationMediaHeaderAtom;
import com.drew.metadata.mov.atoms.SoundSampleDescriptionAtom;
import java.io.IOException;
/* loaded from: classes2.dex */
public class QuickTimeSoundHandler extends QuickTimeMediaHandler<QuickTimeSoundDirectory> {
    public QuickTimeSoundHandler(Metadata metadata) {
        super(metadata);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.drew.imaging.quicktime.QuickTimeHandler
    @NotNull
    /* renamed from: getDirectory  reason: collision with other method in class */
    public QuickTimeSoundDirectory mo6817getDirectory() {
        return new QuickTimeSoundDirectory();
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected String getMediaInformation() {
        return "smhd";
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processMediaInformation(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new SoundInformationMediaHeaderAtom(sequentialReader, atom).addMetadata((QuickTimeSoundDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    public void processSampleDescription(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        new SoundSampleDescriptionAtom(sequentialReader, atom).addMetadata((QuickTimeSoundDirectory) this.directory);
    }

    @Override // com.drew.metadata.mov.QuickTimeMediaHandler
    protected void processTimeToSample(@NotNull SequentialReader sequentialReader, @NotNull Atom atom) throws IOException {
        ((QuickTimeSoundDirectory) this.directory).setDouble(772, QuickTimeHandlerFactory.HANDLER_PARAM_TIME_SCALE.longValue());
    }
}
