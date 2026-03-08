package com.example.signalstrength.data.repository;

import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.remote.auth.LoginRequest;
import com.example.signalstrength.data.remote.auth.RegisterRequest;
import com.example.signalstrength.data.remote.auth.SupabaseAuthDataSource;
import com.example.signalstrength.domain.model.User;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0013\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0014"}, d2 = {"Lcom/example/signalstrength/data/repository/AuthRepository;", "", "authDataSource", "Lcom/example/signalstrength/data/remote/auth/SupabaseAuthDataSource;", "dataStore", "Lcom/example/signalstrength/data/datastore/UserPreferencesDataStore;", "(Lcom/example/signalstrength/data/remote/auth/SupabaseAuthDataSource;Lcom/example/signalstrength/data/datastore/UserPreferencesDataStore;)V", "login", "Lkotlin/Result;", "Lcom/example/signalstrength/domain/model/User;", "email", "", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "register-0E7RQCE", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.remote.auth.SupabaseAuthDataSource authDataSource = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.signalstrength.data.datastore.UserPreferencesDataStore dataStore = null;
    
    @javax.inject.Inject()
    public AuthRepository(@org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.remote.auth.SupabaseAuthDataSource authDataSource, @org.jetbrains.annotations.NotNull()
    com.example.signalstrength.data.datastore.UserPreferencesDataStore dataStore) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}