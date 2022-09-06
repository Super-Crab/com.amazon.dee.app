package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.SerialDescriptorBuilder;
import kotlinx.serialization.internal.UtilKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonElementSerializer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/serialization/SerialDescriptorBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class JsonElementSerializer$descriptor$1 extends Lambda implements Function1<SerialDescriptorBuilder, Unit> {
    public static final JsonElementSerializer$descriptor$1 INSTANCE = new JsonElementSerializer$descriptor$1();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JsonElementSerializer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/serialization/SerialDescriptor;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlinx.serialization.json.JsonElementSerializer$descriptor$1$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<SerialDescriptor> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final SerialDescriptor mo12560invoke() {
            return JsonPrimitiveSerializer.INSTANCE.getDescriptor();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JsonElementSerializer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/serialization/SerialDescriptor;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlinx.serialization.json.JsonElementSerializer$descriptor$1$2  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass2 extends Lambda implements Function0<SerialDescriptor> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        AnonymousClass2() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final SerialDescriptor mo12560invoke() {
            return JsonNullSerializer.INSTANCE.getDescriptor();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JsonElementSerializer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/serialization/SerialDescriptor;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlinx.serialization.json.JsonElementSerializer$descriptor$1$3  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass3 extends Lambda implements Function0<SerialDescriptor> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        AnonymousClass3() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final SerialDescriptor mo12560invoke() {
            return JsonLiteralSerializer.INSTANCE.getDescriptor();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JsonElementSerializer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/serialization/SerialDescriptor;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlinx.serialization.json.JsonElementSerializer$descriptor$1$4  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass4 extends Lambda implements Function0<SerialDescriptor> {
        public static final AnonymousClass4 INSTANCE = new AnonymousClass4();

        AnonymousClass4() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final SerialDescriptor mo12560invoke() {
            return JsonObjectSerializer.INSTANCE.getDescriptor();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JsonElementSerializer.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/serialization/SerialDescriptor;", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlinx.serialization.json.JsonElementSerializer$descriptor$1$5  reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass5 extends Lambda implements Function0<SerialDescriptor> {
        public static final AnonymousClass5 INSTANCE = new AnonymousClass5();

        AnonymousClass5() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final SerialDescriptor mo12560invoke() {
            return JsonArraySerializer.INSTANCE.getDescriptor();
        }
    }

    JsonElementSerializer$descriptor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(SerialDescriptorBuilder serialDescriptorBuilder) {
        invoke2(serialDescriptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull SerialDescriptorBuilder receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SerialDescriptorBuilder.element$default(receiver, "JsonPrimitive", UtilKt.defer(AnonymousClass1.INSTANCE), null, false, 12, null);
        SerialDescriptorBuilder.element$default(receiver, "JsonNull", UtilKt.defer(AnonymousClass2.INSTANCE), null, false, 12, null);
        SerialDescriptorBuilder.element$default(receiver, "JsonLiteral", UtilKt.defer(AnonymousClass3.INSTANCE), null, false, 12, null);
        SerialDescriptorBuilder.element$default(receiver, "JsonObject", UtilKt.defer(AnonymousClass4.INSTANCE), null, false, 12, null);
        SerialDescriptorBuilder.element$default(receiver, "JsonArray", UtilKt.defer(AnonymousClass5.INSTANCE), null, false, 12, null);
    }
}
