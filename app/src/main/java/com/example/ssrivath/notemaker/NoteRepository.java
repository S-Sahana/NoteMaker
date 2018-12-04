package com.example.ssrivath.notemaker;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.os.AsyncTask;
import android.service.voice.VoiceInteractionService;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allnotes;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allnotes = noteDao.getAllNotes();
    }

    public void Insert(Note note){
             new InsertNoteAsynctask(noteDao).execute(note);
    }

    public void Update(Note note){
             new UpdateNoteAsynctask(noteDao).execute(note);
    }

    public  void Delete(Note note){
             new DeleteNoteAsynctask(noteDao).execute(note);
    }
    public void DeleteAllNotes(){
        new DeleteallNoteAsynctask(noteDao).execute();

    }
    public LiveData<List<Note>> getAllnotes(){
        return allnotes;
    }

    private static class InsertNoteAsynctask extends AsyncTask<Note,Void,Void>{
        private NoteDao notedao;

        private InsertNoteAsynctask(NoteDao notedao){
            this.notedao = notedao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            notedao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsynctask extends AsyncTask<Note,Void,Void>{
        private NoteDao notedao;

        private UpdateNoteAsynctask(NoteDao notedao){
            this.notedao = notedao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            notedao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsynctask extends AsyncTask<Note,Void,Void>{
        private NoteDao notedao;

        private DeleteNoteAsynctask(NoteDao notedao){
            this.notedao = notedao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            notedao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteallNoteAsynctask extends AsyncTask<Void,Void,Void>{
        private NoteDao notedao;

        private DeleteallNoteAsynctask(NoteDao notedao){
            this.notedao = notedao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notedao.deleteAllNotes();
            return null;
        }
    }
}
