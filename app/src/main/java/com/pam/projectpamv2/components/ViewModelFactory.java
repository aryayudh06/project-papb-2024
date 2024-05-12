package com.pam.projectpamv2.components;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.pam.projectpamv2.ui.PegawaiInsertUpdateViewModel;
import com.pam.projectpamv2.ui.PegawaiMainViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;
    private final Application mApplication;

    private ViewModelFactory(Application application) {
        mApplication = application;
    }
    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(application);
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if
        (modelClass.isAssignableFrom(PegawaiMainViewModel.class)) {
            return (T) new PegawaiMainViewModel(mApplication);
        } else if
        (modelClass.isAssignableFrom(PegawaiInsertUpdateViewModel.class))
        {
            return (T) new PegawaiInsertUpdateViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
