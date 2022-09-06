package com.amazon.alexa.fitness.model.device;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeviceType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0010\u001a\u00020\u00012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0000\u001a\f\u0010\u0012\u001a\u0004\u0018\u00010\u000f*\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"ARMSTRONG_CASE_ID", "", "ARMSTRONG_CLUSTER_ID", "ARMSTRONG_LEFT_EAR_ID", "ARMSTRONG_RIGHT_EAR_ID", "ARROWHEAD_CASE_ID", "ARROWHEAD_CLUSTER_ID", "ARROWHEAD_LEFT_EAR_ID", "ARROWHEAD_RIGHT_EAR_ID", "ELLINGTON_CASE_ID", "ELLINGTON_CLUSTER_ID", "ELLINGTON_LEFT_EAR_ID", "ELLINGTON_RIGHT_EAR_ID", "identifierToDeviceTypeMap", "", "Lcom/amazon/alexa/fitness/model/device/DeviceType;", "convertIdentifierToDeviceType", "deviceType", "toDeviceType", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class DeviceTypeKt {
    @NotNull
    public static final String ARMSTRONG_CASE_ID = "A3HVREY4JWAZ6K";
    @NotNull
    public static final String ARMSTRONG_CLUSTER_ID = "A15QWUTQ6FSMYX";
    @NotNull
    public static final String ARMSTRONG_LEFT_EAR_ID = "A2QDHDQIWC2LTG";
    @NotNull
    public static final String ARMSTRONG_RIGHT_EAR_ID = "A31PMVIWCRNTX2";
    @NotNull
    public static final String ARROWHEAD_CASE_ID = "A21YKVRGQV9TET";
    @NotNull
    public static final String ARROWHEAD_CLUSTER_ID = "ADXK6Q246T9EA";
    @NotNull
    public static final String ARROWHEAD_LEFT_EAR_ID = "A168KS6Z8QG6RV";
    @NotNull
    public static final String ARROWHEAD_RIGHT_EAR_ID = "A3KF60B9RDMWLY";
    @NotNull
    public static final String ELLINGTON_CASE_ID = "AE9FIEPOC6D9B";
    @NotNull
    public static final String ELLINGTON_CLUSTER_ID = "A16MZVIFVHX6P6";
    @NotNull
    public static final String ELLINGTON_LEFT_EAR_ID = "AS8OPU4NNXJI8";
    @NotNull
    public static final String ELLINGTON_RIGHT_EAR_ID = "ALWUIN0P635PT";
    private static final Map<String, DeviceType> identifierToDeviceTypeMap;

    static {
        int mapCapacity;
        int collectionSizeOrDefault;
        DeviceType[] values = DeviceType.values();
        ArrayList<Pair> arrayList = new ArrayList();
        for (DeviceType deviceType : values) {
            Set<String> identifiers = deviceType.getIdentifiers();
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(identifiers, 10);
            ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
            for (String str : identifiers) {
                arrayList2.add(TuplesKt.to(str, deviceType));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList2);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair : arrayList) {
            String str2 = (String) pair.component1();
            Object obj = linkedHashMap.get(str2);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(str2, obj);
            }
            ((List) obj).add((DeviceType) pair.component2());
        }
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(linkedHashMap.size());
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(mapCapacity);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry.getKey(), (DeviceType) ((List) entry.getValue()).get(0));
        }
        identifierToDeviceTypeMap = linkedHashMap2;
    }

    @NotNull
    public static final String convertIdentifierToDeviceType(@Nullable String str) {
        boolean contains;
        boolean contains2;
        boolean contains3;
        contains = CollectionsKt___CollectionsKt.contains(DeviceType.ELLINGTON.getIdentifiers(), str);
        if (contains) {
            return "A16MZVIFVHX6P6";
        }
        contains2 = CollectionsKt___CollectionsKt.contains(DeviceType.ARMSTRONG.getIdentifiers(), str);
        if (contains2) {
            return "A15QWUTQ6FSMYX";
        }
        contains3 = CollectionsKt___CollectionsKt.contains(DeviceType.ARROWHEAD.getIdentifiers(), str);
        return contains3 ? "ADXK6Q246T9EA" : "";
    }

    @Nullable
    public static final DeviceType toDeviceType(@NotNull String toDeviceType) {
        Intrinsics.checkParameterIsNotNull(toDeviceType, "$this$toDeviceType");
        return identifierToDeviceTypeMap.get(toDeviceType);
    }
}
