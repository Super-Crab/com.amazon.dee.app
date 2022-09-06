package com.amazon.wellness.io.format.abs;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
/* loaded from: classes13.dex */
public final class Sleep {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_SleepDomainTransitionEventV1_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_SleepDomainTransitionEventV1_fieldAccessorTable;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u000bsleep.proto\"s\n\u001cSleepDomainTransitionEventV1\u0012&\n\tnew_state\u0018\u0001 \u0001(\u000e2\u0013.SleepDomainStateV1\u0012+\n\u000eprevious_state\u0018\u0002 \u0001(\u000e2\u0013.SleepDomainStateV1*T\n\u0012SleepDomainStateV1\u0012\u000b\n\u0007UNKNOWN\u0010\u0000\u0012\t\n\u0005AWAKE\u0010\u0001\u0012\n\n\u0006INTENT\u0010\u0002\u0012\u000e\n\nSTOPINTENT\u0010\u0003\u0012\n\n\u0006ASLEEP\u0010\u0004B.\n!com.amazon.wellness.io.format.absP\u0001 \u0001\u0001¢\u0002\u0003ABSb\u0006proto3"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.wellness.io.format.abs.Sleep.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = Sleep.descriptor = fileDescriptor;
                return null;
            }
        });
        internal_static_SleepDomainTransitionEventV1_descriptor = getDescriptor().getMessageTypes().get(0);
        internal_static_SleepDomainTransitionEventV1_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(internal_static_SleepDomainTransitionEventV1_descriptor, new String[]{"NewState", "PreviousState"});
    }

    private Sleep() {
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
