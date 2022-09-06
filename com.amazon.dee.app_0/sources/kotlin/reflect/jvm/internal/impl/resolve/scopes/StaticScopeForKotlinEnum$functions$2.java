package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StaticScopeForKotlinEnum.kt */
/* loaded from: classes4.dex */
public final class StaticScopeForKotlinEnum$functions$2 extends Lambda implements Function0<List<? extends SimpleFunctionDescriptor>> {
    final /* synthetic */ StaticScopeForKotlinEnum this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StaticScopeForKotlinEnum$functions$2(StaticScopeForKotlinEnum staticScopeForKotlinEnum) {
        super(0);
        this.this$0 = staticScopeForKotlinEnum;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final List<? extends SimpleFunctionDescriptor> mo12560invoke() {
        ClassDescriptor classDescriptor;
        ClassDescriptor classDescriptor2;
        List<? extends SimpleFunctionDescriptor> listOf;
        classDescriptor = this.this$0.containingClass;
        classDescriptor2 = this.this$0.containingClass;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new SimpleFunctionDescriptor[]{DescriptorFactory.createEnumValueOfMethod(classDescriptor), DescriptorFactory.createEnumValuesMethod(classDescriptor2)});
        return listOf;
    }
}
