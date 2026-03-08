package com.example.signalstrength.data.repository;

import com.example.signalstrength.data.local.RoomDao;
import com.example.signalstrength.data.local.WifiReadingDao;
import com.example.signalstrength.data.local.WifiReadingEntity;
import com.example.signalstrength.data.remote.supabase.SupabaseDataSource;
import com.example.signalstrength.domain.model.WifiReading;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u0018\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0012\u001a\u00020\u000fJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/signalstrength/data/repository/WifiRepository;", "", "dao", "Lcom/example/signalstrength/data/local/WifiReadingDao;", "roomDao", "Lcom/example/signalstrength/data/local/RoomDao;", "supabaseDataSource", "Lcom/example/signalstrength/data/remote/supabase/SupabaseDataSource;", "(Lcom/example/signalstrength/data/local/WifiReadingDao;Lcom/example/signalstrength/data/local/RoomDao;Lcom/example/signalstrength/data/remote/supabase/SupabaseDataSource;)V", "getAllReadingsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/signalstrength/domain/model/WifiReading;", "getReadingById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReadingsByRoom", "roomId", "insertReading", "", "entity", "Lcom/example/signalstrength/data/local/WifiReadingEntity;", "(Lcom/example/signalstrength/data/local/WifiReadingEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncUnsynced", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class WifiRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.local.WifiReadingDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.local.RoomDao roomDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.remote.supabase.SupabaseDataSource supabaseDataSource = null;
    
    @javax.inject.Inject()
    public WifiRepository(@org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.local.WifiReadingDao dao, @org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.local.RoomDao roomDao, @org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.remote.supabase.SupabaseDataSource supabaseDataSource) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.signalstrength.domain.model.WifiReading>> getAllReadingsFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getReadingById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.signalstrength.domain.model.WifiReading> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertReading(@org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.local.WifiReadingEntity entity, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.signalstrength.domain.model.WifiReading>> getReadingsByRoom(long roomId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncUnsynced(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}