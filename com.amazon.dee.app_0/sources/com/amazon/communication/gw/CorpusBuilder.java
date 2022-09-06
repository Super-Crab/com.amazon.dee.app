package com.amazon.communication.gw;

import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class CorpusBuilder {
    public static final int BYTES_TO_SIGN_OVERHEAD = 9;
    public static final byte DELIMETER = 59;

    private CorpusBuilder() {
    }

    public static byte[] build(String str, long j) {
        byte[] bytes = str.getBytes();
        byte[] bArr = new byte[bytes.length + 9];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.put(bytes);
        wrap.put(DELIMETER);
        wrap.putLong(j);
        return bArr;
    }
}
