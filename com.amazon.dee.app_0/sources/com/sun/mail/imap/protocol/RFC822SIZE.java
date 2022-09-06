package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ParsingException;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes3.dex */
public class RFC822SIZE implements Item {
    static final char[] name = {Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'F', 'C', '8', '2', '2', '.', 'S', 'I', Matrix.MATRIX_TYPE_ZERO, 'E'};
    public int msgno;
    public int size;

    public RFC822SIZE(FetchResponse fetchResponse) throws ParsingException {
        this.msgno = fetchResponse.getNumber();
        fetchResponse.skipSpaces();
        this.size = fetchResponse.readNumber();
    }
}
