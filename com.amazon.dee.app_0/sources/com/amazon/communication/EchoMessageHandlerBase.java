package com.amazon.communication;

import amazon.communication.MessageHandler;
import com.amazon.dp.logger.DPLogger;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public abstract class EchoMessageHandlerBase implements MessageHandler {
    protected static final int HEADER_LENGTH = 3;
    private static final DPLogger log = new DPLogger("TComm.EchoMessageHandlerBase");
    private static final byte[] PING_HEADER = {80, 73, 78};
    private static final byte[] PONG_HEADER = {80, 79, 78};

    private static boolean checkHeader(InputStream inputStream, byte[] bArr) {
        int i = 0;
        while (i < 3) {
            try {
                int read = inputStream.read();
                if (read == -1 || bArr[i] != ((byte) read)) {
                    break;
                }
                i++;
            } catch (IOException e) {
                log.error("checkHeader", "failed due to IOException", e);
                return false;
            }
        }
        return i == 3;
    }

    public static boolean isPingMessage(InputStream inputStream) {
        return checkHeader(inputStream, PING_HEADER);
    }

    public static boolean isPongMessage(InputStream inputStream) {
        return checkHeader(inputStream, PONG_HEADER);
    }

    public static void preparePingHeader(ByteBuffer byteBuffer) {
        byteBuffer.put(PING_HEADER);
    }

    public static void preparePongHeader(ByteBuffer byteBuffer) {
        byteBuffer.put(PONG_HEADER);
    }
}
