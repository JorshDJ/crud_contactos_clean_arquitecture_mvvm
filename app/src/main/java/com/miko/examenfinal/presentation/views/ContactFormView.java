package com.miko.examenfinal.presentation.views;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.miko.examenfinal.Manifest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.miko.examenfinal.R;
import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.presentation.routes.AppNavigator;
import com.miko.examenfinal.presentation.view_models.ContactFormViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactFormView extends AppCompatActivity {

    private ContactFormViewModel viewModel;
    private AppNavigator navigator;
    private Contacto contacto;

    private static final int GALLERY_REQUEST_CODE = 1;
    private Button selectImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        navigator = new AppNavigator(this);
        viewModel = new ViewModelProvider(this).get(ContactFormViewModel.class);

        EditText contactName = findViewById(R.id.input_name);
        EditText contactEspecialidad = findViewById(R.id.input_especialidad);
        EditText contactNumeroTelefono = findViewById(R.id.input_numeroTelefono);
        EditText contactBiografia = findViewById(R.id.input_biografia);
        selectImageButton = findViewById(R.id.select_image_button);

        FloatingActionButton fab = findViewById(R.id.fab);

        String contactId = getIntent().getStringExtra("contact_id");
        if (contactId != null) {
            viewModel.loadContact(contactId);
            viewModel.getContact().observe(this, contacto -> {
                this.contacto = contacto;
                contactName.setText(contacto.getNombre());
                contactEspecialidad.setText(contacto.getEspecialidad());
                contactNumeroTelefono.setText(contacto.getNumeroTelefono());
                contactBiografia.setText(contacto.getBiografia());
                selectImageButton.setTag(contacto.getImageUrl());
            });
        }

        selectImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        });

        fab.setOnClickListener(v -> {
            String name = contactName.getText().toString();
            String especialidad = contactEspecialidad.getText().toString();
            String numeroTelefono = contactNumeroTelefono.getText().toString();
            String biografia = contactBiografia.getText().toString();
            String imageUrl = (String) selectImageButton.getTag();

            if (contacto == null) {
                Contacto newContact = new Contacto();
                newContact.setNombre(name);
                newContact.setEspecialidad(especialidad);
                newContact.setNumeroTelefono(numeroTelefono);
                newContact.setBiografia(biografia);
                newContact.setImageUrl(imageUrl);

                viewModel.createContact(newContact);
            } else {
                contacto.setNombre(name);
                contacto.setEspecialidad(especialidad);
                contacto.setNumeroTelefono(numeroTelefono);
                contacto.setBiografia(biografia);
                contacto.setImageUrl(imageUrl);

                viewModel.updateContact(contacto);
            }
            navigator.navigateToContactList();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            uploadImageToFirebase(selectedImage);
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        // Primero, verifica si la uri de la imagen no es nula
        if (imageUri != null) {

            // Crear una instancia de Firebase Storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            // Crear una referencia a la ubicación en la que deseas subir la imagen
            // En este caso, la ubicación es "images/nombre_del_archivo.jpg"
            StorageReference storageReference = storage.getReference()
                    .child("images/" + System.currentTimeMillis() + ".jpg");

            // Inicia la subida de la imagen
            storageReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // La imagen se subió correctamente, ahora obtiene la URL de descarga
                        storageReference.getDownloadUrl()
                                .addOnSuccessListener(uri -> {
                                    // Ahora tienes el enlace de descarga de la imagen
                                    String imageUrl = uri.toString();
                                    selectImageButton.setTag(imageUrl);
                                })
                                .addOnFailureListener(e -> {
                                    // Ocurrió un error obteniendo el enlace de descarga
                                    Log.d("Firebase", "Error obteniendo URL de descarga.", e);
                                });
                    })
                    .addOnFailureListener(e -> {
                        // Ocurrió un error subiendo la imagen
                        Log.d("Firebase", "Error subiendo imagen a Firebase Storage.", e);
                    });
        }
    }
}
