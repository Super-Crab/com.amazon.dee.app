package com.amazon.deecomms.calling.controller;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.enums.EnhancedProcessingState;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetMpuEnabled;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.settings.IdentityPreferencesProvider;
import java.util.List;
/* loaded from: classes12.dex */
public class MPUSettingHandler extends AsyncTask<Void, Void, Void> {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MPUSettingHandler.class);
    private String callProvider;
    private String callerHGId;
    private GetMpuEnabled getMpuEnabled;
    private List<String> operations;
    private IdentityPreferencesProvider provider;
    private String remoteParticipantID;
    private Runnable runnable;
    private SipClientState sipClientState;

    public MPUSettingHandler(@NonNull String str, @NonNull SipClientState sipClientState, @NonNull List<String> list, @NonNull String str2, @NonNull String str3, @NonNull GetMpuEnabled getMpuEnabled, @NonNull Runnable runnable) {
        this.callerHGId = str;
        this.sipClientState = sipClientState;
        this.runnable = runnable;
        this.operations = list;
        this.getMpuEnabled = getMpuEnabled;
        this.callProvider = str3;
        this.remoteParticipantID = str2;
        this.provider = new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(Void... voidArr) {
        for (String str : this.operations) {
            try {
                if (Constants.GET_SETTING.equalsIgnoreCase(str)) {
                    boolean parseBoolean = Boolean.parseBoolean(this.provider.get(this.callerHGId, Constants.MPU_ENABLED));
                    CommsLogger commsLogger = LOG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("MPU Setting is ");
                    sb.append(parseBoolean);
                    commsLogger.i(sb.toString());
                    this.sipClientState.setEnhancedProcessingSetting(parseBoolean);
                } else if (Constants.GET_MPU_ENABLED.equalsIgnoreCase(str)) {
                    if (this.remoteParticipantID != null && this.callProvider != null && this.getMpuEnabled.getMpuEnabled(this.remoteParticipantID, this.callProvider).isEnabled()) {
                        LOG.i("MPU should be used for the call");
                        this.sipClientState.setSrtpKey(Constants.DTLS);
                        this.sipClientState.setEnhancedProcessingState(EnhancedProcessingState.ON);
                    }
                } else if (Constants.SET_SETTING.equalsIgnoreCase(str)) {
                    this.provider.set(this.callerHGId, Constants.MPU_ENABLED, true);
                }
            } catch (ServiceException unused) {
                LOG.e("Error occurred while retrieving MPU settings");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Void r1) {
        this.runnable.run();
    }

    public MPUSettingHandler(@NonNull String str, @NonNull SipClientState sipClientState, @NonNull List<String> list, @NonNull String str2, @NonNull String str3, @NonNull Runnable runnable) {
        this.callerHGId = str;
        this.sipClientState = sipClientState;
        this.runnable = runnable;
        this.operations = list;
        this.getMpuEnabled = new GetMpuEnabled();
        this.callProvider = str3;
        this.remoteParticipantID = str2;
        this.provider = new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER);
    }

    public MPUSettingHandler(@NonNull String str, @NonNull SipClientState sipClientState, @NonNull List<String> list, @NonNull Runnable runnable) {
        this.callerHGId = str;
        this.sipClientState = sipClientState;
        this.runnable = runnable;
        this.operations = list;
        this.getMpuEnabled = this.getMpuEnabled;
        this.callProvider = this.callProvider;
        this.remoteParticipantID = this.remoteParticipantID;
        this.provider = new IdentityPreferencesProvider(IdentityPreferencesProvider.AuthenticationType.AS_COMMS_USER);
    }

    public MPUSettingHandler(@NonNull String str, @NonNull SipClientState sipClientState, @NonNull Runnable runnable, @NonNull List<String> list, @NonNull IdentityPreferencesProvider identityPreferencesProvider) {
        this.callerHGId = str;
        this.sipClientState = sipClientState;
        this.runnable = runnable;
        this.operations = list;
        this.provider = identityPreferencesProvider;
    }
}
