package org.apache.commons.collections4.functors;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.collections4.FunctorException;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class InvokerTransformer<I, O> implements Transformer<I, O> {
    private final Object[] iArgs;
    private final String iMethodName;
    private final Class<?>[] iParamTypes;

    private InvokerTransformer(String str) {
        this.iMethodName = str;
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public static <I, O> Transformer<I, O> invokerTransformer(String str) {
        if (str != null) {
            return new InvokerTransformer(str);
        }
        throw new NullPointerException("The method to invoke must not be null");
    }

    @Override // org.apache.commons.collections4.Transformer
    /* renamed from: transform */
    public O mo12738transform(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return (O) obj.getClass().getMethod(this.iMethodName, this.iParamTypes).invoke(obj, this.iArgs);
        } catch (IllegalAccessException unused) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InvokerTransformer: The method '");
            outline107.append(this.iMethodName);
            outline107.append("' on '");
            outline107.append(obj.getClass());
            outline107.append("' cannot be accessed");
            throw new FunctorException(outline107.toString());
        } catch (NoSuchMethodException unused2) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("InvokerTransformer: The method '");
            outline1072.append(this.iMethodName);
            outline1072.append("' on '");
            outline1072.append(obj.getClass());
            outline1072.append("' does not exist");
            throw new FunctorException(outline1072.toString());
        } catch (InvocationTargetException e) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("InvokerTransformer: The method '");
            outline1073.append(this.iMethodName);
            outline1073.append("' on '");
            outline1073.append(obj.getClass());
            outline1073.append("' threw an exception");
            throw new FunctorException(outline1073.toString(), e);
        }
    }

    public static <I, O> Transformer<I, O> invokerTransformer(String str, Class<?>[] clsArr, Object[] objArr) {
        if (str != null) {
            if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
                throw new IllegalArgumentException("The parameter types must match the arguments");
            }
            if (clsArr != null && clsArr.length != 0) {
                return new InvokerTransformer(str, clsArr, objArr);
            }
            return new InvokerTransformer(str);
        }
        throw new NullPointerException("The method to invoke must not be null");
    }

    public InvokerTransformer(String str, Class<?>[] clsArr, Object[] objArr) {
        this.iMethodName = str;
        Object[] objArr2 = null;
        this.iParamTypes = clsArr != null ? (Class[]) clsArr.clone() : null;
        this.iArgs = objArr != null ? (Object[]) objArr.clone() : objArr2;
    }
}
