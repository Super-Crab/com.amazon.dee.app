package com.amazon.alexa;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.fileupload.MultipartStream;
/* compiled from: MultipartStreamParser.java */
/* loaded from: classes.dex */
public class wpU {
    public final MultipartStream zZm;

    /* compiled from: MultipartStreamParser.java */
    /* loaded from: classes.dex */
    static class zZm {
        public final MultipartStream zZm;

        public zZm(MultipartStream multipartStream) {
            this.zZm = multipartStream;
        }

        public String zZm() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.zZm.readBodyData(byteArrayOutputStream);
            return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8.name());
        }
    }

    public wpU(InputStream inputStream, String str) {
        this.zZm = new MultipartStream(inputStream, str.getBytes());
    }

    public zZm zZm() {
        return new zZm(this.zZm);
    }
}
