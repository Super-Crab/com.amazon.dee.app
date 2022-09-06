package com.drew.metadata.mov.atoms;

import com.drew.lang.SequentialReader;
import com.drew.metadata.mov.media.QuickTimeTimecodeDirectory;
import java.io.IOException;
/* loaded from: classes2.dex */
public class TimecodeInformationMediaAtom extends FullAtom {
    int[] backgroundColor;
    String fontName;
    int[] textColor;
    int textFace;
    int textFont;
    int textSize;

    public TimecodeInformationMediaAtom(SequentialReader sequentialReader, Atom atom) throws IOException {
        super(sequentialReader, atom);
        this.textFont = sequentialReader.getInt16();
        this.textFace = sequentialReader.getInt16();
        this.textSize = sequentialReader.getInt16();
        sequentialReader.skip(2L);
        this.textColor = new int[]{sequentialReader.getUInt16(), sequentialReader.getUInt16(), sequentialReader.getUInt16()};
        this.backgroundColor = new int[]{sequentialReader.getUInt16(), sequentialReader.getUInt16(), sequentialReader.getUInt16()};
        this.fontName = sequentialReader.getString(sequentialReader.getUInt8());
    }

    public void addMetadata(QuickTimeTimecodeDirectory quickTimeTimecodeDirectory) {
        String str;
        quickTimeTimecodeDirectory.setInt(5, this.textFont);
        int i = this.textFace;
        if (i == 1) {
            str = "Bold";
        } else if (i == 2) {
            str = "Italic";
        } else if (i == 4) {
            str = "Underline";
        } else if (i == 8) {
            str = "Outline";
        } else if (i == 16) {
            str = "Shadow";
        } else if (i != 32) {
            if (i == 64) {
                str = "Extend";
            }
            quickTimeTimecodeDirectory.setInt(7, this.textSize);
            quickTimeTimecodeDirectory.setIntArray(8, this.textColor);
            quickTimeTimecodeDirectory.setIntArray(9, this.backgroundColor);
            quickTimeTimecodeDirectory.setString(10, this.fontName);
        } else {
            str = "Condense";
        }
        quickTimeTimecodeDirectory.setString(6, str);
        quickTimeTimecodeDirectory.setInt(7, this.textSize);
        quickTimeTimecodeDirectory.setIntArray(8, this.textColor);
        quickTimeTimecodeDirectory.setIntArray(9, this.backgroundColor);
        quickTimeTimecodeDirectory.setString(10, this.fontName);
    }
}
