package kotlin.time;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.SinceKotlin;
/* compiled from: TimeSources.kt */
@SinceKotlin(version = "1.3")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", ViewProps.OVERFLOW, "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(D)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, k = 1, mv = {1, 1, 16})
@ExperimentalTime
/* loaded from: classes4.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(TimeUnit.NANOSECONDS);
    }

    /* renamed from: overflow-LRDsOJo  reason: not valid java name */
    private final void m12227overflowLRDsOJo(double d) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TestTimeSource will overflow if its reading ");
        outline107.append(this.reading);
        outline107.append("ns is advanced by ");
        outline107.append(Duration.m12219toStringimpl(d));
        outline107.append('.');
        throw new IllegalStateException(outline107.toString());
    }

    /* renamed from: plusAssign-LRDsOJo  reason: not valid java name */
    public final void m12228plusAssignLRDsOJo(double d) {
        long j;
        double m12213toDoubleimpl = Duration.m12213toDoubleimpl(d, getUnit());
        long j2 = (long) m12213toDoubleimpl;
        if (j2 != Long.MIN_VALUE && j2 != Long.MAX_VALUE) {
            long j3 = this.reading;
            j = j3 + j2;
            if ((j2 ^ j3) >= 0 && (j3 ^ j) < 0) {
                m12227overflowLRDsOJo(d);
            }
        } else {
            double d2 = this.reading + m12213toDoubleimpl;
            if (d2 > Long.MAX_VALUE || d2 < Long.MIN_VALUE) {
                m12227overflowLRDsOJo(d);
            }
            j = (long) d2;
        }
        this.reading = j;
    }

    @Override // kotlin.time.AbstractLongTimeSource
    protected long read() {
        return this.reading;
    }
}
