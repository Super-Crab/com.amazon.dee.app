package com.amazon.photos.uploader;

import com.amazon.photos.uploader.dao.BlockerDao;
import com.amazon.photos.uploader.dao.EventDao;
import com.amazon.photos.uploader.dao.RequestDao;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class UploaderDaos_MembersInjector implements MembersInjector<UploaderDaos> {
    private final Provider<EventDao> p0Provider;
    private final Provider<RequestDao> p0Provider2;
    private final Provider<BlockerDao> p0Provider3;

    public UploaderDaos_MembersInjector(Provider<EventDao> provider, Provider<RequestDao> provider2, Provider<BlockerDao> provider3) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
    }

    public static MembersInjector<UploaderDaos> create(Provider<EventDao> provider, Provider<RequestDao> provider2, Provider<BlockerDao> provider3) {
        return new UploaderDaos_MembersInjector(provider, provider2, provider3);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(UploaderDaos uploaderDaos) {
        uploaderDaos.setEventDao$AndroidPhotosUploader_release(this.p0Provider.mo10268get());
        uploaderDaos.setRequestDao$AndroidPhotosUploader_release(this.p0Provider2.mo10268get());
        uploaderDaos.setBlockerDao$AndroidPhotosUploader_release(this.p0Provider3.mo10268get());
    }
}
