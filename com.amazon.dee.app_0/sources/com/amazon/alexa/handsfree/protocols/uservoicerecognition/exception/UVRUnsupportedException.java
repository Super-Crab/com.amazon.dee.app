package com.amazon.alexa.handsfree.protocols.uservoicerecognition.exception;

import java.util.NoSuchElementException;
/* loaded from: classes8.dex */
public class UVRUnsupportedException extends NoSuchElementException {
    public UVRUnsupportedException() {
        super("UVR unavailable for this device.");
    }
}
