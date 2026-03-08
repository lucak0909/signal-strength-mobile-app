package com.example.signalstrength.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WifiReadingDao_Impl implements WifiReadingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WifiReadingEntity> __insertionAdapterOfWifiReadingEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkSynced;

  public WifiReadingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWifiReadingEntity = new EntityInsertionAdapter<WifiReadingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `wifi_readings` (`id`,`userId`,`wifiRssiDbm`,`linkSpeedMbps`,`isConnected`,`timestampMs`,`syncedToSupabase`,`roomId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WifiReadingEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getWifiRssiDbm() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getWifiRssiDbm());
        }
        if (entity.getLinkSpeedMbps() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getLinkSpeedMbps());
        }
        final int _tmp = entity.isConnected() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getTimestampMs());
        final int _tmp_1 = entity.getSyncedToSupabase() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        if (entity.getRoomId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getRoomId());
        }
      }
    };
    this.__preparedStmtOfMarkSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE wifi_readings SET syncedToSupabase = 1 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertReading(final WifiReadingEntity entity,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWifiReadingEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markSynced(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkSynced.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WifiReadingEntity>> getAllReadingsFlow() {
    final String _sql = "SELECT * FROM wifi_readings ORDER BY timestampMs DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"wifi_readings"}, new Callable<List<WifiReadingEntity>>() {
      @Override
      @NonNull
      public List<WifiReadingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfWifiRssiDbm = CursorUtil.getColumnIndexOrThrow(_cursor, "wifiRssiDbm");
          final int _cursorIndexOfLinkSpeedMbps = CursorUtil.getColumnIndexOrThrow(_cursor, "linkSpeedMbps");
          final int _cursorIndexOfIsConnected = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnected");
          final int _cursorIndexOfTimestampMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMs");
          final int _cursorIndexOfSyncedToSupabase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToSupabase");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final List<WifiReadingEntity> _result = new ArrayList<WifiReadingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WifiReadingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final Integer _tmpWifiRssiDbm;
            if (_cursor.isNull(_cursorIndexOfWifiRssiDbm)) {
              _tmpWifiRssiDbm = null;
            } else {
              _tmpWifiRssiDbm = _cursor.getInt(_cursorIndexOfWifiRssiDbm);
            }
            final Integer _tmpLinkSpeedMbps;
            if (_cursor.isNull(_cursorIndexOfLinkSpeedMbps)) {
              _tmpLinkSpeedMbps = null;
            } else {
              _tmpLinkSpeedMbps = _cursor.getInt(_cursorIndexOfLinkSpeedMbps);
            }
            final boolean _tmpIsConnected;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnected);
            _tmpIsConnected = _tmp != 0;
            final long _tmpTimestampMs;
            _tmpTimestampMs = _cursor.getLong(_cursorIndexOfTimestampMs);
            final boolean _tmpSyncedToSupabase;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSyncedToSupabase);
            _tmpSyncedToSupabase = _tmp_1 != 0;
            final Long _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getLong(_cursorIndexOfRoomId);
            }
            _item = new WifiReadingEntity(_tmpId,_tmpUserId,_tmpWifiRssiDbm,_tmpLinkSpeedMbps,_tmpIsConnected,_tmpTimestampMs,_tmpSyncedToSupabase,_tmpRoomId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super WifiReadingEntity> $completion) {
    final String _sql = "SELECT * FROM wifi_readings WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WifiReadingEntity>() {
      @Override
      @Nullable
      public WifiReadingEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfWifiRssiDbm = CursorUtil.getColumnIndexOrThrow(_cursor, "wifiRssiDbm");
          final int _cursorIndexOfLinkSpeedMbps = CursorUtil.getColumnIndexOrThrow(_cursor, "linkSpeedMbps");
          final int _cursorIndexOfIsConnected = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnected");
          final int _cursorIndexOfTimestampMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMs");
          final int _cursorIndexOfSyncedToSupabase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToSupabase");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final WifiReadingEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final Integer _tmpWifiRssiDbm;
            if (_cursor.isNull(_cursorIndexOfWifiRssiDbm)) {
              _tmpWifiRssiDbm = null;
            } else {
              _tmpWifiRssiDbm = _cursor.getInt(_cursorIndexOfWifiRssiDbm);
            }
            final Integer _tmpLinkSpeedMbps;
            if (_cursor.isNull(_cursorIndexOfLinkSpeedMbps)) {
              _tmpLinkSpeedMbps = null;
            } else {
              _tmpLinkSpeedMbps = _cursor.getInt(_cursorIndexOfLinkSpeedMbps);
            }
            final boolean _tmpIsConnected;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnected);
            _tmpIsConnected = _tmp != 0;
            final long _tmpTimestampMs;
            _tmpTimestampMs = _cursor.getLong(_cursorIndexOfTimestampMs);
            final boolean _tmpSyncedToSupabase;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSyncedToSupabase);
            _tmpSyncedToSupabase = _tmp_1 != 0;
            final Long _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getLong(_cursorIndexOfRoomId);
            }
            _result = new WifiReadingEntity(_tmpId,_tmpUserId,_tmpWifiRssiDbm,_tmpLinkSpeedMbps,_tmpIsConnected,_tmpTimestampMs,_tmpSyncedToSupabase,_tmpRoomId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getUnsynced(final Continuation<? super List<WifiReadingEntity>> $completion) {
    final String _sql = "SELECT * FROM wifi_readings WHERE syncedToSupabase = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WifiReadingEntity>>() {
      @Override
      @NonNull
      public List<WifiReadingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfWifiRssiDbm = CursorUtil.getColumnIndexOrThrow(_cursor, "wifiRssiDbm");
          final int _cursorIndexOfLinkSpeedMbps = CursorUtil.getColumnIndexOrThrow(_cursor, "linkSpeedMbps");
          final int _cursorIndexOfIsConnected = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnected");
          final int _cursorIndexOfTimestampMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMs");
          final int _cursorIndexOfSyncedToSupabase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToSupabase");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final List<WifiReadingEntity> _result = new ArrayList<WifiReadingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WifiReadingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final Integer _tmpWifiRssiDbm;
            if (_cursor.isNull(_cursorIndexOfWifiRssiDbm)) {
              _tmpWifiRssiDbm = null;
            } else {
              _tmpWifiRssiDbm = _cursor.getInt(_cursorIndexOfWifiRssiDbm);
            }
            final Integer _tmpLinkSpeedMbps;
            if (_cursor.isNull(_cursorIndexOfLinkSpeedMbps)) {
              _tmpLinkSpeedMbps = null;
            } else {
              _tmpLinkSpeedMbps = _cursor.getInt(_cursorIndexOfLinkSpeedMbps);
            }
            final boolean _tmpIsConnected;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnected);
            _tmpIsConnected = _tmp != 0;
            final long _tmpTimestampMs;
            _tmpTimestampMs = _cursor.getLong(_cursorIndexOfTimestampMs);
            final boolean _tmpSyncedToSupabase;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSyncedToSupabase);
            _tmpSyncedToSupabase = _tmp_1 != 0;
            final Long _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getLong(_cursorIndexOfRoomId);
            }
            _item = new WifiReadingEntity(_tmpId,_tmpUserId,_tmpWifiRssiDbm,_tmpLinkSpeedMbps,_tmpIsConnected,_tmpTimestampMs,_tmpSyncedToSupabase,_tmpRoomId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WifiReadingEntity>> getReadingsByRoomIdFlow(final long roomId) {
    final String _sql = "SELECT * FROM wifi_readings WHERE roomId = ? ORDER BY timestampMs DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, roomId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"wifi_readings"}, new Callable<List<WifiReadingEntity>>() {
      @Override
      @NonNull
      public List<WifiReadingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfWifiRssiDbm = CursorUtil.getColumnIndexOrThrow(_cursor, "wifiRssiDbm");
          final int _cursorIndexOfLinkSpeedMbps = CursorUtil.getColumnIndexOrThrow(_cursor, "linkSpeedMbps");
          final int _cursorIndexOfIsConnected = CursorUtil.getColumnIndexOrThrow(_cursor, "isConnected");
          final int _cursorIndexOfTimestampMs = CursorUtil.getColumnIndexOrThrow(_cursor, "timestampMs");
          final int _cursorIndexOfSyncedToSupabase = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedToSupabase");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final List<WifiReadingEntity> _result = new ArrayList<WifiReadingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WifiReadingEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final Integer _tmpWifiRssiDbm;
            if (_cursor.isNull(_cursorIndexOfWifiRssiDbm)) {
              _tmpWifiRssiDbm = null;
            } else {
              _tmpWifiRssiDbm = _cursor.getInt(_cursorIndexOfWifiRssiDbm);
            }
            final Integer _tmpLinkSpeedMbps;
            if (_cursor.isNull(_cursorIndexOfLinkSpeedMbps)) {
              _tmpLinkSpeedMbps = null;
            } else {
              _tmpLinkSpeedMbps = _cursor.getInt(_cursorIndexOfLinkSpeedMbps);
            }
            final boolean _tmpIsConnected;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsConnected);
            _tmpIsConnected = _tmp != 0;
            final long _tmpTimestampMs;
            _tmpTimestampMs = _cursor.getLong(_cursorIndexOfTimestampMs);
            final boolean _tmpSyncedToSupabase;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfSyncedToSupabase);
            _tmpSyncedToSupabase = _tmp_1 != 0;
            final Long _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getLong(_cursorIndexOfRoomId);
            }
            _item = new WifiReadingEntity(_tmpId,_tmpUserId,_tmpWifiRssiDbm,_tmpLinkSpeedMbps,_tmpIsConnected,_tmpTimestampMs,_tmpSyncedToSupabase,_tmpRoomId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
