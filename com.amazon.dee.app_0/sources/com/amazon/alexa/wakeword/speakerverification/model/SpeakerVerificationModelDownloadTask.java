package com.amazon.alexa.wakeword.speakerverification.model;

import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadTask;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.davs.ArtifactPersistedData;
import com.amazon.alexa.wakeword.davs.DavsClient;
import com.amazon.alexa.wakeword.davs.SpeakerVerificationModelArtifactInfo;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class SpeakerVerificationModelDownloadTask extends ArtifactDownloadTask {
    private static final String TAG = SpeakerVerificationModelDownloadTask.class.getSimpleName();
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SpeakerVerificationModelDownloadTask(SpeakerVerificationModelArtifactInfo speakerVerificationModelArtifactInfo, TimeProvider timeProvider, DavsClient davsClient, Provider<SharedPreferences> provider, ArtifactManager artifactManager, ArtifactDownloadResultListener artifactDownloadResultListener) {
        super(speakerVerificationModelArtifactInfo, timeProvider, davsClient, artifactManager, artifactDownloadResultListener);
        this.sharedPreferencesProvider = provider;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    public ArtifactModel getArtifactModelAndPersist(String str, byte[] bArr) {
        Log.i(TAG, String.format("artifactId has changed from %s to %s", getCurrentArtifactId(), str));
        long timestamp = getTimestamp();
        SpeakerVerificationModelArtifactInfo speakerVerificationModelArtifactInfo = (SpeakerVerificationModelArtifactInfo) getArtifactInfo();
        ArtifactPersistedData.builder().setArtifactIdentifier(str).setEngineCompatibilityId(speakerVerificationModelArtifactInfo.getEngineCompatibilityId()).setLocale(speakerVerificationModelArtifactInfo.getLocale()).setDownloadTime(timestamp).build().persistArtifactSharedPreferences(this.sharedPreferencesProvider.mo2864get());
        return ArtifactModel.builder().setArtifactIdentifier(str).setEngineCompatibilityId(speakerVerificationModelArtifactInfo.getEngineCompatibilityId()).setLocale(speakerVerificationModelArtifactInfo.getLocale()).setArtifactDownloadedTime(Long.valueOf(timestamp)).setArtifactData(bArr).build();
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    protected long getCurrentArtifactDownloadTime() {
        return ArtifactPersistedData.getPersistedDownloadTime(this.sharedPreferencesProvider.mo2864get()).longValue();
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    protected String getCurrentArtifactId() {
        return ArtifactPersistedData.getPersistedArtifactIdentifier(this.sharedPreferencesProvider.mo2864get());
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    protected void setCurrentArtifactDownloadTime(long j) {
        ArtifactPersistedData.builder().setDownloadTime(j).build().persistArtifactSharedPreferences(this.sharedPreferencesProvider.mo2864get());
    }
}
