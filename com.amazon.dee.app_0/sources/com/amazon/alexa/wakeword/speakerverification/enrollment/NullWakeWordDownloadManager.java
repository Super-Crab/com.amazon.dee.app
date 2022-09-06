package com.amazon.alexa.wakeword.speakerverification.enrollment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelUserParams;
/* loaded from: classes11.dex */
public class NullWakeWordDownloadManager implements WakeWordDownloadManager {
    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void downloadWakeWordModelAsync(@NonNull WakeWordModelUserParams wakeWordModelUserParams) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void downloadWakeWordModelAsync(@NonNull WakeWordModelUserParams wakeWordModelUserParams, @Nullable ArtifactDownloadResultListener artifactDownloadResultListener) {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void initUpdatesCycle() {
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void release() {
    }
}
