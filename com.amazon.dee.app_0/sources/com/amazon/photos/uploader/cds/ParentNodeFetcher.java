package com.amazon.photos.uploader.cds;

import androidx.annotation.WorkerThread;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.clouddrive.cdasdk.CloudDriveException;
import com.amazon.clouddrive.cdasdk.cds.CDSException;
import com.amazon.photos.uploader.log.UploadLogger;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ParentNodeFetcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002J%\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0012H\u0002¢\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0015\u001a\u00020\rH\u0001¢\u0006\u0002\b\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/photos/uploader/cds/ParentNodeFetcher;", "", "cdsCallClientWrapper", "Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;", "logger", "Lcom/amazon/photos/uploader/log/UploadLogger;", "parentIdCache", "Lcom/amazon/photos/uploader/cds/ParentIdCache;", "(Lcom/amazon/photos/uploader/cds/CdsCallClientWrapper;Lcom/amazon/photos/uploader/log/UploadLogger;Lcom/amazon/photos/uploader/cds/ParentIdCache;)V", "pathSplitter", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "getChildNodeId", "", Message.SERIALIZED_NAME_PARENT_ID, "folder", "getNodeIdForAbsolutePath", "splitPath", "", "(Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/String;", "getParentNodeId", RouteParameter.PATH, "getParentNodeId$AndroidPhotosUploader_release", "getRootId", "Companion", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ParentNodeFetcher {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "ParentNodeFetcher";
    private final CdsCallClientWrapper cdsCallClientWrapper;
    private final UploadLogger logger;
    private final ParentIdCache parentIdCache;
    private final Pattern pathSplitter;

    /* compiled from: ParentNodeFetcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/cds/ParentNodeFetcher$Companion;", "", "()V", "TAG", "", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ParentNodeFetcher(@NotNull CdsCallClientWrapper cdsCallClientWrapper, @NotNull UploadLogger logger, @NotNull ParentIdCache parentIdCache) {
        Intrinsics.checkParameterIsNotNull(cdsCallClientWrapper, "cdsCallClientWrapper");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(parentIdCache, "parentIdCache");
        this.cdsCallClientWrapper = cdsCallClientWrapper;
        this.logger = logger;
        this.parentIdCache = parentIdCache;
        this.pathSplitter = Pattern.compile(Pattern.quote(System.getProperty("file.separator")));
    }

    private final String getChildNodeId(String str, String str2) {
        String childNodeId$AndroidPhotosUploader_release = this.cdsCallClientWrapper.getChildNodeId$AndroidPhotosUploader_release(str, str2);
        return childNodeId$AndroidPhotosUploader_release == null ? this.cdsCallClientWrapper.createNode$AndroidPhotosUploader_release(str, str2) : childNodeId$AndroidPhotosUploader_release;
    }

    private final String getNodeIdForAbsolutePath(String str, String[] strArr) {
        boolean isBlank;
        for (String str2 : strArr) {
            isBlank = StringsKt__StringsJVMKt.isBlank(str2);
            if (!isBlank) {
                str = getChildNodeId(str, str2);
            }
        }
        return str;
    }

    private final String getRootId() {
        ParentIdCache parentIdCache = this.parentIdCache;
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        String str2 = parentIdCache.get(str);
        if (str2 != null) {
            return str2;
        }
        String rootNodeId$AndroidPhotosUploader_release = this.cdsCallClientWrapper.getRootNodeId$AndroidPhotosUploader_release();
        if (rootNodeId$AndroidPhotosUploader_release == null) {
            return null;
        }
        ParentIdCache parentIdCache2 = this.parentIdCache;
        String str3 = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str3, "File.separator");
        parentIdCache2.put(str3, rootNodeId$AndroidPhotosUploader_release);
        return rootNodeId$AndroidPhotosUploader_release;
    }

    @WorkerThread
    @Nullable
    public final String getParentNodeId$AndroidPhotosUploader_release(@NotNull String path) throws CDSException, CloudDriveException, IOException, InterruptedIOException {
        boolean isBlank;
        Intrinsics.checkParameterIsNotNull(path, "path");
        isBlank = StringsKt__StringsJVMKt.isBlank(path);
        if (!isBlank) {
            String str = this.parentIdCache.get(path);
            if (str != null) {
                return str;
            }
            String rootId = getRootId();
            if (rootId == null) {
                return null;
            }
            String[] split = this.pathSplitter.split(path);
            Intrinsics.checkExpressionValueIsNotNull(split, "pathSplitter.split(path)");
            String nodeIdForAbsolutePath = getNodeIdForAbsolutePath(rootId, split);
            if (nodeIdForAbsolutePath != null) {
                this.parentIdCache.put(path, nodeIdForAbsolutePath);
            }
            UploadLogger uploadLogger = this.logger;
            uploadLogger.i$AndroidPhotosUploader_release(TAG, "Returning parent id " + nodeIdForAbsolutePath);
            return nodeIdForAbsolutePath;
        }
        throw new IllegalArgumentException("Blank Path not allowed.".toString());
    }
}
