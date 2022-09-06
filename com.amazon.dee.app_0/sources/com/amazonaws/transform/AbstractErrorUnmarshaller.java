package com.amazonaws.transform;

import com.amazonaws.AmazonServiceException;
/* loaded from: classes13.dex */
public abstract class AbstractErrorUnmarshaller<T> implements Unmarshaller<AmazonServiceException, T> {
    protected final Class<? extends AmazonServiceException> exceptionClass;

    public AbstractErrorUnmarshaller() {
        this(AmazonServiceException.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AmazonServiceException newException(String str) throws Exception {
        return this.exceptionClass.getConstructor(String.class).newInstance(str);
    }

    public AbstractErrorUnmarshaller(Class<? extends AmazonServiceException> cls) {
        this.exceptionClass = cls;
    }
}
