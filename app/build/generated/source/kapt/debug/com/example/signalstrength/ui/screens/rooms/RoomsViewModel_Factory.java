package com.example.signalstrength.ui.screens.rooms;

import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.repository.RoomRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class RoomsViewModel_Factory implements Factory<RoomsViewModel> {
  private final Provider<RoomRepository> roomRepositoryProvider;

  private final Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider;

  public RoomsViewModel_Factory(Provider<RoomRepository> roomRepositoryProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider) {
    this.roomRepositoryProvider = roomRepositoryProvider;
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
  }

  @Override
  public RoomsViewModel get() {
    return newInstance(roomRepositoryProvider.get(), userPreferencesDataStoreProvider.get());
  }

  public static RoomsViewModel_Factory create(Provider<RoomRepository> roomRepositoryProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider) {
    return new RoomsViewModel_Factory(roomRepositoryProvider, userPreferencesDataStoreProvider);
  }

  public static RoomsViewModel newInstance(RoomRepository roomRepository,
      UserPreferencesDataStore userPreferencesDataStore) {
    return new RoomsViewModel(roomRepository, userPreferencesDataStore);
  }
}
