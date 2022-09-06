package com.amazon.tarazed.annotations.handlers;

import android.content.Context;
import com.amazon.tarazed.annotations.AnnotationView;
import com.amazon.tarazed.annotations.drawables.AnnotationDrawable;
import com.amazon.tarazed.annotations.drawables.FreeformDrawable;
import com.amazon.tarazed.annotations.drawables.LineDrawable;
import com.amazon.tarazed.annotations.drawables.RectangleDrawable;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazon.tarazed.core.types.annotations.AnnotationColor;
import com.amazon.tarazed.core.types.annotations.AnnotationEvent;
import com.amazon.tarazed.core.types.annotations.AnnotationEventSerializer;
import com.amazon.tarazed.core.types.annotations.FreeformAnnotation;
import com.amazon.tarazed.core.types.annotations.LineAnnotation;
import com.amazon.tarazed.core.types.annotations.RectangleAnnotation;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import org.jetbrains.annotations.NotNull;
/* compiled from: AnnotationHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001b2\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001:\u0001\u001bB-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u001a\u0010\u0017\u001a\u00020\u00182\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/amazon/tarazed/annotations/handlers/AnnotationHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/types/annotations/AnnotationEvent;", "annotationView", "Lcom/amazon/tarazed/annotations/AnnotationView;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "context", "Landroid/content/Context;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "(Lcom/amazon/tarazed/annotations/AnnotationView;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;)V", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "payloadSerializer", "Lkotlinx/serialization/DeserializationStrategy;", "getPayloadSerializer", "()Lkotlinx/serialization/DeserializationStrategy;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AnnotationHandler implements TarazedEventHandler<AnnotationEvent<?>> {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_TYPE_ANNOTATION = "annotation";
    private static final String METRIC_UNKNOWN_ANNOTATION_TYPE = "UnknownAnnotationType";
    private static final String TAG = "AnnotationHandler";
    private final AnnotationView annotationView;
    private final Context context;
    private final DeviceInfoUtility deviceInfo;
    @NotNull
    private final List<String> handledEventTypes;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    @NotNull
    private final DeserializationStrategy<AnnotationEvent<?>> payloadSerializer;

    /* compiled from: AnnotationHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/annotations/handlers/AnnotationHandler$Companion;", "", "()V", "EVENT_TYPE_ANNOTATION", "", "METRIC_UNKNOWN_ANNOTATION_TYPE", "TAG", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public AnnotationHandler(@NotNull AnnotationView annotationView, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull Context context, @NotNull DeviceInfoUtility deviceInfo) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(annotationView, "annotationView");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        this.annotationView = annotationView;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.context = context;
        this.deviceInfo = deviceInfo;
        listOf = CollectionsKt__CollectionsJVMKt.listOf("annotation");
        this.handledEventTypes = listOf;
        this.payloadSerializer = new AnnotationEventSerializer();
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super AnnotationEvent<?>> getPayloadSerializer() {
        return this.payloadSerializer;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends AnnotationEvent<?>> e) {
        AnnotationDrawable rectangleDrawable;
        Intrinsics.checkParameterIsNotNull(e, "e");
        AnnotationEvent<?> payload = e.getPayload();
        Object annotation = payload.getAnnotation();
        AnnotationColor color = payload.getColor();
        if (annotation instanceof FreeformAnnotation) {
            FreeformAnnotation freeformAnnotation = (FreeformAnnotation) annotation;
            if (freeformAnnotation.getPath().size() >= 2) {
                rectangleDrawable = new FreeformDrawable(freeformAnnotation, this.annotationView, this.context, color, this.deviceInfo);
            } else {
                throw new SerializationException("Free-form annotations must contain at least two points", null, 2, null);
            }
        } else if (annotation instanceof LineAnnotation) {
            rectangleDrawable = new LineDrawable((LineAnnotation) annotation, this.annotationView, this.context, color, this.deviceInfo);
        } else if (!(annotation instanceof RectangleAnnotation)) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown annotation type: ");
            outline107.append(payload.getAnnotationType());
            tarazedSessionLogger.w(TAG, outline107.toString());
            this.metricsHelper.addCount(TAG, METRIC_UNKNOWN_ANNOTATION_TYPE, 1.0d);
            return;
        } else {
            rectangleDrawable = new RectangleDrawable((RectangleAnnotation) annotation, this.annotationView, this.context, color, this.deviceInfo);
        }
        this.annotationView.addAnnotation(rectangleDrawable);
    }
}
