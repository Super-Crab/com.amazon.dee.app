package com.amazon.org.codehaus.jackson.impl;

import com.amazon.deecomms.common.Constants;
import com.amazon.org.codehaus.jackson.JsonStreamContext;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class JsonWriteContext extends JsonStreamContext {
    public static final int STATUS_EXPECT_NAME = 5;
    public static final int STATUS_EXPECT_VALUE = 4;
    public static final int STATUS_OK_AFTER_COLON = 2;
    public static final int STATUS_OK_AFTER_COMMA = 1;
    public static final int STATUS_OK_AFTER_SPACE = 3;
    public static final int STATUS_OK_AS_IS = 0;
    protected JsonWriteContext _child = null;
    protected String _currentName;
    protected final JsonWriteContext _parent;

    protected JsonWriteContext(int i, JsonWriteContext jsonWriteContext) {
        this._type = i;
        this._parent = jsonWriteContext;
        this._index = -1;
    }

    public static JsonWriteContext createRootContext() {
        return new JsonWriteContext(0, null);
    }

    private final JsonWriteContext reset(int i) {
        this._type = i;
        this._index = -1;
        this._currentName = null;
        return this;
    }

    protected final void appendDesc(StringBuilder sb) {
        int i = this._type;
        if (i != 2) {
            if (i == 1) {
                sb.append(JsonReaderKt.BEGIN_LIST);
                sb.append(getCurrentIndex());
                sb.append(JsonReaderKt.END_LIST);
                return;
            }
            sb.append("/");
            return;
        }
        sb.append(JsonReaderKt.BEGIN_OBJ);
        if (this._currentName != null) {
            sb.append('\"');
            sb.append(this._currentName);
            sb.append('\"');
        } else {
            sb.append(Constants.DEFAULT_IMAGE_CHAR);
        }
        sb.append(JsonReaderKt.END_OBJ);
    }

    public final JsonWriteContext createChildArrayContext() {
        JsonWriteContext jsonWriteContext = this._child;
        if (jsonWriteContext == null) {
            JsonWriteContext jsonWriteContext2 = new JsonWriteContext(1, this);
            this._child = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.reset(1);
    }

    public final JsonWriteContext createChildObjectContext() {
        JsonWriteContext jsonWriteContext = this._child;
        if (jsonWriteContext == null) {
            JsonWriteContext jsonWriteContext2 = new JsonWriteContext(2, this);
            this._child = jsonWriteContext2;
            return jsonWriteContext2;
        }
        return jsonWriteContext.reset(2);
    }

    @Override // com.amazon.org.codehaus.jackson.JsonStreamContext
    public final String getCurrentName() {
        return this._currentName;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        appendDesc(sb);
        return sb.toString();
    }

    public final int writeFieldName(String str) {
        if (this._type == 2 && this._currentName == null) {
            this._currentName = str;
            return this._index < 0 ? 0 : 1;
        }
        return 4;
    }

    public final int writeValue() {
        int i = this._type;
        if (i == 2) {
            if (this._currentName == null) {
                return 5;
            }
            this._currentName = null;
            this._index++;
            return 2;
        } else if (i == 1) {
            int i2 = this._index;
            this._index = i2 + 1;
            return i2 < 0 ? 0 : 1;
        } else {
            this._index++;
            return this._index == 0 ? 0 : 3;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.JsonStreamContext
    /* renamed from: getParent  reason: collision with other method in class */
    public final JsonWriteContext mo4257getParent() {
        return this._parent;
    }
}
