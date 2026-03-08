package com.example.signalstrength.ui.screens.roomdetail;

import androidx.lifecycle.SavedStateHandle;
import com.example.signalstrength.data.datastore.UserPreferencesDataStore;
import com.example.signalstrength.data.repository.RoomRepository;
import com.example.signalstrength.data.repository.WifiRepository;
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
public final class RoomDetailViewModel_Factory implements Factory<RoomDetailViewModel> {
  private final Provider<WifiRepository> wifiRepositoryProvider;

  private final Provider<RoomRepository> roomRepositoryProvider;

  private final Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public RoomDetailViewModel_Factory(Provider<WifiRepository> wifiRepositoryProvider,
      Provider<RoomRepository> roomRepositoryProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.wifiRepositoryProvider = wifiRepositoryProvider;
    this.roomRepositoryProvider = roomRepositoryProvider;
    this.userPreferencesDataStoreProvider = userPreferencesDataStoreProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public RoomDetailViewModel get() {
    return newInstance(wifiRepositoryProvider.get(), roomRepositoryProvider.get(), userPreferencesDataStoreProvider.get(), savedStateHandleProvider.get());
  }

  public static RoomDetailViewModel_Factory create(Provider<WifiRepository> wifiRepositoryProvider,
      Provider<RoomRepository> roomRepositoryProvider,
      Provider<UserPreferencesDataStore> userPreferencesDataStoreProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new RoomDetailViewModel_Factory(wifiRepositoryProvider, roomRepositoryProvider, userPreferencesDataStoreProvider, savedStateHandleProvider);
  }

  public static RoomDetailViewModel newInstance(WifiRepository wifiRepository,
      RoomRepository roomRepository, UserPreferencesDataStore userPreferencesDataStore,
      SavedStateHandle savedStateHandle) {
    return new RoomDetailViewModel(wifiRepository, roomRepository, userPreferencesDataStore, savedStateHandle);
  }
}
