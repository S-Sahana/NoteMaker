package com.example.ssrivath.notemaker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allnotes;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allnotes = repository.getAllnotes();
    }

    public void insert(Note note){
        repository.Insert(note);
    }

    public void update(Note note){
        repository.Update(note);
    }
    public void delete(Note note){
        repository.Delete(note);
    }
    public void deleteAll(){
        repository.DeleteAllNotes();
    }
    public LiveData<List<Note>> getAllnotes(){
        return allnotes;
    }
}
