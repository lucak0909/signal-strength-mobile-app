package com.example.signalstrength.ui.screens.dashboard;

import com.example.signalstrength.data.repository.AuthRepository;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<WifiRepository> wifiRepositoryProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<RoomRepository> roomRepositoryProvider;

  public DashboardViewModel_Factory(Provider<WifiRepository> wifiRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<RoomRepository> roomRepositoryProvider) {
    this.wifiRepositoryProvider = wifiRepositoryProvider;
    this.authRepositoryProvider = authRepositoryProvider;
    this.roomRepositoryProvider = roomRepositoryProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(wifiRepositoryProvider.get(), authRepositoryProvider.get(), roomRepositoryProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<WifiRepository> wifiRepositoryProvider,
      Provider<AuthRepository> authRepositoryProvider,
      Provider<RoomRepository> roomRepositoryProvider) {
    return new DashboardViewModel_Factory(wifiRepositoryProvider, authRepositoryProvider, roomRepositoryProvider);
  }

  public static DashboardViewModel newInstance(WifiRepository wifiRepository,
      AuthRepository authRepository, RoomRepository roomRepository) {
    return new DashboardViewModel(wifiRepository, authRepository, roomRepository);
  }
}
