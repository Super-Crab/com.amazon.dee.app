package com.amazon.deecomms.nativemodules;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.util.DeviceContactsFetcher;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.List;
/* loaded from: classes12.dex */
public class CommsLocalAddressBookStoreBridge extends ReactContextBaseJavaModule {
    private static final String AUTHORIZATION_STATUS_DENIED = "denied";
    private static final String AUTHORIZATION_STATUS_GRANTED = "authorized";
    private static final String AUTHORIZATION_STATUS_NOT_DETERMINED = "notDetermined";
    private final DeviceContactsFetcher mDeviceContactsFetcher;
    private final ReactBridgeSerializer mReactBridgeSerializer;

    public CommsLocalAddressBookStoreBridge(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new DeviceContactsFetcher(reactApplicationContext.getApplicationContext()));
    }

    private ReadableArray getDeviceContacts() {
        List<ContactUploadInfo> deviceContacts = this.mDeviceContactsFetcher.getDeviceContacts();
        WritableArray createArray = Arguments.createArray();
        for (ContactUploadInfo contactUploadInfo : deviceContacts) {
            WritableMap addressBookContact = this.mReactBridgeSerializer.getAddressBookContact(contactUploadInfo);
            if (addressBookContact != null) {
                createArray.pushMap(addressBookContact);
            }
        }
        return createArray;
    }

    @ReactMethod
    public void getAuthorizationStatus(Promise promise) {
        String str;
        int checkSelfPermission = ContextCompat.checkSelfPermission(getReactApplicationContext(), "android.permission.READ_CONTACTS");
        if (checkSelfPermission != -1) {
            if (checkSelfPermission == 0) {
                str = AUTHORIZATION_STATUS_GRANTED;
            }
            str = AUTHORIZATION_STATUS_NOT_DETERMINED;
        } else {
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null && ActivityCompat.shouldShowRequestPermissionRationale(currentActivity, "android.permission.READ_CONTACTS")) {
                str = "denied";
            }
            str = AUTHORIZATION_STATUS_NOT_DETERMINED;
        }
        promise.resolve(str);
    }

    @ReactMethod
    public void getContacts(Promise promise) {
        ReadableArray deviceContacts = getDeviceContacts();
        if (deviceContacts != null) {
            promise.resolve(deviceContacts);
        } else {
            promise.reject((String) null, "Getting contacts from address book failed.");
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsLocalAddressBookStore";
    }

    public CommsLocalAddressBookStoreBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull DeviceContactsFetcher deviceContactsFetcher) {
        super(reactApplicationContext);
        this.mDeviceContactsFetcher = deviceContactsFetcher;
        this.mReactBridgeSerializer = new ReactBridgeSerializer(reactApplicationContext.getApplicationContext());
    }
}
