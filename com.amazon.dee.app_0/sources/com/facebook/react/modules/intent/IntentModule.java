package com.facebook.react.modules.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.fbreact.specs.NativeIntentAndroidSpec;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = IntentModule.NAME)
/* loaded from: classes2.dex */
public class IntentModule extends NativeIntentAndroidSpec {
    public static final String NAME = "IntentAndroid";

    /* renamed from: com.facebook.react.modules.intent.IntentModule$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public IntentModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.fbreact.specs.NativeIntentAndroidSpec
    public void canOpenURL(String str, Promise promise) {
        if (str != null && !str.isEmpty()) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent.addFlags(268435456);
                promise.resolve(Boolean.valueOf(intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null));
                return;
            } catch (Exception e) {
                promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline115("Could not check if URL '", str, "' can be opened: "))));
                return;
            }
        }
        promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid URL: ", str)));
    }

    @Override // com.facebook.fbreact.specs.NativeIntentAndroidSpec
    public void getInitialURL(Promise promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            String str = null;
            if (currentActivity != null) {
                Intent intent = currentActivity.getIntent();
                String action = intent.getAction();
                Uri data = intent.getData();
                if (data != null && ("android.intent.action.VIEW".equals(action) || "android.nfc.action.NDEF_DISCOVERED".equals(action))) {
                    str = data.toString();
                }
            }
            promise.resolve(str);
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Could not get the initial URL : "))));
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeIntentAndroidSpec
    public void openSettings(Promise promise) {
        try {
            Intent intent = new Intent();
            Activity currentActivity = getCurrentActivity();
            String packageName = getReactApplicationContext().getPackageName();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + packageName));
            intent.addFlags(268435456);
            intent.addFlags(1073741824);
            intent.addFlags(8388608);
            currentActivity.startActivity(intent);
            promise.resolve(true);
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline107("Could not open the Settings: "))));
        }
    }

    @Override // com.facebook.fbreact.specs.NativeIntentAndroidSpec
    public void openURL(String str, Promise promise) {
        if (str != null && !str.isEmpty()) {
            try {
                Activity currentActivity = getCurrentActivity();
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str).normalizeScheme());
                String packageName = getReactApplicationContext().getPackageName();
                ComponentName resolveActivity = intent.resolveActivity(getReactApplicationContext().getPackageManager());
                String packageName2 = resolveActivity != null ? resolveActivity.getPackageName() : "";
                if (currentActivity == null || !packageName.equals(packageName2)) {
                    intent.addFlags(268435456);
                }
                if (currentActivity != null) {
                    currentActivity.startActivity(intent);
                } else {
                    getReactApplicationContext().startActivity(intent);
                }
                promise.resolve(true);
                return;
            } catch (Exception e) {
                promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline115("Could not open URL '", str, "': "))));
                return;
            }
        }
        promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid URL: ", str)));
    }

    @Override // com.facebook.fbreact.specs.NativeIntentAndroidSpec
    public void sendIntent(String str, @Nullable ReadableArray readableArray, Promise promise) {
        if (str != null && !str.isEmpty()) {
            Intent intent = new Intent(str);
            if (intent.resolveActivity(getReactApplicationContext().getPackageManager()) == null) {
                promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline75("Could not launch Intent with action ", str, ".")));
                return;
            }
            if (readableArray != null) {
                for (int i = 0; i < readableArray.size(); i++) {
                    ReadableMap mo6944getMap = readableArray.mo6944getMap(i);
                    String nextKey = mo6944getMap.keySetIterator().nextKey();
                    int ordinal = mo6944getMap.getType(nextKey).ordinal();
                    if (ordinal == 1) {
                        intent.putExtra(nextKey, mo6944getMap.getBoolean(nextKey));
                    } else if (ordinal == 2) {
                        intent.putExtra(nextKey, Double.valueOf(mo6944getMap.getDouble(nextKey)));
                    } else if (ordinal != 3) {
                        promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline75("Extra type for ", nextKey, " not supported.")));
                        return;
                    } else {
                        intent.putExtra(nextKey, mo6944getMap.getString(nextKey));
                    }
                }
            }
            getReactApplicationContext().startActivity(intent);
            return;
        }
        promise.reject(new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid Action: ", str, ".")));
    }
}
