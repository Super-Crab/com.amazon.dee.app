package com.google.android.datatransport.runtime;

import android.content.Context;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.time.Monotonic;
import com.google.android.datatransport.runtime.time.WallTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
@Singleton
/* loaded from: classes2.dex */
public class TransportRuntime implements TransportInternal {
    private static volatile TransportRuntimeComponent instance;
    private final Clock eventClock;
    private final Scheduler scheduler;
    private final Uploader uploader;
    private final Clock uptimeClock;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public TransportRuntime(@WallTime Clock clock, @Monotonic Clock clock2, Scheduler scheduler, Uploader uploader, WorkInitializer workInitializer) {
        this.eventClock = clock;
        this.uptimeClock = clock2;
        this.scheduler = scheduler;
        this.uploader = uploader;
        workInitializer.ensureContextsScheduled();
    }

    private EventInternal convert(SendRequest sendRequest) {
        return EventInternal.builder().setEventMillis(this.eventClock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName(sendRequest.getTransportName()).setEncodedPayload(new EncodedPayload(sendRequest.getEncoding(), sendRequest.getPayload())).setCode(sendRequest.getEvent().getCode()).build();
    }

    public static TransportRuntime getInstance() {
        TransportRuntimeComponent transportRuntimeComponent = instance;
        if (transportRuntimeComponent != null) {
            return transportRuntimeComponent.getTransportRuntime();
        }
        throw new IllegalStateException("Not initialized!");
    }

    private static Set<Encoding> getSupportedEncodings(Destination destination) {
        if (destination instanceof EncodedDestination) {
            return Collections.unmodifiableSet(((EncodedDestination) destination).getSupportedEncodings());
        }
        return Collections.singleton(Encoding.of("proto"));
    }

    public static void initialize(Context context) {
        if (instance == null) {
            synchronized (TransportRuntime.class) {
                if (instance == null) {
                    instance = DaggerTransportRuntimeComponent.builder().mo7264setApplicationContext(context).build();
                }
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    @VisibleForTesting
    static void withInstance(TransportRuntimeComponent transportRuntimeComponent, Callable<Void> callable) throws Throwable {
        TransportRuntimeComponent transportRuntimeComponent2;
        synchronized (TransportRuntime.class) {
            transportRuntimeComponent2 = instance;
            instance = transportRuntimeComponent;
        }
        try {
            callable.call();
            synchronized (TransportRuntime.class) {
                instance = transportRuntimeComponent2;
            }
        } catch (Throwable th) {
            synchronized (TransportRuntime.class) {
                instance = transportRuntimeComponent2;
                throw th;
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Uploader getUploader() {
        return this.uploader;
    }

    @Deprecated
    public TransportFactory newFactory(String str) {
        return new TransportFactoryImpl(getSupportedEncodings(null), TransportContext.builder().setBackendName(str).build(), this);
    }

    @Override // com.google.android.datatransport.runtime.TransportInternal
    public void send(SendRequest sendRequest, TransportScheduleCallback transportScheduleCallback) {
        this.scheduler.schedule(sendRequest.getTransportContext().withPriority(sendRequest.getEvent().getPriority()), convert(sendRequest), transportScheduleCallback);
    }

    public TransportFactory newFactory(Destination destination) {
        return new TransportFactoryImpl(getSupportedEncodings(destination), TransportContext.builder().setBackendName(destination.getName()).setExtras(destination.getExtras()).build(), this);
    }
}
