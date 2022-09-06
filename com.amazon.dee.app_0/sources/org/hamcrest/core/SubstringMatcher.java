package org.hamcrest.core;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes5.dex */
public abstract class SubstringMatcher extends TypeSafeMatcher<String> {
    protected final String substring;

    /* JADX INFO: Access modifiers changed from: protected */
    public SubstringMatcher(String str) {
        this.substring = str;
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string ").appendText(relationship()).appendText(" ").appendValue(this.substring);
    }

    protected abstract boolean evalSubstringOf(String str);

    protected abstract String relationship();

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(String str, Description description) {
        description.appendText("was \"").appendText(str).appendText(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        return evalSubstringOf(str);
    }
}
