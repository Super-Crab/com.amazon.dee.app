package com.amazon.tarazed.core.types.annotations;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AnnotationType.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationType;", "", "value", "", "serializer", "Lkotlinx/serialization/KSerializer;", "(Ljava/lang/String;ILjava/lang/String;Lkotlinx/serialization/KSerializer;)V", "getSerializer", "()Lkotlinx/serialization/KSerializer;", "getValue", "()Ljava/lang/String;", "LINE", "RECTANGLE", "FREEFORM", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public enum AnnotationType {
    LINE("line", LineAnnotation.Companion.serializer()),
    RECTANGLE("rectangle", RectangleAnnotation.Companion.serializer()),
    FREEFORM("freeform", FreeformAnnotation.Companion.serializer());
    
    public static final Companion Companion = new Companion(null);
    private static final Map<String, AnnotationType> valueMap;
    @NotNull
    private final KSerializer<?> serializer;
    @NotNull
    private final String value;

    /* compiled from: AnnotationType.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/core/types/annotations/AnnotationType$Companion;", "", "()V", "valueMap", "", "", "Lcom/amazon/tarazed/core/types/annotations/AnnotationType;", "fromString", "value", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final AnnotationType fromString(@NotNull String value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            return (AnnotationType) AnnotationType.valueMap.get(value);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        int mapCapacity;
        int coerceAtLeast;
        AnnotationType[] values = values();
        mapCapacity = MapsKt__MapsJVMKt.mapCapacity(values.length);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(mapCapacity, 16);
        LinkedHashMap linkedHashMap = new LinkedHashMap(coerceAtLeast);
        for (AnnotationType annotationType : values) {
            linkedHashMap.put(annotationType.value, annotationType);
        }
        valueMap = linkedHashMap;
    }

    AnnotationType(String str, KSerializer kSerializer) {
        this.value = str;
        this.serializer = kSerializer;
    }

    @NotNull
    public final KSerializer<?> getSerializer() {
        return this.serializer;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
