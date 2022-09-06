package com.amazon.photos.uploader.internal;

import android.net.Uri;
import androidx.room.TypeConverter;
import com.amazon.photos.uploader.UploadErrorCategory;
import com.amazon.photos.uploader.UploadState;
import com.amazon.photos.uploader.blockers.BackoffBlocker;
import com.amazon.photos.uploader.blockers.Blocker;
import com.amazon.photos.uploader.blockers.CustomBlocker;
import com.amazon.photos.uploader.blockers.LowBatteryBlocker;
import com.amazon.photos.uploader.blockers.MeteredConnectionBlocker;
import com.amazon.photos.uploader.blockers.NoNetworkBlocker;
import com.amazon.photos.uploader.blockers.NotChargingBlocker;
import com.amazon.photos.uploader.blockers.PauseBlocker;
import com.amazon.photos.uploader.blockers.QuotaExceededBlocker;
import com.amazon.photos.uploader.blockers.TokenUnavailableBlocker;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Converters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\u0014\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0004H\u0007¨\u0006\u0017"}, d2 = {"Lcom/amazon/photos/uploader/internal/Converters;", "", "()V", "fromBlocker", "", "blocker", "Lcom/amazon/photos/uploader/blockers/Blocker;", "fromBlockerString", "fromContentUri", "contentUri", "Landroid/net/Uri;", "fromContentUriString", "contentUriString", "fromUploadErrorCategory", "errorCategory", "Lcom/amazon/photos/uploader/UploadErrorCategory;", "fromUploadErrorCategoryString", "fromUploadState", "state", "Lcom/amazon/photos/uploader/UploadState;", "fromUploadStateString", "uploadState", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Converters {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String DEFAULT_CONTENT_URI_STRING = "no_file";

    /* compiled from: Converters.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/Converters$Companion;", "", "()V", "DEFAULT_CONTENT_URI_STRING", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @TypeConverter
    @Nullable
    public final String fromBlocker(@Nullable Blocker blocker) {
        if (blocker != null) {
            return blocker.getName();
        }
        return null;
    }

    @TypeConverter
    @Nullable
    public final Blocker fromBlockerString(@Nullable String str) {
        if (str != null) {
            return Intrinsics.areEqual(str, MeteredConnectionBlocker.INSTANCE.getName()) ? MeteredConnectionBlocker.INSTANCE : Intrinsics.areEqual(str, NoNetworkBlocker.INSTANCE.getName()) ? NoNetworkBlocker.INSTANCE : Intrinsics.areEqual(str, QuotaExceededBlocker.INSTANCE.getName()) ? QuotaExceededBlocker.INSTANCE : Intrinsics.areEqual(str, TokenUnavailableBlocker.INSTANCE.getName()) ? TokenUnavailableBlocker.INSTANCE : Intrinsics.areEqual(str, NotChargingBlocker.INSTANCE.getName()) ? NotChargingBlocker.INSTANCE : Intrinsics.areEqual(str, LowBatteryBlocker.INSTANCE.getName()) ? LowBatteryBlocker.INSTANCE : Intrinsics.areEqual(str, BackoffBlocker.INSTANCE.getName()) ? BackoffBlocker.INSTANCE : Intrinsics.areEqual(str, PauseBlocker.INSTANCE.getName()) ? PauseBlocker.INSTANCE : new CustomBlocker(str);
        }
        return null;
    }

    @TypeConverter
    @NotNull
    public final String fromContentUri(@NotNull Uri contentUri) {
        Intrinsics.checkParameterIsNotNull(contentUri, "contentUri");
        if (Intrinsics.areEqual(contentUri, Uri.EMPTY)) {
            return DEFAULT_CONTENT_URI_STRING;
        }
        String uri = contentUri.toString();
        Intrinsics.checkExpressionValueIsNotNull(uri, "contentUri.toString()");
        return uri;
    }

    @TypeConverter
    @NotNull
    public final Uri fromContentUriString(@NotNull String contentUriString) {
        Intrinsics.checkParameterIsNotNull(contentUriString, "contentUriString");
        if (Intrinsics.areEqual(contentUriString, DEFAULT_CONTENT_URI_STRING)) {
            Uri uri = Uri.EMPTY;
            Intrinsics.checkExpressionValueIsNotNull(uri, "Uri.EMPTY");
            return uri;
        }
        Uri parse = Uri.parse(contentUriString);
        Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(contentUriString)");
        return parse;
    }

    @TypeConverter
    @Nullable
    public final String fromUploadErrorCategory(@Nullable UploadErrorCategory uploadErrorCategory) {
        if (uploadErrorCategory != null) {
            return uploadErrorCategory.name();
        }
        return null;
    }

    @TypeConverter
    @Nullable
    public final UploadErrorCategory fromUploadErrorCategoryString(@Nullable String str) {
        if (str != null) {
            return UploadErrorCategory.valueOf(str);
        }
        return null;
    }

    @TypeConverter
    @NotNull
    public final String fromUploadState(@NotNull UploadState state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        return state.name();
    }

    @TypeConverter
    @NotNull
    public final UploadState fromUploadStateString(@NotNull String uploadState) {
        Intrinsics.checkParameterIsNotNull(uploadState, "uploadState");
        return UploadState.valueOf(uploadState);
    }
}
