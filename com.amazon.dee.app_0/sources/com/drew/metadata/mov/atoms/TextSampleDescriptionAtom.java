package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import java.io.IOException;
/* loaded from: classes2.dex */
public class TextSampleDescriptionAtom extends SampleDescriptionAtom<TextSampleDescription> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class TextSampleDescription extends SampleDescription {
        int[] backgroundColor;
        long defaultTextBox;
        int displayFlags;
        int fontFace;
        int fontNumber;
        int[] foregroundColor;
        int textJustification;
        String textName;

        public TextSampleDescription(SequentialReader sequentialReader) throws IOException {
            super(sequentialReader);
            this.displayFlags = sequentialReader.getInt32();
            this.textJustification = sequentialReader.getInt32();
            this.backgroundColor = new int[]{sequentialReader.getUInt16(), sequentialReader.getUInt16(), sequentialReader.getUInt16()};
            this.defaultTextBox = sequentialReader.getInt64();
            sequentialReader.skip(8L);
            this.fontNumber = sequentialReader.getUInt16();
            this.fontFace = sequentialReader.getUInt16();
            sequentialReader.skip(1L);
            sequentialReader.skip(2L);
            this.foregroundColor = new int[]{sequentialReader.getUInt16(), sequentialReader.getUInt16(), sequentialReader.getUInt16()};
            this.textName = sequentialReader.getString(sequentialReader.getUInt8());
        }
    }

    public TextSampleDescriptionAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void addMetadata(com.drew.metadata.mov.media.QuickTimeTextDirectory r12) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.mov.atoms.TextSampleDescriptionAtom.addMetadata(com.drew.metadata.mov.media.QuickTimeTextDirectory):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.drew.metadata.mov.atoms.SampleDescriptionAtom
    /* renamed from: getSampleDescription  reason: collision with other method in class */
    public TextSampleDescription mo6811getSampleDescription(SequentialReader sequentialReader) throws IOException {
        return new TextSampleDescription(sequentialReader);
    }
}
