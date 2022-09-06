package com.amazon.wellness.io.format.abs;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes13.dex */
public final class EventOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_Event_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_Event_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u000bevent.proto\u001a\u000fbiometric.proto\u001a\u000bsleep.proto\u001a\ntime.proto\"ó\u0002\n\u0005Event\u0012'\n\u001fabsolute_uptime_milliseconds_lo\u0018\u0001 \u0001(\r\u0012'\n\u001fabsolute_uptime_milliseconds_hi\u0018\u0002 \u0001(\r\u0012$\n\u001crelative_uptime_milliseconds\u0018\u0003 \u0001(\r\u00123\n\u0014biometric_data_point\u0018\u0004 \u0001(\u000b2\u0013.BiometricDataPointH\u0000\u00125\n\u0015synchronize_real_time\u0018\u0005 \u0001(\u000b2\u0014.SynchronizeRealTimeH\u0000\u00120\n\u0012synchronize_uptime\u0018\u0006 \u0001(\u000b2\u0012.SynchronizeUptimeH\u0000\u0012I\n sleep_domain_transition_event_v1\u0018\u0007 \u0001(\u000b2\u001d.SleepDomainTransitionEventV1H\u0000B\t\n\u0007payloadB.\n!com.amazon.wellness.io.format.absP\u0001 \u0001\u0001¢\u0002\u0003ABSb\u0006proto3"}, new Descriptors.FileDescriptor[]{Biometric.getDescriptor(), Sleep.getDescriptor(), Time.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.wellness.io.format.abs.EventOuterClass.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = EventOuterClass.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_Event_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_Event_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_Event_descriptor, new String[]{"AbsoluteUptimeMillisecondsLo", "AbsoluteUptimeMillisecondsHi", "RelativeUptimeMilliseconds", "BiometricDataPoint", "SynchronizeRealTime", "SynchronizeUptime", "SleepDomainTransitionEventV1", DatabaseHelper.appInfo_Payload});
        Biometric.getDescriptor();
        Sleep.getDescriptor();
        Time.getDescriptor();
    }

    private EventOuterClass() {
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
