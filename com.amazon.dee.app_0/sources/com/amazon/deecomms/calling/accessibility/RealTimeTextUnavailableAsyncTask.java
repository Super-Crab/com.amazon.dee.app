package com.amazon.deecomms.calling.accessibility;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import java.lang.ref.WeakReference;
/* loaded from: classes12.dex */
public class RealTimeTextUnavailableAsyncTask extends AsyncTask<Object, Void, Void> {
    private final String callerCommsId;
    private final CapabilitiesManager capabilitiesManager;
    private final WeakReference<Context> context;
    private final IdentityPreferencesProvider identityPreferencesProvider;
    private final RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;
    private final RealTimeTextUnavailableToast realTimeTextUnavailableToast;
    private final SipClientState sipClientState;

    @VisibleForTesting
    public RealTimeTextUnavailableAsyncTask(@NonNull CapabilitiesManager capabilitiesManager, @NonNull SipClientState sipClientState, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority, @NonNull IdentityPreferencesProvider identityPreferencesProvider, @NonNull Context context, @NonNull RealTimeTextUnavailableToast realTimeTextUnavailableToast, @NonNull String str) {
        this.capabilitiesManager = capabilitiesManager;
        this.callerCommsId = str;
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
        this.identityPreferencesProvider = identityPreferencesProvider;
        this.sipClientState = sipClientState;
        this.context = new WeakReference<>(context);
        this.realTimeTextUnavailableToast = realTimeTextUnavailableToast;
    }

    private void showRTTIncapableMessage() {
        if (!this.capabilitiesManager.isRealTimeTextEnabled() || this.context.get() == null) {
            return;
        }
        this.realTimeTextUnavailableToast.showRTTIncapableToast(LayoutInflater.from(this.context.get()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(@Nullable Object... objArr) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        boolean z = false;
        if (this.capabilitiesManager.isRealTimeTextEnabled()) {
            try {
                z = Boolean.parseBoolean(this.identityPreferencesProvider.get(this.callerCommsId, RealTimeTextConstants.RTT_SETTINGS_KEY));
            } catch (ServiceException unused) {
            }
            this.sipClientState.setRTTSettingEnabled(z);
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(@Nullable Void r1) {
        if (this.realTimeTextEnablementAuthority.shouldShowRTTUnavailablePopup()) {
            showRTTIncapableMessage();
        }
    }

    public RealTimeTextUnavailableAsyncTask(@NonNull CapabilitiesManager capabilitiesManager, @NonNull SipClientState sipClientState, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority, @NonNull Context context, @NonNull RealTimeTextUnavailableToast realTimeTextUnavailableToast, @NonNull String str) {
        this.capabilitiesManager = capabilitiesManager;
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
        this.sipClientState = sipClientState;
        this.context = new WeakReference<>(context);
        this.realTimeTextUnavailableToast = realTimeTextUnavailableToast;
        this.identityPreferencesProvider = new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER);
        this.callerCommsId = str;
    }
}
