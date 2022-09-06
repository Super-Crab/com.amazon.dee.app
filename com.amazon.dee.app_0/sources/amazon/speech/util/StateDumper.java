package amazon.speech.util;

import java.io.PrintWriter;
/* loaded from: classes.dex */
public interface StateDumper {
    void dump(PrintWriter printWriter);

    void dumpSecurely(PrintWriter printWriter);
}
