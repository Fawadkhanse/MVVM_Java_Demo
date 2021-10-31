package com.fawadkhanse.mvvmdemo.viewmodels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fawadkhanse.mvvmdemo.models.NicePlace;
import com.fawadkhanse.mvvmdemo.repositoires.NicePlaceRepository;

import java.util.List;

import javax.xml.transform.Result;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<NicePlace>> mListMutableLiveData;
    private MutableLiveData<Boolean> mBooleanMutableLiveData = new MutableLiveData<> ();
    NicePlaceRepository mRepository;

    public void init() {
        if (mListMutableLiveData != null) {
            return;
        }
        mRepository = NicePlaceRepository.getInstance ();
        mListMutableLiveData = mRepository.getNicePlace ();
    }

    public LiveData<List<NicePlace>> getNicePlaces() {
        return mListMutableLiveData;
    }

    public void addNewValue(final NicePlace nicePlace) {
        mBooleanMutableLiveData.setValue ( true );
        new AsyncTask<Void, Void, Void> () {

            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute ( unused );
                List<NicePlace> currentPleace = mListMutableLiveData.getValue ();
                currentPleace.add (nicePlace );
                mListMutableLiveData.postValue ( currentPleace );
                mBooleanMutableLiveData.postValue ( false );
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep ( 2000 );
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
                return null;
            }
        }.execute ();

    }
    public LiveData<Boolean> getLoading(){
        return mBooleanMutableLiveData;

    }
}
