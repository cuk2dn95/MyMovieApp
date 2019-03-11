package com.example.truongquockhanh.mymovieapp

import com.example.truongquockhanh.mymovieapp.module.AppModule
import com.example.truongquockhanh.mymovieapp.module.FragmentModule
import com.example.truongquockhanh.mymovieapp.module.SourceModule
import com.example.truongquockhanh.mymovieapp.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FragmentModule::class, SourceModule::class, AndroidInjectionModule::class, ViewModelModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(mainApplication: MainApplication): Builder

        fun build(): AppComponent
    }
}