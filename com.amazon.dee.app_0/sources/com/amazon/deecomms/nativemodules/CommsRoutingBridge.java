package com.amazon.deecomms.nativemodules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes12.dex */
public class CommsRoutingBridge extends ReactContextBaseJavaModule {
    private static final Bundle EMPTY_BUNDLE = new Bundle();
    private final ContactsDataStoreUtil mContactsDataStoreUtil;
    private final Handler mMainThreadHandler;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommsRoutingBridge(@NonNull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        ContactsDataStoreUtil contactsDataStoreUtil = new ContactsDataStoreUtil(reactApplicationContext.getApplicationContext());
        Handler handler = new Handler(Looper.getMainLooper());
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mMainThreadHandler = handler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToAppSettings$3(Context context, Promise promise) {
        Utils.navigateToAppSettingsPage(context);
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToConversation$0(Activity activity, String str, Promise promise) {
        FragmentNavigationRouter.goToConversationThread(activity, str, false);
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToDiagnostics$2(Promise promise) {
        FragmentNavigationRouter.switchToFragment(CommsView.Diagnostics, EMPTY_BUNDLE, true);
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToNativeContactList$4(Bundle bundle, Promise promise) {
        FragmentNavigationRouter.switchToFragment(CommsView.ContactList, bundle, true);
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToNativeCreateNewConveration$6(Bundle bundle, Promise promise) {
        FragmentNavigationRouter.switchToFragment(CommsView.ContactList, bundle);
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToNativeStartCall$5(Bundle bundle, Promise promise) {
        FragmentNavigationRouter.switchToFragment(CommsView.ContactList, bundle);
        promise.resolve(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$tryRouteToWhitelistContact$1(String str, String str2, Promise promise) {
        FragmentNavigationRouter.goToWhitelistContacts(str, str2);
        promise.resolve(null);
    }

    private boolean tryRouteToAppSettings(String str, final Promise promise) {
        if (!"AMIRouteApplicationSettings".equals(str)) {
            return false;
        }
        final Context applicationContext = getReactApplicationContext().getApplicationContext();
        this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$Rm-xTFOGsMRKjN_vjLxzZsRXYY0
            @Override // java.lang.Runnable
            public final void run() {
                CommsRoutingBridge.lambda$tryRouteToAppSettings$3(applicationContext, promise);
            }
        });
        return true;
    }

    private boolean tryRouteToConversation(String str, ReadableMap readableMap, final Promise promise) {
        if (!"AMIRouteConversation".equals(str)) {
            return false;
        }
        String string = readableMap.getString("intentType");
        if (!"openConversationWithContactId".equals(string) && !"createNewConversation".equals(string)) {
            return false;
        }
        final Activity currentActivity = getCurrentActivity();
        final String commsIdFromServerContactId = this.mContactsDataStoreUtil.getCommsIdFromServerContactId(readableMap.getString("Id"));
        this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$qgXHNl46ynhK6-9PGBhRmEliUn0
            @Override // java.lang.Runnable
            public final void run() {
                CommsRoutingBridge.lambda$tryRouteToConversation$0(currentActivity, commsIdFromServerContactId, promise);
            }
        });
        return true;
    }

    private boolean tryRouteToDiagnostics(String str, final Promise promise) {
        if (!"AMIRouteDiagnostics".equals(str)) {
            return false;
        }
        this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$-AgxTP5YU4hlmKcIBDqNkzINcr4
            @Override // java.lang.Runnable
            public final void run() {
                CommsRoutingBridge.lambda$tryRouteToDiagnostics$2(Promise.this);
            }
        });
        return true;
    }

    private boolean tryRouteToNativeContactList(String str, ReadableMap readableMap, final Promise promise) {
        boolean z = false;
        if ("AMIContactList".equals(str) && "openContactList".equals(readableMap.getString("intentType"))) {
            final Bundle bundle = new Bundle();
            if (!readableMap.isNull("afterDeleteContact") && Boolean.valueOf(readableMap.getString("afterDeleteContact")).booleanValue()) {
                z = true;
            }
            bundle.putBoolean(Constants.ROUTE_AFTER_DELETE_CONTACT, z);
            this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$fPytt4kOP8rJdHvUxoh_cNcMens
                @Override // java.lang.Runnable
                public final void run() {
                    CommsRoutingBridge.lambda$tryRouteToNativeContactList$4(bundle, promise);
                }
            });
            return true;
        }
        return false;
    }

    private boolean tryRouteToNativeCreateNewConveration(String str, ReadableMap readableMap, final Promise promise) {
        if ("AMIContactList".equals(str) && "createNewConversation".equals(readableMap.getString("intentType"))) {
            final Bundle outline11 = GeneratedOutlineSupport1.outline11(Constants.CONTACT_LIST_LAUNCH_FRAGMENT, Constants.FRAGMENT_START_NEW_CONVERSATION);
            this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$E9aqrtWhjqx6kSwFMN-USaGfS-w
                @Override // java.lang.Runnable
                public final void run() {
                    CommsRoutingBridge.lambda$tryRouteToNativeCreateNewConveration$6(outline11, promise);
                }
            });
            return true;
        }
        return false;
    }

    private boolean tryRouteToNativeStartCall(String str, ReadableMap readableMap, final Promise promise) {
        if ("AMIContactList".equals(str) && "callContacts".equals(readableMap.getString("intentType"))) {
            final Bundle outline11 = GeneratedOutlineSupport1.outline11(Constants.CONTACT_LIST_LAUNCH_FRAGMENT, Constants.FRAGMENT_CALL_BOTTOM_SHEET);
            this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$kFsk62meKsixIX2iDIfhZA6wxQs
                @Override // java.lang.Runnable
                public final void run() {
                    CommsRoutingBridge.lambda$tryRouteToNativeStartCall$5(outline11, promise);
                }
            });
            return true;
        }
        return false;
    }

    private boolean tryRouteToOOBE(String str, final ReadableMap readableMap, final Promise promise) {
        if (!"AMIRouteOOBE".equals(str)) {
            return false;
        }
        this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$J9RvV0YJ7JEnDLa9sAGitzNSlmU
            @Override // java.lang.Runnable
            public final void run() {
                CommsRoutingBridge.this.lambda$tryRouteToOOBE$7$CommsRoutingBridge(readableMap, promise);
            }
        });
        return true;
    }

    private boolean tryRouteToWhitelistContact(String str, ReadableMap readableMap, final Promise promise) {
        if ("AMIWhitelistContacts".equals(str) && "whitelistContacts".equals(readableMap.getString("intentType"))) {
            final String string = readableMap.getString("Id");
            final String commsIdFromServerContactId = this.mContactsDataStoreUtil.getCommsIdFromServerContactId(string);
            this.mMainThreadHandler.post(new Runnable() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$CommsRoutingBridge$PM4RGJL07FyFlM-aPytKDTFVe4A
                @Override // java.lang.Runnable
                public final void run() {
                    CommsRoutingBridge.lambda$tryRouteToWhitelistContact$1(commsIdFromServerContactId, string, promise);
                }
            });
            return true;
        }
        return false;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsRouting";
    }

    public /* synthetic */ void lambda$tryRouteToOOBE$7$CommsRoutingBridge(ReadableMap readableMap, Promise promise) {
        Activity currentActivity = getCurrentActivity();
        Intent intent = new Intent(currentActivity, OOBEActivity.class);
        if ("accessoryOOBE".equals(readableMap.getString("intentType"))) {
            String string = readableMap.hasKey(Constants.BUNDLE_KEY_REACT_NATIVE_ROUTE_NAME) ? readableMap.getString(Constants.BUNDLE_KEY_REACT_NATIVE_ROUTE_NAME) : null;
            Bundle outline11 = GeneratedOutlineSupport1.outline11(Constants.OOBE_STARTED_FROM, Constants.ACCESSORY_OOBE);
            if (string != null && string.length() > 0) {
                outline11.putString(Constants.ROUTE_NAME, string);
                outline11.putString("deviceType", readableMap.hasKey("deviceType") ? readableMap.getString("deviceType") : null);
                outline11.putString("deviceSerialNumber", readableMap.hasKey("deviceSerialNumber") ? readableMap.getString("deviceSerialNumber") : null);
                outline11.putString("isNewDevice", readableMap.hasKey("isNewDevice") ? readableMap.getString("isNewDevice") : null);
            }
            intent.putExtras(outline11);
        }
        currentActivity.startActivityForResult(intent, 120);
        promise.resolve(null);
    }

    @ReactMethod
    public void route(String str, ReadableMap readableMap, Promise promise) {
        if (tryRouteToConversation(str, readableMap, promise) || tryRouteToDiagnostics(str, promise) || tryRouteToAppSettings(str, promise) || tryRouteToWhitelistContact(str, readableMap, promise) || tryRouteToNativeContactList(str, readableMap, promise) || tryRouteToNativeStartCall(str, readableMap, promise) || tryRouteToNativeCreateNewConveration(str, readableMap, promise) || tryRouteToOOBE(str, readableMap, promise)) {
            return;
        }
        promise.reject((String) null, "Invalid route.");
    }

    public CommsRoutingBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull Handler handler) {
        super(reactApplicationContext);
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mMainThreadHandler = handler;
    }
}
