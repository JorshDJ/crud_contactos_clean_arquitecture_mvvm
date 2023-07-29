package com.miko.examenfinal.domain.entities;

public class Contacto {
    private String id;
    private String nombre;
    private String especialidad;
    private String numeroTelefono;
    private String biografia;
    private String imageUrl;

    // Constructor vacío es necesario para Firebase
    public Contacto() {}

    // Constructor con todos los campos para facilitar la creación de objetos Contacto
    public Contacto(String id, String nombre, String especialidad, String numeroTelefono, String biografia, String imageUrl) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.numeroTelefono = numeroTelefono;
        this.biografia = biografia;
        this.imageUrl = imageUrl;
    }

    // Getters y setters para cada campo

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}