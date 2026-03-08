package com.example.signalstrength.data.repository;

import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.local.RoomDao;
import com.example.signalstrength.data.local.WifiReadingDao;
import com.example.signalstrength.data.remote.supabase.SupabaseRoomDataSource;
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
public final class RoomRepository_Factory implements Factory<RoomRepository> {
  private final Provider<RoomDao> roomDaoProvider;

  private final Provider<WifiReadingDao> wifiReadingDaoProvider;

  private final Provider<SupabaseRoomDataSource> supabaseRoomDataSourceProvider;

  private final Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider;

  public RoomRepository_Factory(Provider<RoomDao> roomDaoProvider,
      Provider<WifiReadingDao> wifiReadingDaoProvider,
      Provider<SupabaseRoomDataSource> supabaseRoomDataSourceProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider) {
    this.roomDaoProvider = roomDaoProvider;
    this.wifiReadingDaoProvider = wifiReadingDaoProvider;
    this.supabaseRoomDataSourceProvider = supabaseRoomDataSourceProvider;
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  @Override
  public RoomRepository get() {
    return newInstance(roomDaoProvider.get(), wifiReadingDaoProvider.get(), supabaseRoomDataSourceProvider.get(), userPreferencesDataStoreProvider.get());
  }

  public static RoomRepository_Factory create(Provider<RoomDao> roomDaoProvider,
      Provider<WifiReadingDao> wifiReadingDaoProvider,
      Provider<SupabaseRoomDataSource> supabaseRoomDataSourceProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider) {
    return new RoomRepository_Factory(roomDaoProvider, wifiReadingDaoProvider, supabaseRoomDataSourceProvider, userPreferencesDataStoreProvider);
  }

  public static RoomRepository newInstance(RoomDao roomDao, WifiReadingDao wifiReadingDao,
      SupabaseRoomDataSource supabaseRoomDataSource,
      UserPreferencesDataStore userPreferencesDataStore) {
    return new RoomRepository(roomDao, wifiReadingDao, supabaseRoomDataSource, userPreferencesDataStore);
  }
}
