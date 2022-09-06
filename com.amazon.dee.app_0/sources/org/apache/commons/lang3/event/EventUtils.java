package org.apache.commons.lang3.event;

import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.reflect.MethodUtils;
/* loaded from: classes4.dex */
public class EventUtils {

    /* loaded from: classes4.dex */
    private static class EventBindingInvocationHandler implements InvocationHandler {
        private final Set<String> eventTypes;
        private final String methodName;
        private final Object target;

        EventBindingInvocationHandler(Object obj, String str, String[] strArr) {
            this.target = obj;
            this.methodName = str;
            this.eventTypes = new HashSet(Arrays.asList(strArr));
        }

        private boolean hasMatchingParametersMethod(Method method) {
            return MethodUtils.getAccessibleMethod(this.target.getClass(), this.methodName, method.getParameterTypes()) != null;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (this.eventTypes.isEmpty() || this.eventTypes.contains(method.getName())) {
                if (hasMatchingParametersMethod(method)) {
                    return MethodUtils.invokeMethod(this.target, this.methodName, objArr);
                }
                return MethodUtils.invokeMethod(this.target, this.methodName);
            }
            return null;
        }
    }

    public static <L> void addEventListener(Object obj, Class<L> cls, L l) {
        try {
            MethodUtils.invokeMethod(obj, BulkOperationType.add + cls.getSimpleName(), l);
        } catch (IllegalAccessException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Class ");
            outline107.append(obj.getClass().getName());
            outline107.append(" does not have an accessible add");
            outline107.append(cls.getSimpleName());
            outline107.append(" method which takes a parameter of type ");
            outline107.append(cls.getName());
            outline107.append(".");
            throw new IllegalArgumentException(outline107.toString());
        } catch (NoSuchMethodException unused2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Class ");
            outline1072.append(obj.getClass().getName());
            outline1072.append(" does not have a public add");
            outline1072.append(cls.getSimpleName());
            outline1072.append(" method which takes a parameter of type ");
            outline1072.append(cls.getName());
            outline1072.append(".");
            throw new IllegalArgumentException(outline1072.toString());
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to add listener.", e.getCause());
        }
    }

    public static <L> void bindEventsToMethod(Object obj, String str, Object obj2, Class<L> cls, String... strArr) {
        addEventListener(obj2, cls, cls.cast(Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{cls}, new EventBindingInvocationHandler(obj, str, strArr))));
    }
}
