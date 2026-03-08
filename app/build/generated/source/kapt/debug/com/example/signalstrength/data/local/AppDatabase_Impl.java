package com.example.signalstrength.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile WifiReadingDao _wifiReadingDao;

  private volatile RoomDao _roomDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `wifi_readings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `wifiRssiDbm` INTEGER, `linkSpeedMbps` INTEGER, `isConnected` INTEGER NOT NULL, `timestampMs` INTEGER NOT NULL, `syncedToSupabase` INTEGER NOT NULL, `roomId` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `rooms` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` TEXT NOT NULL, `name` TEXT NOT NULL, `supabaseId` INTEGER, `createdAtMs` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bad6468e73f2ec1431d4a756e450340d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `wifi_readings`");
        db.execSQL("DROP TABLE IF EXISTS `rooms`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsWifiReadings = new HashMap<String, TableInfo.Column>(8);
        _columnsWifiReadings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("wifiRssiDbm", new TableInfo.Column("wifiRssiDbm", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("linkSpeedMbps", new TableInfo.Column("linkSpeedMbps", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("isConnected", new TableInfo.Column("isConnected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("timestampMs", new TableInfo.Column("timestampMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("syncedToSupabase", new TableInfo.Column("syncedToSupabase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWifiReadings.put("roomId", new TableInfo.Column("roomId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWifiReadings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWifiReadings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWifiReadings = new TableInfo("wifi_readings", _columnsWifiReadings, _foreignKeysWifiReadings, _indicesWifiReadings);
        final TableInfo _existingWifiReadings = TableInfo.read(db, "wifi_readings");
        if (!_infoWifiReadings.equals(_existingWifiReadings)) {
          return new RoomOpenHelper.ValidationResult(false, "wifi_readings(com.example.signalstrength.data.local.WifiReadingEntity).\n"
                  + " Expected:\n" + _infoWifiReadings + "\n"
                  + " Found:\n" + _existingWifiReadings);
        }
        final HashMap<String, TableInfo.Column> _columnsRooms = new HashMap<String, TableInfo.Column>(5);
        _columnsRooms.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("supabaseId", new TableInfo.Column("supabaseId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("createdAtMs", new TableInfo.Column("createdAtMs", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRooms = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRooms = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRooms = new TableInfo("rooms", _columnsRooms, _foreignKeysRooms, _indicesRooms);
        final TableInfo _existingRooms = TableInfo.read(db, "rooms");
        if (!_infoRooms.equals(_existingRooms)) {
          return new RoomOpenHelper.ValidationResult(false, "rooms(com.example.signalstrength.data.local.RoomEntity).\n"
                  + " Expected:\n" + _infoRooms + "\n"
                  + " Found:\n" + _existingRooms);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bad6468e73f2ec1431d4a756e450340d", "89f13c85f17c52bb32d25ba2f1d0498a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "wifi_readings","rooms");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `wifi_readings`");
      _db.execSQL("DELETE FROM `rooms`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(WifiReadingDao.class, WifiReadingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RoomDao.class, RoomDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public WifiReadingDao wifiReadingDao() {
    if (_wifiReadingDao != null) {
      return _wifiReadingDao;
    } else {
      synchronized(this) {
        if(_wifiReadingDao == null) {
          _wifiReadingDao = new WifiReadingDao_Impl(this);
        }
        return _wifiReadingDao;
      }
    }
  }

  @Override
  public RoomDao roomDao() {
    if (_roomDao != null) {
      return _roomDao;
    } else {
      synchronized(this) {
        if(_roomDao == null) {
          _roomDao = new RoomDao_Impl(this);
        }
        return _roomDao;
      }
    }
  }
}
