package org.junit.internal;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.junit.Assert;
/* loaded from: classes5.dex */
public abstract class ComparisonCriteria {
    private int assertArraysAreSameLength(Object obj, Object obj2, String str) {
        if (obj == null) {
            Assert.fail(str + "expected array was null");
        }
        if (obj2 == null) {
            Assert.fail(str + "actual array was null");
        }
        int length = Array.getLength(obj2);
        int length2 = Array.getLength(obj);
        if (length != length2) {
            Assert.fail(str + "array lengths differed, expected.length=" + length2 + " actual.length=" + length);
        }
        return length2;
    }

    private boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    public void arrayEquals(String str, Object obj, Object obj2) throws ArrayComparisonFailure {
        if (obj != obj2) {
            if (Arrays.deepEquals(new Object[]{obj}, new Object[]{obj2})) {
                return;
            }
            String outline72 = str == null ? "" : GeneratedOutlineSupport1.outline72(str, RealTimeTextConstants.COLON_SPACE);
            int assertArraysAreSameLength = assertArraysAreSameLength(obj, obj2, outline72);
            for (int i = 0; i < assertArraysAreSameLength; i++) {
                Object obj3 = Array.get(obj, i);
                Object obj4 = Array.get(obj2, i);
                if (isArray(obj3) && isArray(obj4)) {
                    try {
                        arrayEquals(str, obj3, obj4);
                    } catch (ArrayComparisonFailure e) {
                        e.addDimension(i);
                        throw e;
                    }
                } else {
                    try {
                        assertElementsEqual(obj3, obj4);
                    } catch (AssertionError e2) {
                        throw new ArrayComparisonFailure(outline72, e2, i);
                    }
                }
            }
        }
    }

    protected abstract void assertElementsEqual(Object obj, Object obj2);
}
