package com.norfsoftware.dofocus.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.norfsoftware.dofocus.Dao.NotesDao;
import com.norfsoftware.dofocus.Database.NotesDatabase;
import com.norfsoftware.dofocus.Model.Notes;

import java.util.List;

public class NotesRepository {


    public NotesDao notesDao;
public LiveData<List<Notes>> getAllNotes;

    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;



        public NotesRepository(Application application){
            NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
            notesDao = database.notesDao();
            getAllNotes = notesDao.getallNotes();
            hightolow = notesDao.highToLow();
            lowtohigh = notesDao.lowToHigh();

        }

   public      void insertNotes(Notes notes){
            notesDao.insertNotes(notes);
        }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void  updateNotes(Notes notes){
            notesDao.updateNotes(notes);
    }

}
