package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lkotlinx/serialization/json/internal/EscapeCharMappings;", "", "()V", "ESCAPE_2_CHAR", "", "initC2ESC", "", "c", "", "esc", "", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class EscapeCharMappings {
    @JvmField
    @NotNull
    public static final char[] ESCAPE_2_CHAR;
    public static final EscapeCharMappings INSTANCE;

    static {
        EscapeCharMappings escapeCharMappings = new EscapeCharMappings();
        INSTANCE = escapeCharMappings;
        ESCAPE_2_CHAR = new char[117];
        for (int i = 0; i <= 31; i++) {
            escapeCharMappings.initC2ESC(i, 'u');
        }
        escapeCharMappings.initC2ESC(8, 'b');
        escapeCharMappings.initC2ESC(9, 't');
        escapeCharMappings.initC2ESC(10, 'n');
        escapeCharMappings.initC2ESC(12, 'f');
        escapeCharMappings.initC2ESC(13, 'r');
        escapeCharMappings.initC2ESC('/', '/');
        escapeCharMappings.initC2ESC('\"', '\"');
        escapeCharMappings.initC2ESC('\\', '\\');
    }

    private EscapeCharMappings() {
    }

    private final void initC2ESC(int i, char c) {
        if (c != 'u') {
            ESCAPE_2_CHAR[c] = (char) i;
        }
    }

    private final void initC2ESC(char c, char c2) {
        initC2ESC((int) c, c2);
    }
}
