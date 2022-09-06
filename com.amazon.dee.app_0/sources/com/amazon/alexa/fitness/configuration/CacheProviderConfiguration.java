package com.amazon.alexa.fitness.configuration;

import kotlin.Metadata;
/* compiled from: Configuration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/CacheProviderConfiguration;", "", "alexaFitnessSessionStorageSizeInMb", "", "fitnessSessionEventStorageSizeInMb", "sessionSummaryStorageSizeInMb", "userProfileStorageSizeInMb", "(IIII)V", "getAlexaFitnessSessionStorageSizeInMb", "()I", "getFitnessSessionEventStorageSizeInMb", "getSessionSummaryStorageSizeInMb", "getUserProfileStorageSizeInMb", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CacheProviderConfiguration {
    private final int alexaFitnessSessionStorageSizeInMb;
    private final int fitnessSessionEventStorageSizeInMb;
    private final int sessionSummaryStorageSizeInMb;
    private final int userProfileStorageSizeInMb;

    public CacheProviderConfiguration(int i, int i2, int i3, int i4) {
        this.alexaFitnessSessionStorageSizeInMb = i;
        this.fitnessSessionEventStorageSizeInMb = i2;
        this.sessionSummaryStorageSizeInMb = i3;
        this.userProfileStorageSizeInMb = i4;
    }

    public final int getAlexaFitnessSessionStorageSizeInMb() {
        return this.alexaFitnessSessionStorageSizeInMb;
    }

    public final int getFitnessSessionEventStorageSizeInMb() {
        return this.fitnessSessionEventStorageSizeInMb;
    }

    public final int getSessionSummaryStorageSizeInMb() {
        return this.sessionSummaryStorageSizeInMb;
    }

    public final int getUserProfileStorageSizeInMb() {
        return this.userProfileStorageSizeInMb;
    }
}
