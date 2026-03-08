package com.example.signalstrength.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
public final class RoomDao_Impl implements RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RoomEntity> __insertionAdapterOfRoomEntity;

  private final EntityDeletionOrUpdateAdapter<RoomEntity> __updateAdapterOfRoomEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateSupabaseId;

  public RoomDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoomEntity = new EntityInsertionAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `rooms` (`id`,`userId`,`name`,`supabaseId`,`createdAtMs`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getSupabaseId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getSupabaseId());
        }
        statement.bindLong(5, entity.getCreatedAtMs());
      }
    };
    this.__updateAdapterOfRoomEntity = new EntityDeletionOrUpdateAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `rooms` SET `id` = ?,`userId` = ?,`name` = ?,`supabaseId` = ?,`createdAtMs` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUserId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUserId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getSupabaseId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getSupabaseId());
        }
        statement.bindLong(5, entity.getCreatedAtMs());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateSupabaseId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE rooms SET supabaseId = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertRoom(final RoomEntity room, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfRoomEntity.insertAndReturnId(room);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRoomEntity.handle(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateSupabaseId(final long localId, final long supabaseId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateSupabaseId.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, supabaseId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, localId);
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
          __preparedStmtOfUpdateSupabaseId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RoomEntity>> getAllRoomsFlow(final String userId) {
    final String _sql = "SELECT * FROM rooms WHERE userId = ? ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSupabaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "supabaseId");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMs");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpSupabaseId;
            if (_cursor.isNull(_cursorIndexOfSupabaseId)) {
              _tmpSupabaseId = null;
            } else {
              _tmpSupabaseId = _cursor.getLong(_cursorIndexOfSupabaseId);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _item = new RoomEntity(_tmpId,_tmpUserId,_tmpName,_tmpSupabaseId,_tmpCreatedAtMs);
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
  public Object getById(final long id, final Continuation<? super RoomEntity> $completion) {
    final String _sql = "SELECT * FROM rooms WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RoomEntity>() {
      @Override
      @Nullable
      public RoomEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSupabaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "supabaseId");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMs");
          final RoomEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpSupabaseId;
            if (_cursor.isNull(_cursorIndexOfSupabaseId)) {
              _tmpSupabaseId = null;
            } else {
              _tmpSupabaseId = _cursor.getLong(_cursorIndexOfSupabaseId);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _result = new RoomEntity(_tmpId,_tmpUserId,_tmpName,_tmpSupabaseId,_tmpCreatedAtMs);
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
  public Object getByName(final String userId, final String name,
      final Continuation<? super RoomEntity> $completion) {
    final String _sql = "SELECT * FROM rooms WHERE userId = ? AND name = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RoomEntity>() {
      @Override
      @Nullable
      public RoomEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSupabaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "supabaseId");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMs");
          final RoomEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpSupabaseId;
            if (_cursor.isNull(_cursorIndexOfSupabaseId)) {
              _tmpSupabaseId = null;
            } else {
              _tmpSupabaseId = _cursor.getLong(_cursorIndexOfSupabaseId);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _result = new RoomEntity(_tmpId,_tmpUserId,_tmpName,_tmpSupabaseId,_tmpCreatedAtMs);
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
  public Object getUnsynced(final Continuation<? super List<RoomEntity>> $completion) {
    final String _sql = "SELECT * FROM rooms WHERE supabaseId IS NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSupabaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "supabaseId");
          final int _cursorIndexOfCreatedAtMs = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMs");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpSupabaseId;
            if (_cursor.isNull(_cursorIndexOfSupabaseId)) {
              _tmpSupabaseId = null;
            } else {
              _tmpSupabaseId = _cursor.getLong(_cursorIndexOfSupabaseId);
            }
            final long _tmpCreatedAtMs;
            _tmpCreatedAtMs = _cursor.getLong(_cursorIndexOfCreatedAtMs);
            _item = new RoomEntity(_tmpId,_tmpUserId,_tmpName,_tmpSupabaseId,_tmpCreatedAtMs);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
