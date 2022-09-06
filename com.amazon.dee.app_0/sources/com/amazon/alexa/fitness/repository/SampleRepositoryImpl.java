package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.util.SerializationUtilsKt;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.dee.app.cachemanager.CacheMetadata;
import java.util.ArrayList;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SampleRepository.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J(\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0011j\b\u0012\u0004\u0012\u00020\u000e`\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0011j\b\u0012\u0004\u0012\u00020\u000e`\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/repository/SampleRepositoryImpl;", "Lcom/amazon/alexa/fitness/repository/SampleRepository;", "cacheProvider", "Lcom/amazon/alexa/fitness/repository/CacheProvider;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/repository/CacheProvider;Lcom/amazon/alexa/fitness/logs/ILog;)V", "cacheMetaData", "Lcom/dee/app/cachemanager/CacheMetadata;", "addSample", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "newSample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "addSamples", "newSamples", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "deleteSamples", "getSamples", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SampleRepositoryImpl implements SampleRepository {
    private final CacheMetadata cacheMetaData;
    private final CacheProvider cacheProvider;
    private final ILog log;

    @Inject
    public SampleRepositoryImpl(@NotNull CacheProvider cacheProvider, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(cacheProvider, "cacheProvider");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.cacheProvider = cacheProvider;
        this.log = log;
        this.cacheMetaData = new CacheMetadata(1);
    }

    @Override // com.amazon.alexa.fitness.repository.SampleRepository
    public void addSample(@NotNull UUID sessionId, @NotNull Sample newSample) {
        ArrayList<Sample> arrayListOf;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(newSample, "newSample");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(newSample);
        addSamples(sessionId, arrayListOf);
    }

    @Override // com.amazon.alexa.fitness.repository.SampleRepository
    public void addSamples(@NotNull UUID sessionId, @NotNull ArrayList<Sample> newSamples) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(newSamples, "newSamples");
        ArrayList<Sample> samples = getSamples(sessionId);
        samples.addAll(newSamples);
        this.cacheProvider.getSampleCache().put(sessionId.toString(), SerializationUtilsKt.serialize(samples), this.cacheMetaData).toBlocking().singleOrDefault(null);
        ILog iLog = this.log;
        ILog.DefaultImpls.debug$default(iLog, "SampleRepository", "New Samples saved for " + sessionId + RealTimeTextConstants.COLON_SPACE + newSamples.size(), null, 4, null);
    }

    @Override // com.amazon.alexa.fitness.repository.SampleRepository
    public void deleteSamples(@NotNull UUID sessionId) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        this.cacheProvider.getSampleCache().remove(sessionId.toString(), this.cacheMetaData).toBlocking().singleOrDefault(null);
        ILog iLog = this.log;
        ILog.DefaultImpls.info$default(iLog, "SampleRepository", "All samples have been deleted for " + sessionId, null, 4, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0039, code lost:
        if (r0 == null) goto L3;
     */
    @Override // com.amazon.alexa.fitness.repository.SampleRepository
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.ArrayList<com.amazon.alexa.fitness.sdk.Sample> getSamples(@org.jetbrains.annotations.NotNull java.util.UUID r8) {
        /*
            r7 = this;
            java.lang.String r0 = "sessionId"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            com.amazon.alexa.fitness.repository.CacheProvider r0 = r7.cacheProvider
            com.dee.app.cachemanager.DefaultObjectCache r0 = r0.getSampleCache()
            java.lang.String r1 = r8.toString()
            com.dee.app.cachemanager.CacheMetadata r2 = r7.cacheMetaData
            rx.Observable r0 = r0.get(r1, r2)
            rx.observables.BlockingObservable r0 = r0.toBlocking()
            java.lang.Object r0 = r0.single()
            com.google.common.base.Optional r0 = (com.google.common.base.Optional) r0
            java.lang.Object r0 = r0.orNull()
            byte[] r0 = (byte[]) r0
            if (r0 == 0) goto L3c
            java.lang.Object r0 = org.apache.commons.lang3.SerializationUtils.deserialize(r0)     // Catch: java.lang.Exception -> L38
            if (r0 == 0) goto L30
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch: java.lang.Exception -> L38
            goto L39
        L30:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch: java.lang.Exception -> L38
            java.lang.String r1 = "null cannot be cast to non-null type java.util.ArrayList<com.amazon.alexa.fitness.sdk.Sample>"
            r0.<init>(r1)     // Catch: java.lang.Exception -> L38
            throw r0     // Catch: java.lang.Exception -> L38
        L38:
            r0 = 0
        L39:
            if (r0 == 0) goto L3c
            goto L41
        L3c:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L41:
            com.amazon.alexa.fitness.logs.ILog r1 = r7.log
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Samples retrieved for "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r8 = ": "
            r2.append(r8)
            int r8 = r0.size()
            r2.append(r8)
            java.lang.String r3 = r2.toString()
            r4 = 0
            r5 = 4
            r6 = 0
            java.lang.String r2 = "SampleRepository"
            com.amazon.alexa.fitness.logs.ILog.DefaultImpls.debug$default(r1, r2, r3, r4, r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.repository.SampleRepositoryImpl.getSamples(java.util.UUID):java.util.ArrayList");
    }
}
