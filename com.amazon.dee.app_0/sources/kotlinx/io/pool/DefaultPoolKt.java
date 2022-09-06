package kotlinx.io.pool;

import kotlin.Metadata;
/* compiled from: DefaultPool.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"MAGIC", "", "MAX_CAPACITY", "MULTIPLIER", "PROBE_COUNT", "kotlinx-io"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class DefaultPoolKt {
    private static final int MAGIC = -1640531527;
    private static final int MAX_CAPACITY = 536870911;
    private static final int MULTIPLIER = 4;
    private static final int PROBE_COUNT = 8;
}
