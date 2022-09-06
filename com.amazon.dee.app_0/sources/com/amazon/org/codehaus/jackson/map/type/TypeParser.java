package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.org.codehaus.jackson.map.util.ClassUtil;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/* loaded from: classes13.dex */
public class TypeParser {
    final TypeFactory _factory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static final class MyTokenizer extends StringTokenizer {
        protected int _index;
        protected final String _input;
        protected String _pushbackToken;

        public MyTokenizer(String str) {
            super(str, "<,>", true);
            this._input = str;
        }

        public String getAllInput() {
            return this._input;
        }

        public String getRemainingInput() {
            return this._input.substring(this._index);
        }

        public String getUsedInput() {
            return this._input.substring(0, this._index);
        }

        @Override // java.util.StringTokenizer
        public boolean hasMoreTokens() {
            return this._pushbackToken != null || super.hasMoreTokens();
        }

        @Override // java.util.StringTokenizer
        public String nextToken() {
            String str = this._pushbackToken;
            if (str != null) {
                this._pushbackToken = null;
            } else {
                str = super.nextToken();
            }
            this._index = str.length() + this._index;
            return str;
        }

        public void pushBack(String str) {
            this._pushbackToken = str;
            this._index -= str.length();
        }
    }

    public TypeParser(TypeFactory typeFactory) {
        this._factory = typeFactory;
    }

    protected IllegalArgumentException _problem(MyTokenizer myTokenizer, String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to parse type '");
        outline107.append(myTokenizer.getAllInput());
        outline107.append("' (remaining: '");
        return new IllegalArgumentException(GeneratedOutlineSupport1.outline92(outline107, myTokenizer.getRemainingInput(), "'): ", str));
    }

    protected Class<?> findClass(String str, MyTokenizer myTokenizer) {
        try {
            return ClassUtil.findClass(str);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            throw _problem(myTokenizer, GeneratedOutlineSupport1.outline41(e, GeneratedOutlineSupport1.outline115("Can not locate class '", str, "', problem: ")));
        }
    }

    public JavaType parse(String str) throws IllegalArgumentException {
        MyTokenizer myTokenizer = new MyTokenizer(str.trim());
        JavaType parseType = parseType(myTokenizer);
        if (!myTokenizer.hasMoreTokens()) {
            return parseType;
        }
        throw _problem(myTokenizer, "Unexpected tokens after complete type");
    }

    protected JavaType parseType(MyTokenizer myTokenizer) throws IllegalArgumentException {
        if (myTokenizer.hasMoreTokens()) {
            Class<?> findClass = findClass(myTokenizer.nextToken(), myTokenizer);
            if (myTokenizer.hasMoreTokens()) {
                String nextToken = myTokenizer.nextToken();
                if (Config.Compare.LESS_THAN.equals(nextToken)) {
                    return this._factory._fromParameterizedClass(findClass, parseTypes(myTokenizer));
                }
                myTokenizer.pushBack(nextToken);
            }
            return this._factory._fromClass(findClass, null);
        }
        throw _problem(myTokenizer, "Unexpected end-of-string");
    }

    protected List<JavaType> parseTypes(MyTokenizer myTokenizer) throws IllegalArgumentException {
        ArrayList arrayList = new ArrayList();
        while (myTokenizer.hasMoreTokens()) {
            arrayList.add(parseType(myTokenizer));
            if (!myTokenizer.hasMoreTokens()) {
                break;
            }
            String nextToken = myTokenizer.nextToken();
            if (Config.Compare.GREATER_THAN.equals(nextToken)) {
                return arrayList;
            }
            if (!",".equals(nextToken)) {
                throw _problem(myTokenizer, GeneratedOutlineSupport1.outline75("Unexpected token '", nextToken, "', expected ',' or '>')"));
            }
        }
        throw _problem(myTokenizer, "Unexpected end-of-string");
    }
}
