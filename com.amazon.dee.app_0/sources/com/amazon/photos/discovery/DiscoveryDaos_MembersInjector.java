package com.amazon.photos.discovery;

import com.amazon.photos.discovery.dao.EditDao;
import com.amazon.photos.discovery.dao.LocalFolderDao;
import com.amazon.photos.discovery.dao.LocalItemDao;
import com.amazon.photos.discovery.dao.UnifiedItemDao;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryDaos_MembersInjector implements MembersInjector<DiscoveryDaos> {
    private final Provider<EditDao> internalEditDaoProvider;
    private final Provider<LocalFolderDao> internalLocalFolderDaoProvider;
    private final Provider<LocalItemDao> internalLocalItemDaoProvider;
    private final Provider<UnifiedItemDao> internalUnifiedItemDaoProvider;

    public DiscoveryDaos_MembersInjector(Provider<LocalItemDao> provider, Provider<UnifiedItemDao> provider2, Provider<EditDao> provider3, Provider<LocalFolderDao> provider4) {
        this.internalLocalItemDaoProvider = provider;
        this.internalUnifiedItemDaoProvider = provider2;
        this.internalEditDaoProvider = provider3;
        this.internalLocalFolderDaoProvider = provider4;
    }

    public static MembersInjector<DiscoveryDaos> create(Provider<LocalItemDao> provider, Provider<UnifiedItemDao> provider2, Provider<EditDao> provider3, Provider<LocalFolderDao> provider4) {
        return new DiscoveryDaos_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectInternalEditDao(DiscoveryDaos discoveryDaos, EditDao editDao) {
        discoveryDaos.internalEditDao = editDao;
    }

    public static void injectInternalLocalFolderDao(DiscoveryDaos discoveryDaos, LocalFolderDao localFolderDao) {
        discoveryDaos.internalLocalFolderDao = localFolderDao;
    }

    public static void injectInternalLocalItemDao(DiscoveryDaos discoveryDaos, LocalItemDao localItemDao) {
        discoveryDaos.internalLocalItemDao = localItemDao;
    }

    public static void injectInternalUnifiedItemDao(DiscoveryDaos discoveryDaos, UnifiedItemDao unifiedItemDao) {
        discoveryDaos.internalUnifiedItemDao = unifiedItemDao;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DiscoveryDaos discoveryDaos) {
        injectInternalLocalItemDao(discoveryDaos, this.internalLocalItemDaoProvider.mo10268get());
        injectInternalUnifiedItemDao(discoveryDaos, this.internalUnifiedItemDaoProvider.mo10268get());
        injectInternalEditDao(discoveryDaos, this.internalEditDaoProvider.mo10268get());
        injectInternalLocalFolderDao(discoveryDaos, this.internalLocalFolderDaoProvider.mo10268get());
    }
}
