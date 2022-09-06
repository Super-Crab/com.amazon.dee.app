package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class UID implements Item {
    static final char[] name = {Matrix.MATRIX_TYPE_RANDOM_UT, 'I', 'D'};
    public int seqnum;
    public long uid;

    public UID(FetchResponse fetchResponse) throws ParsingException {
        this.seqnum = fetchResponse.getNumber();
        fetchResponse.skipSpaces();
        this.uid = fetchResponse.readLong();
    }
}
