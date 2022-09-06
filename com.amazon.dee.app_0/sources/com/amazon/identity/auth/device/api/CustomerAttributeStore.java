package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.as;
import com.amazon.identity.auth.device.at;
import com.amazon.identity.auth.device.au;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ej;
import com.amazon.identity.auth.device.f;
import com.amazon.identity.auth.device.g;
import com.amazon.identity.auth.device.im;
import com.amazon.identity.auth.device.in;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.EnumSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class CustomerAttributeStore {
    @FireOsSdk
    public static final String AMAZON_ACCOUNT_PROPERTY_CHANGED_INTENT_ACTION = "com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed";
    @FireOsSdk
    public static final String COR_PFM_CHANGED_INTENT_ACTION = "com.amazon.dcp.sso.broadcast.CORPFMHasChanged";
    @FireOsSdk
    public static final int ERROR_CODE_ACCOUNT_NOT_REGISTERED = 3;
    @FireOsSdk
    public static final int ERROR_CODE_GENERIC_INTERNAL_ERROR = 1;
    @FireOsSdk
    public static final int ERROR_CODE_KEY_NOT_FOUND = 2;
    @FireOsSdk
    public static final int ERROR_CODE_UNABLE_TO_GET_ATTRIBUTE_ERROR = 4;
    @FireOsSdk
    public static final int ERROR_CODE_UNABLE_TO_SET_ATTRIBUTE_ERROR = 5;
    @FireOsSdk
    public static final String KEY_DEFAULT_VALUE = "defaut_value_key";
    @FireOsSdk
    public static final String KEY_DEVICE_EMAIL = "com.amazon.dcp.sso.property.deviceemail";
    @FireOsSdk
    public static final String KEY_DEVICE_NAME = "com.amazon.dcp.sso.property.devicename";
    @FireOsSdk
    public static final String KEY_DIRECTED_ID = "new.account.property.changed";
    @FireOsSdk
    public static final String KEY_ERROR_CODE = "error_code_key";
    @FireOsSdk
    public static final String KEY_ERROR_MESSAGE = "error_message_key";
    @FireOsSdk
    public static final String KEY_NEW_DEVICE_NAME = "new_device_name";
    @FireOsSdk
    public static final String KEY_PACKAGE_NAME_FOR_RENAMING_CHILD_DEVICE_3P_DEVICES = "key_package_name_for_renaming_child_device_3p_devices";
    @FireOsSdk
    public static final String KEY_VALUE = "value_key";
    private static final String TAG = "com.amazon.identity.auth.device.api.CustomerAttributeStore";
    private static final String fL = "CustomerAttributeStore";
    private static CustomerAttributeStore fV;
    private as fW;
    private f fX;
    private final ed o;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum GetAttributeOptions {
        ForceRefresh(HttpClientModule.ElementsRequestKey.FORCE_REFRESH);
        
        private final String mUniqueValue;

        GetAttributeOptions(String str) {
            this.mUniqueValue = str;
        }

        public static EnumSet<GetAttributeOptions> deserializeList(JSONArray jSONArray) {
            EnumSet<GetAttributeOptions> noneOf = EnumSet.noneOf(GetAttributeOptions.class);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    noneOf.add(getOptionFromValue(jSONArray.getString(i)));
                } catch (JSONException e) {
                    io.e(CustomerAttributeStore.TAG, "Could not deseralize part of getAttribute options", e);
                }
            }
            return noneOf;
        }

        public static GetAttributeOptions getOptionFromValue(String str) {
            GetAttributeOptions[] values;
            for (GetAttributeOptions getAttributeOptions : values()) {
                if (getAttributeOptions.getValue().equals(str)) {
                    return getAttributeOptions;
                }
            }
            return null;
        }

        public static JSONArray serializeList(EnumSet<GetAttributeOptions> enumSet) {
            JSONArray jSONArray = new JSONArray();
            Iterator it2 = enumSet.iterator();
            while (it2.hasNext()) {
                jSONArray.put(((GetAttributeOptions) it2.next()).getValue());
            }
            return jSONArray;
        }

        public String getValue() {
            return this.mUniqueValue;
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class RenameDeviceError {
        @FireOsSdk
        public static final int AUTHENTICATION_FAILED = 2;
        @FireOsSdk
        public static final int INVALID_INPUT = 3;
        @FireOsSdk
        public static final int NAME_ALREADY_USED = 5;
        @FireOsSdk
        public static final int NETWORK_FAILURE = 1;
        @FireOsSdk
        public static final int NO_AMAZON_ACCOUNT = 6;
        @FireOsSdk
        public static final int OVERRIDE_DEVICE_TYPE_NOT_DETERMINED = 8;
        @FireOsSdk
        public static final int PACKAGE_NAME_UNRECOGNIZED = 9;
        @FireOsSdk
        public static final int PACKAGE_RUNNING_ON_FIRST_PARTY_DEVICE = 11;
        @FireOsSdk
        public static final int PACKAGE_USING_CENTRAL_DEVICE_TYPE = 10;
        @FireOsSdk
        public static final int PARSE_ERROR = 4;
        @FireOsSdk
        public static final int UNRECOGNIZED = 7;

        private RenameDeviceError() {
        }
    }

    CustomerAttributeStore(Context context) {
        MAPInit.getInstance(context).initialize();
        this.o = ed.M(context);
        this.fX = null;
    }

    private synchronized as bg() {
        if (this.fW == null) {
            this.fW = at.i(this.o);
        }
        return this.fW;
    }

    @FireOsSdk
    public static void generateNewInstance(Context context) {
        fV = new CustomerAttributeStore(context.getApplicationContext());
    }

    @FireOsSdk
    public static synchronized CustomerAttributeStore getInstance(Context context) {
        CustomerAttributeStore customerAttributeStore;
        synchronized (CustomerAttributeStore.class) {
            in.a(context, "context");
            if (fV == null) {
                generateNewInstance(context);
            }
            customerAttributeStore = fV;
        }
        return customerAttributeStore;
    }

    @FireOsSdk
    public static String getValueOrAttributeDefault(Bundle bundle) {
        in.a(bundle, "attributeResult");
        String string = bundle.getString("value_key");
        return string != null ? string : bundle.getString(KEY_DEFAULT_VALUE);
    }

    private void j(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            if (str == null && !au.a(im.dl(str2))) {
                throw new IllegalArgumentException("Account cannot be null");
            }
            return;
        }
        throw new IllegalArgumentException("Key cannot be null or empty");
    }

    @FireOsSdk
    public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback) {
        return getAttribute(str, str2, callback, EnumSet.noneOf(GetAttributeOptions.class));
    }

    @FireOsSdk
    public Bundle peekAttribute(String str, String str2) {
        ej by = ej.by("CustomerAttributeStore:peekAttribute");
        j(str, str2);
        mv ea = by.ea();
        try {
            return bg().peekAttribute(str, str2);
        } finally {
            ea.stop();
            by.eb();
        }
    }

    @FireOsSdk
    public MAPFuture<Bundle> renameDevice(String str, String str2, Bundle bundle, Callback callback) {
        ej by = ej.by("RenameDevice");
        io.a(TAG, "renameDevice called by %s", this.o.getPackageName());
        mv ea = by.ea();
        if (this.fX == null) {
            this.fX = g.a(this.o);
        }
        return this.fX.b(str, str2, bundle, mq.a(by, ea, callback), by);
    }

    @FireOsSdk
    @Deprecated
    public MAPFuture<Bundle> setAttribute(String str, String str2, String str3, Callback callback) {
        io.gE();
        ej by = ej.by("CustomerAttributeStore:setAttribute");
        j(str, str2);
        return bg().setAttribute(str, str2, str3, mq.a(by.ea(), callback));
    }

    @FireOsSdk
    public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback, EnumSet<GetAttributeOptions> enumSet) {
        return getAttribute(str, str2, callback, new Bundle(), enumSet);
    }

    @FireOsSdk
    public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback, Bundle bundle, EnumSet<GetAttributeOptions> enumSet) {
        ej by = ej.by("CustomerAttributeStore:GetAttribute");
        j(str, str2);
        mv ea = by.ea();
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        if (enumSet == null) {
            enumSet = EnumSet.noneOf(GetAttributeOptions.class);
        }
        return bg().a(str, str2, mq.a(by, ea, callback), bundle2, enumSet, by);
    }
}
