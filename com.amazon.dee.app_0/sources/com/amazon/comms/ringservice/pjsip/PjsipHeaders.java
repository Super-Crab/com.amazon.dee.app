package com.amazon.comms.ringservice.pjsip;

import com.amazon.comms.calling.sipclient.SipHeaders;
import java.util.Iterator;
import java.util.Map;
import org.pjsip.pjsua2.SipHeader;
import org.pjsip.pjsua2.SipHeaderVector;
/* loaded from: classes12.dex */
public final class PjsipHeaders {
    public static SipHeaders transform(SipHeaderVector sipHeaderVector) {
        SipHeaders sipHeaders = new SipHeaders();
        int size = (int) sipHeaderVector.size();
        for (int i = 0; i < size; i++) {
            SipHeader sipHeader = sipHeaderVector.get(i);
            sipHeaders.put(sipHeader.getHName(), sipHeader.getHValue());
        }
        return sipHeaders;
    }

    public static SipHeaderVector transform(SipHeaders sipHeaders) {
        SipHeaderVector sipHeaderVector = new SipHeaderVector();
        Iterator<Map.Entry<String, String>> it2 = sipHeaders.iterator();
        while (it2.hasNext()) {
            Map.Entry<String, String> next = it2.next();
            SipHeader sipHeader = new SipHeader();
            sipHeader.setHName(next.getKey());
            sipHeader.setHValue(next.getValue());
            sipHeaderVector.add(sipHeader);
        }
        return sipHeaderVector;
    }
}
