package org.junit.experimental.results;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes5.dex */
public class ResultMatchers {
    public static Matcher<PrintableResult> failureCountIs(final int i) {
        return new TypeSafeMatcher<PrintableResult>() { // from class: org.junit.experimental.results.ResultMatchers.1
            @Override // org.hamcrest.SelfDescribing
            public void describeTo(Description description) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("has ");
                outline107.append(i);
                outline107.append(" failures");
                description.appendText(outline107.toString());
            }

            @Override // org.hamcrest.TypeSafeMatcher
            public boolean matchesSafely(PrintableResult printableResult) {
                return printableResult.failureCount() == i;
            }
        };
    }

    public static Matcher<PrintableResult> hasFailureContaining(final String str) {
        return new BaseMatcher<PrintableResult>() { // from class: org.junit.experimental.results.ResultMatchers.3
            @Override // org.hamcrest.SelfDescribing
            public void describeTo(Description description) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("has failure containing ");
                outline107.append(str);
                description.appendText(outline107.toString());
            }

            @Override // org.hamcrest.Matcher
            public boolean matches(Object obj) {
                return obj.toString().contains(str);
            }
        };
    }

    public static Matcher<Object> hasSingleFailureContaining(final String str) {
        return new BaseMatcher<Object>() { // from class: org.junit.experimental.results.ResultMatchers.2
            @Override // org.hamcrest.SelfDescribing
            public void describeTo(Description description) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("has single failure containing ");
                outline107.append(str);
                description.appendText(outline107.toString());
            }

            @Override // org.hamcrest.Matcher
            public boolean matches(Object obj) {
                return obj.toString().contains(str) && ResultMatchers.failureCountIs(1).matches(obj);
            }
        };
    }

    public static Matcher<PrintableResult> isSuccessful() {
        return failureCountIs(0);
    }
}
