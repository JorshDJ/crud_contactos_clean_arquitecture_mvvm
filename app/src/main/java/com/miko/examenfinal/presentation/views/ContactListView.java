package com.miko.examenfinal.presentation.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.miko.examenfinal.R;
import com.miko.examenfinal.presentation.routes.AppNavigator;
import com.miko.examenfinal.presentation.view_models.ContactListViewModel;
import com.miko.examenfinal.presentation.views.adapter.ContactListAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactListView extends AppCompatActivity {

    private AppNavigator navigator;//navigator como un miembro de la clase
    private ContactListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);  // Asume que existe un layout llamado activity_contact_list

        navigator = new AppNavigator(this);  // Inicializa el navigator

        viewModel = new ViewModelProvider(this).get(ContactListViewModel.class);// Obtiene la instancia del ViewModel

        //  lista en tu layout contact_list
        RecyclerView contactList = findViewById(R.id.contact_list);

        // Configura un adaptador para la lista ()
        ContactListAdapter adapter = new ContactListAdapter(new ArrayList<>(),navigator);
        contactList.setAdapter(adapter);
        contactList.setLayoutManager(new LinearLayoutManager(this));

        // Observa los cambios en los datos y actualiza la UI
        viewModel.getContacts().observe(this, contacts -> {
            adapter.updateContacts(contacts);
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            //Intent intent = new Intent(ContactListView.this, ContactFormView.class);
            //startActivity(intent);
            navigator.navigateToContactForm();  //navigator para navegar a ContactFormView
        });
    }
}
