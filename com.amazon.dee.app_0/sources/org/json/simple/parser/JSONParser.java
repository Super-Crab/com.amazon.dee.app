package org.json.simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/* loaded from: classes5.dex */
public class JSONParser {
    public static final int S_END = 6;
    public static final int S_INIT = 0;
    public static final int S_IN_ARRAY = 3;
    public static final int S_IN_ERROR = -1;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_PASSED_PAIR_KEY = 4;
    private LinkedList handlerStatusStack;
    private Yylex lexer = new Yylex((Reader) null);
    private Yytoken token = null;
    private int status = 0;

    private List createArrayContainer(ContainerFactory containerFactory) {
        List creatArrayContainer;
        return (containerFactory == null || (creatArrayContainer = containerFactory.creatArrayContainer()) == null) ? new JSONArray() : creatArrayContainer;
    }

    private Map createObjectContainer(ContainerFactory containerFactory) {
        Map createObjectContainer;
        return (containerFactory == null || (createObjectContainer = containerFactory.createObjectContainer()) == null) ? new JSONObject() : createObjectContainer;
    }

    private void nextToken() throws ParseException, IOException {
        this.token = this.lexer.yylex();
        if (this.token == null) {
            this.token = new Yytoken(-1, null);
        }
    }

    private int peekStatus(LinkedList linkedList) {
        if (linkedList.size() == 0) {
            return -1;
        }
        return ((Integer) linkedList.getFirst()).intValue();
    }

    public int getPosition() {
        return this.lexer.getPosition();
    }

    public Object parse(Reader reader) throws IOException, ParseException {
        return parse(reader, (ContainerFactory) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0031, code lost:
        if (r1 != 6) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object parse(java.io.Reader r9, org.json.simple.parser.ContainerFactory r10) throws java.io.IOException, org.json.simple.parser.ParseException {
        /*
            Method dump skipped, instructions count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.simple.parser.JSONParser.parse(java.io.Reader, org.json.simple.parser.ContainerFactory):java.lang.Object");
    }

    public Object parse(String str) throws ParseException {
        return parse(str, (ContainerFactory) null);
    }

    public Object parse(String str, ContainerFactory containerFactory) throws ParseException {
        try {
            return parse(new StringReader(str), containerFactory);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void parse(Reader reader, ContentHandler contentHandler) throws IOException, ParseException {
        parse(reader, contentHandler, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r0 != 6) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:124:0x002a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x014a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01dc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002b A[Catch: Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, IOException -> 0x01f4, TryCatch #2 {IOException -> 0x01f4, Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, blocks: (B:10:0x001d, B:11:0x0025, B:97:0x01c2, B:99:0x01c6, B:104:0x01dc, B:105:0x01e7, B:14:0x002b, B:17:0x003b, B:22:0x004a, B:23:0x004e, B:26:0x006c, B:29:0x008a, B:32:0x009e, B:35:0x00a5, B:42:0x00b7, B:44:0x00bd, B:46:0x00c9, B:45:0x00c7, B:49:0x00d0, B:52:0x00e3, B:55:0x00f6, B:58:0x0101, B:63:0x0110, B:65:0x0116, B:67:0x0122, B:66:0x0120, B:70:0x0129, B:72:0x0131, B:75:0x014a, B:77:0x0153, B:79:0x0159, B:80:0x0166, B:81:0x0167, B:86:0x0179, B:89:0x018c, B:92:0x019f, B:95:0x01b6, B:96:0x01c1), top: B:118:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003b A[Catch: Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, IOException -> 0x01f4, TryCatch #2 {IOException -> 0x01f4, Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, blocks: (B:10:0x001d, B:11:0x0025, B:97:0x01c2, B:99:0x01c6, B:104:0x01dc, B:105:0x01e7, B:14:0x002b, B:17:0x003b, B:22:0x004a, B:23:0x004e, B:26:0x006c, B:29:0x008a, B:32:0x009e, B:35:0x00a5, B:42:0x00b7, B:44:0x00bd, B:46:0x00c9, B:45:0x00c7, B:49:0x00d0, B:52:0x00e3, B:55:0x00f6, B:58:0x0101, B:63:0x0110, B:65:0x0116, B:67:0x0122, B:66:0x0120, B:70:0x0129, B:72:0x0131, B:75:0x014a, B:77:0x0153, B:79:0x0159, B:80:0x0166, B:81:0x0167, B:86:0x0179, B:89:0x018c, B:92:0x019f, B:95:0x01b6, B:96:0x01c1), top: B:118:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a5 A[Catch: Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, IOException -> 0x01f4, TryCatch #2 {IOException -> 0x01f4, Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, blocks: (B:10:0x001d, B:11:0x0025, B:97:0x01c2, B:99:0x01c6, B:104:0x01dc, B:105:0x01e7, B:14:0x002b, B:17:0x003b, B:22:0x004a, B:23:0x004e, B:26:0x006c, B:29:0x008a, B:32:0x009e, B:35:0x00a5, B:42:0x00b7, B:44:0x00bd, B:46:0x00c9, B:45:0x00c7, B:49:0x00d0, B:52:0x00e3, B:55:0x00f6, B:58:0x0101, B:63:0x0110, B:65:0x0116, B:67:0x0122, B:66:0x0120, B:70:0x0129, B:72:0x0131, B:75:0x014a, B:77:0x0153, B:79:0x0159, B:80:0x0166, B:81:0x0167, B:86:0x0179, B:89:0x018c, B:92:0x019f, B:95:0x01b6, B:96:0x01c1), top: B:118:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0101 A[Catch: Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, IOException -> 0x01f4, TryCatch #2 {IOException -> 0x01f4, Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, blocks: (B:10:0x001d, B:11:0x0025, B:97:0x01c2, B:99:0x01c6, B:104:0x01dc, B:105:0x01e7, B:14:0x002b, B:17:0x003b, B:22:0x004a, B:23:0x004e, B:26:0x006c, B:29:0x008a, B:32:0x009e, B:35:0x00a5, B:42:0x00b7, B:44:0x00bd, B:46:0x00c9, B:45:0x00c7, B:49:0x00d0, B:52:0x00e3, B:55:0x00f6, B:58:0x0101, B:63:0x0110, B:65:0x0116, B:67:0x0122, B:66:0x0120, B:70:0x0129, B:72:0x0131, B:75:0x014a, B:77:0x0153, B:79:0x0159, B:80:0x0166, B:81:0x0167, B:86:0x0179, B:89:0x018c, B:92:0x019f, B:95:0x01b6, B:96:0x01c1), top: B:118:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0167 A[Catch: Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, IOException -> 0x01f4, TryCatch #2 {IOException -> 0x01f4, Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, blocks: (B:10:0x001d, B:11:0x0025, B:97:0x01c2, B:99:0x01c6, B:104:0x01dc, B:105:0x01e7, B:14:0x002b, B:17:0x003b, B:22:0x004a, B:23:0x004e, B:26:0x006c, B:29:0x008a, B:32:0x009e, B:35:0x00a5, B:42:0x00b7, B:44:0x00bd, B:46:0x00c9, B:45:0x00c7, B:49:0x00d0, B:52:0x00e3, B:55:0x00f6, B:58:0x0101, B:63:0x0110, B:65:0x0116, B:67:0x0122, B:66:0x0120, B:70:0x0129, B:72:0x0131, B:75:0x014a, B:77:0x0153, B:79:0x0159, B:80:0x0166, B:81:0x0167, B:86:0x0179, B:89:0x018c, B:92:0x019f, B:95:0x01b6, B:96:0x01c1), top: B:118:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01c6 A[Catch: Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, IOException -> 0x01f4, TRY_LEAVE, TryCatch #2 {IOException -> 0x01f4, Error -> 0x01e8, RuntimeException -> 0x01ec, ParseException -> 0x01f0, blocks: (B:10:0x001d, B:11:0x0025, B:97:0x01c2, B:99:0x01c6, B:104:0x01dc, B:105:0x01e7, B:14:0x002b, B:17:0x003b, B:22:0x004a, B:23:0x004e, B:26:0x006c, B:29:0x008a, B:32:0x009e, B:35:0x00a5, B:42:0x00b7, B:44:0x00bd, B:46:0x00c9, B:45:0x00c7, B:49:0x00d0, B:52:0x00e3, B:55:0x00f6, B:58:0x0101, B:63:0x0110, B:65:0x0116, B:67:0x0122, B:66:0x0120, B:70:0x0129, B:72:0x0131, B:75:0x014a, B:77:0x0153, B:79:0x0159, B:80:0x0166, B:81:0x0167, B:86:0x0179, B:89:0x018c, B:92:0x019f, B:95:0x01b6, B:96:0x01c1), top: B:118:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parse(java.io.Reader r8, org.json.simple.parser.ContentHandler r9, boolean r10) throws java.io.IOException, org.json.simple.parser.ParseException {
        /*
            Method dump skipped, instructions count: 524
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.simple.parser.JSONParser.parse(java.io.Reader, org.json.simple.parser.ContentHandler, boolean):void");
    }

    public void parse(String str, ContentHandler contentHandler) throws ParseException {
        parse(str, contentHandler, false);
    }

    public void parse(String str, ContentHandler contentHandler, boolean z) throws ParseException {
        try {
            parse(new StringReader(str), contentHandler, z);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void reset() {
        this.token = null;
        this.status = 0;
        this.handlerStatusStack = null;
    }

    public void reset(Reader reader) {
        this.lexer.yyreset(reader);
        reset();
    }
}
