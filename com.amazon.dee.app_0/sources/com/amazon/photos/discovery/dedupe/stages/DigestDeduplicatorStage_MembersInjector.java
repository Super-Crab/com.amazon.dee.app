package com.amazon.photos.discovery.dedupe.stages;

import com.amazon.photos.discovery.internal.dedupe.digest.CloudDigestAssociator;
import com.amazon.photos.discovery.internal.dedupe.digest.LocalDigestAssociator;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DigestDeduplicatorStage_MembersInjector implements MembersInjector<DigestDeduplicatorStage> {
    private final Provider<CloudDigestAssociator> cloudAssociatorProvider;
    private final Provider<LocalDigestAssociator> localAssociatorProvider;

    public DigestDeduplicatorStage_MembersInjector(Provider<LocalDigestAssociator> provider, Provider<CloudDigestAssociator> provider2) {
        this.localAssociatorProvider = provider;
        this.cloudAssociatorProvider = provider2;
    }

    public static MembersInjector<DigestDeduplicatorStage> create(Provider<LocalDigestAssociator> provider, Provider<CloudDigestAssociator> provider2) {
        return new DigestDeduplicatorStage_MembersInjector(provider, provider2);
    }

    public static void injectCloudAssociator(DigestDeduplicatorStage digestDeduplicatorStage, CloudDigestAssociator cloudDigestAssociator) {
        digestDeduplicatorStage.cloudAssociator = cloudDigestAssociator;
    }

    public static void injectLocalAssociator(DigestDeduplicatorStage digestDeduplicatorStage, LocalDigestAssociator localDigestAssociator) {
        digestDeduplicatorStage.localAssociator = localDigestAssociator;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DigestDeduplicatorStage digestDeduplicatorStage) {
        injectLocalAssociator(digestDeduplicatorStage, this.localAssociatorProvider.mo10268get());
        injectCloudAssociator(digestDeduplicatorStage, this.cloudAssociatorProvider.mo10268get());
    }
}
