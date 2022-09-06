package com.sun.mail.util;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes3.dex */
public class LineOutputStream extends FilterOutputStream {
    private static byte[] newline = new byte[2];

    static {
        byte[] bArr = newline;
        bArr[0] = 13;
        bArr[1] = 10;
    }

    public LineOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeln(String str) throws IOException {
        ((FilterOutputStream) this).out.write(ASCIIUtility.getBytes(str));
        ((FilterOutputStream) this).out.write(newline);
    }

    public void writeln() throws IOException {
        ((FilterOutputStream) this).out.write(newline);
    }
}
