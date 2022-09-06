package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.AtomicBooleanDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.CalendarDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.ClassDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.JavaTypeDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.StringDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.TimestampDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.TokenBufferDeserializer;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import java.util.GregorianCalendar;
import java.util.HashMap;
/* loaded from: classes13.dex */
class StdDeserializers {
    final HashMap<ClassKey, JsonDeserializer<Object>> _deserializers = new HashMap<>();

    private StdDeserializers() {
        add(new com.amazon.org.codehaus.jackson.map.deser.std.UntypedObjectDeserializer());
        StringDeserializer stringDeserializer = new StringDeserializer();
        add(stringDeserializer, String.class);
        add(stringDeserializer, CharSequence.class);
        add(new ClassDeserializer());
        add(new StdDeserializer.BooleanDeserializer(Boolean.class, null));
        add(new StdDeserializer.ByteDeserializer(Byte.class, null));
        add(new StdDeserializer.ShortDeserializer(Short.class, null));
        add(new StdDeserializer.CharacterDeserializer(Character.class, null));
        add(new StdDeserializer.IntegerDeserializer(Integer.class, null));
        add(new StdDeserializer.LongDeserializer(Long.class, null));
        add(new StdDeserializer.FloatDeserializer(Float.class, null));
        add(new StdDeserializer.DoubleDeserializer(Double.class, null));
        add(new StdDeserializer.BooleanDeserializer(Boolean.TYPE, Boolean.FALSE));
        add(new StdDeserializer.ByteDeserializer(Byte.TYPE, (byte) 0));
        add(new StdDeserializer.ShortDeserializer(Short.TYPE, (short) 0));
        add(new StdDeserializer.CharacterDeserializer(Character.TYPE, (char) 0));
        add(new StdDeserializer.IntegerDeserializer(Integer.TYPE, 0));
        add(new StdDeserializer.LongDeserializer(Long.TYPE, 0L));
        add(new StdDeserializer.FloatDeserializer(Float.TYPE, Float.valueOf(0.0f)));
        add(new StdDeserializer.DoubleDeserializer(Double.TYPE, Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)));
        add(new StdDeserializer.NumberDeserializer());
        add(new StdDeserializer.BigDecimalDeserializer());
        add(new StdDeserializer.BigIntegerDeserializer());
        add(new CalendarDeserializer());
        add(new com.amazon.org.codehaus.jackson.map.deser.std.DateDeserializer());
        add(new CalendarDeserializer(GregorianCalendar.class), GregorianCalendar.class);
        add(new StdDeserializer.SqlDateDeserializer());
        add(new TimestampDeserializer());
        for (com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer<?> fromStringDeserializer : com.amazon.org.codehaus.jackson.map.deser.std.FromStringDeserializer.all()) {
            add(fromStringDeserializer);
        }
        add(new StdDeserializer.StackTraceElementDeserializer());
        add(new AtomicBooleanDeserializer());
        add(new TokenBufferDeserializer());
        add(new JavaTypeDeserializer());
    }

    private void add(com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer<?> stdDeserializer) {
        add(stdDeserializer, stdDeserializer.getValueClass());
    }

    public static HashMap<ClassKey, JsonDeserializer<Object>> constructAll() {
        return new StdDeserializers()._deserializers;
    }

    private void add(com.amazon.org.codehaus.jackson.map.deser.std.StdDeserializer<?> stdDeserializer, Class<?> cls) {
        this._deserializers.put(new ClassKey(cls), stdDeserializer);
    }
}
