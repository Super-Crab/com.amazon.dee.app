package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.LinkedList;
import java.util.Queue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@Beta
@GwtIncompatible
/* loaded from: classes3.dex */
public final class LineReader {
    private final Readable readable;
    @NullableDecl
    private final Reader reader;
    private final CharBuffer cbuf = CharStreams.createBuffer();
    private final char[] buf = this.cbuf.array();
    private final Queue<String> lines = new LinkedList();
    private final LineBuffer lineBuf = new LineBuffer() { // from class: com.google.common.io.LineReader.1
        @Override // com.google.common.io.LineBuffer
        protected void handleLine(String str, String str2) {
            LineReader.this.lines.add(str);
        }
    };

    public LineReader(Readable readable) {
        this.readable = (Readable) Preconditions.checkNotNull(readable);
        this.reader = readable instanceof Reader ? (Reader) readable : null;
    }

    @CanIgnoreReturnValue
    public String readLine() throws IOException {
        int read;
        while (true) {
            if (this.lines.peek() != null) {
                break;
            }
            Java8Compatibility.clear(this.cbuf);
            Reader reader = this.reader;
            if (reader != null) {
                char[] cArr = this.buf;
                read = reader.read(cArr, 0, cArr.length);
            } else {
                read = this.readable.read(this.cbuf);
            }
            if (read == -1) {
                this.lineBuf.finish();
                break;
            }
            this.lineBuf.add(this.buf, 0, read);
        }
        return this.lines.poll();
    }
}
