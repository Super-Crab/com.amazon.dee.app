package org.codehaus.jackson;

import com.amazon.alexa.biloba.utils.WebConstants;
/* loaded from: classes5.dex */
public abstract class JsonStreamContext {
    protected static final int TYPE_ARRAY = 1;
    protected static final int TYPE_OBJECT = 2;
    protected static final int TYPE_ROOT = 0;
    protected int _index;
    protected int _type;

    public final int getCurrentIndex() {
        int i = this._index;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public abstract String getCurrentName();

    public final int getEntryCount() {
        return this._index + 1;
    }

    /* renamed from: getParent */
    public abstract JsonStreamContext mo12871getParent();

    public final String getTypeDesc() {
        int i = this._type;
        return i != 0 ? i != 1 ? i != 2 ? WebConstants.UriConstants.QUESTIONMARK_KEY : "OBJECT" : "ARRAY" : "ROOT";
    }

    public final boolean inArray() {
        return this._type == 1;
    }

    public final boolean inObject() {
        return this._type == 2;
    }

    public final boolean inRoot() {
        return this._type == 0;
    }
}
