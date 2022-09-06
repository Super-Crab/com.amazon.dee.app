package com.amazon.dee.app.elements;

import android.app.Application;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.LatencyService;
import com.dee.app.metrics.MetricComponentName;
import com.dee.app.metrics.MetricDescriptor;
import com.dee.app.metrics.MetricName;
import com.dee.app.metrics.MetricsConstants;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import dagger.Lazy;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class ReactBridgeService {
    public static final String REACT_DEVELOPER_SUPPORT = "ReactDeveloperSupport";
    private static final String TAG = Log.tag(ReactBridgeService.class);
    private final AlexaNativeModuleCallExceptionHandler alexaNativeModuleCallExceptionHandler;
    private final Application application;
    private final MetricsServiceV2 metricsServiceV2;
    private final Set<ReactPackage> packages;
    private final boolean reactDeveloperSupportEnabled;
    private Lazy<RoutingService> routingService;
    private ReactInstanceManager rim = null;
    private ReactBridgeMetrics bridgeMetrics = null;
    private int currentState = 3;
    private PublishSubject<Integer> onStateChanged = PublishSubject.create();
    private List<ReactInstanceManager.ReactInstanceEventListener> eventListeners = new ArrayList();

    /* loaded from: classes12.dex */
    final class ReactBridgeLifecycleObserver implements MainActivityLifecycleObserver {
        ReactBridgeLifecycleObserver() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityCreated() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityDestroy() {
            ReactBridgeService.this.cleanup();
            ReactBridgeService.this.currentState = 3;
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityPause() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityResume() {
            if (ReactBridgeService.this.currentState != 3) {
                ReactBridgeService.this.reintializeReactBridge();
            }
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStart() {
        }

        @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
        public void onActivityStop() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface State {
        public static final int STARTED = 1;
        public static final int STARTING = 0;
        public static final int STOPPED = 2;
        public static final int UNINITIALIZED = 3;
    }

    public ReactBridgeService(Application application, Set<ReactPackage> set, @Named("ReactDeveloperSupport") boolean z, AlexaNativeModuleCallExceptionHandler alexaNativeModuleCallExceptionHandler, MetricsServiceV2 metricsServiceV2, Lazy<RoutingService> lazy, MainActivityLifecycleObserverRegistrar mainActivityLifecycleObserverRegistrar) {
        this.application = application;
        this.packages = set;
        this.reactDeveloperSupportEnabled = z;
        this.alexaNativeModuleCallExceptionHandler = alexaNativeModuleCallExceptionHandler;
        this.metricsServiceV2 = metricsServiceV2;
        this.routingService = lazy;
        mainActivityLifecycleObserverRegistrar.addObserver(new ReactBridgeLifecycleObserver());
    }

    private ReactInstanceManager createRim() {
        ReactInstanceManagerBuilder initialLifecycleState = createBuilder().setApplication(this.application).setUseDeveloperSupport(this.reactDeveloperSupportEnabled).setJSMainModulePath("index").setJSBundleFile("assets://index.bundle.hbc").setNativeModuleCallExceptionHandler(this.alexaNativeModuleCallExceptionHandler).setInitialLifecycleState(LifecycleState.BEFORE_RESUME);
        for (ReactPackage reactPackage : this.packages) {
            initialLifecycleState.addPackage(reactPackage);
        }
        return initialLifecycleState.build();
    }

    private ReactInstanceManager.ReactInstanceEventListener createStartedListener() {
        return new ReactInstanceManager.ReactInstanceEventListener() { // from class: com.amazon.dee.app.elements.ReactBridgeService.1
            @Override // com.facebook.react.ReactInstanceManager.ReactInstanceEventListener
            public void onReactContextInitialized(ReactContext reactContext) {
                ReactBridgeService.this.setState(1);
                LatencyService.catalystLoaded();
                if (ReactBridgeService.this.rim != null) {
                    ReactBridgeService.this.rim.removeReactInstanceEventListener(this);
                }
            }
        };
    }

    private void recordRestartError() {
        this.metricsServiceV2.recordCount(new MetricDescriptor.Builder(new MetricName.Builder("RESTART_OUT_OF_ORDER").withSource(MetricsConstants.Source.NATIVE_ELEMENTS).withModule(MetricsConstants.Module.CORE).build(), new MetricComponentName.Builder().withCategoryId("load").build()).build(), 1.0d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int i) {
        this.currentState = i;
        this.onStateChanged.onNext(Integer.valueOf(i));
    }

    public void addEventListener(ReactInstanceManager.ReactInstanceEventListener reactInstanceEventListener) {
        this.eventListeners.add(reactInstanceEventListener);
    }

    @VisibleForTesting
    void cleanup() {
        this.onStateChanged.onComplete();
        this.rim = null;
    }

    @VisibleForTesting
    ReactInstanceManagerBuilder createBuilder() {
        return ReactInstanceManager.builder();
    }

    public int getState() {
        return this.currentState;
    }

    public ReactInstanceManager instance() {
        return this.rim;
    }

    public boolean isStopped() {
        return this.currentState == 2;
    }

    public /* synthetic */ void lambda$stop$0$ReactBridgeService() {
        ReactInstanceManager reactInstanceManager = this.rim;
        if (reactInstanceManager != null) {
            reactInstanceManager.destroy();
            this.rim = null;
        }
    }

    public Observable<Integer> onStateChanged() {
        return this.onStateChanged.hide();
    }

    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    @VisibleForTesting
    void onUIThread(Runnable runnable) {
        ReactInstanceManager reactInstanceManager = this.rim;
        if (reactInstanceManager != null && reactInstanceManager.getCurrentReactContext() != null && this.rim.getCurrentReactContext().getCurrentActivity() != null) {
            this.rim.getCurrentReactContext().getCurrentActivity().runOnUiThread(runnable);
        } else {
            Log.i(TAG, "Activity was null. Not running any code.");
        }
    }

    void reintializeReactBridge() {
        if (isStopped()) {
            start(false);
            RouteContext currentRoute = this.routingService.mo358get().getCurrentRoute();
            if (currentRoute == null || currentRoute.getRoute().getAdapterId() != 1) {
                return;
            }
            this.routingService.mo358get().refresh();
        }
    }

    public void restart() {
        if (this.currentState == 0) {
            recordRestartError();
        }
        if (this.rim == null) {
            return;
        }
        if (this.bridgeMetrics != null) {
            long currentTimeMillis = System.currentTimeMillis();
            setState(0);
            this.rim.addReactInstanceEventListener(createStartedListener());
            ReactInstanceManager reactInstanceManager = this.rim;
            reactInstanceManager.addReactInstanceEventListener(this.bridgeMetrics.startTimers(currentTimeMillis, reactInstanceManager));
        }
        this.rim.recreateReactContextInBackground();
    }

    public void setBridgeMetrics(ReactBridgeMetrics reactBridgeMetrics) {
        this.bridgeMetrics = reactBridgeMetrics;
    }

    public void start(boolean z) {
        if (this.rim == null) {
            this.rim = createRim();
        }
        setState(0);
        if (z) {
            if (this.bridgeMetrics != null) {
                long currentTimeMillis = System.currentTimeMillis();
                ReactInstanceManager reactInstanceManager = this.rim;
                reactInstanceManager.addReactInstanceEventListener(this.bridgeMetrics.startTimers(currentTimeMillis, reactInstanceManager));
            }
            this.rim.addReactInstanceEventListener(createStartedListener());
            for (ReactInstanceManager.ReactInstanceEventListener reactInstanceEventListener : this.eventListeners) {
                this.rim.addReactInstanceEventListener(reactInstanceEventListener);
            }
            this.rim.createReactContextInBackground();
        }
    }

    public void stop() {
        if (this.rim == null) {
            return;
        }
        setState(2);
        onUIThread(new Runnable() { // from class: com.amazon.dee.app.elements.-$$Lambda$ReactBridgeService$-R57d_U5lKOpU5yovuFRpmldJIA
            @Override // java.lang.Runnable
            public final void run() {
                ReactBridgeService.this.lambda$stop$0$ReactBridgeService();
            }
        });
    }
}
