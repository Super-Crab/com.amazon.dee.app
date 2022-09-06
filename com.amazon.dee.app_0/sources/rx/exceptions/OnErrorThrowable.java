package rx.exceptions;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.Set;
import rx.plugins.RxJavaPlugins;
/* loaded from: classes5.dex */
public final class OnErrorThrowable extends RuntimeException {
    private static final long serialVersionUID = -569558213262703934L;
    private final boolean hasValue;
    private final Object value;

    /* loaded from: classes5.dex */
    public static class OnNextValue extends RuntimeException {
        private static final long serialVersionUID = -3454462756050397899L;
        private final Object value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes5.dex */
        public static final class Primitives {
            static final Set<Class<?>> INSTANCE = create();

            Primitives() {
            }

            private static Set<Class<?>> create() {
                HashSet hashSet = new HashSet();
                hashSet.add(Boolean.class);
                hashSet.add(Character.class);
                hashSet.add(Byte.class);
                hashSet.add(Short.class);
                hashSet.add(Integer.class);
                hashSet.add(Long.class);
                hashSet.add(Float.class);
                hashSet.add(Double.class);
                return hashSet;
            }
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public OnNextValue(java.lang.Object r3) {
            /*
                r2 = this;
                java.lang.String r0 = "OnError while emitting onNext value: "
                java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
                java.lang.String r1 = renderValue(r3)
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r2.<init>(r0)
                r2.value = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.exceptions.OnErrorThrowable.OnNextValue.<init>(java.lang.Object):void");
        }

        static String renderValue(Object obj) {
            if (obj == null) {
                return "null";
            }
            if (Primitives.INSTANCE.contains(obj.getClass())) {
                return obj.toString();
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof Enum) {
                return ((Enum) obj).name();
            }
            String handleOnNextValueRendering = RxJavaPlugins.getInstance().getErrorHandler().handleOnNextValueRendering(obj);
            return handleOnNextValueRendering != null ? handleOnNextValueRendering : GeneratedOutlineSupport1.outline46(obj, new StringBuilder(), ".class");
        }

        public Object getValue() {
            return this.value;
        }
    }

    private OnErrorThrowable(Throwable th) {
        super(th);
        this.hasValue = false;
        this.value = null;
    }

    public static Throwable addValueAsLastCause(Throwable th, Object obj) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable finalCause = Exceptions.getFinalCause(th);
        if (!(finalCause instanceof OnNextValue) || ((OnNextValue) finalCause).getValue() != obj) {
            Exceptions.addCause(th, new OnNextValue(obj));
            return th;
        }
        return th;
    }

    public static OnErrorThrowable from(Throwable th) {
        if (th == null) {
            th = new NullPointerException();
        }
        Throwable finalCause = Exceptions.getFinalCause(th);
        if (finalCause instanceof OnNextValue) {
            return new OnErrorThrowable(th, ((OnNextValue) finalCause).getValue());
        }
        return new OnErrorThrowable(th);
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isValueNull() {
        return this.hasValue;
    }

    private OnErrorThrowable(Throwable th, Object obj) {
        super(th);
        this.hasValue = true;
        this.value = obj;
    }
}
