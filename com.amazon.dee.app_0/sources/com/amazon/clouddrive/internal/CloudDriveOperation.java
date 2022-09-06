package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import java.util.concurrent.Callable;
/* loaded from: classes11.dex */
public interface CloudDriveOperation<Response> extends Callable<Response> {
    @Override // java.util.concurrent.Callable
    Response call() throws CloudDriveException, InterruptedException;
}
