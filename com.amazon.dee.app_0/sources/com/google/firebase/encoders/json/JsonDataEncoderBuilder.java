package com.google.firebase.encoders.json;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
/* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
/* loaded from: classes3.dex */
public final class JsonDataEncoderBuilder implements EncoderConfig<JsonDataEncoderBuilder> {
    private static final ValueEncoder<Boolean> BOOLEAN_ENCODER;
    private static final ValueEncoder<String> STRING_ENCODER;
    private static final TimestampEncoder TIMESTAMP_ENCODER;
    private final Map<Class<?>, ObjectEncoder<?>> objectEncoders = new HashMap();
    private final Map<Class<?>, ValueEncoder<?>> valueEncoders = new HashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.firebase:firebase-encoders-json@@16.0.0 */
    /* loaded from: classes3.dex */
    public static final class TimestampEncoder implements ValueEncoder<Date> {
        private static final DateFormat rfc339 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);

        static {
            rfc339.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        }

        private TimestampEncoder() {
        }

        @Override // com.google.firebase.encoders.ValueEncoder, com.google.firebase.encoders.Encoder
        public void encode(@Nullable Date date, @NonNull ValueEncoderContext valueEncoderContext) throws EncodingException, IOException {
            valueEncoderContext.mo8296add(rfc339.format(date));
        }
    }

    static {
        ValueEncoder<String> valueEncoder;
        ValueEncoder<Boolean> valueEncoder2;
        valueEncoder = JsonDataEncoderBuilder$$Lambda$1.instance;
        STRING_ENCODER = valueEncoder;
        valueEncoder2 = JsonDataEncoderBuilder$$Lambda$4.instance;
        BOOLEAN_ENCODER = valueEncoder2;
        TIMESTAMP_ENCODER = new TimestampEncoder();
    }

    public JsonDataEncoderBuilder() {
        registerEncoder(String.class, (ValueEncoder) STRING_ENCODER);
        registerEncoder(Boolean.class, (ValueEncoder) BOOLEAN_ENCODER);
        registerEncoder(Date.class, (ValueEncoder) TIMESTAMP_ENCODER);
    }

    @NonNull
    public DataEncoder build() {
        return new DataEncoder() { // from class: com.google.firebase.encoders.json.JsonDataEncoderBuilder.1
            {
                JsonDataEncoderBuilder.this = this;
            }

            @Override // com.google.firebase.encoders.DataEncoder
            public void encode(@NonNull Object obj, @NonNull Writer writer) throws IOException, EncodingException {
                JsonValueObjectEncoderContext jsonValueObjectEncoderContext = new JsonValueObjectEncoderContext(writer, JsonDataEncoderBuilder.this.objectEncoders, JsonDataEncoderBuilder.this.valueEncoders);
                jsonValueObjectEncoderContext.add(obj);
                jsonValueObjectEncoderContext.close();
            }

            @Override // com.google.firebase.encoders.DataEncoder
            public String encode(@NonNull Object obj) throws EncodingException {
                StringWriter stringWriter = new StringWriter();
                try {
                    encode(obj, stringWriter);
                } catch (IOException unused) {
                }
                return stringWriter.toString();
            }
        };
    }

    @NonNull
    public JsonDataEncoderBuilder configureWith(@NonNull Configurator configurator) {
        configurator.configure(this);
        return this;
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public <T> JsonDataEncoderBuilder registerEncoder(@NonNull Class<T> cls, @NonNull ObjectEncoder<? super T> objectEncoder) {
        if (!this.objectEncoders.containsKey(cls)) {
            this.objectEncoders.put(cls, objectEncoder);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("Encoder already registered for ")));
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    @NonNull
    public <T> JsonDataEncoderBuilder registerEncoder(@NonNull Class<T> cls, @NonNull ValueEncoder<? super T> valueEncoder) {
        if (!this.valueEncoders.containsKey(cls)) {
            this.valueEncoders.put(cls, valueEncoder);
            return this;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(cls, GeneratedOutlineSupport1.outline107("Encoder already registered for ")));
    }
}
