package com.amazon.photos.autosave.internal.upload;

import android.net.Uri;
import com.amazon.photos.autosave.UploadPriorityResolver;
import com.amazon.photos.autosave.internal.db.AutosaveTransactionRunner;
import com.amazon.photos.autosave.internal.utils.DateUtils;
import com.amazon.photos.autosave.internal.utils.SystemUtil;
import com.amazon.photos.autosave.model.MediaType;
import com.amazon.photos.autosave.model.ModelExtensionsKt;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.model.ItemType;
import com.amazon.photos.discovery.model.LocalFolder;
import com.amazon.photos.discovery.model.LocalItem;
import com.amazon.photos.uploader.UploadRequest;
import com.amazon.photos.uploader.UploadRequestBuilder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadHelper.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u0017J1\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001eJ\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020%H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/photos/autosave/internal/upload/UploadHelper;", "", "systemUtil", "Lcom/amazon/photos/autosave/internal/utils/SystemUtil;", "uploadPriorityResolver", "Lcom/amazon/photos/autosave/UploadPriorityResolver;", "transactionRunner", "Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "dateUtils", "Lcom/amazon/photos/autosave/internal/utils/DateUtils;", "(Lcom/amazon/photos/autosave/internal/utils/SystemUtil;Lcom/amazon/photos/autosave/UploadPriorityResolver;Lcom/amazon/photos/autosave/internal/db/AutosaveTransactionRunner;Lcom/amazon/photos/discovery/Discovery;Lcom/amazon/photos/autosave/internal/utils/DateUtils;)V", "createModifiedCopyRequest", "Lcom/amazon/photos/uploader/UploadRequest;", "uploadRequest", "addToFamily", "", "createModifiedCopyRequest$AndroidPhotosAutosave_release", "createUploadPathForItem", "", "folderId", "", "createUploadPathForItem$AndroidPhotosAutosave_release", "createUploadRequests", "", "localItems", "Lcom/amazon/photos/discovery/model/LocalItem;", "mediaType", "Lcom/amazon/photos/autosave/model/MediaType;", "createUploadRequests$AndroidPhotosAutosave_release", "getContentDate", "dateTaken", "getContentUri", "Landroid/net/Uri;", "id", "type", "Lcom/amazon/photos/discovery/model/ItemType;", "Companion", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class UploadHelper {
    private static final String CLOUD_ROOT_SEPARATOR = "/";
    public static final Companion Companion = new Companion(null);
    private final DateUtils dateUtils;
    private final Discovery discovery;
    private final SystemUtil systemUtil;
    private final AutosaveTransactionRunner transactionRunner;
    private final UploadPriorityResolver uploadPriorityResolver;

    /* compiled from: UploadHelper.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/autosave/internal/upload/UploadHelper$Companion;", "", "()V", "CLOUD_ROOT_SEPARATOR", "", "AndroidPhotosAutosave_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ItemType.values().length];

        static {
            $EnumSwitchMapping$0[ItemType.PHOTO.ordinal()] = 1;
            $EnumSwitchMapping$0[ItemType.VIDEO.ordinal()] = 2;
        }
    }

    public UploadHelper(@NotNull SystemUtil systemUtil, @NotNull UploadPriorityResolver uploadPriorityResolver, @NotNull AutosaveTransactionRunner transactionRunner, @NotNull Discovery discovery, @NotNull DateUtils dateUtils) {
        Intrinsics.checkParameterIsNotNull(systemUtil, "systemUtil");
        Intrinsics.checkParameterIsNotNull(uploadPriorityResolver, "uploadPriorityResolver");
        Intrinsics.checkParameterIsNotNull(transactionRunner, "transactionRunner");
        Intrinsics.checkParameterIsNotNull(discovery, "discovery");
        Intrinsics.checkParameterIsNotNull(dateUtils, "dateUtils");
        this.systemUtil = systemUtil;
        this.uploadPriorityResolver = uploadPriorityResolver;
        this.transactionRunner = transactionRunner;
        this.discovery = discovery;
        this.dateUtils = dateUtils;
    }

    private final String getContentDate(long j) {
        return DateUtils.formatISO8601WithTimeZone$default(this.dateUtils, j, null, null, 6, null);
    }

    private final Uri getContentUri(long j, ItemType itemType) {
        int i = WhenMappings.$EnumSwitchMapping$0[itemType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            return this.systemUtil.getVideoContentUri$AndroidPhotosAutosave_release(j);
        }
        return this.systemUtil.getImageContentUri$AndroidPhotosAutosave_release(j);
    }

    @NotNull
    public final UploadRequest createModifiedCopyRequest$AndroidPhotosAutosave_release(@NotNull UploadRequest uploadRequest, boolean z) {
        Intrinsics.checkParameterIsNotNull(uploadRequest, "uploadRequest");
        UploadRequestBuilder uploadPriority = new UploadRequestBuilder(uploadRequest.getFilePath(), uploadRequest.getContentUri()).uploadCategory(uploadRequest.getUploadCategory()).addToFamilyVault(z).uploadPriority(uploadRequest.getPriority());
        String md5 = uploadRequest.getMd5();
        if (md5 != null) {
            uploadPriority.md5(md5);
        }
        String visualDigest = uploadRequest.getVisualDigest();
        if (visualDigest != null) {
            uploadPriority.visualDigest(visualDigest);
        }
        String uploadPath = uploadRequest.getUploadPath();
        if (uploadPath != null) {
            uploadPriority.uploadPath(uploadPath);
        }
        String contentDate = uploadRequest.getContentDate();
        if (contentDate != null) {
            uploadPriority.contentDate(contentDate);
        }
        return uploadPriority.build();
    }

    @NotNull
    public final String createUploadPathForItem$AndroidPhotosAutosave_release(final long j) {
        boolean startsWith$default;
        final StringBuilder sb = new StringBuilder();
        sb.append(this.systemUtil.getPicturesDirectory$AndroidPhotosAutosave_release() + '/');
        sb.append(this.systemUtil.getBuildModel$AndroidPhotosAutosave_release() + '/');
        this.transactionRunner.runInTransaction$AndroidPhotosAutosave_release(new Runnable() { // from class: com.amazon.photos.autosave.internal.upload.UploadHelper$createUploadPathForItem$1
            @Override // java.lang.Runnable
            public final void run() {
                Discovery discovery;
                List<Long> listOf;
                discovery = UploadHelper.this.discovery;
                LocalFolderDao localFolderDao = discovery.getDaos().getLocalFolderDao();
                listOf = CollectionsKt__CollectionsJVMKt.listOf(Long.valueOf(j));
                LocalFolder localFolder = (LocalFolder) CollectionsKt.firstOrNull((List<? extends Object>) localFolderDao.getFoldersById(listOf));
                if (localFolder != null) {
                    StringBuilder sb2 = sb;
                    sb2.append(localFolder.getName() + '/');
                    if (sb2 != null) {
                        return;
                    }
                }
                throw new IllegalStateException("Folder does not exist when creating upload path.");
            }
        });
        String cloudFilePath = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(cloudFilePath, "cloudFilePath");
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(cloudFilePath, "/", false, 2, null);
        return startsWith$default ? cloudFilePath : GeneratedOutlineSupport1.outline72("/", cloudFilePath);
    }

    @NotNull
    public final List<UploadRequest> createUploadRequests$AndroidPhotosAutosave_release(@NotNull List<LocalItem> localItems, @NotNull MediaType mediaType, boolean z) {
        int collectionSizeOrDefault;
        Intrinsics.checkParameterIsNotNull(localItems, "localItems");
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        ArrayList<LocalItem> arrayList = new ArrayList();
        for (Object obj : localItems) {
            LocalItem localItem = (LocalItem) obj;
            if (ModelExtensionsKt.toMediaType(localItem.getType()) == mediaType && localItem.getFilePath() != null) {
                arrayList.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (LocalItem localItem2 : arrayList) {
            String filePath = localItem2.getFilePath();
            if (filePath != null) {
                UploadRequestBuilder uploadPriority = new UploadRequestBuilder(filePath, getContentUri(localItem2.getId(), localItem2.getType())).uploadCategory(ModelExtensionsKt.toMediaType(localItem2.getType()).name()).uploadPath(createUploadPathForItem$AndroidPhotosAutosave_release(localItem2.getParentId())).addToFamilyVault(z).uploadPriority(this.uploadPriorityResolver.getUploadPriority(localItem2));
                if (mediaType != MediaType.PHOTO) {
                    uploadPriority.contentDate(getContentDate(localItem2.getDateTaken()));
                }
                String md5 = localItem2.getMd5();
                if (md5 != null) {
                    uploadPriority.md5(md5);
                }
                String visualDigest = localItem2.getVisualDigest();
                if (visualDigest != null) {
                    uploadPriority.visualDigest(visualDigest);
                }
                arrayList2.add(uploadPriority.build());
            } else {
                throw new IllegalArgumentException("Required value was null.".toString());
            }
        }
        return arrayList2;
    }
}
