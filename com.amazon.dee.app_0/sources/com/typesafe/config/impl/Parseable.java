package com.typesafe.config.impl;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import com.typesafe.config.ConfigSyntax;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.parser.ConfigDocument;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Properties;
/* loaded from: classes3.dex */
public abstract class Parseable implements ConfigParseable {
    private static final int MAX_INCLUDE_DEPTH = 50;
    private static final String hoconContentType = "application/hocon";
    private static final String jsonContentType = "application/json";
    private static final ThreadLocal<LinkedList<Parseable>> parseStack = new ThreadLocal<LinkedList<Parseable>>() { // from class: com.typesafe.config.impl.Parseable.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public LinkedList<Parseable> initialValue() {
            return new LinkedList<>();
        }
    };
    private static final String propertiesContentType = "text/x-java-properties";
    private ConfigIncludeContext includeContext;
    private ConfigParseOptions initialOptions;
    private ConfigOrigin initialOrigin;

    /* renamed from: com.typesafe.config.impl.Parseable$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$ConfigSyntax = new int[ConfigSyntax.values().length];

        static {
            try {
                $SwitchMap$com$typesafe$config$ConfigSyntax[ConfigSyntax.JSON.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigSyntax[ConfigSyntax.CONF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigSyntax[ConfigSyntax.PROPERTIES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ParseableFile extends Parseable {
        private final File input;

        ParseableFile(File file, ConfigParseOptions configParseOptions) {
            this.input = file;
            postConstruct(configParseOptions);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newFile(this.input.getPath());
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigSyntax guessSyntax() {
            return Parseable.syntaxFromExtension(this.input.getName());
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() throws IOException {
            if (ConfigImpl.traceLoadsEnabled()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loading config from a file: ");
                outline107.append(this.input);
                Parseable.trace(outline107.toString());
            }
            return Parseable.readerFromStream(new FileInputStream(this.input));
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigParseable relativeTo(String str) {
            File relativeTo;
            if (new File(str).isAbsolute()) {
                relativeTo = new File(str);
            } else {
                relativeTo = Parseable.relativeTo(this.input, str);
            }
            if (relativeTo == null) {
                return null;
            }
            if (relativeTo.exists()) {
                Parseable.trace(relativeTo + " exists, so loading it as a file");
                return Parseable.newFile(relativeTo, options().setOriginDescription(null));
            }
            Parseable.trace(relativeTo + " does not exist, so trying it as a classpath resource");
            return super.relativeTo(str);
        }

        @Override // com.typesafe.config.impl.Parseable
        public String toString() {
            return ParseableFile.class.getSimpleName() + "(" + this.input.getPath() + ")";
        }
    }

    /* loaded from: classes3.dex */
    private static final class ParseableNotFound extends Parseable {
        private final String message;
        private final String what;

        ParseableNotFound(String str, String str2, ConfigParseOptions configParseOptions) {
            this.what = str;
            this.message = str2;
            postConstruct(configParseOptions);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple(this.what);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() throws IOException {
            throw new FileNotFoundException(this.message);
        }
    }

    /* loaded from: classes3.dex */
    private static final class ParseableProperties extends Parseable {
        private final Properties props;

        ParseableProperties(Properties properties, ConfigParseOptions configParseOptions) {
            this.props = properties;
            postConstruct(configParseOptions);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple("properties");
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigSyntax guessSyntax() {
            return ConfigSyntax.PROPERTIES;
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() throws IOException {
            throw new ConfigException.BugOrBroken("reader() should not be called on props");
        }

        @Override // com.typesafe.config.impl.Parseable
        public String toString() {
            return ParseableProperties.class.getSimpleName() + "(" + this.props.size() + " props)";
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.typesafe.config.impl.Parseable
        /* renamed from: rawParseValue */
        public AbstractConfigObject mo10222rawParseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
            if (ConfigImpl.traceLoadsEnabled()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loading config from properties ");
                outline107.append(this.props);
                Parseable.trace(outline107.toString());
            }
            return PropertiesParser.fromProperties(configOrigin, this.props);
        }
    }

    /* loaded from: classes3.dex */
    private static final class ParseableReader extends Parseable {
        private final Reader reader;

        ParseableReader(Reader reader, ConfigParseOptions configParseOptions) {
            this.reader = reader;
            postConstruct(configParseOptions);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple("Reader");
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() {
            if (ConfigImpl.traceLoadsEnabled()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loading config from reader ");
                outline107.append(this.reader);
                Parseable.trace(outline107.toString());
            }
            return this.reader;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ParseableResourceURL extends ParseableURL {
        private final Relativizer relativizer;
        private final String resource;

        ParseableResourceURL(URL url, ConfigParseOptions configParseOptions, String str, Relativizer relativizer) {
            super(url);
            this.relativizer = relativizer;
            this.resource = str;
            postConstruct(configParseOptions);
        }

        @Override // com.typesafe.config.impl.Parseable.ParseableURL, com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newResource(this.resource, this.input);
        }

        @Override // com.typesafe.config.impl.Parseable.ParseableURL, com.typesafe.config.impl.Parseable
        ConfigParseable relativeTo(String str) {
            return this.relativizer.relativeTo(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class ParseableResources extends Parseable implements Relativizer {
        private final String resource;

        ParseableResources(String str, ConfigParseOptions configParseOptions) {
            this.resource = str;
            postConstruct(configParseOptions);
        }

        static String parent(String str) {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf < 0) {
                return null;
            }
            return str.substring(0, lastIndexOf);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newResource(this.resource);
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigSyntax guessSyntax() {
            return Parseable.syntaxFromExtension(this.resource);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() throws IOException {
            throw new ConfigException.BugOrBroken("reader() should not be called on resources");
        }

        @Override // com.typesafe.config.impl.Parseable
        public ConfigParseable relativeTo(String str) {
            if (str.startsWith("/")) {
                return Parseable.newResources(str.substring(1), options().setOriginDescription(null));
            }
            String parent = parent(this.resource);
            if (parent == null) {
                return Parseable.newResources(str, options().setOriginDescription(null));
            }
            return Parseable.newResources(GeneratedOutlineSupport1.outline75(parent, "/", str), options().setOriginDescription(null));
        }

        @Override // com.typesafe.config.impl.Parseable
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(ParseableResources.class.getSimpleName());
            sb.append("(");
            return GeneratedOutlineSupport1.outline91(sb, this.resource, ")");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.typesafe.config.impl.Parseable
        /* renamed from: rawParseValue */
        public AbstractConfigObject mo10222rawParseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
            ClassLoader classLoader = configParseOptions.getClassLoader();
            if (classLoader != null) {
                Enumeration<URL> resources = classLoader.getResources(this.resource);
                if (!resources.hasMoreElements()) {
                    if (ConfigImpl.traceLoadsEnabled()) {
                        Parseable.trace("Loading config from class loader " + classLoader + " but there were no resources called " + this.resource);
                    }
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("resource not found on classpath: ");
                    outline107.append(this.resource);
                    throw new IOException(outline107.toString());
                }
                AbstractConfigObject empty = SimpleConfigObject.empty(configOrigin);
                while (resources.hasMoreElements()) {
                    URL nextElement = resources.nextElement();
                    if (ConfigImpl.traceLoadsEnabled()) {
                        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Loading config from resource '");
                        outline1072.append(this.resource);
                        outline1072.append("' URL ");
                        outline1072.append(nextElement.toExternalForm());
                        outline1072.append(" from class loader ");
                        outline1072.append(classLoader);
                        Parseable.trace(outline1072.toString());
                    }
                    empty = empty.mo10234withFallback((ConfigMergeable) Parseable.newResourceURL(nextElement, configParseOptions, this.resource, this).parseValue());
                }
                return empty;
            }
            throw new ConfigException.BugOrBroken("null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()");
        }
    }

    /* loaded from: classes3.dex */
    private static final class ParseableString extends Parseable {
        private final String input;

        ParseableString(String str, ConfigParseOptions configParseOptions) {
            this.input = str;
            postConstruct(configParseOptions);
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newSimple("String");
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() {
            if (ConfigImpl.traceLoadsEnabled()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Loading config from a String ");
                outline107.append(this.input);
                Parseable.trace(outline107.toString());
            }
            return new StringReader(this.input);
        }

        @Override // com.typesafe.config.impl.Parseable
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(ParseableString.class.getSimpleName());
            sb.append("(");
            return GeneratedOutlineSupport1.outline91(sb, this.input, ")");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ParseableURL extends Parseable {
        private String contentType;
        protected final URL input;

        protected ParseableURL(URL url) {
            this.contentType = null;
            this.input = url;
        }

        private static String acceptContentType(ConfigParseOptions configParseOptions) {
            if (configParseOptions.getSyntax() == null) {
                return null;
            }
            int ordinal = configParseOptions.getSyntax().ordinal();
            if (ordinal == 0) {
                return "application/json";
            }
            if (ordinal == 1) {
                return Parseable.hoconContentType;
            }
            if (ordinal == 2) {
                return Parseable.propertiesContentType;
            }
            return null;
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigSyntax contentType() {
            String str = this.contentType;
            if (str != null) {
                if (str.equals("application/json")) {
                    return ConfigSyntax.JSON;
                }
                if (this.contentType.equals(Parseable.propertiesContentType)) {
                    return ConfigSyntax.PROPERTIES;
                }
                if (this.contentType.equals(Parseable.hoconContentType)) {
                    return ConfigSyntax.CONF;
                }
                if (ConfigImpl.traceLoadsEnabled()) {
                    Parseable.trace(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("'"), this.contentType, "' isn't a known content type"));
                }
            }
            return null;
        }

        @Override // com.typesafe.config.impl.Parseable
        protected ConfigOrigin createOrigin() {
            return SimpleConfigOrigin.newURL(this.input);
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigSyntax guessSyntax() {
            return Parseable.syntaxFromExtension(this.input.getPath());
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader() throws IOException {
            throw new ConfigException.BugOrBroken("reader() without options should not be called on ParseableURL");
        }

        @Override // com.typesafe.config.impl.Parseable
        ConfigParseable relativeTo(String str) {
            URL relativeTo = Parseable.relativeTo(this.input, str);
            if (relativeTo == null) {
                return null;
            }
            return Parseable.newURL(relativeTo, options().setOriginDescription(null));
        }

        @Override // com.typesafe.config.impl.Parseable
        public String toString() {
            return getClass().getSimpleName() + "(" + this.input.toExternalForm() + ")";
        }

        @Override // com.typesafe.config.impl.Parseable
        protected Reader reader(ConfigParseOptions configParseOptions) throws IOException {
            try {
                if (ConfigImpl.traceLoadsEnabled()) {
                    Parseable.trace("Loading config from a URL: " + this.input.toExternalForm());
                }
                URLConnection openConnection = this.input.openConnection();
                String acceptContentType = acceptContentType(configParseOptions);
                if (acceptContentType != null) {
                    openConnection.setRequestProperty("Accept", acceptContentType);
                }
                openConnection.connect();
                this.contentType = openConnection.getContentType();
                if (this.contentType != null) {
                    if (ConfigImpl.traceLoadsEnabled()) {
                        Parseable.trace("URL sets Content-Type: '" + this.contentType + "'");
                    }
                    this.contentType = this.contentType.trim();
                    int indexOf = this.contentType.indexOf(59);
                    if (indexOf >= 0) {
                        this.contentType = this.contentType.substring(0, indexOf);
                    }
                }
                return Parseable.readerFromStream(openConnection.getInputStream());
            } catch (FileNotFoundException e) {
                throw e;
            } catch (IOException e2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot load config from URL: ");
                outline107.append(this.input.toExternalForm());
                throw new ConfigException.BugOrBroken(outline107.toString(), e2);
            }
        }

        ParseableURL(URL url, ConfigParseOptions configParseOptions) {
            this(url);
            postConstruct(configParseOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes3.dex */
    public interface Relativizer {
        ConfigParseable relativeTo(String str);
    }

    protected Parseable() {
    }

    private static String convertResourceName(Class<?> cls, String str) {
        if (str.startsWith("/")) {
            return str.substring(1);
        }
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf < 0 ? str : GeneratedOutlineSupport1.outline75(name.substring(0, lastIndexOf).replace('.', '/'), "/", str);
    }

    private static Reader doNotClose(Reader reader) {
        return new FilterReader(reader) { // from class: com.typesafe.config.impl.Parseable.2
            @Override // java.io.FilterReader, java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }
        };
    }

    private ConfigParseOptions fixupOptions(ConfigParseOptions configParseOptions) {
        ConfigSyntax syntax = configParseOptions.getSyntax();
        if (syntax == null) {
            syntax = guessSyntax();
        }
        if (syntax == null) {
            syntax = ConfigSyntax.CONF;
        }
        ConfigParseOptions appendIncluder = configParseOptions.setSyntax(syntax).appendIncluder(ConfigImpl.defaultIncluder());
        return appendIncluder.setIncluder(SimpleIncluder.makeFull(appendIncluder.getIncluder()));
    }

    static AbstractConfigObject forceParsedToObject(ConfigValue configValue) {
        if (configValue instanceof AbstractConfigObject) {
            return (AbstractConfigObject) configValue;
        }
        throw new ConfigException.WrongType(configValue.mo10176origin(), "", "object at file root", configValue.valueType().name());
    }

    public static Parseable newFile(File file, ConfigParseOptions configParseOptions) {
        return new ParseableFile(file, configParseOptions);
    }

    public static Parseable newNotFound(String str, String str2, ConfigParseOptions configParseOptions) {
        return new ParseableNotFound(str, str2, configParseOptions);
    }

    public static Parseable newProperties(Properties properties, ConfigParseOptions configParseOptions) {
        return new ParseableProperties(properties, configParseOptions);
    }

    public static Parseable newReader(Reader reader, ConfigParseOptions configParseOptions) {
        return new ParseableReader(doNotClose(reader), configParseOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Parseable newResourceURL(URL url, ConfigParseOptions configParseOptions, String str, Relativizer relativizer) {
        return new ParseableResourceURL(url, configParseOptions, str, relativizer);
    }

    public static Parseable newResources(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return newResources(convertResourceName(cls, str), configParseOptions.setClassLoader(cls.getClassLoader()));
    }

    public static Parseable newString(String str, ConfigParseOptions configParseOptions) {
        return new ParseableString(str, configParseOptions);
    }

    public static Parseable newURL(URL url, ConfigParseOptions configParseOptions) {
        if (url.getProtocol().equals("file")) {
            return newFile(ConfigImplUtil.urlToFile(url), configParseOptions);
        }
        return new ParseableURL(url, configParseOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Reader readerFromStream(InputStream inputStream) {
        return readerFromStream(inputStream, "UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ConfigSyntax syntaxFromExtension(String str) {
        if (str.endsWith(".json")) {
            return ConfigSyntax.JSON;
        }
        if (str.endsWith(".conf")) {
            return ConfigSyntax.CONF;
        }
        if (!str.endsWith(".properties")) {
            return null;
        }
        return ConfigSyntax.PROPERTIES;
    }

    protected static void trace(String str) {
        if (ConfigImpl.traceLoadsEnabled()) {
            ConfigImpl.trace(str);
        }
    }

    ConfigSyntax contentType() {
        return null;
    }

    protected abstract ConfigOrigin createOrigin();

    ConfigSyntax guessSyntax() {
        return null;
    }

    ConfigIncludeContext includeContext() {
        return this.includeContext;
    }

    @Override // com.typesafe.config.ConfigParseable
    public ConfigParseOptions options() {
        return this.initialOptions;
    }

    @Override // com.typesafe.config.ConfigParseable
    public final ConfigOrigin origin() {
        return this.initialOrigin;
    }

    @Override // com.typesafe.config.ConfigParseable
    public ConfigObject parse(ConfigParseOptions configParseOptions) {
        LinkedList<Parseable> linkedList = parseStack.get();
        if (linkedList.size() < 50) {
            linkedList.addFirst(this);
            try {
                return forceParsedToObject(parseValue(configParseOptions));
            } finally {
                linkedList.removeFirst();
                if (linkedList.isEmpty()) {
                    parseStack.remove();
                }
            }
        }
        ConfigOrigin configOrigin = this.initialOrigin;
        throw new ConfigException.Parse(configOrigin, "include statements nested more than 50 times, you probably have a cycle in your includes. Trace: " + linkedList);
    }

    public ConfigDocument parseConfigDocument() {
        return parseDocument(options());
    }

    final ConfigDocument parseDocument(ConfigParseOptions configParseOptions) {
        ConfigOrigin configOrigin;
        ConfigParseOptions fixupOptions = fixupOptions(configParseOptions);
        if (fixupOptions.getOriginDescription() != null) {
            configOrigin = SimpleConfigOrigin.newSimple(fixupOptions.getOriginDescription());
        } else {
            configOrigin = this.initialOrigin;
        }
        return parseDocument(configOrigin, fixupOptions);
    }

    final AbstractConfigValue parseValue(ConfigParseOptions configParseOptions) {
        ConfigOrigin configOrigin;
        ConfigParseOptions fixupOptions = fixupOptions(configParseOptions);
        if (fixupOptions.getOriginDescription() != null) {
            configOrigin = SimpleConfigOrigin.newSimple(fixupOptions.getOriginDescription());
        } else {
            configOrigin = this.initialOrigin;
        }
        return parseValue(configOrigin, fixupOptions);
    }

    protected void postConstruct(ConfigParseOptions configParseOptions) {
        this.initialOptions = fixupOptions(configParseOptions);
        this.includeContext = new SimpleIncludeContext(this);
        if (this.initialOptions.getOriginDescription() != null) {
            this.initialOrigin = SimpleConfigOrigin.newSimple(this.initialOptions.getOriginDescription());
        } else {
            this.initialOrigin = createOrigin();
        }
    }

    protected ConfigDocument rawParseDocument(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        Reader reader = reader(configParseOptions);
        ConfigSyntax contentType = contentType();
        if (contentType != null) {
            if (ConfigImpl.traceLoadsEnabled() && configParseOptions.getSyntax() != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Overriding syntax ");
                outline107.append(configParseOptions.getSyntax());
                outline107.append(" with Content-Type which specified ");
                outline107.append(contentType);
                trace(outline107.toString());
            }
            configParseOptions = configParseOptions.setSyntax(contentType);
        }
        try {
            return rawParseDocument(reader, configOrigin, configParseOptions);
        } finally {
            reader.close();
        }
    }

    /* renamed from: rawParseValue */
    protected AbstractConfigValue mo10222rawParseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        Reader reader = reader(configParseOptions);
        ConfigSyntax contentType = contentType();
        if (contentType != null) {
            if (ConfigImpl.traceLoadsEnabled() && configParseOptions.getSyntax() != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Overriding syntax ");
                outline107.append(configParseOptions.getSyntax());
                outline107.append(" with Content-Type which specified ");
                outline107.append(contentType);
                trace(outline107.toString());
            }
            configParseOptions = configParseOptions.setSyntax(contentType);
        }
        try {
            return rawParseValue(reader, configOrigin, configParseOptions);
        } finally {
            reader.close();
        }
    }

    protected abstract Reader reader() throws IOException;

    protected Reader reader(ConfigParseOptions configParseOptions) throws IOException {
        return reader();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigParseable relativeTo(String str) {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        return newResources(str, options().setOriginDescription(null));
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    private static Reader readerFromStream(InputStream inputStream, String str) {
        try {
            return new BufferedReader(new InputStreamReader(inputStream, str));
        } catch (UnsupportedEncodingException e) {
            throw new ConfigException.BugOrBroken("Java runtime does not support UTF-8", e);
        }
    }

    public static Parseable newResources(String str, ConfigParseOptions configParseOptions) {
        if (configParseOptions.getClassLoader() != null) {
            return new ParseableResources(str, configParseOptions);
        }
        throw new ConfigException.BugOrBroken("null class loader; pass in a class loader or use Thread.currentThread().setContextClassLoader()");
    }

    static URL relativeTo(URL url, String str) {
        if (new File(str).isAbsolute()) {
            return null;
        }
        try {
            return url.toURI().resolve(new URI(str)).toURL();
        } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused) {
            return null;
        }
    }

    private final ConfigDocument parseDocument(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        try {
            return rawParseDocument(configOrigin, configParseOptions);
        } catch (IOException e) {
            if (configParseOptions.getAllowMissing()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new ConfigNodeObject(new ArrayList()));
                return new SimpleConfigDocument(new ConfigNodeRoot(arrayList, configOrigin), configParseOptions);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception loading ");
            outline107.append(configOrigin.description());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.getClass().getName());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.getMessage());
            trace(outline107.toString());
            throw new ConfigException.IO(configOrigin, e.getClass().getName() + RealTimeTextConstants.COLON_SPACE + e.getMessage(), e);
        }
    }

    private final AbstractConfigValue parseValue(ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) {
        try {
            return mo10222rawParseValue(configOrigin, configParseOptions);
        } catch (IOException e) {
            if (configParseOptions.getAllowMissing()) {
                trace(e.getMessage() + ". Allowing Missing File, this can be turned off by setting ConfigParseOptions.allowMissing = false");
                return SimpleConfigObject.emptyMissing(configOrigin);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exception loading ");
            outline107.append(configOrigin.description());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.getClass().getName());
            outline107.append(RealTimeTextConstants.COLON_SPACE);
            outline107.append(e.getMessage());
            trace(outline107.toString());
            throw new ConfigException.IO(configOrigin, e.getClass().getName() + RealTimeTextConstants.COLON_SPACE + e.getMessage(), e);
        }
    }

    static File relativeTo(File file, String str) {
        File parentFile;
        if (!new File(str).isAbsolute() && (parentFile = file.getParentFile()) != null) {
            return new File(parentFile, str);
        }
        return null;
    }

    private ConfigDocument rawParseDocument(Reader reader, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        return new SimpleConfigDocument(ConfigDocumentParser.parse(Tokenizer.tokenize(configOrigin, reader, configParseOptions.getSyntax()), configOrigin, configParseOptions), configParseOptions);
    }

    private AbstractConfigValue rawParseValue(Reader reader, ConfigOrigin configOrigin, ConfigParseOptions configParseOptions) throws IOException {
        if (configParseOptions.getSyntax() == ConfigSyntax.PROPERTIES) {
            return PropertiesParser.parse(reader, configOrigin);
        }
        return ConfigParser.parse(ConfigDocumentParser.parse(Tokenizer.tokenize(configOrigin, reader, configParseOptions.getSyntax()), configOrigin, configParseOptions), configOrigin, configParseOptions, includeContext());
    }

    public ConfigObject parse() {
        return forceParsedToObject(parseValue(options()));
    }

    AbstractConfigValue parseValue() {
        return parseValue(options());
    }
}
