package com.alonemusk.medicalapp.ui.Search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.alonemusk.medicalapp.ui.utils.Utils;

import java.util.List;

import okhttp3.internal.Util;

public class SearchViewModel extends AndroidViewModel {

    private LiveData<List<SearchMedicine>> currentName;
    private SearchRepository repository;
    public MutableLiveData<List<SearchMedicine>> search;
    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository=new SearchRepository(application);
        currentName=repository.getAllnote();
        search = new MutableLiveData<>();

    }


    public LiveData<List<SearchMedicine>> getCurrentName() {
        if (currentName == null) {
            currentName=repository.getAllnote();
        }
        return currentName;
    }
    public void Insert(SearchMedicine note){
        repository.insert(note);
    }

    public List<SearchMedicine> search(String str) {
        return repository.search(str);
    }
    public void Update(SearchMedicine note){
        repository.update(note);
    }
    public  void delete(SearchMedicine note){
        repository.delete(note);
    }
    public void deleteAll(){
        repository.deleteAll();
    }
    public LiveData<List<SearchMedicine>> getAllnote(){
        return currentName;
    }

    public void search(List<SearchMedicine> medicines) {
        search.postValue(medicines);
    }

    }

