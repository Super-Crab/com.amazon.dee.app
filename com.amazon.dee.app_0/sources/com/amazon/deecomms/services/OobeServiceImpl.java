package com.amazon.deecomms.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.OobeService;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.conversation.ConversationService;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.android.tools.r8.GeneratedOutlineSupport;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class OobeServiceImpl implements OobeService {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, OobeServiceImpl.class);
    private final Lazy<CapabilitiesManager> capabilitiesManagerLazy;
    private final Lazy<CommsDeviceSupport> commsDeviceSupportLazy;
    private final Lazy<CommsIdentityManager> commsIdentityManagerlazy;
    private final Lazy<CommsManager> commsManagerLazy;
    private final Context context;
    private final Lazy<ConversationService> conversationServiceLazy;
    private final Lazy<EventBus> eventBusLazy;
    private final AtomicBoolean oobeJustFinished = new AtomicBoolean(false);
    private final AtomicBoolean isOobeInProgress = new AtomicBoolean(false);

    public OobeServiceImpl(@NonNull Context context, @NonNull Lazy<CommsIdentityManager> lazy, @NonNull Lazy<CommsDeviceSupport> lazy2, @NonNull Lazy<ConversationService> lazy3, @NonNull Lazy<CommsManager> lazy4, @NonNull Lazy<CapabilitiesManager> lazy5, @NonNull Lazy<EventBus> lazy6) {
        this.context = context;
        this.commsIdentityManagerlazy = lazy;
        this.commsDeviceSupportLazy = lazy2;
        this.conversationServiceLazy = lazy3;
        this.commsManagerLazy = lazy4;
        this.capabilitiesManagerLazy = lazy5;
        this.eventBusLazy = lazy6;
    }

    @Override // com.amazon.deecomms.api.OobeService
    public void cleanUpOobe() {
        this.oobeJustFinished.set(false);
        this.isOobeInProgress.set(false);
    }

    @Override // com.amazon.deecomms.api.OobeService
    public boolean getCommsOobeJustFinished() {
        this.conversationServiceLazy.mo358get().ensureInitialized();
        boolean z = this.oobeJustFinished.get();
        GeneratedOutlineSupport.outline5("Get Oobe just finished: ", z, LOG);
        return z;
    }

    @Override // com.amazon.deecomms.api.OobeService
    public boolean getSkippedCommsOobe() {
        this.conversationServiceLazy.mo358get().ensureInitialized();
        boolean booleanPreferenceFromSharedPrefs = Utils.getBooleanPreferenceFromSharedPrefs(this.context, Constants.OOBE_SKIPPED_PREF, false);
        GeneratedOutlineSupport.outline5("Get skipped Comms Oobe: ", booleanPreferenceFromSharedPrefs, LOG);
        return booleanPreferenceFromSharedPrefs;
    }

    @Override // com.amazon.deecomms.api.OobeService
    public boolean hasSelectedProfile() {
        this.conversationServiceLazy.mo358get().ensureInitialized();
        String profileName = this.commsIdentityManagerlazy.mo358get().getProfileName();
        boolean z = profileName != null && !profileName.isEmpty();
        GeneratedOutlineSupport.outline5("Has selected profile: ", z, LOG);
        return z;
    }

    @Override // com.amazon.deecomms.api.OobeService
    public boolean isCommsOoobeCompleted() {
        boolean z = false;
        if (!this.commsDeviceSupportLazy.mo358get().check()) {
            LOG.i("Comms not supported. Oobe not completed.");
            return false;
        }
        this.conversationServiceLazy.mo358get().ensureInitialized();
        if (this.commsIdentityManagerlazy.mo358get().isCoreIdentityPopulated("isCommsOoobeCompleted", false) || this.isOobeInProgress.get()) {
            z = true;
        }
        GeneratedOutlineSupport.outline5("Is comms oobe completed: ", z, LOG);
        return z;
    }

    @Override // com.amazon.deecomms.api.OobeService
    public void routeUserToOobe(Activity activity, CommsView commsView) {
        Intent intent = new Intent(activity, OOBEActivity.class);
        intent.putExtra(OOBEActivity.OOBE_INTENT, commsView == null ? null : commsView.name());
        activity.startActivityForResult(intent, 120);
    }

    @Override // com.amazon.deecomms.api.OobeService
    public void sendOOBECompletionEvent() {
        LOG.i("Sending OOBE ended event to event bus");
        this.eventBusLazy.mo358get().publish(new Message.Builder().setEventType("comms::oobe-ended").build());
    }

    @Override // com.amazon.deecomms.api.OobeService
    public void setCommsOobeInProgress(boolean z) {
        GeneratedOutlineSupport.outline5("Setting comms oobe in progress to: ", z, LOG);
        this.conversationServiceLazy.mo358get().ensureInitialized();
        this.isOobeInProgress.set(z);
    }

    @Override // com.amazon.deecomms.api.OobeService
    public void setCommsOobeJustFinished(boolean z) {
        GeneratedOutlineSupport.outline5("Setting Oobe just finished to: ", z, LOG);
        this.conversationServiceLazy.mo358get().ensureInitialized();
        this.oobeJustFinished.set(z);
    }

    @Override // com.amazon.deecomms.api.OobeService
    public void setSkippedCommsOobe(boolean z) {
        GeneratedOutlineSupport.outline5("Setting skipped Comms Oobe to: ", z, LOG);
        this.conversationServiceLazy.mo358get().ensureInitialized();
        Utils.writeBooleanPreferenceToSharedPrefs(this.context, Constants.OOBE_SKIPPED_PREF, z);
    }

    @Override // com.amazon.deecomms.api.OobeService
    public boolean shouldShowCommsOOBEForUser() {
        CommsProvisionStatus userProvisionStatus = this.commsManagerLazy.mo358get().getUserProvisionStatus();
        boolean z = (!CommsProvisionStatus.AUTO_PROVISIONED.equals(userProvisionStatus) || !this.capabilitiesManagerLazy.mo358get().isRecommendationsEnabled()) && !CommsProvisionStatus.PROVISIONED.equals(userProvisionStatus);
        GeneratedOutlineSupport.outline5("Should show comms oobe: ", z, LOG);
        return z;
    }
}
