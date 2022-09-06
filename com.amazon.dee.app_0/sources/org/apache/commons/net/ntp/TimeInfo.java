package org.apache.commons.net.ntp;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes4.dex */
public class TimeInfo {
    private List _comments;
    private Long _delay;
    private boolean _detailsComputed;
    private NtpV3Packet _message;
    private Long _offset;
    private long _returnTime;

    public TimeInfo(NtpV3Packet ntpV3Packet, long j) {
        this(ntpV3Packet, j, null, true);
    }

    public void addComment(String str) {
        if (this._comments == null) {
            this._comments = new ArrayList();
        }
        this._comments.add(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void computeDetails() {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ntp.TimeInfo.computeDetails():void");
    }

    public List getComments() {
        return this._comments;
    }

    public Long getDelay() {
        return this._delay;
    }

    public NtpV3Packet getMessage() {
        return this._message;
    }

    public Long getOffset() {
        return this._offset;
    }

    public long getReturnTime() {
        return this._returnTime;
    }

    public TimeInfo(NtpV3Packet ntpV3Packet, long j, List list) {
        this(ntpV3Packet, j, list, true);
    }

    public TimeInfo(NtpV3Packet ntpV3Packet, long j, boolean z) {
        this(ntpV3Packet, j, null, z);
    }

    public TimeInfo(NtpV3Packet ntpV3Packet, long j, List list, boolean z) {
        if (ntpV3Packet != null) {
            this._returnTime = j;
            this._message = ntpV3Packet;
            this._comments = list;
            if (!z) {
                return;
            }
            computeDetails();
            return;
        }
        throw new IllegalArgumentException("message cannot be null");
    }
}
