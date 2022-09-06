package com.sun.mail.imap.protocol;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.iap.ParsingException;
import com.sun.mail.iap.Response;
import java.util.ArrayList;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ENVELOPE.java */
/* loaded from: classes3.dex */
public class IMAPAddress extends InternetAddress {
    private static final long serialVersionUID = -3835822029483122232L;
    private boolean group;
    private InternetAddress[] grouplist;
    private String groupname;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMAPAddress(Response response) throws ParsingException {
        this.group = false;
        response.skipSpaces();
        if (response.readByte() == 40) {
            this.encodedPersonal = response.readString();
            response.readString();
            String readString = response.readString();
            String readString2 = response.readString();
            response.skipSpaces();
            if (response.readByte() != 41) {
                throw new ParsingException("ADDRESS parse error");
            }
            if (readString2 == null) {
                this.group = true;
                this.groupname = readString;
                if (this.groupname == null) {
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.groupname);
                stringBuffer.append(JsonReaderKt.COLON);
                ArrayList arrayList = new ArrayList();
                while (response.peekByte() != 41) {
                    IMAPAddress iMAPAddress = new IMAPAddress(response);
                    if (iMAPAddress.isEndOfGroup()) {
                        break;
                    }
                    if (arrayList.size() != 0) {
                        stringBuffer.append(JsonReaderKt.COMMA);
                    }
                    stringBuffer.append(iMAPAddress.toString());
                    arrayList.add(iMAPAddress);
                }
                stringBuffer.append(';');
                this.address = stringBuffer.toString();
                this.grouplist = (InternetAddress[]) arrayList.toArray(new IMAPAddress[arrayList.size()]);
                return;
            } else if (readString != null && readString.length() != 0) {
                if (readString2.length() == 0) {
                    this.address = readString;
                    return;
                } else {
                    this.address = GeneratedOutlineSupport1.outline75(readString, "@", readString2);
                    return;
                }
            } else {
                this.address = readString2;
                return;
            }
        }
        throw new ParsingException("ADDRESS parse error");
    }

    @Override // javax.mail.internet.InternetAddress
    public InternetAddress[] getGroup(boolean z) throws AddressException {
        InternetAddress[] internetAddressArr = this.grouplist;
        if (internetAddressArr == null) {
            return null;
        }
        return (InternetAddress[]) internetAddressArr.clone();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEndOfGroup() {
        return this.group && this.groupname == null;
    }

    @Override // javax.mail.internet.InternetAddress
    public boolean isGroup() {
        return this.group;
    }
}
