package com.amazon.device.nos;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/* loaded from: classes12.dex */
public class TransferCriteria implements Parcelable, Serializable {
    public static final Parcelable.Creator<TransferCriteria> CREATOR = new Parcelable.Creator<TransferCriteria>() { // from class: com.amazon.device.nos.TransferCriteria.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public TransferCriteria mo3828createFromParcel(Parcel parcel) {
            return new TransferCriteria(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public TransferCriteria[] mo3829newArray(int i) {
            return new TransferCriteria[i];
        }
    };
    public static final long DEFAULT_MAX_TRANSFER_DELAY_MILLIS = 86400000;
    public static final long DEFAULT_MIN_TRANSFER_DELAY_MILLIS = 0;
    public static final int NETWORK_TYPE_MOBILE = 2;
    public static final int NETWORK_TYPE_ROAMING = 4;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final long REPEAT_INTERVAL_LOWER_BOUND_MILLIS = 5000;
    public static final long REPEAT_INTERVAL_UPPER_BOUND_MILLIS = 604800000;
    private static final String TAG = "NOS:TransferCriteria";
    public static final long TRANSFER_DELAY_DELTA_MILLIS = 1000;
    public static final long TRANSFER_DELAY_LOWER_BOUND_MILLIS = 5000;
    public static final long TRANSFER_DELAY_UPPER_BOUND_MILLIS = 604800000;
    private static final long serialVersionUID = 10172014;
    private int mBatteryThresholdCharging;
    private int mBatteryThresholdNotCharging;
    private ComponentName mComponentName;
    private DataSizeBucket mDataSizeBucket;
    private long mDataSizeKB;
    private long mMaxTransferDelayMillis;
    private long mMinTransferDelayMillis;
    private int mNetworkType;
    private int mRegistrationID;
    private long mRegistrationTimeMillis;
    private long mRepeatIntervalMillis;
    private boolean mRequireBatteryCharging;
    private boolean mRequireDeviceIdle;
    private int mSignalStrengthThreshold;

    /* loaded from: classes12.dex */
    public enum DataSizeBucket {
        XSMALL(0, null),
        SMALL(256, XSMALL),
        MEDIUM(2048, SMALL),
        LARGE(15360, MEDIUM);
        
        private final int lowerLimit;
        DataSizeBucket nextLowerBucket;

        DataSizeBucket(int i, DataSizeBucket dataSizeBucket) {
            this.lowerLimit = i;
            this.nextLowerBucket = dataSizeBucket;
        }

        public int getLowerLimit() {
            return this.lowerLimit;
        }
    }

    private static TransferCriteriaBuilder readFromParcel(Parcel parcel) {
        ComponentName readFromParcel = ComponentName.readFromParcel(parcel);
        TransferCriteriaBuilder transferCriteriaBuilder = new TransferCriteriaBuilder();
        boolean z = false;
        TransferCriteriaBuilder batteryChargingRequired = transferCriteriaBuilder.setComponentName(readFromParcel).setDataSizeKB(parcel.readLong()).setRegistrationId(parcel.readInt()).setMinTransferDelayMillis(parcel.readLong()).setMaxTransferDelayMillis(parcel.readLong()).setRepeatIntervalMillis(parcel.readLong()).setNetworkType(parcel.readInt()).setBatteryChargingRequired(parcel.readByte() == 1);
        if (parcel.readByte() == 1) {
            z = true;
        }
        batteryChargingRequired.setDeviceIdleRequired(z);
        return transferCriteriaBuilder;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.mRegistrationID = objectInputStream.readInt();
        this.mDataSizeKB = objectInputStream.readLong();
        this.mNetworkType = objectInputStream.readInt();
        this.mComponentName = new ComponentName(objectInputStream.readUTF(), objectInputStream.readUTF());
        this.mMinTransferDelayMillis = objectInputStream.readLong();
        this.mMaxTransferDelayMillis = objectInputStream.readLong();
        this.mRepeatIntervalMillis = objectInputStream.readLong();
        this.mRegistrationTimeMillis = objectInputStream.readLong();
        this.mRequireBatteryCharging = objectInputStream.readBoolean();
        this.mRequireDeviceIdle = objectInputStream.readBoolean();
        updateDataSizeBucket();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.mRegistrationID);
        objectOutputStream.writeLong(this.mDataSizeKB);
        objectOutputStream.writeInt(this.mNetworkType);
        objectOutputStream.writeUTF(this.mComponentName.getPackageName());
        objectOutputStream.writeUTF(this.mComponentName.getClassName());
        objectOutputStream.writeLong(this.mMinTransferDelayMillis);
        objectOutputStream.writeLong(this.mMaxTransferDelayMillis);
        objectOutputStream.writeLong(this.mRepeatIntervalMillis);
        objectOutputStream.writeLong(this.mRegistrationTimeMillis);
        objectOutputStream.writeBoolean(this.mRequireBatteryCharging);
        objectOutputStream.writeBoolean(this.mRequireDeviceIdle);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != TransferCriteria.class) {
            return false;
        }
        TransferCriteria transferCriteria = (TransferCriteria) obj;
        return this.mComponentName.equals(transferCriteria.mComponentName) && this.mRegistrationID == transferCriteria.mRegistrationID && this.mDataSizeKB == transferCriteria.mDataSizeKB && this.mMinTransferDelayMillis == transferCriteria.mMinTransferDelayMillis && this.mMaxTransferDelayMillis == transferCriteria.mMaxTransferDelayMillis && this.mRepeatIntervalMillis == transferCriteria.mRepeatIntervalMillis && this.mNetworkType == transferCriteria.mNetworkType && this.mRequireBatteryCharging == transferCriteria.mRequireBatteryCharging && this.mRequireDeviceIdle == transferCriteria.mRequireDeviceIdle;
    }

    public long getActivationTimeMillis() {
        return this.mRegistrationTimeMillis + this.mMinTransferDelayMillis;
    }

    public int getBatteryThresholdCharging() {
        return this.mBatteryThresholdCharging;
    }

    public int getBatteryThresholdNotCharging() {
        return this.mBatteryThresholdNotCharging;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public DataSizeBucket getDataSizeBucket() {
        return this.mDataSizeBucket;
    }

    public long getDataSizeKB() {
        return this.mDataSizeKB;
    }

    public long getExpiryTimeMillis() {
        return this.mRegistrationTimeMillis + this.mMaxTransferDelayMillis;
    }

    public long getMaxTransferDelayMillis() {
        return this.mMaxTransferDelayMillis;
    }

    public long getMinTransferDelayMillis() {
        return this.mMinTransferDelayMillis;
    }

    public int getNetworkType() {
        return this.mNetworkType;
    }

    public int getRegistrationID() {
        return this.mRegistrationID;
    }

    public long getRegistrationTimeMillis() {
        return this.mRegistrationTimeMillis;
    }

    public long getRepeatIntervalMillis() {
        return this.mRepeatIntervalMillis;
    }

    public int getSignalStrengthThreshold() {
        return this.mSignalStrengthThreshold;
    }

    public boolean hasActivated() {
        return getActivationTimeMillis() <= SystemClock.elapsedRealtime();
    }

    public boolean hasActivatedDelta() {
        return getActivationTimeMillis() - 1000 <= SystemClock.elapsedRealtime();
    }

    public boolean hasExpired() {
        return getExpiryTimeMillis() <= SystemClock.elapsedRealtime();
    }

    public boolean hasExpiredDelta() {
        return getExpiryTimeMillis() - 1000 <= SystemClock.elapsedRealtime();
    }

    public int hashCode() {
        return new Boolean(this.mRequireDeviceIdle).hashCode() + new Boolean(this.mRequireBatteryCharging).hashCode() + new Long(this.mRepeatIntervalMillis).hashCode() + new Long(this.mMaxTransferDelayMillis).hashCode() + new Long(this.mMinTransferDelayMillis).hashCode() + new Long(this.mDataSizeKB).hashCode() + this.mComponentName.hashCode() + this.mRegistrationID + this.mNetworkType;
    }

    public boolean isBatteryChargingRequired() {
        return this.mRequireBatteryCharging;
    }

    public boolean isDeviceIdleRequired() {
        return this.mRequireDeviceIdle;
    }

    public boolean isMobile() {
        return (this.mNetworkType & 2) == 2;
    }

    public boolean isRepeatRegistration() {
        return this.mRepeatIntervalMillis != 0;
    }

    public boolean isRoaming() {
        return (this.mNetworkType & 4) == 4;
    }

    public boolean isWiFi() {
        return (this.mNetworkType & 1) == 1;
    }

    public void setBatteryThresholdCharging(int i) {
        this.mBatteryThresholdCharging = i;
    }

    public void setBatteryThresholdNotCharging(int i) {
        this.mBatteryThresholdNotCharging = i;
    }

    public void setDataSizeBucket(DataSizeBucket dataSizeBucket) {
        this.mDataSizeBucket = dataSizeBucket;
    }

    public void setMaxTransferDelayMillis(long j) {
        this.mMaxTransferDelayMillis = j;
    }

    public void setNetworkType(int i) {
        this.mNetworkType = i;
    }

    public void setRegistrationID(int i) {
        this.mRegistrationID = i;
    }

    public void setRegistrationTime(long j) {
        this.mRegistrationTimeMillis = j;
    }

    public void setRegistrationTimeMillis(long j) {
        this.mRegistrationTimeMillis = j;
    }

    public void setThresholds(int i, int i2, int i3) {
        this.mBatteryThresholdCharging = i;
        this.mBatteryThresholdNotCharging = i2;
        this.mSignalStrengthThreshold = i3;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.mComponentName.toString());
        stringBuffer.append("; ");
        stringBuffer.append("Registration ID: " + this.mRegistrationID);
        stringBuffer.append("; ");
        stringBuffer.append("Data Size KB: " + this.mDataSizeKB);
        stringBuffer.append("; ");
        stringBuffer.append("Min Delay Millis: " + this.mMinTransferDelayMillis);
        stringBuffer.append("; ");
        stringBuffer.append("Max Delay Millis: " + this.mMaxTransferDelayMillis);
        stringBuffer.append("; ");
        stringBuffer.append("Repeat Interval Millis: " + this.mRepeatIntervalMillis);
        stringBuffer.append("; ");
        stringBuffer.append("Mobile?: " + isMobile());
        stringBuffer.append("; ");
        stringBuffer.append("WIFI?: " + isWiFi());
        stringBuffer.append("; ");
        stringBuffer.append("Roaming?: " + isRoaming());
        stringBuffer.append("Require Charing?: " + isBatteryChargingRequired());
        stringBuffer.append("Require Device Idle?: " + isDeviceIdleRequired());
        return stringBuffer.toString();
    }

    public void updateDataSizeBucket() {
        this.mDataSizeBucket = DataSizeBucket.LARGE;
        while (this.mDataSizeKB < this.mDataSizeBucket.lowerLimit) {
            this.mDataSizeBucket = this.mDataSizeBucket.nextLowerBucket;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ComponentName.writeToParcel(this.mComponentName, parcel);
        parcel.writeLong(this.mDataSizeKB);
        parcel.writeInt(this.mRegistrationID);
        parcel.writeLong(this.mMinTransferDelayMillis);
        parcel.writeLong(this.mMaxTransferDelayMillis);
        parcel.writeLong(this.mRepeatIntervalMillis);
        parcel.writeInt(this.mNetworkType);
        parcel.writeByte(this.mRequireBatteryCharging ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mRequireDeviceIdle ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransferCriteria(TransferCriteriaBuilder transferCriteriaBuilder) {
        this.mRequireBatteryCharging = false;
        this.mRequireDeviceIdle = false;
        this.mRegistrationID = transferCriteriaBuilder.mBuilderRegistrationId;
        this.mDataSizeKB = transferCriteriaBuilder.mBuilderDataSizeKB;
        this.mNetworkType = transferCriteriaBuilder.mBuilderNetworkType;
        this.mComponentName = transferCriteriaBuilder.mBuilderComponentName;
        this.mMinTransferDelayMillis = transferCriteriaBuilder.mBuilderMinTransferDelayMillis;
        this.mMaxTransferDelayMillis = transferCriteriaBuilder.mBuilderMaxTransferDelayMillis;
        this.mRepeatIntervalMillis = transferCriteriaBuilder.mBuilderRepeatIntervalMillis;
        this.mRequireBatteryCharging = transferCriteriaBuilder.mRequireBatteryCharging;
        this.mRequireDeviceIdle = transferCriteriaBuilder.mRequireDeviceIdle;
        this.mRegistrationTimeMillis = SystemClock.elapsedRealtime();
        updateDataSizeBucket();
    }

    private TransferCriteria(Parcel parcel) {
        this(readFromParcel(parcel));
    }
}
