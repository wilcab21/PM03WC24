package firebase.app.entrevista;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import firebase.app.entrevista.model.Persona;

public class MainActivity extends AppCompatActivity {

    private List<Persona> listPerson = new ArrayList<Persona>();
    ArrayAdapter<Persona> arrayAdapterPersona;

    EditText uid, desc, fec;
    ListView listV_personas;

FirebaseDatabase firebaseDatabase;
DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        uid = findViewById(R.id.txt_uidPersona);
        desc = findViewById(R.id.txt_descripcionPersona);
        fec = findViewById(R.id.txt_uidDate);

        listV_personas = findViewById(R.id.lv_datosPersonas);
        inicializarFirebase();
        listaDatos();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void listaDatos() {
        databaseReference.child("Persona").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Persona p = objSnaptshot.getValue(Persona.class);
                    listPerson.add(p);

                    arrayAdapterPersona = new ArrayAdapter<Persona>(MainActivity.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter((arrayAdapterPersona));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String id = uid.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:{
                if (id.equals("")){
                    validacion();
                    break;
                } else {
                    Persona p = new Persona();
                    p.setUid(id);
                    p.setDescripcion(String.valueOf(desc));
                    p.setDate(fec);
                    databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Agregar", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;}
            }
            case R.id.icon_save:{
                Toast.makeText(this,"Guardar", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_erase:{
                Toast.makeText(this,"Borrar", Toast.LENGTH_LONG).show();
                break;
            }
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void limpiarCajas() {
        uid.setText("");
        desc.setText("");
        fec.setText("");

    }

    private void validacion() {
        String id = uid.getText().toString();
        if (id.equals("")){
            uid.getError();
        }

    }
}