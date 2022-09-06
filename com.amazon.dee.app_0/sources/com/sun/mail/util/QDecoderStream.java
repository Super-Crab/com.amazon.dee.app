package com.sun.mail.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes3.dex */
public class QDecoderStream extends QPDecoderStream {
    public QDecoderStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // com.sun.mail.util.QPDecoderStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = ((FilterInputStream) this).in.read();
        if (read == 95) {
            return 32;
        }
        if (read != 61) {
            return read;
        }
        this.ba[0] = (byte) ((FilterInputStream) this).in.read();
        this.ba[1] = (byte) ((FilterInputStream) this).in.read();
        try {
            return ASCIIUtility.parseInt(this.ba, 0, 2, 16);
        } catch (NumberFormatException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("QDecoder: Error in QP stream ");
            outline107.append(e.getMessage());
            throw new DecodingException(outline107.toString());
        }
    }
}
