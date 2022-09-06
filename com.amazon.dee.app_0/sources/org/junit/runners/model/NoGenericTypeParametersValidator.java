package org.junit.runners.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
/* loaded from: classes5.dex */
class NoGenericTypeParametersValidator {
    private final Method method;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NoGenericTypeParametersValidator(Method method) {
        this.method = method;
    }

    private void validateNoTypeParameterOnGenericArrayType(GenericArrayType genericArrayType, List<Throwable> list) {
        validateNoTypeParameterOnType(genericArrayType.getGenericComponentType(), list);
    }

    private void validateNoTypeParameterOnParameterizedType(ParameterizedType parameterizedType, List<Throwable> list) {
        for (Type type : parameterizedType.getActualTypeArguments()) {
            validateNoTypeParameterOnType(type, list);
        }
    }

    private void validateNoTypeParameterOnType(Type type, List<Throwable> list) {
        if (type instanceof TypeVariable) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Method ");
            outline107.append(this.method.getName());
            outline107.append("() contains unresolved type variable ");
            outline107.append(type);
            list.add(new Exception(outline107.toString()));
        } else if (type instanceof ParameterizedType) {
            validateNoTypeParameterOnParameterizedType((ParameterizedType) type, list);
        } else if (type instanceof WildcardType) {
            validateNoTypeParameterOnWildcardType((WildcardType) type, list);
        } else if (!(type instanceof GenericArrayType)) {
        } else {
            validateNoTypeParameterOnGenericArrayType((GenericArrayType) type, list);
        }
    }

    private void validateNoTypeParameterOnWildcardType(WildcardType wildcardType, List<Throwable> list) {
        for (Type type : wildcardType.getUpperBounds()) {
            validateNoTypeParameterOnType(type, list);
        }
        for (Type type2 : wildcardType.getLowerBounds()) {
            validateNoTypeParameterOnType(type2, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void validate(List<Throwable> list) {
        for (Type type : this.method.getGenericParameterTypes()) {
            validateNoTypeParameterOnType(type, list);
        }
    }
}
