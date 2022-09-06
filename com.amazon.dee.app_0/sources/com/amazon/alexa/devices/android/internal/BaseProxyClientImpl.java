package com.amazon.alexa.devices.android.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.Version;
import com.amazon.alexa.devices.sdk.IAlexaApiGateway;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public abstract class BaseProxyClientImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "BaseProxyClientImpl";
    private static final IBinder token = new Binder();
    private Map<String, Method> methodIntercepts = new HashMap();
    private boolean bDestroyed = false;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes6.dex */
    public @interface ComponentVersion {
        int build();

        int major();

        int minor();
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes6.dex */
    public @interface MethodIntercept {
        Class<?> clazz();

        String name();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public interface WrapperUpdater {
        void updateWrappedObject(Object obj);
    }

    public BaseProxyClientImpl() {
        Method[] declaredMethods;
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(MethodIntercept.class)) {
                this.methodIntercepts.put(((MethodIntercept) method.getAnnotation(MethodIntercept.class)).name(), method);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Class<?> getRealClass(Class<?> cls) {
        HashMap<Class, Class> hashMap = new HashMap<Class, Class>() { // from class: com.amazon.alexa.devices.android.internal.BaseProxyClientImpl.1
            {
                put(Boolean.class, Boolean.TYPE);
                put(Character.class, Character.TYPE);
                put(Byte.class, Byte.TYPE);
                put(Short.class, Short.TYPE);
                put(Integer.class, Integer.TYPE);
                put(Long.class, Long.TYPE);
                put(Float.class, Float.TYPE);
                put(Double.class, Double.TYPE);
                put(Void.class, Void.TYPE);
            }
        };
        return hashMap.containsKey(cls) ? hashMap.get(cls) : cls;
    }

    private Class<?> getStubClass(Class<?> cls) {
        Class<?>[] declaredClasses;
        for (Class<?> cls2 : cls.getDeclaredClasses()) {
            if (cls2.getName().equals(cls.getName() + "$Stub")) {
                return cls2;
            }
        }
        return null;
    }

    public abstract void connect(IAlexaApiGateway iAlexaApiGateway, Version version) throws RemoteException, AlexaException;

    protected <T> T createInterfaceProxyWrapper(Class<T> cls, Object obj) {
        return (T) createInterfaceProxyWrapper(cls, obj, this, null);
    }

    public void destroy() {
        this.bDestroyed = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T getRemoteComponent(IAlexaApiGateway iAlexaApiGateway, Class<T> cls, Version version) throws AlexaException, RemoteException {
        if (getClass().isAnnotationPresent(ComponentVersion.class)) {
            ComponentVersion componentVersion = (ComponentVersion) getClass().getAnnotation(ComponentVersion.class);
            IBinder component = iAlexaApiGateway.getComponent(token, version, new Version(componentVersion.major(), componentVersion.minor(), componentVersion.build()), cls.getName());
            T t = (T) component.queryLocalInterface(component.getInterfaceDescriptor());
            if (t != null) {
                return t;
            }
            Class<?> stubClass = getStubClass(cls);
            if (stubClass != null) {
                try {
                    return (T) stubClass.getMethod("asInterface", IBinder.class).invoke(null, component);
                } catch (Exception e) {
                    throw new AlexaException(e);
                }
            }
            throw new AlexaException("Invalid Component");
        }
        throw new AlexaException(String.format("Component Version annotation missing for class %s. Did you forget to add @ComponentVersion annotation?", getClass().getName()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateWrappedObject(Object obj, Object obj2) throws RemoteException {
        ((WrapperUpdater) obj).updateWrappedObject(obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T createInterfaceProxyWrapper(Class<T> cls, final Object obj, final Object obj2, final Map<String, String> map) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{cls, WrapperUpdater.class}, new InvocationHandler() { // from class: com.amazon.alexa.devices.android.internal.BaseProxyClientImpl.2
            static final /* synthetic */ boolean $assertionsDisabled = false;
            private Object wrappedObject;

            {
                this.wrappedObject = obj;
            }

            private Method getMethod(Object obj3, String str, Class<?>... clsArr) throws NoSuchMethodException {
                try {
                    return obj3.getClass().getMethod(str, clsArr);
                } catch (NoSuchMethodException unused) {
                    return obj3.getClass().getDeclaredMethod(str, clsArr);
                }
            }

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj3, Method method, Object[] objArr) throws Throwable {
                Method method2;
                if (obj3 == null) {
                    return method.invoke(obj3, objArr);
                }
                if (method.getName().equals("updateWrappedObject")) {
                    updateWrappedObject(objArr[0]);
                    return null;
                }
                try {
                    String name = method.getName();
                    if (BaseProxyClientImpl.this.methodIntercepts.containsKey(name)) {
                        Method method3 = (Method) BaseProxyClientImpl.this.methodIntercepts.get(name);
                        ArrayList arrayList = new ArrayList();
                        if (method3.getParameterTypes()[0].isAssignableFrom(this.wrappedObject.getClass())) {
                            arrayList.add(this.wrappedObject);
                        } else {
                            arrayList.add(obj2);
                        }
                        if (objArr != null) {
                            for (Object obj4 : objArr) {
                                arrayList.add(obj4);
                            }
                        }
                        return method3.invoke(BaseProxyClientImpl.this, arrayList.toArray());
                    }
                    if (map != null && map.containsKey(name)) {
                        name = (String) map.get(name);
                    }
                    if (objArr == null) {
                        method2 = getMethod(this.wrappedObject, name, new Class[0]);
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        for (Object obj5 : objArr) {
                            arrayList2.add(BaseProxyClientImpl.this.getRealClass(obj5.getClass()));
                        }
                        method2 = getMethod(this.wrappedObject, name, (Class[]) arrayList2.toArray(new Class[0]));
                    }
                    return method2.invoke(this.wrappedObject, objArr);
                } catch (Throwable th) {
                    th = th;
                    Log.e(BaseProxyClientImpl.TAG, "error", th);
                    if (th instanceof InvocationTargetException) {
                        th = ((InvocationTargetException) th).getTargetException();
                    }
                    if (th instanceof AlexaException) {
                        throw th;
                    }
                    throw new AlexaException(th);
                }
            }

            public void updateWrappedObject(Object obj3) {
                this.wrappedObject = obj3;
            }
        });
    }
}
