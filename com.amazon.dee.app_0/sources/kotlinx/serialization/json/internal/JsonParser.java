package kotlinx.serialization.json.internal;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
/* compiled from: JsonParser.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\bH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/internal/JsonParser;", "", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lkotlinx/serialization/json/JsonConfiguration;", "reader", "Lkotlinx/serialization/json/internal/JsonReader;", "(Lkotlinx/serialization/json/JsonConfiguration;Lkotlinx/serialization/json/internal/JsonReader;)V", "isLenient", "", "read", "Lkotlinx/serialization/json/JsonElement;", "readArray", "readObject", "readValue", "isString", "kotlinx-serialization-runtime"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class JsonParser {
    private final boolean isLenient;
    private final JsonReader reader;

    public JsonParser(@NotNull JsonConfiguration configuration, @NotNull JsonReader reader) {
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(reader, "reader");
        this.reader = reader;
        this.isLenient = configuration.isLenient$kotlinx_serialization_runtime();
    }

    private final JsonElement readArray() {
        JsonReader jsonReader;
        byte b;
        JsonReader jsonReader2 = this.reader;
        if (jsonReader2.tokenClass != 8) {
            jsonReader2.fail("Expected start of the array", jsonReader2.tokenPosition);
            throw null;
        }
        jsonReader2.nextToken();
        JsonReader jsonReader3 = this.reader;
        boolean z = jsonReader3.tokenClass != 4;
        int i = this.reader.currentPosition;
        if (z) {
            ArrayList arrayList = new ArrayList();
            do {
                boolean z2 = false;
                while (this.reader.getCanBeginValue()) {
                    arrayList.add(read());
                    jsonReader = this.reader;
                    b = jsonReader.tokenClass;
                    if (b != 4) {
                        break;
                    }
                    jsonReader.nextToken();
                    z2 = true;
                }
                JsonReader jsonReader4 = this.reader;
                boolean z3 = !z2;
                int i2 = jsonReader4.currentPosition;
                if (z3) {
                    jsonReader4.nextToken();
                    return new JsonArray(arrayList);
                }
                jsonReader4.fail("Unexpected trailing comma", i2);
                throw null;
            } while (b == 9);
            jsonReader.fail("Expected end of the array or comma", jsonReader.tokenPosition);
            throw null;
        }
        jsonReader3.fail("Unexpected leading comma", i);
        throw null;
    }

    private final JsonElement readObject() {
        JsonReader jsonReader;
        byte b;
        JsonReader jsonReader2 = this.reader;
        if (jsonReader2.tokenClass != 6) {
            jsonReader2.fail("Expected start of the object", jsonReader2.tokenPosition);
            throw null;
        }
        jsonReader2.nextToken();
        JsonReader jsonReader3 = this.reader;
        boolean z = true;
        boolean z2 = jsonReader3.tokenClass != 4;
        int i = this.reader.currentPosition;
        if (z2) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            do {
                boolean z3 = false;
                while (this.reader.getCanBeginValue()) {
                    String takeString = this.isLenient ? this.reader.takeString() : this.reader.takeStringQuoted();
                    JsonReader jsonReader4 = this.reader;
                    if (jsonReader4.tokenClass != 5) {
                        jsonReader4.fail("Expected ':'", jsonReader4.tokenPosition);
                        throw null;
                    }
                    jsonReader4.nextToken();
                    linkedHashMap.put(takeString, read());
                    jsonReader = this.reader;
                    b = jsonReader.tokenClass;
                    if (b != 4) {
                        break;
                    }
                    jsonReader.nextToken();
                    z3 = true;
                }
                JsonReader jsonReader5 = this.reader;
                if (z3 || jsonReader5.tokenClass != 7) {
                    z = false;
                }
                JsonReader jsonReader6 = this.reader;
                int i2 = jsonReader6.currentPosition;
                if (z) {
                    jsonReader6.nextToken();
                    return new JsonObject(linkedHashMap);
                }
                jsonReader5.fail("Expected end of the object", i2);
                throw null;
            } while (b == 7);
            jsonReader.fail("Expected end of the object or comma", jsonReader.tokenPosition);
            throw null;
        }
        jsonReader3.fail("Unexpected leading comma", i);
        throw null;
    }

    private final JsonElement readValue(boolean z) {
        String takeStringQuoted;
        if (this.isLenient) {
            takeStringQuoted = this.reader.takeString();
        } else {
            JsonReader jsonReader = this.reader;
            takeStringQuoted = z ? jsonReader.takeStringQuoted() : jsonReader.takeString();
        }
        return new JsonLiteral(takeStringQuoted, z);
    }

    @NotNull
    public final JsonElement read() {
        if (this.reader.getCanBeginValue()) {
            JsonReader jsonReader = this.reader;
            byte b = jsonReader.tokenClass;
            if (b == 0) {
                return readValue(false);
            }
            if (b == 1) {
                return readValue(true);
            }
            if (b == 6) {
                return readObject();
            }
            if (b == 8) {
                return readArray();
            }
            if (b == 10) {
                JsonNull jsonNull = JsonNull.INSTANCE;
                jsonReader.nextToken();
                return jsonNull;
            }
            JsonReader.fail$default(jsonReader, "Can't begin reading element, unexpected token", 0, 2, null);
            throw null;
        }
        JsonReader.fail$default(this.reader, "Can't begin reading value from here", 0, 2, null);
        throw null;
    }
}
