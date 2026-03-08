package com.example.signalstrength.data.repository;

import com.example.signalstrength.data.local.RoomDao;
import com.example.signalstrength.data.local.WifiReadingDao;
import com.example.signalstrength.data.remote.supabase.SupabaseDataSource;
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
public final class WifiRepository_Factory implements Factory<WifiRepository> {
  private final Provider<WifiReadingDao> daoProvider;

  private final Provider<RoomDao> roomDaoProvider;

  private final Provider<SupabaseDataSource> supabaseDataSourceProvider;

  public WifiRepository_Factory(Provider<WifiReadingDao> daoProvider,
      Provider<RoomDao> roomDaoProvider, Provider<SupabaseDataSource> supabaseDataSourceProvider) {
    this.daoProvider = daoProvider;
    this.roomDaoProvider = roomDaoProvider;
    this.supabaseDataSourceProvider = supabaseDataSourceProvider;
  }

  @Override
  public WifiRepository get() {
    return newInstance(daoProvider.get(), roomDaoProvider.get(), supabaseDataSourceProvider.get());
  }

  public static WifiRepository_Factory create(Provider<WifiReadingDao> daoProvider,
      Provider<RoomDao> roomDaoProvider, Provider<SupabaseDataSource> supabaseDataSourceProvider) {
    return new WifiRepository_Factory(daoProvider, roomDaoProvider, supabaseDataSourceProvider);
  }

  public static WifiRepository newInstance(WifiReadingDao dao, RoomDao roomDao,
      SupabaseDataSource supabaseDataSource) {
    return new WifiRepository(dao, roomDao, supabaseDataSource);
  }
}
