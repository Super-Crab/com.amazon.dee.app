package com.sun.mail.imap.protocol;

import com.sun.mail.iap.ByteArray;
import com.sun.mail.iap.ParsingException;
import java.io.ByteArrayInputStream;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes3.dex */
public class BODY implements Item {
    static final char[] name = {'B', 'O', 'D', 'Y'};
    private final ByteArray data;
    private final boolean isHeader;
    private final int msgno;
    private final int origin;
    private final String section;

    public BODY(FetchResponse fetchResponse) throws ParsingException {
        this.msgno = fetchResponse.getNumber();
        fetchResponse.skipSpaces();
        if (fetchResponse.readByte() == 91) {
            this.section = fetchResponse.readString(JsonReaderKt.END_LIST);
            if (fetchResponse.readByte() == 93) {
                this.isHeader = this.section.regionMatches(true, 0, "HEADER", 0, 6);
                if (fetchResponse.readByte() == 60) {
                    this.origin = fetchResponse.readNumber();
                    fetchResponse.skip(1);
                } else {
                    this.origin = 0;
                }
                this.data = fetchResponse.readByteArray();
                return;
            }
            throw new ParsingException("BODY parse error: missing ``]'' at section end");
        }
        throw new ParsingException("BODY parse error: missing ``['' at section start");
    }

    public ByteArray getByteArray() {
        return this.data;
    }

    public ByteArrayInputStream getByteArrayInputStream() {
        ByteArray byteArray = this.data;
        if (byteArray != null) {
            return byteArray.toByteArrayInputStream();
        }
        return null;
    }

    public String getSection() {
        return this.section;
    }

    public boolean isHeader() {
        return this.isHeader;
    }
}
