package com.amazon.whisperjoin.common.sharedtypes.utility;

import java.nio.charset.Charset;
import java.util.Arrays;
/* loaded from: classes13.dex */
public class EncodingHelpers {
    private static final int MAX_BYTE_VALUE = 255;
    private static final int MAX_COMMAND_LENGTH = 64;
    private static final String UTF_8 = "UTF-8";

    /* loaded from: classes13.dex */
    public interface CommandInformation {
        String getCommand();

        byte[] getPayload();
    }

    private int getBufferSize(byte[] bArr, byte[] bArr2, int i) {
        int length = bArr.length + bArr2.length + 1;
        if (length < i) {
            return length;
        }
        throw new IllegalArgumentException("buffer size is larger than maximumMessageSize");
    }

    private String getCommand(byte[] bArr) {
        return new String(bArr, 1, getCommandSize(bArr), Charset.forName("UTF-8"));
    }

    private byte[] getCommandBytes(String str) {
        byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
        if (bytes.length <= 127) {
            return bytes;
        }
        throw new IllegalArgumentException("command's UTF 8 bytes larger than Byte.MAX_VALUE");
    }

    private byte getCommandSize(byte[] bArr) {
        if (bArr.length > 1) {
            byte b = bArr[0];
            if (bArr.length < b + 1) {
                throw new IllegalArgumentException("command size is larger than buffer size");
            }
            return b;
        }
        throw new IllegalArgumentException("messageBuffer not large enough to have a command");
    }

    private byte[] getPayload(byte[] bArr) {
        int commandSize = getCommandSize(bArr) + 1;
        if (commandSize == bArr.length) {
            return null;
        }
        if (commandSize <= bArr.length) {
            return Arrays.copyOfRange(bArr, commandSize, bArr.length);
        }
        throw new IllegalArgumentException("command size is larger than message size");
    }

    private void setCommand(byte[] bArr, byte[] bArr2) {
        setCommandSize(bArr, bArr2.length);
        System.arraycopy(bArr2, 0, bArr, 1, bArr2.length);
    }

    private void setCommandSize(byte[] bArr, int i) {
        if (i <= 255) {
            if (i > 0) {
                if (bArr.length >= i + 1) {
                    bArr[0] = (byte) i;
                    return;
                }
                throw new IllegalArgumentException("final buffer not large enough for commandBuffer");
            }
            throw new IllegalArgumentException("command size can not be <= 0");
        }
        throw new IllegalArgumentException("command's UTF 8 bytes larger than Byte.MAX_VALUE");
    }

    private void setPayload(byte[] bArr, byte[] bArr2) {
        byte commandSize = getCommandSize(bArr);
        if (bArr.length == bArr2.length + commandSize + 1) {
            System.arraycopy(bArr2, 0, bArr, commandSize + 1, bArr2.length);
            return;
        }
        throw new IllegalArgumentException("finalBuffer is not the correct size");
    }

    public CommandInformation decodeCommand(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            if (bArr.length > 1) {
                final String command = getCommand(bArr);
                final byte[] payload = getPayload(bArr);
                return new CommandInformation() { // from class: com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers.1
                    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers.CommandInformation
                    public String getCommand() {
                        return command;
                    }

                    @Override // com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers.CommandInformation
                    public byte[] getPayload() {
                        return payload;
                    }
                };
            }
            throw new IllegalArgumentException("message is too small for a command");
        }
        throw new IllegalArgumentException("data is empty");
    }

    public byte[] encodeCommand(String str, byte[] bArr, int i) {
        if (str != null && str.length() != 0) {
            if (str.length() <= 64) {
                if (bArr == null) {
                    bArr = new byte[0];
                }
                byte[] commandBytes = getCommandBytes(str);
                byte[] bArr2 = new byte[getBufferSize(commandBytes, bArr, i)];
                setCommand(bArr2, commandBytes);
                setPayload(bArr2, bArr);
                return bArr2;
            }
            throw new IllegalArgumentException("command is too long");
        }
        throw new IllegalArgumentException("command must not null or greater than 0 length");
    }
}
