package com.amazon.deecomms.conversation;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.api.MultiFilterSubscriber;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.marketplace.api.MarketplaceName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.MarketplaceInfo;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.calling.model.VoxCallInfo;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import dagger.Lazy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import rx.Subscription;
/* loaded from: classes12.dex */
public class CommsConversationService implements ConversationService {
    private final String deviceNameTemplate;
    private MultiFilterSubscriber.FilterUuid identityEventSubscription;
    private Subscription identitySubscription;
    private volatile boolean initializeComplete = false;
    private boolean initializeInProgress = false;
    private final Lazy<CommsDeviceSupport> lazyCommsDeviceSupport;
    private final Lazy<CommsDynamicFeatureService> lazyCommsFeatureService;
    private final Lazy<CommsManager> lazyCommsManager;
    private final Lazy<EventBus> lazyEventBus;
    private final Lazy<IdentityService> lazyIdentityService;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsConversationService.class);
    private static final Map<String, Locale> LANG_TO_LOCALE = new HashMap(7);
    private static final Set<Locale> ACCEPTED_LOCALES = new HashSet(3);
    private static final String className = CommsConversationService.class.getName();

    static {
        LANG_TO_LOCALE.put(Locale.GERMAN.getLanguage(), Locale.GERMANY);
        LANG_TO_LOCALE.put(Locale.ITALIAN.getLanguage(), Locale.ITALY);
        LANG_TO_LOCALE.put(Locale.FRENCH.getLanguage(), Locale.FRANCE);
        LANG_TO_LOCALE.put(Locale.JAPANESE.getLanguage(), Locale.JAPAN);
        LANG_TO_LOCALE.put("es", new Locale("es", "ES"));
        LANG_TO_LOCALE.put("pt", new Locale("pt", "BR"));
        LANG_TO_LOCALE.put("ar", new Locale("ar", "AE"));
        ACCEPTED_LOCALES.add(Locale.US);
        ACCEPTED_LOCALES.add(Locale.CANADA);
        ACCEPTED_LOCALES.add(new Locale("ar", "SA"));
    }

    public CommsConversationService(Lazy<CommsManager> lazy, Lazy<CommsDeviceSupport> lazy2, Lazy<IdentityService> lazy3, Lazy<CommsDynamicFeatureService> lazy4, String str, Lazy<EventBus> lazy5) {
        this.lazyCommsManager = lazy;
        this.lazyCommsDeviceSupport = lazy2;
        this.lazyIdentityService = lazy3;
        this.lazyCommsFeatureService = lazy4;
        this.deviceNameTemplate = str;
        this.lazyEventBus = lazy5;
    }

    @NonNull
    public static String determineDisplayLocale() {
        Locale locale = Locale.getDefault();
        Locale locale2 = LANG_TO_LOCALE.get(locale.getLanguage());
        if (ACCEPTED_LOCALES.contains(locale)) {
            return locale.toLanguageTag();
        }
        if (locale2 != null) {
            return locale2.toLanguageTag();
        }
        return Locale.UK.toLanguageTag();
    }

    @NonNull
    public static String getCommsPfmById(String str, String str2) {
        Marketplace findMarketplaceById = Marketplace.findMarketplaceById(str, null);
        return findMarketplaceById != null ? findMarketplaceById.getMarketplaceName().toString() : str2;
    }

    private boolean isSameUser(UserIdentity userIdentity) {
        String commsId = this.lazyCommsManager.mo358get().getCommsId();
        if (commsId == null || userIdentity == null || userIdentity.getUserProfile() == null || userIdentity.getUserProfile().getCommsProfile() == null) {
            return false;
        }
        return commsId.equals(userIdentity.getUserProfile().getCommsProfile().getCommsId());
    }

    private boolean recordSingleMetric(String str, String str2) {
        CounterMetric generateOperational = CounterMetric.generateOperational(str);
        generateOperational.getMetadata().put("source", str2);
        try {
            MetricsHelper.recordSingleOccurrence(generateOperational);
            return true;
        } catch (NullPointerException unused) {
            LOG.e("Only a test should have null MetricsHelper. Metrics are not being recorded on tests.");
            return false;
        }
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public void acceptCall(@NonNull String str) {
        ensureInitialized();
        this.lazyCommsManager.mo358get().acceptCall(str);
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public void beginCall(@NonNull VoxCallInfo voxCallInfo) {
        ensureInitialized();
        this.lazyCommsManager.mo358get().beginCall(voxCallInfo);
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public void enableVideoStreamInVideoCall(@NonNull boolean z) {
        ensureInitialized();
        this.lazyCommsManager.mo358get().enableVideoStreamInVideoCall(z);
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public void endActiveCallIfAny(@Nullable String str) {
        ensureInitialized();
        this.lazyCommsManager.mo358get().endActiveCallIfAny(str);
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public void ensureInitialized() {
        if (!this.initializeComplete) {
            initialize();
        }
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    @Nullable
    public CharSequence getProfileName() {
        if (!this.lazyCommsDeviceSupport.mo358get().check()) {
            LOG.i("Comms not supported.");
            return null;
        }
        return this.lazyCommsManager.mo358get().getProfileName();
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public synchronized void initialize() {
        if (this.initializeInProgress || this.initializeComplete) {
            return;
        }
        this.initializeInProgress = true;
        LOG.i("Starting initialize");
        if (this.lazyCommsDeviceSupport.mo358get().check()) {
            initializeComms();
        } else {
            LOG.i("Comms not supported.");
        }
        this.initializeComplete = true;
        this.initializeInProgress = false;
    }

    @VisibleForTesting
    void initializeComms() {
        CommsManager mo358get = this.lazyCommsManager.mo358get();
        final IdentityService mo358get2 = this.lazyIdentityService.mo358get();
        mo358get.setDeviceNameForMAPRegistration(this.deviceNameTemplate);
        if (mo358get2.isAuthenticated(className)) {
            LOG.i("User is authenticated; starting comms.");
            recordSingleMetric(MetricKeys.COMMS_STARTED, "initialize()");
            mo358get.startComms();
        }
        if (this.identityEventSubscription == null) {
            LOG.i("identityEventSubscription is null; setting up callback to later start comms");
            this.identityEventSubscription = this.lazyEventBus.mo358get().getSubscriber().subscribeFilter($$Lambda$CommsConversationService$kzq_FEoFD63l9AocHU9f9HAGZM4.INSTANCE, new MessageHandler() { // from class: com.amazon.deecomms.conversation.-$$Lambda$CommsConversationService$K7cuRhLmmxQnU7mH4usa9Z2Euhw
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    CommsConversationService.this.lambda$initializeComms$1$CommsConversationService(mo358get2, message);
                }
            });
        } else {
            LOG.d("identityEventSubscription was not null");
        }
        updateCommsWithUser(mo358get2.getUser(className));
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public boolean isUserProvisioned() {
        ensureInitialized();
        return !TextUtils.isEmpty(this.lazyCommsManager.mo358get().getCommsId());
    }

    public /* synthetic */ void lambda$initializeComms$1$CommsConversationService(IdentityService identityService, Message message) {
        userChangedCallback(identityService.getUser(className));
    }

    @Override // com.amazon.deecomms.conversation.ConversationService
    public void passDirectivePayload(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        ensureInitialized();
        this.lazyCommsManager.mo358get().passDirectivePayload(str, str2, str3);
    }

    void updateCommsWithUser(UserIdentity userIdentity) {
        boolean hasActiveDevices;
        this.lazyCommsFeatureService.mo358get().pushFeatures(userIdentity);
        if (userIdentity == null) {
            LOG.w("user is null");
            hasActiveDevices = false;
        } else {
            hasActiveDevices = userIdentity.hasActiveDevices();
        }
        CommsLogger commsLogger = LOG;
        Object[] objArr = new Object[1];
        objArr[0] = hasActiveDevices ? "yes" : "no";
        commsLogger.i(String.format("userHasDevice: %s", objArr));
        CommsDaggerWrapper.getComponent().getCommsIdentityManager().setHasDevices(hasActiveDevices);
    }

    @VisibleForTesting
    void userChangedCallback(@Nullable UserIdentity userIdentity) {
        if (userIdentity == null) {
            LOG.i("[userChangedCallback] has null user.");
            this.lazyCommsManager.mo358get().logoutComms();
        } else if (isSameUser(userIdentity) && CommsInternal.getInstance().isCommsRunning()) {
            LOG.i("[userChangedCallback] has existing user and comms started; NOT restarting comms.");
            recordSingleMetric(MetricKeys.COMMS_STARTED, "userChangedCallback::sameUser");
        } else {
            LOG.i("[userChangedCallback] has user; starting comms.");
            recordSingleMetric(MetricKeys.COMMS_STARTED, "userChangedCallback");
            this.lazyCommsManager.mo358get().startComms();
        }
        updateCommsWithUser(userIdentity);
        if (userIdentity != null) {
            CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
            String str = null;
            String marketplaceId = userIdentity.getOriginalMarketplace() == null ? null : userIdentity.getOriginalMarketplace().getObfuscatedId().toString();
            if (userIdentity.getOriginalMarketplace() != null) {
                str = userIdentity.getOriginalMarketplace().getCountryCode().toString();
            }
            commsIdentityManager.setUserPFMInfo(new MarketplaceInfo(marketplaceId, str, getCommsPfmById(marketplaceId, MarketplaceName.US.toString()), true));
            commsIdentityManager.setCountryOfResidence(userIdentity.getCountryOfResidence());
            this.lazyCommsManager.mo358get().setCommsLocale(determineDisplayLocale());
        }
    }
}
