package com.alonemusk.medicalapp.ui.Search;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;

import com.alonemusk.medicalapp.ui.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class SearchRepository {
    private SearchDao noteDao;
    private LiveData<List<SearchMedicine>> allnote;

    public SearchRepository(Application application) {
        SearchDatabase database = SearchDatabase.getInstance(application);
        noteDao = database.searchDao();
        allnote=noteDao.getAllNote();
        // allnote=noteDao.getAllnodes();
    }
    public List<SearchMedicine> search(String str) {
        try {
            return new GetSearch(noteDao).execute(str).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<SearchMedicine>();
    }
    public void insert(SearchMedicine note) {
        new InsertNoteAsyncTask(noteDao).execute(note);

    }

    public void update(SearchMedicine note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);

    }

    public void delete(SearchMedicine note) {
        new DeleteNoteAsyncTask(noteDao).execute(note);

    }

    public void deleteAll() {
        new DeleteAllNoteAsyncTask(noteDao).execute();


    }

    public LiveData<List<SearchMedicine>> getAllnote() {

        return allnote;
    }

    public static class InsertNoteAsyncTask extends AsyncTask<SearchMedicine, Void, Void> {
        private SearchDao noteDao;

        private InsertNoteAsyncTask(SearchDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(SearchMedicine... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<SearchMedicine, Void, Void> {
        private SearchDao noteDao;

        private UpdateNoteAsyncTask(SearchDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(SearchMedicine... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTask extends AsyncTask<SearchMedicine, Void, Void> {
        private SearchDao noteDao;

        private DeleteNoteAsyncTask(SearchDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(SearchMedicine... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private SearchDao noteDao;

        private DeleteAllNoteAsyncTask(SearchDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
    public static class GetAllNoteAsyncTask extends AsyncTask<String, String, String> {
        private SearchDao noteDao;
        private GetAllNoteAsyncTask(SearchDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected String doInBackground(String... voids) {
            noteDao.getAllNote();
            return null;
        }
    }
    public static class GetSearch extends AsyncTask<String, Void, List<SearchMedicine>> {
        private SearchDao noteDao;
        private GetSearch(SearchDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected List<SearchMedicine> doInBackground(String... voids) {
           return noteDao.search(voids[0]+"%");

        }
    }
}
