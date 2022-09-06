package com.amazon.alexa.mobilytics.recorder;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import java.util.List;
import rx.Observable;
/* loaded from: classes9.dex */
public interface EventRecorder {

    /* loaded from: classes9.dex */
    public interface Factory {
        EventRecorder create(@NonNull Endpoint endpoint, @NonNull CredentialsProvider credentialsProvider);
    }

    Observable<List<byte[]>> onSaveFailed();

    void saveRecord(@NonNull String str);

    void saveRecord(@NonNull String str, @NonNull String str2);

    void saveRecord(byte[] bArr);

    void saveRecord(byte[] bArr, @NonNull String str);

    long sizeOnDisk();

    void submitAllRecords();
}
