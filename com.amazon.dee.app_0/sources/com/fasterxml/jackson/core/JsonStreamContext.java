package com.fasterxml.jackson.core;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.deecomms.common.Constants;
import com.fasterxml.jackson.core.io.CharTypes;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes2.dex */
public abstract class JsonStreamContext {
    public static final int TYPE_ARRAY = 1;
    public static final int TYPE_OBJECT = 2;
    public static final int TYPE_ROOT = 0;
    protected int _index;
    protected int _type;

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonStreamContext() {
    }

    public final int getCurrentIndex() {
        int i = this._index;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public abstract String getCurrentName();

    public Object getCurrentValue() {
        return null;
    }

    public final int getEntryCount() {
        return this._index + 1;
    }

    /* renamed from: getParent */
    public abstract JsonStreamContext mo7184getParent();

    public JsonLocation getStartLocation(Object obj) {
        return JsonLocation.NA;
    }

    @Deprecated
    public final String getTypeDesc() {
        int i = this._type;
        return i != 0 ? i != 1 ? i != 2 ? WebConstants.UriConstants.QUESTIONMARK_KEY : "OBJECT" : "ARRAY" : "ROOT";
    }

    public boolean hasCurrentIndex() {
        return this._index >= 0;
    }

    public boolean hasCurrentName() {
        return getCurrentName() != null;
    }

    public boolean hasPathSegment() {
        int i = this._type;
        if (i == 2) {
            return hasCurrentName();
        }
        if (i != 1) {
            return false;
        }
        return hasCurrentIndex();
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

    public JsonPointer pathAsPointer() {
        return JsonPointer.forPath(this, false);
    }

    public void setCurrentValue(Object obj) {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i = this._type;
        if (i == 0) {
            sb.append("/");
        } else if (i != 1) {
            sb.append(JsonReaderKt.BEGIN_OBJ);
            String currentName = getCurrentName();
            if (currentName != null) {
                sb.append('\"');
                CharTypes.appendQuoted(sb, currentName);
                sb.append('\"');
            } else {
                sb.append(Constants.DEFAULT_IMAGE_CHAR);
            }
            sb.append(JsonReaderKt.END_OBJ);
        } else {
            sb.append(JsonReaderKt.BEGIN_LIST);
            sb.append(getCurrentIndex());
            sb.append(JsonReaderKt.END_LIST);
        }
        return sb.toString();
    }

    public String typeDesc() {
        int i = this._type;
        return i != 0 ? i != 1 ? i != 2 ? WebConstants.UriConstants.QUESTIONMARK_KEY : "Object" : "Array" : "root";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonStreamContext(JsonStreamContext jsonStreamContext) {
        this._type = jsonStreamContext._type;
        this._index = jsonStreamContext._index;
    }

    public JsonPointer pathAsPointer(boolean z) {
        return JsonPointer.forPath(this, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonStreamContext(int i, int i2) {
        this._type = i;
        this._index = i2;
    }
}
