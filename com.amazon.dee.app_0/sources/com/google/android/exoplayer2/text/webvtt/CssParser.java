package com.google.android.exoplayer2.text.webvtt;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
final class CssParser {
    private static final String PROPERTY_BGCOLOR = "background-color";
    private static final String PROPERTY_COLOR = "color";
    private static final String PROPERTY_FONT_FAMILY = "font-family";
    private static final String PROPERTY_FONT_STYLE = "font-style";
    private static final String PROPERTY_FONT_WEIGHT = "font-weight";
    private static final String PROPERTY_RUBY_POSITION = "ruby-position";
    private static final String PROPERTY_TEXT_COMBINE_UPRIGHT = "text-combine-upright";
    private static final String PROPERTY_TEXT_DECORATION = "text-decoration";
    private static final String RULE_END = "}";
    private static final String RULE_START = "{";
    private static final String TAG = "CssParser";
    private static final String VALUE_ALL = "all";
    private static final String VALUE_BOLD = "bold";
    private static final String VALUE_DIGITS = "digits";
    private static final String VALUE_ITALIC = "italic";
    private static final String VALUE_OVER = "over";
    private static final String VALUE_UNDER = "under";
    private static final String VALUE_UNDERLINE = "underline";
    private static final Pattern VOICE_NAME_PATTERN = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private final ParsableByteArray styleInput = new ParsableByteArray();
    private final StringBuilder stringBuilder = new StringBuilder();

    private void applySelectorToStyle(WebvttCssStyle webvttCssStyle, String str) {
        if ("".equals(str)) {
            return;
        }
        int indexOf = str.indexOf(91);
        if (indexOf != -1) {
            Matcher matcher = VOICE_NAME_PATTERN.matcher(str.substring(indexOf));
            if (matcher.matches()) {
                webvttCssStyle.setTargetVoice((String) Assertions.checkNotNull(matcher.group(1)));
            }
            str = str.substring(0, indexOf);
        }
        String[] split = Util.split(str, ArcusConfig.PATH_SEPARATOR);
        String str2 = split[0];
        int indexOf2 = str2.indexOf(35);
        if (indexOf2 != -1) {
            webvttCssStyle.setTargetTagName(str2.substring(0, indexOf2));
            webvttCssStyle.setTargetId(str2.substring(indexOf2 + 1));
        } else {
            webvttCssStyle.setTargetTagName(str2);
        }
        if (split.length <= 1) {
            return;
        }
        webvttCssStyle.setTargetClasses((String[]) Util.nullSafeArrayCopyOfRange(split, 1, split.length));
    }

    private static boolean maybeSkipComment(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] data = parsableByteArray.getData();
        if (position + 2 <= limit) {
            int i = position + 1;
            if (data[position] != 47) {
                return false;
            }
            int i2 = i + 1;
            if (data[i] != 42) {
                return false;
            }
            while (true) {
                int i3 = i2 + 1;
                if (i3 < limit) {
                    if (((char) data[i2]) == '*' && ((char) data[i3]) == '/') {
                        i2 = i3 + 1;
                        limit = i2;
                    } else {
                        i2 = i3;
                    }
                } else {
                    parsableByteArray.skipBytes(limit - parsableByteArray.getPosition());
                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private static boolean maybeSkipWhitespace(ParsableByteArray parsableByteArray) {
        char peekCharAtPosition = peekCharAtPosition(parsableByteArray, parsableByteArray.getPosition());
        if (peekCharAtPosition == '\t' || peekCharAtPosition == '\n' || peekCharAtPosition == '\f' || peekCharAtPosition == '\r' || peekCharAtPosition == ' ') {
            parsableByteArray.skipBytes(1);
            return true;
        }
        return false;
    }

    private static String parseIdentifier(ParsableByteArray parsableByteArray, StringBuilder sb) {
        boolean z = false;
        sb.setLength(0);
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        while (position < limit && !z) {
            char c = (char) parsableByteArray.getData()[position];
            if ((c < 'A' || c > 'Z') && ((c < 'a' || c > 'z') && !((c >= '0' && c <= '9') || c == '#' || c == '-' || c == '.' || c == '_'))) {
                z = true;
            } else {
                position++;
                sb.append(c);
            }
        }
        parsableByteArray.skipBytes(position - parsableByteArray.getPosition());
        return sb.toString();
    }

    @Nullable
    static String parseNextToken(ParsableByteArray parsableByteArray, StringBuilder sb) {
        skipWhitespaceAndComments(parsableByteArray);
        if (parsableByteArray.bytesLeft() == 0) {
            return null;
        }
        String parseIdentifier = parseIdentifier(parsableByteArray, sb);
        if (!"".equals(parseIdentifier)) {
            return parseIdentifier;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
        outline107.append((char) parsableByteArray.readUnsignedByte());
        return outline107.toString();
    }

    @Nullable
    private static String parsePropertyValue(ParsableByteArray parsableByteArray, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder();
        boolean z = false;
        while (!z) {
            int position = parsableByteArray.getPosition();
            String parseNextToken = parseNextToken(parsableByteArray, sb);
            if (parseNextToken == null) {
                return null;
            }
            if (!"}".equals(parseNextToken) && !";".equals(parseNextToken)) {
                sb2.append(parseNextToken);
            } else {
                parsableByteArray.setPosition(position);
                z = true;
            }
        }
        return sb2.toString();
    }

    @Nullable
    private static String parseSelector(ParsableByteArray parsableByteArray, StringBuilder sb) {
        skipWhitespaceAndComments(parsableByteArray);
        if (parsableByteArray.bytesLeft() >= 5 && "::cue".equals(parsableByteArray.readString(5))) {
            int position = parsableByteArray.getPosition();
            String parseNextToken = parseNextToken(parsableByteArray, sb);
            if (parseNextToken == null) {
                return null;
            }
            if ("{".equals(parseNextToken)) {
                parsableByteArray.setPosition(position);
                return "";
            }
            String readCueTarget = "(".equals(parseNextToken) ? readCueTarget(parsableByteArray) : null;
            if (")".equals(parseNextToken(parsableByteArray, sb))) {
                return readCueTarget;
            }
            return null;
        }
        return null;
    }

    private static void parseStyleDeclaration(ParsableByteArray parsableByteArray, WebvttCssStyle webvttCssStyle, StringBuilder sb) {
        skipWhitespaceAndComments(parsableByteArray);
        String parseIdentifier = parseIdentifier(parsableByteArray, sb);
        if (!"".equals(parseIdentifier) && ":".equals(parseNextToken(parsableByteArray, sb))) {
            skipWhitespaceAndComments(parsableByteArray);
            String parsePropertyValue = parsePropertyValue(parsableByteArray, sb);
            if (parsePropertyValue == null || "".equals(parsePropertyValue)) {
                return;
            }
            int position = parsableByteArray.getPosition();
            String parseNextToken = parseNextToken(parsableByteArray, sb);
            if (!";".equals(parseNextToken)) {
                if (!"}".equals(parseNextToken)) {
                    return;
                }
                parsableByteArray.setPosition(position);
            }
            if ("color".equals(parseIdentifier)) {
                webvttCssStyle.setFontColor(ColorParser.parseCssColor(parsePropertyValue));
            } else if (PROPERTY_BGCOLOR.equals(parseIdentifier)) {
                webvttCssStyle.setBackgroundColor(ColorParser.parseCssColor(parsePropertyValue));
            } else {
                boolean z = true;
                if (PROPERTY_RUBY_POSITION.equals(parseIdentifier)) {
                    if (VALUE_OVER.equals(parsePropertyValue)) {
                        webvttCssStyle.setRubyPosition(1);
                    } else if (!VALUE_UNDER.equals(parsePropertyValue)) {
                    } else {
                        webvttCssStyle.setRubyPosition(2);
                    }
                } else if (PROPERTY_TEXT_COMBINE_UPRIGHT.equals(parseIdentifier)) {
                    if (!"all".equals(parsePropertyValue) && !parsePropertyValue.startsWith(VALUE_DIGITS)) {
                        z = false;
                    }
                    webvttCssStyle.setCombineUpright(z);
                } else if (PROPERTY_TEXT_DECORATION.equals(parseIdentifier)) {
                    if (!"underline".equals(parsePropertyValue)) {
                        return;
                    }
                    webvttCssStyle.setUnderline(true);
                } else if (PROPERTY_FONT_FAMILY.equals(parseIdentifier)) {
                    webvttCssStyle.setFontFamily(parsePropertyValue);
                } else if (PROPERTY_FONT_WEIGHT.equals(parseIdentifier)) {
                    if (!"bold".equals(parsePropertyValue)) {
                        return;
                    }
                    webvttCssStyle.setBold(true);
                } else if (!PROPERTY_FONT_STYLE.equals(parseIdentifier) || !"italic".equals(parsePropertyValue)) {
                } else {
                    webvttCssStyle.setItalic(true);
                }
            }
        }
    }

    private static char peekCharAtPosition(ParsableByteArray parsableByteArray, int i) {
        return (char) parsableByteArray.getData()[i];
    }

    private static String readCueTarget(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        boolean z = false;
        while (position < limit && !z) {
            int i = position + 1;
            z = ((char) parsableByteArray.getData()[position]) == ')';
            position = i;
        }
        return parsableByteArray.readString((position - 1) - parsableByteArray.getPosition()).trim();
    }

    static void skipStyleBlock(ParsableByteArray parsableByteArray) {
        do {
        } while (!TextUtils.isEmpty(parsableByteArray.readLine()));
    }

    static void skipWhitespaceAndComments(ParsableByteArray parsableByteArray) {
        while (true) {
            for (boolean z = true; parsableByteArray.bytesLeft() > 0 && z; z = false) {
                if (!maybeSkipWhitespace(parsableByteArray) && !maybeSkipComment(parsableByteArray)) {
                }
            }
            return;
        }
    }

    public List<WebvttCssStyle> parseBlock(ParsableByteArray parsableByteArray) {
        this.stringBuilder.setLength(0);
        int position = parsableByteArray.getPosition();
        skipStyleBlock(parsableByteArray);
        this.styleInput.reset(parsableByteArray.getData(), parsableByteArray.getPosition());
        this.styleInput.setPosition(position);
        ArrayList arrayList = new ArrayList();
        while (true) {
            String parseSelector = parseSelector(this.styleInput, this.stringBuilder);
            if (parseSelector == null || !"{".equals(parseNextToken(this.styleInput, this.stringBuilder))) {
                return arrayList;
            }
            WebvttCssStyle webvttCssStyle = new WebvttCssStyle();
            applySelectorToStyle(webvttCssStyle, parseSelector);
            String str = null;
            boolean z = false;
            while (!z) {
                int position2 = this.styleInput.getPosition();
                str = parseNextToken(this.styleInput, this.stringBuilder);
                boolean z2 = str == null || "}".equals(str);
                if (!z2) {
                    this.styleInput.setPosition(position2);
                    parseStyleDeclaration(this.styleInput, webvttCssStyle, this.stringBuilder);
                }
                z = z2;
            }
            if ("}".equals(str)) {
                arrayList.add(webvttCssStyle);
            }
        }
    }
}
