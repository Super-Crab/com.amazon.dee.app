package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
/* loaded from: classes2.dex */
public class JsonFactoryBuilder extends TSFBuilder<JsonFactory, JsonFactoryBuilder> {
    protected CharacterEscapes _characterEscapes;
    protected int _maximumNonEscapedChar;
    protected char _quoteChar;
    protected SerializableString _rootValueSeparator;

    public JsonFactoryBuilder() {
        this._quoteChar = '\"';
        this._rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        this._maximumNonEscapedChar = 0;
    }

    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactory build() {
        return new JsonFactory(this);
    }

    public JsonFactoryBuilder characterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public JsonFactoryBuilder highestNonEscapedChar(int i) {
        this._maximumNonEscapedChar = i <= 0 ? 0 : Math.max(127, i);
        return this;
    }

    public JsonFactoryBuilder quoteChar(char c) {
        if (c <= 127) {
            this._quoteChar = c;
            return this;
        }
        throw new IllegalArgumentException("Can only use Unicode characters up to 0x7F as quote characters");
    }

    public JsonFactoryBuilder rootValueSeparator(String str) {
        this._rootValueSeparator = str == null ? null : new SerializedString(str);
        return this;
    }

    public CharacterEscapes characterEscapes() {
        return this._characterEscapes;
    }

    public int highestNonEscapedChar() {
        return this._maximumNonEscapedChar;
    }

    public JsonFactoryBuilder rootValueSeparator(SerializableString serializableString) {
        this._rootValueSeparator = serializableString;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: configure */
    public JsonFactoryBuilder mo6986configure(JsonReadFeature jsonReadFeature, boolean z) {
        return z ? mo6992enable(jsonReadFeature) : mo6988disable(jsonReadFeature);
    }

    public char quoteChar() {
        return this._quoteChar;
    }

    public SerializableString rootValueSeparator() {
        return this._rootValueSeparator;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: configure */
    public JsonFactoryBuilder mo6987configure(JsonWriteFeature jsonWriteFeature, boolean z) {
        return z ? mo6994enable(jsonWriteFeature) : mo6990disable(jsonWriteFeature);
    }

    public JsonFactoryBuilder(JsonFactory jsonFactory) {
        super(jsonFactory);
        this._quoteChar = '\"';
        this._characterEscapes = jsonFactory.getCharacterEscapes();
        this._rootValueSeparator = jsonFactory._rootValueSeparator;
        this._maximumNonEscapedChar = jsonFactory._maximumNonEscapedChar;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: disable */
    public JsonFactoryBuilder mo6988disable(JsonReadFeature jsonReadFeature) {
        _legacyDisable(jsonReadFeature.mappedFeature());
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: enable */
    public JsonFactoryBuilder mo6992enable(JsonReadFeature jsonReadFeature) {
        _legacyEnable(jsonReadFeature.mappedFeature());
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: disable */
    public JsonFactoryBuilder mo6989disable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        _legacyDisable(jsonReadFeature.mappedFeature());
        for (JsonReadFeature jsonReadFeature2 : jsonReadFeatureArr) {
            _legacyEnable(jsonReadFeature2.mappedFeature());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: enable */
    public JsonFactoryBuilder mo6993enable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        _legacyEnable(jsonReadFeature.mappedFeature());
        mo6992enable(jsonReadFeature);
        for (JsonReadFeature jsonReadFeature2 : jsonReadFeatureArr) {
            _legacyEnable(jsonReadFeature2.mappedFeature());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: disable */
    public JsonFactoryBuilder mo6990disable(JsonWriteFeature jsonWriteFeature) {
        _legacyDisable(jsonWriteFeature.mappedFeature());
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: disable */
    public JsonFactoryBuilder mo6991disable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        _legacyDisable(jsonWriteFeature.mappedFeature());
        for (JsonWriteFeature jsonWriteFeature2 : jsonWriteFeatureArr) {
            _legacyDisable(jsonWriteFeature2.mappedFeature());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: enable */
    public JsonFactoryBuilder mo6994enable(JsonWriteFeature jsonWriteFeature) {
        JsonGenerator.Feature mappedFeature = jsonWriteFeature.mappedFeature();
        if (mappedFeature != null) {
            _legacyEnable(mappedFeature);
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    /* renamed from: enable */
    public JsonFactoryBuilder mo6995enable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        _legacyEnable(jsonWriteFeature.mappedFeature());
        for (JsonWriteFeature jsonWriteFeature2 : jsonWriteFeatureArr) {
            _legacyEnable(jsonWriteFeature2.mappedFeature());
        }
        return this;
    }
}
