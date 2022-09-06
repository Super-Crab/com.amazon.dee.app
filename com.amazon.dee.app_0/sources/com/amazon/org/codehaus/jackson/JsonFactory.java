package com.amazon.org.codehaus.jackson;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.format.InputAccessor;
import com.amazon.org.codehaus.jackson.format.MatchStrength;
import com.amazon.org.codehaus.jackson.impl.ByteSourceBootstrapper;
import com.amazon.org.codehaus.jackson.impl.ReaderBasedParser;
import com.amazon.org.codehaus.jackson.impl.Utf8Generator;
import com.amazon.org.codehaus.jackson.impl.WriterBasedGenerator;
import com.amazon.org.codehaus.jackson.io.CharacterEscapes;
import com.amazon.org.codehaus.jackson.io.IOContext;
import com.amazon.org.codehaus.jackson.io.InputDecorator;
import com.amazon.org.codehaus.jackson.io.OutputDecorator;
import com.amazon.org.codehaus.jackson.io.UTF8Writer;
import com.amazon.org.codehaus.jackson.sym.BytesToNameCanonicalizer;
import com.amazon.org.codehaus.jackson.sym.CharsToNameCanonicalizer;
import com.amazon.org.codehaus.jackson.util.BufferRecycler;
import com.amazon.org.codehaus.jackson.util.VersionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.ref.SoftReference;
import java.net.URL;
/* loaded from: classes13.dex */
public class JsonFactory implements Versioned {
    public static final String FORMAT_NAME_JSON = "JSON";
    protected CharacterEscapes _characterEscapes;
    protected int _generatorFeatures;
    protected InputDecorator _inputDecorator;
    protected ObjectCodec _objectCodec;
    protected OutputDecorator _outputDecorator;
    protected int _parserFeatures;
    protected BytesToNameCanonicalizer _rootByteSymbols;
    protected CharsToNameCanonicalizer _rootCharSymbols;
    static final int DEFAULT_PARSER_FEATURE_FLAGS = JsonParser.Feature.collectDefaults();
    static final int DEFAULT_GENERATOR_FEATURE_FLAGS = JsonGenerator.Feature.collectDefaults();
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef = new ThreadLocal<>();

    public JsonFactory() {
        this(null);
    }

    protected IOContext _createContext(Object obj, boolean z) {
        return new IOContext(_getBufferRecycler(), obj, z);
    }

    protected JsonGenerator _createJsonGenerator(Writer writer, IOContext iOContext) throws IOException {
        WriterBasedGenerator writerBasedGenerator = new WriterBasedGenerator(iOContext, this._generatorFeatures, this._objectCodec, writer);
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            writerBasedGenerator.setCharacterEscapes(characterEscapes);
        }
        return writerBasedGenerator;
    }

    protected JsonParser _createJsonParser(InputStream inputStream, IOContext iOContext) throws IOException, JsonParseException {
        return new ByteSourceBootstrapper(iOContext, inputStream).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols);
    }

    protected JsonGenerator _createUTF8JsonGenerator(OutputStream outputStream, IOContext iOContext) throws IOException {
        Utf8Generator utf8Generator = new Utf8Generator(iOContext, this._generatorFeatures, this._objectCodec, outputStream);
        CharacterEscapes characterEscapes = this._characterEscapes;
        if (characterEscapes != null) {
            utf8Generator.setCharacterEscapes(characterEscapes);
        }
        return utf8Generator;
    }

    protected Writer _createWriter(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) throws IOException {
        if (jsonEncoding == JsonEncoding.UTF8) {
            return new UTF8Writer(iOContext, outputStream);
        }
        return new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    public BufferRecycler _getBufferRecycler() {
        SoftReference<BufferRecycler> softReference = _recyclerRef.get();
        BufferRecycler bufferRecycler = softReference == null ? null : softReference.get();
        if (bufferRecycler == null) {
            BufferRecycler bufferRecycler2 = new BufferRecycler();
            _recyclerRef.set(new SoftReference<>(bufferRecycler2));
            return bufferRecycler2;
        }
        return bufferRecycler;
    }

    protected InputStream _optimizedStreamFromURL(URL url) throws IOException {
        String host;
        if ("file".equals(url.getProtocol()) && ((host = url.getHost()) == null || host.length() == 0)) {
            return new FileInputStream(url.getPath());
        }
        return url.openStream();
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public JsonGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) throws IOException {
        IOContext _createContext = _createContext(outputStream, false);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            OutputDecorator outputDecorator = this._outputDecorator;
            if (outputDecorator != null) {
                outputStream = outputDecorator.decorate(_createContext, outputStream);
            }
            return _createUTF8JsonGenerator(outputStream, _createContext);
        }
        Writer _createWriter = _createWriter(outputStream, jsonEncoding, _createContext);
        OutputDecorator outputDecorator2 = this._outputDecorator;
        if (outputDecorator2 != null) {
            _createWriter = outputDecorator2.decorate(_createContext, _createWriter);
        }
        return _createJsonGenerator(_createWriter, _createContext);
    }

    public JsonParser createJsonParser(File file) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(file, true);
        InputStream fileInputStream = new FileInputStream(file);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            fileInputStream = inputDecorator.decorate(_createContext, fileInputStream);
        }
        return _createJsonParser(fileInputStream, _createContext);
    }

    public JsonFactory disable(JsonParser.Feature feature) {
        this._parserFeatures = (~feature.getMask()) & this._parserFeatures;
        return this;
    }

    @Deprecated
    public final void disableGeneratorFeature(JsonGenerator.Feature feature) {
        disable(feature);
    }

    public final void disableParserFeature(JsonParser.Feature feature) {
        disable(feature);
    }

    public JsonFactory enable(JsonParser.Feature feature) {
        this._parserFeatures = feature.getMask() | this._parserFeatures;
        return this;
    }

    @Deprecated
    public final void enableGeneratorFeature(JsonGenerator.Feature feature) {
        enable(feature);
    }

    public final void enableParserFeature(JsonParser.Feature feature) {
        enable(feature);
    }

    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    /* renamed from: getCodec */
    public ObjectCodec mo4112getCodec() {
        return this._objectCodec;
    }

    public String getFormatName() {
        if (getClass() == JsonFactory.class) {
            return "JSON";
        }
        return null;
    }

    public InputDecorator getInputDecorator() {
        return this._inputDecorator;
    }

    public OutputDecorator getOutputDecorator() {
        return this._outputDecorator;
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        if (getClass() == JsonFactory.class) {
            return hasJSONFormat(inputAccessor);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MatchStrength hasJSONFormat(InputAccessor inputAccessor) throws IOException {
        return ByteSourceBootstrapper.hasJSONFormat(inputAccessor);
    }

    public final boolean isEnabled(JsonParser.Feature feature) {
        return (feature.getMask() & this._parserFeatures) != 0;
    }

    @Deprecated
    public final boolean isGeneratorFeatureEnabled(JsonGenerator.Feature feature) {
        return isEnabled(feature);
    }

    public final boolean isParserFeatureEnabled(JsonParser.Feature feature) {
        return (feature.getMask() & this._parserFeatures) != 0;
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterEscapes) {
        this._characterEscapes = characterEscapes;
        return this;
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
        return this;
    }

    @Deprecated
    public final void setGeneratorFeature(JsonGenerator.Feature feature, boolean z) {
        configure(feature, z);
    }

    public JsonFactory setInputDecorator(InputDecorator inputDecorator) {
        this._inputDecorator = inputDecorator;
        return this;
    }

    public JsonFactory setOutputDecorator(OutputDecorator outputDecorator) {
        this._outputDecorator = outputDecorator;
        return this;
    }

    public final void setParserFeature(JsonParser.Feature feature, boolean z) {
        configure(feature, z);
    }

    @Override // com.amazon.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(Utf8Generator.class);
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this._rootCharSymbols = CharsToNameCanonicalizer.createRoot();
        this._rootByteSymbols = BytesToNameCanonicalizer.createRoot();
        this._parserFeatures = DEFAULT_PARSER_FEATURE_FLAGS;
        this._generatorFeatures = DEFAULT_GENERATOR_FEATURE_FLAGS;
        this._objectCodec = objectCodec;
    }

    protected JsonParser _createJsonParser(Reader reader, IOContext iOContext) throws IOException, JsonParseException {
        return new ReaderBasedParser(iOContext, this._parserFeatures, reader, this._objectCodec, this._rootCharSymbols.makeChild(isEnabled(JsonParser.Feature.CANONICALIZE_FIELD_NAMES), isEnabled(JsonParser.Feature.INTERN_FIELD_NAMES)));
    }

    public JsonFactory disable(JsonGenerator.Feature feature) {
        this._generatorFeatures = (~feature.getMask()) & this._generatorFeatures;
        return this;
    }

    public JsonFactory enable(JsonGenerator.Feature feature) {
        this._generatorFeatures = feature.getMask() | this._generatorFeatures;
        return this;
    }

    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this._generatorFeatures) != 0;
    }

    protected JsonParser _createJsonParser(byte[] bArr, int i, int i2, IOContext iOContext) throws IOException, JsonParseException {
        return new ByteSourceBootstrapper(iOContext, bArr, i, i2).constructParser(this._parserFeatures, this._objectCodec, this._rootByteSymbols, this._rootCharSymbols);
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean z) {
        if (z) {
            enable(feature);
        } else {
            disable(feature);
        }
        return this;
    }

    public JsonParser createJsonParser(URL url) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(url, true);
        InputStream _optimizedStreamFromURL = _optimizedStreamFromURL(url);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            _optimizedStreamFromURL = inputDecorator.decorate(_createContext, _optimizedStreamFromURL);
        }
        return _createJsonParser(_optimizedStreamFromURL, _createContext);
    }

    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
        IOContext _createContext = _createContext(writer, false);
        OutputDecorator outputDecorator = this._outputDecorator;
        if (outputDecorator != null) {
            writer = outputDecorator.decorate(_createContext, writer);
        }
        return _createJsonGenerator(writer, _createContext);
    }

    public JsonParser createJsonParser(InputStream inputStream) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(inputStream, false);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            inputStream = inputDecorator.decorate(_createContext, inputStream);
        }
        return _createJsonParser(inputStream, _createContext);
    }

    public JsonGenerator createJsonGenerator(OutputStream outputStream) throws IOException {
        return createJsonGenerator(outputStream, JsonEncoding.UTF8);
    }

    public JsonParser createJsonParser(Reader reader) throws IOException, JsonParseException {
        IOContext _createContext = _createContext(reader, false);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            reader = inputDecorator.decorate(_createContext, reader);
        }
        return _createJsonParser(reader, _createContext);
    }

    public JsonGenerator createJsonGenerator(File file, JsonEncoding jsonEncoding) throws IOException {
        OutputStream fileOutputStream = new FileOutputStream(file);
        IOContext _createContext = _createContext(fileOutputStream, true);
        _createContext.setEncoding(jsonEncoding);
        if (jsonEncoding == JsonEncoding.UTF8) {
            OutputDecorator outputDecorator = this._outputDecorator;
            if (outputDecorator != null) {
                fileOutputStream = outputDecorator.decorate(_createContext, fileOutputStream);
            }
            return _createUTF8JsonGenerator(fileOutputStream, _createContext);
        }
        Writer _createWriter = _createWriter(fileOutputStream, jsonEncoding, _createContext);
        OutputDecorator outputDecorator2 = this._outputDecorator;
        if (outputDecorator2 != null) {
            _createWriter = outputDecorator2.decorate(_createContext, _createWriter);
        }
        return _createJsonGenerator(_createWriter, _createContext);
    }

    public JsonParser createJsonParser(byte[] bArr) throws IOException, JsonParseException {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null && (decorate = inputDecorator.decorate(_createContext, bArr, 0, bArr.length)) != null) {
            return _createJsonParser(decorate, _createContext);
        }
        return _createJsonParser(bArr, 0, bArr.length, _createContext);
    }

    public JsonParser createJsonParser(byte[] bArr, int i, int i2) throws IOException, JsonParseException {
        InputStream decorate;
        IOContext _createContext = _createContext(bArr, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null && (decorate = inputDecorator.decorate(_createContext, bArr, i, i2)) != null) {
            return _createJsonParser(decorate, _createContext);
        }
        return _createJsonParser(bArr, i, i2, _createContext);
    }

    public JsonParser createJsonParser(String str) throws IOException, JsonParseException {
        Reader stringReader = new StringReader(str);
        IOContext _createContext = _createContext(stringReader, true);
        InputDecorator inputDecorator = this._inputDecorator;
        if (inputDecorator != null) {
            stringReader = inputDecorator.decorate(_createContext, stringReader);
        }
        return _createJsonParser(stringReader, _createContext);
    }
}
