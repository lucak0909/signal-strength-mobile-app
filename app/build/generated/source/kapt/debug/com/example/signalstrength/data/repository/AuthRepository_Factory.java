package com.example.signalstrength.data.repository;

import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.remote.auth.SupabaseAuthDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class AuthRepository_Factory implements Factory<AuthRepository> {
  private final Provider<SupabaseAuthDataSource> authDataSourceProvider;

  private final Provider<UserPreferencesDataStore> dataStoreProvider;

  public AuthRepository_Factory(Provider<SupabaseAuthDataSource> authDataSourceProvider,
      Provider<UserPreferencesDataStore> dataStoreProvider) {
    this.authDataSourceProvider = authDataSourceProvider;
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public AuthRepository get() {
    return newInstance(authDataSourceProvider.get(), dataStoreProvider.get());
  }

  public static AuthRepository_Factory create(
      Provider<SupabaseAuthDataSource> authDataSourceProvider,
      Provider<UserPreferencesDataStore> dataStoreProvider) {
    return new AuthRepository_Factory(authDataSourceProvider, dataStoreProvider);
  }

  public static AuthRepository newInstance(SupabaseAuthDataSource authDataSource,
      UserPreferencesDataStore dataStore) {
    return new AuthRepository(authDataSource, dataStore);
  }
}
