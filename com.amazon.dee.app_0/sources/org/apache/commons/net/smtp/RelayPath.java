package org.apache.commons.net.smtp;

import java.util.Enumeration;
import java.util.Vector;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes4.dex */
public final class RelayPath {
    String _emailAddress;
    Vector _path = new Vector();

    public RelayPath(String str) {
        this._emailAddress = str;
    }

    public void addRelay(String str) {
        this._path.addElement(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Typography.less);
        Enumeration elements = this._path.elements();
        if (elements.hasMoreElements()) {
            stringBuffer.append('@');
            stringBuffer.append((String) elements.nextElement());
            while (elements.hasMoreElements()) {
                stringBuffer.append(",@");
                stringBuffer.append((String) elements.nextElement());
            }
            stringBuffer.append(JsonReaderKt.COLON);
        }
        stringBuffer.append(this._emailAddress);
        stringBuffer.append(Typography.greater);
        return stringBuffer.toString();
    }
}
