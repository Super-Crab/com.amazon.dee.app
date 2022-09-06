package com.fasterxml.jackson.databind.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes2.dex */
public abstract class NameTransformer {
    public static final NameTransformer NOP = new NopTransformer();

    /* loaded from: classes2.dex */
    public static class Chained extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String reverse(String str) {
            String reverse = this._t1.reverse(str);
            return reverse != null ? this._t2.reverse(reverse) : reverse;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[ChainedTransformer(");
            outline107.append(this._t1);
            outline107.append(", ");
            outline107.append(this._t2);
            outline107.append(")]");
            return outline107.toString();
        }

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }
    }

    /* loaded from: classes2.dex */
    protected static final class NopTransformer extends NameTransformer implements Serializable {
        private static final long serialVersionUID = 1;

        protected NopTransformer() {
        }

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String reverse(String str) {
            return str;
        }

        @Override // com.fasterxml.jackson.databind.util.NameTransformer
        public String transform(String str) {
            return str;
        }
    }

    protected NameTransformer() {
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        return new Chained(nameTransformer, nameTransformer2);
    }

    public static NameTransformer simpleTransformer(final String str, final String str2) {
        boolean z = true;
        boolean z2 = str != null && !str.isEmpty();
        if (str2 == null || str2.isEmpty()) {
            z = false;
        }
        if (z2) {
            if (z) {
                return new NameTransformer() { // from class: com.fasterxml.jackson.databind.util.NameTransformer.1
                    @Override // com.fasterxml.jackson.databind.util.NameTransformer
                    public String reverse(String str3) {
                        if (str3.startsWith(str)) {
                            String substring = str3.substring(str.length());
                            if (!substring.endsWith(str2)) {
                                return null;
                            }
                            return substring.substring(0, substring.length() - str2.length());
                        }
                        return null;
                    }

                    public String toString() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[PreAndSuffixTransformer('");
                        outline107.append(str);
                        outline107.append("','");
                        return GeneratedOutlineSupport1.outline91(outline107, str2, "')]");
                    }

                    @Override // com.fasterxml.jackson.databind.util.NameTransformer
                    public String transform(String str3) {
                        return str + str3 + str2;
                    }
                };
            }
            return new NameTransformer() { // from class: com.fasterxml.jackson.databind.util.NameTransformer.2
                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String reverse(String str3) {
                    if (str3.startsWith(str)) {
                        return str3.substring(str.length());
                    }
                    return null;
                }

                public String toString() {
                    return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("[PrefixTransformer('"), str, "')]");
                }

                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String transform(String str3) {
                    return GeneratedOutlineSupport1.outline91(new StringBuilder(), str, str3);
                }
            };
        } else if (z) {
            return new NameTransformer() { // from class: com.fasterxml.jackson.databind.util.NameTransformer.3
                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String reverse(String str3) {
                    if (str3.endsWith(str2)) {
                        return str3.substring(0, str3.length() - str2.length());
                    }
                    return null;
                }

                public String toString() {
                    return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("[SuffixTransformer('"), str2, "')]");
                }

                @Override // com.fasterxml.jackson.databind.util.NameTransformer
                public String transform(String str3) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str3);
                    outline107.append(str2);
                    return outline107.toString();
                }
            };
        } else {
            return NOP;
        }
    }

    public abstract String reverse(String str);

    public abstract String transform(String str);
}
