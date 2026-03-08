package com.example.signalstrength.di;

import com.example.signalstrength.data.local.AppDatabase;
import com.example.signalstrength.data.local.WifiReadingDao;
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
public final class AppModule_ProvideWifiReadingDaoFactory implements Factory<WifiReadingDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideWifiReadingDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public WifiReadingDao get() {
    return provideWifiReadingDao(databaseProvider.get());
  }

  public static AppModule_ProvideWifiReadingDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideWifiReadingDaoFactory(databaseProvider);
  }

  public static WifiReadingDao provideWifiReadingDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideWifiReadingDao(database));
  }
}
