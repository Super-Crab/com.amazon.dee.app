package org.apache.commons.lang;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.sharing.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.facebook.common.callercontext.ContextChain;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes4.dex */
class Entities {
    public static final Entities HTML32;
    public static final Entities HTML40;
    public static final Entities XML;
    private final EntityMap map;
    private static final String[][] BASIC_ARRAY = {new String[]{"quot", "34"}, new String[]{"amp", "38"}, new String[]{"lt", Constants.DEFAULT_SENDMSG_TIMEOUT}, new String[]{"gt", "62"}};
    private static final String[][] APOS_ARRAY = {new String[]{"apos", "39"}};
    static final String[][] ISO8859_1_ARRAY = {new String[]{"nbsp", "160"}, new String[]{"iexcl", "161"}, new String[]{"cent", "162"}, new String[]{"pound", "163"}, new String[]{"curren", "164"}, new String[]{"yen", "165"}, new String[]{"brvbar", "166"}, new String[]{"sect", "167"}, new String[]{"uml", "168"}, new String[]{"copy", "169"}, new String[]{"ordf", "170"}, new String[]{"laquo", "171"}, new String[]{"not", "172"}, new String[]{"shy", "173"}, new String[]{"reg", "174"}, new String[]{"macr", "175"}, new String[]{"deg", "176"}, new String[]{"plusmn", "177"}, new String[]{"sup2", "178"}, new String[]{"sup3", "179"}, new String[]{"acute", "180"}, new String[]{"micro", "181"}, new String[]{"para", "182"}, new String[]{"middot", "183"}, new String[]{"cedil", "184"}, new String[]{"sup1", "185"}, new String[]{"ordm", "186"}, new String[]{"raquo", "187"}, new String[]{"frac14", "188"}, new String[]{"frac12", "189"}, new String[]{"frac34", "190"}, new String[]{"iquest", "191"}, new String[]{"Agrave", "192"}, new String[]{"Aacute", "193"}, new String[]{"Acirc", "194"}, new String[]{"Atilde", "195"}, new String[]{"Auml", "196"}, new String[]{"Aring", "197"}, new String[]{"AElig", "198"}, new String[]{"Ccedil", "199"}, new String[]{"Egrave", "200"}, new String[]{"Eacute", "201"}, new String[]{"Ecirc", "202"}, new String[]{"Euml", "203"}, new String[]{"Igrave", "204"}, new String[]{"Iacute", "205"}, new String[]{"Icirc", "206"}, new String[]{"Iuml", "207"}, new String[]{"ETH", "208"}, new String[]{"Ntilde", "209"}, new String[]{"Ograve", "210"}, new String[]{"Oacute", "211"}, new String[]{"Ocirc", "212"}, new String[]{"Otilde", "213"}, new String[]{"Ouml", "214"}, new String[]{"times", "215"}, new String[]{"Oslash", "216"}, new String[]{"Ugrave", "217"}, new String[]{"Uacute", "218"}, new String[]{"Ucirc", "219"}, new String[]{"Uuml", "220"}, new String[]{"Yacute", "221"}, new String[]{"THORN", "222"}, new String[]{"szlig", "223"}, new String[]{"agrave", "224"}, new String[]{"aacute", "225"}, new String[]{"acirc", "226"}, new String[]{"atilde", "227"}, new String[]{"auml", "228"}, new String[]{"aring", "229"}, new String[]{"aelig", "230"}, new String[]{"ccedil", "231"}, new String[]{"egrave", "232"}, new String[]{"eacute", "233"}, new String[]{"ecirc", "234"}, new String[]{"euml", "235"}, new String[]{"igrave", "236"}, new String[]{"iacute", "237"}, new String[]{"icirc", "238"}, new String[]{"iuml", "239"}, new String[]{"eth", "240"}, new String[]{"ntilde", "241"}, new String[]{"ograve", "242"}, new String[]{"oacute", "243"}, new String[]{"ocirc", "244"}, new String[]{"otilde", "245"}, new String[]{"ouml", "246"}, new String[]{"divide", "247"}, new String[]{"oslash", "248"}, new String[]{"ugrave", "249"}, new String[]{"uacute", "250"}, new String[]{"ucirc", "251"}, new String[]{"uuml", "252"}, new String[]{"yacute", "253"}, new String[]{"thorn", "254"}, new String[]{"yuml", "255"}};
    static final String[][] HTML40_ARRAY = {new String[]{"fnof", "402"}, new String[]{"Alpha", "913"}, new String[]{"Beta", "914"}, new String[]{ExifInterface.TAG_GAMMA, "915"}, new String[]{"Delta", "916"}, new String[]{"Epsilon", "917"}, new String[]{"Zeta", "918"}, new String[]{"Eta", "919"}, new String[]{"Theta", "920"}, new String[]{"Iota", "921"}, new String[]{"Kappa", "922"}, new String[]{"Lambda", "923"}, new String[]{"Mu", "924"}, new String[]{"Nu", "925"}, new String[]{"Xi", "926"}, new String[]{"Omicron", "927"}, new String[]{"Pi", "928"}, new String[]{"Rho", "929"}, new String[]{"Sigma", "931"}, new String[]{"Tau", "932"}, new String[]{"Upsilon", "933"}, new String[]{"Phi", "934"}, new String[]{"Chi", "935"}, new String[]{"Psi", "936"}, new String[]{"Omega", "937"}, new String[]{"alpha", "945"}, new String[]{"beta", "946"}, new String[]{"gamma", "947"}, new String[]{"delta", "948"}, new String[]{"epsilon", "949"}, new String[]{"zeta", "950"}, new String[]{"eta", "951"}, new String[]{"theta", "952"}, new String[]{"iota", "953"}, new String[]{"kappa", "954"}, new String[]{"lambda", "955"}, new String[]{"mu", "956"}, new String[]{"nu", "957"}, new String[]{"xi", "958"}, new String[]{"omicron", "959"}, new String[]{ContextChain.TAG_PRODUCT_AND_INFRA, "960"}, new String[]{"rho", "961"}, new String[]{"sigmaf", "962"}, new String[]{"sigma", "963"}, new String[]{"tau", "964"}, new String[]{"upsilon", "965"}, new String[]{"phi", "966"}, new String[]{"chi", "967"}, new String[]{"psi", "968"}, new String[]{"omega", "969"}, new String[]{"thetasym", "977"}, new String[]{"upsih", "978"}, new String[]{"piv", "982"}, new String[]{"bull", "8226"}, new String[]{"hellip", "8230"}, new String[]{"prime", "8242"}, new String[]{"Prime", "8243"}, new String[]{"oline", "8254"}, new String[]{"frasl", "8260"}, new String[]{"weierp", "8472"}, new String[]{"image", "8465"}, new String[]{"real", "8476"}, new String[]{"trade", "8482"}, new String[]{"alefsym", "8501"}, new String[]{"larr", "8592"}, new String[]{"uarr", "8593"}, new String[]{"rarr", "8594"}, new String[]{"darr", "8595"}, new String[]{"harr", "8596"}, new String[]{"crarr", "8629"}, new String[]{"lArr", "8656"}, new String[]{"uArr", "8657"}, new String[]{"rArr", "8658"}, new String[]{"dArr", "8659"}, new String[]{"hArr", "8660"}, new String[]{"forall", "8704"}, new String[]{"part", "8706"}, new String[]{"exist", "8707"}, new String[]{MetricKeys.EMPTY_VALUE, "8709"}, new String[]{"nabla", "8711"}, new String[]{"isin", "8712"}, new String[]{"notin", "8713"}, new String[]{"ni", "8715"}, new String[]{"prod", "8719"}, new String[]{"sum", "8721"}, new String[]{"minus", "8722"}, new String[]{"lowast", "8727"}, new String[]{"radic", "8730"}, new String[]{"prop", "8733"}, new String[]{"infin", "8734"}, new String[]{"ang", "8736"}, new String[]{"and", "8743"}, new String[]{"or", "8744"}, new String[]{"cap", "8745"}, new String[]{"cup", "8746"}, new String[]{"int", "8747"}, new String[]{"there4", "8756"}, new String[]{"sim", "8764"}, new String[]{"cong", "8773"}, new String[]{"asymp", "8776"}, new String[]{"ne", "8800"}, new String[]{"equiv", "8801"}, new String[]{"le", "8804"}, new String[]{"ge", "8805"}, new String[]{"sub", "8834"}, new String[]{"sup", "8835"}, new String[]{"sube", "8838"}, new String[]{"supe", "8839"}, new String[]{"oplus", "8853"}, new String[]{"otimes", "8855"}, new String[]{"perp", "8869"}, new String[]{"sdot", "8901"}, new String[]{"lceil", "8968"}, new String[]{"rceil", "8969"}, new String[]{"lfloor", "8970"}, new String[]{"rfloor", "8971"}, new String[]{"lang", "9001"}, new String[]{"rang", "9002"}, new String[]{"loz", "9674"}, new String[]{"spades", "9824"}, new String[]{"clubs", "9827"}, new String[]{"hearts", "9829"}, new String[]{"diams", "9830"}, new String[]{"OElig", "338"}, new String[]{"oelig", "339"}, new String[]{"Scaron", "352"}, new String[]{"scaron", "353"}, new String[]{"Yuml", "376"}, new String[]{"circ", "710"}, new String[]{"tilde", "732"}, new String[]{"ensp", "8194"}, new String[]{"emsp", "8195"}, new String[]{"thinsp", "8201"}, new String[]{"zwnj", "8204"}, new String[]{"zwj", "8205"}, new String[]{"lrm", "8206"}, new String[]{"rlm", "8207"}, new String[]{"ndash", "8211"}, new String[]{"mdash", "8212"}, new String[]{"lsquo", "8216"}, new String[]{"rsquo", "8217"}, new String[]{"sbquo", "8218"}, new String[]{"ldquo", "8220"}, new String[]{"rdquo", "8221"}, new String[]{"bdquo", "8222"}, new String[]{"dagger", "8224"}, new String[]{"Dagger", "8225"}, new String[]{"permil", "8240"}, new String[]{"lsaquo", "8249"}, new String[]{"rsaquo", "8250"}, new String[]{"euro", "8364"}};

    /* loaded from: classes4.dex */
    static class BinaryEntityMap extends ArrayEntityMap {
        public BinaryEntityMap() {
        }

        private int binarySearch(int i) {
            int i2 = this.size - 1;
            int i3 = 0;
            while (i3 <= i2) {
                int i4 = (i3 + i2) >>> 1;
                int i5 = this.values[i4];
                if (i5 < i) {
                    i3 = i4 + 1;
                } else if (i5 <= i) {
                    return i4;
                } else {
                    i2 = i4 - 1;
                }
            }
            return -(i3 + 1);
        }

        @Override // org.apache.commons.lang.Entities.ArrayEntityMap, org.apache.commons.lang.Entities.EntityMap
        public void add(String str, int i) {
            ensureCapacity(this.size + 1);
            int binarySearch = binarySearch(i);
            if (binarySearch > 0) {
                return;
            }
            int i2 = -(binarySearch + 1);
            int[] iArr = this.values;
            int i3 = i2 + 1;
            System.arraycopy(iArr, i2, iArr, i3, this.size - i2);
            this.values[i2] = i;
            String[] strArr = this.names;
            System.arraycopy(strArr, i2, strArr, i3, this.size - i2);
            this.names[i2] = str;
            this.size++;
        }

        @Override // org.apache.commons.lang.Entities.ArrayEntityMap, org.apache.commons.lang.Entities.EntityMap
        public String name(int i) {
            int binarySearch = binarySearch(i);
            if (binarySearch < 0) {
                return null;
            }
            return this.names[binarySearch];
        }

        public BinaryEntityMap(int i) {
            super(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface EntityMap {
        void add(String str, int i);

        String name(int i);

        int value(String str);
    }

    /* loaded from: classes4.dex */
    static class HashEntityMap extends MapIntMap {
        public HashEntityMap() {
            super(new HashMap(), new HashMap());
        }
    }

    /* loaded from: classes4.dex */
    static class LookupEntityMap extends PrimitiveEntityMap {
        private static final int LOOKUP_TABLE_SIZE = 256;
        private String[] lookupTable;

        LookupEntityMap() {
        }

        private void createLookupTable() {
            this.lookupTable = new String[256];
            for (int i = 0; i < 256; i++) {
                this.lookupTable[i] = super.name(i);
            }
        }

        private String[] lookupTable() {
            if (this.lookupTable == null) {
                createLookupTable();
            }
            return this.lookupTable;
        }

        @Override // org.apache.commons.lang.Entities.PrimitiveEntityMap, org.apache.commons.lang.Entities.EntityMap
        public String name(int i) {
            if (i < 256) {
                return lookupTable()[i];
            }
            return super.name(i);
        }
    }

    /* loaded from: classes4.dex */
    static abstract class MapIntMap implements EntityMap {
        protected final Map mapNameToValue;
        protected final Map mapValueToName;

        MapIntMap(Map map, Map map2) {
            this.mapNameToValue = map;
            this.mapValueToName = map2;
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public void add(String str, int i) {
            this.mapNameToValue.put(str, new Integer(i));
            this.mapValueToName.put(new Integer(i), str);
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public String name(int i) {
            return (String) this.mapValueToName.get(new Integer(i));
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public int value(String str) {
            Object obj = this.mapNameToValue.get(str);
            if (obj == null) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class PrimitiveEntityMap implements EntityMap {
        private final Map mapNameToValue = new HashMap();
        private final IntHashMap mapValueToName = new IntHashMap();

        PrimitiveEntityMap() {
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public void add(String str, int i) {
            this.mapNameToValue.put(str, new Integer(i));
            this.mapValueToName.put(i, str);
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public String name(int i) {
            return (String) this.mapValueToName.get(i);
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public int value(String str) {
            Object obj = this.mapNameToValue.get(str);
            if (obj == null) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }
    }

    /* loaded from: classes4.dex */
    static class TreeEntityMap extends MapIntMap {
        public TreeEntityMap() {
            super(new TreeMap(), new TreeMap());
        }
    }

    static {
        Entities entities = new Entities();
        entities.addEntities(BASIC_ARRAY);
        entities.addEntities(APOS_ARRAY);
        XML = entities;
        Entities entities2 = new Entities();
        entities2.addEntities(BASIC_ARRAY);
        entities2.addEntities(ISO8859_1_ARRAY);
        HTML32 = entities2;
        Entities entities3 = new Entities();
        fillWithHtml40Entities(entities3);
        HTML40 = entities3;
    }

    public Entities() {
        this.map = new LookupEntityMap();
    }

    private StringWriter createStringWriter(String str) {
        return new StringWriter((int) ((str.length() * 0.1d) + str.length()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0067, code lost:
        if (r2 > 65535) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void doUnescape(java.io.Writer r11, java.lang.String r12, int r13) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
            r11.write(r12, r0, r13)
            int r1 = r12.length()
        L8:
            if (r13 >= r1) goto L86
            char r2 = r12.charAt(r13)
            r3 = 38
            r4 = 1
            if (r2 != r3) goto L81
            int r5 = r13 + 1
            r6 = 59
            int r7 = r12.indexOf(r6, r5)
            r8 = -1
            if (r7 != r8) goto L22
            r11.write(r2)
            goto L84
        L22:
            int r9 = r12.indexOf(r3, r5)
            if (r9 == r8) goto L2e
            if (r9 >= r7) goto L2e
            r11.write(r2)
            goto L84
        L2e:
            java.lang.String r13 = r12.substring(r5, r7)
            int r2 = r13.length()
            if (r2 <= 0) goto L6f
            char r5 = r13.charAt(r0)
            r9 = 35
            if (r5 != r9) goto L6a
            if (r2 <= r4) goto L6f
            char r2 = r13.charAt(r4)
            r5 = 88
            if (r2 == r5) goto L59
            r5 = 120(0x78, float:1.68E-43)
            if (r2 == r5) goto L59
            java.lang.String r2 = r13.substring(r4)     // Catch: java.lang.NumberFormatException -> L6f
            r5 = 10
            int r2 = java.lang.Integer.parseInt(r2, r5)     // Catch: java.lang.NumberFormatException -> L6f
            goto L64
        L59:
            r2 = 2
            java.lang.String r2 = r13.substring(r2)     // Catch: java.lang.NumberFormatException -> L6f
            r5 = 16
            int r2 = java.lang.Integer.parseInt(r2, r5)     // Catch: java.lang.NumberFormatException -> L6f
        L64:
            r5 = 65535(0xffff, float:9.1834E-41)
            if (r2 <= r5) goto L70
            goto L6f
        L6a:
            int r2 = r10.entityValue(r13)
            goto L70
        L6f:
            r2 = r8
        L70:
            if (r2 != r8) goto L7c
            r11.write(r3)
            r11.write(r13)
            r11.write(r6)
            goto L7f
        L7c:
            r11.write(r2)
        L7f:
            r13 = r7
            goto L84
        L81:
            r11.write(r2)
        L84:
            int r13 = r13 + r4
            goto L8
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.Entities.doUnescape(java.io.Writer, java.lang.String, int):void");
    }

    static void fillWithHtml40Entities(Entities entities) {
        entities.addEntities(BASIC_ARRAY);
        entities.addEntities(ISO8859_1_ARRAY);
        entities.addEntities(HTML40_ARRAY);
    }

    public void addEntities(String[][] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            addEntity(strArr[i][0], Integer.parseInt(strArr[i][1]));
        }
    }

    public void addEntity(String str, int i) {
        this.map.add(str, i);
    }

    public String entityName(int i) {
        return this.map.name(i);
    }

    public int entityValue(String str) {
        return this.map.value(str);
    }

    public String escape(String str) {
        StringWriter createStringWriter = createStringWriter(str);
        try {
            escape(createStringWriter, str);
            return createStringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public String unescape(String str) {
        int indexOf = str.indexOf(38);
        if (indexOf < 0) {
            return str;
        }
        StringWriter createStringWriter = createStringWriter(str);
        try {
            doUnescape(createStringWriter, str, indexOf);
            return createStringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    Entities(EntityMap entityMap) {
        this.map = entityMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ArrayEntityMap implements EntityMap {
        protected final int growBy;
        protected String[] names;
        protected int size;
        protected int[] values;

        public ArrayEntityMap() {
            this.size = 0;
            this.growBy = 100;
            int i = this.growBy;
            this.names = new String[i];
            this.values = new int[i];
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public void add(String str, int i) {
            ensureCapacity(this.size + 1);
            String[] strArr = this.names;
            int i2 = this.size;
            strArr[i2] = str;
            this.values[i2] = i;
            this.size = i2 + 1;
        }

        protected void ensureCapacity(int i) {
            if (i > this.names.length) {
                int max = Math.max(i, this.size + this.growBy);
                String[] strArr = new String[max];
                System.arraycopy(this.names, 0, strArr, 0, this.size);
                this.names = strArr;
                int[] iArr = new int[max];
                System.arraycopy(this.values, 0, iArr, 0, this.size);
                this.values = iArr;
            }
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public String name(int i) {
            for (int i2 = 0; i2 < this.size; i2++) {
                if (this.values[i2] == i) {
                    return this.names[i2];
                }
            }
            return null;
        }

        @Override // org.apache.commons.lang.Entities.EntityMap
        public int value(String str) {
            for (int i = 0; i < this.size; i++) {
                if (this.names[i].equals(str)) {
                    return this.values[i];
                }
            }
            return -1;
        }

        public ArrayEntityMap(int i) {
            this.size = 0;
            this.growBy = i;
            this.names = new String[i];
            this.values = new int[i];
        }
    }

    public void escape(Writer writer, String str) throws IOException {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            String entityName = entityName(charAt);
            if (entityName != null) {
                writer.write(38);
                writer.write(entityName);
                writer.write(59);
            } else if (charAt > 127) {
                writer.write("&#");
                writer.write(Integer.toString(charAt, 10));
                writer.write(59);
            } else {
                writer.write(charAt);
            }
        }
    }

    public void unescape(Writer writer, String str) throws IOException {
        int indexOf = str.indexOf(38);
        if (indexOf < 0) {
            writer.write(str);
        } else {
            doUnescape(writer, str, indexOf);
        }
    }
}
