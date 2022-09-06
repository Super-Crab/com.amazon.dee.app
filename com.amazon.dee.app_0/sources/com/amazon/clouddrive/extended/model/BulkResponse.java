package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import java.util.Map;
/* loaded from: classes11.dex */
public interface BulkResponse extends CloudDriveResponse {
    Map<String, Integer> getErrorMap();

    String getMessage();

    void setErrorMap(Map<String, Integer> map);

    void setMessage(String str);
}
