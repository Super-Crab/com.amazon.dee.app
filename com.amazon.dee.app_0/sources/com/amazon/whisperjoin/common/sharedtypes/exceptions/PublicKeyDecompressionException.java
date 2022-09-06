package com.amazon.whisperjoin.common.sharedtypes.exceptions;
/* loaded from: classes13.dex */
public class PublicKeyDecompressionException extends Exception {
    public PublicKeyDecompressionException(Exception exc) {
        super("Couldn't decompress public key from Barcode", exc);
    }
}
