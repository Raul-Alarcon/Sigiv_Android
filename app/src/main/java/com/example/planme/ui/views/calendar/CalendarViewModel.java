package com.example.planme.ui.views.calendar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.planme.data.repository.TaskRepository;
import com.example.planme.ui.models.FlightUI;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.ui.models.MessageException;
import com.example.planme.utils.ExceptionHelper;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CalendarViewModel extends ViewModel {
    private final TaskRepository taskRepository;
    private final MutableLiveData<List<FlightUI>> task;
    public final List<FlightUI> taskDataCache;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser userSession;

    CalendarViewModel(){
        this.taskRepository = new TaskRepository(db.getReference());
        this.userSession = auth.getCurrentUser();
        this.task = new MutableLiveData<>();
        this.taskDataCache = new ArrayList<>();

        this.initialize().thenAccept(unused -> {}).exceptionally(throwable -> {
            ExceptionHelper.log(new Exception(throwable.getMessage()));
            return null;
        });
    }
    private CompletableFuture<Void>  initialize(){
        CompletableFuture<Void> future = new CompletableFuture<>();
        try {
            this.taskRepository.getAllByUser(userSession.getUid(),flightUIS -> {
                this.taskDataCache.clear();
                this.taskDataCache.addAll(flightUIS);
                task.setValue(flightUIS);
            });
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        return future;
    }

    public LiveData<List<FlightUI>> getDataFlight(){
        return task;
    }

    public CompletableFuture<Boolean> AddDataTask(FlightUI task){
        return this.taskRepository.addTask(userSession.getUid(), task);
    }
    public  CompletableFuture<Boolean> DeleteTask(FlightUI task){
        return  this.taskRepository.Delete(userSession.getUid(), task.getId());
    }
    public  CompletableFuture<Boolean> UpdateTask(FlightUI task){
        return this.taskRepository.updaTask(userSession.getUid(), task);
    }
}