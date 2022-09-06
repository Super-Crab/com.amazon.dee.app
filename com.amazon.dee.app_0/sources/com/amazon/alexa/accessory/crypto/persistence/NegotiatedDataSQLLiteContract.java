package com.amazon.alexa.accessory.crypto.persistence;

import android.provider.BaseColumns;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
interface NegotiatedDataSQLLiteContract {
    public static final String SQLITE_DB_NAME = "CryptoKeyNegotiatedData.db";
    public static final int VERSION = 2;

    /* loaded from: classes.dex */
    public interface Columns extends BaseColumns {
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME_ACCESSORY_ID = "accessory_id";
        public static final String COLUMN_NAME_ACTIVE_KEY_VERSION = "active_key_version";
        public static final String COLUMN_NAME_CIPHER_SUITE = "cipher_suite";
        public static final String COLUMN_NAME_CIPHER_TRANSFORMATION = "cipher_transformation";
        public static final String COLUMN_NAME_KEY_EXCHANGE_PROTOCOL_VERSION = "key_exchange_protocol_version";
        public static final String COLUMN_NAME_KEY_EXCHANGE_TIMESTAMP = "key_exchange_timestamp_millis";
        public static final String COLUMN_NAME_KEY_ROTATION_TIMESTAMP = "key_rotation_timestamp_millis";
        public static final String COLUMN_NAME_KEY_STORAGE_VERSION = "key_storage_version";
        public static final String COLUMN_NAME_WRAPPED_ROOT_KEY = "wrapped_root_key";

        /* loaded from: classes.dex */
        public interface V1 extends BaseColumns {
            public static final String COLUMN_NAME_AUTHENTICATION_KEY_ALIAS = "authentication_key_alias";
            public static final String COLUMN_NAME_DECRYPTION_KEY_ALIAS = "decryption_key_alias";
            public static final String COLUMN_NAME_WRAPPING_CIPHER_TRANSFORMATION = "wrapping_cipher_transformation";
            public static final String COLUMN_NAME_WRAPPING_KEY_ALIAS = "wrapping_key_alias";
        }

        /* loaded from: classes.dex */
        public interface V2 extends BaseColumns {
            public static final String COLUMN_NAME_AUTHENTICATION_KEY_WRAPPER_ALIAS = "authentication_key_wrapper_alias";
            public static final String COLUMN_NAME_DECRYPTION_KEY_WRAPPER_ALIAS = "decryption_key_wrapper_alias";
            public static final String COLUMN_NAME_ROOT_KEY_WRAPPER_ALIAS = "root_key_wrapper_alias";
            public static final String COLUMN_NAME_WRAPPED_AUTHENTICATION_KEY = "wrapped_authentication_key";
            public static final String COLUMN_NAME_WRAPPED_AUTHENTICATION_KEY_CIPHER_TRANSFORMATION = "wrapped_authentication_key_cipher_transformation";
            public static final String COLUMN_NAME_WRAPPED_DECRYPTION_KEY = "wrapped_decryption_key";
            public static final String COLUMN_NAME_WRAPPED_DECRYPTION_KEY_CIPHER_TRANSFORMATION = "wrapped_decryption_key_cipher_transformation";
            public static final String COLUMN_NAME_WRAPPED_ROOT_KEY_CIPHER_TRANSFORMATION = "wrapped_root_key_cipher_transformation";
        }
    }

    /* loaded from: classes.dex */
    public interface Queries {
        public static final String GET_DATA_QUERY = "SELECT * FROM accessories_negotiated_crypto_data WHERE accessory_id = ?";
        public static final String LIST_ACCESSORIES_FROM_START_QUERY = "SELECT accessory_id FROM accessories_negotiated_crypto_data ORDER BY accessory_id ASC";
        public static final String LIST_ACCESSORIES_STARTING_FROM_QUERY = "SELECT accessory_id FROM accessories_negotiated_crypto_data WHERE accessory_id > ? ORDER BY accessory_id ASC";
        public static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS accessories_negotiated_crypto_data (_id INTEGER PRIMARY KEY AUTOINCREMENT,accessory_id TEXT UNIQUE,key_exchange_protocol_version INTEGER NOT NULL,cipher_suite TEXT NOT NULL,cipher_transformation TEXT NOT NULL,key_exchange_timestamp_millis INTEGER NOT NULL,key_rotation_timestamp_millis INTEGER NOT NULL,active_key_version INTEGER NOT NULL,wrapped_root_key TEXT NOT NULL,wrapping_cipher_transformation TEXT, wrapping_key_alias TEXT, decryption_key_alias TEXT, authentication_key_alias TEXT,wrapped_root_key_cipher_transformation TEXT, root_key_wrapper_alias TEXT, wrapped_decryption_key TEXT, wrapped_decryption_key_cipher_transformation TEXT, decryption_key_wrapper_alias TEXT, wrapped_authentication_key TEXT, wrapped_authentication_key_cipher_transformation TEXT, authentication_key_wrapper_alias TEXT, key_storage_version INTEGER NOT NULL)";
        public static final List<String> V1_TO_V2_MIGRATE_STATEMENTS = Arrays.asList("ALTER TABLE accessories_negotiated_crypto_data RENAME TO tmp_migration_table", CREATE_TABLE_QUERY, "INSERT INTO accessories_negotiated_crypto_data SELECT *, NULL AS wrapped_root_key_cipher_transformation, NULL AS root_key_wrapper_alias, NULL AS wrapped_decryption_key, NULL AS wrapped_decryption_key_cipher_transformation, NULL AS decryption_key_wrapper_alias, NULL AS wrapped_authentication_key, NULL AS wrapped_authentication_key_cipher_transformation, NULL AS authentication_key_wrapper_alias, 1 AS key_storage_version FROM tmp_migration_table", "DROP TABLE tmp_migration_table");
    }

    /* loaded from: classes.dex */
    public interface Tables {
        public static final String ACCESSORY_NEGOTIATED_CRYPTO_DATA = "accessories_negotiated_crypto_data";
    }

    /* loaded from: classes.dex */
    public interface Values {
        public static final int KEY_STORAGE_VERSION_V1 = 1;
        public static final int KEY_STORAGE_VERSION_V2 = 2;
    }
}
