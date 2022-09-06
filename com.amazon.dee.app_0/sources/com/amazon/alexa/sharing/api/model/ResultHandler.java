package com.amazon.alexa.sharing.api.model;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.sharing.repo.models.Result;
import com.amazon.commsnetworking.api.INetworkResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class ResultHandler {
    private final String sourceClass;

    public ResultHandler(@NonNull String str) {
        this.sourceClass = str;
    }

    public Result exceptionOccurredResponse(String str) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorMessage(GeneratedOutlineSupport1.outline93(new StringBuilder(), this.sourceClass, ".", str, " not successful"));
        return result;
    }

    public Result processNetworkResponse(INetworkResponse iNetworkResponse, String str) {
        Result result = new Result();
        if (iNetworkResponse != null && iNetworkResponse.isSuccessful()) {
            result.setSuccess(true);
        } else {
            Log.e(this.sourceClass + "." + str, "was not successful");
            result.setSuccess(false);
            result.setErrorMessage(GeneratedOutlineSupport1.outline93(new StringBuilder(), this.sourceClass, ".", str, " not successful"));
        }
        return result;
    }
}
