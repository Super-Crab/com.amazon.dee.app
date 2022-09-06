package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.NoRetryServiceException;
import java.io.InputStream;
/* loaded from: classes11.dex */
public interface ServiceExceptionProvider {
    CloudDriveException getCloudDriveException(InputStream inputStream, int i) throws NoRetryServiceException;
}
