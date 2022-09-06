package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import com.amazon.CoralAndroidClient.Model.StructureValue;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class ClientRequest {
    public static final String ANONYMOUS_OPERATION = "AnonymousOperation";
    private String mOperationName;
    private StructureValue mRequestParameter;
    private String mRequestParameterString;
    private Map<String, String> mAdditionalHeaders = new HashMap();
    private String mResAddress = "";

    public ClientRequest(String str, StructureValue structureValue) {
        setOperationName(str);
        setRequestParameter(structureValue);
    }

    public void addHeader(String str, String str2) throws NativeException {
        if (str != null && str2 != null) {
            this.mAdditionalHeaders.put(str, str2);
            return;
        }
        throw new NativeException("headerName or value is null.");
    }

    public Map<String, String> getHeaders() {
        return new HashMap(this.mAdditionalHeaders);
    }

    public String getOperationName() {
        return this.mOperationName;
    }

    public StructureValue getRequestParameter() {
        return this.mRequestParameter;
    }

    public void removeHeader(String str) {
        if (str == null) {
            return;
        }
        this.mAdditionalHeaders.remove(str);
    }

    public void setOperationName(String str) {
        if (Helper.isStringNullOrEmpty(str)) {
            str = ANONYMOUS_OPERATION;
        }
        this.mOperationName = str;
    }

    public void setRequestParameter(StructureValue structureValue) {
        if (structureValue == null) {
            this.mRequestParameter = new StructureValue();
            return;
        }
        this.mRequestParameter = structureValue;
        this.mRequestParameterString = null;
    }

    public String toJson() throws NativeException {
        StructureValue structureValue = this.mRequestParameter;
        if (structureValue == null) {
            String str = this.mRequestParameterString;
            return str != null ? str : "{}";
        }
        return structureValue.toJsonObject().toString();
    }

    public ClientRequest(String str, String str2) {
        setOperationName(str);
        this.mRequestParameterString = str2;
        this.mRequestParameter = null;
    }
}
