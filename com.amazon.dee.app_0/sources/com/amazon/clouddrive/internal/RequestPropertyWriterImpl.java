package com.amazon.clouddrive.internal;

import com.amazon.clouddrive.exceptions.CloudDriveException;
import com.amazon.clouddrive.exceptions.InvalidParameter;
import com.amazon.clouddrive.internal.utils.ThreadUtil;
import com.google.common.net.HttpHeaders;
import java.net.HttpURLConnection;
import java.util.LinkedHashSet;
import java.util.Set;
import okhttp3.Request;
/* loaded from: classes11.dex */
class RequestPropertyWriterImpl implements RequestPropertyWriter {
    private final Set<Property> mRequestProperties = new LinkedHashSet();

    /* loaded from: classes11.dex */
    private static class Property {
        final String field;
        final String value;

        public Property(String str, String str2) {
            this.field = str;
            this.value = str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setRequestProperty(String str, String str2) {
        this.mRequestProperties.add(new Property(str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setStreamingMode(HttpURLConnection httpURLConnection, boolean z, int i, long j) throws InvalidParameter {
        if (z) {
            httpURLConnection.setChunkedStreamingMode(i);
            httpURLConnection.setRequestProperty(HttpHeaders.TRANSFER_ENCODING, "chunked");
        } else if (j <= 2147483647L) {
            httpURLConnection.setFixedLengthStreamingMode((int) j);
        } else {
            throw new InvalidParameter("The file is too large to be uploaded through the fixed length streaming mode. Use the chunked mode instead.", InvalidParameter.FILE_TOO_LARGE, getClass().getSimpleName());
        }
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriter
    @Deprecated
    public void writeHeaders(HttpURLConnection httpURLConnection) throws CloudDriveException, InterruptedException {
        for (Property property : this.mRequestProperties) {
            ThreadUtil.checkIfInterrupted();
            httpURLConnection.setRequestProperty(property.field, property.value);
        }
    }

    @Override // com.amazon.clouddrive.internal.RequestPropertyWriter
    public void writeHeaders(Request.Builder builder) throws CloudDriveException, InterruptedException {
        for (Property property : this.mRequestProperties) {
            ThreadUtil.checkIfInterrupted();
            builder.header(property.field, property.value);
        }
    }
}
