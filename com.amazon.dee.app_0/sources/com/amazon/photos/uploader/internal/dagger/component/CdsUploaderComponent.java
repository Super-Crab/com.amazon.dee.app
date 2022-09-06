package com.amazon.photos.uploader.internal.dagger.component;

import com.amazon.photos.uploader.cds.CdsUploader;
import com.amazon.photos.uploader.cds.multipart.MultipartDatabaseModule;
import com.amazon.photos.uploader.cds.nodecache.NodeCacheDatabaseModule;
import com.amazon.photos.uploader.internal.dagger.PerAccount;
import com.amazon.photos.uploader.internal.dagger.module.CdsUploaderConfigurationModule;
import dagger.Component;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: CdsUploaderComponent.kt */
@Component(modules = {CdsUploaderConfigurationModule.class, MultipartDatabaseModule.class, NodeCacheDatabaseModule.class})
@PerAccount
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/uploader/internal/dagger/component/CdsUploaderComponent;", "", "inject", "", "cdsUploader", "Lcom/amazon/photos/uploader/cds/CdsUploader;", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface CdsUploaderComponent {
    void inject(@NotNull CdsUploader cdsUploader);
}
