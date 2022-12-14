package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.QuickTimeDictionary;
import com.drew.metadata.mov.media.QuickTimeSoundDirectory;
import java.io.IOException;
/* loaded from: classes2.dex */
public class SoundSampleDescriptionAtom extends SampleDescriptionAtom<SoundSampleDescription> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class SoundSampleDescription extends SampleDescription {
        int compressionID;
        int numberOfChannels;
        int packetSize;
        int revisionLevel;
        long sampleRate;
        int sampleSize;
        int vendor;
        int version;

        public SoundSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.version = sequentialReader.getUInt16();
            this.revisionLevel = sequentialReader.getUInt16();
            this.vendor = sequentialReader.getInt32();
            this.numberOfChannels = sequentialReader.getUInt16();
            this.sampleSize = sequentialReader.getUInt16();
            this.compressionID = sequentialReader.getUInt16();
            this.packetSize = sequentialReader.getUInt16();
            this.sampleRate = sequentialReader.getUInt32();
        }
    }

    public SoundSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    public void addMetadata(QuickTimeSoundDirectory quickTimeSoundDirectory) {
        SoundSampleDescription soundSampleDescription = (SoundSampleDescription) this.sampleDescriptions.get(0);
        quickTimeSoundDirectory.setString(769, QuickTimeDictionary.lookup(769, soundSampleDescription.dataFormat));
        quickTimeSoundDirectory.setInt(770, soundSampleDescription.numberOfChannels);
        quickTimeSoundDirectory.setInt(771, soundSampleDescription.sampleSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.drew.metadata.mov.atoms.SampleDescriptionAtom
    /* renamed from: getSampleDescription  reason: collision with other method in class */
    public SoundSampleDescription mo6811getSampleDescription(SequentialReader sequentialReader) throws IOException {
        return new SoundSampleDescription(sequentialReader);
    }
}
