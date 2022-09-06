package com.amazon.alexa.wakeword.pryon;

import com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WakeWordDownloadManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/wakeword/pryon/WakeWordDownloadManager;", "", "downloadWakeWordModelAsync", "", "userParams", "Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;", "initUpdatesCycle", "release", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface WakeWordDownloadManager {

    /* compiled from: WakeWordDownloadManager.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class DefaultImpls {
        public static void downloadWakeWordModelAsync(WakeWordDownloadManager wakeWordDownloadManager, @NotNull WakeWordModelUserParams userParams) {
            Intrinsics.checkParameterIsNotNull(userParams, "userParams");
            wakeWordDownloadManager.downloadWakeWordModelAsync(userParams, null);
        }

        public static /* synthetic */ void downloadWakeWordModelAsync$default(WakeWordDownloadManager wakeWordDownloadManager, WakeWordModelUserParams wakeWordModelUserParams, ArtifactDownloadResultListener artifactDownloadResultListener, int i, Object obj) {
            if (obj == null) {
                if ((i & 2) != 0) {
                    artifactDownloadResultListener = null;
                }
                wakeWordDownloadManager.downloadWakeWordModelAsync(wakeWordModelUserParams, artifactDownloadResultListener);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: downloadWakeWordModelAsync");
        }
    }

    void downloadWakeWordModelAsync(@NotNull WakeWordModelUserParams wakeWordModelUserParams);

    void downloadWakeWordModelAsync(@NotNull WakeWordModelUserParams wakeWordModelUserParams, @Nullable ArtifactDownloadResultListener artifactDownloadResultListener);

    void initUpdatesCycle();

    void release();
}
