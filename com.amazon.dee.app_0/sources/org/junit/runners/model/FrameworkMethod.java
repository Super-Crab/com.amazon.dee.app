package org.junit.runners.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.internal.runners.model.ReflectiveCallable;
/* loaded from: classes5.dex */
public class FrameworkMethod extends FrameworkMember<FrameworkMethod> {
    private final Method method;

    public FrameworkMethod(Method method) {
        if (method != null) {
            this.method = method;
            return;
        }
        throw new NullPointerException("FrameworkMethod cannot be created without an underlying method.");
    }

    private Class<?>[] getParameterTypes() {
        return this.method.getParameterTypes();
    }

    public boolean equals(Object obj) {
        if (!FrameworkMethod.class.isInstance(obj)) {
            return false;
        }
        return ((FrameworkMethod) obj).method.equals(this.method);
    }

    @Override // org.junit.runners.model.Annotatable
    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        return (T) this.method.getAnnotation(cls);
    }

    @Override // org.junit.runners.model.Annotatable
    public Annotation[] getAnnotations() {
        return this.method.getAnnotations();
    }

    @Override // org.junit.runners.model.FrameworkMember
    public Class<?> getDeclaringClass() {
        return this.method.getDeclaringClass();
    }

    public Method getMethod() {
        return this.method;
    }

    @Override // org.junit.runners.model.FrameworkMember
    protected int getModifiers() {
        return this.method.getModifiers();
    }

    @Override // org.junit.runners.model.FrameworkMember
    public String getName() {
        return this.method.getName();
    }

    public Class<?> getReturnType() {
        return this.method.getReturnType();
    }

    @Override // org.junit.runners.model.FrameworkMember
    public Class<?> getType() {
        return getReturnType();
    }

    public int hashCode() {
        return this.method.hashCode();
    }

    public Object invokeExplosively(final Object obj, final Object... objArr) throws Throwable {
        return new ReflectiveCallable() { // from class: org.junit.runners.model.FrameworkMethod.1
            @Override // org.junit.internal.runners.model.ReflectiveCallable
            protected Object runReflectiveCall() throws Throwable {
                return FrameworkMethod.this.method.invoke(obj, objArr);
            }
        }.run();
    }

    @Deprecated
    public boolean producesType(Type type) {
        return getParameterTypes().length == 0 && (type instanceof Class) && ((Class) type).isAssignableFrom(this.method.getReturnType());
    }

    public String toString() {
        return this.method.toString();
    }

    public void validateNoTypeParametersOnArgs(List<Throwable> list) {
        new NoGenericTypeParametersValidator(this.method).validate(list);
    }

    public void validatePublicVoid(boolean z, List<Throwable> list) {
        if (isStatic() != z) {
            String str = z ? "should" : "should not";
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Method ");
            outline107.append(this.method.getName());
            outline107.append("() ");
            outline107.append(str);
            outline107.append(" be static");
            list.add(new Exception(outline107.toString()));
        }
        if (!isPublic()) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Method ");
            outline1072.append(this.method.getName());
            outline1072.append("() should be public");
            list.add(new Exception(outline1072.toString()));
        }
        if (this.method.getReturnType() != Void.TYPE) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Method ");
            outline1073.append(this.method.getName());
            outline1073.append("() should be void");
            list.add(new Exception(outline1073.toString()));
        }
    }

    public void validatePublicVoidNoArg(boolean z, List<Throwable> list) {
        validatePublicVoid(z, list);
        if (this.method.getParameterTypes().length != 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Method ");
            outline107.append(this.method.getName());
            outline107.append(" should have no parameters");
            list.add(new Exception(outline107.toString()));
        }
    }

    @Override // org.junit.runners.model.FrameworkMember
    public boolean isShadowedBy(FrameworkMethod frameworkMethod) {
        if (frameworkMethod.getName().equals(getName()) && frameworkMethod.getParameterTypes().length == getParameterTypes().length) {
            for (int i = 0; i < frameworkMethod.getParameterTypes().length; i++) {
                if (!frameworkMethod.getParameterTypes()[i].equals(getParameterTypes()[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
