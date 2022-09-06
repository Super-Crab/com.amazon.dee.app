package com.amazon.alexa.lifecycle;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.EventBusException;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.amazon.alexa.lifecycle.api.LifecycleManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes9.dex */
public class DefaultLifecycleManager implements LifecycleManager {
    private static final String TAG = "DefaultLifecycleManager";
    private EventBus eventBus;
    private Gson gson;
    private boolean isForeground;
    private boolean isLoaded;
    private boolean isReady;
    private boolean isShuttingDown;
    private boolean isStarted;
    private final LifecycleManagerObserver lifecycleManagerObserver;
    private LifecycleOwner lifecycleOwner;

    /* renamed from: com.amazon.alexa.lifecycle.DefaultLifecycleManager$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$lifecycle$api$LifecycleEvent = new int[LifecycleEvent.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$lifecycle$api$LifecycleEvent[LifecycleEvent.APPLICATION_DID_FOREGROUND.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$lifecycle$api$LifecycleEvent[LifecycleEvent.APPLICATION_DID_BACKGROUND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$lifecycle$api$LifecycleEvent[LifecycleEvent.APPLICATION_READY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$lifecycle$api$LifecycleEvent[LifecycleEvent.APPLICATION_LOADED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$lifecycle$api$LifecycleEvent[LifecycleEvent.APPLICATION_WILL_SHUTDOWN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes9.dex */
    class LifecycleManagerObserver implements LifecycleObserver {
        private final DefaultLifecycleManager lcm;

        LifecycleManagerObserver(DefaultLifecycleManager defaultLifecycleManager) {
            this.lcm = defaultLifecycleManager;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy() {
            this.lcm.notify(LifecycleEvent.APPLICATION_WILL_SHUTDOWN);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
            this.lcm.notify(LifecycleEvent.APPLICATION_DID_FOREGROUND);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
            String unused = DefaultLifecycleManager.TAG;
            String str = Lifecycle.Event.ON_START.name() + " called";
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            this.lcm.notify(LifecycleEvent.APPLICATION_DID_BACKGROUND);
        }
    }

    @VisibleForTesting
    DefaultLifecycleManager(EventBus eventBus, LifecycleOwner lifecycleOwner) {
        this.isForeground = false;
        this.isLoaded = false;
        this.isReady = false;
        this.isShuttingDown = false;
        this.isStarted = false;
        this.lifecycleManagerObserver = new LifecycleManagerObserver(this);
        this.lifecycleOwner = lifecycleOwner;
        this.eventBus = eventBus;
    }

    @Override // com.amazon.alexa.lifecycle.api.LifecycleManager
    public Map<String, Boolean> getCurrentState() {
        HashMap hashMap = new HashMap();
        hashMap.put(LifecycleEvent.IS_FOREGROUND, Boolean.valueOf(this.isForeground));
        hashMap.put(LifecycleEvent.IS_LOADED, Boolean.valueOf(this.isLoaded));
        hashMap.put(LifecycleEvent.IS_READY, Boolean.valueOf(this.isReady));
        return hashMap;
    }

    @Override // com.amazon.alexa.lifecycle.api.LifecycleManager
    public void notify(LifecycleEvent lifecycleEvent) {
        int ordinal = lifecycleEvent.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        if (ordinal != 4) {
                            Log.w(TAG, String.format("No known event: '%s'", lifecycleEvent.name));
                            return;
                        } else if (this.isLoaded) {
                            return;
                        } else {
                            this.isLoaded = true;
                        }
                    } else if (this.isReady) {
                        return;
                    } else {
                        this.isReady = true;
                    }
                } else if (this.isShuttingDown) {
                    return;
                } else {
                    this.isShuttingDown = true;
                }
            } else if (!this.isForeground) {
                return;
            } else {
                this.isForeground = false;
            }
        } else if (this.isForeground) {
            return;
        } else {
            this.isForeground = true;
        }
        String str = lifecycleEvent.name;
        try {
            this.eventBus.publish(new Message.Builder().setEventType(lifecycleEvent.name).setPayload(this.gson.toJson(getCurrentState())).build());
            String.format("LifecycleEvent broadcast: %s", lifecycleEvent.name);
        } catch (EventBusException e) {
            Log.e(TAG, String.format("Failed to send the '%s' event. %s", lifecycleEvent.name, e.getMessage()));
        }
    }

    @VisibleForTesting
    void setInitialStates() {
        this.isForeground = false;
        this.isLoaded = false;
        this.isReady = false;
        this.isShuttingDown = false;
    }

    @Override // com.amazon.alexa.lifecycle.api.LifecycleManager
    public void start() {
        if (this.isStarted) {
            return;
        }
        this.gson = new Gson();
        setInitialStates();
        this.lifecycleOwner.getLifecycle().addObserver(this.lifecycleManagerObserver);
        this.isStarted = true;
    }

    public DefaultLifecycleManager(Context context) {
        this.isForeground = false;
        this.isLoaded = false;
        this.isReady = false;
        this.isShuttingDown = false;
        this.isStarted = false;
        this.lifecycleManagerObserver = new LifecycleManagerObserver(this);
        this.lifecycleOwner = ProcessLifecycleOwner.get();
        this.eventBus = (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }
}
