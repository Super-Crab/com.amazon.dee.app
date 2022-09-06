package com.fasterxml.jackson.core.filter;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonPointer;
/* loaded from: classes2.dex */
public class JsonPointerBasedFilter extends TokenFilter {
    protected final JsonPointer _pathToMatch;

    public JsonPointerBasedFilter(String str) {
        this(JsonPointer.compile(str));
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    protected boolean _includeScalar() {
        return this._pathToMatch.matches();
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter filterStartArray() {
        return this;
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter filterStartObject() {
        return this;
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter includeElement(int i) {
        JsonPointer matchElement = this._pathToMatch.matchElement(i);
        if (matchElement == null) {
            return null;
        }
        if (matchElement.matches()) {
            return TokenFilter.INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(matchElement);
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public TokenFilter includeProperty(String str) {
        JsonPointer matchProperty = this._pathToMatch.matchProperty(str);
        if (matchProperty == null) {
            return null;
        }
        if (matchProperty.matches()) {
            return TokenFilter.INCLUDE_ALL;
        }
        return new JsonPointerBasedFilter(matchProperty);
    }

    @Override // com.fasterxml.jackson.core.filter.TokenFilter
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[JsonPointerFilter at: ");
        outline107.append(this._pathToMatch);
        outline107.append("]");
        return outline107.toString();
    }

    public JsonPointerBasedFilter(JsonPointer jsonPointer) {
        this._pathToMatch = jsonPointer;
    }
}
