package com.amazon.deecomms.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telecom.TelecomManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.MarketplaceName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.accessories.CommsAccessorySessionListener;
import com.amazon.deecomms.alexa.CommsDirectiveHandler;
import com.amazon.deecomms.alexa.HalloConstants;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.auth.AccountDeregisterTask;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.model.CurrentVoxCallInfo;
import com.amazon.deecomms.calling.model.VoxCallInfo;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCContextProvider;
import com.amazon.deecomms.calling.phonecallcontroller.PCCDirectiveHandler;
import com.amazon.deecomms.calling.ui.EnhancedProcessingStateObservable;
import com.amazon.deecomms.calling.ui.NameChangeObservable;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.VoxUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.acmsrecipes.GetDeviceCommsPreferences;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.conversation.CommsConversationService;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.core.FeatureFlagManager;
import com.amazon.deecomms.media.audio.AudioRecorder;
import com.amazon.deecomms.notifications.PushNotificationManager;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerDirectiveHandler;
import com.amazon.deecomms.util.BiConsumer;
import com.android.tools.r8.GeneratedOutlineSupport;
import dagger.Lazy;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public final class InternalCommsManager implements CommsManager {
    public static final String BUNDLE_KEY_NOTIFICATION_REDIRECT_PATH = "notification_redirect_path";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, InternalCommsManager.class);
    private static final String className = InternalCommsManager.class.getName();
    @Inject
    ApplicationManager applicationManager;
    @Inject
    AudioRecorder audioRecorder;
    @Inject
    CallContext callContext;
    @Inject
    Lazy<CallManager> callManagerLazy;
    @Inject
    CallingFacade callingFacade;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsConnectivityMonitor commsConnectivityMonitor;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsInternal commsInternal;
    @Inject
    ContactsOperationsManager contactsOperationsManager;
    private final Context context;
    @Inject
    EnhancedProcessingStateObservable enhancedProcessingStateObservable;
    @Inject
    FeatureFlagManager featureFlagManager;
    @Inject
    NameChangeObservable nameChangeObservable;
    @Inject
    protected PCCDirectiveHandler pCCDirectiveHandler;
    @Inject
    PCCContextProvider pccContextProvider;
    @Inject
    ProvisioningManager provisioningManager;
    @Inject
    PushNotificationManager pushNotificationManager;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    @Inject
    TelecomManager telecomManager;
    private final Set<CommsView> validEntryViewSet;
    @Inject
    VoxUtils voxUtils;

    public InternalCommsManager(@NonNull Context context) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.context = context;
        this.validEntryViewSet = new HashSet();
        this.validEntryViewSet.add(CommsView.ContactList);
        this.validEntryViewSet.add(CommsView.ReactNativeContactList);
        this.validEntryViewSet.add(CommsView.ReactNativeConversationList);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void acceptCall(@NonNull String str) {
        CallUtils.acceptCall(this.context, str);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void beginCall(@NonNull VoxCallInfo voxCallInfo) {
        this.voxUtils.beginCall(voxCallInfo);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void clearCommsUIDelegate() {
        setCommsUIDelegate(null);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void deregisterForPush() {
        this.pushNotificationManager.deregisterForPushAsynchronously();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void enableVideoStreamInVideoCall(boolean z) {
        this.callManagerLazy.mo358get().enableVideoStreamInVideoCall(z);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void endActiveCallIfAny(@NonNull String str) {
        CallUtils.cancelAnyCall(str);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getAor() {
        return this.commsIdentityManager.getAor();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getCommsId() {
        return this.commsIdentityManager.getCommsId("InternalCommsManager", false);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @NonNull
    public CommsIdentity getCommsIdentity() {
        return this.commsIdentityManager.getCurrentCommsIdentity();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getDirectedId() {
        return this.commsIdentityManager.getDirectedId("InternalCommsManager", false);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getEmail() {
        return this.commsIdentityManager.getEmail();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getFirstName() {
        return this.commsIdentityManager.getFirstName();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public String getGcmSenderId() {
        return this.pushNotificationManager.getGcmSenderId();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getHashedCommsId() {
        return this.commsIdentityManager.getHashedCommsId();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getHomeGroupId() {
        return this.commsIdentityManager.getHomeGroupId("InternalCommsManager", false);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getLastName() {
        return this.commsIdentityManager.getLastName();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getPhoneNumber() {
        return this.commsIdentityManager.getPhoneNumber();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    @Nullable
    public String getProfileName() {
        return this.commsIdentityManager.getProfileName();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public CommsProvisionStatus getUserProvisionStatus() {
        return this.commsIdentityManager.getProvisionStatus(true, "InternalCommsManager.getUserProvisionStatus", false);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void handleInterruptedAudioMessageRecording() {
        this.audioRecorder.handleInterruptedVoiceMessageRecording();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void initializeAccessoryComponents() {
        CommsAccessorySessionListener.initializeAccessoryComponents();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public boolean isUserAutoProvisioned() {
        if (!this.capabilitiesManager.isAutoProvisioningEnabled()) {
            return false;
        }
        return CommsProvisionStatus.AUTO_PROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "InternalCommsManager.isUserAutoProvisioned", false));
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public boolean isUserProvisioned() {
        return CommsProvisionStatus.PROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "InternalCommsManager.isUserProvisioned", false));
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public boolean isValidEntryPoint(@NonNull CommsView commsView) {
        return this.validEntryViewSet.contains(commsView);
    }

    public /* synthetic */ void lambda$performCommsBlocking$0$InternalCommsManager(Boolean bool, Map map) {
        if (bool.booleanValue()) {
            LOG.i("Navigating to this device's communication settings");
            Bundle bundle = new Bundle();
            bundle.putString(Constants.BUNDLE_SERIAL_NUMBER, (String) map.get(Constants.BUNDLE_SERIAL_NUMBER));
            bundle.putString("deviceType", (String) map.get("deviceType"));
            this.applicationManager.navigateToView(CommsView.CommunicationsSettings, bundle, false);
            return;
        }
        LOG.i("Cancel was selected - do not route");
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void logoutComms() {
        new AccountDeregisterTask(this.context, false).execute(new Void[0]);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public boolean onPush(Bundle bundle) {
        return this.pushNotificationManager.onPush(bundle);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void passDirectivePayload(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        char c;
        int hashCode = str2.hashCode();
        if (hashCode == 117940768) {
            if (str2.equals(HalloConstants.CONTACTS_NAMESPACE)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 1253912869) {
            if (str2.equals("SipClient")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1682705326) {
            if (hashCode == 2042189718 && str2.equals(PCCConstants.PHONE_CALL_CONTROLLER_NAMESPACE)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str2.equals(MessagingControllerConstant.MESSAGING_CONTROLLER_NAMESPACE)) {
                c = 3;
            }
            c = 65535;
        }
        if (c == 0 || c == 1) {
            new CommsDirectiveHandler(this.context, this.commsIdentityManager, this.callManagerLazy, this.voxUtils, this.applicationManager, this.callingFacade, this.capabilitiesManager, this.sipClientState, this.callContext, this.nameChangeObservable, this.enhancedProcessingStateObservable).handleDirective(str, str2, str3);
        } else if (c == 2) {
            this.pCCDirectiveHandler.handleDirective(this.context, str, str3);
        } else if (c != 3) {
            GeneratedOutlineSupport.outline3("Received an unsupported directive over namespace: ", str2, LOG);
        } else {
            new MessagingControllerDirectiveHandler(this.capabilitiesManager).handleDirective(this.context, str, str3);
        }
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public boolean performCommsBlocking(@NonNull Activity activity) {
        if (!GetDeviceCommsPreferences.isDeviceCommunicationsEnabled(activity, CommsDaggerWrapper.getComponent().getCapabilitiesManager())) {
            LOG.i("Comms is disabled, showing popup");
            Utils.shouldRedirectToSettingsDialog(activity, new BiConsumer() { // from class: com.amazon.deecomms.api.-$$Lambda$InternalCommsManager$WXgZBGTk3rE2CM1wbXu2bCm-_ts
                @Override // com.amazon.deecomms.util.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    InternalCommsManager.this.lambda$performCommsBlocking$0$InternalCommsManager((Boolean) obj, (Map) obj2);
                }
            });
            return true;
        }
        return false;
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void registerForPush(String str, String str2, String str3) {
        registerForPush(str, str3);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void requestFragment(CommsView commsView, Bundle bundle, boolean z) {
        FragmentNavigationRouter.switchToFragment(commsView, bundle, z);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void resetDynamicFeatures(@NonNull Map<String, Boolean> map) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Resetting the dynamic features to " + map);
        setDynamicFeatures(true, map);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void setCallInfo(@NonNull CurrentVoxCallInfo currentVoxCallInfo) {
        beginCall(currentVoxCallInfo);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void setCommsLocale(@NonNull String str) {
        this.commsInternal.setLocale(str);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void setCommsUIDelegate(CommsUIDelegateBase commsUIDelegateBase) {
        this.applicationManager.setUIDelegate(commsUIDelegateBase);
        if (commsUIDelegateBase != null) {
            if (CommsProvisionStatus.DEPROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "InternalCommsManager.setCommsUIDelegate", false))) {
                commsUIDelegateBase.setIndicatorIconVisible(false);
            } else if (this.commsIdentityManager.isCoreIdentityPopulated("InternalCommsManager.setCommsUIDelegate", false) && CommsProvisionStatus.PROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "InternalCommsManager.setCommsUIDelegate", false))) {
            } else {
                commsUIDelegateBase.setIndicatorIconVisible(true);
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void setDeviceNameForMAPRegistration(@NonNull String str) {
        this.commsInternal.setDeviceNameForRegisteringWithMAP(str);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void setDynamicFeatures(@NonNull Map<String, Boolean> map) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("Setting the dynamic features to " + map);
        setDynamicFeatures(false, map);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void setupCommsUser() {
        LOG.i("setting up Comms user");
        CommsComponent component = CommsDaggerWrapper.getComponent();
        IdentityService identityService = component.getIdentityService();
        CommsDynamicFeatureService commsDynamicFeatureService = component.getCommsDynamicFeatureService();
        if (identityService != null) {
            UserIdentity user = identityService.getUser(className);
            if (user != null) {
                String str = null;
                String marketplaceId = user.getOriginalMarketplace() == null ? null : user.getOriginalMarketplace().getObfuscatedId().toString();
                if (user.getOriginalMarketplace() != null) {
                    str = user.getOriginalMarketplace().getCountryCode().toString();
                }
                this.commsIdentityManager.setUserPFMInfo(new MarketplaceInfo(marketplaceId, str, CommsConversationService.getCommsPfmById(marketplaceId, MarketplaceName.US.toString()), true));
                this.commsIdentityManager.setCountryOfResidence(user.getCountryOfResidence());
                setCommsLocale(CommsConversationService.determineDisplayLocale());
            } else {
                LOG.i("userIdentity is null");
            }
            if (commsDynamicFeatureService == null) {
                return;
            }
            commsDynamicFeatureService.pushFeatures(user);
        }
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public boolean shouldRouteToReactNativeContactList() {
        return this.capabilitiesManager.isSunflowersEnabled();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void signOut() {
        this.applicationManager.appLogout();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void startComms() {
        this.commsInternal.start();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void stopComms() {
        this.commsInternal.shutDown();
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void deregisterForPush(boolean z) {
        if (z) {
            this.pushNotificationManager.deregisterForPush();
        } else {
            this.pushNotificationManager.deregisterForPushAsynchronously();
        }
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void registerForPush(String str, String str2) {
        this.pushNotificationManager.registerForPush(str, str2);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void requestFragment(String str) {
        FragmentNavigationRouter.switchToFragment(str);
    }

    private void setDynamicFeatures(boolean z, @NonNull Map<String, Boolean> map) {
        EnumMap<CommsDynamicFeature, Boolean> enumMap = new EnumMap<>(CommsDynamicFeature.class);
        if (z) {
            for (CommsDynamicFeature commsDynamicFeature : CommsDynamicFeature.values()) {
                enumMap.put((EnumMap<CommsDynamicFeature, Boolean>) commsDynamicFeature, (CommsDynamicFeature) false);
            }
        }
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            CommsDynamicFeature featureFromName = CommsDynamicFeature.getFeatureFromName(entry.getKey());
            if (featureFromName != null) {
                enumMap.put((EnumMap<CommsDynamicFeature, Boolean>) featureFromName, (CommsDynamicFeature) entry.getValue());
            }
        }
        this.featureFlagManager.setFeatures(enumMap);
    }

    @Override // com.amazon.deecomms.api.CommsManager
    public void registerForPush() {
        this.pushNotificationManager.registerForPush();
    }
}
