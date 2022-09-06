package com.amazon.alexa.wakeword.davs;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
/* compiled from: ArtifactDownloadTask.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 :2\u00020\u0001:\u0001:B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010 \u001a\n \"*\u0004\u0018\u00010!0!2\u0006\u0010#\u001a\u00020$H\u0017J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020!H'J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001aH\u0002J\u0010\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020.H\u0002J\u0018\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00142\u0006\u00102\u001a\u00020&H\u0002J \u00103\u001a\u0002002\u0006\u00101\u001a\u00020\u00142\u0006\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u0002002\u0006\u00101\u001a\u00020\u0014H\u0002J\u0018\u00108\u001a\u0002002\u0006\u00101\u001a\u00020\u00142\u0006\u00102\u001a\u00020&H\u0002J\b\u00109\u001a\u000200H\u0016R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\u00020\u0014X¤\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u001aX¤\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u0014X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016¨\u0006;"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadTask;", "Ljava/lang/Runnable;", "artifactInfo", "Lcom/amazon/alexa/wakeword/davs/ArtifactInfo;", "timeProvider", "Lcom/amazon/alexa/utils/TimeProvider;", "davsClient", "Lcom/amazon/alexa/wakeword/davs/DavsClient;", "artifactManager", "Lcom/amazon/alexa/wakeword/davs/ArtifactManager;", "artifactDownloadResultListener", "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;", "(Lcom/amazon/alexa/wakeword/davs/ArtifactInfo;Lcom/amazon/alexa/utils/TimeProvider;Lcom/amazon/alexa/wakeword/davs/DavsClient;Lcom/amazon/alexa/wakeword/davs/ArtifactManager;Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;)V", "getArtifactDownloadResultListener", "()Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;", "setArtifactDownloadResultListener", "(Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadResultListener;)V", "getArtifactInfo", "()Lcom/amazon/alexa/wakeword/davs/ArtifactInfo;", "currentArtifactDownloadTime", "", "getCurrentArtifactDownloadTime", "()J", "setCurrentArtifactDownloadTime", "(J)V", "currentArtifactId", "", "getCurrentArtifactId", "()Ljava/lang/String;", "currentTime", "timestamp", "getTimestamp", "convertStreamToByteArray", "", "kotlin.jvm.PlatformType", "inputStream", "Ljava/io/InputStream;", "getArtifactModelAndPersist", "Lcom/amazon/alexa/wakeword/davs/ArtifactModel;", "artifactIdentifier", "artifactData", "hasArtifactFile", "", "artifactName", "readArtifactAsBytes", "artifact", "Lcom/amazon/alexa/wakeword/davs/ArtifactFile;", "reportAlreadyUpToDate", "", "attemptStartTime", "artifactModel", "reportFailure", "artifactMD5", ADMRegistrationConstants.CALL_EXCEPTION, "Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadException;", "reportInterrupted", "reportSuccess", "run", "Companion", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public abstract class ArtifactDownloadTask implements Runnable {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = ArtifactDownloadTask.class.getSimpleName();
    @VisibleForTesting
    @NotNull
    private ArtifactDownloadResultListener artifactDownloadResultListener;
    @VisibleForTesting
    @NotNull
    private final ArtifactInfo artifactInfo;
    private final ArtifactManager artifactManager;
    private final long currentTime;
    private final DavsClient davsClient;
    private final TimeProvider timeProvider;
    private final long timestamp;

    /* compiled from: ArtifactDownloadTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/ArtifactDownloadTask$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ArtifactDownloadTask(@NotNull ArtifactInfo artifactInfo, @NotNull TimeProvider timeProvider, @NotNull DavsClient davsClient, @NotNull ArtifactManager artifactManager, @NotNull ArtifactDownloadResultListener artifactDownloadResultListener) {
        Intrinsics.checkParameterIsNotNull(artifactInfo, "artifactInfo");
        Intrinsics.checkParameterIsNotNull(timeProvider, "timeProvider");
        Intrinsics.checkParameterIsNotNull(davsClient, "davsClient");
        Intrinsics.checkParameterIsNotNull(artifactManager, "artifactManager");
        Intrinsics.checkParameterIsNotNull(artifactDownloadResultListener, "artifactDownloadResultListener");
        this.artifactInfo = artifactInfo;
        this.timeProvider = timeProvider;
        this.davsClient = davsClient;
        this.artifactManager = artifactManager;
        this.artifactDownloadResultListener = artifactDownloadResultListener;
        this.timestamp = this.timeProvider.currentTimeMillis();
        this.currentTime = this.timeProvider.elapsedRealTime();
    }

    private final boolean hasArtifactFile(String str) {
        return this.artifactManager.hasArtifact(str);
    }

    private final byte[] readArtifactAsBytes(ArtifactFile artifactFile) throws ArtifactDownloadException {
        try {
            InputStream inputStream = artifactFile.getArtifactInputStream();
            Intrinsics.checkExpressionValueIsNotNull(inputStream, "inputStream");
            byte[] convertStreamToByteArray = convertStreamToByteArray(inputStream);
            Intrinsics.checkExpressionValueIsNotNull(convertStreamToByteArray, "convertStreamToByteArray(inputStream)");
            CloseableKt.closeFinally(inputStream, null);
            return convertStreamToByteArray;
        } catch (IOException e) {
            ArtifactDownloadException create = ArtifactDownloadException.create(ArtifactDownloadFailure.READ_ARTIFACT_FILE_FAILURE, e);
            Intrinsics.checkExpressionValueIsNotNull(create, "ArtifactDownloadExceptio…ARTIFACT_FILE_FAILURE, e)");
            throw create;
        }
    }

    private final void reportAlreadyUpToDate(long j, ArtifactModel artifactModel) {
        long j2 = this.currentTime - j;
        GeneratedOutlineSupport1.outline153("reporting artifact already up to date. request duration: ", j2);
        this.artifactDownloadResultListener.onArtifactAlreadyUpToDate(j2, artifactModel);
    }

    private final void reportFailure(long j, String str, ArtifactDownloadException artifactDownloadException) {
        if (Thread.interrupted()) {
            reportInterrupted(j);
            return;
        }
        long j2 = this.currentTime - j;
        String str2 = "reporting download task failed. request duration: " + j2;
        Exception underlyingException = artifactDownloadException.getUnderlyingException();
        this.artifactDownloadResultListener.onArtifactDownloadFailure(j2, str, underlyingException != null ? underlyingException : artifactDownloadException, artifactDownloadException.getDownloadFailureReason().name());
    }

    private final void reportInterrupted(long j) {
        long j2 = this.currentTime - j;
        GeneratedOutlineSupport1.outline153("reporting download task interrupted. request duration: ", j2);
        this.artifactDownloadResultListener.onArtifactDownloadInterrupted(j2);
    }

    private final void reportSuccess(long j, ArtifactModel artifactModel) {
        long j2 = this.currentTime - j;
        GeneratedOutlineSupport1.outline153("reporting download task succeeded. request duration: ", j2);
        this.artifactDownloadResultListener.onArtifactDownloadSuccess(j2, artifactModel);
    }

    @VisibleForTesting
    public byte[] convertStreamToByteArray(@NotNull InputStream inputStream) throws IOException {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        return IOUtils.toByteArray(inputStream);
    }

    @NotNull
    public final ArtifactDownloadResultListener getArtifactDownloadResultListener() {
        return this.artifactDownloadResultListener;
    }

    @NotNull
    public final ArtifactInfo getArtifactInfo() {
        return this.artifactInfo;
    }

    @VisibleForTesting
    @NotNull
    public abstract ArtifactModel getArtifactModelAndPersist(@NotNull String str, @NotNull byte[] bArr);

    protected abstract long getCurrentArtifactDownloadTime();

    @NotNull
    protected abstract String getCurrentArtifactId();

    /* JADX INFO: Access modifiers changed from: protected */
    public final long getTimestamp() {
        return this.timestamp;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        Exception e;
        IllegalArgumentException e2;
        ArtifactDownloadException e3;
        ArtifactManifest manifest;
        boolean equals;
        int i;
        long j = this.currentTime;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("starting downloading artifact:");
        outline107.append(this.artifactInfo.getArtifactType());
        outline107.toString();
        if (Thread.interrupted()) {
            reportInterrupted(j);
            return;
        }
        String artifactType = this.artifactInfo.getArtifactType();
        try {
            try {
                String str2 = "checking artifactType: " + artifactType;
                manifest = this.davsClient.getArtifactManifest(this.artifactInfo.getArtifactRequest());
                Intrinsics.checkExpressionValueIsNotNull(manifest, "manifest");
                Checksum checksum = manifest.getChecksum();
                Intrinsics.checkExpressionValueIsNotNull(checksum, "manifest.checksum");
                str = checksum.getMd5();
                Intrinsics.checkExpressionValueIsNotNull(str, "manifest.checksum.md5");
                try {
                } catch (ArtifactDownloadException e4) {
                    e3 = e4;
                } catch (IllegalArgumentException e5) {
                    e2 = e5;
                } catch (Exception e6) {
                    e = e6;
                }
            } finally {
                if (getCurrentArtifactDownloadTime() == 0) {
                    setCurrentArtifactDownloadTime(this.timestamp);
                }
            }
        } catch (ArtifactDownloadException e7) {
            str = "";
            e3 = e7;
        } catch (IllegalArgumentException e8) {
            str = "";
            e2 = e8;
        } catch (Exception e9) {
            str = "";
            e = e9;
        }
        if (Thread.interrupted()) {
            reportInterrupted(j);
            if (i != 0) {
                return;
            }
            return;
        }
        String newArtifactId = manifest.getArtifactIdentifier();
        String targetArtifactFilename = ArtifactNameFactory.getArtifactFilename(this.artifactInfo, newArtifactId);
        Intrinsics.checkExpressionValueIsNotNull(targetArtifactFilename, "targetArtifactFilename");
        if (hasArtifactFile(targetArtifactFilename)) {
            String str3 = "artifact file " + targetArtifactFilename + " already exists. check for update";
            String currentArtifactId = getCurrentArtifactId();
            ArtifactFile readArtifact = this.artifactManager.readArtifact(targetArtifactFilename);
            Intrinsics.checkExpressionValueIsNotNull(readArtifact, "artifactManager.readArti…t(targetArtifactFilename)");
            byte[] readArtifactAsBytes = readArtifactAsBytes(readArtifact);
            String md5 = CheckSumUtils.getMD5(readArtifactAsBytes);
            if (Intrinsics.areEqual(currentArtifactId, newArtifactId)) {
                equals = StringsKt__StringsJVMKt.equals(md5, str, true);
                if (equals) {
                    Log.i(TAG, "Artifact is already up to date. ArtifactId: " + newArtifactId);
                    reportAlreadyUpToDate(j, getArtifactModelAndPersist(newArtifactId, readArtifactAsBytes));
                    if (getCurrentArtifactDownloadTime() != 0) {
                        return;
                    }
                    setCurrentArtifactDownloadTime(this.timestamp);
                    return;
                }
            }
            Log.i(TAG, "Artifact change detected, pulling the new one");
        }
        Log.i(TAG, "pulling new artifact for: " + targetArtifactFilename);
        ArtifactFile artifactFile = this.davsClient.getArtifact(manifest, targetArtifactFilename);
        Log.i(TAG, "successfully downloaded artifact " + targetArtifactFilename + " (" + newArtifactId + ')');
        Intrinsics.checkExpressionValueIsNotNull(artifactFile, "artifactFile");
        byte[] readArtifactAsBytes2 = readArtifactAsBytes(artifactFile);
        String md52 = CheckSumUtils.getMD5(readArtifactAsBytes2);
        Intrinsics.checkExpressionValueIsNotNull(md52, "CheckSumUtils.getMD5(artifactData)");
        try {
            Intrinsics.checkExpressionValueIsNotNull(newArtifactId, "newArtifactId");
            reportSuccess(j, getArtifactModelAndPersist(newArtifactId, readArtifactAsBytes2));
            if (getCurrentArtifactDownloadTime() != 0) {
                return;
            }
        } catch (ArtifactDownloadException e10) {
            e3 = e10;
            str = md52;
            Log.e(TAG, "Got ArtifactDownloadException while downloading: " + artifactType, e3);
            reportFailure(j, str, e3);
            if (getCurrentArtifactDownloadTime() != 0) {
                return;
            }
            setCurrentArtifactDownloadTime(this.timestamp);
        } catch (IllegalArgumentException e11) {
            e2 = e11;
            str = md52;
            Log.e(TAG, "Got IllegalArgumentException while downloading: " + artifactType, e2);
            ArtifactDownloadException create = ArtifactDownloadException.create(ArtifactDownloadFailure.ILLEGAL_ARGUMENT, e2);
            Intrinsics.checkExpressionValueIsNotNull(create, "ArtifactDownloadExceptio…lure.ILLEGAL_ARGUMENT, e)");
            reportFailure(j, str, create);
            if (getCurrentArtifactDownloadTime() != 0) {
                return;
            }
            setCurrentArtifactDownloadTime(this.timestamp);
        } catch (Exception e12) {
            e = e12;
            str = md52;
            Log.e(TAG, "Got Exception while downloading: " + artifactType, e);
            ArtifactDownloadException create2 = ArtifactDownloadException.create(ArtifactDownloadFailure.OTHER, e);
            Intrinsics.checkExpressionValueIsNotNull(create2, "ArtifactDownloadExceptio…DownloadFailure.OTHER, e)");
            reportFailure(j, str, create2);
            if (getCurrentArtifactDownloadTime() != 0) {
                return;
            }
            setCurrentArtifactDownloadTime(this.timestamp);
        }
        setCurrentArtifactDownloadTime(this.timestamp);
    }

    public final void setArtifactDownloadResultListener(@NotNull ArtifactDownloadResultListener artifactDownloadResultListener) {
        Intrinsics.checkParameterIsNotNull(artifactDownloadResultListener, "<set-?>");
        this.artifactDownloadResultListener = artifactDownloadResultListener;
    }

    protected abstract void setCurrentArtifactDownloadTime(long j);
}
