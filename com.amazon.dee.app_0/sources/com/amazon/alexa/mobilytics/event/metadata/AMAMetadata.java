package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
import com.amazon.alexa.mobilytics.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes9.dex */
public class AMAMetadata implements DefaultEventMetadata {
    private static final String KEY_PATTERN = "^VALUE_[0-9]$";
    private static final String TAG = Log.tag(AMAMetadata.class);
    private static final long VALUE_LIMIT = 10;
    private Long accTimestamp;
    private String accessoryLibraryVersion;
    private String bootNumber;
    private String deviceSerialNumber;
    private String deviceType;
    private String dialogID;
    private String dialogTurnID;
    private String firmAcc1;
    private String firmAcc2;
    private String firmAcc3;
    private String firmwareLocale;
    private String firmwareName;
    private String firmwareVersionName;
    private String intentName;
    private String localExecReason;
    private String sequenceNumber;
    private String transportType;
    private AMAValues values;
    private String voiceReqID;
    private final String metadataType = EventMetadataType.AMA;
    private transient HashMap<String, String> valueMap = new HashMap<>();

    public AMAMetadata clearValues() {
        this.values = null;
        return this;
    }

    public String getAccessoryLibraryVersion() {
        return this.accessoryLibraryVersion;
    }

    @DCMAttributeName(AccessoryMetricsConstants.TIMESTAMP)
    public String getAccessoryTimestamp() {
        return String.valueOf(this.accTimestamp);
    }

    @DCMAttributeName("boot_number")
    public String getBootNumber() {
        return this.bootNumber;
    }

    @DCMAttributeName("deviceId_accessory")
    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    @DCMAttributeName("deviceType_accessory")
    public String getDeviceType() {
        return this.deviceType;
    }

    @DCMAttributeName("dialogId")
    public String getDialogId() {
        return this.dialogID;
    }

    public String getDialogTurnId() {
        return this.dialogTurnID;
    }

    public String getFirmwareLocale() {
        return this.firmwareLocale;
    }

    public String getFirmwareName() {
        return this.firmwareName;
    }

    @DCMAttributeName("firmware_accessory")
    public String getFirmwareVersionName() {
        return this.firmwareVersionName;
    }

    public String getIntentName() {
        return this.intentName;
    }

    public String getLocalExecuteReason() {
        return this.localExecReason;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.EventMetadata
    public String getMetadataType() {
        return EventMetadataType.AMA;
    }

    @DCMAttributeName("sequence_number")
    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getTransportType() {
        return this.transportType;
    }

    @DCMAttributeMap("values")
    public Map<String, String> getValues() {
        return this.valueMap;
    }

    public String getVoiceRequestId() {
        return this.voiceReqID;
    }

    public AMAMetadata removeValue(String str) {
        AMAValues aMAValues = this.values;
        if (aMAValues == null) {
            return this;
        }
        aMAValues.remove(str);
        if (this.values.isEmpty()) {
            this.values = null;
        }
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.metadata.DefaultEventMetadata
    @NonNull
    public EventDetailsProto.Metadata serialize() {
        EventDetailsProto.Metadata.Builder newBuilder = EventDetailsProto.Metadata.newBuilder();
        EventDetailsProto.Metadata.Ama.Builder newBuilder2 = EventDetailsProto.Metadata.Ama.newBuilder();
        String str = this.accessoryLibraryVersion;
        if (str != null) {
            newBuilder2.setAccessoryLibraryVersion(str);
        }
        String str2 = this.deviceType;
        if (str2 != null) {
            newBuilder2.setDeviceType(str2);
        }
        String str3 = this.deviceSerialNumber;
        if (str3 != null) {
            newBuilder2.setDeviceSerialNumber(str3);
        }
        String str4 = this.firmwareVersionName;
        if (str4 != null) {
            newBuilder2.setFirmwareVersionName(str4);
        }
        String str5 = this.firmwareName;
        if (str5 != null) {
            newBuilder2.setFirmwareName(str5);
        }
        String str6 = this.firmwareLocale;
        if (str6 != null) {
            newBuilder2.setFirmwareLocale(str6);
        }
        String str7 = this.transportType;
        if (str7 != null) {
            newBuilder2.setTransportType(str7);
        }
        String str8 = this.firmAcc1;
        if (str8 != null) {
            newBuilder2.setFirmAcc1(str8);
        }
        String str9 = this.firmAcc2;
        if (str9 != null) {
            newBuilder2.setFirmAcc2(str9);
        }
        String str10 = this.firmAcc3;
        if (str10 != null) {
            newBuilder2.setFirmAcc3(str10);
        }
        Long l = this.accTimestamp;
        if (l != null) {
            newBuilder2.setAccTimestamp(l.longValue());
        }
        String str11 = this.dialogID;
        if (str11 != null) {
            newBuilder2.setDialogID(str11);
        }
        String str12 = this.dialogTurnID;
        if (str12 != null) {
            newBuilder2.setDialogTurnID(str12);
        }
        String str13 = this.intentName;
        if (str13 != null) {
            newBuilder2.setIntentName(str13);
        }
        String str14 = this.localExecReason;
        if (str14 != null) {
            newBuilder2.setLocalExecReason(str14);
        }
        String str15 = this.voiceReqID;
        if (str15 != null) {
            newBuilder2.setVoiceReqID(str15);
        }
        AMAValues aMAValues = this.values;
        if (aMAValues != null) {
            newBuilder2.setValues(aMAValues.toString());
        }
        String str16 = this.bootNumber;
        if (str16 != null) {
            newBuilder2.setBootNumber(str16);
        }
        String str17 = this.sequenceNumber;
        if (str17 != null) {
            newBuilder2.setSequenceNumber(str17);
        }
        newBuilder.setAma(newBuilder2);
        return newBuilder.mo10084build();
    }

    public void setBootNumber(String str) {
        this.bootNumber = str;
    }

    public void setDialogId(@Nullable String str) {
        this.dialogID = str;
    }

    public void setDialogTurnId(@Nullable String str) {
        this.dialogTurnID = str;
    }

    public void setIntentName(@Nullable String str) {
        this.intentName = str;
    }

    public void setLocalExecuteReason(@Nullable String str) {
        this.localExecReason = str;
    }

    public void setSequenceNumber(String str) {
        this.sequenceNumber = str;
    }

    public AMAMetadata setValue(String str, Number number) {
        if (this.values == null) {
            this.values = new AMAValues();
        }
        if (this.valueMap.size() < 10 && Pattern.matches(KEY_PATTERN, str)) {
            if (Pattern.matches(KEY_PATTERN, str)) {
                this.values.set(str, number);
                this.valueMap.put(str, String.valueOf(number));
            } else {
                Log.w(TAG, "key parameter does not conform to pattern %s.Accessory key [%s] and value [%f] will be dropped.", KEY_PATTERN, str, Float.valueOf(number.floatValue()));
            }
        } else {
            Log.w(TAG, "Count of values greater than %d.Accessory key [%s] and value [%f] will be dropped.", 10L, str, Float.valueOf(number.floatValue()));
        }
        return this;
    }

    public void setVoiceRequestId(@Nullable String str) {
        this.voiceReqID = str;
    }

    public AMAMetadata withAccessoryLibraryVersion(String str) {
        this.accessoryLibraryVersion = str;
        return this;
    }

    public AMAMetadata withBootNumber(String str) {
        this.bootNumber = str;
        return this;
    }

    public AMAMetadata withDeviceSerialNumber(String str) {
        this.deviceSerialNumber = str;
        return this;
    }

    public AMAMetadata withDeviceType(String str) {
        this.deviceType = str;
        return this;
    }

    public AMAMetadata withFirmAcc1(String str) {
        this.firmAcc1 = str;
        return this;
    }

    public AMAMetadata withFirmAcc2(String str) {
        this.firmAcc2 = str;
        return this;
    }

    public AMAMetadata withFirmAcc3(String str) {
        this.firmAcc3 = str;
        return this;
    }

    public AMAMetadata withFirmwareLocale(String str) {
        this.firmwareLocale = str;
        return this;
    }

    public AMAMetadata withFirmwareName(String str) {
        this.firmwareName = str;
        return this;
    }

    public AMAMetadata withFirmwareVersionName(String str) {
        this.firmwareVersionName = str;
        return this;
    }

    public AMAMetadata withSequenceNumber(String str) {
        this.sequenceNumber = str;
        return this;
    }

    public AMAMetadata withTimestampAccessories(long j) {
        this.accTimestamp = Long.valueOf(j);
        return this;
    }

    public AMAMetadata withTransportType(String str) {
        this.transportType = str;
        return this;
    }
}
