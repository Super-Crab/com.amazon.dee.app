package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes13.dex */
public final class Time {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_SynchronizeRealTime_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_SynchronizeRealTime_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_SynchronizeUptime_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_SynchronizeUptime_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\ntime.proto\"g\n\u0013SynchronizeRealTime\u0012'\n\u001fmilliseconds_since_the_epoch_lo\u0018\u0001 \u0001(\r\u0012'\n\u001fmilliseconds_since_the_epoch_hi\u0018\u0002 \u0001(\r\"\u0013\n\u0011SynchronizeUptimeB.\n!com.amazon.wellness.io.format.absP\u0001 \u0001\u0001¢\u0002\u0003ABSb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.wellness.io.format.abs.Time.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Time.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_SynchronizeRealTime_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_SynchronizeRealTime_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SynchronizeRealTime_descriptor, new String[]{"MillisecondsSinceTheEpochLo", "MillisecondsSinceTheEpochHi"});
        internal_static_SynchronizeUptime_descriptor = getDescriptor().getMessageTypes().get(1);
        internal_static_SynchronizeUptime_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SynchronizeUptime_descriptor, new String[0]);
    }

    private Time() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }
}
