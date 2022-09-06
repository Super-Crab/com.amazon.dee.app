package com.amazon.communication;

import com.amazon.communication.IInputStream;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes12.dex */
public class InputStreamProxy extends IInputStream.Stub {
    public static final int IOEXCEPTION_ERROR_CODE = -2;
    public static final int OTHER_EXCEPTION_ERROR_CODE = -3;
    private static final DPLogger log = new DPLogger("TComm.InputStreamProxy");
    private final InputStream mInputStream;

    public InputStreamProxy(InputStream inputStream) {
        if (inputStream != null) {
            this.mInputStream = inputStream;
            return;
        }
        throw new IllegalArgumentException("InputStream cannot be null");
    }

    @Override // com.amazon.communication.IInputStream
    public int available() {
        try {
            return this.mInputStream.available();
        } catch (IOException e) {
            log.error("available", "IOException caught while calling available", e);
            return -2;
        } catch (Exception e2) {
            log.error("readBytes", "Caught exception from mInputStream.available()", e2);
            return -3;
        }
    }

    @Override // com.amazon.communication.IInputStream
    public boolean close() {
        try {
            this.mInputStream.close();
            return true;
        } catch (IOException e) {
            log.error("close", "IOException caught while calling close", e);
            return false;
        } catch (Exception e2) {
            log.error("readBytes", "Caught exception from mInputStream.close()", e2);
            return false;
        }
    }

    @Override // com.amazon.communication.IInputStream
    public int readByte() {
        try {
            return this.mInputStream.read();
        } catch (IOException e) {
            log.error("readByte", "IOException caught while calling read", e);
            return -2;
        } catch (Exception e2) {
            log.error("readBytes", "Caught exception from mInputStream.read()", e2);
            return -3;
        }
    }

    @Override // com.amazon.communication.IInputStream
    public int readBytes(byte[] bArr) {
        FailFast.expectNotNull(bArr);
        log.debug("read(byte[])", "About to read", "length", Integer.valueOf(bArr.length));
        try {
            return this.mInputStream.read(bArr);
        } catch (IOException e) {
            log.error("readBytes", "IOException caught while calling read", "data.length", Integer.valueOf(bArr.length), e);
            return -2;
        } catch (Exception e2) {
            log.error("readBytes", "Caught exception from mInputStream.read(data)", e2);
            return -3;
        }
    }

    @Override // com.amazon.communication.IInputStream
    public int readBytesIntoOffset(byte[] bArr, int i, int i2) {
        FailFast.expectNotNull(bArr);
        log.debug("read(byte[],int,int)", "About to read", "data.length", Integer.valueOf(bArr.length), "offset", Integer.valueOf(i), "length", Integer.valueOf(i2));
        try {
            return this.mInputStream.read(bArr, i, i2);
        } catch (IOException e) {
            log.error("readBytesIntoOffset", "IOException caught while calling read", e);
            return -2;
        } catch (Exception e2) {
            log.error("readBytes", "Caught exception from mInputStream.read(data, offset, length)", e2);
            return -3;
        }
    }
}
