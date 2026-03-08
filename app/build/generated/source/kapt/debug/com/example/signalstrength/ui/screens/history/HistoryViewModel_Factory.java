package com.example.signalstrength.ui.screens.history;

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
public final class HistoryViewModel_Factory implements Factory<HistoryViewModel> {
  private final Provider<WifiRepository> wifiRepositoryProvider;

  public HistoryViewModel_Factory(Provider<WifiRepository> wifiRepositoryProvider) {
    this.wifiRepositoryProvider = wifiRepositoryProvider;
  }

  @Override
  public HistoryViewModel get() {
    return newInstance(wifiRepositoryProvider.get());
  }

  public static HistoryViewModel_Factory create(Provider<WifiRepository> wifiRepositoryProvider) {
    return new HistoryViewModel_Factory(wifiRepositoryProvider);
  }

  public static HistoryViewModel newInstance(WifiRepository wifiRepository) {
    return new HistoryViewModel(wifiRepository);
  }
}
