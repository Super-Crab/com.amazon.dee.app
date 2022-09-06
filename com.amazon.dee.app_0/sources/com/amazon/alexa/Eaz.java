package com.amazon.alexa;

import com.amazon.alexa.utils.validation.Preconditions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import javax.inject.Singleton;
/* compiled from: Id3FrameHeaderParser.java */
@Singleton
/* loaded from: classes.dex */
public class Eaz {
    public mFA zZm(byte[] bArr) throws IllegalArgumentException {
        Preconditions.isFalse(bArr.length < 10, "Invalid Id3 frame length");
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[2];
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.readBytes(bArr2, 0, 4);
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        parsableByteArray.readBytes(bArr3, 0, 2);
        return new mFA(bArr2, readSynchSafeInt, bArr3);
    }
}
