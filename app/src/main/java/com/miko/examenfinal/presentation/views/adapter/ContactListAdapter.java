package com.miko.examenfinal.presentation.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miko.examenfinal.R;
import com.miko.examenfinal.domain.entities.Contacto;
import com.miko.examenfinal.presentation.routes.AppNavigator;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<Contacto> contactos;
    private AppNavigator navigator;

    //public ContactListAdapter(List<Contacto> contactos) {
    //    this.contactos = contactos;
    //}
    public ContactListAdapter(List<Contacto> contactos, AppNavigator navigator) {
        this.contactos = contactos;
        this.navigator = navigator;
    }

    /*
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view,navigator);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contacto contacto = contactos.get(position);
        //holder.contactName.setText(contacto.getNombre());
        holder.bind(contacto);


        // Aquí puedes actualizar los demás campos de tu ViewHolder
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public void updateContacts(List<Contacto> newContactos) {
        this.contactos = newContactos;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactName;

        ImageView contactImage;
        AppNavigator navigator;

        public ViewHolder(View view, AppNavigator navigator) {
            super(view);
            this.navigator = navigator;
            contactName = view.findViewById(R.id.contact_name);
            contactImage = view.findViewById(R.id.contact_image); // Inicializa el ImageView
        }

        public void bind(Contacto contacto) {
            contactName.setText(contacto.getNombre());

            // Carga la imagen a partir de la URL con Glide
            Glide.with(itemView.getContext())
                    .load(contacto.getImageUrl())
                    .into(contactImage);

            itemView.setOnClickListener(v -> navigator.navigateToContactDetail(contacto.getId()));
        }
    }
}
