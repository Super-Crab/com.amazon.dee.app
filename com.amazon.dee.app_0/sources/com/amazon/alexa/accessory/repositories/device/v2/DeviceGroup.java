package com.amazon.alexa.accessory.repositories.device.v2;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public final class DeviceGroup implements Iterable<Device> {
    private static final String DEVICES_KEY = "devices";
    private static final String IDENTIFIER_KEY = "identifier";
    private static final String ID_KEY = "id";
    private static final String LIST_KEY = "list";
    private static final String STANDBY_REASON_ORDINAL_KEY = "standbyReasonOrdinal";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String TRANSPORT_TYPE_ORDINAL_KEY = "transport";
    private final List<Device> devices;
    private final long id;
    private final String identifier;
    private final long standbyExpirationTimestamp;
    private final StandbyReason standbyReason;
    private final AccessoryTransport.Type transport;

    /* loaded from: classes6.dex */
    public enum Condition {
        ACTIVE(1),
        STANDBY_TIMEOUT(Long.MIN_VALUE) { // from class: com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup.Condition.1
            @Override // com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup.Condition
            public long getSpecialTimestamp() {
                throw new UnsupportedOperationException("STANDBY_TIMEOUT does not have special timestamp value");
            }
        },
        STANDBY_INDEFINITE(0);
        
        private final long specialTimestamp;

        public long getSpecialTimestamp() {
            return this.specialTimestamp;
        }

        Condition(long j) {
            this.specialTimestamp = j;
        }
    }

    /* loaded from: classes6.dex */
    public enum StandbyReason {
        UNKNOWN,
        AAP_CONNECTION_REFUSAL
    }

    public static DeviceGroup fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList;
        Bundle bundle2 = bundle.getBundle("devices");
        ArrayList arrayList = new ArrayList();
        if (bundle2 != null && (parcelableArrayList = bundle2.getParcelableArrayList(LIST_KEY)) != null) {
            Iterator it2 = parcelableArrayList.iterator();
            while (it2.hasNext()) {
                arrayList.add(Device.fromBundle((Bundle) it2.next()));
            }
        }
        return newBuilder().devices(arrayList).identifier(bundle.getString("identifier")).setConditionStandbyTimeout(bundle.getLong("timestamp"), StandbyReason.values()[bundle.getInt(STANDBY_REASON_ORDINAL_KEY)]).transport(AccessoryTransport.Type.values()[bundle.getInt("transport")]).id(bundle.getLong("id")).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getDeviceWithHighestDeviceId$0(Device device, Device device2) {
        return device.getDeviceId().intValue() - device2.getDeviceId().intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getDeviceWithHighestId$1(Device device, Device device2) {
        return device.getDeviceId().intValue() - device2.getDeviceId().intValue();
    }

    @NonNull
    public static Builder newBuilder() {
        return new Builder();
    }

    public static Bundle toBundle(DeviceGroup deviceGroup) {
        List<Device> devices = deviceGroup.getDevices();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(devices.size());
        for (Device device : devices) {
            arrayList.add(Device.toBundle(device));
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(LIST_KEY, arrayList);
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("devices", bundle);
        bundle2.putString("identifier", deviceGroup.identifier);
        bundle2.putInt("transport", deviceGroup.transport.ordinal());
        bundle2.putLong("timestamp", deviceGroup.standbyExpirationTimestamp);
        bundle2.putInt(STANDBY_REASON_ORDINAL_KEY, deviceGroup.standbyReason.ordinal());
        bundle2.putLong("id", deviceGroup.id);
        return bundle2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceGroup.class != obj.getClass()) {
            return false;
        }
        DeviceGroup deviceGroup = (DeviceGroup) obj;
        return this.id == deviceGroup.id && Objects.equals(new HashSet(this.devices), new HashSet(deviceGroup.devices)) && Objects.equals(this.identifier, deviceGroup.identifier) && this.transport == deviceGroup.transport;
    }

    public Condition getCondition(long j) {
        if (this.standbyExpirationTimestamp == Condition.STANDBY_INDEFINITE.getSpecialTimestamp()) {
            return Condition.STANDBY_INDEFINITE;
        }
        if (this.standbyExpirationTimestamp > j) {
            return Condition.STANDBY_TIMEOUT;
        }
        return Condition.ACTIVE;
    }

    @Nullable
    public Device getDevice(long j) {
        Iterator<Device> it2 = iterator();
        while (it2.hasNext()) {
            Device next = it2.next();
            if (next.getDeviceId().intValue() == j) {
                return next;
            }
        }
        return null;
    }

    @Nullable
    public Device getDeviceWithHighestDeviceId() {
        if (this.devices.isEmpty()) {
            return null;
        }
        return (Device) Collections.max(this.devices, $$Lambda$DeviceGroup$ScHOuFVu__ZnyF5i9B9fl_eGCw.INSTANCE);
    }

    @Nullable
    public Device getDeviceWithHighestId() {
        if (this.devices.isEmpty()) {
            return null;
        }
        return (Device) Collections.max(this.devices, $$Lambda$DeviceGroup$9UOwFXpp7T1emlF8hjeUHFeRYRE.INSTANCE);
    }

    @NonNull
    public List<Device> getDevices() {
        return this.devices;
    }

    public long getId() {
        return this.id;
    }

    @NonNull
    public String getIdentifier() {
        return this.identifier;
    }

    public long getStandbyExpirationTimestamp() {
        return this.standbyExpirationTimestamp;
    }

    public StandbyReason getStandbyReason() {
        return this.standbyReason;
    }

    @NonNull
    public AccessoryTransport.Type getTransportType() {
        return this.transport;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id), this.devices, this.identifier, this.transport);
    }

    @Override // java.lang.Iterable
    public Iterator<Device> iterator() {
        return this.devices.iterator();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceGroup{id=");
        outline107.append(this.id);
        outline107.append(", devices=");
        outline107.append(this.devices);
        outline107.append(", identifier='");
        GeneratedOutlineSupport1.outline176(outline107, this.identifier, Chars.QUOTE, ", transport=");
        outline107.append(this.transport);
        outline107.append(", standbyExpirationTimestamp=");
        outline107.append(this.standbyExpirationTimestamp);
        outline107.append(", standbyReason=");
        outline107.append(this.standbyReason);
        outline107.append(", computedCondition=");
        outline107.append(getCondition(System.currentTimeMillis()));
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private DeviceGroup(@NonNull Builder builder) {
        this.id = builder.id;
        this.identifier = builder.identifier;
        this.transport = builder.transport;
        this.standbyExpirationTimestamp = builder.standbyExpirationTimestamp;
        this.standbyReason = builder.standbyReason;
        this.devices = Collections.unmodifiableList(builder.devices);
    }

    @NonNull
    public static Builder newBuilder(@NonNull DeviceGroup deviceGroup) {
        Preconditions.notNull(deviceGroup, "deviceGroup");
        return new Builder(deviceGroup);
    }

    @Nullable
    public Device getDevice() {
        if (!this.devices.isEmpty()) {
            return this.devices.get(0);
        }
        return null;
    }

    /* loaded from: classes6.dex */
    public static final class Builder {
        private final ArrayList<Device> devices;
        private long id;
        private String identifier;
        private long standbyExpirationTimestamp;
        private StandbyReason standbyReason;
        private AccessoryTransport.Type transport;

        public Builder() {
            this.id = -1L;
            this.devices = new ArrayList<>();
            this.standbyExpirationTimestamp = Condition.ACTIVE.getSpecialTimestamp();
            this.standbyReason = StandbyReason.UNKNOWN;
        }

        @NonNull
        public DeviceGroup build() {
            Preconditions.notNull(this.identifier, "identifier");
            Preconditions.notNull(this.transport, "transport");
            Preconditions.notNull(this.devices, "devices");
            Preconditions.precondition(this.standbyExpirationTimestamp >= 0, "standbyExpirationTimestamp >= 0");
            Preconditions.notNull(this.standbyReason, "standbyReason");
            return new DeviceGroup(this);
        }

        @Deprecated
        public Builder condition(Condition condition) {
            Preconditions.precondition(condition != Condition.STANDBY_TIMEOUT, "STANDBY_TIMEOUT condition must be set with setConditionStandbyTimeout()");
            this.standbyExpirationTimestamp = condition.getSpecialTimestamp();
            return this;
        }

        public Builder devices(List<Device> list) {
            this.devices.clear();
            this.devices.addAll(list);
            return this;
        }

        public Builder id(long j) {
            this.id = j;
            return this;
        }

        public Builder identifier(String str) {
            this.identifier = str;
            return this;
        }

        public Builder setConditionActive() {
            this.standbyExpirationTimestamp = Condition.ACTIVE.getSpecialTimestamp();
            return this;
        }

        public Builder setConditionStandbyIndefinite(StandbyReason standbyReason) {
            this.standbyExpirationTimestamp = Condition.STANDBY_INDEFINITE.getSpecialTimestamp();
            this.standbyReason = standbyReason;
            return this;
        }

        public Builder setConditionStandbyTimeout(long j, StandbyReason standbyReason) {
            this.standbyExpirationTimestamp = j;
            this.standbyReason = standbyReason;
            return this;
        }

        public Builder transport(AccessoryTransport.Type type) {
            this.transport = type;
            return this;
        }

        public Builder(@NonNull DeviceGroup deviceGroup) {
            this.id = -1L;
            Preconditions.notNull(deviceGroup, "deviceGroup");
            this.id = deviceGroup.id;
            this.identifier = deviceGroup.identifier;
            this.transport = deviceGroup.transport;
            this.devices = new ArrayList<>(deviceGroup.devices.size());
            this.devices.addAll(deviceGroup.devices);
            this.standbyExpirationTimestamp = deviceGroup.standbyExpirationTimestamp;
            this.standbyReason = deviceGroup.standbyReason;
        }
    }
}
