package kotlin.reflect.jvm.internal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KCallableImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0006\b\u0000\u0010\u0003 \u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lkotlin/reflect/KParameter;", "R", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class KCallableImpl$_parameters$1 extends Lambda implements Function0<ArrayList<KParameter>> {
    final /* synthetic */ KCallableImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: KCallableImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "R", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlin.reflect.jvm.internal.KCallableImpl$_parameters$1$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<ReceiverParameterDescriptor> {
        final /* synthetic */ ReceiverParameterDescriptor $instanceReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ReceiverParameterDescriptor receiverParameterDescriptor) {
            super(0);
            this.$instanceReceiver = receiverParameterDescriptor;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final ReceiverParameterDescriptor mo12560invoke() {
            return this.$instanceReceiver;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: KCallableImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002 \u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/ReceiverParameterDescriptor;", "R", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlin.reflect.jvm.internal.KCallableImpl$_parameters$1$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass2 extends Lambda implements Function0<ReceiverParameterDescriptor> {
        final /* synthetic */ ReceiverParameterDescriptor $extensionReceiver;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ReceiverParameterDescriptor receiverParameterDescriptor) {
            super(0);
            this.$extensionReceiver = receiverParameterDescriptor;
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* renamed from: invoke  reason: collision with other method in class */
        public final ReceiverParameterDescriptor mo12560invoke() {
            return this.$extensionReceiver;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: KCallableImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\u0006\b\u0000\u0010\u0003 \u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlin/reflect/jvm/internal/impl/descriptors/ValueParameterDescriptor;", "kotlin.jvm.PlatformType", "R", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: kotlin.reflect.jvm.internal.KCallableImpl$_parameters$1$3  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static final class AnonymousClass3 extends Lambda implements Function0<ValueParameterDescriptor> {
        final /* synthetic */ CallableMemberDescriptor $descriptor;
        final /* synthetic */ int $i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(CallableMemberDescriptor callableMemberDescriptor, int i) {
            super(0);
            this.$descriptor = callableMemberDescriptor;
            this.$i = i;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: collision with other method in class */
        public final ValueParameterDescriptor mo12560invoke() {
            ValueParameterDescriptor valueParameterDescriptor = this.$descriptor.getValueParameters().get(this.$i);
            Intrinsics.checkExpressionValueIsNotNull(valueParameterDescriptor, "descriptor.valueParameters[i]");
            return valueParameterDescriptor;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KCallableImpl$_parameters$1(KCallableImpl kCallableImpl) {
        super(0);
        this.this$0 = kCallableImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final ArrayList<KParameter> mo12560invoke() {
        int i;
        CallableMemberDescriptor mo11480getDescriptor = this.this$0.mo11480getDescriptor();
        ArrayList<KParameter> arrayList = new ArrayList<>();
        int i2 = 0;
        if (!this.this$0.isBound()) {
            ReceiverParameterDescriptor instanceReceiverParameter = UtilKt.getInstanceReceiverParameter(mo11480getDescriptor);
            if (instanceReceiverParameter != null) {
                arrayList.add(new KParameterImpl(this.this$0, 0, KParameter.Kind.INSTANCE, new AnonymousClass1(instanceReceiverParameter)));
                i = 1;
            } else {
                i = 0;
            }
            ReceiverParameterDescriptor extensionReceiverParameter = mo11480getDescriptor.getExtensionReceiverParameter();
            if (extensionReceiverParameter != null) {
                arrayList.add(new KParameterImpl(this.this$0, i, KParameter.Kind.EXTENSION_RECEIVER, new AnonymousClass2(extensionReceiverParameter)));
                i++;
            }
        } else {
            i = 0;
        }
        List<ValueParameterDescriptor> valueParameters = mo11480getDescriptor.getValueParameters();
        Intrinsics.checkExpressionValueIsNotNull(valueParameters, "descriptor.valueParameters");
        int size = valueParameters.size();
        while (i2 < size) {
            arrayList.add(new KParameterImpl(this.this$0, i, KParameter.Kind.VALUE, new AnonymousClass3(mo11480getDescriptor, i2)));
            i2++;
            i++;
        }
        if (this.this$0.isAnnotationConstructor() && (mo11480getDescriptor instanceof JavaCallableMemberDescriptor) && arrayList.size() > 1) {
            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList, new Comparator<T>() { // from class: kotlin.reflect.jvm.internal.KCallableImpl$_parameters$1$$special$$inlined$sortBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int compareValues;
                    compareValues = ComparisonsKt__ComparisonsKt.compareValues(((KParameter) t).getName(), ((KParameter) t2).getName());
                    return compareValues;
                }
            });
        }
        arrayList.trimToSize();
        return arrayList;
    }
}
