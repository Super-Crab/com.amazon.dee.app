package com.amazon.alexa;

import com.amazon.alexa.utils.validation.Preconditions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import javax.inject.Singleton;
/* compiled from: Id3HeaderParser.java */
@Singleton
/* loaded from: classes.dex */
public class DJX {
    public mYa zZm(byte[] bArr) throws IllegalArgumentException {
        Preconditions.isFalse(bArr.length < 10, "Invalid Id3 frame length");
        byte[] bArr2 = new byte[3];
        byte[] bArr3 = new byte[2];
        byte[] bArr4 = new byte[1];
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        parsableByteArray.readBytes(bArr2, 0, 3);
        parsableByteArray.readBytes(bArr3, 0, 2);
        parsableByteArray.readBytes(bArr4, 0, 1);
        return new mYa(bArr2, bArr3, bArr4, parsableByteArray.readSynchSafeInt());
    }
}
