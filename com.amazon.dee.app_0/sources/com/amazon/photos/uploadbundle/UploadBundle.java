package com.amazon.photos.uploadbundle;

import androidx.annotation.AnyThread;
import com.amazon.photos.autosave.AutosaveManager;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import javax.security.auth.Destroyable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: UploadBundle.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u000e\u001a\u00020\u000fH'J\b\u0010\u0010\u001a\u00020\u000fH'R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/amazon/photos/uploadbundle/UploadBundle;", "Ljavax/security/auth/Destroyable;", "autosaveManager", "Lcom/amazon/photos/autosave/AutosaveManager;", "getAutosaveManager", "()Lcom/amazon/photos/autosave/AutosaveManager;", "discovery", "Lcom/amazon/photos/discovery/Discovery;", "getDiscovery", "()Lcom/amazon/photos/discovery/Discovery;", "uploadManager", "Lcom/amazon/photos/uploader/UploadManager;", "getUploadManager", "()Lcom/amazon/photos/uploader/UploadManager;", "pauseAllWork", "", "resumeAllWork", "AndroidPhotosUploadBundle_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface UploadBundle extends Destroyable {
    @NotNull
    AutosaveManager getAutosaveManager();

    @NotNull
    Discovery getDiscovery();

    @NotNull
    UploadManager getUploadManager();

    @AnyThread
    void pauseAllWork();

    @AnyThread
    void resumeAllWork();
}
