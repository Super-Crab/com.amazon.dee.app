package com.amazon.alexa.accessory.frames.contacts;

import com.amazon.alexa.accessory.frames.metrics.MetricsConstants;
import com.amazon.alexa.accessory.frames.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.frames.provider.StorageUtils;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class ContactsModule {
    private static final String TAG = "ContactsModule";
    public static JSONObject contactResponse;
    private static ContactsModule contactsModule;

    private ContactsModule() {
    }

    public static synchronized ContactsModule getInstance() {
        ContactsModule contactsModule2;
        synchronized (ContactsModule.class) {
            if (contactsModule == null) {
                contactsModule = new ContactsModule();
            }
            contactsModule2 = contactsModule;
        }
        return contactsModule2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$verifyAndUpdateContactDetails$1(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("failed to clear - ");
        outline107.append(th.getLocalizedMessage());
        Log.d(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_LOCAL_STORAGE_CLEAR_FAILED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$verifyAndUpdateContactDetails$3(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("failed to clear - ");
        outline107.append(th.getLocalizedMessage());
        Log.d(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_LOCAL_STORAGE_CLEAR_FAILED);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$verifyAndUpdateContactDetails$4(String str) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("success put to local storage - ");
        outline107.append(str.toString());
        Log.d(str2, outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$verifyAndUpdateContactDetails$5(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("failed to put value - ");
        outline107.append(th.getLocalizedMessage());
        Log.d(str, outline107.toString());
        MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_LOCAL_STORAGE_PUT_VALUE_FAILED);
    }

    public String createContactQueryPayload(String str) {
        Log.i(TAG, "in createContactQueryPayload");
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject4 = new JSONObject();
        try {
            jSONObject4.put("id", str);
            jSONObject4.put(ContactsModuleConstants.CONTACT_ID_TYPE_KEY, ContactsModuleConstants.CONTACT_ID_TYPE_VALUE);
            jSONArray.put(jSONObject4);
            jSONObject3.put(ContactsModuleConstants.CONTACT_IDS, jSONArray);
            jSONObject2.put(ContactsModuleConstants.FILTER_CONFIG, jSONObject3);
            jSONObject2.put(ContactsModuleConstants.CONTACTS_FIELD_TO_INCLUDE_KEY, ContactsModuleConstants.CONTACTS_FIELD_TO_INCLUDE_VALUE);
            jSONObject.put("payload", jSONObject2);
            jSONObject.put(ContactsModuleConstants.OPERATION_TYPE_KEY, ContactsModuleConstants.OPERATION_TYPE_VALUE);
        } catch (JSONException e) {
            String str2 = TAG;
            Log.e(str2, "Error: " + e);
        }
        return jSONObject.toString();
    }

    public void startContactEventBusResponseTimer() {
        new Timer().schedule(new TimerTask() { // from class: com.amazon.alexa.accessory.frames.contacts.ContactsModule.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (TopContactManager.getInstance().getTTSState() == TopContactManager.TTSState.CONTACT_VERIFICATION) {
                    Log.i(ContactsModule.TAG, "Event bus request time out");
                    MetricsRecorder.getInstance().recordCounter(MetricsConstants.TOP_CONTACT_EVENT_BUS_TIME_OUT);
                    if (!TopContactManager.topContactFlowInprogress.get()) {
                        return;
                    }
                    TopContactManager.getInstance().setTTSState(TopContactManager.TTSState.CONTACT_SETUP);
                    TopContactManager.getInstance().requestAudioFocus();
                }
            }
        }, 2000L);
    }

    public void verifyAndUpdateContactDetails() throws JSONException {
        Log.i(TAG, "in updateContactDetails");
        boolean z = false;
        JSONObject optJSONObject = contactResponse.optJSONArray(ContactsModuleConstants.OUTPUT).optJSONObject(0);
        if (optJSONObject == null) {
            Log.i(TAG, "Contact response contains empty contact");
            TopContactManager.latestContactDetails = null;
            StorageUtils.clear(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, TopContactManager.deviceAccountId).subscribe($$Lambda$ContactsModule$MzxYxl12gnP7EYcQ8pqhoPcqGSA.INSTANCE, $$Lambda$ContactsModule$p4jpgj34Iqf0DCuQsoXxLW0ubyQ.INSTANCE);
            return;
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray(ContactsModuleConstants.CONTACT_PHONE_NUMBERS);
        int i = 0;
        while (true) {
            if (i >= optJSONArray.length()) {
                break;
            }
            if (TopContactManager.latestContactDetails.optString("phoneNumber").equals(optJSONArray.optJSONObject(i).optString("number"))) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            Log.i(TAG, "Contact response is not same as top contact");
            TopContactManager.latestContactDetails = null;
            StorageUtils.clear(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, TopContactManager.deviceAccountId).subscribe($$Lambda$ContactsModule$IEVgak1ZiBYvEq4V7YgG8s1Vw9c.INSTANCE, $$Lambda$ContactsModule$a445JkYE4IfSsHbPN6PCGxBpvU.INSTANCE);
            return;
        }
        String optString = optJSONObject.optJSONObject("name").optString("firstName");
        if (TopContactManager.latestContactDetails.optString("name").equals(optString)) {
            return;
        }
        TopContactManager.contactNameHasChanged.set(true);
        TopContactManager.latestContactDetails.put("name", optString);
        StorageUtils.put(SettingsStorageModule.DEVICE_ACCOUNT_ID_KEY, TopContactManager.deviceAccountId, TopContactManager.latestContactDetails).subscribe($$Lambda$ContactsModule$OD4bJ_dP3tyhL1HUK16TFyFHpGU.INSTANCE, $$Lambda$ContactsModule$6RMIyXYUhYOttid6bfmFO6xXBk.INSTANCE);
    }
}
