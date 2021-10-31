package com.fawadkhanse.mvvmdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.fawadkhanse.mvvmdemo.adapter.PlaceAdapter;
import com.fawadkhanse.mvvmdemo.databinding.ActivityMainBinding;
import com.fawadkhanse.mvvmdemo.models.NicePlace;
import com.fawadkhanse.mvvmdemo.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mMainBinding;
    PlaceAdapter mPlaceAdapter;
    List<NicePlace> mNicePlaces;
    MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        mMainBinding = ActivityMainBinding.inflate ( getLayoutInflater () );
        setContentView ( mMainBinding.getRoot () );
        mMainBinding.recycler.setLayoutManager ( new LinearLayoutManager ( this ) );
        mMainActivityViewModel = new ViewModelProvider ( this ).get ( MainActivityViewModel.class );
        mMainActivityViewModel.init ();
        mMainActivityViewModel.getNicePlaces ().observe ( this, new Observer<List<NicePlace>> () {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<NicePlace> nicePlaces) {
                mPlaceAdapter.notifyDataSetChanged ();
            }
        } );
        mMainActivityViewModel.getLoading ().observe ( this, new Observer<Boolean> () {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showPregress ();
                } else {
                    hideProgress ();
                    mMainBinding.recycler.smoothScrollToPosition ( mMainActivityViewModel.getNicePlaces ().getValue ().size ()-1 );
                }
            }
        } );
        mPlaceAdapter = new PlaceAdapter ( this, mMainActivityViewModel.getNicePlaces ().getValue () );
        mMainBinding.recycler.setAdapter ( mPlaceAdapter );
        mMainBinding.fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mMainActivityViewModel.addNewValue (
                        new NicePlace ( "https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg", "Havasu Falls" ) );
            }
        } );

    }

    private void showPregress() {
        mMainBinding.progressBar.setVisibility ( View.VISIBLE );
    }

    private void hideProgress() {
        mMainBinding.progressBar.setVisibility ( View.GONE );
    }
}