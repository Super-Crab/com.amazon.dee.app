package org.bouncycastle.util.encoders;

import org.apache.commons.fileupload.MultipartStream;
/* loaded from: classes5.dex */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        byte[] bArr = this.encodingTable;
        bArr[bArr.length - 2] = MultipartStream.DASH;
        bArr[bArr.length - 1] = 95;
        this.padding = (byte) 46;
        initialiseDecodingTable();
    }
}
