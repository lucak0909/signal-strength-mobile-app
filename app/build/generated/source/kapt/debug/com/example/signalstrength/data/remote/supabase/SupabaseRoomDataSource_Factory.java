package com.example.signalstrength.data.remote.supabase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.github.jan.supabase.SupabaseClient;
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
public final class SupabaseRoomDataSource_Factory implements Factory<SupabaseRoomDataSource> {
  private final Provider<SupabaseClient> clientProvider;

  public SupabaseRoomDataSource_Factory(Provider<SupabaseClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public SupabaseRoomDataSource get() {
    return newInstance(clientProvider.get());
  }

  public static SupabaseRoomDataSource_Factory create(Provider<SupabaseClient> clientProvider) {
    return new SupabaseRoomDataSource_Factory(clientProvider);
  }

  public static SupabaseRoomDataSource newInstance(SupabaseClient client) {
    return new SupabaseRoomDataSource(client);
  }
}
