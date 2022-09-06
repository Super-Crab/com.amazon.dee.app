package org.junit.internal.runners.statements;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.junit.internal.AssumptionViolatedException;
import org.junit.runners.model.Statement;
/* loaded from: classes5.dex */
public class ExpectException extends Statement {
    private final Class<? extends Throwable> expected;
    private final Statement next;

    public ExpectException(Statement statement, Class<? extends Throwable> cls) {
        this.next = statement;
        this.expected = cls;
    }

    @Override // org.junit.runners.model.Statement
    public void evaluate() throws Exception {
        boolean z;
        try {
            this.next.evaluate();
            z = true;
        } catch (AssumptionViolatedException e) {
            throw e;
        } catch (Throwable th) {
            if (!this.expected.isAssignableFrom(th.getClass())) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected exception, expected<");
                outline107.append(this.expected.getName());
                outline107.append("> but was<");
                outline107.append(th.getClass().getName());
                outline107.append(Config.Compare.GREATER_THAN);
                throw new Exception(outline107.toString(), th);
            }
            z = false;
        }
        if (!z) {
            return;
        }
        throw new AssertionError(GeneratedOutlineSupport1.outline38(this.expected, GeneratedOutlineSupport1.outline107("Expected exception: ")));
    }
}
