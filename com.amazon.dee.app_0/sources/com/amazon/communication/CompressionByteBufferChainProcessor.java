package com.amazon.communication;

import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.DeflaterOutputStream;
/* loaded from: classes12.dex */
public class CompressionByteBufferChainProcessor implements ByteBufferChainProcessor {
    private static final int BUFFER_SIZE_GROWTH_RATIO = 10;
    private static final int INITIAL_BUFFER_SIZE_RATIO = 2;
    private static final int MIN_BUFFER_SIZE = 1;
    private static final int SINGLE_BUFFER_MESSAGE_SIZE_BOUND = 576;
    private static final DPLogger log = new DPLogger("TComm.CompressionByteBufferChainProcessor");
    private final Encoding mEncoding;

    public CompressionByteBufferChainProcessor(Encoding encoding) {
        this.mEncoding = encoding;
    }

    @Override // com.amazon.communication.ByteBufferChainProcessor
    public ByteBufferChain process(ByteBufferChain byteBufferChain) throws ByteBufferChainProcessingException {
        int dataSize;
        int dataSize2;
        FailFast.expectTrue(byteBufferChain.getDataSize() > 0);
        if (byteBufferChain.getDataSize() < SINGLE_BUFFER_MESSAGE_SIZE_BOUND) {
            dataSize = byteBufferChain.getDataSize();
            dataSize2 = dataSize;
        } else {
            dataSize = byteBufferChain.getDataSize() / 2;
            dataSize2 = byteBufferChain.getDataSize() / 10;
            FailFast.expectTrue(dataSize > 1, "Initial capacity ", Integer.valueOf(dataSize), " should be larger than minimal buffer size ", 1);
        }
        GrowableByteBufferChainOutputStream growableByteBufferChainOutputStream = new GrowableByteBufferChainOutputStream(dataSize, dataSize2);
        DeflaterOutputStream deflaterOutputStream = null;
        try {
            try {
                deflaterOutputStream = CompressionStreamFactory.createDeflaterOutputStream(this.mEncoding, growableByteBufferChainOutputStream);
                ByteBuffer[] byteBuffers = byteBufferChain.getByteBuffers();
                for (int i = 0; i < byteBuffers.length; i++) {
                    deflaterOutputStream.write(byteBuffers[i].array(), byteBuffers[i].position(), byteBuffers[i].remaining());
                }
                deflaterOutputStream.finish();
                ByteBufferChain byteBufferChain2 = growableByteBufferChainOutputStream.getByteBufferChain();
                try {
                    deflaterOutputStream.close();
                    growableByteBufferChainOutputStream.close();
                } catch (IOException e) {
                    log.error("process", "IOException when closing the DeflaterOutputStream", "deflaterOutputStream", deflaterOutputStream, e);
                }
                return byteBufferChain2;
            } catch (IOException e2) {
                log.error("process", "IOException while compressing data", e2);
                throw new ByteBufferChainProcessingException(e2);
            }
        } catch (Throwable th) {
            if (deflaterOutputStream != null) {
                try {
                    deflaterOutputStream.close();
                    growableByteBufferChainOutputStream.close();
                } catch (IOException e3) {
                    log.error("process", "IOException when closing the DeflaterOutputStream", "deflaterOutputStream", deflaterOutputStream, e3);
                }
            }
            throw th;
        }
    }
}
