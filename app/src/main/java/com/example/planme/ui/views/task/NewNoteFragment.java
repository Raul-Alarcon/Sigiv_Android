package com.example.planme.ui.views.task;

import androidx.core.view.MenuProvider;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.planme.R;
import com.example.planme.databinding.FragmentNewNoteBinding;
import com.example.planme.ui.models.NoteUI;
import com.example.planme.utils.DateFormatHelper;
import com.example.planme.utils.ExceptionHelper;

public class NewNoteFragment extends Fragment {
    FragmentNewNoteBinding binding;
    NewNoteViewModel newNoteViewModel;
    NavController navController;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.newNoteViewModel =
                new ViewModelProvider(this).get(NewNoteViewModel.class);

        this.binding = FragmentNewNoteBinding.inflate(inflater, container, false);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navController = Navigation.findNavController(view);

        if(this.binding != null){
            String date = DateFormatHelper.getCurrentDateTime();
            date = DateFormatHelper.format(date, "HH:mm a");
            String formattedText = getString(R.string.date_message, date);
            this.binding.date.setText(formattedText);
        }

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_new_note, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                boolean result = false;
                if (menuItem.getItemId() == R.id.action_save) {
                    handleSaveClick();
                    result = true;
                }
                return result;
            }
        }, getViewLifecycleOwner());
    }

    private void handleSaveClick(){
        String title = this.binding.title.getText().toString();
        String content = this.binding.content.getText().toString();
        NoteUI noteUI = new NoteUI("", "", content, title);

        try {
            this.newNoteViewModel.addNote(noteUI)
                    .thenAccept( isSave -> {
                        if(isSave) {
                            this.navController.popBackStack();
                        }
                        if(!isSave){
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }).exceptionally( throwable -> {
                        ExceptionHelper.log(new Exception(throwable.getMessage()));
                        return null;
                    });
        } catch (Exception e){
            ExceptionHelper.log(e);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }
}