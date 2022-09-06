package com.sun.mail.imap.protocol;

import com.sun.mail.iap.Protocol;
import com.sun.mail.iap.ProtocolException;
import com.sun.mail.iap.Response;
import com.sun.mail.util.ASCIIUtility;
import java.io.IOException;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class IMAPResponse extends Response {
    private String key;
    private int number;

    public IMAPResponse(Protocol protocol) throws IOException, ProtocolException {
        super(protocol);
        init();
    }

    private void init() throws IOException, ProtocolException {
        if (!isUnTagged() || isOK() || isNO() || isBAD() || isBYE()) {
            return;
        }
        this.key = readAtom();
        try {
            this.number = Integer.parseInt(this.key);
            this.key = readAtom();
        } catch (NumberFormatException unused) {
        }
    }

    public String getKey() {
        return this.key;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean keyEquals(String str) {
        String str2 = this.key;
        return str2 != null && str2.equalsIgnoreCase(str);
    }

    public String[] readSimpleList() {
        byte[] bArr;
        int i;
        skipSpaces();
        byte[] bArr2 = this.buffer;
        int i2 = this.index;
        if (bArr2[i2] != 40) {
            return null;
        }
        this.index = i2 + 1;
        ArrayList arrayList = new ArrayList();
        int i3 = this.index;
        while (true) {
            bArr = this.buffer;
            i = this.index;
            if (bArr[i] == 41) {
                break;
            }
            if (bArr[i] == 32) {
                arrayList.add(ASCIIUtility.toString(bArr, i3, i));
                i3 = this.index + 1;
            }
            this.index++;
        }
        if (i > i3) {
            arrayList.add(ASCIIUtility.toString(bArr, i3, i));
        }
        this.index++;
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[size]);
    }

    public IMAPResponse(IMAPResponse iMAPResponse) {
        super(iMAPResponse);
        this.key = iMAPResponse.key;
        this.number = iMAPResponse.number;
    }

    public IMAPResponse(String str) throws IOException, ProtocolException {
        super(str);
        init();
    }
}
