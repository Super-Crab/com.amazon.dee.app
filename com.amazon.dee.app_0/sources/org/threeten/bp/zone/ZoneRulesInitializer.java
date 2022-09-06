package org.threeten.bp.zone;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes5.dex */
public abstract class ZoneRulesInitializer {
    public static final ZoneRulesInitializer DO_NOTHING = new DoNothingZoneRulesInitializer();
    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);
    private static final AtomicReference<ZoneRulesInitializer> INITIALIZER = new AtomicReference<>();

    /* loaded from: classes5.dex */
    static class DoNothingZoneRulesInitializer extends ZoneRulesInitializer {
        DoNothingZoneRulesInitializer() {
        }

        @Override // org.threeten.bp.zone.ZoneRulesInitializer
        protected void initializeProviders() {
        }
    }

    /* loaded from: classes5.dex */
    static class ServiceLoaderZoneRulesInitializer extends ZoneRulesInitializer {
        ServiceLoaderZoneRulesInitializer() {
        }

        @Override // org.threeten.bp.zone.ZoneRulesInitializer
        protected void initializeProviders() {
            Iterator it2 = ServiceLoader.load(ZoneRulesProvider.class, ZoneRulesProvider.class.getClassLoader()).iterator();
            while (it2.hasNext()) {
                try {
                    ZoneRulesProvider.registerProvider((ZoneRulesProvider) it2.next());
                } catch (ServiceConfigurationError e) {
                    if (!(e.getCause() instanceof SecurityException)) {
                        throw e;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize() {
        if (!INITIALIZED.getAndSet(true)) {
            INITIALIZER.compareAndSet(null, new ServiceLoaderZoneRulesInitializer());
            INITIALIZER.get().initializeProviders();
            return;
        }
        throw new IllegalStateException("Already initialized");
    }

    public static void setInitializer(ZoneRulesInitializer zoneRulesInitializer) {
        if (!INITIALIZED.get()) {
            if (!INITIALIZER.compareAndSet(null, zoneRulesInitializer)) {
                throw new IllegalStateException("Initializer was already set, possibly with a default during initialization");
            }
            return;
        }
        throw new IllegalStateException("Already initialized");
    }

    protected abstract void initializeProviders();
}
