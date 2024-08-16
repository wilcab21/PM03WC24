package firebase.app.entrevista.model;

import android.widget.EditText;

public class Persona {

    public String uid;
    public String descripcion;
    public EditText date;

    public Persona() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EditText getDate() {
        return date;
    }

    public void setDate(EditText date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
