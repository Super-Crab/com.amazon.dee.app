package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.ClientException;
/* loaded from: classes.dex */
public interface ResultHandler {
    void onException(ClientException clientException);

    void onSuccess(ClientOutput clientOutput);
}
