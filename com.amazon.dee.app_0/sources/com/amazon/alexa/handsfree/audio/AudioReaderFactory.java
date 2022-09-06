package com.amazon.alexa.handsfree.audio;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.audio.features.AudioEnrollmentTypeResolver;
import com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifyingAudioReader;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class AudioReaderFactory {
    private static final String TAG = "AudioReaderFactory";
    private Context mContext;
    private final AudioEnrollmentTypeResolver mEnrollmentTypeResolver;

    public AudioReaderFactory(Context context) {
        this(new AudioEnrollmentTypeResolver(context), context);
    }

    @NonNull
    public AudioReader createAudioReader() {
        if (this.mEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
            Log.d(TAG, "Creating 1PSV audio reader");
            return new SpeakerVerifyingAudioReader(this.mContext);
        }
        Log.d(TAG, "Creating 3PSV audio reader");
        return new AudioReader();
    }

    @VisibleForTesting
    public AudioReaderFactory(@NonNull AudioEnrollmentTypeResolver audioEnrollmentTypeResolver, @NonNull Context context) {
        this.mEnrollmentTypeResolver = audioEnrollmentTypeResolver;
        this.mContext = context;
    }
}
