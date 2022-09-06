package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigSyntax;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigParser {

    /* renamed from: com.typesafe.config.impl.ConfigParser$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind = new int[ConfigIncludeKind.values().length];

        static {
            try {
                $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind[ConfigIncludeKind.URL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind[ConfigIncludeKind.FILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind[ConfigIncludeKind.CLASSPATH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$ConfigIncludeKind[ConfigIncludeKind.HEURISTIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ParseContext {
        private final ConfigOrigin baseOrigin;
        private final ConfigNodeRoot document;
        private final ConfigSyntax flavor;
        private final ConfigIncludeContext includeContext;
        private final FullIncluder includer;
        private int lineNumber = 1;
        private final LinkedList<Path> pathStack = new LinkedList<>();
        int arrayCount = 0;

        ParseContext(ConfigSyntax configSyntax, ConfigOrigin configOrigin, ConfigNodeRoot configNodeRoot, FullIncluder fullIncluder, ConfigIncludeContext configIncludeContext) {
            this.document = configNodeRoot;
            this.flavor = configSyntax;
            this.baseOrigin = configOrigin;
            this.includer = fullIncluder;
            this.includeContext = configIncludeContext;
        }

        private static AbstractConfigObject createValueUnderPath(Path path, AbstractConfigValue abstractConfigValue) {
            ArrayList arrayList = new ArrayList();
            String first = path.first();
            Path remainder = path.remainder();
            while (first != null) {
                arrayList.add(first);
                if (remainder == null) {
                    break;
                }
                first = remainder.first();
                remainder = remainder.remainder();
            }
            ListIterator listIterator = arrayList.listIterator(arrayList.size());
            SimpleConfigObject simpleConfigObject = new SimpleConfigObject(abstractConfigValue.mo10176origin().mo10262withComments((List<String>) null), Collections.singletonMap((String) listIterator.previous(), abstractConfigValue));
            while (listIterator.hasPrevious()) {
                simpleConfigObject = new SimpleConfigObject(abstractConfigValue.mo10176origin().mo10262withComments((List<String>) null), Collections.singletonMap(listIterator.previous(), simpleConfigObject));
            }
            return simpleConfigObject;
        }

        private Path fullCurrentPath() {
            if (!this.pathStack.isEmpty()) {
                return new Path(this.pathStack.descendingIterator());
            }
            throw new ConfigException.BugOrBroken("Bug in parser; tried to get current path when at root");
        }

        private SimpleConfigOrigin lineOrigin() {
            return ((SimpleConfigOrigin) this.baseOrigin).mo10263withLineNumber(this.lineNumber);
        }

        private SimpleConfigList parseArray(ConfigNodeArray configNodeArray) {
            this.arrayCount++;
            SimpleConfigOrigin lineOrigin = lineOrigin();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            AbstractConfigValue abstractConfigValue = null;
            loop0: while (true) {
                boolean z = false;
                for (AbstractConfigNode abstractConfigNode : configNodeArray.children()) {
                    if (abstractConfigNode instanceof ConfigNodeComment) {
                        arrayList2.add(((ConfigNodeComment) abstractConfigNode).commentText());
                    } else if ((abstractConfigNode instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                        this.lineNumber++;
                        if (z && abstractConfigValue == null) {
                            arrayList2.clear();
                        } else if (abstractConfigValue != null) {
                            arrayList.add(abstractConfigValue.mo10244withOrigin((ConfigOrigin) abstractConfigValue.mo10176origin().appendComments(new ArrayList(arrayList2))));
                            arrayList2.clear();
                            abstractConfigValue = null;
                        }
                        z = true;
                    } else if (abstractConfigNode instanceof AbstractConfigNodeValue) {
                        if (abstractConfigValue != null) {
                            arrayList.add(abstractConfigValue.mo10244withOrigin((ConfigOrigin) abstractConfigValue.mo10176origin().appendComments(new ArrayList(arrayList2))));
                            arrayList2.clear();
                        }
                        abstractConfigValue = parseValue((AbstractConfigNodeValue) abstractConfigNode, arrayList2);
                    }
                }
                break loop0;
            }
            if (abstractConfigValue != null) {
                arrayList.add(abstractConfigValue.mo10244withOrigin((ConfigOrigin) abstractConfigValue.mo10176origin().appendComments(new ArrayList(arrayList2))));
            }
            this.arrayCount--;
            return new SimpleConfigList(lineOrigin, arrayList);
        }

        private AbstractConfigValue parseConcatenation(ConfigNodeConcatenation configNodeConcatenation) {
            if (this.flavor != ConfigSyntax.JSON) {
                ArrayList arrayList = new ArrayList();
                for (AbstractConfigNode abstractConfigNode : configNodeConcatenation.children()) {
                    if (abstractConfigNode instanceof AbstractConfigNodeValue) {
                        arrayList.add(parseValue((AbstractConfigNodeValue) abstractConfigNode, null));
                    }
                }
                return ConfigConcatenation.concatenate(arrayList);
            }
            throw new ConfigException.BugOrBroken("Found a concatenation node in JSON");
        }

        private ConfigException parseError(String str) {
            return parseError(str, null);
        }

        private void parseInclude(Map<String, AbstractConfigValue> map, ConfigNodeInclude configNodeInclude) {
            AbstractConfigObject abstractConfigObject;
            boolean isRequired = configNodeInclude.isRequired();
            ConfigIncludeContext configIncludeContext = this.includeContext;
            ConfigIncludeContext parseOptions = configIncludeContext.setParseOptions(configIncludeContext.parseOptions().setAllowMissing(!isRequired));
            int ordinal = configNodeInclude.kind().ordinal();
            if (ordinal == 0) {
                try {
                    abstractConfigObject = (AbstractConfigObject) this.includer.includeURL(parseOptions, new URL(configNodeInclude.name()));
                } catch (MalformedURLException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("include url() specifies an invalid URL: ");
                    outline107.append(configNodeInclude.name());
                    throw parseError(outline107.toString(), e);
                }
            } else if (ordinal == 1) {
                abstractConfigObject = (AbstractConfigObject) this.includer.includeFile(parseOptions, new File(configNodeInclude.name()));
            } else if (ordinal == 2) {
                abstractConfigObject = (AbstractConfigObject) this.includer.includeResources(parseOptions, configNodeInclude.name());
            } else if (ordinal == 3) {
                abstractConfigObject = (AbstractConfigObject) this.includer.include(parseOptions, configNodeInclude.name());
            } else {
                throw new ConfigException.BugOrBroken("should not be reached");
            }
            if (this.arrayCount > 0 && abstractConfigObject.resolveStatus() != ResolveStatus.RESOLVED) {
                throw parseError("Due to current limitations of the config parser, when an include statement is nested inside a list value, ${} substitutions inside the included file cannot be resolved correctly. Either move the include outside of the list value or remove the ${} statements from the included file.");
            }
            AbstractConfigObject abstractConfigObject2 = abstractConfigObject;
            if (!this.pathStack.isEmpty()) {
                abstractConfigObject2 = abstractConfigObject.mo10251relativized(fullCurrentPath());
            }
            for (String str : abstractConfigObject2.keySet()) {
                AbstractConfigValue mo10248get = abstractConfigObject2.mo10248get((Object) str);
                AbstractConfigValue abstractConfigValue = map.get(str);
                if (abstractConfigValue != null) {
                    map.put(str, mo10248get.mo10234withFallback((ConfigMergeable) abstractConfigValue));
                } else {
                    map.put(str, mo10248get);
                }
            }
        }

        private AbstractConfigObject parseObject(ConfigNodeObject configNodeObject) {
            Map<String, AbstractConfigValue> hashMap = new HashMap<>();
            SimpleConfigOrigin lineOrigin = lineOrigin();
            ArrayList arrayList = new ArrayList(configNodeObject.children());
            List<String> arrayList2 = new ArrayList<>();
            int i = 0;
            boolean z = false;
            while (i < arrayList.size()) {
                AbstractConfigNode abstractConfigNode = (AbstractConfigNode) arrayList.get(i);
                if (abstractConfigNode instanceof ConfigNodeComment) {
                    arrayList2.add(((ConfigNodeComment) abstractConfigNode).commentText());
                } else {
                    if ((abstractConfigNode instanceof ConfigNodeSingleToken) && Tokens.isNewline(((ConfigNodeSingleToken) abstractConfigNode).token())) {
                        this.lineNumber++;
                        if (z) {
                            arrayList2.clear();
                        }
                        z = true;
                    } else if (this.flavor != ConfigSyntax.JSON && (abstractConfigNode instanceof ConfigNodeInclude)) {
                        parseInclude(hashMap, (ConfigNodeInclude) abstractConfigNode);
                    } else if (abstractConfigNode instanceof ConfigNodeField) {
                        ConfigNodeField configNodeField = (ConfigNodeField) abstractConfigNode;
                        Path value = configNodeField.path().value();
                        arrayList2.addAll(configNodeField.comments());
                        this.pathStack.push(value);
                        if (configNodeField.separator() == Tokens.PLUS_EQUALS) {
                            int i2 = this.arrayCount;
                            if (i2 <= 0) {
                                this.arrayCount = i2 + 1;
                            } else {
                                throw parseError("Due to current limitations of the config parser, += does not work nested inside a list. += expands to a ${} substitution and the path in ${} cannot currently refer to list elements. You might be able to move the += outside of the list and then refer to it from inside the list with ${}.");
                            }
                        }
                        AbstractConfigValue parseValue = parseValue(configNodeField.value(), arrayList2);
                        if (configNodeField.separator() == Tokens.PLUS_EQUALS) {
                            this.arrayCount--;
                            ArrayList arrayList3 = new ArrayList(2);
                            ConfigReference configReference = new ConfigReference(parseValue.mo10176origin(), new SubstitutionExpression(fullCurrentPath(), true));
                            SimpleConfigList simpleConfigList = new SimpleConfigList(parseValue.mo10176origin(), Collections.singletonList(parseValue));
                            arrayList3.add(configReference);
                            arrayList3.add(simpleConfigList);
                            parseValue = ConfigConcatenation.concatenate(arrayList3);
                        }
                        if (i < arrayList.size() - 1) {
                            while (true) {
                                i++;
                                if (i < arrayList.size()) {
                                    if (arrayList.get(i) instanceof ConfigNodeComment) {
                                        parseValue = parseValue.mo10244withOrigin((ConfigOrigin) parseValue.mo10176origin().appendComments(Collections.singletonList(((ConfigNodeComment) arrayList.get(i)).commentText())));
                                        break;
                                    } else if (!(arrayList.get(i) instanceof ConfigNodeSingleToken)) {
                                        break;
                                    } else {
                                        ConfigNodeSingleToken configNodeSingleToken = (ConfigNodeSingleToken) arrayList.get(i);
                                        if (configNodeSingleToken.token() != Tokens.COMMA && !Tokens.isIgnoredWhitespace(configNodeSingleToken.token())) {
                                            break;
                                        }
                                    }
                                } else {
                                    break;
                                }
                            }
                            i--;
                        }
                        this.pathStack.pop();
                        String first = value.first();
                        Path remainder = value.remainder();
                        if (remainder == null) {
                            AbstractConfigValue abstractConfigValue = hashMap.get(first);
                            if (abstractConfigValue != null) {
                                if (this.flavor != ConfigSyntax.JSON) {
                                    parseValue = parseValue.mo10234withFallback((ConfigMergeable) abstractConfigValue);
                                } else {
                                    StringBuilder outline115 = GeneratedOutlineSupport1.outline115("JSON does not allow duplicate fields: '", first, "' was already seen at ");
                                    outline115.append(abstractConfigValue.mo10176origin().description());
                                    throw parseError(outline115.toString());
                                }
                            }
                            hashMap.put(first, parseValue);
                        } else if (this.flavor != ConfigSyntax.JSON) {
                            AbstractConfigObject createValueUnderPath = createValueUnderPath(remainder, parseValue);
                            AbstractConfigValue abstractConfigValue2 = hashMap.get(first);
                            if (abstractConfigValue2 != null) {
                                createValueUnderPath = createValueUnderPath.mo10234withFallback((ConfigMergeable) abstractConfigValue2);
                            }
                            hashMap.put(first, createValueUnderPath);
                        } else {
                            throw new ConfigException.BugOrBroken("somehow got multi-element path in JSON mode");
                        }
                    } else {
                        continue;
                    }
                    i++;
                }
                z = false;
                i++;
            }
            return new SimpleConfigObject(lineOrigin, hashMap);
        }

        private AbstractConfigValue parseValue(AbstractConfigNodeValue abstractConfigNodeValue, List<String> list) {
            AbstractConfigValue parseConcatenation;
            int i = this.arrayCount;
            if (abstractConfigNodeValue instanceof ConfigNodeSimpleValue) {
                parseConcatenation = ((ConfigNodeSimpleValue) abstractConfigNodeValue).value();
            } else if (abstractConfigNodeValue instanceof ConfigNodeObject) {
                parseConcatenation = parseObject((ConfigNodeObject) abstractConfigNodeValue);
            } else if (abstractConfigNodeValue instanceof ConfigNodeArray) {
                parseConcatenation = parseArray((ConfigNodeArray) abstractConfigNodeValue);
            } else if (abstractConfigNodeValue instanceof ConfigNodeConcatenation) {
                parseConcatenation = parseConcatenation((ConfigNodeConcatenation) abstractConfigNodeValue);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expecting a value but got wrong node type: ");
                outline107.append(abstractConfigNodeValue.getClass());
                throw parseError(outline107.toString());
            }
            if (list != null && !list.isEmpty()) {
                parseConcatenation = parseConcatenation.mo10244withOrigin((ConfigOrigin) parseConcatenation.mo10176origin().prependComments(new ArrayList(list)));
                list.clear();
            }
            if (this.arrayCount == i) {
                return parseConcatenation;
            }
            throw new ConfigException.BugOrBroken("Bug in config parser: unbalanced array count");
        }

        AbstractConfigValue parse() {
            AbstractConfigNode next;
            ArrayList arrayList = new ArrayList();
            Iterator<AbstractConfigNode> it2 = this.document.children().iterator();
            AbstractConfigValue abstractConfigValue = null;
            while (true) {
                AbstractConfigValue abstractConfigValue2 = abstractConfigValue;
                while (true) {
                    boolean z = false;
                    while (it2.hasNext()) {
                        next = it2.next();
                        if (next instanceof ConfigNodeComment) {
                            break;
                        } else if (next instanceof ConfigNodeSingleToken) {
                            if (Tokens.isNewline(((ConfigNodeSingleToken) next).token())) {
                                this.lineNumber++;
                                if (z && abstractConfigValue2 == null) {
                                    arrayList.clear();
                                } else if (abstractConfigValue2 != null) {
                                    AbstractConfigValue mo10244withOrigin = abstractConfigValue2.mo10244withOrigin((ConfigOrigin) abstractConfigValue2.mo10176origin().appendComments(new ArrayList<>(arrayList)));
                                    arrayList.clear();
                                    return mo10244withOrigin;
                                }
                                z = true;
                            } else {
                                continue;
                            }
                        } else if (next instanceof ConfigNodeComplexValue) {
                            break;
                        }
                    }
                    return abstractConfigValue2;
                    arrayList.add(((ConfigNodeComment) next).commentText());
                }
                abstractConfigValue = parseValue((ConfigNodeComplexValue) next, arrayList);
            }
        }

        private ConfigException parseError(String str, Throwable th) {
            return new ConfigException.Parse(lineOrigin(), str, th);
        }
    }

    ConfigParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigValue parse(ConfigNodeRoot configNodeRoot, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions, ConfigIncludeContext configIncludeContext) {
        return new ParseContext(configParseOptions.getSyntax(), configOrigin, configNodeRoot, SimpleIncluder.makeFull(configParseOptions.getIncluder()), configIncludeContext).parse();
    }
}
