package com.amazon.alexa.accessorykit.interprocess.mobilytics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.mobilytics.identity.MobilyticsDevice;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class AccessoryMobilyticsDevice implements JsonObjectSerializable, MobilyticsDevice {
    private static final String MOBILYTICS_DEVICE_SERIAL_NUMBER_KEY = "mobilyticsDeviceSerialNumber";
    private static final String MOBILYTICS_DEVICE_TYPE_KEY = "mobilyticsDeviceType";
    final String mobilyticsDeviceSerialNumber;
    final String mobilyticsDeviceType;
    public static final AccessoryMobilyticsDevice UNKNOWN = new AccessoryMobilyticsDevice();
    public static final JsonObjectSerializable.Factory<AccessoryMobilyticsDevice> FACTORY = new Factory();

    /* loaded from: classes6.dex */
    private static final class Factory implements JsonObjectSerializable.Factory<AccessoryMobilyticsDevice> {
        private Factory() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public AccessoryMobilyticsDevice mo1239create(JSONObject jSONObject) throws JSONException {
            return new AccessoryMobilyticsDevice(jSONObject.getString(AccessoryMobilyticsDevice.MOBILYTICS_DEVICE_TYPE_KEY), jSONObject.getString(AccessoryMobilyticsDevice.MOBILYTICS_DEVICE_SERIAL_NUMBER_KEY));
        }
    }

    public AccessoryMobilyticsDevice(@NonNull String str, @NonNull String str2) {
        Preconditions.precondition(!TextUtils.isEmpty(str), "mobilyticsDeviceType cannot be empty");
        Preconditions.precondition(!TextUtils.isEmpty(str2), "mobilyticsDeviceSerialNumber cannot be empty");
        this.mobilyticsDeviceType = str;
        this.mobilyticsDeviceSerialNumber = str2;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDevice
    public String deviceSerialNumber() {
        return this.mobilyticsDeviceSerialNumber;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDevice
    public String deviceType() {
        return this.mobilyticsDeviceType;
    }

    public boolean equals(Object obj) {
        return true;
    }

    public int hashCode() {
        return 0;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(MOBILYTICS_DEVICE_TYPE_KEY, this.mobilyticsDeviceType).put(MOBILYTICS_DEVICE_SERIAL_NUMBER_KEY, this.mobilyticsDeviceSerialNumber);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryMobilyticsDevice{mobilyticsDeviceType='");
        GeneratedOutlineSupport1.outline176(outline107, this.mobilyticsDeviceType, Chars.QUOTE, ", mobilyticsDeviceSerialNumber='");
        return GeneratedOutlineSupport1.outline90(outline107, this.mobilyticsDeviceSerialNumber, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    private AccessoryMobilyticsDevice() {
        this("Unknown", "Unknown");
    }
}
