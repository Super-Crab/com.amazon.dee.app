package com.amazon.android.codahalemetricreporter.impl;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.amazon.android.codahalemetricreporter.impl.IRemoteFile;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public class ReportProvider {
    private static final String FILE_NAME_PREFIX = "com.amazon.android.codahalemetricreporter.report.";
    private final ReportGenerator mGenerator;
    private final RemoteFile mRemoteFile;

    /* loaded from: classes11.dex */
    private static class RemoteFile extends IRemoteFile.Stub {
        private final File mFile;

        public RemoteFile(File file) {
            this.mFile = file;
        }

        @Override // com.amazon.android.codahalemetricreporter.impl.IRemoteFile
        public ParcelFileDescriptor getFd() throws RemoteException {
            try {
                return ParcelFileDescriptor.open(this.mFile, 268435456);
            } catch (IOException e) {
                throw new RemoteException(e.getMessage());
            }
        }

        public File getFile() {
            return this.mFile;
        }
    }

    public ReportProvider(Context context, String str, ReportGenerator reportGenerator) {
        File cacheDir = context.getCacheDir();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(FILE_NAME_PREFIX);
        outline107.append(suffix(str));
        this.mRemoteFile = new RemoteFile(new File(cacheDir, outline107.toString()));
        this.mGenerator = reportGenerator;
    }

    private static OutputStream fileOutput(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to create directory: ");
            outline107.append(parentFile.toString());
            throw new IOException(outline107.toString());
        }
        return new FileOutputStream(file);
    }

    private static String suffix(String str) {
        return str.replace(JsonReaderKt.COLON, '_').replace('/', '_');
    }

    public IRemoteFile.Stub generate(MetricRegistry metricRegistry, MetricFilter metricFilter) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutput(this.mRemoteFile.getFile()));
        try {
            this.mGenerator.generate(metricRegistry, metricFilter, bufferedOutputStream);
            bufferedOutputStream.close();
            return this.mRemoteFile;
        } catch (Throwable th) {
            bufferedOutputStream.close();
            throw th;
        }
    }
}
