package com.drew.metadata.ico;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.IOException;
/* loaded from: classes2.dex */
public class IcoReader {
    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        int i;
        int uInt16;
        sequentialReader.setMotorolaByteOrder(false);
        try {
            if (sequentialReader.getUInt16() != 0) {
                IcoDirectory icoDirectory = new IcoDirectory();
                icoDirectory.addError("Invalid header bytes");
                metadata.addDirectory(icoDirectory);
                return;
            }
            int uInt162 = sequentialReader.getUInt16();
            if (uInt162 != 1 && uInt162 != 2) {
                IcoDirectory icoDirectory2 = new IcoDirectory();
                icoDirectory2.addError("Invalid type " + uInt162 + " -- expecting 1 or 2");
                metadata.addDirectory(icoDirectory2);
                return;
            }
            int uInt163 = sequentialReader.getUInt16();
            if (uInt163 == 0) {
                IcoDirectory icoDirectory3 = new IcoDirectory();
                icoDirectory3.addError("Image count cannot be zero");
                metadata.addDirectory(icoDirectory3);
                return;
            }
            for (int i2 = 0; i2 < uInt163; i2++) {
                IcoDirectory icoDirectory4 = new IcoDirectory();
                try {
                    icoDirectory4.setInt(1, uInt162);
                    icoDirectory4.setInt(2, sequentialReader.getUInt8());
                    icoDirectory4.setInt(3, sequentialReader.getUInt8());
                    icoDirectory4.setInt(4, sequentialReader.getUInt8());
                    sequentialReader.getUInt8();
                    if (uInt162 == 1) {
                        icoDirectory4.setInt(5, sequentialReader.getUInt16());
                        i = 7;
                        uInt16 = sequentialReader.getUInt16();
                    } else {
                        icoDirectory4.setInt(6, sequentialReader.getUInt16());
                        i = 8;
                        uInt16 = sequentialReader.getUInt16();
                    }
                    icoDirectory4.setInt(i, uInt16);
                    icoDirectory4.setLong(9, sequentialReader.getUInt32());
                    icoDirectory4.setLong(10, sequentialReader.getUInt32());
                } catch (IOException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception reading ICO file metadata: ");
                    outline107.append(e.getMessage());
                    icoDirectory4.addError(outline107.toString());
                }
                metadata.addDirectory(icoDirectory4);
            }
        } catch (IOException e2) {
            IcoDirectory icoDirectory5 = new IcoDirectory();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Exception reading ICO file metadata: ");
            outline1072.append(e2.getMessage());
            icoDirectory5.addError(outline1072.toString());
            metadata.addDirectory(icoDirectory5);
        }
    }
}
