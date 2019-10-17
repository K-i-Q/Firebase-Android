package com.br.firebaseandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        Contato contato = new Contato("Juarez","juarez@gmail.com");

        myRef.child("contatos").push().setValue(contato);

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                List<Contato> listaContatos = new ArrayList<>();
                String _nome;
                String _email;
                Contato _contato;

                for(DataSnapshot dtsn :dataSnapshot.getChildren()){
                    _nome = dtsn.child("name").getValue(String.class);
                    _email = dtsn.child("email").getValue(String.class);
                    _contato = new Contato(_nome,_email);
                    listaContatos.add(_contato);
                };
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
