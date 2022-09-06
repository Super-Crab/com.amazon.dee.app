package com.amazon.deecomms.calling.model;

import amazon.speech.simclient.settings.Settings;
import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class CallingMetricsModel extends AbstractCallModel implements Parcelable {
    public static final Parcelable.Creator<CallingMetricsModel> CREATOR = new Parcelable.Creator<CallingMetricsModel>() { // from class: com.amazon.deecomms.calling.model.CallingMetricsModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public CallingMetricsModel mo3631createFromParcel(Parcel parcel) {
            return new CallingMetricsModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public CallingMetricsModel[] mo3632newArray(int i) {
            return new CallingMetricsModel[i];
        }
    };
    @JsonProperty("callEndTime")
    private long callEndTime;
    @JsonProperty("callProvider")
    private String callProvider;
    @JsonProperty("rating")
    private String callRating;
    @JsonProperty("callStartTime")
    private long callStartTime;
    @JsonProperty("calleeDeviceType")
    private String calleeDeviceType;
    @JsonProperty("calleeId")
    private String calleeId;
    @JsonProperty("calleePhoneNumber")
    private String calleePhoneNumber;
    @JsonProperty("callerDeviceType")
    private String callerDeviceType;
    @JsonProperty("callerId")
    private String callerId;
    @JsonProperty("country")
    private String country;
    private boolean isA2A;
    private Boolean isDeviceTargeted;
    private boolean isDropIn;
    @JsonProperty(Settings.ListeningSettings.EXTRA_REASON)
    private String reason;
    @JsonProperty("statusCode")
    private String statusCode;

    public CallingMetricsModel() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getCallEndTime() {
        return this.callEndTime;
    }

    public String getCallProvider() {
        return this.callProvider;
    }

    public String getCallRating() {
        return this.callRating;
    }

    public long getCallStartTime() {
        return this.callStartTime;
    }

    public String getCalleeDeviceType() {
        return this.calleeDeviceType;
    }

    public String getCalleeId() {
        return this.calleeId;
    }

    public String getCalleePhoneNumber() {
        return this.calleePhoneNumber;
    }

    public String getCallerDeviceType() {
        return this.callerDeviceType;
    }

    public String getCallerId() {
        return this.callerId;
    }

    public String getCountry() {
        return this.country;
    }

    public String getReason() {
        return this.reason;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    @JsonProperty("isA2A")
    public boolean isA2A() {
        return this.isA2A;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("isDeviceTargeted")
    public Boolean isDeviceTargeted() {
        return this.isDeviceTargeted;
    }

    @JsonProperty("isDropIn")
    public boolean isDropIn() {
        return this.isDropIn;
    }

    public void setA2A(boolean z) {
        this.isA2A = z;
    }

    public void setCallEndTime(long j) {
        this.callEndTime = j;
    }

    public void setCallProvider(String str) {
        this.callProvider = str;
    }

    public void setCallRating(String str) {
        this.callRating = str;
    }

    public void setCallStartTime(long j) {
        this.callStartTime = j;
    }

    public void setCalleeDeviceType(String str) {
        this.calleeDeviceType = str;
    }

    public void setCalleeId(String str) {
        this.calleeId = str;
    }

    public void setCalleePhoneNumber(String str) {
        this.calleePhoneNumber = str;
    }

    public void setCallerDeviceType(String str) {
        this.callerDeviceType = str;
    }

    public void setCallerId(String str) {
        this.callerId = str;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setDeviceTargeted(Boolean bool) {
        this.isDeviceTargeted = bool;
    }

    public void setDropIn(boolean z) {
        this.isDropIn = z;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getCallId());
        parcel.writeString(getPlatform());
        parcel.writeLong(getCreationDate());
        parcel.writeString(getLocalTime());
        parcel.writeString(this.country);
        parcel.writeString(this.statusCode);
        parcel.writeString(this.reason);
        parcel.writeLong(this.callStartTime);
        parcel.writeLong(this.callEndTime);
        parcel.writeString(this.callerId);
        parcel.writeString(this.calleeId);
        parcel.writeString(this.callerDeviceType);
        parcel.writeString(this.calleeDeviceType);
        parcel.writeString(this.callRating);
        parcel.writeByte(isA2A() ? (byte) 1 : (byte) 0);
        parcel.writeByte(isDropIn() ? (byte) 1 : (byte) 0);
        Boolean bool = this.isDeviceTargeted;
        parcel.writeByte((bool == null || !bool.booleanValue()) ? (byte) 0 : (byte) 1);
        parcel.writeString(this.callProvider);
        parcel.writeString(this.calleePhoneNumber);
        parcel.writeString(getDeviceMake());
        parcel.writeString(getDeviceModel());
        parcel.writeDouble(getDeviceScreenSize());
    }

    public CallingMetricsModel(Parcel parcel) {
        setCallId(parcel.readString());
        setPlatform(parcel.readString());
        setCreationDate(parcel.readLong());
        setLocalTime(parcel.readString());
        this.country = parcel.readString();
        this.statusCode = parcel.readString();
        this.reason = parcel.readString();
        this.callStartTime = parcel.readLong();
        this.callEndTime = parcel.readLong();
        this.callerId = parcel.readString();
        this.calleeId = parcel.readString();
        this.callerDeviceType = parcel.readString();
        this.calleeDeviceType = parcel.readString();
        this.callRating = parcel.readString();
        boolean z = true;
        this.isA2A = parcel.readByte() != 0;
        this.isDropIn = parcel.readByte() != 0;
        this.isDeviceTargeted = Boolean.valueOf(parcel.readByte() == 0 ? false : z);
        this.callProvider = parcel.readString();
        this.calleePhoneNumber = parcel.readString();
        setDeviceMake(parcel.readString());
        setDeviceModel(parcel.readString());
        setDeviceScreenSize(parcel.readDouble());
    }
}
