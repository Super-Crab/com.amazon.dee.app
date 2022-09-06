package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.util.Base64;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class FileRecordParser {
    private static final String DELIMITER = ",";
    byte[] bytes;
    String streamName;

    public static String asString(String str, byte[] bArr) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, ",");
        outline113.append(Base64.encodeAsString(bArr));
        return outline113.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void parse(String str) {
        String[] split = str.split(",", 2);
        if (split.length >= 2) {
            this.streamName = split[0];
            this.bytes = Base64.decode(split[1]);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid line: ", str));
    }

    public String toString() {
        return asString(this.streamName, this.bytes);
    }
}
