package com.amazon.photos.uploader.cds;

import com.amazon.clouddrive.android.core.interfaces.Metrics;
import com.amazon.photos.uploader.UploadFrameworkContext;
import com.amazon.photos.uploader.cds.multipart.CdsMultiPartUploader;
import com.amazon.photos.uploader.internal.utils.FileUtils;
import com.amazon.photos.uploader.log.UploadLogger;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class CdsUploader_MembersInjector implements MembersInjector<CdsUploader> {
    private final Provider<CdsSinglePartUploader> p0Provider;
    private final Provider<CdsMultiPartUploader> p0Provider2;
    private final Provider<UploadFrameworkContext> p0Provider3;
    private final Provider<Metrics> p0Provider4;
    private final Provider<FileUtils> p0Provider5;
    private final Provider<ParentIdCache> p0Provider6;
    private final Provider<UploadLogger> p0Provider7;

    public CdsUploader_MembersInjector(Provider<CdsSinglePartUploader> provider, Provider<CdsMultiPartUploader> provider2, Provider<UploadFrameworkContext> provider3, Provider<Metrics> provider4, Provider<FileUtils> provider5, Provider<ParentIdCache> provider6, Provider<UploadLogger> provider7) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
    }

    public static MembersInjector<CdsUploader> create(Provider<CdsSinglePartUploader> provider, Provider<CdsMultiPartUploader> provider2, Provider<UploadFrameworkContext> provider3, Provider<Metrics> provider4, Provider<FileUtils> provider5, Provider<ParentIdCache> provider6, Provider<UploadLogger> provider7) {
        return new CdsUploader_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CdsUploader cdsUploader) {
        cdsUploader.setSinglePartUploader$AndroidPhotosUploader_release(this.p0Provider.mo10268get());
        cdsUploader.setMultiPartUploader$AndroidPhotosUploader_release(this.p0Provider2.mo10268get());
        cdsUploader.setUploadFrameworkContext$AndroidPhotosUploader_release(this.p0Provider3.mo10268get());
        cdsUploader.setMetrics$AndroidPhotosUploader_release(this.p0Provider4.mo10268get());
        cdsUploader.setFileUtils$AndroidPhotosUploader_release(this.p0Provider5.mo10268get());
        cdsUploader.setParentIdCache$AndroidPhotosUploader_release(this.p0Provider6.mo10268get());
        cdsUploader.setLogger$AndroidPhotosUploader_release(this.p0Provider7.mo10268get());
    }
}
