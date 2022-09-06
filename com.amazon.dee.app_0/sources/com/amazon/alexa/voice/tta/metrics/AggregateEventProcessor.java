package com.amazon.alexa.voice.tta.metrics;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.metrics.TypedEventProcessor;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AggregateEventProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B\u001b\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0001J,\u0010\t\u001a\u00020\u0000\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\u000eJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0015R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor;", "Lcom/amazon/alexa/voice/tta/metrics/MetricEventProcessor;", "childProcessors", "", "([Lcom/amazon/alexa/voice/tta/metrics/MetricEventProcessor;)V", "", "(Ljava/util/List;)V", BulkOperationType.add, MetricKeys.VALUE_IS_CHILD, "addTyped", ExifInterface.LONGITUDE_EAST, "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "eventClass", "Ljava/lang/Class;", "Lcom/amazon/alexa/voice/tta/metrics/TypedEventProcessor$Listener;", "processEvent", "", "event", "time", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "whenEvent", "Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder;", "Builder", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class AggregateEventProcessor implements MetricEventProcessor {
    private final List<MetricEventProcessor> childProcessors;

    /* compiled from: AggregateEventProcessor.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0001.B3\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003JA\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0000\"\n\b\u0001\u0010\u001b\u0018\u0001*\u00028\u0000H\u0086\bJ$\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0000\"\b\b\u0001\u0010\u001b*\u00028\u00002\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0007J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J#\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u0000\"\b\b\u0001\u0010\u001b*\u00028\u00002\u0006\u0010\u001f\u001a\u0002H\u001b¢\u0006\u0002\u0010 J%\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000#\"\u00028\u0000¢\u0006\u0002\u0010$J%\u0010%\u001a\u00020\u00052\u001a\b\u0004\u0010&\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'H\u0086\bJ\u0014\u0010%\u001a\u00020\u00052\f\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000*J\t\u0010+\u001a\u00020,HÖ\u0001J%\u0010-\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0014\b\u0004\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\tH\u0086\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR(\u0010\b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006/"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "", "aggregate", "Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor;", "eventClass", "Ljava/lang/Class;", "predicate", "Lkotlin/Function1;", "", "(Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor;Ljava/lang/Class;Lkotlin/jvm/functions/Function1;)V", "getAggregate", "()Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor;", "getEventClass", "()Ljava/lang/Class;", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "setPredicate", "(Lkotlin/jvm/functions/Function1;)V", "component1", "component2", "component3", "copy", "equals", "other", "hasType", "U", "hashCode", "", "isEqualTo", "item", "(Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;)Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder;", "isOneOf", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, "", "([Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;)Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder;", "thenDo", "action", "Lkotlin/Function2;", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "", "Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder$OnEvent;", "toString", "", "where", "OnEvent", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Builder<T extends TtaEvent> {
        @NotNull
        private final AggregateEventProcessor aggregate;
        @NotNull
        private final Class<T> eventClass;
        @Nullable
        private Function1<? super T, Boolean> predicate;

        /* compiled from: AggregateEventProcessor.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u0007H&¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder$OnEvent;", ExifInterface.GPS_DIRECTION_TRUE, "", "onEvent", "", "event", "time", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "(Ljava/lang/Object;Lcom/amazon/alexa/voice/tta/metrics/EventTime;)V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes11.dex */
        public interface OnEvent<T> {
            void onEvent(T t, @NotNull EventTime eventTime);
        }

        public Builder(@NotNull AggregateEventProcessor aggregate, @NotNull Class<T> eventClass, @Nullable Function1<? super T, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(aggregate, "aggregate");
            Intrinsics.checkParameterIsNotNull(eventClass, "eventClass");
            this.aggregate = aggregate;
            this.eventClass = eventClass;
            this.predicate = function1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Builder copy$default(Builder builder, AggregateEventProcessor aggregateEventProcessor, Class cls, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                aggregateEventProcessor = builder.aggregate;
            }
            if ((i & 2) != 0) {
                cls = builder.eventClass;
            }
            if ((i & 4) != 0) {
                function1 = builder.predicate;
            }
            return builder.copy(aggregateEventProcessor, cls, function1);
        }

        @NotNull
        public final AggregateEventProcessor component1() {
            return this.aggregate;
        }

        @NotNull
        public final Class<T> component2() {
            return this.eventClass;
        }

        @Nullable
        public final Function1<T, Boolean> component3() {
            return (Function1<? super T, Boolean>) this.predicate;
        }

        @NotNull
        public final Builder<T> copy(@NotNull AggregateEventProcessor aggregate, @NotNull Class<T> eventClass, @Nullable Function1<? super T, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(aggregate, "aggregate");
            Intrinsics.checkParameterIsNotNull(eventClass, "eventClass");
            return new Builder<>(aggregate, eventClass, function1);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Builder)) {
                    return false;
                }
                Builder builder = (Builder) obj;
                return Intrinsics.areEqual(this.aggregate, builder.aggregate) && Intrinsics.areEqual(this.eventClass, builder.eventClass) && Intrinsics.areEqual(this.predicate, builder.predicate);
            }
            return true;
        }

        @NotNull
        public final AggregateEventProcessor getAggregate() {
            return this.aggregate;
        }

        @NotNull
        public final Class<T> getEventClass() {
            return this.eventClass;
        }

        @Nullable
        public final Function1<T, Boolean> getPredicate() {
            return (Function1<? super T, Boolean>) this.predicate;
        }

        @NotNull
        public final <U extends T> Builder<U> hasType(@NotNull Class<U> eventClass) {
            Intrinsics.checkParameterIsNotNull(eventClass, "eventClass");
            return new Builder<>(this.aggregate, eventClass, this.predicate);
        }

        public int hashCode() {
            AggregateEventProcessor aggregateEventProcessor = this.aggregate;
            int i = 0;
            int hashCode = (aggregateEventProcessor != null ? aggregateEventProcessor.hashCode() : 0) * 31;
            Class<T> cls = this.eventClass;
            int hashCode2 = (hashCode + (cls != null ? cls.hashCode() : 0)) * 31;
            Function1<? super T, Boolean> function1 = this.predicate;
            if (function1 != null) {
                i = function1.hashCode();
            }
            return hashCode2 + i;
        }

        /* JADX WARN: Incorrect types in method signature: <U::TT;>(TU;)Lcom/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder<TU;>; */
        @NotNull
        public final Builder isEqualTo(@NotNull TtaEvent item) {
            Function1<? super U, Boolean> aggregateEventProcessor$Builder$isEqualTo$$inlined$where$2;
            Intrinsics.checkParameterIsNotNull(item, "item");
            Builder<U> hasType = hasType(item.getClass());
            Function1<U, Boolean> predicate = hasType.getPredicate();
            if (predicate == null) {
                aggregateEventProcessor$Builder$isEqualTo$$inlined$where$2 = new AggregateEventProcessor$Builder$isEqualTo$$inlined$where$1(item);
            } else {
                aggregateEventProcessor$Builder$isEqualTo$$inlined$where$2 = new AggregateEventProcessor$Builder$isEqualTo$$inlined$where$2(predicate, item);
            }
            hasType.setPredicate(aggregateEventProcessor$Builder$isEqualTo$$inlined$where$2);
            return hasType;
        }

        @NotNull
        public final Builder<T> isOneOf(@NotNull T... items) {
            Function1<? super T, Boolean> aggregateEventProcessor$Builder$isOneOf$$inlined$where$2;
            Intrinsics.checkParameterIsNotNull(items, "items");
            Function1<T, Boolean> predicate = getPredicate();
            if (predicate == null) {
                aggregateEventProcessor$Builder$isOneOf$$inlined$where$2 = new AggregateEventProcessor$Builder$isOneOf$$inlined$where$1(items);
            } else {
                aggregateEventProcessor$Builder$isOneOf$$inlined$where$2 = new AggregateEventProcessor$Builder$isOneOf$$inlined$where$2(predicate, items);
            }
            setPredicate(aggregateEventProcessor$Builder$isOneOf$$inlined$where$2);
            return this;
        }

        public final void setPredicate(@Nullable Function1<? super T, Boolean> function1) {
            this.predicate = function1;
        }

        @NotNull
        public final AggregateEventProcessor thenDo(@NotNull final Function2<? super T, ? super EventTime, Unit> action) {
            Intrinsics.checkParameterIsNotNull(action, "action");
            final Class<T> eventClass = getEventClass();
            final Function1<T, Boolean> predicate = getPredicate();
            if (predicate == null) {
                predicate = AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE;
            }
            return getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor$Builder$thenDo$1
                @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
                public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                    Intrinsics.checkParameterIsNotNull(event, "event");
                    Intrinsics.checkParameterIsNotNull(time, "time");
                    if (!eventClass.isInstance(event)) {
                        return false;
                    }
                    Object cast = eventClass.cast(event);
                    if (cast == null) {
                        Intrinsics.throwNpe();
                    }
                    TtaEvent ttaEvent = (TtaEvent) cast;
                    if (!((Boolean) predicate.mo12165invoke(ttaEvent)).booleanValue()) {
                        return false;
                    }
                    action.mo12248invoke(ttaEvent, time);
                    return true;
                }
            });
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Builder(aggregate=");
            outline107.append(this.aggregate);
            outline107.append(", eventClass=");
            outline107.append(this.eventClass);
            outline107.append(", predicate=");
            outline107.append(this.predicate);
            outline107.append(")");
            return outline107.toString();
        }

        @NotNull
        public final Builder<T> where(@NotNull Function1<? super T, Boolean> predicate) {
            Function1<? super T, Boolean> aggregateEventProcessor$Builder$where$$inlined$apply$lambda$2;
            Intrinsics.checkParameterIsNotNull(predicate, "predicate");
            Function1<T, Boolean> predicate2 = getPredicate();
            if (predicate2 == null) {
                aggregateEventProcessor$Builder$where$$inlined$apply$lambda$2 = new AggregateEventProcessor$Builder$where$$inlined$apply$lambda$1(predicate);
            } else {
                aggregateEventProcessor$Builder$where$$inlined$apply$lambda$2 = new AggregateEventProcessor$Builder$where$$inlined$apply$lambda$2(predicate2, predicate);
            }
            setPredicate(aggregateEventProcessor$Builder$where$$inlined$apply$lambda$2);
            return this;
        }

        public /* synthetic */ Builder(AggregateEventProcessor aggregateEventProcessor, Class cls, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(aggregateEventProcessor, cls, (i & 4) != 0 ? null : function1);
        }

        @NotNull
        public final AggregateEventProcessor thenDo(@NotNull final OnEvent<T> action) {
            Intrinsics.checkParameterIsNotNull(action, "action");
            final Class<T> eventClass = getEventClass();
            final Function1<T, Boolean> predicate = getPredicate();
            if (predicate == null) {
                predicate = AggregateEventProcessor$Builder$thenDo$predicate$1.INSTANCE;
            }
            return getAggregate().add(new MetricEventProcessor() { // from class: com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor$Builder$thenDo$$inlined$thenDo$1
                @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
                public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
                    Intrinsics.checkParameterIsNotNull(event, "event");
                    Intrinsics.checkParameterIsNotNull(time, "time");
                    if (!eventClass.isInstance(event)) {
                        return false;
                    }
                    Object cast = eventClass.cast(event);
                    if (cast == null) {
                        Intrinsics.throwNpe();
                    }
                    TtaEvent ttaEvent = (TtaEvent) cast;
                    if (!((Boolean) predicate.mo12165invoke(ttaEvent)).booleanValue()) {
                        return false;
                    }
                    action.onEvent(ttaEvent, time);
                    return true;
                }
            });
        }

        @NotNull
        public final /* synthetic */ <U extends T> Builder<U> hasType() {
            Intrinsics.reifiedOperationMarker(4, "U");
            return hasType(TtaEvent.class);
        }
    }

    public AggregateEventProcessor(@NotNull List<MetricEventProcessor> childProcessors) {
        Intrinsics.checkParameterIsNotNull(childProcessors, "childProcessors");
        this.childProcessors = childProcessors;
    }

    @NotNull
    public final AggregateEventProcessor add(@NotNull MetricEventProcessor child) {
        Intrinsics.checkParameterIsNotNull(child, "child");
        this.childProcessors.add(child);
        return this;
    }

    @NotNull
    public final <E extends TtaEvent> AggregateEventProcessor addTyped(@NotNull Class<E> eventClass, @NotNull TypedEventProcessor.Listener<E> child) {
        Intrinsics.checkParameterIsNotNull(eventClass, "eventClass");
        Intrinsics.checkParameterIsNotNull(child, "child");
        TypedEventProcessor create = TypedEventProcessor.create(eventClass, child);
        Intrinsics.checkExpressionValueIsNotNull(create, "TypedEventProcessor.create(eventClass, child)");
        return add(create);
    }

    @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
    public boolean processEvent(@NotNull TtaEvent event, @NotNull EventTime time) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(time, "time");
        boolean z = false;
        for (MetricEventProcessor metricEventProcessor : this.childProcessors) {
            z |= metricEventProcessor.processEvent(event, time);
        }
        return z;
    }

    @NotNull
    public final Builder<TtaEvent> whenEvent() {
        return new Builder<>(this, TtaEvent.class, null, 4, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AggregateEventProcessor(@org.jetbrains.annotations.NotNull com.amazon.alexa.voice.tta.metrics.MetricEventProcessor... r2) {
        /*
            r1 = this;
            java.lang.String r0 = "childProcessors"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r0)
            java.util.List r2 = kotlin.collections.ArraysKt.toMutableList(r2)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor.<init>(com.amazon.alexa.voice.tta.metrics.MetricEventProcessor[]):void");
    }
}
