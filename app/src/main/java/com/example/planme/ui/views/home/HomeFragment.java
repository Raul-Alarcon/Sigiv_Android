package com.example.planme.ui.views.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.planme.R;
import com.example.planme.databinding.FragmentHomeBinding;
import com.example.planme.ui.adapters.RVGroupsAdapter;
import com.example.planme.ui.models.GroupUI;
import com.example.planme.utils.ExceptionHelper;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    NavController navController;
    FormGroup addGroupForm = new FormGroup();
    ActionsGroup actionsGroup = new ActionsGroup();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        RVGroupsAdapter rvGroupsAdapter = new RVGroupsAdapter();
        rvGroupsAdapter.setOnClickListener((position, group) -> {
            Bundle args = new Bundle();
            args.putString("groupId", group.getId());
            this.navController.navigate(R.id.navigation_home_to_navigation_chat, args);
        });
        rvGroupsAdapter.setOnPressListener(this::handlerOnPress);
        binding.rvAllGroups.setAdapter(rvGroupsAdapter);
        setUpRvGroups(rvGroupsAdapter);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        if(homeViewModel != null){
            this.homeViewModel.getException().observe(getViewLifecycleOwner(), exception -> {
                if(exception.isActive()){
                    Toast.makeText(getContext(), exception.getContent(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        addGroupForm.setOnSaveClick( groupUI -> {
            GroupUI _groupUI = (GroupUI) groupUI;
            this.handlerSaveClick(_groupUI);
        });

        this.binding.btnFormGroup.setOnClickListener(__ ->
                addGroupForm.show(getParentFragmentManager(), "add_group"));

    }
    
    private void handlerOnPress(GroupUI groupUI){
        this.actionsGroup.setGroupSelected(groupUI);
        this.actionsGroup.show(getParentFragmentManager(), "actions_group");
    }
    private void handlerSaveClick(GroupUI groupUI){
        try {
            this.homeViewModel.addGroup(groupUI);
            addGroupForm.dismiss();
            addGroupForm.clearForm();
        } catch (Exception e){
            ExceptionHelper.log(e);
        }
    }

    private void setUpRvGroups(RVGroupsAdapter rvGroupsAdapter) {
        this.homeViewModel.getGroups().observe(getViewLifecycleOwner(),rvGroupsAdapter::setGroups);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}