package com.amazon.alexa.accessorykit.interprocess.feature;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsUserProvider;
import com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureFlagConsumer;
import com.amazon.alexa.mobilytics.integration.ama.AmaMobilyticsFeature;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes6.dex */
public class InterprocessFeatureChecker implements FeatureChecker, AccessoryMobilyticsUserProvider.StringFeatureChecker {
    private static final Set<String> FEATURES = new HashSet();
    private static final String TAG = "InterprocessFeatureChecker:";
    private final ExecutorService executor;
    private final FeatureFlagConsumer featureFlagConsumer;
    private Completable featureLoaderCompletable;
    private volatile boolean isFeatureFlagConsumerLoaded = false;

    static {
        for (AccessoryFeature accessoryFeature : AccessoryFeature.values()) {
            FEATURES.add(accessoryFeature.getName());
        }
        FEATURES.addAll(AmaMobilyticsFeature.AMA_MOBILYTICS_FEATURE_SET);
    }

    public InterprocessFeatureChecker(Context context) {
        Preconditions.notNull(context, "context");
        this.featureFlagConsumer = new DefaultFeatureFlagConsumer(context);
        this.executor = Executors.newSingleThreadExecutor();
        initialize();
        loadFeatureFlagAsync();
    }

    static Set<String> getSupportedFeatures() {
        return FEATURES;
    }

    private boolean isFeatureActive(String str) {
        return this.isFeatureFlagConsumerLoaded && this.featureFlagConsumer.getFeatureQuery().isActive(str);
    }

    private Completable loadFeatureFlag() {
        return Completable.fromAction(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.feature.-$$Lambda$InterprocessFeatureChecker$Ys3kUyZgIPXp1TJPyx8MHHC5m-M
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                InterprocessFeatureChecker.this.lambda$loadFeatureFlag$2$InterprocessFeatureChecker();
            }
        }).subscribeOn(Schedulers.io());
    }

    private void loadFeatureFlagAsync() {
        this.featureLoaderCompletable = loadFeatureFlag();
        this.featureLoaderCompletable.subscribe(new Action() { // from class: com.amazon.alexa.accessorykit.interprocess.feature.-$$Lambda$InterprocessFeatureChecker$DWp0XMwpSb49TBTWmLjay1mqiEU
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                InterprocessFeatureChecker.this.lambda$loadFeatureFlagAsync$0$InterprocessFeatureChecker();
            }
        }, $$Lambda$InterprocessFeatureChecker$04SyfFg9AqwX2dLWhY9BXP7HyQ.INSTANCE);
    }

    public boolean getFeatureWithTimeout(final String str, long j) {
        boolean z;
        try {
            try {
                z = ((Boolean) this.executor.submit(new Callable() { // from class: com.amazon.alexa.accessorykit.interprocess.feature.-$$Lambda$InterprocessFeatureChecker$wX7XYy_artGllzc9u5-UEgNg8JQ
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return InterprocessFeatureChecker.this.lambda$getFeatureWithTimeout$3$InterprocessFeatureChecker(str);
                    }
                }).get(j, TimeUnit.SECONDS)).booleanValue();
                try {
                    Logger.d("%s getFeatureWithTimeout for feature: %s hasAccess: %s", TAG, str, Boolean.valueOf(z));
                } catch (InterruptedException e) {
                    e = e;
                    Logger.e("%s Error in getting feature value for %s", e, TAG, str);
                    return z;
                } catch (ExecutionException e2) {
                    e = e2;
                    Logger.e("%s Error in getting feature value for %s", e, TAG, str);
                    return z;
                } catch (TimeoutException e3) {
                    e = e3;
                    Logger.e("%s Error in getting feature value for %s", e, TAG, str);
                    return z;
                }
            } finally {
                this.executor.shutdownNow();
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e4) {
            e = e4;
            z = false;
        }
        return z;
    }

    @Override // com.amazon.alexa.accessory.utils.feature.FeatureChecker
    public boolean hasAccess(AccessoryFeature accessoryFeature) {
        return isFeatureActive(accessoryFeature.getName());
    }

    public void initialize() {
        this.featureFlagConsumer.initialize();
    }

    public /* synthetic */ Boolean lambda$getFeatureWithTimeout$3$InterprocessFeatureChecker(String str) throws Exception {
        return Boolean.valueOf(this.featureFlagConsumer.getFeatureQuery().isActive(str));
    }

    public /* synthetic */ void lambda$loadFeatureFlag$2$InterprocessFeatureChecker() throws Throwable {
        this.featureFlagConsumer.load(getSupportedFeatures());
    }

    public /* synthetic */ void lambda$loadFeatureFlagAsync$0$InterprocessFeatureChecker() throws Throwable {
        this.isFeatureFlagConsumerLoaded = true;
        Logger.d("FeatureFlag loaded");
    }

    public Completable observeOnFeatureLoaded() {
        return this.featureLoaderCompletable;
    }

    public void teardown() {
        this.featureFlagConsumer.teardown();
    }

    @Override // com.amazon.alexa.accessorykit.interprocess.mobilytics.AccessoryMobilyticsUserProvider.StringFeatureChecker
    public boolean hasAccess(String str) {
        boolean isFeatureActive = isFeatureActive(str);
        Logger.d("%s feature: %s hasAccess: %s", TAG, str, Boolean.valueOf(isFeatureActive));
        return isFeatureActive;
    }

    @VisibleForTesting
    InterprocessFeatureChecker(FeatureFlagConsumer featureFlagConsumer, ExecutorService executorService) {
        this.featureFlagConsumer = featureFlagConsumer;
        this.executor = executorService;
        loadFeatureFlagAsync();
    }
}
