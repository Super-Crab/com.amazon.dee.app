package com.amazon.identity.auth.device.attribute;

import android.content.Context;
import com.amazon.identity.auth.device.hx;
import com.amazon.identity.auth.device.ie;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public enum DeviceAttribute {
    CentralDeviceType { // from class: com.amazon.identity.auth.device.attribute.DeviceAttribute.1
        @Override // com.amazon.identity.auth.device.attribute.DeviceAttribute
        public Object fetchValue(Context context) {
            return ie.au(context);
        }
    },
    CentralAPK { // from class: com.amazon.identity.auth.device.attribute.DeviceAttribute.2
        @Override // com.amazon.identity.auth.device.attribute.DeviceAttribute
        public Object fetchValue(Context context) {
            return Boolean.valueOf(hx.ak(context));
        }
    };

    public abstract Object fetchValue(Context context);
}
