package kotlin.reflect.jvm.internal.impl.types;

import java.util.Collection;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.model.SimpleTypeMarker;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractTypeChecker.kt */
/* loaded from: classes4.dex */
public final class AbstractTypeChecker$checkSubtypeForIntegerLiteralType$1 extends Lambda implements Function3<SimpleTypeMarker, SimpleTypeMarker, Boolean, Boolean> {
    final /* synthetic */ AbstractTypeCheckerContext $this_checkSubtypeForIntegerLiteralType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractTypeChecker$checkSubtypeForIntegerLiteralType$1(AbstractTypeCheckerContext abstractTypeCheckerContext) {
        super(3);
        this.$this_checkSubtypeForIntegerLiteralType = abstractTypeCheckerContext;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Boolean invoke(SimpleTypeMarker simpleTypeMarker, SimpleTypeMarker simpleTypeMarker2, Boolean bool) {
        return Boolean.valueOf(invoke(simpleTypeMarker, simpleTypeMarker2, bool.booleanValue()));
    }

    public final boolean invoke(@NotNull SimpleTypeMarker integerLiteralType, @NotNull SimpleTypeMarker type, boolean z) {
        boolean z2;
        Intrinsics.checkParameterIsNotNull(integerLiteralType, "integerLiteralType");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Collection<KotlinTypeMarker> possibleIntegerTypes = this.$this_checkSubtypeForIntegerLiteralType.possibleIntegerTypes(integerLiteralType);
        if (!(possibleIntegerTypes instanceof Collection) || !possibleIntegerTypes.isEmpty()) {
            for (KotlinTypeMarker kotlinTypeMarker : possibleIntegerTypes) {
                if (Intrinsics.areEqual(this.$this_checkSubtypeForIntegerLiteralType.typeConstructor(kotlinTypeMarker), this.$this_checkSubtypeForIntegerLiteralType.typeConstructor(type)) || (z && AbstractTypeChecker.INSTANCE.isSubtypeOf(this.$this_checkSubtypeForIntegerLiteralType, type, kotlinTypeMarker))) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (z2) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
