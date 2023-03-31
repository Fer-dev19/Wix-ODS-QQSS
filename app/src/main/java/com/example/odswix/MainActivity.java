package com.example.odswix;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;

import BusinessLogic.User;
import Persistence.UserDAO;


public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";

    private static final String USERNAME = "KogMaw";
    private static final String PASSWORD = "WIXAdmin1";
    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.texto);

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

    }
    //Metodo de prueba para transferir datos
    public void ejecutar_registrarse(View view) {
        Intent intent = new Intent(this, IniciarSesion.class);

        //TextView text = (TextView) findViewById(R.id.texto);
        //intent.putExtra("ejemplo", text.getText());

        startActivity(intent);
    }

    public void buttonConnectToMySQL(View view){
        try {
            UserDAO userPrueba = new UserDAO();
            userPrueba.guardar(new User(15, "paquito","paquito2@gmail.com", "pacoelmejor",100,5,5));

        }
        catch (Exception e){
            textView.setText(e.toString());
            System.out.print(e.toString());
        }
    }

    public void datosTrans(View vista){
        TextView texto = (TextView) findViewById(R.id.texto);
        texto.setText("");
    }
}