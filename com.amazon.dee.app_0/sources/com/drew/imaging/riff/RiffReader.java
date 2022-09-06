package com.drew.imaging.riff;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;
/* loaded from: classes2.dex */
public class RiffReader {
    public void processChunks(SequentialReader sequentialReader, int i, RiffHandler riffHandler) throws IOException {
        long j;
        while (sequentialReader.getPosition() < i) {
            String str = new String(sequentialReader.getBytes(4));
            int int32 = sequentialReader.getInt32();
            if (str.equals("LIST") || str.equals("RIFF")) {
                int i2 = int32 - 4;
                if (riffHandler.shouldAcceptList(new String(sequentialReader.getBytes(4)))) {
                    processChunks(sequentialReader, i2, riffHandler);
                } else {
                    j = i2;
                    sequentialReader.skip(j);
                }
            } else {
                if (riffHandler.shouldAcceptChunk(str)) {
                    riffHandler.processChunk(str, sequentialReader.getBytes(int32));
                } else {
                    sequentialReader.skip(int32);
                }
                if (int32 % 2 == 1) {
                    j = 1;
                    sequentialReader.skip(j);
                }
            }
        }
    }

    public void processRiff(@NotNull SequentialReader sequentialReader, @NotNull RiffHandler riffHandler) throws RiffProcessingException, IOException {
        sequentialReader.setMotorolaByteOrder(false);
        String string = sequentialReader.getString(4);
        if (string.equals("RIFF")) {
            int int32 = sequentialReader.getInt32() - 4;
            if (!riffHandler.shouldAcceptRiffIdentifier(sequentialReader.getString(4))) {
                return;
            }
            processChunks(sequentialReader, int32, riffHandler);
            return;
        }
        throw new RiffProcessingException(GeneratedOutlineSupport1.outline72("Invalid RIFF header: ", string));
    }
}
