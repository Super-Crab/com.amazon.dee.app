package com.amazon.dee.app.services.alexadevicebackground;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public class BackgroundImage implements Parcelable {
    public static final String BACKGROUND_IMAGE_MODEL = "background_image_model";
    public static final String DEFAULT_THEME = "DEFAULT_THEME";
    public static final String DELETE_PERSONAL_PHOTO = "DELETE_PERSONAL_PHOTOS";
    public static final String DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String DOWNLOADING_PERSONAL_PHOTOS = "DOWNLOADING_PERSONAL_PHOTOS";
    public static final String PERSONAL_BG_IMAGE = "PERSONAL_PHOTOS";
    public static final String PRIME_PHOTOS = "PRIME_PHOTOS";
    public static final String SOFTWARE_VERSION = "softwareVersion";
    private String backgroundImageID;
    private String backgroundImageType;
    private String backgroundImageURL;
    private String deviceSerialNumber;
    private String deviceType;
    private String softwareVersion;
    public static final String BACKGROUND_IMAGE_ID = "backgroundImageID";
    public static final String BACKGROUND_IMAGE_TYPE = "backgroundImageType";
    public static final String BACKGROUND_IMAGE_URL = "backgroundImageURL";
    private static final List<String> requiredKeys = Arrays.asList("deviceType", "deviceSerialNumber", "softwareVersion", BACKGROUND_IMAGE_ID, BACKGROUND_IMAGE_TYPE, BACKGROUND_IMAGE_URL);
    public static final Parcelable.Creator<BackgroundImage> CREATOR = new Parcelable.Creator<BackgroundImage>() { // from class: com.amazon.dee.app.services.alexadevicebackground.BackgroundImage.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public BackgroundImage mo3594createFromParcel(Parcel parcel) {
            return new BackgroundImage(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public BackgroundImage[] mo3595newArray(int i) {
            return new BackgroundImage[i];
        }
    };

    /* loaded from: classes12.dex */
    public @interface BackgroundImageType {
    }

    private String getString(Map map, String str) throws InvalidValueException {
        Object obj = map.get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        throw new InvalidValueException("Value '" + obj + "' must be a string or null.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BackgroundImage.class != obj.getClass()) {
            return false;
        }
        BackgroundImage backgroundImage = (BackgroundImage) obj;
        String str = this.deviceSerialNumber;
        if (str == null ? backgroundImage.deviceSerialNumber != null : !str.equals(backgroundImage.deviceSerialNumber)) {
            return false;
        }
        String str2 = this.deviceType;
        if (str2 == null ? backgroundImage.deviceType != null : !str2.equals(backgroundImage.deviceType)) {
            return false;
        }
        String str3 = this.softwareVersion;
        if (str3 == null ? backgroundImage.softwareVersion != null : !str3.equals(backgroundImage.softwareVersion)) {
            return false;
        }
        String str4 = this.backgroundImageType;
        if (str4 == null ? backgroundImage.backgroundImageType != null : !str4.equals(backgroundImage.backgroundImageType)) {
            return false;
        }
        return TextUtils.isEmpty(this.backgroundImageID) ? TextUtils.isEmpty(backgroundImage.backgroundImageID) : this.backgroundImageID.equals(backgroundImage.backgroundImageID);
    }

    public String getBackgroundImageID() {
        return this.backgroundImageID;
    }

    public String getBackgroundImageType() {
        return this.backgroundImageType;
    }

    public String getBackgroundImageURL() {
        return this.backgroundImageURL;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public int hashCode() {
        String str = this.deviceSerialNumber;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.deviceType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.softwareVersion;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.backgroundImageType;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.backgroundImageID;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.backgroundImageURL;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode5 + i;
    }

    public boolean isDelete() {
        return this.backgroundImageType.equals(DELETE_PERSONAL_PHOTO);
    }

    public boolean isDownloading() {
        return this.backgroundImageType.equals(DOWNLOADING_PERSONAL_PHOTOS);
    }

    public void setBackgroundImageID(String str) {
        this.backgroundImageID = str;
    }

    public void setBackgroundImageType(@BackgroundImageType String str) {
        this.backgroundImageType = str;
    }

    public void setBackgroundImageURL(String str) {
        this.backgroundImageURL = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BackgroundImage{deviceSerialNumber = '");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceSerialNumber, Chars.QUOTE, ", deviceType =' ");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceType, Chars.QUOTE, ", softwareVersion =' ");
        GeneratedOutlineSupport1.outline176(outline107, this.softwareVersion, Chars.QUOTE, ", backgroundImageID = '");
        GeneratedOutlineSupport1.outline176(outline107, this.backgroundImageID, Chars.QUOTE, ", backgroundImageType = ");
        GeneratedOutlineSupport1.outline176(outline107, this.backgroundImageType, Chars.QUOTE, ", backgroundImageURL = ");
        return GeneratedOutlineSupport1.outline89(outline107, this.backgroundImageURL, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceSerialNumber);
        parcel.writeString(this.deviceType);
        parcel.writeString(this.softwareVersion);
        parcel.writeString(this.backgroundImageID);
        parcel.writeString(this.backgroundImageType);
        parcel.writeString(this.backgroundImageURL);
    }

    public BackgroundImage() {
    }

    private BackgroundImage(Parcel parcel) {
        this.deviceSerialNumber = parcel.readString();
        this.deviceType = parcel.readString();
        this.softwareVersion = parcel.readString();
        this.backgroundImageID = parcel.readString();
        this.backgroundImageType = parcel.readString();
        this.backgroundImageURL = parcel.readString();
    }

    public BackgroundImage(Map<String, Object> map) throws MissingKeyException, InvalidValueException {
        if (map.keySet().containsAll(requiredKeys)) {
            this.deviceSerialNumber = getString(map, "deviceSerialNumber");
            this.deviceType = getString(map, "deviceType");
            this.softwareVersion = getString(map, "softwareVersion");
            this.backgroundImageID = getString(map, BACKGROUND_IMAGE_ID);
            this.backgroundImageType = getString(map, BACKGROUND_IMAGE_TYPE);
            this.backgroundImageURL = getString(map, BACKGROUND_IMAGE_URL);
            return;
        }
        ArrayList arrayList = new ArrayList(requiredKeys);
        arrayList.removeAll(map.keySet());
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid Background Image Data: Missing keys: ");
        outline107.append(arrayList.toString());
        throw new MissingKeyException(outline107.toString());
    }
}
