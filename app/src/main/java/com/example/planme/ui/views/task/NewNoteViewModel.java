package com.example.planme.ui.views.task;

import androidx.lifecycle.ViewModel;

import com.example.planme.data.models.Note;
import com.example.planme.data.repository.NoteRepository;
import com.example.planme.ui.models.NoteUI;
import com.example.planme.utils.Mapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;

public class NewNoteViewModel extends ViewModel {
    final NoteRepository noteRepository;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userSession;

    public NewNoteViewModel(){
        this.noteRepository =  new NoteRepository(db.getReference());
        userSession = auth.getCurrentUser();
    }

    public CompletableFuture<Boolean> addNote(NoteUI noteUI){
        Note note = Mapper.noteUiToModel(noteUI);
        String userId = this.userSession.getUid();
        return this.noteRepository.addNote(userId, note);
    }
}