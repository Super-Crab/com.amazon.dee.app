package com.amazon.tarazed.annotations.handlers;

import com.amazon.tarazed.annotations.AnnotationView;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.signaling.events.EmptySerializable;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationRemovalHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/annotations/handlers/AnnotationRemovalHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/signaling/events/EmptySerializable;", "annotationView", "Lcom/amazon/tarazed/annotations/AnnotationView;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/annotations/AnnotationView;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "payloadSerializer", "Lkotlinx/serialization/DeserializationStrategy;", "getPayloadSerializer", "()Lkotlinx/serialization/DeserializationStrategy;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AnnotationRemovalHandler implements TarazedEventHandler<EmptySerializable> {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_TYPE_REMOVE_ALL_ANNOTATIONS = "removeAllAnnotations";
    @NotNull
    public static final String EVENT_TYPE_REMOVE_LAST_ANNOTATION = "removeLastAnnotation";
    private static final String METRIC_UNKNOWN_EVENT_TYPE = "UnknownEventType";
    private static final String TAG = "AnnotationRemHandler";
    private final AnnotationView annotationView;
    @NotNull
    private final List<String> handledEventTypes;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    private final DeserializationStrategy<? super EmptySerializable> payloadSerializer;

    /* compiled from: AnnotationRemovalHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/tarazed/annotations/handlers/AnnotationRemovalHandler$Companion;", "", "()V", "EVENT_TYPE_REMOVE_ALL_ANNOTATIONS", "", "EVENT_TYPE_REMOVE_LAST_ANNOTATION", "METRIC_UNKNOWN_EVENT_TYPE", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AnnotationRemovalHandler(@NotNull AnnotationView annotationView, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(annotationView, "annotationView");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.annotationView = annotationView;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{EVENT_TYPE_REMOVE_ALL_ANNOTATIONS, EVENT_TYPE_REMOVE_LAST_ANNOTATION});
        this.handledEventTypes = listOf;
        this.payloadSerializer = EmptySerializable.Companion.serializer();
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super EmptySerializable> getPayloadSerializer() {
        return this.payloadSerializer;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends EmptySerializable> e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        if (Intrinsics.areEqual(EVENT_TYPE_REMOVE_ALL_ANNOTATIONS, e.getType())) {
            this.annotationView.removeAllAnnotations();
        } else if (Intrinsics.areEqual(EVENT_TYPE_REMOVE_LAST_ANNOTATION, e.getType())) {
            this.annotationView.removeLastAnnotation();
        } else {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown event type: ");
            outline107.append(e.getType());
            tarazedSessionLogger.w(TAG, outline107.toString());
            this.metricsHelper.addCount(TAG, "UnknownEventType", 1.0d);
        }
    }
}
