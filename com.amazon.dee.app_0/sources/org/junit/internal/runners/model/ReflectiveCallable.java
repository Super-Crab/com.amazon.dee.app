package org.junit.internal.runners.model;

import java.lang.reflect.InvocationTargetException;
/* loaded from: classes5.dex */
public abstract class ReflectiveCallable {
    public Object run() throws Throwable {
        try {
            return runReflectiveCall();
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    protected abstract Object runReflectiveCall() throws Throwable;
}
