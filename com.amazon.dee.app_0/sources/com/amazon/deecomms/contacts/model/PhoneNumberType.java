package com.amazon.deecomms.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
@JsonDeserialize(using = Deserializer.class)
@JsonSerialize(using = Serializer.class)
/* loaded from: classes12.dex */
public enum PhoneNumberType implements Parcelable {
    Home(1, R.string.phone_number_type_home, "Home"),
    Work(3, R.string.phone_number_type_work, NavigationMetrics.CardType.WORK),
    Mobile(2, R.string.phone_number_type_mobile, "Mobile"),
    HomeFax(5, R.string.phone_number_type_home_fax, "Home Fax"),
    Radio(14, R.string.phone_number_type_radio, "Radio"),
    WorkFax(4, R.string.phone_number_type_work_fax, "Work Fax"),
    Main(12, R.string.phone_number_type_main, "Main"),
    Pager(6, R.string.phone_number_type_pager, "Pager"),
    Assistant(19, R.string.phone_number_type_assistant, "Assistant"),
    CompanyMain(10, R.string.phone_number_type_company_main, "Company Main"),
    Car(9, R.string.phone_number_type_car, "Car"),
    Other(7, R.string.phone_number_type_other, "Other"),
    Custom(0, R.string.phone_number_type_custom, "Custom"),
    Alexa(0, R.string.phone_number_type_alexa, CallProvider.Alexa),
    iPhone(0, R.string.phone_number_type_iphone, "IPhone"),
    Cell(0, R.string.phone_number_type_cell, "Cell");
    
    public static final Parcelable.Creator<PhoneNumberType> CREATOR = new Parcelable.Creator<PhoneNumberType>() { // from class: com.amazon.deecomms.contacts.model.PhoneNumberType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public PhoneNumberType mo3662createFromParcel(Parcel parcel) {
            return PhoneNumberType.values()[parcel.readInt()];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public PhoneNumberType[] mo3663newArray(int i) {
            return new PhoneNumberType[i];
        }
    };
    private final String mAcmsType;
    private final int mAddressBookType;
    private final int mDisplayResId;

    /* loaded from: classes12.dex */
    static class Deserializer extends JsonDeserializer<PhoneNumberType> {
        Deserializer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /* renamed from: deserialize */
        public PhoneNumberType mo7111deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return PhoneNumberType.fromAcmsType(jsonParser.getValueAsString());
        }
    }

    /* loaded from: classes12.dex */
    static class Serializer extends JsonSerializer<PhoneNumberType> {
        Serializer() {
        }

        @Override // com.fasterxml.jackson.databind.JsonSerializer
        public void serialize(PhoneNumberType phoneNumberType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString(phoneNumberType.getAcmsType());
        }
    }

    PhoneNumberType(int i, int i2, @NonNull String str) {
        this.mDisplayResId = i2;
        this.mAddressBookType = i;
        this.mAcmsType = str;
    }

    @NonNull
    public static PhoneNumberType fromAcmsType(@Nullable String str) {
        PhoneNumberType[] values;
        if (!TextUtils.isEmpty(str)) {
            for (PhoneNumberType phoneNumberType : values()) {
                if (phoneNumberType.getAcmsType().equals(str)) {
                    return phoneNumberType;
                }
            }
        }
        return Other;
    }

    @NonNull
    public static PhoneNumberType fromAddressBookType(int i) {
        PhoneNumberType[] values;
        for (PhoneNumberType phoneNumberType : values()) {
            if (phoneNumberType.getAddressBookType() == i) {
                return phoneNumberType;
            }
        }
        return Other;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @NonNull
    public String getAcmsType() {
        return this.mAcmsType;
    }

    public int getAddressBookType() {
        return this.mAddressBookType;
    }

    public int getDisplayResId() {
        return this.mDisplayResId;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }

    @NonNull
    public static PhoneNumberType fromAddressBookType(String str) {
        PhoneNumberType[] values;
        for (PhoneNumberType phoneNumberType : values()) {
            if (phoneNumberType.getAcmsType() == str) {
                return phoneNumberType;
            }
        }
        return Other;
    }
}
