package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import java.util.List;
/* loaded from: classes13.dex */
public interface DeadLetterListener {
    void onRecordsDropped(String str, List<byte[]> list);
}
