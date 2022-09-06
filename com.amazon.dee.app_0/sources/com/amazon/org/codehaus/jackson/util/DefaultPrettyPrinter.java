package com.amazon.org.codehaus.jackson.util;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.PrettyPrinter;
import com.amazon.org.codehaus.jackson.impl.Indenter;
import java.io.IOException;
import java.util.Arrays;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class DefaultPrettyPrinter implements PrettyPrinter {
    protected Indenter _arrayIndenter = new FixedSpaceIndenter();
    protected Indenter _objectIndenter = new Lf2SpacesIndenter();
    protected boolean _spacesInObjectEntries = true;
    protected int _nesting = 0;

    /* loaded from: classes13.dex */
    public static class FixedSpaceIndenter implements Indenter {
        @Override // com.amazon.org.codehaus.jackson.impl.Indenter
        public boolean isInline() {
            return true;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
            jsonGenerator.writeRaw(Chars.SPACE);
        }
    }

    /* loaded from: classes13.dex */
    public static class Lf2SpacesIndenter implements Indenter {
        static final char[] SPACES;
        static final int SPACE_COUNT = 64;
        static final String SYSTEM_LINE_SEPARATOR;

        static {
            String str;
            try {
                str = System.getProperty("line.separator");
            } catch (Throwable unused) {
                str = null;
            }
            if (str == null) {
                str = "\n";
            }
            SYSTEM_LINE_SEPARATOR = str;
            SPACES = new char[64];
            Arrays.fill(SPACES, (char) Chars.SPACE);
        }

        @Override // com.amazon.org.codehaus.jackson.impl.Indenter
        public boolean isInline() {
            return false;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
            jsonGenerator.writeRaw(SYSTEM_LINE_SEPARATOR);
            int i2 = i + i;
            while (i2 > 64) {
                jsonGenerator.writeRaw(SPACES, 0, 64);
                i2 -= SPACES.length;
            }
            jsonGenerator.writeRaw(SPACES, 0, i2);
        }
    }

    /* loaded from: classes13.dex */
    public static class NopIndenter implements Indenter {
        @Override // com.amazon.org.codehaus.jackson.impl.Indenter
        public boolean isInline() {
            return true;
        }

        @Override // com.amazon.org.codehaus.jackson.impl.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i) {
        }
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void indentArraysWith(Indenter indenter) {
        if (indenter == null) {
            indenter = new NopIndenter();
        }
        this._arrayIndenter = indenter;
    }

    public void indentObjectsWith(Indenter indenter) {
        if (indenter == null) {
            indenter = new NopIndenter();
        }
        this._objectIndenter = indenter;
    }

    public void spacesInObjectEntries(boolean z) {
        this._spacesInObjectEntries = z;
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(JsonReaderKt.COMMA);
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        if (!this._arrayIndenter.isInline()) {
            this._nesting--;
        }
        if (i > 0) {
            this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
        } else {
            jsonGenerator.writeRaw(Chars.SPACE);
        }
        jsonGenerator.writeRaw(JsonReaderKt.END_LIST);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        if (!this._objectIndenter.isInline()) {
            this._nesting--;
        }
        if (i > 0) {
            this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
        } else {
            jsonGenerator.writeRaw(Chars.SPACE);
        }
        jsonGenerator.writeRaw(JsonReaderKt.END_OBJ);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(JsonReaderKt.COMMA);
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        if (this._spacesInObjectEntries) {
            jsonGenerator.writeRaw(" : ");
        } else {
            jsonGenerator.writeRaw(JsonReaderKt.COLON);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(Chars.SPACE);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeStartArray(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        if (!this._arrayIndenter.isInline()) {
            this._nesting++;
        }
        jsonGenerator.writeRaw(JsonReaderKt.BEGIN_LIST);
    }

    @Override // com.amazon.org.codehaus.jackson.PrettyPrinter
    public void writeStartObject(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw(JsonReaderKt.BEGIN_OBJ);
        if (!this._objectIndenter.isInline()) {
            this._nesting++;
        }
    }
}
