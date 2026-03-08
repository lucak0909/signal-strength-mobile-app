package com.example.signalstrength.di;

import com.example.signalstrength.data.local.AppDatabase;
import com.example.signalstrength.data.local.RoomDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AppModule_ProvideRoomDaoFactory implements Factory<RoomDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideRoomDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoomDao get() {
    return provideRoomDao(databaseProvider.get());
  }

  public static AppModule_ProvideRoomDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideRoomDaoFactory(databaseProvider);
  }

  public static RoomDao provideRoomDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideRoomDao(database));
  }
}
