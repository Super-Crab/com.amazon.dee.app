package com.dee.app.data.reactnative;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.redesign.utils.Constants;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class ElementsDataRequest {
    @Nullable
    public DataBlob body;
    @Nullable
    public String domain;
    @Nullable
    public String id;
    @Nullable
    public Method method;
    @Nullable
    public String operationName;
    @Nullable
    public String uri;
    @Nullable
    public Integer connectionTimeout = null;
    @Nullable
    public Integer readTimeout = null;
    public boolean singleResult = false;
    public Map<String, String> headers = new HashMap();
    public ElementsCacheOptions cache = new ElementsCacheOptions();
    private ElementsDataRequestAuthentication authentication = new ElementsDataRequestAuthentication();

    /* loaded from: classes2.dex */
    public enum Method {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH;

        @Nullable
        @SuppressLint({"DefaultLocale"})
        public static Method fromString(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String upperCase = str.toUpperCase();
            char c = 65535;
            switch (upperCase.hashCode()) {
                case 70454:
                    if (upperCase.equals("GET")) {
                        c = 0;
                        break;
                    }
                    break;
                case 79599:
                    if (upperCase.equals(SmartDeviceDataProvider.METHOD_HTTP_PUT)) {
                        c = 2;
                        break;
                    }
                    break;
                case 2461856:
                    if (upperCase.equals("POST")) {
                        c = 1;
                        break;
                    }
                    break;
                case 75900968:
                    if (upperCase.equals("PATCH")) {
                        c = 4;
                        break;
                    }
                    break;
                case 2012838315:
                    if (upperCase.equals(Constants.REQUEST_METHOD_DELETE)) {
                        c = 3;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                return GET;
            }
            if (c == 1) {
                return POST;
            }
            if (c == 2) {
                return PUT;
            }
            if (c == 3) {
                return DELETE;
            }
            if (c == 4) {
                return PATCH;
            }
            return null;
        }
    }

    public ElementsDataRequestAuthentication getAuth() {
        return this.authentication;
    }
}
