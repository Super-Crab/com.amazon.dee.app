package com.amazon.alexa.drive.smart.device;

import android.content.Context;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.navigation.AutoValueAdapterFactory;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.alexa.drive.smart.device.data.SmartDevice;
import com.amazon.alexa.drive.smart.device.guard.Guard;
import com.amazon.alexa.drive.smart.device.guard.GuardDataProvider;
import com.amazon.alexa.drive.smart.device.guard.model.GuardResponse;
import com.amazon.alexa.drive.smart.device.guard.model.ModeResult;
import com.amazon.alexa.drive.smart.device.lock.Lock;
import com.amazon.alexa.drive.smart.device.lock.LockDataProvider;
import com.amazon.alexa.drive.smart.device.lock.model.Endpoint;
import com.amazon.alexa.drive.smart.device.lock.model.Feature;
import com.amazon.alexa.drive.smart.device.lock.model.LockResponse;
import com.amazon.alexa.drive.smart.device.lock.model.Operation;
import com.amazon.alexa.drive.smart.device.lock.model.Property;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class SmartDeviceInteractor implements SmartDeviceContract.Interactor {
    private static final String GUARD_LOCKED = "AWAY";
    private static final String GUARD_UNLOCKED = "HOME";
    public static final String HAS_GUARD = "hasGuard";
    private static final String LOCK_FEATURE_CONTROL_RESPONSE = "featureControlResponses";
    private static final String LOCK_FEATURE_REACHABLE = "OK";
    private static final String LOCK_FEATURE_RESPONSE_CODE = "code";
    private static final String LOCK_FEATURE_RESPONSE_CODE_SUCCESS = "SUCCESS";
    private static final String LOCK_FEATURE_RESPONSE_ID = "endpointId";
    private static final String LOCK_FEATURE_RESPONSE_OPERATION_LOCK = "lock";
    private static final String LOCK_FEATURE_RESPONSE_OPERATION_NAME = "featureOperationName";
    private static final String LOCK_FEATURE_UNLOCK_PERMISSION = "unlock";
    private static final String LOCK_LIST_FEATURE = "listEndpoints";
    private static final String LOCK_LOCKED = "LOCKED";
    private static final String LOCK_RESPONSE_DATA = "data";
    private static final String LOCK_SET_FEATURE = "setEndpointFeatures";
    private static final String LOCK_UNLOCKED = "UNLOCKED";
    private static final String TAG = "SmartDeviceInteractor";
    private final Context context;
    @VisibleForTesting
    GuardDataProvider guardDataProvider;
    private boolean hasGuard;
    private boolean hasUnlockPermission;
    @VisibleForTesting
    LockDataProvider lockDataProvider;
    @VisibleForTesting
    final Map<String, SmartDevice> smartDeviceMap = new HashMap();
    private SmartDeviceContract.Interactor.UpdateDevice updateDeviceListener;
    private WeblabManager weblabManager;

    /* loaded from: classes7.dex */
    public static class HomeCardData {
        private String description;
        private String guardButtonText;
        private boolean guardSecured;
        private String title;

        public HomeCardData(String str, String str2, String str3, boolean z) {
            this.title = str;
            this.description = str2;
            this.guardButtonText = str3;
            this.guardSecured = z;
        }

        public String getDescription() {
            return this.description;
        }

        public String getGuardButtonText() {
            return this.guardButtonText;
        }

        public String getTitle() {
            return this.title;
        }

        public boolean isGuardSecured() {
            return this.guardSecured;
        }
    }

    public SmartDeviceInteractor(Context context, WeblabManager weblabManager) {
        this.context = context;
        this.weblabManager = weblabManager;
        this.guardDataProvider = new GuardDataProvider(context, new SmartDeviceContract.ServerResponseListener() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$kUqMkYyb_1pipnFdd4w179Rv_40
            @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.ServerResponseListener
            public final void onResponse(String str, String str2) {
                SmartDeviceInteractor.this.updateGuardResponse(str, str2);
            }
        });
        this.lockDataProvider = new LockDataProvider(context, new SmartDeviceContract.ServerResponseListener() { // from class: com.amazon.alexa.drive.smart.device.-$$Lambda$2JVSXKxPU4hf2hL05HspBoJT_TE
            @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.ServerResponseListener
            public final void onResponse(String str, String str2) {
                SmartDeviceInteractor.this.updateLockResponse(str, str2);
            }
        });
    }

    private void updateLockIfNecessary(Endpoint endpoint, List<Feature> list) {
        Lock lock = new Lock();
        lock.setTag(endpoint.getFriendlyNameObject().getValue().getText());
        lock.setEndpointId(endpoint.getId());
        lock.setTitle(lock.getTag());
        this.hasUnlockPermission = false;
        for (Feature feature : list) {
            updateUnlockPermission(feature);
            List<Property> properties = feature.getProperties();
            if (properties != null) {
                for (Property property : properties) {
                    if (property.getLockState() != null) {
                        lock.setLocked(LOCK_LOCKED.equals(property.getLockState()));
                    }
                    if (property.getReachabilityStatusValue() != null) {
                        lock.setReachable(LOCK_FEATURE_REACHABLE.equals(property.getReachabilityStatusValue()));
                    }
                }
                lock.setIcon(lock.isLocked() ? this.context.getDrawable(R.drawable.ic_smart_lock_on) : this.context.getDrawable(R.drawable.ic_smart_lock_off));
                if (lock.isReachable()) {
                    lock.setDescription("");
                    lock.setLockState(lock.isLocked() ? this.context.getString(R.string.dm_smart_home_locked) : this.context.getString(R.string.dm_smart_home_unlocked));
                } else {
                    lock.setDescription(this.context.getString(R.string.dm_smart_home_lock_unresponsive));
                    lock.setLockState("");
                }
                if (lock.getTitle() != null) {
                    this.smartDeviceMap.put(endpoint.getId(), lock);
                }
            }
        }
    }

    private void updateSetLockStatusIfNecessary(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("data")) {
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (!jSONObject2.has(LOCK_SET_FEATURE)) {
                return;
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject(LOCK_SET_FEATURE);
            if (!jSONObject3.has(LOCK_FEATURE_CONTROL_RESPONSE)) {
                return;
            }
            JSONArray jSONArray = jSONObject3.getJSONArray(LOCK_FEATURE_CONTROL_RESPONSE);
            String str2 = "size of featureControlResponses = " + jSONArray.length();
            if (jSONArray.length() <= 0) {
                return;
            }
            JSONObject jSONObject4 = jSONArray.getJSONObject(0);
            String string = jSONObject4.getString("code");
            String string2 = jSONObject4.getString(LOCK_FEATURE_RESPONSE_ID);
            String string3 = jSONObject4.getString(LOCK_FEATURE_RESPONSE_OPERATION_NAME);
            if (!"SUCCESS".equals(string) || !this.smartDeviceMap.containsKey(string2)) {
                return;
            }
            ((Lock) this.smartDeviceMap.get(string2)).setLastUserAction(string3);
            this.updateDeviceListener.onUpdate();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline107("Get exception when switch lock state: "), TAG);
        }
    }

    private void updateUnlockPermission(Feature feature) {
        List<Operation> operations = feature.getOperations();
        if (operations == null || operations.size() == 0) {
            return;
        }
        for (Operation operation : operations) {
            this.hasUnlockPermission = this.hasUnlockPermission || "unlock".equals(operation.getName());
        }
    }

    @VisibleForTesting
    void getAllSmartDevices() {
        try {
            if (this.hasGuard) {
                this.guardDataProvider.getGuardStatus();
            }
            if (!this.weblabManager.isSmartHomeLockWeblabEnabled()) {
                return;
            }
            this.lockDataProvider.getLockDevice();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Interactor
    public HomeCardData getHomeCardData() {
        String string;
        String string2;
        String string3;
        String string4;
        if (this.smartDeviceMap.isEmpty()) {
            return null;
        }
        boolean z = false;
        String str = "";
        int i = 0;
        for (Map.Entry<String, SmartDevice> entry : this.smartDeviceMap.entrySet()) {
            if (entry.getValue() instanceof Guard) {
                boolean isLocked = entry.getValue().isLocked();
                if (isLocked) {
                    string4 = this.context.getString(R.string.dm_smart_home_guard_away);
                } else {
                    string4 = this.context.getString(R.string.dm_smart_home_guard_home);
                }
                String str2 = string4;
                z = isLocked;
                str = str2;
            }
            if (entry.getValue().isLocked()) {
                i++;
            }
        }
        if (i == 0) {
            string = this.context.getString(R.string.dm_smart_home_not_secured);
            string2 = this.context.getString(R.string.dm_smart_home_not_secured_description);
        } else if (i < this.smartDeviceMap.size()) {
            string = this.context.getString(R.string.dm_smart_home_partially_secured);
            if (i == 1) {
                string3 = this.context.getString(R.string.dm_smart_home_partially_secured_device);
            } else {
                string3 = this.context.getString(R.string.dm_smart_home_partially_secured_devices);
            }
            string2 = String.format(Locale.ENGLISH, "%d %s", Integer.valueOf(i), string3);
        } else {
            string = this.context.getString(R.string.dm_smart_home_secured);
            string2 = this.context.getString(R.string.dm_smart_home_secured_description);
        }
        return new HomeCardData(string, string2, str, z);
    }

    public void getSmartDeviceInfo() {
        try {
            this.guardDataProvider.hasGuard();
        } catch (MalformedURLException unused) {
        }
    }

    public List<SmartDevice> getSmartDevices() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, SmartDevice> entry : this.smartDeviceMap.entrySet()) {
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }

    public boolean hasGuard() {
        return this.hasGuard;
    }

    public boolean hasUnlockPermission() {
        return this.hasUnlockPermission;
    }

    @VisibleForTesting
    void setHasGuard(boolean z) {
        this.hasGuard = z;
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Interactor
    public void setUpdateDeviceListener(SmartDeviceContract.Interactor.UpdateDevice updateDevice) {
        this.updateDeviceListener = updateDevice;
    }

    @Override // com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract.Interactor
    public void switchDeviceLockState(SmartDevice smartDevice) {
        int smartDeviceType = smartDevice.getSmartDeviceType();
        boolean z = true;
        if (smartDeviceType == 1) {
            try {
                this.guardDataProvider.setGuardStatus(smartDevice.isLocked() ? "HOME" : GUARD_LOCKED);
            } catch (MalformedURLException e) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Guard device maMalformedURL exception");
                outline107.append(e.getMessage());
                Log.e(str, outline107.toString());
            }
        } else if (smartDeviceType != 2) {
        } else {
            Lock lock = (Lock) smartDevice;
            try {
                LockDataProvider lockDataProvider = this.lockDataProvider;
                String endpointId = lock.getEndpointId();
                if (lock.isLocked()) {
                    z = false;
                }
                lockDataProvider.setLockDevice(endpointId, z);
            } catch (MalformedURLException e2) {
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Lock device maMalformedURL exception ");
                outline1072.append(e2.getMessage());
                Log.e(str2, outline1072.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateGuardResponse(String str, String str2) {
        Gson create = new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
        if (str.equals(HAS_GUARD)) {
            JsonObject jsonObject = (JsonObject) create.fromJson(str2, (Class<Object>) JsonObject.class);
            if (jsonObject == null) {
                return;
            }
            JsonElement jsonElement = jsonObject.get(HAS_GUARD);
            this.hasGuard = jsonElement != null && jsonElement.getAsBoolean();
            getAllSmartDevices();
            return;
        }
        GuardResponse guardResponse = (GuardResponse) create.fromJson(str2, (Class<Object>) GuardResponse.class);
        if (guardResponse == null) {
            return;
        }
        ModeResult guard = guardResponse.getGuard();
        String identifier = guard.getEntity().getIdentifier();
        Guard guard2 = new Guard();
        guard2.setEndpointId(identifier);
        guard2.setLocked(GUARD_LOCKED.equals(guard.getMode()));
        guard2.setTag(guard.getMode());
        this.smartDeviceMap.put(identifier, guard2);
        SmartDeviceContract.Interactor.UpdateDevice updateDevice = this.updateDeviceListener;
        if (updateDevice == null) {
            return;
        }
        updateDevice.onUpdate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void updateLockResponse(String str, String str2) {
        Gson create = new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
        if (str2.contains(LOCK_SET_FEATURE)) {
            updateSetLockStatusIfNecessary(str2);
        } else if (str2.contains(LOCK_LIST_FEATURE)) {
            for (Endpoint endpoint : ((LockResponse) create.fromJson(str2, (Class<Object>) LockResponse.class)).getData().getListEndpoints().getEndpoints()) {
                updateLockIfNecessary(endpoint, endpoint.getFeatures());
            }
            SmartDeviceContract.Interactor.UpdateDevice updateDevice = this.updateDeviceListener;
            if (updateDevice == null) {
                return;
            }
            updateDevice.onUpdate();
        }
    }
}
