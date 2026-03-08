package com.example.signalstrength.data.repository;

import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.local.RoomDao;
import com.example.signalstrength.data.local.RoomEntity;
import com.example.signalstrength.data.local.WifiReadingDao;
import com.example.signalstrength.data.remote.supabase.SupabaseRoomDataSource;
import com.example.signalstrength.domain.model.Room;
import com.example.signalstrength.domain.model.RoomWithStats;
import java.time.Instant;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u0012J\u000e\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\f\u0010\u001c\u001a\u00020\f*\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/example/signalstrength/data/repository/RoomRepository;", "", "roomDao", "Lcom/example/signalstrength/data/local/RoomDao;", "wifiReadingDao", "Lcom/example/signalstrength/data/local/WifiReadingDao;", "supabaseRoomDataSource", "Lcom/example/signalstrength/data/remote/supabase/SupabaseRoomDataSource;", "userPreferencesDataStore", "Lcom/example/signalstrength/data/datastore/UserPreferencesDataStore;", "(Lcom/example/signalstrength/data/local/RoomDao;Lcom/example/signalstrength/data/local/WifiReadingDao;Lcom/example/signalstrength/data/remote/supabase/SupabaseRoomDataSource;Lcom/example/signalstrength/data/datastore/UserPreferencesDataStore;)V", "getByLocalId", "Lcom/example/signalstrength/domain/model/Room;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrCreate", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoomsWithStatsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/signalstrength/domain/model/RoomWithStats;", "userId", "syncRooms", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toDomain", "Lcom/example/signalstrength/data/local/RoomEntity;", "app_debug"})
public final class RoomRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.local.RoomDao roomDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.local.WifiReadingDao wifiReadingDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.remote.supabase.SupabaseRoomDataSource supabaseRoomDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.datastore.UserPreferencesDataStore userPreferencesDataStore = null;
    
    @javax.inject.Inject()
    public RoomRepository(@org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.local.RoomDao roomDao, @org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.local.WifiReadingDao wifiReadingDao, @org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.remote.supabase.SupabaseRoomDataSource supabaseRoomDataSource, @org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.datastore.UserPreferencesDataStore userPreferencesDataStore) {
        super();
    }
    
    /**
     * Flow of all rooms (for current user) combined with their aggregate stats.
     * Re-emits whenever rooms or readings change.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.signalstrength.domain.model.RoomWithStats>> getRoomsWithStatsFlow(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    /**
     * Returns the local Room DB id for the given room name (creates it if it doesn't exist).
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOrCreate(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    /**
     * Syncs any rooms that haven't yet been pushed to Supabase.
     * Called before syncing wifi_samples so the FK is available.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncRooms(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getByLocalId(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.signalstrength.domain.model.Room> $completion) {
        return null;
    }
    
    private final com.example.signalstrength.domain.model.Room toDomain(com.example.signalstrength.data.local.RoomEntity $this$toDomain) {
        return null;
    }
}