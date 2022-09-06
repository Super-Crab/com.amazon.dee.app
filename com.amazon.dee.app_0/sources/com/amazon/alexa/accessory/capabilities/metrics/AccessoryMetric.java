package com.amazon.alexa.accessory.capabilities.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class AccessoryMetric {
    @VisibleForTesting
    static final int METRIC_IDENTIFIER_APP_USAGE = 2;
    @VisibleForTesting
    static final int METRIC_IDENTIFIER_UNKNOWN = -1;
    @VisibleForTesting
    static final int METRIC_IDENTIFIER_USER_PRESENT = 1;
    private final long bootNumber;
    private final int destination;
    private final String name;
    private final long sequenceNumber;
    private final long timestamp;
    private final Type type;
    private final List<Value> values;

    /* loaded from: classes.dex */
    public enum Destination {
        USER_PRESENT(1),
        APP_USAGE(2),
        UNKNOWN(-1);
        
        private final int value;

        Destination(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum Type {
        COUNTER,
        TIMER
    }

    public AccessoryMetric(String str, long j, int i, Type type, List<Value> list, long j2, long j3) {
        Preconditions.notNull(str, "name");
        Preconditions.notNull(type, "type");
        Preconditions.notNull(list, "values");
        this.name = str;
        this.timestamp = j;
        this.type = type;
        this.values = list;
        this.destination = i;
        this.bootNumber = j2;
        this.sequenceNumber = j3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AccessoryMetric.class != obj.getClass()) {
            return false;
        }
        AccessoryMetric accessoryMetric = (AccessoryMetric) obj;
        return this.timestamp == accessoryMetric.timestamp && Objects.equals(this.name, accessoryMetric.name) && this.type == accessoryMetric.type && this.destination == accessoryMetric.destination && this.bootNumber == accessoryMetric.bootNumber && this.sequenceNumber == accessoryMetric.sequenceNumber && Objects.equals(this.values, accessoryMetric.values);
    }

    public long getBootNumber() {
        return this.bootNumber;
    }

    public Destination getDestination() {
        int i = this.destination;
        if (i == 1) {
            return Destination.USER_PRESENT;
        }
        if (i == 2) {
            return Destination.APP_USAGE;
        }
        return Destination.UNKNOWN;
    }

    public String getName() {
        return this.name;
    }

    public long getSequenceNumber() {
        return this.sequenceNumber;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public Type getType() {
        return this.type;
    }

    public List<Value> getValues() {
        return this.values;
    }

    public int hashCode() {
        return Objects.hash(this.name, Long.valueOf(this.timestamp), Integer.valueOf(this.destination), this.type, this.values, Long.valueOf(this.bootNumber), Long.valueOf(this.sequenceNumber));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryMetric{name='");
        GeneratedOutlineSupport1.outline176(outline107, this.name, Chars.QUOTE, ", timestamp=");
        outline107.append(this.timestamp);
        outline107.append(", destination=");
        outline107.append(this.destination);
        outline107.append(", bootNumber=");
        outline107.append(this.bootNumber);
        outline107.append(", sequenceNumber=");
        outline107.append(this.sequenceNumber);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", values=");
        return GeneratedOutlineSupport1.outline94(outline107, this.values, JsonReaderKt.END_OBJ);
    }

    /* loaded from: classes.dex */
    public static final class Value {
        private final Object value;

        public Value(Integer num) {
            this.value = num;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && Value.class == obj.getClass()) {
                return Objects.equals(this.value, ((Value) obj).value);
            }
            return false;
        }

        public Boolean getBoolean() {
            Preconditions.precondition(isBoolean(), "not a boolean.");
            return (Boolean) this.value;
        }

        public Double getDouble() {
            Preconditions.precondition(isDouble(), "not a double.");
            return (Double) this.value;
        }

        public Float getFloat() {
            Preconditions.precondition(isFloat(), "not a float.");
            return (Float) this.value;
        }

        public Integer getInteger() {
            Preconditions.precondition(isInteger(), "not an integer.");
            return (Integer) this.value;
        }

        public String getString() {
            Preconditions.precondition(isString(), "not a string.");
            return (String) this.value;
        }

        public int hashCode() {
            return Objects.hash(this.value);
        }

        public boolean isBoolean() {
            return this.value instanceof Boolean;
        }

        public boolean isDouble() {
            return this.value instanceof Double;
        }

        public boolean isFloat() {
            return this.value instanceof Float;
        }

        public boolean isInteger() {
            return this.value instanceof Integer;
        }

        public boolean isString() {
            return this.value instanceof String;
        }

        public Value(Float f) {
            this.value = f;
        }

        public Value(Double d) {
            this.value = d;
        }

        public Value(String str) {
            this.value = str;
        }

        public Value(Boolean bool) {
            this.value = bool;
        }
    }
}
