package com.amazon.alexa.handsfree.audio.speakerverification;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.speakerverification.model.ModelType;
import com.amazon.alexa.wakeword.speakerverification.model.SpeakerVerificationModelContentProviderUtility;
/* loaded from: classes8.dex */
class SpeakerVerificationModelProvider {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public ArtifactModel getArtifactModel(@NonNull Context context) {
        return SpeakerVerificationModelContentProviderUtility.getArtifactModel(context, ModelType.CLASSIFICATION);
    }
}
