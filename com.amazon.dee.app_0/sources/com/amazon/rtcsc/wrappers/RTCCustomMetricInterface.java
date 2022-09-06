package com.amazon.rtcsc.wrappers;

import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.amazon.devicesetupservice.scap.v1.BleConnectionPriority;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class RTCCustomMetricInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RTCCustomMetricInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RTCCustomMetricInterface rTCCustomMetricInterface) {
        if (rTCCustomMetricInterface == null) {
            return 0L;
        }
        return rTCCustomMetricInterface.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (!this.swigCMemOwn) {
                this.swigCPtr = 0L;
            } else {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
        }
    }

    public CounterVector getCounters() {
        return new CounterVector(RTCControllerAndroidJNI.RTCCustomMetricInterface_getCounters(this.swigCPtr, this), true);
    }

    public MetadataVector getMetadata() {
        return new MetadataVector(RTCControllerAndroidJNI.RTCCustomMetricInterface_getMetadata(this.swigCPtr, this), true);
    }

    public Priority getPriority() {
        return Priority.swigToEnum(RTCControllerAndroidJNI.RTCCustomMetricInterface_getPriority(this.swigCPtr, this));
    }

    public String getProgramName() {
        return RTCControllerAndroidJNI.RTCCustomMetricInterface_getProgramName(this.swigCPtr, this);
    }

    public String getSourceName() {
        return RTCControllerAndroidJNI.RTCCustomMetricInterface_getSourceName(this.swigCPtr, this);
    }

    public TimerVector getTimers() {
        return new TimerVector(RTCControllerAndroidJNI.RTCCustomMetricInterface_getTimers(this.swigCPtr, this), true);
    }

    /* loaded from: classes13.dex */
    public static class Counter {
        protected transient boolean swigCMemOwn;
        private transient long swigCPtr;

        /* JADX INFO: Access modifiers changed from: protected */
        public Counter(long j, boolean z) {
            this.swigCMemOwn = z;
            this.swigCPtr = j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static long getCPtr(Counter counter) {
            if (counter == null) {
                return 0L;
            }
            return counter.swigCPtr;
        }

        public synchronized void delete() {
            if (this.swigCPtr != 0) {
                if (this.swigCMemOwn) {
                    this.swigCMemOwn = false;
                    RTCControllerAndroidJNI.delete_RTCCustomMetricInterface_Counter(this.swigCPtr);
                }
                this.swigCPtr = 0L;
            }
        }

        protected void finalize() {
            delete();
        }

        public String getName() {
            return RTCControllerAndroidJNI.RTCCustomMetricInterface_Counter_name_get(this.swigCPtr, this);
        }

        public int getValue() {
            return RTCControllerAndroidJNI.RTCCustomMetricInterface_Counter_value_get(this.swigCPtr, this);
        }

        public void setName(String str) {
            RTCControllerAndroidJNI.RTCCustomMetricInterface_Counter_name_set(this.swigCPtr, this, str);
        }

        public void setValue(int i) {
            RTCControllerAndroidJNI.RTCCustomMetricInterface_Counter_value_set(this.swigCPtr, this, i);
        }

        public Counter() {
            this(RTCControllerAndroidJNI.new_RTCCustomMetricInterface_Counter(), true);
        }
    }

    /* loaded from: classes13.dex */
    public static class Metadata {
        protected transient boolean swigCMemOwn;
        private transient long swigCPtr;

        /* JADX INFO: Access modifiers changed from: protected */
        public Metadata(long j, boolean z) {
            this.swigCMemOwn = z;
            this.swigCPtr = j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static long getCPtr(Metadata metadata) {
            if (metadata == null) {
                return 0L;
            }
            return metadata.swigCPtr;
        }

        public synchronized void delete() {
            if (this.swigCPtr != 0) {
                if (this.swigCMemOwn) {
                    this.swigCMemOwn = false;
                    RTCControllerAndroidJNI.delete_RTCCustomMetricInterface_Metadata(this.swigCPtr);
                }
                this.swigCPtr = 0L;
            }
        }

        protected void finalize() {
            delete();
        }

        public String getName() {
            return RTCControllerAndroidJNI.RTCCustomMetricInterface_Metadata_name_get(this.swigCPtr, this);
        }

        public String getValue() {
            return RTCControllerAndroidJNI.RTCCustomMetricInterface_Metadata_value_get(this.swigCPtr, this);
        }

        public void setName(String str) {
            RTCControllerAndroidJNI.RTCCustomMetricInterface_Metadata_name_set(this.swigCPtr, this, str);
        }

        public void setValue(String str) {
            RTCControllerAndroidJNI.RTCCustomMetricInterface_Metadata_value_set(this.swigCPtr, this, str);
        }

        public Metadata() {
            this(RTCControllerAndroidJNI.new_RTCCustomMetricInterface_Metadata(), true);
        }
    }

    /* loaded from: classes13.dex */
    public static final class Priority {
        private final String swigName;
        private final int swigValue;
        public static final Priority NORMAL = new Priority(DriveModeVoxUiConstants.NORMAL);
        public static final Priority HIGH = new Priority(BleConnectionPriority.HIGH);
        public static final Priority CRITICAL = new Priority(DriveModeVoxUiConstants.CRITICAL);
        private static Priority[] swigValues = {NORMAL, HIGH, CRITICAL};
        private static int swigNext = 0;

        private Priority(String str) {
            this.swigName = str;
            int i = swigNext;
            swigNext = i + 1;
            this.swigValue = i;
        }

        public static Priority swigToEnum(int i) {
            Priority[] priorityArr = swigValues;
            if (i >= priorityArr.length || i < 0 || priorityArr[i].swigValue != i) {
                int i2 = 0;
                while (true) {
                    Priority[] priorityArr2 = swigValues;
                    if (i2 < priorityArr2.length) {
                        if (priorityArr2[i2].swigValue == i) {
                            return priorityArr2[i2];
                        }
                        i2++;
                    } else {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline67("No enum ", Priority.class, " with value ", i));
                    }
                }
            } else {
                return priorityArr[i];
            }
        }

        public final int swigValue() {
            return this.swigValue;
        }

        public String toString() {
            return this.swigName;
        }

        private Priority(String str, int i) {
            this.swigName = str;
            this.swigValue = i;
            swigNext = i + 1;
        }

        private Priority(String str, Priority priority) {
            this.swigName = str;
            this.swigValue = priority.swigValue;
            swigNext = this.swigValue + 1;
        }
    }

    /* loaded from: classes13.dex */
    public static class Timer {
        protected transient boolean swigCMemOwn;
        private transient long swigCPtr;

        /* JADX INFO: Access modifiers changed from: protected */
        public Timer(long j, boolean z) {
            this.swigCMemOwn = z;
            this.swigCPtr = j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static long getCPtr(Timer timer) {
            if (timer == null) {
                return 0L;
            }
            return timer.swigCPtr;
        }

        public synchronized void delete() {
            if (this.swigCPtr != 0) {
                if (this.swigCMemOwn) {
                    this.swigCMemOwn = false;
                    RTCControllerAndroidJNI.delete_RTCCustomMetricInterface_Timer(this.swigCPtr);
                }
                this.swigCPtr = 0L;
            }
        }

        protected void finalize() {
            delete();
        }

        public String getName() {
            return RTCControllerAndroidJNI.RTCCustomMetricInterface_Timer_name_get(this.swigCPtr, this);
        }

        public double getValue() {
            return RTCControllerAndroidJNI.RTCCustomMetricInterface_Timer_value_get(this.swigCPtr, this);
        }

        public void setName(String str) {
            RTCControllerAndroidJNI.RTCCustomMetricInterface_Timer_name_set(this.swigCPtr, this, str);
        }

        public void setValue(double d) {
            RTCControllerAndroidJNI.RTCCustomMetricInterface_Timer_value_set(this.swigCPtr, this, d);
        }

        public Timer() {
            this(RTCControllerAndroidJNI.new_RTCCustomMetricInterface_Timer(), true);
        }
    }
}
