package com.amazon.blueshift.bluefront.android.audio.encoder;

import com.amazon.blueshift.bluefront.android.SpeechClientException;
/* loaded from: classes11.dex */
public class AudioEncoderException extends SpeechClientException {
    private static final long serialVersionUID = -4016454290306351776L;

    public AudioEncoderException(String str) {
        super(str);
    }

    public AudioEncoderException(Throwable th) {
        super(th);
    }

    public AudioEncoderException(String str, Throwable th) {
        super(str, th);
    }
}
