package com.amazon.blueshift.bluefront.android.vad;

import com.amazon.blueshift.bluefront.android.SpeechClientException;
/* loaded from: classes11.dex */
public class VADException extends SpeechClientException {
    private static final long serialVersionUID = -6299168403661684245L;

    public VADException(String str) {
        super(str);
    }

    public VADException(Throwable th) {
        super(th);
    }

    public VADException(String str, Throwable th) {
        super(str, th);
    }
}
