package com.amazon.alexa;

import android.content.SharedPreferences;
import android.util.Log;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.amazon.alexa.wakeword.davs.ArtifactDownloadTask;
import com.amazon.alexa.wakeword.davs.ArtifactInfo;
import com.amazon.alexa.wakeword.davs.ArtifactManager;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.davs.ArtifactPersistedData;
import com.amazon.alexa.wakeword.davs.DavsClient;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: OfflinePromptsDownloadTask.kt */
/* loaded from: classes.dex */
public final class Dwc extends ArtifactDownloadTask {
    public static final String TAG = Dwc.class.getSimpleName();
    public final Provider<SharedPreferences> BIo;
    public final String zZm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Dwc(@NotNull RJu offlinePromptsArtifactInfo, @NotNull TimeProvider timeProvider, @NotNull DavsClient davsClient, @NotNull Provider<SharedPreferences> sharedPreferencesProvider, @NotNull ArtifactManager artifactManager, @NotNull ArtifactDownloadResultListener artifactDownloadResultListener) {
        super(offlinePromptsArtifactInfo, timeProvider, davsClient, artifactManager, artifactDownloadResultListener);
        Intrinsics.checkParameterIsNotNull(offlinePromptsArtifactInfo, "offlinePromptsArtifactInfo");
        Intrinsics.checkParameterIsNotNull(timeProvider, "timeProvider");
        Intrinsics.checkParameterIsNotNull(davsClient, "davsClient");
        Intrinsics.checkParameterIsNotNull(sharedPreferencesProvider, "sharedPreferencesProvider");
        Intrinsics.checkParameterIsNotNull(artifactManager, "artifactManager");
        Intrinsics.checkParameterIsNotNull(artifactDownloadResultListener, "artifactDownloadResultListener");
        this.BIo = sharedPreferencesProvider;
        this.zZm = ArtifactPersistedData.getPersistedArtifactIdentifier(this.BIo.mo2864get());
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    @NotNull
    public ArtifactModel getArtifactModelAndPersist(@NotNull String artifactIdentifier, @NotNull byte[] artifactData) {
        Intrinsics.checkParameterIsNotNull(artifactIdentifier, "artifactIdentifier");
        Intrinsics.checkParameterIsNotNull(artifactData, "artifactData");
        String str = TAG;
        StringBuilder zZm = C0179Pya.zZm("artifactId has changed from ");
        zZm.append(this.zZm);
        zZm.append(" to ");
        zZm.append(artifactIdentifier);
        Log.i(str, zZm.toString());
        ArtifactInfo artifactInfo = getArtifactInfo();
        if (artifactInfo != null) {
            String str2 = ((RJu) artifactInfo).BIo;
            long timestamp = getTimestamp();
            ArtifactPersistedData.builder().setArtifactIdentifier(artifactIdentifier).setLocale(str2).setDownloadTime(timestamp).build().persistArtifactSharedPreferences(this.BIo.mo2864get());
            ArtifactModel build = ArtifactModel.builder().setArtifactIdentifier(artifactIdentifier).setLocale(str2).setArtifactDownloadedTime(Long.valueOf(timestamp)).setArtifactData(artifactData).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "ArtifactModel.builder()\n…\n                .build()");
            return build;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.client.alexaservice.offlineprompts.OfflinePromptsArtifactInfo");
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    public long getCurrentArtifactDownloadTime() {
        return ArtifactPersistedData.getPersistedDownloadTime(this.BIo.mo2864get()).longValue();
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    public String getCurrentArtifactId() {
        return this.zZm;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    public void setCurrentArtifactDownloadTime(long j) {
        Long value = Long.valueOf(j);
        ArtifactPersistedData.Builder builder = ArtifactPersistedData.builder();
        Intrinsics.checkExpressionValueIsNotNull(value, "value");
        builder.setDownloadTime(value.longValue()).build().persistArtifactSharedPreferences(this.BIo.mo2864get());
    }
}
