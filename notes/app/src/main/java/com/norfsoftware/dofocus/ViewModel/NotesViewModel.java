package com.norfsoftware.dofocus.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.norfsoftware.dofocus.Model.Notes;
import com.norfsoftware.dofocus.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

public NotesRepository repository;
public LiveData<List<Notes>> getAllNotes;


public LiveData<List<Notes>> hightolow;
public LiveData<List<Notes>> lowttohigh;


    public NotesViewModel(Application application) {
        super(application);

         repository = new NotesRepository(application);
         getAllNotes = repository.getAllNotes;


        hightolow = repository.hightolow;
        lowttohigh = repository.lowtohigh;


    }

    public  void  insertNotes(Notes notes){

        repository.insertNotes(notes);
    }

    public  void  delettNotes(int id){

        repository.deleteNotes(id);
    }

    public  void  updateNote(Notes notes){

        repository.updateNotes(notes);
    }
}
