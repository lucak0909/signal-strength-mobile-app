package com.example.signalstrength.service;

import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.repository.WifiRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class WifiCollectorService_MembersInjector implements MembersInjector<WifiCollectorService> {
  private final Provider<WifiRepository> wifiRepositoryProvider;

  private final Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider;

  public WifiCollectorService_MembersInjector(Provider<WifiRepository> wifiRepositoryProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider) {
    this.wifiRepositoryProvider = wifiRepositoryProvider;
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  public static MembersInjector<WifiCollectorService> create(
      Provider<WifiRepository> wifiRepositoryProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider) {
    return new WifiCollectorService_MembersInjector(wifiRepositoryProvider, userPreferencesDataStoreProvider);
  }

  @Override
  public void injectMembers(WifiCollectorService instance) {
    injectWifiRepository(instance, wifiRepositoryProvider.get());
    injectUserPreferencesDataStore(instance, userPreferencesDataStoreProvider.get());
  }

  @InjectedFieldSignature("com.example.signalstrength.service.WifiCollectorService.wifiRepository")
  public static void injectWifiRepository(WifiCollectorService instance,
      WifiRepository wifiRepository) {
    instance.wifiRepository = wifiRepository;
  }

  @InjectedFieldSignature("com.example.signalstrength.service.WifiCollectorService.userPreferencesDataStore")
  public static void injectUserPreferencesDataStore(WifiCollectorService instance,
      UserPreferencesDataStore userPreferencesDataStore) {
    instance.userPreferencesDataStore = userPreferencesDataStore;
  }
}
