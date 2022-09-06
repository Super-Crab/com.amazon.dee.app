package com.fasterxml.jackson.core;

import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
/* loaded from: classes2.dex */
public abstract class TSFBuilder<F extends JsonFactory, B extends TSFBuilder<F, B>> {
    protected int _factoryFeatures;
    protected InputDecorator _inputDecorator;
    protected OutputDecorator _outputDecorator;
    protected int _streamReadFeatures;
    protected int _streamWriteFeatures;
    protected static final int DEFAULT_FACTORY_FEATURE_FLAGS = JsonFactory.Feature.collectDefaults();
    protected static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    protected static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();

    /* JADX INFO: Access modifiers changed from: protected */
    public TSFBuilder() {
        this._factoryFeatures = DEFAULT_FACTORY_FEATURE_FLAGS;
        this._streamReadFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._streamWriteFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._inputDecorator = null;
        this._outputDecorator = null;
    }

    private B _failNonJSON(Object obj) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Feature ");
        outline107.append(obj.getClass().getName());
        outline107.append(MqttTopic.MULTI_LEVEL_WILDCARD);
        outline107.append(obj.toString());
        outline107.append(" not supported for non-JSON backend");
        throw new IllegalArgumentException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _legacyDisable(JsonParser.Feature feature) {
        if (feature != null) {
            this._streamReadFeatures = (~feature.getMask()) & this._streamReadFeatures;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _legacyEnable(JsonParser.Feature feature) {
        if (feature != null) {
            this._streamReadFeatures = feature.getMask() | this._streamReadFeatures;
        }
    }

    protected final B _this() {
        return this;
    }

    public abstract F build();

    public B configure(JsonFactory.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public B disable(JsonFactory.Feature feature) {
        this._factoryFeatures = (~feature.getMask()) & this._factoryFeatures;
        return _this();
    }

    public B enable(JsonFactory.Feature feature) {
        this._factoryFeatures = feature.getMask() | this._factoryFeatures;
        return _this();
    }

    public int factoryFeaturesMask() {
        return this._factoryFeatures;
    }

    public InputDecorator inputDecorator() {
        return this._inputDecorator;
    }

    public OutputDecorator outputDecorator() {
        return this._outputDecorator;
    }

    public int streamReadFeatures() {
        return this._streamReadFeatures;
    }

    public int streamWriteFeatures() {
        return this._streamWriteFeatures;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _legacyDisable(JsonGenerator.Feature feature) {
        if (feature != null) {
            this._streamWriteFeatures = (~feature.getMask()) & this._streamWriteFeatures;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void _legacyEnable(JsonGenerator.Feature feature) {
        if (feature != null) {
            this._streamWriteFeatures = feature.getMask() | this._streamWriteFeatures;
        }
    }

    public B configure(StreamReadFeature streamReadFeature, boolean z) {
        return z ? enable(streamReadFeature) : disable(streamReadFeature);
    }

    public B inputDecorator(InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return _this();
    }

    public B outputDecorator(OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return _this();
    }

    public B configure(StreamWriteFeature streamWriteFeature, boolean z) {
        return z ? enable(streamWriteFeature) : disable(streamWriteFeature);
    }

    public B disable(StreamReadFeature streamReadFeature) {
        this._streamReadFeatures = (~streamReadFeature.mappedFeature().getMask()) & this._streamReadFeatures;
        return _this();
    }

    public B enable(StreamReadFeature streamReadFeature) {
        this._streamReadFeatures = streamReadFeature.mappedFeature().getMask() | this._streamReadFeatures;
        return _this();
    }

    /* renamed from: configure */
    public B mo6986configure(JsonReadFeature jsonReadFeature, boolean z) {
        return _failNonJSON(jsonReadFeature);
    }

    /* renamed from: configure */
    public B mo6987configure(JsonWriteFeature jsonWriteFeature, boolean z) {
        return _failNonJSON(jsonWriteFeature);
    }

    public B disable(StreamReadFeature streamReadFeature, StreamReadFeature... streamReadFeatureArr) {
        this._streamReadFeatures = (~streamReadFeature.mappedFeature().getMask()) & this._streamReadFeatures;
        for (StreamReadFeature streamReadFeature2 : streamReadFeatureArr) {
            this._streamReadFeatures = (~streamReadFeature2.mappedFeature().getMask()) & this._streamReadFeatures;
        }
        return _this();
    }

    public B enable(StreamReadFeature streamReadFeature, StreamReadFeature... streamReadFeatureArr) {
        this._streamReadFeatures = streamReadFeature.mappedFeature().getMask() | this._streamReadFeatures;
        for (StreamReadFeature streamReadFeature2 : streamReadFeatureArr) {
            this._streamReadFeatures = streamReadFeature2.mappedFeature().getMask() | this._streamReadFeatures;
        }
        return _this();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TSFBuilder(JsonFactory jsonFactory) {
        this(jsonFactory._factoryFeatures, jsonFactory._parserFeatures, jsonFactory._generatorFeatures);
    }

    protected TSFBuilder(int i, int i2, int i3) {
        this._factoryFeatures = i;
        this._streamReadFeatures = i2;
        this._streamWriteFeatures = i3;
    }

    public B disable(StreamWriteFeature streamWriteFeature) {
        this._streamWriteFeatures = (~streamWriteFeature.mappedFeature().getMask()) & this._streamWriteFeatures;
        return _this();
    }

    public B enable(StreamWriteFeature streamWriteFeature) {
        this._streamWriteFeatures = streamWriteFeature.mappedFeature().getMask() | this._streamWriteFeatures;
        return _this();
    }

    public B disable(StreamWriteFeature streamWriteFeature, StreamWriteFeature... streamWriteFeatureArr) {
        this._streamWriteFeatures = (~streamWriteFeature.mappedFeature().getMask()) & this._streamWriteFeatures;
        for (StreamWriteFeature streamWriteFeature2 : streamWriteFeatureArr) {
            this._streamWriteFeatures = (~streamWriteFeature2.mappedFeature().getMask()) & this._streamWriteFeatures;
        }
        return _this();
    }

    public B enable(StreamWriteFeature streamWriteFeature, StreamWriteFeature... streamWriteFeatureArr) {
        this._streamWriteFeatures = streamWriteFeature.mappedFeature().getMask() | this._streamWriteFeatures;
        for (StreamWriteFeature streamWriteFeature2 : streamWriteFeatureArr) {
            this._streamWriteFeatures = streamWriteFeature2.mappedFeature().getMask() | this._streamWriteFeatures;
        }
        return _this();
    }

    /* renamed from: disable */
    public B mo6988disable(JsonReadFeature jsonReadFeature) {
        return _failNonJSON(jsonReadFeature);
    }

    /* renamed from: enable */
    public B mo6992enable(JsonReadFeature jsonReadFeature) {
        return _failNonJSON(jsonReadFeature);
    }

    /* renamed from: disable */
    public B mo6989disable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        return _failNonJSON(jsonReadFeature);
    }

    /* renamed from: enable */
    public B mo6993enable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        return _failNonJSON(jsonReadFeature);
    }

    /* renamed from: disable */
    public B mo6990disable(JsonWriteFeature jsonWriteFeature) {
        return _failNonJSON(jsonWriteFeature);
    }

    /* renamed from: enable */
    public B mo6994enable(JsonWriteFeature jsonWriteFeature) {
        return _failNonJSON(jsonWriteFeature);
    }

    /* renamed from: disable */
    public B mo6991disable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        return _failNonJSON(jsonWriteFeature);
    }

    /* renamed from: enable */
    public B mo6995enable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        return _failNonJSON(jsonWriteFeature);
    }
}
