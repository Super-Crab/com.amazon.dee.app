package com.amazonaws.services.kinesisfirehose.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class Record implements Serializable {
    private ByteBuffer data;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Record)) {
            return false;
        }
        Record record = (Record) obj;
        if ((record.getData() == null) ^ (getData() == null)) {
            return false;
        }
        return record.getData() == null || record.getData().equals(getData());
    }

    public ByteBuffer getData() {
        return this.data;
    }

    public int hashCode() {
        return 31 + (getData() == null ? 0 : getData().hashCode());
    }

    public void setData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getData() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Data: ");
            outline1072.append(getData());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Record withData(ByteBuffer byteBuffer) {
        this.data = byteBuffer;
        return this;
    }
}
