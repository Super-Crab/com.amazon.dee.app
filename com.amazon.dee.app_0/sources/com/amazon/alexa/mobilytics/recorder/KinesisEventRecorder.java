package com.amazon.alexa.mobilytics.recorder;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.amazon.alexa.mobilytics.recorder.EventRecorder;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.DeadLetterListener;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.KinesisRecorder;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.KinesisRecorderConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.subjects.PublishSubject;
/* loaded from: classes9.dex */
public final class KinesisEventRecorder implements EventRecorder {
    private static final String TAG = Log.tag(KinesisEventRecorder.class);
    private final PublishSubject<List<byte[]>> errorSubject;
    private KinesisRecorder kinesisRecorder;
    private String streamName;

    @Singleton
    /* loaded from: classes9.dex */
    public static class Factory implements EventRecorder.Factory {
        private static final String DATA_DIRECTORY = "mobilytics";
        private static final long MAX_STORAGE_SIZE = 5242880;
        private static final String TAG = Log.tag(Factory.class);
        private final Context context;

        @Inject
        public Factory(@NonNull Context context) {
            this.context = (Context) Preconditions.checkNotNull(context);
        }

        @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder.Factory
        public EventRecorder create(@NonNull Endpoint endpoint, @NonNull CredentialsProvider credentialsProvider) {
            Preconditions.checkNotNull(endpoint);
            Preconditions.checkNotNull(credentialsProvider);
            if (endpoint.type() == 0) {
                if (credentialsProvider.type() == 0) {
                    KinesisEndpoint kinesisEndpoint = (KinesisEndpoint) endpoint;
                    CognitoCredentialsProvider cognitoCredentialsProvider = (CognitoCredentialsProvider) credentialsProvider;
                    String streamName = kinesisEndpoint.streamName();
                    String outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("mobilytics"), File.separator, streamName);
                    File createDirectory = Utils.createDirectory(this.context, outline91);
                    if (createDirectory != null) {
                        Log.i(TAG, "Storing data in %s", outline91);
                        final KinesisEventRecorder kinesisEventRecorder = new KinesisEventRecorder();
                        kinesisEventRecorder.streamName = streamName;
                        KinesisRecorderConfig kinesisRecorderConfig = new KinesisRecorderConfig();
                        kinesisRecorderConfig.withDeadLetterListener(new DeadLetterListener() { // from class: com.amazon.alexa.mobilytics.recorder.-$$Lambda$KinesisEventRecorder$Factory$0Ovxdt5XcfW5A_5kHDExteg0LHU
                            @Override // com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.DeadLetterListener
                            public final void onRecordsDropped(String str, List list) {
                                KinesisEventRecorder.this.errorSubject.onNext(list);
                            }
                        }).withMaxStorageSize(5242880L);
                        kinesisEventRecorder.kinesisRecorder = new KinesisRecorder(createDirectory, kinesisEndpoint.awsRegion(), cognitoCredentialsProvider.awsCredentialsProvider(), kinesisRecorderConfig);
                        return kinesisEventRecorder;
                    }
                    throw new RuntimeException("failed to create data directory");
                }
                throw new IllegalArgumentException("wrong credential provider type");
            }
            throw new IllegalArgumentException("wrong endpoint type");
        }
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public Observable<List<byte[]>> onSaveFailed() {
        return this.errorSubject.asObservable();
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public void saveRecord(@NonNull String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.kinesisRecorder.saveRecord(str, this.streamName);
        } catch (Exception e) {
            Log.e(TAG, e, "AWS SDK error -- saveRecord", new Object[0]);
        }
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public long sizeOnDisk() {
        try {
            return this.kinesisRecorder.getDiskBytesUsed();
        } catch (Exception e) {
            Log.e(TAG, e, "AWS SDK error -- sizeOnDisk", new Object[0]);
            return 0L;
        }
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public void submitAllRecords() {
        try {
            this.kinesisRecorder.submitAllRecords();
        } catch (Throwable th) {
            Log.e(TAG, th, "AWS SDK error -- submitAllRecords", new Object[0]);
        }
    }

    private KinesisEventRecorder() {
        this.errorSubject = PublishSubject.create();
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public void saveRecord(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        try {
            this.kinesisRecorder.saveRecord(bArr, this.streamName);
        } catch (Exception e) {
            Log.e(TAG, e, "AWS SDK error -- saveRecord", new Object[0]);
        }
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public void saveRecord(@NonNull String str, @NonNull String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.kinesisRecorder.saveRecord(str, str2);
        } catch (Exception e) {
            Log.e(TAG, e, "AWS SDK error -- saveRecord", new Object[0]);
        }
    }

    @Override // com.amazon.alexa.mobilytics.recorder.EventRecorder
    public void saveRecord(byte[] bArr, @NonNull String str) {
        if (bArr == null || bArr.length == 0) {
            return;
        }
        try {
            this.kinesisRecorder.saveRecord(bArr, str);
        } catch (Exception e) {
            Log.e(TAG, e, "AWS SDK error -- saveRecord", new Object[0]);
        }
    }
}
