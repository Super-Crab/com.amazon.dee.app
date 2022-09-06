package com.amazon.photos.autosave;

import com.amazon.photos.autosave.internal.AutosaveOperations;
import com.amazon.photos.autosave.internal.db.DestroyableDatabaseWrapper;
import com.amazon.photos.autosave.internal.observers.AutosaveObserverCoordinator;
import com.amazon.photos.autosave.internal.preferences.InternalPreferences;
import com.amazon.photos.discovery.Discovery;
import com.amazon.photos.uploader.UploadManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class AutosaveManager_MembersInjector implements MembersInjector<AutosaveManager> {
    private final Provider<AutosaveFrameworkContext> p0Provider;
    private final Provider<DestroyableDatabaseWrapper> p0Provider2;
    private final Provider<UploadManager> p0Provider3;
    private final Provider<Discovery> p0Provider4;
    private final Provider<InternalPreferences> p0Provider5;
    private final Provider<AutosaveOperations> p0Provider6;
    private final Provider<AutosaveObserverCoordinator> p0Provider7;

    public AutosaveManager_MembersInjector(Provider<AutosaveFrameworkContext> provider, Provider<DestroyableDatabaseWrapper> provider2, Provider<UploadManager> provider3, Provider<Discovery> provider4, Provider<InternalPreferences> provider5, Provider<AutosaveOperations> provider6, Provider<AutosaveObserverCoordinator> provider7) {
        this.p0Provider = provider;
        this.p0Provider2 = provider2;
        this.p0Provider3 = provider3;
        this.p0Provider4 = provider4;
        this.p0Provider5 = provider5;
        this.p0Provider6 = provider6;
        this.p0Provider7 = provider7;
    }

    public static MembersInjector<AutosaveManager> create(Provider<AutosaveFrameworkContext> provider, Provider<DestroyableDatabaseWrapper> provider2, Provider<UploadManager> provider3, Provider<Discovery> provider4, Provider<InternalPreferences> provider5, Provider<AutosaveOperations> provider6, Provider<AutosaveObserverCoordinator> provider7) {
        return new AutosaveManager_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AutosaveManager autosaveManager) {
        autosaveManager.setAutosaveFrameworkContext$AndroidPhotosAutosave_release(this.p0Provider.mo10268get());
        autosaveManager.setDatabaseWrapper$AndroidPhotosAutosave_release(this.p0Provider2.mo10268get());
        autosaveManager.setUploadManager$AndroidPhotosAutosave_release(this.p0Provider3.mo10268get());
        autosaveManager.setDiscovery$AndroidPhotosAutosave_release(this.p0Provider4.mo10268get());
        autosaveManager.setAutosavePreferences$AndroidPhotosAutosave_release(this.p0Provider5.mo10268get());
        autosaveManager.setOperations$AndroidPhotosAutosave_release(this.p0Provider6.mo10268get());
        autosaveManager.setAutosaveObserverCoordinator$AndroidPhotosAutosave_release(this.p0Provider7.mo10268get());
    }
}
