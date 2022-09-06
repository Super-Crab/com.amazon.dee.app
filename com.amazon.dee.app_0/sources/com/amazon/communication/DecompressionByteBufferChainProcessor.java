package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.InflaterInputStream;
/* loaded from: classes12.dex */
public class DecompressionByteBufferChainProcessor implements ByteBufferChainProcessor {
    private static final DPLogger log = new DPLogger("TComm.DecompressionByteBufferChainProcessor");
    private final Encoding mEncoding;

    public DecompressionByteBufferChainProcessor(Encoding encoding) {
        this.mEncoding = encoding;
    }

    @Override // com.amazon.communication.ByteBufferChainProcessor
    public ByteBufferChain process(ByteBufferChain byteBufferChain) throws ByteBufferChainProcessingException {
        FailFast.expectTrue(byteBufferChain.getDataSize() > 0);
        ByteBufferChainInputStream inputStream = byteBufferChain.getInputStream();
        int max = Math.max(byteBufferChain.getDataSize() / 2, 1);
        ByteBufferChain byteBufferChain2 = new ByteBufferChain();
        InflaterInputStream inflaterInputStream = null;
        try {
            try {
                inflaterInputStream = CompressionStreamFactory.createInflaterInputStream(this.mEncoding, inputStream);
                byte[] bArr = new byte[max];
                while (true) {
                    int read = inflaterInputStream.read(bArr);
                    if (read > 0) {
                        byteBufferChain2.append(ByteBuffer.wrap(bArr, 0, read));
                        bArr = new byte[max];
                    } else {
                        try {
                            break;
                        } catch (IOException e) {
                            log.error("process", "IOException while closing InflaterInputStream", "inflaterInputStream", inflaterInputStream, e);
                        }
                    }
                }
                inflaterInputStream.close();
                inputStream.close();
                return byteBufferChain2;
            } catch (IOException e2) {
                log.error("process", "IOException while decompressing data", e2);
                throw new ByteBufferChainProcessingException(e2);
            }
        } catch (Throwable th) {
            if (inflaterInputStream != null) {
                try {
                    inflaterInputStream.close();
                } catch (IOException e3) {
                    log.error("process", "IOException while closing InflaterInputStream", "inflaterInputStream", inflaterInputStream, e3);
                    throw th;
                }
            }
            inputStream.close();
            throw th;
        }
    }
}
