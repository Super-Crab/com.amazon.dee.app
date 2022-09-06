package com.amazon.deecomms.oobe.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.text.MessageFormat;
/* loaded from: classes12.dex */
public class DeviceMetadataStoreRegistrar {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeviceMetadataStoreRegistrar.class);
    private final CapabilitiesManager capabilitiesManager;
    private final CommsIdentityManager commsIdentityManager;
    private final Context context;
    private String deviceSerialNumber = null;
    private String deviceType = null;
    private SharedPreferences sharedPrefs;

    public DeviceMetadataStoreRegistrar(Context context, CommsIdentityManager commsIdentityManager, CapabilitiesManager capabilitiesManager) {
        this.context = context;
        this.commsIdentityManager = commsIdentityManager;
        this.capabilitiesManager = capabilitiesManager;
    }

    public void registerDeviceAndCommsIdWithDMDS(@NonNull final String str) {
        if (!this.capabilitiesManager.isVoxPersonalizationEnabled()) {
            LOG.e("Unable to register device to DMDS, user is not in the weblab.");
            return;
        }
        Context context = this.context;
        if (context == null) {
            LOG.e("Unable to register device to DMDS, context was null.");
            return;
        }
        this.sharedPrefs = context.getSharedPreferences("SHARED_PREFS", 0);
        if (this.sharedPrefs.getBoolean(Constants.DEVICE_REGISTERED_WITH_DMDS, false)) {
            LOG.i("Device has already registered to DMDS.");
        } else if (TextUtils.isEmpty(this.commsIdentityManager.getCommsId("registerDeviceAndCommsIdWithDMDS", false))) {
            LOG.e("Unable to register device to DMDS, commsId was null.");
        } else {
            DeviceDataStore deviceDataStore = DeviceDataStore.getInstance(this.context);
            try {
                this.deviceSerialNumber = deviceDataStore.getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                if (TextUtils.isEmpty(this.deviceSerialNumber)) {
                    LOG.e("Device serial number was null.");
                    return;
                }
                this.deviceType = deviceDataStore.getValue("DeviceType");
                if (TextUtils.isEmpty(this.deviceType)) {
                    LOG.e("Device type was null.");
                } else {
                    new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.oobe.util.DeviceMetadataStoreRegistrar.1
                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // android.os.AsyncTask
                        public Void doInBackground(Void... voidArr) {
                            if (DeviceMetadataStoreRegistrar.this.registerToDMDS(str)) {
                                DeviceMetadataStoreRegistrar.LOG.i("Registration succeeded - setting DEVICE_REGISTERED_WITH_DMDS to true in shared prefs.");
                                GeneratedOutlineSupport1.outline143(DeviceMetadataStoreRegistrar.this.sharedPrefs, Constants.DEVICE_REGISTERED_WITH_DMDS, true);
                                return null;
                            }
                            return null;
                        }
                    }.execute(new Void[0]);
                }
            } catch (DeviceDataStoreException e) {
                LOG.e("Error while getValue from deviceDataStore.", e);
            }
        }
    }

    @VisibleForTesting
    boolean registerToDMDS(@NonNull String str) {
        ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_SET_DEVICE_ACCOUNT_COMMS_ID);
        String format = MessageFormat.format(AppUrl.SET_DEVICE_ACCOUNT_COMMS_ID, this.commsIdentityManager.getCommsId("registerToDMDS", false));
        ObjectNode mo7041createObjectNode = new ObjectMapper().mo7041createObjectNode();
        mo7041createObjectNode.put("deviceType", this.deviceType);
        mo7041createObjectNode.put("deviceSerialNumber", this.deviceSerialNumber);
        CounterMetric generateOperational = CounterMetric.generateOperational(MetricKeys.REGISTER_TO_DMDS);
        generateOperational.getMetadata().put("source", str);
        MetricsHelper.recordSingleOccurrence(generateOperational);
        try {
            IHttpClient.Response mo3640execute = aCMSClient.request(format).authenticatedAsCurrentCommsUser().put("application/json", mo7041createObjectNode).mo3640execute();
            CommsLogger commsLogger = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("Successfully added device type and device serial number for comms with response: ");
            sb.append(mo3640execute.code());
            commsLogger.i(sb.toString());
            mo3640execute.close();
            return true;
        } catch (ServiceException | IOException e) {
            LOG.e("Failed to add device type and device serial number for comms in DMDS.", e);
            return false;
        }
    }
}
