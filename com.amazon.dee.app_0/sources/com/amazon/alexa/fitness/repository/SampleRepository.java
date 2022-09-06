package com.amazon.alexa.fitness.repository;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.sdk.Sample;
import java.util.ArrayList;
import java.util.UUID;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SampleRepository.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J(\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00070\nj\b\u0012\u0004\u0012\u00020\u0007`\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00070\nj\b\u0012\u0004\u0012\u00020\u0007`\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/repository/SampleRepository;", "", "addSample", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "newSample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "addSamples", "newSamples", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "deleteSamples", "getSamples", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SampleRepository {
    void addSample(@NotNull UUID uuid, @NotNull Sample sample);

    void addSamples(@NotNull UUID uuid, @NotNull ArrayList<Sample> arrayList);

    void deleteSamples(@NotNull UUID uuid);

    @NotNull
    ArrayList<Sample> getSamples(@NotNull UUID uuid);
}
