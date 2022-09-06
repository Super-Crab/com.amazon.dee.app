package com.fasterxml.jackson.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
/* loaded from: classes2.dex */
public abstract class OutputDecorator implements Serializable {
    public abstract OutputStream decorate(IOContext iOContext, OutputStream outputStream) throws IOException;

    public abstract Writer decorate(IOContext iOContext, Writer writer) throws IOException;
}
