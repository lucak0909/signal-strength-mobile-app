package com.example.signalstrength.ui.screens.detail;

import androidx.lifecycle.SavedStateHandle;
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
public final class ReadingDetailViewModel_Factory implements Factory<ReadingDetailViewModel> {
  private final Provider<WifiRepository> wifiRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public ReadingDetailViewModel_Factory(Provider<WifiRepository> wifiRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.wifiRepositoryProvider = wifiRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ReadingDetailViewModel get() {
    return newInstance(wifiRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static ReadingDetailViewModel_Factory create(
      Provider<WifiRepository> wifiRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ReadingDetailViewModel_Factory(wifiRepositoryProvider, savedStateHandleProvider);
  }

  public static ReadingDetailViewModel newInstance(WifiRepository wifiRepository,
      SavedStateHandle savedStateHandle) {
    return new ReadingDetailViewModel(wifiRepository, savedStateHandle);
  }
}
