package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.MultiDeviceUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Device;
/* loaded from: classes.dex */
final class AccessorySessionAttributes implements AccessoryAttributes {
    private static final String TAG = "AccessorySessionAttributes:";
    private final AccessorySession accessorySession;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessorySessionAttributes(AccessorySession accessorySession) {
        Preconditions.notNull(accessorySession, "anAccessorySession");
        this.accessorySession = accessorySession;
    }

    @NonNull
    private Device.DeviceInformation selectFirstDeviceInformation() {
        return MultiDeviceUtils.getDeviceInformationWithHighestDeviceId(this.accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().blockingFirst());
    }

    @Override // com.amazon.alexa.accessory.engagement.AccessoryAttributes
    @Nullable
    public String getAccessoryDeviceLanguage() {
        try {
            return (String) this.accessorySession.getSystemRepository().queryLocales().map($$Lambda$AccessorySessionAttributes$RNgRAjewYMptD5Ge65_6bw7PsS0.INSTANCE).blockingFirst();
        } catch (Exception e) {
            Logger.e("%s Failed to obtain accessory device language.", e, TAG);
            return null;
        }
    }

    @Override // com.amazon.alexa.accessory.engagement.AccessoryAttributes
    @NonNull
    public String getAccessoryDeviceSerialNumber() {
        return selectFirstDeviceInformation().getSerialNumber();
    }

    @Override // com.amazon.alexa.accessory.engagement.AccessoryAttributes
    @NonNull
    public String getAccessoryDeviceType() {
        return selectFirstDeviceInformation().getDeviceType();
    }

    @Override // com.amazon.alexa.accessory.engagement.AccessoryAttributes
    @NonNull
    public String getAccessorySoftwareVersion() {
        return (String) this.accessorySession.getFirmwareRepositoryV2().queryInformationSet().map($$Lambda$kTr_FAzIzBku4_pbIPWuzYj2wAQ.INSTANCE).map($$Lambda$AccessorySessionAttributes$OhGW85oXMpz_mo0owRtPgKOfFnM.INSTANCE).blockingGet();
    }
}
