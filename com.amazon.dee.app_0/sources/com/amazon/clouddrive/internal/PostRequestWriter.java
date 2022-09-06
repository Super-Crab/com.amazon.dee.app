package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import java.io.OutputStream;
/* loaded from: classes11.dex */
interface PostRequestWriter extends RequestPropertyWriter {
    void writeRequest(OutputStream outputStream) throws CloudDriveException, InterruptedException;
}
