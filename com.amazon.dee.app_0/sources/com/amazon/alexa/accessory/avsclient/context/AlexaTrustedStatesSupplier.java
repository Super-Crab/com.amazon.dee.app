package com.amazon.alexa.accessory.avsclient.context;

import android.util.Pair;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.context.TrustedStates;
import com.amazon.alexa.accessory.avsclient.utils.ISO8601TimeSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.deecomms.common.Constants;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class AlexaTrustedStatesSupplier implements TrustedStatesSupplier {
    private static final String AH_CASE = "A21YKVRGQV9TET";
    private static final String ARMSTRONG_CASE = "A3HVREY4JWAZ6K";
    private static final String ECHO_BUDS_CASE = "AE9FIEPOC6D9B";
    private static final Set<String> IGNORED_DEVICE_TYPES = new HashSet(Arrays.asList("AE9FIEPOC6D9B", "A3HVREY4JWAZ6K", "A21YKVRGQV9TET"));
    private static final String TAG = "AlexaTrustedStatesSupplier:";
    private boolean activated;
    private final CompositeDisposable compositeDisposable;
    private final Object lock;
    private final PhoneLockState phoneLockState;
    private final RegistrationSupplier registrationSupplier;
    private final SecurityStateSupplier securityStateSupplier;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final ISO8601TimeSupplier timeSupplier;
    private final Map<String, AccessoryTrustState> trustedStatesMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class AccessoryTrustState {
        final String deviceType;
        final String serialNumber;
        final long sessionStartTime;
        long longestTimeUntrustedAggregate = 0;
        long timeLastBecameUnpresent = System.currentTimeMillis() - 1;
        long timeLastBecamePresent = -1;
        long trustSessionStartTime = -1;

        public AccessoryTrustState(long j, String str, String str2) {
            Preconditions.notNull(str, "deviceType");
            Preconditions.notNull(str2, Constants.BUNDLE_SERIAL_NUMBER);
            this.sessionStartTime = j;
            this.deviceType = str;
            this.serialNumber = str2;
        }

        public long getLongestTimeUntrustedCalculated() {
            if (isPresent()) {
                return this.longestTimeUntrustedAggregate;
            }
            if (timeSinceLastPresent() != null) {
                return Math.max(timeSinceLastPresent().longValue(), this.longestTimeUntrustedAggregate);
            }
            return System.currentTimeMillis() - this.sessionStartTime;
        }

        public boolean isPresent() {
            long j = this.timeLastBecamePresent;
            return j != -1 && j > this.timeLastBecameUnpresent;
        }

        public Long timeSinceLastPresent() {
            if (this.timeLastBecamePresent == -1) {
                return null;
            }
            return Long.valueOf(System.currentTimeMillis() - this.timeLastBecameUnpresent);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class PhoneLockState {
        static final String LOCKED = "LOCKED";
        static final String METHOD_NONE = "NONE";
        static final String METHOD_SECURE_UNKNOWN = "SECURE_UNKNOWN";
        static final String NEVER_UNLOCKED = "NEVER_UNLOCKED";
        static final String UNLOCKED = "UNLOCKED";
        private long lastUnlockedTime = -1;
        private long lastLockedTime = -1;

        PhoneLockState() {
        }

        public void clear() {
            this.lastUnlockedTime = -1L;
            this.lastLockedTime = -1L;
        }

        public boolean hasBeenUnlocked() {
            return this.lastUnlockedTime != -1;
        }

        public boolean isUnlocked() {
            return this.lastUnlockedTime > this.lastLockedTime;
        }

        public Long lastTimeInUnlockedState() {
            if (isUnlocked()) {
                return Long.valueOf(System.currentTimeMillis());
            }
            if (this.lastUnlockedTime != -1) {
                return Long.valueOf(this.lastLockedTime);
            }
            return null;
        }

        public void setLastLockedTime(long j) {
            this.lastLockedTime = j;
        }

        public void setLastUnlockedTime(long j) {
            this.lastUnlockedTime = j;
        }
    }

    /* loaded from: classes.dex */
    private class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            Logger.d("%s onAccessorySessionConnected for %s will observe accessory", AlexaTrustedStatesSupplier.TAG, accessory);
            AccessorySession session = AlexaTrustedStatesSupplier.this.sessionSupplier.getSession(accessory);
            if (session == null) {
                return;
            }
            AlexaTrustedStatesSupplier.this.observeSession(session);
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            Logger.d("%s onAccessorySessionReleased for %s will halt observation for accessory", AlexaTrustedStatesSupplier.TAG, accessory);
            synchronized (AlexaTrustedStatesSupplier.this.lock) {
                AlexaTrustedStatesSupplier.this.trustedStatesMap.remove(accessory.getAddress());
            }
        }
    }

    public AlexaTrustedStatesSupplier(SessionSupplier sessionSupplier, RegistrationSupplier registrationSupplier, SecurityStateSupplier securityStateSupplier) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(registrationSupplier, "registrationSupplier");
        Preconditions.notNull(securityStateSupplier, "securityStateSupplier");
        this.sessionSupplier = sessionSupplier;
        this.registrationSupplier = registrationSupplier;
        this.timeSupplier = new ISO8601TimeSupplier();
        this.securityStateSupplier = securityStateSupplier;
        this.phoneLockState = new PhoneLockState();
        this.trustedStatesMap = new HashMap();
        this.sessionListener = new SessionListener();
        this.compositeDisposable = new CompositeDisposable();
        this.lock = new Object();
    }

    private static void accumulateLongestUntrustedTime(AccessoryTrustState accessoryTrustState) {
        Long timeSinceLastPresent = accessoryTrustState.timeSinceLastPresent();
        accessoryTrustState.longestTimeUntrustedAggregate = Math.max(timeSinceLastPresent != null ? timeSinceLastPresent.longValue() : 0L, accessoryTrustState.longestTimeUntrustedAggregate);
    }

    private Single<Pair<String, String>> awaitRegistration(final AccessorySession accessorySession) {
        Logger.d("%s awaitRegistration called for session %s", TAG, accessorySession.getAddress());
        return this.registrationSupplier.queryRegistrations().flatMapMaybe(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$VhPDWnj-te1ik9vJUbWNMK22zFI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaTrustedStatesSupplier.this.lambda$awaitRegistration$1$AlexaTrustedStatesSupplier(accessorySession, (Set) obj);
            }
        }).firstOrError().map($$Lambda$AlexaTrustedStatesSupplier$jMsPRUZwjpvTluTw0Mrt8l5mKmY.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Pair<String, String> extractDeviceInformation(DeviceRegistration deviceRegistration) {
        if (deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getFirstPartyDevice() != null) {
            return new Pair<>(deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getFirstPartyDevice().getDeviceType(), deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getFirstPartyDevice().getDsn());
        }
        if (deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getThirdPartyDevice() != null) {
            return new Pair<>(deviceRegistration.getDeviceRegistrationResponse().getDeviceType(), deviceRegistration.getDeviceRegistrationResponse().getInternalDeviceSerialNumber());
        }
        if (deviceRegistration.getDeviceRegistrationRequest().getDeviceRegistrationRequestIdentifier().getFirstPartyClusterDevice() != null) {
            return new Pair<>(deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceType(), deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceSerialNumber());
        }
        throw new RuntimeException("AlexaTrustedStatesSupplier: Cannot determine device type and serial number for registration " + deviceRegistration);
    }

    private static boolean isPresent(Set<Device.DeviceInformation> set) {
        for (Device.DeviceInformation deviceInformation : set) {
            if (!IGNORED_DEVICE_TYPES.contains(deviceInformation.getDeviceType()) && deviceInformation.hasStatus() && deviceInformation.getStatus().getPresence() == Device.DevicePresence.DEVICE_PRESENCE_ACTIVE) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lockStateChanged(boolean z) {
        synchronized (this.lock) {
            Logger.d("%s observed a change in isLocked to: %b", TAG, Boolean.valueOf(z));
            long currentTimeMillis = System.currentTimeMillis();
            if (!z) {
                Logger.d("%s Phone is unlocked. Setting unlockState", TAG);
                this.phoneLockState.lastUnlockedTime = currentTimeMillis;
            } else {
                Logger.d("%s Phone is locked. Setting unlockState", TAG);
                this.phoneLockState.lastLockedTime = currentTimeMillis;
            }
            if (!this.phoneLockState.isUnlocked()) {
                return;
            }
            for (Map.Entry<String, AccessoryTrustState> entry : this.trustedStatesMap.entrySet()) {
                AccessoryTrustState value = entry.getValue();
                if (value.isPresent()) {
                    value.longestTimeUntrustedAggregate = 0L;
                    value.trustSessionStartTime = currentTimeMillis;
                    Logger.d("%s isUnlocked and isPresent true, preparing accessory %s with new start of trust session", TAG, entry.getKey());
                }
            }
        }
    }

    private void observeLockState() {
        this.compositeDisposable.add(this.securityStateSupplier.queryDeviceLockedState().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$h09yAntf1qca_xmE8U476YZfqGY
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaTrustedStatesSupplier.this.lockStateChanged(((Boolean) obj).booleanValue());
            }
        }, $$Lambda$AlexaTrustedStatesSupplier$mHAILKnEPTeqJUGdOdeWpBwvso.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void observeSession(final AccessorySession accessorySession) {
        this.compositeDisposable.add(awaitRegistration(accessorySession).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$oOSEjGtAmCEn1e2Ew598bOc2n2M
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Pair pair = (Pair) obj;
                Logger.d("%s Registration info retrieved for accessory %s with deviceType %s and serialNumber %s", AlexaTrustedStatesSupplier.TAG, AccessorySession.this.getAccessory().getAddress(), pair.first, pair.second);
                return pair;
            }
        }).observeOn(AndroidSchedulers.mainThread()).flatMapObservable(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$-rjMynz0WjNc-zCfi4jWrkyj66w
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaTrustedStatesSupplier.this.lambda$observeSession$5$AlexaTrustedStatesSupplier(accessorySession, (Pair) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$tIYRvC2RHVb-fs0ChvUx7yqgOBM
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Logger.d("%s Presence did change for accessory %s: isPresent: %b", AlexaTrustedStatesSupplier.TAG, AccessorySession.this.getAddress(), (Boolean) obj);
            }
        }, $$Lambda$AlexaTrustedStatesSupplier$yYPs4ZJH6_IEfCjoxALF6ufv4.INSTANCE));
    }

    private void presenceDidChange(String str, boolean z) {
        synchronized (this.lock) {
            AccessoryTrustState accessoryTrustState = this.trustedStatesMap.get(str);
            if (accessoryTrustState == null) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!z) {
                accessoryTrustState.timeLastBecameUnpresent = currentTimeMillis;
                return;
            }
            accessoryTrustState.timeLastBecamePresent = currentTimeMillis;
            if (this.phoneLockState.isUnlocked()) {
                accessoryTrustState.longestTimeUntrustedAggregate = 0L;
                accessoryTrustState.trustSessionStartTime = currentTimeMillis;
                Logger.d("%s isUnlocked and isPresent true, preparing accessory %s with new start of trust session", TAG, str);
            } else {
                accumulateLongestUntrustedTime(accessoryTrustState);
            }
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.TrustedStatesSupplier
    public void activate() {
        Preconditions.mainThread();
        if (this.activated) {
            return;
        }
        this.activated = true;
        this.securityStateSupplier.activate();
        synchronized (this.lock) {
            this.phoneLockState.clear();
            this.trustedStatesMap.clear();
        }
        Logger.d("%s Activate", TAG);
        for (AccessorySession accessorySession : this.sessionSupplier.getActiveSessions()) {
            if (accessorySession.isConnected()) {
                observeSession(accessorySession);
            }
        }
        this.sessionSupplier.addSessionListener(this.sessionListener);
        observeLockState();
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.TrustedStatesSupplier
    public void deactivate() {
        Preconditions.mainThread();
        if (!this.activated) {
            return;
        }
        this.activated = false;
        this.securityStateSupplier.deactivate();
        Logger.d("%s Deactivate", TAG);
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        this.compositeDisposable.clear();
    }

    @Override // com.amazon.alexa.accessory.avsclient.context.TrustedStatesSupplier
    public TrustedStates getContext() {
        String str;
        String str2;
        String str3;
        TrustedStates trustedStates;
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.lock) {
            ArrayList arrayList = new ArrayList(this.trustedStatesMap.size());
            Iterator<AccessoryTrustState> it2 = this.trustedStatesMap.values().iterator();
            while (true) {
                str = null;
                Long valueOf = null;
                if (!it2.hasNext()) {
                    break;
                }
                AccessoryTrustState next = it2.next();
                long longestTimeUntrustedCalculated = next.getLongestTimeUntrustedCalculated();
                long j = next.trustSessionStartTime;
                if (j != -1) {
                    valueOf = Long.valueOf(j);
                }
                arrayList.add(new TrustedStates.SessionState(this.timeSupplier.getTime(next.sessionStartTime), this.timeSupplier.getTimeOptional(valueOf), longestTimeUntrustedCalculated, next.deviceType, next.serialNumber));
            }
            if (this.phoneLockState.hasBeenUnlocked()) {
                str2 = this.phoneLockState.isUnlocked() ? "UNLOCKED" : "LOCKED";
                str = this.securityStateSupplier.isDeviceSecure() ? "SECURE_UNKNOWN" : "NONE";
                str3 = this.timeSupplier.getTimeOptional(this.phoneLockState.lastTimeInUnlockedState());
            } else {
                str2 = "NEVER_UNLOCKED";
                str3 = null;
            }
            Logger.d("%s getContext took %d milliseconds", TAG, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            trustedStates = new TrustedStates(str2, str3, str, arrayList);
        }
        return trustedStates;
    }

    public /* synthetic */ MaybeSource lambda$awaitRegistration$1$AlexaTrustedStatesSupplier(AccessorySession accessorySession, Set set) throws Throwable {
        return this.registrationSupplier.getDeviceRegistration(accessorySession.getAddress()).toMaybe().onErrorResumeWith(Maybe.empty());
    }

    public /* synthetic */ ObservableSource lambda$null$4$AlexaTrustedStatesSupplier(AccessorySession accessorySession, Boolean bool) throws Throwable {
        presenceDidChange(accessorySession.getAccessory().getAddress(), bool.booleanValue());
        return Observable.just(bool);
    }

    public /* synthetic */ ObservableSource lambda$observeSession$5$AlexaTrustedStatesSupplier(final AccessorySession accessorySession, Pair pair) throws Throwable {
        synchronized (this.lock) {
            if (!accessorySession.isConnecting() && !accessorySession.isConnected()) {
                throw new IllegalStateException("Session is released");
            }
            this.trustedStatesMap.put(accessorySession.getAccessory().getAddress(), new AccessoryTrustState(System.currentTimeMillis(), (String) pair.first, (String) pair.second));
        }
        return accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().observeOn(Schedulers.io()).concatMap($$Lambda$AlexaTrustedStatesSupplier$WbGGvOIfkHoQ41OvIGCE30N3OcQ.INSTANCE).distinctUntilChanged().concatMap(new Function() { // from class: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$TALl6on68wz9uzoMsb2TdY9tksc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AlexaTrustedStatesSupplier.this.lambda$null$4$AlexaTrustedStatesSupplier(accessorySession, (Boolean) obj);
            }
        });
    }
}
