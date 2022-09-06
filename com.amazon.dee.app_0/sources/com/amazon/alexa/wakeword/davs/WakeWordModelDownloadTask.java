package com.amazon.alexa.wakeword.davs;

import android.util.Log;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WakeWordModelDownloadTask.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u0000  2\u00020\u0001:\u0001 B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108T@TX\u0094\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\n \u0018*\u0004\u0018\u00010\u00170\u0017X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/WakeWordModelDownloadTask;", "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadTask;", "wakeWordModelArtifactInfo", "Lcom/amazon/alexa/wakeword/davs/WakeWordModelArtifactInfo;", "wakeWordModelContentProviderHelper", "Lcom/amazon/alexa/wakeword/pryon/WakeWordModelContentProviderHelper;", "timeProvider", "Lcom/amazon/alexa/utils/TimeProvider;", "davsClient", "Lcom/amazon/alexa/wakeword/davs/DavsClient;", "artifactManager", "Lcom/amazon/alexa/wakeword/davs/ArtifactManager;", "artifactDownloadResultListener", "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;", "(Lcom/amazon/alexa/wakeword/davs/WakeWordModelArtifactInfo;Lcom/amazon/alexa/wakeword/pryon/WakeWordModelContentProviderHelper;Lcom/amazon/alexa/utils/TimeProvider;Lcom/amazon/alexa/wakeword/davs/DavsClient;Lcom/amazon/alexa/wakeword/davs/ArtifactManager;Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;)V", "value", "", "currentArtifactDownloadTime", "getCurrentArtifactDownloadTime", "()J", "setCurrentArtifactDownloadTime", "(J)V", "currentArtifactId", "", "kotlin.jvm.PlatformType", "getCurrentArtifactId", "()Ljava/lang/String;", "getArtifactModelAndPersist", "Lcom/amazon/alexa/wakeword/davs/ArtifactModel;", "artifactIdentifier", "artifactData", "", "Companion", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class WakeWordModelDownloadTask extends ArtifactDownloadTask {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = WakeWordModelDownloadTask.class.getSimpleName();
    private final String currentArtifactId;
    private final WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper;

    /* compiled from: WakeWordModelDownloadTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/WakeWordModelDownloadTask$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WakeWordModelDownloadTask(@NotNull WakeWordModelArtifactInfo wakeWordModelArtifactInfo, @NotNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, @NotNull TimeProvider timeProvider, @NotNull DavsClient davsClient, @NotNull ArtifactManager artifactManager, @NotNull ArtifactDownloadResultListener artifactDownloadResultListener) {
        super(wakeWordModelArtifactInfo, timeProvider, davsClient, artifactManager, artifactDownloadResultListener);
        Intrinsics.checkParameterIsNotNull(wakeWordModelArtifactInfo, "wakeWordModelArtifactInfo");
        Intrinsics.checkParameterIsNotNull(wakeWordModelContentProviderHelper, "wakeWordModelContentProviderHelper");
        Intrinsics.checkParameterIsNotNull(timeProvider, "timeProvider");
        Intrinsics.checkParameterIsNotNull(davsClient, "davsClient");
        Intrinsics.checkParameterIsNotNull(artifactManager, "artifactManager");
        Intrinsics.checkParameterIsNotNull(artifactDownloadResultListener, "artifactDownloadResultListener");
        this.wakeWordModelContentProviderHelper = wakeWordModelContentProviderHelper;
        this.currentArtifactId = this.wakeWordModelContentProviderHelper.readExistingModelArtifactId();
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    @NotNull
    public ArtifactModel getArtifactModelAndPersist(@NotNull String artifactIdentifier, @NotNull byte[] artifactData) {
        Intrinsics.checkParameterIsNotNull(artifactIdentifier, "artifactIdentifier");
        Intrinsics.checkParameterIsNotNull(artifactData, "artifactData");
        ArtifactInfo artifactInfo = getArtifactInfo();
        if (artifactInfo != null) {
            WakeWordModelArtifactInfo wakeWordModelArtifactInfo = (WakeWordModelArtifactInfo) artifactInfo;
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("artifactId has changed from ");
            outline107.append(getCurrentArtifactId());
            outline107.append(" to ");
            outline107.append(artifactIdentifier);
            Log.i(str, outline107.toString());
            long timestamp = getTimestamp();
            this.wakeWordModelContentProviderHelper.setArtifactData(ArtifactModel.builder().setArtifactIdentifier(artifactIdentifier).setLocale(wakeWordModelArtifactInfo.getLocale()).setEngineCompatibilityId(WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId()).setArtifactDownloadedTime(Long.valueOf(timestamp)).setWakeWords(wakeWordModelArtifactInfo.getWakeWords()).build());
            ArtifactModel build = ArtifactModel.builder().setArtifactIdentifier(artifactIdentifier).setEngineCompatibilityId(WakeWordModelArtifactInfo.Companion.getEngineCompatibilityId()).setLocale(wakeWordModelArtifactInfo.getLocale()).setArtifactDownloadedTime(Long.valueOf(timestamp)).setWakeWords(wakeWordModelArtifactInfo.getWakeWords()).setArtifactData(artifactData).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "ArtifactModel.builder()\n…\n                .build()");
            return build;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.wakeword.davs.WakeWordModelArtifactInfo");
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    protected long getCurrentArtifactDownloadTime() {
        return this.wakeWordModelContentProviderHelper.readWakeWordModelDownloadTime();
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    protected String getCurrentArtifactId() {
        return this.currentArtifactId;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadTask
    protected void setCurrentArtifactDownloadTime(long j) {
        this.wakeWordModelContentProviderHelper.setArtifactDownloadTime(j);
    }
}
