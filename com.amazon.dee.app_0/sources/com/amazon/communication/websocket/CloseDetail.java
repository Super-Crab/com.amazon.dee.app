package com.amazon.communication.websocket;

import amazon.communication.connection.ConnectionClosedDetails;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class CloseDetail {
    private final String mDetail;
    private final int mStatusCode;

    public CloseDetail(int i, String str) {
        this.mStatusCode = i;
        this.mDetail = str;
    }

    public ConnectionClosedDetails convertToConnectionClosedDetails() {
        if (this.mStatusCode != 4001) {
            return new ConnectionClosedDetails(0, this.mDetail);
        }
        return new ConnectionClosedDetails(1, this.mDetail);
    }

    public boolean equals(Object obj) {
        if (obj == null || CloseDetail.class != obj.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        CloseDetail closeDetail = (CloseDetail) obj;
        if (getStatusCode() != closeDetail.getStatusCode()) {
            return false;
        }
        if (getDetail() != null) {
            return getDetail().equals(closeDetail.getDetail());
        }
        return closeDetail.getDetail() == null;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public int hashCode() {
        String str = this.mDetail;
        return (str == null ? 0 : str.hashCode()) + this.mStatusCode;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CloseDetail [Status code is ");
        outline107.append(this.mStatusCode);
        outline107.append(", detail is ");
        return GeneratedOutlineSupport1.outline91(outline107, this.mDetail, "]");
    }
}
