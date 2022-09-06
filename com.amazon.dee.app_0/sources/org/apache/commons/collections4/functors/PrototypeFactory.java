package org.apache.commons.collections4.functors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.collections4.Factory;
import org.apache.commons.collections4.FunctorException;
/* loaded from: classes4.dex */
public class PrototypeFactory {

    /* loaded from: classes4.dex */
    static class PrototypeCloneFactory<T> implements Factory<T> {
        private transient Method iCloneMethod;
        private final T iPrototype;

        private void findCloneMethod() {
            try {
                this.iCloneMethod = this.iPrototype.getClass().getMethod("clone", null);
            } catch (NoSuchMethodException unused) {
                throw new IllegalArgumentException("PrototypeCloneFactory: The clone method must exist and be public ");
            }
        }

        @Override // org.apache.commons.collections4.Factory
        /* renamed from: create */
        public T mo12724create() {
            if (this.iCloneMethod == null) {
                findCloneMethod();
            }
            try {
                return (T) this.iCloneMethod.invoke(this.iPrototype, null);
            } catch (IllegalAccessException e) {
                throw new FunctorException("PrototypeCloneFactory: Clone method must be public", e);
            } catch (InvocationTargetException e2) {
                throw new FunctorException("PrototypeCloneFactory: Clone method threw an exception", e2);
            }
        }

        private PrototypeCloneFactory(T t, Method method) {
            this.iPrototype = t;
            this.iCloneMethod = method;
        }
    }

    /* loaded from: classes4.dex */
    static class PrototypeSerializationFactory<T extends Serializable> implements Factory<T> {
        private final T iPrototype;

        private PrototypeSerializationFactory(T t) {
            this.iPrototype = t;
        }

        @Override // org.apache.commons.collections4.Factory
        /* renamed from: create */
        public T mo12724create() {
            ByteArrayInputStream byteArrayInputStream;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
            ByteArrayInputStream byteArrayInputStream2 = null;
            try {
                try {
                    new ObjectOutputStream(byteArrayOutputStream).writeObject(this.iPrototype);
                    byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            } catch (ClassNotFoundException e2) {
                e = e2;
            }
            try {
                T t = (T) new ObjectInputStream(byteArrayInputStream).readObject();
                try {
                    byteArrayInputStream.close();
                } catch (IOException unused) {
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused2) {
                }
                return t;
            } catch (IOException e3) {
                e = e3;
                throw new FunctorException(e);
            } catch (ClassNotFoundException e4) {
                e = e4;
                throw new FunctorException(e);
            } catch (Throwable th2) {
                th = th2;
                byteArrayInputStream2 = byteArrayInputStream;
                if (byteArrayInputStream2 != null) {
                    try {
                        byteArrayInputStream2.close();
                    } catch (IOException unused3) {
                    }
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused4) {
                }
                throw th;
            }
        }
    }

    private PrototypeFactory() {
    }

    public static <T> Factory<T> prototypeFactory(T t) {
        if (t == null) {
            return ConstantFactory.constantFactory(null);
        }
        try {
            try {
                return new PrototypeCloneFactory(t, t.getClass().getMethod("clone", null));
            } catch (NoSuchMethodException unused) {
                t.getClass().getConstructor(t.getClass());
                return new InstantiateFactory(t.getClass(), new Class[]{t.getClass()}, new Object[]{t});
            }
        } catch (NoSuchMethodException unused2) {
            if (t instanceof Serializable) {
                return new PrototypeSerializationFactory((Serializable) t);
            }
            throw new IllegalArgumentException("The prototype must be cloneable via a public clone method");
        }
    }
}
