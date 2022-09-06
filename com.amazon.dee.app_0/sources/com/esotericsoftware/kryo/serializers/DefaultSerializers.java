package com.esotericsoftware.kryo.serializers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.Util;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
/* loaded from: classes2.dex */
public class DefaultSerializers {

    /* loaded from: classes2.dex */
    public static class BigDecimalSerializer extends Serializer<BigDecimal> {
        private BigIntegerSerializer bigIntegerSerializer = new BigIntegerSerializer();

        public BigDecimalSerializer() {
            setAcceptsNull(true);
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public BigDecimal mo6848read(Kryo kryo, Input input, Class<BigDecimal> cls) {
            BigInteger mo6848read = this.bigIntegerSerializer.mo6848read(kryo, input, (Class<BigInteger>) null);
            if (mo6848read == null) {
                return null;
            }
            return new BigDecimal(mo6848read, input.readInt(false));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BigDecimal bigDecimal) {
            if (bigDecimal == null) {
                output.writeVarInt(0, true);
                return;
            }
            this.bigIntegerSerializer.write(kryo, output, bigDecimal.unscaledValue());
            output.writeInt(bigDecimal.scale(), false);
        }
    }

    /* loaded from: classes2.dex */
    public static class BigIntegerSerializer extends Serializer<BigInteger> {
        public BigIntegerSerializer() {
            setImmutable(true);
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: avoid collision after fix types in other method */
        public BigInteger mo6848read(Kryo kryo, Input input, Class<BigInteger> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            return new BigInteger(input.readBytes(readVarInt - 1));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, BigInteger bigInteger) {
            if (bigInteger == null) {
                output.writeVarInt(0, true);
                return;
            }
            byte[] byteArray = bigInteger.toByteArray();
            output.writeVarInt(byteArray.length + 1, true);
            output.writeBytes(byteArray);
        }
    }

    /* loaded from: classes2.dex */
    public static class BooleanSerializer extends Serializer<Boolean> {
        public BooleanSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Boolean mo6848read(Kryo kryo, Input input, Class<Boolean> cls) {
            return Boolean.valueOf(input.readBoolean());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Boolean bool) {
            output.writeBoolean(bool.booleanValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class ByteSerializer extends Serializer<Byte> {
        public ByteSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Byte mo6848read(Kryo kryo, Input input, Class<Byte> cls) {
            return Byte.valueOf(input.readByte());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Byte b) {
            output.writeByte(b.byteValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class CalendarSerializer extends Serializer<Calendar> {
        private static final long DEFAULT_GREGORIAN_CUTOVER = -12219292800000L;
        TimeZoneSerializer timeZoneSerializer = new TimeZoneSerializer();

        @Override // com.esotericsoftware.kryo.Serializer
        public Calendar copy(Kryo kryo, Calendar calendar) {
            return (Calendar) calendar.clone();
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Calendar mo6848read(Kryo kryo, Input input, Class<Calendar> cls) {
            Calendar calendar = Calendar.getInstance(this.timeZoneSerializer.mo6848read(kryo, input, TimeZone.class));
            calendar.setTimeInMillis(input.readLong(true));
            calendar.setLenient(input.readBoolean());
            calendar.setFirstDayOfWeek(input.readInt(true));
            calendar.setMinimalDaysInFirstWeek(input.readInt(true));
            long readLong = input.readLong(false);
            if (readLong != DEFAULT_GREGORIAN_CUTOVER && (calendar instanceof GregorianCalendar)) {
                ((GregorianCalendar) calendar).setGregorianChange(new Date(readLong));
            }
            return calendar;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Calendar calendar) {
            this.timeZoneSerializer.write(kryo, output, calendar.getTimeZone());
            output.writeLong(calendar.getTimeInMillis(), true);
            output.writeBoolean(calendar.isLenient());
            output.writeInt(calendar.getFirstDayOfWeek(), true);
            output.writeInt(calendar.getMinimalDaysInFirstWeek(), true);
            if (calendar instanceof GregorianCalendar) {
                output.writeLong(((GregorianCalendar) calendar).getGregorianChange().getTime(), false);
            } else {
                output.writeLong(DEFAULT_GREGORIAN_CUTOVER, false);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class CharSerializer extends Serializer<Character> {
        public CharSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Character mo6848read(Kryo kryo, Input input, Class<Character> cls) {
            return Character.valueOf(input.readChar());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Character ch) {
            output.writeChar(ch.charValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class ClassSerializer extends Serializer<Class> {
        public ClassSerializer() {
            setImmutable(true);
            setAcceptsNull(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Class mo6848read(Kryo kryo, Input input, Class<Class> cls) {
            Registration readClass = kryo.readClass(input);
            int read = input.read();
            Class type = readClass != null ? readClass.getType() : null;
            return (type == null || !type.isPrimitive() || read == 1) ? type : Util.getWrapperClass(type);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Class cls) {
            kryo.writeClass(output, cls);
            output.writeByte((cls == null || !cls.isPrimitive()) ? 0 : 1);
        }
    }

    /* loaded from: classes2.dex */
    public static class CollectionsEmptyListSerializer extends Serializer {
        public CollectionsEmptyListSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object mo6848read(Kryo kryo, Input input, Class cls) {
            return Collections.EMPTY_LIST;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
        }
    }

    /* loaded from: classes2.dex */
    public static class CollectionsEmptyMapSerializer extends Serializer {
        public CollectionsEmptyMapSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object mo6848read(Kryo kryo, Input input, Class cls) {
            return Collections.EMPTY_MAP;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
        }
    }

    /* loaded from: classes2.dex */
    public static class CollectionsEmptySetSerializer extends Serializer {
        public CollectionsEmptySetSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object mo6848read(Kryo kryo, Input input, Class cls) {
            return Collections.EMPTY_SET;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
        }
    }

    /* loaded from: classes2.dex */
    public static class CollectionsSingletonListSerializer extends Serializer<List> {
        public CollectionsSingletonListSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public List mo6848read(Kryo kryo, Input input, Class<List> cls) {
            return Collections.singletonList(kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, List list) {
            kryo.writeClassAndObject(output, list.get(0));
        }
    }

    /* loaded from: classes2.dex */
    public static class CollectionsSingletonMapSerializer extends Serializer<Map> {
        public CollectionsSingletonMapSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Map mo6848read(Kryo kryo, Input input, Class<Map> cls) {
            return Collections.singletonMap(kryo.readClassAndObject(input), kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Map map) {
            Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
            kryo.writeClassAndObject(output, entry.getKey());
            kryo.writeClassAndObject(output, entry.getValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class CollectionsSingletonSetSerializer extends Serializer<Set> {
        public CollectionsSingletonSetSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Set mo6848read(Kryo kryo, Input input, Class<Set> cls) {
            return Collections.singleton(kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Set set) {
            kryo.writeClassAndObject(output, set.iterator().next());
        }
    }

    /* loaded from: classes2.dex */
    public static class CurrencySerializer extends Serializer<Currency> {
        public CurrencySerializer() {
            setImmutable(true);
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Currency mo6848read(Kryo kryo, Input input, Class<Currency> cls) {
            String readString = input.readString();
            if (readString == null) {
                return null;
            }
            return Currency.getInstance(readString);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Currency currency) {
            output.writeString(currency == null ? null : currency.getCurrencyCode());
        }
    }

    /* loaded from: classes2.dex */
    public static class DateSerializer extends Serializer<Date> {
        private Date create(Kryo kryo, Class<?> cls, long j) throws KryoException {
            if (cls.equals(Date.class)) {
                return new Date(j);
            }
            if (cls.equals(Timestamp.class)) {
                return new Timestamp(j);
            }
            if (cls.equals(java.sql.Date.class)) {
                return new java.sql.Date(j);
            }
            if (cls.equals(Time.class)) {
                return new Time(j);
            }
            try {
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(Long.TYPE);
                if (declaredConstructor != null) {
                    if (!declaredConstructor.isAccessible()) {
                        try {
                            declaredConstructor.setAccessible(true);
                        } catch (Throwable unused) {
                        }
                    }
                    return (Date) declaredConstructor.newInstance(Long.valueOf(j));
                }
                Date date = (Date) kryo.newInstance(cls);
                date.setTime(j);
                return date;
            } catch (Exception e) {
                throw new KryoException(e);
            }
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Date copy(Kryo kryo, Date date) {
            return create(kryo, date.getClass(), date.getTime());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Date mo6848read(Kryo kryo, Input input, Class<Date> cls) {
            return create(kryo, cls, input.readLong(true));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Date date) {
            output.writeLong(date.getTime(), true);
        }
    }

    /* loaded from: classes2.dex */
    public static class DoubleSerializer extends Serializer<Double> {
        public DoubleSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Double mo6848read(Kryo kryo, Input input, Class<Double> cls) {
            return Double.valueOf(input.readDouble());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Double d) {
            output.writeDouble(d.doubleValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class EnumSerializer extends Serializer<Enum> {
        private Object[] enumConstants;

        public EnumSerializer(Class<? extends Enum> cls) {
            setImmutable(true);
            setAcceptsNull(true);
            this.enumConstants = cls.getEnumConstants();
            if (this.enumConstants != null) {
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline66("The type must be an enum: ", cls));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Enum mo6848read(Kryo kryo, Input input, Class<Enum> cls) {
            int readVarInt = input.readVarInt(true);
            if (readVarInt == 0) {
                return null;
            }
            int i = readVarInt - 1;
            if (i >= 0) {
                Object[] objArr = this.enumConstants;
                if (i <= objArr.length - 1) {
                    return (Enum) objArr[i];
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid ordinal for enum \"");
            outline107.append(cls.getName());
            outline107.append("\": ");
            outline107.append(i);
            throw new KryoException(outline107.toString());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Enum r3) {
            if (r3 == null) {
                output.writeVarInt(0, true);
            } else {
                output.writeVarInt(r3.ordinal() + 1, true);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class EnumSetSerializer extends Serializer<EnumSet> {
        @Override // com.esotericsoftware.kryo.Serializer
        public EnumSet copy(Kryo kryo, EnumSet enumSet) {
            return EnumSet.copyOf(enumSet);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public EnumSet mo6848read(Kryo kryo, Input input, Class<EnumSet> cls) {
            Registration readClass = kryo.readClass(input);
            EnumSet noneOf = EnumSet.noneOf(readClass.getType());
            Serializer serializer = readClass.getSerializer();
            int readInt = input.readInt(true);
            for (int i = 0; i < readInt; i++) {
                noneOf.add(serializer.mo6848read(kryo, input, null));
            }
            return noneOf;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, EnumSet enumSet) {
            Serializer serializer;
            if (enumSet.isEmpty()) {
                EnumSet complementOf = EnumSet.complementOf(enumSet);
                if (!complementOf.isEmpty()) {
                    serializer = kryo.writeClass(output, complementOf.iterator().next().getClass()).getSerializer();
                } else {
                    throw new KryoException("An EnumSet must have a defined Enum to be serialized.");
                }
            } else {
                serializer = kryo.writeClass(output, enumSet.iterator().next().getClass()).getSerializer();
            }
            output.writeInt(enumSet.size(), true);
            Iterator it2 = enumSet.iterator();
            while (it2.hasNext()) {
                serializer.write(kryo, output, it2.next());
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class FloatSerializer extends Serializer<Float> {
        public FloatSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Float mo6848read(Kryo kryo, Input input, Class<Float> cls) {
            return Float.valueOf(input.readFloat());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Float f) {
            output.writeFloat(f.floatValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class IntSerializer extends Serializer<Integer> {
        public IntSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Integer mo6848read(Kryo kryo, Input input, Class<Integer> cls) {
            return Integer.valueOf(input.readInt(false));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Integer num) {
            output.writeInt(num.intValue(), false);
        }
    }

    /* loaded from: classes2.dex */
    public static class KryoSerializableSerializer extends Serializer<KryoSerializable> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public KryoSerializable mo6848read(Kryo kryo, Input input, Class<KryoSerializable> cls) {
            KryoSerializable kryoSerializable = (KryoSerializable) kryo.newInstance(cls);
            kryo.reference(kryoSerializable);
            kryoSerializable.read(kryo, input);
            return kryoSerializable;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, KryoSerializable kryoSerializable) {
            kryoSerializable.write(kryo, output);
        }
    }

    /* loaded from: classes2.dex */
    public static class LocaleSerializer extends Serializer<Locale> {
        public LocaleSerializer() {
            setImmutable(true);
        }

        protected static boolean isSameLocale(Locale locale, String str, String str2, String str3) {
            return locale != null && locale.getLanguage().equals(str) && locale.getCountry().equals(str2) && locale.getVariant().equals(str3);
        }

        protected Locale create(String str, String str2, String str3) {
            if (isSameLocale(Locale.US, str, str2, str3)) {
                return Locale.US;
            }
            if (isSameLocale(Locale.UK, str, str2, str3)) {
                return Locale.UK;
            }
            if (isSameLocale(Locale.ENGLISH, str, str2, str3)) {
                return Locale.ENGLISH;
            }
            if (isSameLocale(Locale.FRENCH, str, str2, str3)) {
                return Locale.FRENCH;
            }
            if (isSameLocale(Locale.GERMAN, str, str2, str3)) {
                return Locale.GERMAN;
            }
            if (isSameLocale(Locale.ITALIAN, str, str2, str3)) {
                return Locale.ITALIAN;
            }
            if (isSameLocale(Locale.FRANCE, str, str2, str3)) {
                return Locale.FRANCE;
            }
            if (isSameLocale(Locale.GERMANY, str, str2, str3)) {
                return Locale.GERMANY;
            }
            if (isSameLocale(Locale.ITALY, str, str2, str3)) {
                return Locale.ITALY;
            }
            if (isSameLocale(Locale.JAPAN, str, str2, str3)) {
                return Locale.JAPAN;
            }
            if (isSameLocale(Locale.KOREA, str, str2, str3)) {
                return Locale.KOREA;
            }
            if (isSameLocale(Locale.CHINA, str, str2, str3)) {
                return Locale.CHINA;
            }
            if (isSameLocale(Locale.PRC, str, str2, str3)) {
                return Locale.PRC;
            }
            if (isSameLocale(Locale.TAIWAN, str, str2, str3)) {
                return Locale.TAIWAN;
            }
            if (isSameLocale(Locale.CANADA, str, str2, str3)) {
                return Locale.CANADA;
            }
            if (isSameLocale(Locale.CANADA_FRENCH, str, str2, str3)) {
                return Locale.CANADA_FRENCH;
            }
            if (isSameLocale(Locale.JAPANESE, str, str2, str3)) {
                return Locale.JAPANESE;
            }
            if (isSameLocale(Locale.KOREAN, str, str2, str3)) {
                return Locale.KOREAN;
            }
            if (isSameLocale(Locale.CHINESE, str, str2, str3)) {
                return Locale.CHINESE;
            }
            if (isSameLocale(Locale.SIMPLIFIED_CHINESE, str, str2, str3)) {
                return Locale.SIMPLIFIED_CHINESE;
            }
            if (isSameLocale(Locale.TRADITIONAL_CHINESE, str, str2, str3)) {
                return Locale.TRADITIONAL_CHINESE;
            }
            return new Locale(str, str2, str3);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public Locale copy(Kryo kryo, Locale locale) {
            return create(locale.getLanguage(), locale.getDisplayCountry(), locale.getVariant());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Locale mo6848read(Kryo kryo, Input input, Class<Locale> cls) {
            return create(input.readString(), input.readString(), input.readString());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Locale locale) {
            output.writeString(locale.getLanguage());
            output.writeString(locale.getCountry());
            output.writeString(locale.getVariant());
        }
    }

    /* loaded from: classes2.dex */
    public static class LongSerializer extends Serializer<Long> {
        public LongSerializer() {
            setImmutable(true);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Long mo6848read(Kryo kryo, Input input, Class<Long> cls) {
            return Long.valueOf(input.readLong(false));
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Long l) {
            output.writeLong(l.longValue(), false);
        }
    }

    /* loaded from: classes2.dex */
    public static class ShortSerializer extends Serializer<Short> {
        public ShortSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public Short mo6848read(Kryo kryo, Input input, Class<Short> cls) {
            return Short.valueOf(input.readShort());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Short sh) {
            output.writeShort(sh.shortValue());
        }
    }

    /* loaded from: classes2.dex */
    public static class StringBufferSerializer extends Serializer<StringBuffer> {
        public StringBufferSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public StringBuffer copy(Kryo kryo, StringBuffer stringBuffer) {
            return new StringBuffer(stringBuffer);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public StringBuffer mo6848read(Kryo kryo, Input input, Class<StringBuffer> cls) {
            String readString = input.readString();
            if (readString == null) {
                return null;
            }
            return new StringBuffer(readString);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, StringBuffer stringBuffer) {
            output.writeString(stringBuffer);
        }
    }

    /* loaded from: classes2.dex */
    public static class StringBuilderSerializer extends Serializer<StringBuilder> {
        public StringBuilderSerializer() {
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public StringBuilder copy(Kryo kryo, StringBuilder sb) {
            return new StringBuilder(sb);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public StringBuilder mo6848read(Kryo kryo, Input input, Class<StringBuilder> cls) {
            return input.readStringBuilder();
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, StringBuilder sb) {
            output.writeString(sb);
        }
    }

    /* loaded from: classes2.dex */
    public static class StringSerializer extends Serializer<String> {
        public StringSerializer() {
            setImmutable(true);
            setAcceptsNull(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public String mo6848read(Kryo kryo, Input input, Class<String> cls) {
            return input.readString();
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, String str) {
            output.writeString(str);
        }
    }

    /* loaded from: classes2.dex */
    public static class TimeZoneSerializer extends Serializer<TimeZone> {
        public TimeZoneSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read  reason: collision with other method in class */
        public TimeZone mo6848read(Kryo kryo, Input input, Class<TimeZone> cls) {
            return TimeZone.getTimeZone(input.readString());
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, TimeZone timeZone) {
            output.writeString(timeZone.getID());
        }
    }

    /* loaded from: classes2.dex */
    public static class TreeMapSerializer extends MapSerializer {
        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        protected Map create(Kryo kryo, Input input, Class<Map> cls) {
            return new TreeMap((Comparator) kryo.readClassAndObject(input));
        }

        @Override // com.esotericsoftware.kryo.serializers.MapSerializer
        protected Map createCopy(Kryo kryo, Map map) {
            return new TreeMap(((TreeMap) map).comparator());
        }

        @Override // com.esotericsoftware.kryo.serializers.MapSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Map map) {
            kryo.writeClassAndObject(output, ((TreeMap) map).comparator());
            super.write(kryo, output, map);
        }
    }

    /* loaded from: classes2.dex */
    public static class TreeSetSerializer extends CollectionSerializer {
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create */
        protected /* bridge */ /* synthetic */ Collection mo6846create(Kryo kryo, Input input, Class cls) {
            return mo6846create(kryo, input, (Class<Collection>) cls);
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: create  reason: collision with other method in class */
        protected TreeSet mo6846create(Kryo kryo, Input input, Class<Collection> cls) {
            return new TreeSet((Comparator) kryo.readClassAndObject(input));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer
        /* renamed from: createCopy  reason: collision with other method in class */
        public TreeSet mo6847createCopy(Kryo kryo, Collection collection) {
            return new TreeSet(((TreeSet) collection).comparator());
        }

        @Override // com.esotericsoftware.kryo.serializers.CollectionSerializer, com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Collection collection) {
            kryo.writeClassAndObject(output, ((TreeSet) collection).comparator());
            super.write(kryo, output, collection);
        }
    }

    /* loaded from: classes2.dex */
    public static class VoidSerializer extends Serializer {
        public VoidSerializer() {
            setImmutable(true);
        }

        @Override // com.esotericsoftware.kryo.Serializer
        /* renamed from: read */
        public Object mo6848read(Kryo kryo, Input input, Class cls) {
            return null;
        }

        @Override // com.esotericsoftware.kryo.Serializer
        public void write(Kryo kryo, Output output, Object obj) {
        }
    }
}
