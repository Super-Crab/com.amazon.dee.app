package org.apache.commons.net.tftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
/* loaded from: classes4.dex */
public class TFTPClient extends TFTP {
    public static final int DEFAULT_MAX_TIMEOUTS = 5;
    private int __maxTimeouts = 5;

    public int getMaxTimeouts() {
        return this.__maxTimeouts;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0117, code lost:
        r12 = r13;
        r6 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011e, code lost:
        bufferedSend(new org.apache.commons.net.tftp.TFTPErrorPacket(r10.getAddress(), r10.getPort(), 5, "Unexpected host or port."));
        r0 = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int receiveFile(java.lang.String r17, int r18, java.io.OutputStream r19, java.net.InetAddress r20, int r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.tftp.TFTPClient.receiveFile(java.lang.String, int, java.io.OutputStream, java.net.InetAddress, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0106, code lost:
        bufferedSend(new org.apache.commons.net.tftp.TFTPErrorPacket(r11.getAddress(), r11.getPort(), 5, "Unexpected host or port."));
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0118, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0118, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void sendFile(java.lang.String r11, int r12, java.io.InputStream r13, java.net.InetAddress r14, int r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.tftp.TFTPClient.sendFile(java.lang.String, int, java.io.InputStream, java.net.InetAddress, int):void");
    }

    public void setMaxTimeouts(int i) {
        if (i < 1) {
            this.__maxTimeouts = 1;
        } else {
            this.__maxTimeouts = i;
        }
    }

    public void sendFile(String str, int i, InputStream inputStream, String str2, int i2) throws UnknownHostException, IOException {
        sendFile(str, i, inputStream, InetAddress.getByName(str2), i2);
    }

    public void sendFile(String str, int i, InputStream inputStream, InetAddress inetAddress) throws IOException {
        sendFile(str, i, inputStream, inetAddress, 69);
    }

    public void sendFile(String str, int i, InputStream inputStream, String str2) throws UnknownHostException, IOException {
        sendFile(str, i, inputStream, InetAddress.getByName(str2), 69);
    }

    public int receiveFile(String str, int i, OutputStream outputStream, String str2, int i2) throws UnknownHostException, IOException {
        return receiveFile(str, i, outputStream, InetAddress.getByName(str2), i2);
    }

    public int receiveFile(String str, int i, OutputStream outputStream, InetAddress inetAddress) throws IOException {
        return receiveFile(str, i, outputStream, inetAddress, 69);
    }

    public int receiveFile(String str, int i, OutputStream outputStream, String str2) throws UnknownHostException, IOException {
        return receiveFile(str, i, outputStream, InetAddress.getByName(str2), 69);
    }
}
