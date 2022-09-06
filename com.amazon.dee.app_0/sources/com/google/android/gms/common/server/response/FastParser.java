package com.google.android.gms.common.server.response;

import android.util.Log;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.C;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import okio.internal.BufferKt;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes2.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqg = {'u', 'l', 'l'};
    private static final char[] zaqh = {'r', 'u', 'e'};
    private static final char[] zaqi = {'r', 'u', 'e', '\"'};
    private static final char[] zaqj = {'a', 'l', 's', 'e'};
    private static final char[] zaqk = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zaql = {'\n'};
    private static final zaa<Integer> zaqn = new com.google.android.gms.common.server.response.zaa();
    private static final zaa<Long> zaqo = new zab();
    private static final zaa<Float> zaqp = new zac();
    private static final zaa<Double> zaqq = new zad();
    private static final zaa<Boolean> zaqr = new zae();
    private static final zaa<String> zaqs = new zaf();
    private static final zaa<BigInteger> zaqt = new zag();
    private static final zaa<BigDecimal> zaqu = new zah();
    private final char[] zaqb = new char[1];
    private final char[] zaqc = new char[32];
    private final char[] zaqd = new char[1024];
    private final StringBuilder zaqe = new StringBuilder(32);
    private final StringBuilder zaqf = new StringBuilder(1024);
    private final Stack<Integer> zaqm = new Stack<>();

    @ShowFirstParty
    @KeepForSdk
    /* loaded from: classes2.dex */
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        HashMap hashMap;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String zaa2 = zaa(bufferedReader);
        if (zaa2 == null) {
            zak(1);
            return false;
        }
        while (zaa2 != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(zaa2);
            if (field == null) {
                zaa2 = zab(bufferedReader);
            } else {
                int i = 4;
                this.zaqm.push(4);
                int i2 = field.zapr;
                switch (i2) {
                    case 0:
                        if (field.zaps) {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, (ArrayList<Integer>) zaa(bufferedReader, zaqn));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zad(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 1:
                        if (field.zaps) {
                            fastJsonResponse.zab((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zaa(bufferedReader, zaqt));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zaf(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 2:
                        if (field.zaps) {
                            fastJsonResponse.zac(field, zaa(bufferedReader, zaqo));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zae(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 3:
                        if (field.zaps) {
                            fastJsonResponse.zad(field, zaa(bufferedReader, zaqp));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zag(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 4:
                        if (field.zaps) {
                            fastJsonResponse.zae(field, zaa(bufferedReader, zaqq));
                        } else {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 5:
                        if (field.zaps) {
                            fastJsonResponse.zaf(field, zaa(bufferedReader, zaqu));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zai(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 6:
                        if (field.zaps) {
                            fastJsonResponse.zag(field, zaa(bufferedReader, zaqr));
                        } else {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                        }
                        i = 4;
                        break;
                    case 7:
                        if (field.zaps) {
                            fastJsonResponse.zah(field, zaa(bufferedReader, zaqs));
                        } else {
                            fastJsonResponse.zaa((FastJsonResponse.Field) field, zac(bufferedReader));
                        }
                        i = 4;
                        break;
                    case 8:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decode(zaa(bufferedReader, this.zaqd, this.zaqf, zaql)));
                        i = 4;
                        break;
                    case 9:
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqd, this.zaqf, zaql)));
                        i = 4;
                        break;
                    case 10:
                        char zaj = zaj(bufferedReader);
                        if (zaj == 'n') {
                            zab(bufferedReader, zaqg);
                            hashMap = null;
                        } else if (zaj == '{') {
                            this.zaqm.push(1);
                            hashMap = new HashMap();
                            while (true) {
                                char zaj2 = zaj(bufferedReader);
                                if (zaj2 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                }
                                if (zaj2 == '\"') {
                                    String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
                                    if (zaj(bufferedReader) != ':') {
                                        String valueOf = String.valueOf(zab);
                                        throw new ParseException(valueOf.length() != 0 ? "No map value found for key ".concat(valueOf) : new String("No map value found for key "));
                                    } else if (zaj(bufferedReader) != '\"') {
                                        String valueOf2 = String.valueOf(zab);
                                        throw new ParseException(valueOf2.length() != 0 ? "Expected String value for key ".concat(valueOf2) : new String("Expected String value for key "));
                                    } else {
                                        hashMap.put(zab, zab(bufferedReader, this.zaqc, this.zaqe, null));
                                        char zaj3 = zaj(bufferedReader);
                                        if (zaj3 != ',') {
                                            if (zaj3 == '}') {
                                                zak(1);
                                            } else {
                                                StringBuilder sb = new StringBuilder(48);
                                                sb.append("Unexpected character while parsing string map: ");
                                                sb.append(zaj3);
                                                throw new ParseException(sb.toString());
                                            }
                                        }
                                    }
                                } else if (zaj2 == '}') {
                                    zak(1);
                                }
                                i = 4;
                                break;
                            }
                        } else {
                            throw new ParseException("Expected start of a map object");
                        }
                        fastJsonResponse.zaa((FastJsonResponse.Field) field, (Map<String, String>) hashMap);
                        i = 4;
                    case 11:
                        if (field.zaps) {
                            char zaj4 = zaj(bufferedReader);
                            if (zaj4 == 'n') {
                                zab(bufferedReader, zaqg);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, null);
                                break;
                            } else {
                                this.zaqm.push(5);
                                if (zaj4 == '[') {
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapv, zaa(bufferedReader, field));
                                    break;
                                } else {
                                    throw new ParseException("Expected array start");
                                }
                            }
                        } else {
                            char zaj5 = zaj(bufferedReader);
                            if (zaj5 != 'n') {
                                this.zaqm.push(1);
                                if (zaj5 == '{') {
                                    try {
                                        FastJsonResponse zacp = field.zacp();
                                        zaa(bufferedReader, zacp);
                                        fastJsonResponse.addConcreteTypeInternal(field, field.zapv, zacp);
                                        break;
                                    } catch (IllegalAccessException e) {
                                        throw new ParseException("Error instantiating inner object", e);
                                    } catch (InstantiationException e2) {
                                        throw new ParseException("Error instantiating inner object", e2);
                                    }
                                } else {
                                    throw new ParseException("Expected start of object");
                                }
                            } else {
                                zab(bufferedReader, zaqg);
                                fastJsonResponse.addConcreteTypeInternal(field, field.zapv, null);
                                break;
                            }
                        }
                    default:
                        throw new ParseException(GeneratedOutlineSupport1.outline27(30, "Invalid field type ", i2));
                }
                zak(i);
                zak(2);
                char zaj6 = zaj(bufferedReader);
                if (zaj6 == ',') {
                    zaa2 = zaa(bufferedReader);
                } else if (zaj6 != '}') {
                    StringBuilder sb2 = new StringBuilder(55);
                    sb2.append("Expected end of object or field separator, but found: ");
                    sb2.append(zaj6);
                    throw new ParseException(sb2.toString());
                } else {
                    zaa2 = null;
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            if (bufferedReader.read(this.zaqb) != -1) {
                char c = this.zaqb[0];
                boolean z = false;
                do {
                    if (c != '\"' || z) {
                        z = c == '\\' ? !z : false;
                        if (bufferedReader.read(this.zaqb) != -1) {
                            c = this.zaqb[0];
                        } else {
                            throw new ParseException("Unexpected EOF while parsing string");
                        }
                    }
                } while (!Character.isISOControl(c));
                throw new ParseException("Unexpected control character while reading string");
            }
            throw new ParseException("Unexpected EOF while parsing string");
        } else if (zaj == ',') {
            throw new ParseException("Missing value");
        } else {
            int i = 1;
            if (zaj == '[') {
                this.zaqm.push(5);
                bufferedReader.mark(32);
                if (zaj(bufferedReader) == ']') {
                    zak(5);
                } else {
                    bufferedReader.reset();
                    boolean z2 = false;
                    boolean z3 = false;
                    while (i > 0) {
                        char zaj2 = zaj(bufferedReader);
                        if (zaj2 != 0) {
                            if (Character.isISOControl(zaj2)) {
                                throw new ParseException("Unexpected control character while reading array");
                            }
                            if (zaj2 == '\"' && !z2) {
                                z3 = !z3;
                            }
                            if (zaj2 == '[' && !z3) {
                                i++;
                            }
                            if (zaj2 == ']' && !z3) {
                                i--;
                            }
                            z2 = (zaj2 != '\\' || !z3) ? false : !z2;
                        } else {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                    }
                    zak(5);
                }
            } else if (zaj != '{') {
                bufferedReader.reset();
                zaa(bufferedReader, this.zaqd);
            } else {
                this.zaqm.push(1);
                bufferedReader.mark(32);
                char zaj3 = zaj(bufferedReader);
                if (zaj3 == '}') {
                    zak(1);
                } else if (zaj3 == '\"') {
                    bufferedReader.reset();
                    zaa(bufferedReader);
                    do {
                    } while (zab(bufferedReader) != null);
                    zak(1);
                } else {
                    StringBuilder sb = new StringBuilder(18);
                    sb.append("Unexpected token ");
                    sb.append(zaj3);
                    throw new ParseException(sb.toString());
                }
            }
        }
        char zaj4 = zaj(bufferedReader);
        if (zaj4 == ',') {
            zak(2);
            return zaa(bufferedReader);
        } else if (zaj4 == '}') {
            zak(2);
            return null;
        } else {
            StringBuilder sb2 = new StringBuilder(18);
            sb2.append("Unexpected token ");
            sb2.append(zaj4);
            throw new ParseException(sb2.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zaqc, this.zaqe, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0;
        }
        char[] cArr = this.zaqd;
        if (zaa2 > 0) {
            if (cArr[0] == '-') {
                i = Integer.MIN_VALUE;
                i2 = 1;
                z = true;
            } else {
                z = false;
                i = -2147483647;
                i2 = 0;
            }
            if (i2 < zaa2) {
                i4 = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i3 = -digit;
            } else {
                int i5 = i2;
                i3 = 0;
                i4 = i5;
            }
            while (i4 < zaa2) {
                int i6 = i4 + 1;
                int digit2 = Character.digit(cArr[i4], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (i3 < -214748364) {
                    throw new ParseException("Number too large");
                }
                int i7 = i3 * 10;
                if (i7 < i + digit2) {
                    throw new ParseException("Number too large");
                }
                i3 = i7 - digit2;
                i4 = i6;
            }
            if (!z) {
                return -i3;
            }
            if (i4 <= 1) {
                throw new ParseException("No digits to parse");
            }
            return i3;
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        boolean z;
        long j2;
        int i;
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0L;
        }
        char[] cArr = this.zaqd;
        if (zaa2 > 0) {
            int i2 = 0;
            if (cArr[0] == '-') {
                j = Long.MIN_VALUE;
                i2 = 1;
                z = true;
            } else {
                j = C.TIME_UNSET;
                z = false;
            }
            if (i2 < zaa2) {
                i = i2 + 1;
                int digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                j2 = -digit;
            } else {
                j2 = 0;
                i = i2;
            }
            while (i < zaa2) {
                int i3 = i + 1;
                int digit2 = Character.digit(cArr[i], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (j2 < BufferKt.OVERFLOW_ZONE) {
                    throw new ParseException("Number too large");
                }
                long j3 = j2 * 10;
                boolean z2 = z;
                long j4 = digit2;
                if (j3 < j + j4) {
                    throw new ParseException("Number too large");
                }
                j2 = j3 - j4;
                i = i3;
                z = z2;
            }
            if (!z) {
                return -j2;
            }
            if (i <= 1) {
                throw new ParseException("No digits to parse");
            }
            return j2;
        }
        throw new ParseException("No number to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqd, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqd, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        return zaa2 == 0 ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : Double.parseDouble(new String(this.zaqd, 0, zaa2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa2 = zaa(bufferedReader, this.zaqd);
        if (zaa2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqd, 0, zaa2));
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaqb) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zaqb[0])) {
            if (bufferedReader.read(this.zaqb) == -1) {
                return (char) 0;
            }
        }
        return this.zaqb[0];
    }

    private final void zak(int i) throws ParseException {
        if (!this.zaqm.isEmpty()) {
            int intValue = this.zaqm.pop().intValue();
            if (intValue != i) {
                throw new ParseException(GeneratedOutlineSupport1.outline28(46, "Expected state ", i, " but had ", intValue));
            }
            return;
        }
        StringBuilder sb = new StringBuilder(46);
        sb.append("Expected state ");
        sb.append(i);
        sb.append(" but had empty stack");
        throw new ParseException(sb.toString());
    }

    @KeepForSdk
    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zaqm.push(0);
                char zaj = zaj(bufferedReader);
                if (zaj != 0) {
                    if (zaj == '[') {
                        this.zaqm.push(5);
                        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                        if (fieldMappings.size() == 1) {
                            FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                            t.addConcreteTypeArrayInternal(value, value.zapv, zaa(bufferedReader, value));
                        } else {
                            throw new ParseException("Object array response class must have a single Field");
                        }
                    } else if (zaj == '{') {
                        this.zaqm.push(1);
                        zaa(bufferedReader, t);
                    } else {
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(zaj);
                        throw new ParseException(sb.toString());
                    }
                    zak(0);
                    try {
                        bufferedReader.close();
                        return;
                    } catch (IOException unused) {
                        Log.w("FastParser", "Failed to close reader while parsing.");
                        return;
                    }
                }
                throw new ParseException("No data to parse");
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        boolean z;
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                boolean z4 = z3;
                boolean z5 = z2;
                for (int i = 0; i < read; i++) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        if (cArr2 != null) {
                            for (char c2 : cArr2) {
                                if (c2 == c) {
                                    z = true;
                                    break;
                                }
                            }
                        }
                        z = false;
                        if (!z) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                    }
                    if (c == '\"' && !z5) {
                        sb.append(cArr, 0, i);
                        bufferedReader.reset();
                        bufferedReader.skip(i + 1);
                        if (z4) {
                            return JsonUtils.unescapeString(sb.toString());
                        }
                        return sb.toString();
                    }
                    if (c == '\\') {
                        z5 = !z5;
                        z4 = true;
                    } else {
                        z5 = false;
                    }
                }
                sb.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
                z2 = z5;
                z3 = z4;
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zaqc, 0, cArr.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zaqc[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zaqm.push(2);
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            this.zaqm.push(3);
            String zab = zab(bufferedReader, this.zaqc, this.zaqe, null);
            zak(3);
            if (zaj(bufferedReader) != ':') {
                throw new ParseException("Expected key/value separator");
            }
            return zab;
        } else if (zaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        } else if (zaj == '}') {
            zak(2);
            return null;
        } else {
            StringBuilder sb = new StringBuilder(19);
            sb.append("Unexpected token: ");
            sb.append(zaj);
            throw new ParseException(sb.toString());
        }
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaaVar) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            return null;
        } else if (zaj == '[') {
            this.zaqm.push(5);
            ArrayList<O> arrayList = new ArrayList<>();
            while (true) {
                bufferedReader.mark(1024);
                char zaj2 = zaj(bufferedReader);
                if (zaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (zaj2 != ',') {
                    if (zaj2 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaaVar.zah(this, bufferedReader));
                    } else {
                        zak(5);
                        return arrayList;
                    }
                }
            }
        } else {
            throw new ParseException("Expected start of array");
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj != '\"') {
            if (zaj == 'n') {
                zab(bufferedReader, zaqg);
                return null;
            }
            throw new ParseException("Expected string");
        }
        return zab(bufferedReader, cArr, sb, cArr2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char zaj = zaj(bufferedReader);
            if (zaj != '\"') {
                if (zaj == 'f') {
                    zab(bufferedReader, z ? zaqk : zaqj);
                    return false;
                } else if (zaj == 'n') {
                    zab(bufferedReader, zaqg);
                    return false;
                } else if (zaj == 't') {
                    zab(bufferedReader, z ? zaqi : zaqh);
                    return true;
                } else {
                    StringBuilder sb = new StringBuilder(19);
                    sb.append("Unexpected token: ");
                    sb.append(zaj);
                    throw new ParseException(sb.toString());
                }
            } else if (z) {
                throw new ParseException("No boolean value found in string");
            } else {
                z = true;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        ArrayList<T> arrayList = (ArrayList<T>) new ArrayList();
        char zaj = zaj(bufferedReader);
        if (zaj == ']') {
            zak(5);
            return arrayList;
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqg);
            zak(5);
            return null;
        } else if (zaj == '{') {
            this.zaqm.push(1);
            while (true) {
                try {
                    FastJsonResponse zacp = field.zacp();
                    if (!zaa(bufferedReader, zacp)) {
                        return arrayList;
                    }
                    arrayList.add(zacp);
                    char zaj2 = zaj(bufferedReader);
                    if (zaj2 != ',') {
                        if (zaj2 == ']') {
                            zak(5);
                            return arrayList;
                        }
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(zaj2);
                        throw new ParseException(sb.toString());
                    } else if (zaj(bufferedReader) == '{') {
                        this.zaqm.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (IllegalAccessException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (InstantiationException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Unexpected token: ");
            sb2.append(zaj);
            throw new ParseException(sb2.toString());
        }
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char zaj = zaj(bufferedReader);
        if (zaj != 0) {
            if (zaj == ',') {
                throw new ParseException("Missing value");
            }
            if (zaj == 'n') {
                zab(bufferedReader, zaqg);
                return 0;
            }
            bufferedReader.mark(1024);
            if (zaj == '\"') {
                i = 0;
                boolean z = false;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        throw new ParseException("Unexpected control character while reading string");
                    }
                    if (c == '\"' && !z) {
                        bufferedReader.reset();
                        bufferedReader.skip(i + 1);
                        return i;
                    }
                    z = c == '\\' ? !z : false;
                    i++;
                }
            } else {
                cArr[0] = zaj;
                i = 1;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                        bufferedReader.reset();
                        bufferedReader.skip(i - 1);
                        cArr[i] = 0;
                        return i;
                    }
                    i++;
                }
            }
            if (i == cArr.length) {
                throw new ParseException("Absurdly long value");
            }
            throw new ParseException("Unexpected EOF");
        }
        throw new ParseException("Unexpected EOF");
    }
}
