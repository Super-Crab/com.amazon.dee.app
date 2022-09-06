package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public class FixASCIIControlsReader extends PushbackReader {
    private static final int BUFFER_SIZE = 8;
    private static final int STATE_AMP = 1;
    private static final int STATE_DIG1 = 4;
    private static final int STATE_ERROR = 5;
    private static final int STATE_HASH = 2;
    private static final int STATE_HEX = 3;
    private static final int STATE_START = 0;
    private int control;
    private int digits;
    private int state;

    public FixASCIIControlsReader(Reader reader) {
        super(reader, 8);
        this.state = 0;
        this.control = 0;
        this.digits = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0043, code lost:
        if (com.adobe.xmp.impl.Utils.isControlChar((char) r10.control) != false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0086, code lost:
        if (com.adobe.xmp.impl.Utils.isControlChar((char) r10.control) != false) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private char processChar(char r11) {
        /*
            Method dump skipped, instructions count: 184
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.FixASCIIControlsReader.processChar(char):char");
    }

    @Override // java.io.PushbackReader, java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        boolean z;
        char[] cArr2 = new char[8];
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        loop0: while (true) {
            z = true;
            while (z && i4 < i2) {
                z = super.read(cArr2, i5, 1) == 1;
                if (z) {
                    char processChar = processChar(cArr2[i5]);
                    int i6 = this.state;
                    if (i6 == 0) {
                        if (Utils.isControlChar(processChar)) {
                            processChar = Chars.SPACE;
                        }
                        cArr[i3] = processChar;
                        i4++;
                        i3++;
                    } else {
                        i5++;
                        if (i6 == 5) {
                            unread(cArr2, 0, i5);
                        }
                    }
                    i5 = 0;
                } else if (i5 > 0) {
                    break;
                }
            }
            unread(cArr2, 0, i5);
            this.state = 5;
            i5 = 0;
        }
        if (i4 > 0 || z) {
            return i4;
        }
        return -1;
    }
}
