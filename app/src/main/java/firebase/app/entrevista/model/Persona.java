package firebase.app.entrevista.model;

import java.text.DateFormat;
import java.util.Date;

public class Persona {

    public String uid;
    public String descripcion;
    public DateFormat date;

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

    public DateFormat getDate() {
        return date;
    }

    public void setDate(DateFormat date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
