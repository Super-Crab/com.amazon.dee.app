package com.amazon.alexa.accessory.internal.http;

import com.amazon.alexa.accessory.internal.http.HttpResponse;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.io.OutputStreamSink;
import com.amazon.alexa.accessory.io.Source;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public final class FileHttpResponseFactory implements HttpResponse.Factory<File> {
    private final File file;

    public FileHttpResponseFactory(File file) {
        this.file = file;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessory.internal.http.HttpResponse.Factory
    /* renamed from: create */
    public File mo309create(Source source) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(this.file);
        try {
            IOUtils.transfer(source, new OutputStreamSink(fileOutputStream));
            fileOutputStream.close();
            return this.file;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    fileOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }
}
