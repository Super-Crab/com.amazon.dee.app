package com.amazon.alexa.wakeword.speakerverification.encryption;

import androidx.annotation.NonNull;
import com.amazon.alexa.wakeword.speakerverification.errors.EncryptionFailure;
/* loaded from: classes11.dex */
public interface EncryptionMetricsListener {
    void onEncryptionFailure(@NonNull EncryptionFailure encryptionFailure);

    void onEncryptionSuccess();

    void onNonFatalError(@NonNull Exception exc, @NonNull String str);
}
