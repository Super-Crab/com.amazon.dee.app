package com.drew.metadata.pcx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
/* loaded from: classes2.dex */
public class PcxReader {
    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        sequentialReader.setMotorolaByteOrder(false);
        PcxDirectory pcxDirectory = new PcxDirectory();
        metadata.addDirectory(pcxDirectory);
        try {
            if (sequentialReader.getInt8() != 10) {
                throw new ImageProcessingException("Invalid PCX identifier byte");
            }
            pcxDirectory.setInt(1, sequentialReader.getInt8());
            if (sequentialReader.getInt8() != 1) {
                throw new ImageProcessingException("Invalid PCX encoding byte");
            }
            pcxDirectory.setInt(2, sequentialReader.getUInt8());
            pcxDirectory.setInt(3, sequentialReader.getUInt16());
            pcxDirectory.setInt(4, sequentialReader.getUInt16());
            pcxDirectory.setInt(5, sequentialReader.getUInt16());
            pcxDirectory.setInt(6, sequentialReader.getUInt16());
            pcxDirectory.setInt(7, sequentialReader.getUInt16());
            pcxDirectory.setInt(8, sequentialReader.getUInt16());
            pcxDirectory.setByteArray(9, sequentialReader.getBytes(48));
            sequentialReader.skip(1L);
            pcxDirectory.setInt(10, sequentialReader.getUInt8());
            pcxDirectory.setInt(11, sequentialReader.getUInt16());
            int uInt16 = sequentialReader.getUInt16();
            if (uInt16 != 0) {
                pcxDirectory.setInt(12, uInt16);
            }
            int uInt162 = sequentialReader.getUInt16();
            if (uInt162 != 0) {
                pcxDirectory.setInt(13, uInt162);
            }
            int uInt163 = sequentialReader.getUInt16();
            if (uInt163 == 0) {
                return;
            }
            pcxDirectory.setInt(14, uInt163);
        } catch (Exception e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception reading PCX file metadata: ");
            outline107.append(e.getMessage());
            pcxDirectory.addError(outline107.toString());
        }
    }
}
