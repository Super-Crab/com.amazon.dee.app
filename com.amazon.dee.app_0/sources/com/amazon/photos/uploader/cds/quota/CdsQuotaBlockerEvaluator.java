package com.amazon.photos.uploader.cds.quota;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.clouddrive.android.core.interfaces.MetricName;
import com.amazon.clouddrive.android.core.interfaces.MetricRecordingType;
import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.clouddrive.cdasdk.CDClient;
import com.amazon.clouddrive.cdasdk.cds.CDSCalls;
import com.amazon.clouddrive.cdasdk.cds.account.Benefit;
import com.amazon.clouddrive.cdasdk.cds.account.GetQuotaRequest;
import com.amazon.clouddrive.cdasdk.cds.account.GetQuotaResponse;
import com.amazon.clouddrive.cdasdk.cds.account.Grant;
import com.amazon.photos.uploader.ResultMetadata;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.CacheableBlocker;
import com.amazon.photos.uploader.blockers.QuotaExceededBlocker;
import com.amazon.photos.uploader.blockers.RequestBlockerEvaluator;
import com.amazon.photos.uploader.cds.CdsMetrics;
import com.amazon.photos.uploader.customblockers.BlockerReevaluator;
import com.amazon.photos.uploader.internal.utils.ISO8601;
import com.amazon.photos.uploader.log.UploadLogger;
import com.amazon.photos.uploader.observables.AbandonedRequestInfo;
import com.amazon.photos.uploader.observables.UploadRequestObserver;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Single;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CdsQuotaBlockerEvaluator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 =2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001=B3\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fB1\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u0011J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0015\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001¢\u0006\u0002\b!J\b\u0010\"\u001a\u00020\u0018H\u0017J\b\u0010#\u001a\u00020\u0013H\u0016J\u0010\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001cH\u0016J\u0016\u0010&\u001a\u00020\u00182\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0016J\u0018\u0010*\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u001aH\u0016J\"\u0010,\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001c2\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200H\u0016J \u00101\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\u001e2\u0006\u00103\u001a\u00020\u001eH\u0016J\u0010\u00104\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001cH\u0016J\u0018\u00105\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001c2\u0006\u00106\u001a\u000207H\u0016J\u0012\u00108\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u00109\u001a\u00020\u00182\u0006\u0010:\u001a\u00020;H\u0002J\f\u0010<\u001a\u00020 *\u00020 H\u0002R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/amazon/photos/uploader/cds/quota/CdsQuotaBlockerEvaluator;", "Lcom/amazon/photos/uploader/blockers/RequestBlockerEvaluator;", "Lcom/amazon/photos/uploader/observables/UploadRequestObserver;", "Lcom/amazon/photos/uploader/blockers/CacheableBlocker;", "Ljavax/security/auth/Destroyable;", "uploaderContext", "Lcom/amazon/photos/uploader/UploadFrameworkContext;", "metrics", "Lcom/amazon/clouddrive/android/core/interfaces/Metrics;", "logger", "Lcom/amazon/clouddrive/android/core/interfaces/Logger;", "cdClient", "Lcom/amazon/clouddrive/cdasdk/CDClient;", "blockerReevaluator", "Lcom/amazon/photos/uploader/customblockers/BlockerReevaluator;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/clouddrive/android/core/interfaces/Logger;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/customblockers/BlockerReevaluator;)V", "Lcom/amazon/photos/uploader/log/UploadLogger;", "(Lcom/amazon/photos/uploader/UploadFrameworkContext;Lcom/amazon/clouddrive/android/core/interfaces/Metrics;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/clouddrive/cdasdk/CDClient;Lcom/amazon/photos/uploader/customblockers/BlockerReevaluator;)V", "destroyed", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "destroy", "", "getBlocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "request", "Lcom/amazon/photos/uploader/UploadRequest;", "getFileSize", "", "filePath", "", "getFileSize$AndroidPhotosUploader_release", "invalidateCacheBlocking", "isDestroyed", "onRequestAdded", "uploadRequest", "onRequestsAbandoned", "abandonedRequestInfoList", "", "Lcom/amazon/photos/uploader/observables/AbandonedRequestInfo;", "onUploadBlocked", "blocker", "onUploadFailed", "ex", "", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "onUploadProgressUpdate", "currentProgress", "maxProgress", "onUploadStarted", "onUploadSucceeded", "resultMetadata", "Lcom/amazon/photos/uploader/ResultMetadata;", "queryBlocker", "updateQuotas", "quotaResponse", "Lcom/amazon/clouddrive/cdasdk/cds/account/GetQuotaResponse;", "quotaCategory", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class CdsQuotaBlockerEvaluator implements RequestBlockerEvaluator, UploadRequestObserver, CacheableBlocker, Destroyable {
    @NotNull
    public static final String CDS_QUOTA_BLOCKER_EVALUATOR_PREFERENCES = "CDS_QUOTA_BLOCKER_EVALUATOR";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY = "QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY";
    @NotNull
    public static final String QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY = "QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY";
    @NotNull
    public static final String QUOTA_DATE_SHARED_PREFERENCE_KEY = "QUOTA_DATE_SHARED_PREFERENCE_KEY";
    @NotNull
    public static final String SUBSCRIPTION_HASH_SHARED_PREFERENCE_KEY = "SUBSCRIPTION_HASH_SHARED_PREFERENCE_KEY";
    @NotNull
    public static final String TAG = "CdsQuotaBlockerEvaluator";
    @NotNull
    public static final String VIDEO_CATEGORY = "VIDEO";
    private final BlockerReevaluator blockerReevaluator;
    private final CDClient cdClient;
    private boolean destroyed;
    private final UploadLogger logger;
    private final Metrics metrics;
    private final SharedPreferences sharedPreferences;

    /* compiled from: CdsQuotaBlockerEvaluator.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/photos/uploader/cds/quota/CdsQuotaBlockerEvaluator$Companion;", "", "()V", "CDS_QUOTA_BLOCKER_EVALUATOR_PREFERENCES", "", CdsQuotaBlockerEvaluator.QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY, CdsQuotaBlockerEvaluator.QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY, CdsQuotaBlockerEvaluator.QUOTA_DATE_SHARED_PREFERENCE_KEY, CdsQuotaBlockerEvaluator.SUBSCRIPTION_HASH_SHARED_PREFERENCE_KEY, "TAG", "VIDEO_CATEGORY", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public CdsQuotaBlockerEvaluator(@NotNull UploadFrameworkContext uploaderContext, @NotNull Metrics metrics, @NotNull UploadLogger logger, @NotNull CDClient cdClient, @Nullable BlockerReevaluator blockerReevaluator) {
        Intrinsics.checkParameterIsNotNull(uploaderContext, "uploaderContext");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
        this.metrics = metrics;
        this.logger = logger;
        this.cdClient = cdClient;
        this.blockerReevaluator = blockerReevaluator;
        Context applicationContext = uploaderContext.getApplicationContext();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CDS_QUOTA_BLOCKER_EVALUATOR_");
        outline107.append(uploaderContext.getHashedDirectedId());
        outline107.append(')');
        this.sharedPreferences = applicationContext.getSharedPreferences(outline107.toString(), 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String quotaCategory(@NotNull String str) {
        boolean contains$default;
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "VIDEO", false, 2, (Object) null);
        return contains$default ? "VIDEO" : str;
    }

    private final void updateQuotas(GetQuotaResponse getQuotaResponse) {
        String str = "";
        int i = 0;
        for (Benefit benefit : getQuotaResponse.getBenefits()) {
            i ^= benefit.hashCode();
        }
        for (Grant grant : getQuotaResponse.getGrants()) {
            i ^= grant.hashCode();
        }
        for (String str2 : getQuotaResponse.getPlans()) {
            i ^= str2.hashCode();
        }
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        if (!this.sharedPreferences.contains(SUBSCRIPTION_HASH_SHARED_PREFERENCE_KEY) || i != this.sharedPreferences.getInt(SUBSCRIPTION_HASH_SHARED_PREFERENCE_KEY, 0)) {
            edit.putInt(SUBSCRIPTION_HASH_SHARED_PREFERENCE_KEY, i);
            edit.putStringSet(QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY, new LinkedHashSet());
        }
        try {
            ISO8601 iso8601 = ISO8601.INSTANCE;
            String lastCalculated = getQuotaResponse.getLastCalculated();
            Intrinsics.checkExpressionValueIsNotNull(lastCalculated, "quotaResponse.lastCalculated");
            Date dateFromString = iso8601.getDateFromString(lastCalculated);
            ISO8601 iso86012 = ISO8601.INSTANCE;
            String string = this.sharedPreferences.getString(QUOTA_DATE_SHARED_PREFERENCE_KEY, str);
            if (string != null) {
                str = string;
            }
            Date dateFromString2 = iso86012.getDateFromString(str);
            if (dateFromString != null && (dateFromString2 == null || dateFromString.after(dateFromString2))) {
                edit.putString(QUOTA_DATE_SHARED_PREFERENCE_KEY, getQuotaResponse.getLastCalculated());
                Long available = getQuotaResponse.getAvailable();
                Intrinsics.checkExpressionValueIsNotNull(available, "quotaResponse.available");
                edit.putLong(QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY, available.longValue());
            }
        } catch (ParseException e) {
            this.logger.e$AndroidPhotosUploader_release(TAG, "Could not parse date associated with quota response.", e);
            Long available2 = getQuotaResponse.getAvailable();
            Intrinsics.checkExpressionValueIsNotNull(available2, "quotaResponse.available");
            edit.putLong(QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY, available2.longValue());
            this.metrics.recordSimpleEvent(TAG, CdsQuotaBlockerEvaluator$updateQuotas$1.INSTANCE, new MetricRecordingType[0]);
        }
        edit.apply();
    }

    @Override // javax.security.auth.Destroyable
    public void destroy() {
        this.sharedPreferences.edit().clear().apply();
        this.destroyed = true;
    }

    @Override // com.amazon.photos.uploader.blockers.RequestBlockerEvaluator
    @Nullable
    public synchronized Blocker getBlocker(@NotNull UploadRequest request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        return queryBlocker(request);
    }

    @VisibleForTesting
    public final long getFileSize$AndroidPhotosUploader_release(@NotNull String filePath) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        return new File(filePath).length();
    }

    @Override // com.amazon.photos.uploader.blockers.CacheableBlocker
    @WorkerThread
    public synchronized void invalidateCacheBlocking() {
        if (isDestroyed()) {
            return;
        }
        this.logger.i$AndroidPhotosUploader_release(TAG, "Checking quota subscription info for changes.");
        GetQuotaRequest getQuotaRequest = new GetQuotaRequest();
        CDSCalls cDSCalls = this.cdClient.getCDSCalls();
        Intrinsics.checkExpressionValueIsNotNull(cDSCalls, "cdClient.cdsCalls");
        Single<GetQuotaResponse> quota = cDSCalls.getAccountCalls().getQuota(getQuotaRequest);
        Intrinsics.checkExpressionValueIsNotNull(quota, "cdClient.cdsCalls.accoun…getQuota(getQuotaRequest)");
        this.metrics.recordSimpleEvent(TAG, CdsQuotaBlockerEvaluator$invalidateCacheBlocking$1.INSTANCE, new MetricRecordingType[0]);
        GetQuotaResponse blockingGet = quota.blockingGet();
        Unit unit = Unit.INSTANCE;
        if (blockingGet != null) {
            updateQuotas(blockingGet);
        }
    }

    @Override // javax.security.auth.Destroyable
    public boolean isDestroyed() {
        return this.destroyed;
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestAdded(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onRequestsAbandoned(@NotNull List<AbandonedRequestInfo> abandonedRequestInfoList) {
        Intrinsics.checkParameterIsNotNull(abandonedRequestInfoList, "abandonedRequestInfoList");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Blocker blocker) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blocker, "blocker");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadBlocked(@NotNull UploadRequest uploadRequest, @NotNull Set<? extends Blocker> blockers) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(blockers, "blockers");
        UploadRequestObserver.DefaultImpls.onUploadBlocked(this, uploadRequest, blockers);
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public synchronized void onUploadFailed(@NotNull UploadRequest uploadRequest, @Nullable Throwable th, @NotNull UploadErrorCategory errorCategory) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(errorCategory, "errorCategory");
        if (errorCategory == UploadErrorCategory.QUOTA_ERROR && !isDestroyed()) {
            final String uploadCategory = uploadRequest.getUploadCategory();
            long fileSize$AndroidPhotosUploader_release = getFileSize$AndroidPhotosUploader_release(uploadRequest.getFilePath());
            SharedPreferences.Editor edit = this.sharedPreferences.edit();
            long j = this.sharedPreferences.getLong(QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY, Long.MAX_VALUE);
            Set<String> stringSet = this.sharedPreferences.getStringSet(QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY, new LinkedHashSet());
            if (stringSet == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(stringSet, "sharedPreferences.getStr…CE_KEY, mutableSetOf())!!");
            boolean z = !stringSet.contains(uploadCategory) && !stringSet.contains(quotaCategory(uploadCategory));
            if (z) {
                this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.quota.CdsQuotaBlockerEvaluator$onUploadFailed$1
                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                    @NotNull
                    public final String getEventName() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.QUOTA_ERROR_BLOCKED_NEW_CATEGORY_PREFIX);
                        outline107.append(uploadCategory);
                        return outline107.toString();
                    }
                }, new MetricRecordingType[0]);
                this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.quota.CdsQuotaBlockerEvaluator$onUploadFailed$2
                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                    @NotNull
                    public final String getEventName() {
                        String quotaCategory;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.QUOTA_ERROR_BLOCKED_NEW_CATEGORY_TYPE_PREFIX);
                        quotaCategory = CdsQuotaBlockerEvaluator.this.quotaCategory(uploadCategory);
                        outline107.append(quotaCategory);
                        return outline107.toString();
                    }
                }, new MetricRecordingType[0]);
                UploadLogger uploadLogger = this.logger;
                uploadLogger.i$AndroidPhotosUploader_release(TAG, "Upload Failed. Add new entry with size " + fileSize$AndroidPhotosUploader_release + " for upload category " + uploadCategory);
                stringSet.add(quotaCategory(uploadCategory));
                edit.remove(QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY);
                edit.apply();
                edit.putStringSet(QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY, stringSet);
            } else {
                this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.quota.CdsQuotaBlockerEvaluator$onUploadFailed$3
                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                    @NotNull
                    public final String getEventName() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.QUOTA_ERROR_BLOCKED_EXISTING_CATEGORY_PREFIX);
                        outline107.append(uploadCategory);
                        return outline107.toString();
                    }
                }, new MetricRecordingType[0]);
                this.metrics.recordSimpleEvent(TAG, new MetricName() { // from class: com.amazon.photos.uploader.cds.quota.CdsQuotaBlockerEvaluator$onUploadFailed$4
                    @Override // com.amazon.clouddrive.android.core.interfaces.MetricName
                    @NotNull
                    public final String getEventName() {
                        String quotaCategory;
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(CdsMetrics.QUOTA_ERROR_BLOCKED_EXISTING_CATEGORY_TYPE_PREFIX);
                        quotaCategory = CdsQuotaBlockerEvaluator.this.quotaCategory(uploadCategory);
                        outline107.append(quotaCategory);
                        return outline107.toString();
                    }
                }, new MetricRecordingType[0]);
                UploadLogger uploadLogger2 = this.logger;
                uploadLogger2.i$AndroidPhotosUploader_release(TAG, "Upload Failed. Update the remaining quota by min of " + fileSize$AndroidPhotosUploader_release + " and " + j + " for upload category " + uploadCategory);
            }
            if (fileSize$AndroidPhotosUploader_release < j) {
                edit.putLong(QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY, Math.min(fileSize$AndroidPhotosUploader_release, j));
            }
            edit.apply();
            BlockerReevaluator blockerReevaluator = this.blockerReevaluator;
            if (blockerReevaluator != null) {
                UploadLogger uploadLogger3 = this.logger;
                uploadLogger3.i$AndroidPhotosUploader_release(TAG, "Upload failed because of over-quota for category " + quotaCategory(uploadCategory) + ". Is category new [" + z + "]. Calling re-evaluate blockers.");
                blockerReevaluator.reevaluateBlockers();
            }
        }
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadProgressUpdate(@NotNull UploadRequest uploadRequest, long j, long j2) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadStarted(@NotNull UploadRequest uploadRequest) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
    }

    @Override // com.amazon.photos.uploader.observables.UploadRequestObserver
    public void onUploadSucceeded(@NotNull UploadRequest uploadRequest, @NotNull ResultMetadata resultMetadata) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        Intrinsics.checkParameterIsNotNull(resultMetadata, "resultMetadata");
    }

    @Override // com.amazon.photos.uploader.blockers.RequestBlockerEvaluator
    @Nullable
    public synchronized Blocker queryBlocker(@NotNull UploadRequest request) {
        Set<String> emptySet;
        Intrinsics.checkParameterIsNotNull(request, "request");
        String uploadCategory = request.getUploadCategory();
        QuotaExceededBlocker quotaExceededBlocker = null;
        if (isDestroyed()) {
            return null;
        }
        SharedPreferences sharedPreferences = this.sharedPreferences;
        emptySet = SetsKt__SetsKt.emptySet();
        Set<String> stringSet = sharedPreferences.getStringSet(QUOTA_CATEGORIES_SHARED_PREFERENCE_KEY, emptySet);
        if (stringSet != null && (stringSet.contains(uploadCategory) || stringSet.contains(quotaCategory(uploadCategory)))) {
            long j = this.sharedPreferences.getLong(QUOTA_AVAILABLE_SHARED_PREFERENCE_KEY, Long.MAX_VALUE);
            long fileSize$AndroidPhotosUploader_release = getFileSize$AndroidPhotosUploader_release(request.getFilePath());
            if (fileSize$AndroidPhotosUploader_release >= j) {
                UploadLogger uploadLogger = this.logger;
                uploadLogger.i$AndroidPhotosUploader_release(TAG, "Returning Blocker. New upload size " + fileSize$AndroidPhotosUploader_release + " is at least the size of remaining quota " + j + " for upload category " + uploadCategory);
                this.metrics.recordSimpleEvent(TAG, CdsQuotaBlockerEvaluator$queryBlocker$1.INSTANCE, new MetricRecordingType[0]);
                quotaExceededBlocker = QuotaExceededBlocker.INSTANCE;
            }
            return quotaExceededBlocker;
        }
        return null;
    }

    public /* synthetic */ CdsQuotaBlockerEvaluator(UploadFrameworkContext uploadFrameworkContext, Metrics metrics, UploadLogger uploadLogger, CDClient cDClient, BlockerReevaluator blockerReevaluator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uploadFrameworkContext, metrics, uploadLogger, cDClient, (i & 16) != 0 ? null : blockerReevaluator);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CdsQuotaBlockerEvaluator(@NotNull UploadFrameworkContext uploaderContext, @NotNull Metrics metrics, @NotNull Logger logger, @NotNull CDClient cdClient, @Nullable BlockerReevaluator blockerReevaluator) {
        this(uploaderContext, metrics, new UploadLogger(logger, UploadLogger.SecurityLevel.OBFUSCATED), cdClient, blockerReevaluator);
        Intrinsics.checkParameterIsNotNull(uploaderContext, "uploaderContext");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(cdClient, "cdClient");
    }

    public /* synthetic */ CdsQuotaBlockerEvaluator(UploadFrameworkContext uploadFrameworkContext, Metrics metrics, Logger logger, CDClient cDClient, BlockerReevaluator blockerReevaluator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uploadFrameworkContext, metrics, logger, cDClient, (i & 16) != 0 ? null : blockerReevaluator);
    }
}
