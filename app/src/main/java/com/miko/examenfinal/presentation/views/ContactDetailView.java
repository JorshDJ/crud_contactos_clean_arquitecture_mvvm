package com.miko.examenfinal.presentation.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.miko.examenfinal.R;
import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.presentation.routes.AppNavigator;
import com.miko.examenfinal.presentation.view_models.ContactDetailViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactDetailView extends AppCompatActivity {
    private ContactDetailViewModel viewModel;
    private AppNavigator navigator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_detail);  // Asume que existe un layout llamado activity_contact_detail
        setContentView(R.layout.app_bar_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigator = new AppNavigator(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewModel = new ViewModelProvider(this).get(ContactDetailViewModel.class); // Obtiene la instancia del ViewModel

        // Asume que tienes un TextView para el nombre del contacto en tu layout
        TextView contactName = findViewById(R.id.contact_name);
        TextView contactEspecialidad = findViewById(R.id.contact_especialidad);
        TextView contactNumeroTelefono = findViewById(R.id.contact_numeroTelefono);
        TextView contactBiografia = findViewById(R.id.contact_biografia);
        ImageView contactImageUrl = findViewById(R.id.contact_imageUrl);


        // Carga el contacto
        String id = getIntent().getStringExtra("contact_id");
        viewModel.loadContact(id);


        // Observa los cambios en los datos y actualiza la UI
        viewModel.getContact().observe(this, contacto -> {
            contactName.setText(contacto.getNombre());
            contactEspecialidad.setText(contacto.getEspecialidad());
            contactNumeroTelefono.setText(contacto.getNumeroTelefono());
            contactBiografia.setText(contacto.getBiografia());
            // Suponiendo que 'contacto.getImageUrl()' te devuelve una URL de imagen
            // Puedes usar bibliotecas como Glide o Picasso para cargar la imagen a partir de una URL
            // Glide.with(this).load(contacto.getImageUrl()).into(contactImageUrl);
            Glide.with(this)
                    .load(contacto.getImageUrl())
                    .into(contactImageUrl);

        });
    }
    //Debemos de crear la carpeta menu, y ahí colocar nuestro menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }
    //Segunda manera para hacer que el app bar aparezca
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == android.R.id.home) {
            // Implementar la acción cuando se haga clic en el botón de navegación hacia atrás
            finish();
            System.out.println("Estoy presionado");
            //navigator.navigateToContactList();
            return true;
        } else if (id == R.id.edit_icon) {
            // Implementar la acción de edición aquí
            System.out.println("editame");
            String id2 = getIntent().getStringExtra("contact_id");
            Intent intent = new Intent(this, ContactFormView.class);  // asume que ContactFormView es el nombre de tu actividad de edición
            intent.putExtra("contact_id", id2);
            startActivity(intent);
            return true;
        } else if (id == R.id.delete_icon) {
            String id2 = getIntent().getStringExtra("contact_id");
            viewModel.deleteContact(id2);  // Nuevo
            navigator.navigateToContactList();
            System.out.println("borrame");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
