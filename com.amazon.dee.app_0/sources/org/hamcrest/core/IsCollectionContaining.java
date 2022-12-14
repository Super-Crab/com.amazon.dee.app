package org.hamcrest.core;

import java.util.ArrayList;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
/* loaded from: classes5.dex */
public class IsCollectionContaining<T> extends TypeSafeDiagnosingMatcher<Iterable<? super T>> {
    private final Matcher<? super T> elementMatcher;

    public IsCollectionContaining(Matcher<? super T> matcher) {
        this.elementMatcher = matcher;
    }

    @Factory
    public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> matcher) {
        return new IsCollectionContaining(matcher);
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... matcherArr) {
        ArrayList arrayList = new ArrayList(matcherArr.length);
        for (Matcher<? super T> matcher : matcherArr) {
            arrayList.add(new IsCollectionContaining(matcher));
        }
        return AllOf.allOf(arrayList);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
    }

    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    protected /* bridge */ /* synthetic */ boolean matchesSafely(Object obj, Description description) {
        return matchesSafely((Iterable) ((Iterable) obj), description);
    }

    @Factory
    public static <T> Matcher<Iterable<? super T>> hasItem(T t) {
        return new IsCollectionContaining(IsEqual.equalTo(t));
    }

    protected boolean matchesSafely(Iterable<? super T> iterable, Description description) {
        boolean z = false;
        for (T t : iterable) {
            if (this.elementMatcher.matches(t)) {
                return true;
            }
            if (z) {
                description.appendText(", ");
            }
            this.elementMatcher.describeMismatch(t, description);
            z = true;
        }
        return false;
    }

    @Factory
    public static <T> Matcher<Iterable<T>> hasItems(T... tArr) {
        ArrayList arrayList = new ArrayList(tArr.length);
        for (T t : tArr) {
            arrayList.add(hasItem(t));
        }
        return AllOf.allOf(arrayList);
    }
}
