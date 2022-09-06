package com.amazon.blueshift.bluefront.android.task;

import com.amazon.blueshift.bluefront.android.SpeechClientException;
/* loaded from: classes11.dex */
public interface MultipartRequestTaskListener {
    void onError(SpeechClientException speechClientException);

    void onResponse(byte[] bArr);
}
