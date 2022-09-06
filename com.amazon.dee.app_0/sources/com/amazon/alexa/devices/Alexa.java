package com.amazon.alexa.devices;
/* loaded from: classes6.dex */
public interface Alexa {

    /* loaded from: classes6.dex */
    public interface ComponentCallback<T> {
        void onFailure(AlexaException alexaException);

        void onSuccess(T t) throws AlexaException;
    }

    /* loaded from: classes6.dex */
    public interface UnrecoverableExceptionHandler {
        void handleException(AlexaException alexaException);
    }

    void destroy();

    <T> T getComponent(Class<T> cls, ComponentCallback<T> componentCallback);

    boolean isConnected();

    void setExceptionHandler(UnrecoverableExceptionHandler unrecoverableExceptionHandler);
}
