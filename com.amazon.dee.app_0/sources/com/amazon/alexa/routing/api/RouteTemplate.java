package com.amazon.alexa.routing.api;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class RouteTemplate implements Parcelable {
    private static final String DELIMITER = "/";
    private static final String QUERYSTRING_DELIMITER = "&";
    private final String template;
    private static final String TAG = RouteTemplate.class.getSimpleName();
    public static final Parcelable.Creator<RouteTemplate> CREATOR = new Parcelable.Creator<RouteTemplate>() { // from class: com.amazon.alexa.routing.api.RouteTemplate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RouteTemplate mo2395createFromParcel(Parcel parcel) {
            return new RouteTemplate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RouteTemplate[] mo2396newArray(int i) {
            return new RouteTemplate[i];
        }
    };
    private final Map<String, Token> queryParams = new HashMap();
    private final Map<String, Token> tokenMap = new HashMap();
    private final List<Token> tokens = new ArrayList();

    /* renamed from: com.amazon.alexa.routing.api.RouteTemplate$2  reason: invalid class name */
    /* loaded from: classes10.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$routing$api$RouteTemplate$Token$Type = new int[Token.Type.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$routing$api$RouteTemplate$Token$Type[Token.Type.CatchAll.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$routing$api$RouteTemplate$Token$Type[Token.Type.Optional.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public RouteTemplate(String str) {
        this.template = str;
        parse(str, this.queryParams, this.tokens, this.tokenMap);
    }

    private String compilePath(RouteContext routeContext) throws UnsupportedEncodingException {
        ArrayList arrayList = new ArrayList();
        for (Token token : this.tokens) {
            if (token.type != Token.Type.Literal) {
                Object obj = routeContext.get(token.value);
                if (obj != null) {
                    arrayList.add(encode(obj.toString()));
                }
            } else {
                arrayList.add(encode(token.value.toString()));
            }
        }
        return TextUtils.join("/", arrayList);
    }

    private String compileQueryString(RouteContext routeContext) throws UnsupportedEncodingException {
        if (this.queryParams.size() == 0) {
            return routeContext.getString(BilobaRouteUtil.RAW_QUERY_STRING, "");
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Token> entry : this.queryParams.entrySet()) {
            String key = entry.getKey();
            Token value = entry.getValue();
            if (value.type == Token.Type.Literal) {
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(key, Config.Compare.EQUAL_TO);
                outline113.append(value.value);
                arrayList.add(outline113.toString());
            } else {
                Object obj = routeContext.get(value.value);
                if (obj != null) {
                    arrayList.add(key + Config.Compare.EQUAL_TO + obj);
                } else if (value.type != Token.Type.Optional) {
                    Log.e(TAG, "Route context is missing parameter");
                    throw new IllegalArgumentException("Route context is missing parameter");
                }
            }
        }
        return arrayList.size() > 0 ? TextUtils.join("&", arrayList) : "";
    }

    private String decode(String str) throws UnsupportedEncodingException {
        String[] split = str.split("/");
        String[] strArr = new String[split.length];
        for (int i = 0; i < 1; i++) {
            strArr[i] = Uri.decode(split[i]);
        }
        return TextUtils.join("/", strArr);
    }

    private String encode(String str) throws UnsupportedEncodingException {
        String[] split = str.split("/", -1);
        String[] strArr = new String[split.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = Uri.encode(split[i]);
        }
        return TextUtils.join("/", strArr);
    }

    private static HashMap<String, Token> extractTokens(HashMap<String, String> hashMap) {
        HashMap<String, Token> hashMap2 = new HashMap<>();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            hashMap2.put(entry.getKey(), parseToken(entry.getValue()));
        }
        return hashMap2;
    }

    private boolean isCorrectSegmentCount(Token.Type type, int i, int i2) {
        int ordinal = type.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (i2 != i) {
                    return false;
                }
            } else if (i < i2) {
                return false;
            }
        } else if (i != i2 - 1 && i != i2) {
            return false;
        }
        return true;
    }

    private static boolean isLastSegmentValid(String str) {
        return str.startsWith(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN) || !str.contains(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
    }

    private static boolean isListsUri(String str) {
        return str.startsWith("/lists") | str.startsWith("v2/lists");
    }

    private String joinSegments(String[] strArr) {
        return TextUtils.join("/", strArr);
    }

    private static String[] normalizeTemplateParts(String[] strArr) {
        int length = strArr.length;
        String[] strArr2 = (String[]) Arrays.copyOf(strArr, length);
        int i = length - 1;
        strArr2[i] = WebConstants.UriConstants.QUESTIONMARK_KEY.concat(strArr2[i]);
        return strArr2;
    }

    private String normalizeUrl(String str) {
        String[] split;
        String str2 = str.split("/", -1)[split.length - 1];
        if (str2.length() == 0) {
            return str;
        }
        for (int i = 0; i < str2.length() - 1; i++) {
            if (str2.charAt(i) == '=') {
                int lastIndexOf = str.lastIndexOf(47) + 1;
                StringBuilder sb = new StringBuilder();
                GeneratedOutlineSupport1.outline150(str, 0, lastIndexOf, sb, WebConstants.UriConstants.QUESTIONMARK_KEY);
                return GeneratedOutlineSupport1.outline55(str, lastIndexOf, sb);
            } else if (str2.charAt(i) == '?') {
                return str;
            }
        }
        return str;
    }

    private static void parse(String str, Map<String, Token> map, List<Token> list, Map<String, Token> map2) {
        boolean z;
        String[] split = str.split("/", -1);
        if (TextUtils.isEmpty(split[split.length - 1])) {
            Log.i(TAG, "Trailing /, appending it to last token");
            split = (String[]) Arrays.copyOf(split, split.length - 1);
            z = true;
        } else {
            z = false;
        }
        if (isListsUri(str) && !isLastSegmentValid(split[split.length - 1])) {
            split = normalizeTemplateParts(split);
        }
        for (int i = 0; i < split.length; i++) {
            String str2 = split[i];
            if (i == split.length - 1) {
                String[] parseLastSegment = parseLastSegment(str2);
                if (!TextUtils.isEmpty(parseLastSegment[0])) {
                    Token parseToken = parseToken(parseLastSegment[0]);
                    list.add(parseToken);
                    if (parseToken.type != Token.Type.Literal) {
                        map2.put(parseToken.value, parseToken);
                    }
                } else {
                    z = true;
                }
                Token token = (Token) GeneratedOutlineSupport1.outline25(list, 1);
                StringBuilder sb = new StringBuilder();
                sb.append(token.value);
                sb.append((!z || token.type != Token.Type.Literal) ? "" : "/");
                token.value = sb.toString();
                if (parseLastSegment.length > 1 && parseLastSegment[1].length() > 0) {
                    HashMap<String, Token> extractTokens = extractTokens(parseQueryString(parseLastSegment[1]));
                    map2.putAll(extractTokens);
                    map.putAll(extractTokens);
                }
            } else if (!TextUtils.isEmpty(str2)) {
                Token.Type type = parseToken(str2).type;
                if (type != Token.Type.CatchAll && type != Token.Type.Optional) {
                    Token parseToken2 = parseToken(str2);
                    list.add(parseToken2);
                    if (parseToken2.type != Token.Type.Literal) {
                        map2.put(parseToken2.value, parseToken2);
                    }
                } else {
                    throw new IllegalArgumentException("Catchall or Optional tokens can only bethe last token in the path.");
                }
            } else {
                continue;
            }
        }
    }

    private static String[] parseLastSegment(String str) {
        ArrayList arrayList = new ArrayList();
        if (str.charAt(0) == '{') {
            int indexOf = str.indexOf(125);
            int i = indexOf + 1;
            arrayList.add(str.substring(0, i));
            int i2 = indexOf + 2;
            if (str.length() > i2) {
                if (str.charAt(i) == '?') {
                    arrayList.add(str.substring(i2));
                } else {
                    throw new IllegalArgumentException("Last segment malformed or empty querystring");
                }
            }
        } else {
            int i3 = 0;
            while (true) {
                if (i3 >= str.length()) {
                    break;
                } else if (str.charAt(i3) != '{') {
                    if (str.charAt(i3) == '?') {
                        arrayList.add(str.substring(0, i3));
                        if (str.length() > i3 + 2) {
                            arrayList.add(str.substring(i3 + 1));
                        }
                    } else {
                        i3++;
                    }
                } else {
                    throw new IllegalArgumentException("Last segment malformed or empty querystring");
                }
            }
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static HashMap<String, String> parseQueryString(String str) {
        String[] split;
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : str.split("&")) {
            String[] split2 = str2.split(Config.Compare.EQUAL_TO);
            if (split2.length == 0) {
                GeneratedOutlineSupport1.outline158("Ignoring Malformed query parameter : ", str2);
            } else {
                hashMap.put(split2[0], split2.length > 1 ? split2[1] : null);
            }
        }
        return hashMap;
    }

    private static Token parseToken(String str) {
        if (str.charAt(0) == '{') {
            return parseVariableToken(str);
        }
        return new Token(str);
    }

    private static Token parseVariableToken(String str) {
        if (str.charAt(str.length() - 1) == '}') {
            char charAt = str.charAt(str.length() - 2);
            if (charAt == '*') {
                return new Token(GeneratedOutlineSupport1.outline50(str, -2, 1), Token.Type.CatchAll);
            }
            if (charAt != '?') {
                return new Token(GeneratedOutlineSupport1.outline51(str, 1, 1), Token.Type.Variable);
            }
            return new Token(GeneratedOutlineSupport1.outline50(str, -2, 1), Token.Type.Optional);
        }
        throw new IllegalArgumentException("Template variable not properly closed with closing bracket");
    }

    public void addParameter(Bundle bundle, String str, String str2) {
        if (!"true".equalsIgnoreCase(str2) && !PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE.equalsIgnoreCase(str2)) {
            try {
                bundle.putInt(str, Integer.parseInt(str2));
                return;
            } catch (NumberFormatException unused) {
                bundle.putString(str, str2);
                return;
            }
        }
        bundle.putBoolean(str, Boolean.parseBoolean(str2));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && RouteTemplate.class == obj.getClass()) {
            return this.template.equals(((RouteTemplate) obj).template);
        }
        return false;
    }

    public String getTemplateName() {
        return this.template;
    }

    public int hashCode() {
        return this.template.hashCode();
    }

    public boolean isOptional(String str) {
        Token token = this.tokenMap.get(str);
        return token == null || token.type == Token.Type.Optional;
    }

    @Nullable
    public Bundle parseParameters(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (isListsUri(str)) {
            str = normalizeUrl(str);
        }
        String[] split = str.split("\\?");
        String[] split2 = split[0].split("/", -1);
        Bundle bundle = new Bundle();
        Token token = (Token) GeneratedOutlineSupport1.outline25(this.tokens, 1);
        if (split2.length > 0 && TextUtils.isEmpty(split2[split2.length - 1])) {
            split2 = (String[]) Arrays.copyOf(split2, split2.length - 1);
            StringBuilder sb = new StringBuilder();
            int length = split2.length - 1;
            split2[length] = GeneratedOutlineSupport1.outline91(sb, split2[length], "/");
        }
        if (!isCorrectSegmentCount(token.type, split2.length, this.tokens.size())) {
            return null;
        }
        for (int i = 0; i < split2.length && i < this.tokens.size(); i++) {
            Token token2 = this.tokens.get(i);
            Token.Type type = token2.type;
            if (type == Token.Type.CatchAll) {
                addParameter(bundle, token2.value, Uri.decode(joinSegments((String[]) Arrays.copyOfRange(split2, i, split2.length))));
            } else if (type != Token.Type.Literal) {
                addParameter(bundle, token2.value, Uri.decode(split2[i]));
            } else if (!token2.value.equalsIgnoreCase(split2[i])) {
                return null;
            }
        }
        if (split.length > 1) {
            HashMap<String, String> parseQueryString = parseQueryString(split[1]);
            bundle.putString(BilobaRouteUtil.RAW_QUERY_STRING, split[1]);
            Bundle bundle2 = new Bundle();
            for (Map.Entry<String, Token> entry : this.queryParams.entrySet()) {
                String key = entry.getKey();
                Token value = entry.getValue();
                if (parseQueryString.containsKey(key)) {
                    if (value.type == Token.Type.Literal) {
                        try {
                            if (!decode(parseQueryString.get(key)).equals(value.value)) {
                                return null;
                            }
                        } catch (UnsupportedEncodingException e) {
                            Log.e(TAG, "Could not decode parameter in url", e);
                            return null;
                        }
                    } else {
                        bundle.putString(value.value, parseQueryString.get(key));
                    }
                } else if (value.type != Token.Type.Optional) {
                    return null;
                }
            }
            bundle.putBundle("queryParts", bundle2);
        }
        return bundle;
    }

    public String toString(RouteContext routeContext) {
        StringBuilder sb = new StringBuilder();
        try {
            String compilePath = compilePath(routeContext);
            String compileQueryString = compileQueryString(routeContext);
            sb.append(compilePath);
            if (compileQueryString.length() > 0) {
                sb.append(Constants.DEFAULT_IMAGE_CHAR);
                sb.append(compileQueryString);
            }
            return sb.toString();
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.template);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class Token {
        Type type;
        String value;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes10.dex */
        public enum Type {
            Optional,
            CatchAll,
            Variable,
            Literal
        }

        Token(String str) {
            this.value = str;
            this.type = Type.Literal;
        }

        Token(String str, Type type) {
            this.value = str;
            this.type = type;
        }
    }

    public RouteTemplate(Parcel parcel) {
        this.template = parcel.readString();
        parse(this.template, this.queryParams, this.tokens, this.tokenMap);
    }

    public String toString() {
        return this.template;
    }
}
