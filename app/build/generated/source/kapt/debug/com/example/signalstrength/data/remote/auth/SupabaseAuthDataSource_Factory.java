package com.example.signalstrength.data.remote.auth;

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
public final class SupabaseAuthDataSource_Factory implements Factory<SupabaseAuthDataSource> {
  private final Provider<SupabaseClient> clientProvider;

  public SupabaseAuthDataSource_Factory(Provider<SupabaseClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public SupabaseAuthDataSource get() {
    return newInstance(clientProvider.get());
  }

  public static SupabaseAuthDataSource_Factory create(Provider<SupabaseClient> clientProvider) {
    return new SupabaseAuthDataSource_Factory(clientProvider);
  }

  public static SupabaseAuthDataSource newInstance(SupabaseClient client) {
    return new SupabaseAuthDataSource(client);
  }
}
