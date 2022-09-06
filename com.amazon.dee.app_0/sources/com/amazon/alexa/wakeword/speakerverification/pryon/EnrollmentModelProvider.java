package com.amazon.alexa.wakeword.speakerverification.pryon;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.PackageNameProvider;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import com.amazon.alexa.wakeword.speakerverification.model.ModelType;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelContentProviderUtility;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
/* loaded from: classes11.dex */
public class EnrollmentModelProvider {
    public static final String NULL_MODEL_ID = "null_model";
    private static final String TAG = "EnrollmentModelProvider";
    private final WakeWordModelContentProviderHelper mContentProviderHelper;
    private final Context mContext;

    public EnrollmentModelProvider(@NonNull final Context context) {
        this(context, new WakeWordModelContentProviderHelper(context.getContentResolver(), new PackageNameProvider() { // from class: com.amazon.alexa.wakeword.speakerverification.pryon.EnrollmentModelProvider.1
            @Override // com.amazon.alexa.utils.PackageNameProvider
            public String getPackageName() {
                return context.getPackageName();
            }
        }));
    }

    @Nullable
    public byte[] getSpeakerVerificationModel() {
        ArtifactModel artifactModel = SpeakerVerificationModelContentProviderUtility.getArtifactModel(this.mContext, ModelType.REGENERATION);
        if (artifactModel != null) {
            return artifactModel.getArtifactData();
        }
        return null;
    }

    @Nullable
    public byte[] getWakeWordModel() {
        try {
            InputStream openWakeWordModelInputStream = this.mContentProviderHelper.openWakeWordModelInputStream();
            byte[] byteArray = IOUtils.toByteArray(openWakeWordModelInputStream);
            if (openWakeWordModelInputStream != null) {
                openWakeWordModelInputStream.close();
            }
            return byteArray;
        } catch (IOException e) {
            Log.e(TAG, "getWakeWordModel fail", e, new Object[0]);
            return null;
        }
    }

    @VisibleForTesting
    EnrollmentModelProvider(@NonNull Context context, @NonNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper) {
        this.mContext = context;
        this.mContentProviderHelper = wakeWordModelContentProviderHelper;
    }
}
