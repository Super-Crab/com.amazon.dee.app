package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.json.JsonReadContext;
/* loaded from: classes2.dex */
public class TokenBufferReadContext extends JsonStreamContext {
    protected String _currentName;
    protected Object _currentValue;
    protected final JsonStreamContext _parent;
    protected final JsonLocation _startLocation;

    protected TokenBufferReadContext(JsonStreamContext jsonStreamContext, Object obj) {
        super(jsonStreamContext);
        this._parent = jsonStreamContext.mo7184getParent();
        this._currentName = jsonStreamContext.getCurrentName();
        this._currentValue = jsonStreamContext.getCurrentValue();
        if (jsonStreamContext instanceof JsonReadContext) {
            this._startLocation = ((JsonReadContext) jsonStreamContext).getStartLocation(obj);
        } else {
            this._startLocation = JsonLocation.NA;
        }
    }

    public static TokenBufferReadContext createRootContext(JsonStreamContext jsonStreamContext) {
        if (jsonStreamContext == null) {
            return new TokenBufferReadContext();
        }
        return new TokenBufferReadContext(jsonStreamContext, (JsonLocation) null);
    }

    public TokenBufferReadContext createChildArrayContext() {
        this._index++;
        return new TokenBufferReadContext(this, 1, -1);
    }

    public TokenBufferReadContext createChildObjectContext() {
        this._index++;
        return new TokenBufferReadContext(this, 2, -1);
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public String getCurrentName() {
        return this._currentName;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public Object getCurrentValue() {
        return this._currentValue;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    /* renamed from: getParent */
    public JsonStreamContext mo7184getParent() {
        return this._parent;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public boolean hasCurrentName() {
        return this._currentName != null;
    }

    public TokenBufferReadContext parentOrCopy() {
        JsonStreamContext jsonStreamContext = this._parent;
        if (jsonStreamContext instanceof TokenBufferReadContext) {
            return (TokenBufferReadContext) jsonStreamContext;
        }
        if (jsonStreamContext == null) {
            return new TokenBufferReadContext();
        }
        return new TokenBufferReadContext(jsonStreamContext, this._startLocation);
    }

    public void setCurrentName(String str) throws JsonProcessingException {
        this._currentName = str;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public void updateForValue() {
        this._index++;
    }

    protected TokenBufferReadContext(JsonStreamContext jsonStreamContext, JsonLocation jsonLocation) {
        super(jsonStreamContext);
        this._parent = jsonStreamContext.mo7184getParent();
        this._currentName = jsonStreamContext.getCurrentName();
        this._currentValue = jsonStreamContext.getCurrentValue();
        this._startLocation = jsonLocation;
    }

    protected TokenBufferReadContext() {
        super(0, -1);
        this._parent = null;
        this._startLocation = JsonLocation.NA;
    }

    protected TokenBufferReadContext(TokenBufferReadContext tokenBufferReadContext, int i, int i2) {
        super(i, i2);
        this._parent = tokenBufferReadContext;
        this._startLocation = tokenBufferReadContext._startLocation;
    }
}
