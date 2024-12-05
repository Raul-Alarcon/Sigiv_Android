package com.example.planme.ui.views.task;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.repository.NoteRepository;
import com.example.planme.ui.models.NoteUI;
import com.example.planme.utils.ExceptionHelper;
import com.example.planme.utils.Mapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TaskViewModel extends ViewModel {
    final NoteRepository noteRepository;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userSession;
    MutableLiveData<List<NoteUI>> noteList = new MutableLiveData<>();

    public TaskViewModel(){
        this.noteRepository =  new NoteRepository(db.getReference());
        userSession = auth.getCurrentUser();
        this.initialize()
                .thenAccept( unused -> {});
    }


    private CompletableFuture<Void> initialize(){
        CompletableFuture<Void> future = new CompletableFuture<>();
        if(userSession != null){
            noteRepository.getAllByUser(userSession.getUid(), notes -> {
                if (notes != null){
                    List<NoteUI> noteUIS = notes.stream()
                            .map(Mapper::noteToUi)
                            .collect(Collectors.toList());

                    this.noteList.setValue(noteUIS);
                }
            });
        }
        future.complete(null);
        return future;
    }

    public LiveData<List<NoteUI>> getNotesUI(){
        return this.noteList;
    }
}