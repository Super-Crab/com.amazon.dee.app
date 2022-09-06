package com.amazon.alexa.fitness.sdk.sample;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import com.amazon.deecomms.common.Constants;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* compiled from: SampleStore.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J9\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052'\u0010\u0007\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\n0\t¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\bH&J3\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00030\bH&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\t2\u0006\u0010\u0004\u001a\u00020\u0005H&J9\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052'\u0010\u0015\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00140\t¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\bH&J3\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00030\bH&J;\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00030\bH&J\u0010\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u000fH&J\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0014H&J \u0010\u001e\u001a\u00020\u00032\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u001fj\b\u0012\u0004\u0012\u00020\u0014` H&¨\u0006!"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/sample/SampleStore;", "", "deleteAllSamples", "", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "queryAllLocationSamples", Constants.BUNDLE_RECEIVER_TAG, "Lkotlin/Function1;", "", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "Lkotlin/ParameterName;", "name", "samples", "queryAllLocationSamplesSubscription", "Lcom/amazon/alexa/fitness/sdk/sample/ObserverToken;", "observer", "sample", "queryAllLocationSamplesSync", "queryAllSamples", "Lcom/amazon/alexa/fitness/sdk/Sample;", "sampleReceiver", "queryEchoBudSamplesSubscription", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "queryMeasurementSamplesSubscription", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "removeObserver", "token", HttpClientModule.ElementsCacheKey.STORE, "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public interface SampleStore {
    void deleteAllSamples(@NotNull UUID uuid);

    void queryAllLocationSamples(@NotNull UUID uuid, @NotNull Function1<? super List<Sample.LocationSample>, Unit> function1);

    @NotNull
    ObserverToken queryAllLocationSamplesSubscription(@NotNull UUID uuid, @NotNull Function1<? super Sample.LocationSample, Unit> function1);

    @NotNull
    List<Sample.LocationSample> queryAllLocationSamplesSync(@NotNull UUID uuid);

    @NotNull
    List<Sample> queryAllSamples(@NotNull UUID uuid);

    void queryAllSamples(@NotNull UUID uuid, @NotNull Function1<? super List<? extends Sample>, Unit> function1);

    @NotNull
    ObserverToken queryEchoBudSamplesSubscription(@NotNull UUID uuid, @NotNull Function1<? super Sample.EchoBudSample, Unit> function1);

    @NotNull
    ObserverToken queryMeasurementSamplesSubscription(@NotNull UUID uuid, @NotNull SampleType sampleType, @NotNull Function1<? super Sample.MeasurementSample, Unit> function1);

    void removeObserver(@NotNull ObserverToken observerToken);

    void store(@NotNull Sample sample);

    void store(@NotNull ArrayList<Sample> arrayList);
}
