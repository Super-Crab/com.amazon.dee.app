package com.amazon.alexa.wakeword.davs;

import amazon.speech.simclient.settings.Settings;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.utils.DataDirectoryProvider;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener;
import com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager;
import com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper;
import com.amazon.alexa.wakeword.pryon.WakeWordModelUserParams;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.File;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DavsWakeWordDownloadManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 02\u00020\u0001:\u0003012Bm\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\u001a\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010(\u001a\u00020$H\u0016J\u0010\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020+H\u0017J\b\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020$H\u0016R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager;", "Lcom/amazon/alexa/wakeword/pryon/WakeWordDownloadManager;", "wakeWordModelContentProviderHelper", "Lcom/amazon/alexa/wakeword/pryon/WakeWordModelContentProviderHelper;", "artifactManager", "Lcom/amazon/alexa/wakeword/davs/ArtifactManager;", "timeProvider", "Lcom/amazon/alexa/utils/TimeProvider;", "networkManager", "Lcom/amazon/alexa/wakeword/davs/NetworkManager;", "metricsListener", "Lcom/amazon/alexa/wakeword/pryon/WakeWordDetectionMetricsListener;", "davsClient", "Lcom/amazon/alexa/wakeword/davs/DavsClient;", "crashReporter", "Lcom/amazon/alexa/client/crashreporting/CrashReporter;", "artifactDownloadListener", "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;", "isCurrentDeviceHandsFree", "", "dataDirectoryProvider", "Lcom/amazon/alexa/utils/DataDirectoryProvider;", "multiWakeWordFeatureEnabledProvider", "Lcom/amazon/alexa/wakeword/davs/MultiWakeWordFeatureEnabledProvider;", "davsAccessExecutor", "Ljava/util/concurrent/ScheduledExecutorService;", "(Lcom/amazon/alexa/wakeword/pryon/WakeWordModelContentProviderHelper;Lcom/amazon/alexa/wakeword/davs/ArtifactManager;Lcom/amazon/alexa/utils/TimeProvider;Lcom/amazon/alexa/wakeword/davs/NetworkManager;Lcom/amazon/alexa/wakeword/pryon/WakeWordDetectionMetricsListener;Lcom/amazon/alexa/wakeword/davs/DavsClient;Lcom/amazon/alexa/client/crashreporting/CrashReporter;Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;ZLcom/amazon/alexa/utils/DataDirectoryProvider;Lcom/amazon/alexa/wakeword/davs/MultiWakeWordFeatureEnabledProvider;Ljava/util/concurrent/ScheduledExecutorService;)V", "isReadyForUpdate", "()Z", "lookForUpdatesFuture", "Ljava/util/concurrent/Future;", "timestamp", "", "getTimestamp", "()J", "downloadWakeWordModelAsync", "", "userParams", "Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "initUpdatesCycle", "isFileExist", "file", "Ljava/io/File;", "lookForUpdates", "needUpdate", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus;", "release", "Companion", "InternalArtifactDownloadResultListener", "UpdateStatus", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public class DavsWakeWordDownloadManager implements WakeWordDownloadManager {
    public static final double EXCEPTION_REPORT_PROBABILITY = 0.001d;
    private static final String EXECUTOR_SERVICE_THREAD_NAME = "davs";
    private final ArtifactDownloadResultListener artifactDownloadListener;
    private final ArtifactManager artifactManager;
    private final CrashReporter crashReporter;
    private final DataDirectoryProvider dataDirectoryProvider;
    private final ScheduledExecutorService davsAccessExecutor;
    private final DavsClient davsClient;
    private final boolean isCurrentDeviceHandsFree;
    private Future<?> lookForUpdatesFuture;
    private final WakeWordDetectionMetricsListener metricsListener;
    private final MultiWakeWordFeatureEnabledProvider multiWakeWordFeatureEnabledProvider;
    private final NetworkManager networkManager;
    private final TimeProvider timeProvider;
    private final WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = DavsWakeWordDownloadManager.class.getSimpleName();
    @JvmField
    public static final long WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);

    /* compiled from: DavsWakeWordDownloadManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$Companion;", "", "()V", "EXCEPTION_REPORT_PROBABILITY", "", "EXECUTOR_SERVICE_THREAD_NAME", "", "TAG", "kotlin.jvm.PlatformType", "WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS", "", "WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS$annotations", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: DavsWakeWordDownloadManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J.\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$InternalArtifactDownloadResultListener;", "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;", "callerListener", "(Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager;Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;)V", "onArtifactAlreadyUpToDate", "", "requestDuration", "", "artifactModel", "Lcom/amazon/alexa/wakeword/davs/ArtifactModel;", "onArtifactDownloadFailure", "artifactMD5", "", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", Settings.ListeningSettings.EXTRA_REASON, "onArtifactDownloadInterrupted", "onArtifactDownloadSuccess", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public final class InternalArtifactDownloadResultListener implements ArtifactDownloadResultListener {
        private final ArtifactDownloadResultListener callerListener;

        public InternalArtifactDownloadResultListener(@Nullable ArtifactDownloadResultListener artifactDownloadResultListener) {
            this.callerListener = artifactDownloadResultListener;
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
        public void onArtifactAlreadyUpToDate(long j, @NotNull ArtifactModel artifactModel) {
            Intrinsics.checkParameterIsNotNull(artifactModel, "artifactModel");
            String unused = DavsWakeWordDownloadManager.TAG;
            String str = "onArtifactAlreadyUpToDate " + artifactModel.getArtifactIdentifier();
            DavsWakeWordDownloadManager.this.artifactDownloadListener.onArtifactAlreadyUpToDate(j, artifactModel);
            ArtifactDownloadResultListener artifactDownloadResultListener = this.callerListener;
            if (artifactDownloadResultListener != null) {
                artifactDownloadResultListener.onArtifactAlreadyUpToDate(j, artifactModel);
            }
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
        public void onArtifactDownloadFailure(long j, @Nullable String str, @Nullable Exception exc, @Nullable String str2) {
            String unused = DavsWakeWordDownloadManager.TAG;
            String str3 = "onArtifactDownloadFailure " + str2;
            DavsWakeWordDownloadManager.this.metricsListener.onWakeWordModelDownloadFailure(j, str, str2);
            DavsWakeWordDownloadManager.this.artifactDownloadListener.onArtifactDownloadFailure(j, str, exc, str2);
            ArtifactDownloadResultListener artifactDownloadResultListener = this.callerListener;
            if (artifactDownloadResultListener != null) {
                artifactDownloadResultListener.onArtifactDownloadFailure(j, str, exc, str2);
            }
            if (exc != null) {
                DavsWakeWordDownloadManager.this.crashReporter.notifyHandledException(exc, 0.001d);
            }
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
        public void onArtifactDownloadInterrupted(long j) {
            String unused = DavsWakeWordDownloadManager.TAG;
            DavsWakeWordDownloadManager.this.metricsListener.onWakeWordModelDownloadInterrupted(j);
            DavsWakeWordDownloadManager.this.artifactDownloadListener.onArtifactDownloadInterrupted(j);
            ArtifactDownloadResultListener artifactDownloadResultListener = this.callerListener;
            if (artifactDownloadResultListener != null) {
                artifactDownloadResultListener.onArtifactDownloadInterrupted(j);
            }
        }

        @Override // com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener
        public void onArtifactDownloadSuccess(long j, @NotNull ArtifactModel artifactModel) {
            Intrinsics.checkParameterIsNotNull(artifactModel, "artifactModel");
            String unused = DavsWakeWordDownloadManager.TAG;
            String str = "onArtifactDownloadSuccess " + artifactModel.getArtifactIdentifier();
            DavsWakeWordDownloadManager.this.metricsListener.onWakeWordModelDownloadSuccess(j, CheckSumUtils.getMD5(artifactModel.getArtifactData()));
            DavsWakeWordDownloadManager.this.artifactDownloadListener.onArtifactDownloadSuccess(j, artifactModel);
            ArtifactDownloadResultListener artifactDownloadResultListener = this.callerListener;
            if (artifactDownloadResultListener != null) {
                artifactDownloadResultListener.onArtifactDownloadSuccess(j, artifactModel);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DavsWakeWordDownloadManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus;", "", "()V", "CantUpdate", "NeedUpdate", "UpToDate", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus$NeedUpdate;", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus$UpToDate;", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus$CantUpdate;", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static abstract class UpdateStatus {

        /* compiled from: DavsWakeWordDownloadManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus$CantUpdate;", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus;", "()V", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class CantUpdate extends UpdateStatus {
            public static final CantUpdate INSTANCE = new CantUpdate();

            private CantUpdate() {
                super(null);
            }
        }

        /* compiled from: DavsWakeWordDownloadManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus$NeedUpdate;", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus;", "userParams", "Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", "(Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;)V", "getUserParams", "()Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class NeedUpdate extends UpdateStatus {
            @NotNull
            private final WakeWordModelUserParams userParams;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NeedUpdate(@NotNull WakeWordModelUserParams userParams) {
                super(null);
                Intrinsics.checkParameterIsNotNull(userParams, "userParams");
                this.userParams = userParams;
            }

            public static /* synthetic */ NeedUpdate copy$default(NeedUpdate needUpdate, WakeWordModelUserParams wakeWordModelUserParams, int i, Object obj) {
                if ((i & 1) != 0) {
                    wakeWordModelUserParams = needUpdate.userParams;
                }
                return needUpdate.copy(wakeWordModelUserParams);
            }

            @NotNull
            public final WakeWordModelUserParams component1() {
                return this.userParams;
            }

            @NotNull
            public final NeedUpdate copy(@NotNull WakeWordModelUserParams userParams) {
                Intrinsics.checkParameterIsNotNull(userParams, "userParams");
                return new NeedUpdate(userParams);
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    return (obj instanceof NeedUpdate) && Intrinsics.areEqual(this.userParams, ((NeedUpdate) obj).userParams);
                }
                return true;
            }

            @NotNull
            public final WakeWordModelUserParams getUserParams() {
                return this.userParams;
            }

            public int hashCode() {
                WakeWordModelUserParams wakeWordModelUserParams = this.userParams;
                if (wakeWordModelUserParams != null) {
                    return wakeWordModelUserParams.hashCode();
                }
                return 0;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NeedUpdate(userParams=");
                outline107.append(this.userParams);
                outline107.append(")");
                return outline107.toString();
            }
        }

        /* compiled from: DavsWakeWordDownloadManager.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus$UpToDate;", "Lcom/amazon/alexa/wakeword/davs/DavsWakeWordDownloadManager$UpdateStatus;", "userParams", "Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", "(Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;)V", "getUserParams", "()Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public static final class UpToDate extends UpdateStatus {
            @NotNull
            private final WakeWordModelUserParams userParams;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UpToDate(@NotNull WakeWordModelUserParams userParams) {
                super(null);
                Intrinsics.checkParameterIsNotNull(userParams, "userParams");
                this.userParams = userParams;
            }

            public static /* synthetic */ UpToDate copy$default(UpToDate upToDate, WakeWordModelUserParams wakeWordModelUserParams, int i, Object obj) {
                if ((i & 1) != 0) {
                    wakeWordModelUserParams = upToDate.userParams;
                }
                return upToDate.copy(wakeWordModelUserParams);
            }

            @NotNull
            public final WakeWordModelUserParams component1() {
                return this.userParams;
            }

            @NotNull
            public final UpToDate copy(@NotNull WakeWordModelUserParams userParams) {
                Intrinsics.checkParameterIsNotNull(userParams, "userParams");
                return new UpToDate(userParams);
            }

            public boolean equals(@Nullable Object obj) {
                if (this != obj) {
                    return (obj instanceof UpToDate) && Intrinsics.areEqual(this.userParams, ((UpToDate) obj).userParams);
                }
                return true;
            }

            @NotNull
            public final WakeWordModelUserParams getUserParams() {
                return this.userParams;
            }

            public int hashCode() {
                WakeWordModelUserParams wakeWordModelUserParams = this.userParams;
                if (wakeWordModelUserParams != null) {
                    return wakeWordModelUserParams.hashCode();
                }
                return 0;
            }

            @NotNull
            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpToDate(userParams=");
                outline107.append(this.userParams);
                outline107.append(")");
                return outline107.toString();
            }
        }

        private UpdateStatus() {
        }

        public /* synthetic */ UpdateStatus(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmOverloads
    public DavsWakeWordDownloadManager(@NotNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, @NotNull ArtifactManager artifactManager, @NotNull TimeProvider timeProvider, @NotNull NetworkManager networkManager, @NotNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NotNull DavsClient davsClient, @NotNull CrashReporter crashReporter, @NotNull ArtifactDownloadResultListener artifactDownloadResultListener, boolean z) {
        this(wakeWordModelContentProviderHelper, artifactManager, timeProvider, networkManager, wakeWordDetectionMetricsListener, davsClient, crashReporter, artifactDownloadResultListener, z, null, null, null, 3584, null);
    }

    @JvmOverloads
    public DavsWakeWordDownloadManager(@NotNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, @NotNull ArtifactManager artifactManager, @NotNull TimeProvider timeProvider, @NotNull NetworkManager networkManager, @NotNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NotNull DavsClient davsClient, @NotNull CrashReporter crashReporter, @NotNull ArtifactDownloadResultListener artifactDownloadResultListener, boolean z, @NotNull DataDirectoryProvider dataDirectoryProvider) {
        this(wakeWordModelContentProviderHelper, artifactManager, timeProvider, networkManager, wakeWordDetectionMetricsListener, davsClient, crashReporter, artifactDownloadResultListener, z, dataDirectoryProvider, null, null, 3072, null);
    }

    @JvmOverloads
    public DavsWakeWordDownloadManager(@NotNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, @NotNull ArtifactManager artifactManager, @NotNull TimeProvider timeProvider, @NotNull NetworkManager networkManager, @NotNull WakeWordDetectionMetricsListener wakeWordDetectionMetricsListener, @NotNull DavsClient davsClient, @NotNull CrashReporter crashReporter, @NotNull ArtifactDownloadResultListener artifactDownloadResultListener, boolean z, @NotNull DataDirectoryProvider dataDirectoryProvider, @NotNull MultiWakeWordFeatureEnabledProvider multiWakeWordFeatureEnabledProvider) {
        this(wakeWordModelContentProviderHelper, artifactManager, timeProvider, networkManager, wakeWordDetectionMetricsListener, davsClient, crashReporter, artifactDownloadResultListener, z, dataDirectoryProvider, multiWakeWordFeatureEnabledProvider, null, 2048, null);
    }

    @JvmOverloads
    public DavsWakeWordDownloadManager(@NotNull WakeWordModelContentProviderHelper wakeWordModelContentProviderHelper, @NotNull ArtifactManager artifactManager, @NotNull TimeProvider timeProvider, @NotNull NetworkManager networkManager, @NotNull WakeWordDetectionMetricsListener metricsListener, @NotNull DavsClient davsClient, @NotNull CrashReporter crashReporter, @NotNull ArtifactDownloadResultListener artifactDownloadListener, boolean z, @NotNull DataDirectoryProvider dataDirectoryProvider, @NotNull MultiWakeWordFeatureEnabledProvider multiWakeWordFeatureEnabledProvider, @NotNull ScheduledExecutorService davsAccessExecutor) {
        Intrinsics.checkParameterIsNotNull(wakeWordModelContentProviderHelper, "wakeWordModelContentProviderHelper");
        Intrinsics.checkParameterIsNotNull(artifactManager, "artifactManager");
        Intrinsics.checkParameterIsNotNull(timeProvider, "timeProvider");
        Intrinsics.checkParameterIsNotNull(networkManager, "networkManager");
        Intrinsics.checkParameterIsNotNull(metricsListener, "metricsListener");
        Intrinsics.checkParameterIsNotNull(davsClient, "davsClient");
        Intrinsics.checkParameterIsNotNull(crashReporter, "crashReporter");
        Intrinsics.checkParameterIsNotNull(artifactDownloadListener, "artifactDownloadListener");
        Intrinsics.checkParameterIsNotNull(dataDirectoryProvider, "dataDirectoryProvider");
        Intrinsics.checkParameterIsNotNull(multiWakeWordFeatureEnabledProvider, "multiWakeWordFeatureEnabledProvider");
        Intrinsics.checkParameterIsNotNull(davsAccessExecutor, "davsAccessExecutor");
        this.wakeWordModelContentProviderHelper = wakeWordModelContentProviderHelper;
        this.artifactManager = artifactManager;
        this.timeProvider = timeProvider;
        this.networkManager = networkManager;
        this.metricsListener = metricsListener;
        this.davsClient = davsClient;
        this.crashReporter = crashReporter;
        this.artifactDownloadListener = artifactDownloadListener;
        this.isCurrentDeviceHandsFree = z;
        this.dataDirectoryProvider = dataDirectoryProvider;
        this.multiWakeWordFeatureEnabledProvider = multiWakeWordFeatureEnabledProvider;
        this.davsAccessExecutor = davsAccessExecutor;
    }

    private final long getTimestamp() {
        return this.timeProvider.currentTimeMillis();
    }

    private final boolean isReadyForUpdate() {
        return getTimestamp() - this.wakeWordModelContentProviderHelper.readWakeWordModelDownloadTime() >= WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void lookForUpdates() {
        UpdateStatus needUpdate = needUpdate();
        if (needUpdate instanceof UpdateStatus.NeedUpdate) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("wake word model needs update for ");
            UpdateStatus.NeedUpdate needUpdate2 = (UpdateStatus.NeedUpdate) needUpdate;
            outline107.append(needUpdate2.getUserParams());
            outline107.toString();
            downloadWakeWordModelAsync(needUpdate2.getUserParams());
        } else if (needUpdate instanceof UpdateStatus.UpToDate) {
            if (!isReadyForUpdate()) {
                return;
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("routine update for ");
            UpdateStatus.UpToDate upToDate = (UpdateStatus.UpToDate) needUpdate;
            outline1072.append(upToDate.getUserParams());
            outline1072.toString();
            downloadWakeWordModelAsync(upToDate.getUserParams());
        } else if (!(needUpdate instanceof UpdateStatus.CantUpdate)) {
        } else {
            Log.w(TAG, "not enough data to update ww model");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager.UpdateStatus needUpdate() {
        /*
            Method dump skipped, instructions count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager.needUpdate():com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager$UpdateStatus");
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void downloadWakeWordModelAsync(@NotNull WakeWordModelUserParams userParams) {
        Intrinsics.checkParameterIsNotNull(userParams, "userParams");
        WakeWordDownloadManager.DefaultImpls.downloadWakeWordModelAsync(this, userParams);
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void initUpdatesCycle() {
        String str = "initiating wake word model updates check once in " + TimeUnit.MINUTES.convert(WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS, TimeUnit.MILLISECONDS) + " minutes";
        this.lookForUpdatesFuture = this.davsAccessExecutor.scheduleAtFixedRate(new Runnable() { // from class: com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager$initUpdatesCycle$1
            @Override // java.lang.Runnable
            public final void run() {
                DavsWakeWordDownloadManager.this.lookForUpdates();
            }
        }, 0L, WAKE_WORD_MODEL_UPDATE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
    }

    @VisibleForTesting
    public boolean isFileExist(@NotNull File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        return file.exists();
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void release() {
        Future<?> future = this.lookForUpdatesFuture;
        if (future != null) {
            future.cancel(true);
        }
        this.networkManager.teardown();
        this.davsAccessExecutor.shutdownNow();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ DavsWakeWordDownloadManager(com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper r16, com.amazon.alexa.wakeword.davs.ArtifactManager r17, com.amazon.alexa.utils.TimeProvider r18, com.amazon.alexa.wakeword.davs.NetworkManager r19, com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener r20, com.amazon.alexa.wakeword.davs.DavsClient r21, com.amazon.alexa.client.crashreporting.CrashReporter r22, com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener r23, boolean r24, com.amazon.alexa.utils.DataDirectoryProvider r25, com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider r26, java.util.concurrent.ScheduledExecutorService r27, int r28, kotlin.jvm.internal.DefaultConstructorMarker r29) {
        /*
            r15 = this;
            r0 = r28
            r1 = r0 & 512(0x200, float:7.175E-43)
            if (r1 == 0) goto Ld
            com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager$1 r1 = new com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager$1
            r1.<init>()
            r12 = r1
            goto Lf
        Ld:
            r12 = r25
        Lf:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L1a
            com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager$2 r1 = new com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager$2
            r1.<init>()
            r13 = r1
            goto L1c
        L1a:
            r13 = r26
        L1c:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L2d
            java.lang.String r0 = "davs"
            java.util.concurrent.ScheduledExecutorService r0 = com.amazon.alexa.utils.concurrent.ManagedExecutorFactory.newSingleThreadScheduledExecutor(r0)
            java.lang.String r1 = "ManagedExecutorFactory.n…UTOR_SERVICE_THREAD_NAME)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r14 = r0
            goto L2f
        L2d:
            r14 = r27
        L2f:
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.wakeword.davs.DavsWakeWordDownloadManager.<init>(com.amazon.alexa.wakeword.pryon.WakeWordModelContentProviderHelper, com.amazon.alexa.wakeword.davs.ArtifactManager, com.amazon.alexa.utils.TimeProvider, com.amazon.alexa.wakeword.davs.NetworkManager, com.amazon.alexa.wakeword.pryon.WakeWordDetectionMetricsListener, com.amazon.alexa.wakeword.davs.DavsClient, com.amazon.alexa.client.crashreporting.CrashReporter, com.amazon.alexa.wakeword.davs.ArtifactDownloadResultListener, boolean, com.amazon.alexa.utils.DataDirectoryProvider, com.amazon.alexa.wakeword.davs.MultiWakeWordFeatureEnabledProvider, java.util.concurrent.ScheduledExecutorService, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Override // com.amazon.alexa.wakeword.pryon.WakeWordDownloadManager
    public void downloadWakeWordModelAsync(@NotNull WakeWordModelUserParams userParams, @Nullable ArtifactDownloadResultListener artifactDownloadResultListener) {
        Intrinsics.checkParameterIsNotNull(userParams, "userParams");
        String str = "Starting download of ww model with params " + userParams;
        if (!this.multiWakeWordFeatureEnabledProvider.isEnabled() && userParams.getWakeWords().size() > 1) {
            Log.w(TAG, "multi ww feature flag is disabled! not starting download");
        } else {
            this.davsAccessExecutor.submit(new WakeWordModelDownloadTask(new WakeWordModelArtifactInfo(userParams.getLocale(), this.isCurrentDeviceHandsFree, userParams.getWakeWords()), this.wakeWordModelContentProviderHelper, this.timeProvider, this.davsClient, this.artifactManager, new InternalArtifactDownloadResultListener(artifactDownloadResultListener)));
        }
    }
}
