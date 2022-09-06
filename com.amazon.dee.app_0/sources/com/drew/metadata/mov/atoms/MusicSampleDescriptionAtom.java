package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeMusicDirectory;
import java.io.IOException;
/* loaded from: classes2.dex */
public class MusicSampleDescriptionAtom extends SampleDescriptionAtom<MusicSampleDescription> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class MusicSampleDescription extends SampleDescription {
        long flags;

        public MusicSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.flags = sequentialReader.getUInt32();
        }
    }

    public MusicSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    public void addMetadata(QuickTimeMusicDirectory quickTimeMusicDirectory) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.drew.metadata.mov.atoms.SampleDescriptionAtom
    /* renamed from: getSampleDescription */
    public MusicSampleDescription mo6811getSampleDescription(SequentialReader sequentialReader) throws IOException {
        return new MusicSampleDescription(sequentialReader);
    }
}
