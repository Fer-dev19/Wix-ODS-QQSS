package com.example.odswix;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.User;
import ClasesObserver.EstadisticasObserver;
import ClasesObserver.GestorEstadisticas;
import ClasesObserver.ObserverPerfil;


public class Perfil extends AppCompatActivity {
    User usuario = null;
    String contraseña = "";
    boolean visible = false;
    String popupUsername;
    String popupPassword;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GestorEstadisticas observer = new GestorEstadisticas();
        observer.registrarObserver(new ObserverPerfil(this));

        //Ocultar menu desplazable
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.perfil);

        Intent intent = getIntent();
        usuario = (User) intent.getSerializableExtra("user");

        setData(usuario);

        TextView password = findViewById(R.id.contraseñaUser);
        contraseña = password.getText().toString();
        password.setTransformationMethod(new PasswordTransformationMethod());
    }
    public void setData(User user){
        TextView username = findViewById(R.id.nombreUser);
        username.setText(user.getUsername());
        TextView email = findViewById(R.id.emailUser);
        email.setText(user.getEmail());
        TextView password = findViewById(R.id.contraseñaUser);
        password.setText(user.getPassword());
        TextView score = findViewById(R.id.puntosUser);
        score.setText(String.valueOf(user.getPuntosAcumTotales()));
        TextView tvNivel = findViewById(R.id.nivelUser);
        tvNivel.setText(String.valueOf(usuario.getNivel()));
    }

    public void obserSetDatos() {
        setData(usuario);
    }

    public void cambiarUsername(View view){
        TextView email = findViewById(R.id.emailUser);
        EditText input = new EditText(this);
        TextView username = findViewById(R.id.nombreUser);

        AlertDialog.Builder popout = new AlertDialog.Builder(this);
        AlertDialog.Builder popoutConfirm = new AlertDialog.Builder(this);

        popout.setTitle("Cambiar nombre de usuario");
        popoutConfirm.setTitle("Cambiar nombre de usuario");
        popout.setMessage("Ingrese el nuevo nombre de usuario:");
        popoutConfirm.setMessage("¿Está seguro de que quiere cambiar su nombre de usuario?");
        popout.setView(input);
        popout.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
        });
        popoutConfirm.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
        });

        popoutConfirm.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    PreparedStatement ps = SingletonSQL.insertar("UPDATE user SET username = ? WHERE email = ?");
                    ps.setString(1, popupUsername);
                    ps.setString(2, email.getText().toString());
                    ps.executeUpdate();
                    username.setText(popupUsername);
                    usuario.setUsername(popupUsername);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        popout.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String newUsername = input.getText().toString();
                    List<String> sql = new ArrayList<>();
                    ResultSet rs = SingletonSQL.consultar("SELECT username FROM user");

                    while(rs.next()) {
                        sql.add(rs.getString("username"));
                    }

                    boolean encontrado = false;
                    for (int i = 0; i < sql.size(); i++) {
                        if (newUsername.equals(sql.get(i))) {
                            encontrado = true;
                            break;
                        }
                    }

                    TextView err = findViewById(R.id.errUsername);
                    if(newUsername.isEmpty()){
                        err.setText("Debe de poner un nombre");
                        err.setVisibility(View.VISIBLE);
                    }
                    else if(encontrado){
                        err.setText("Ese nombre ya está registrado");
                        err.setVisibility(View.VISIBLE);
                    }
                    else{
                        err.setVisibility(View.INVISIBLE);
                        dialog.cancel();
                        popupUsername = newUsername;
                        AlertDialog dialogConfirm = popoutConfirm.create();
                        dialogConfirm.show();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        });


        AlertDialog dialog = popout.create();
        dialog.show();
    }
    public void cambiarPassword(View view){
        TextView email = findViewById(R.id.emailUser);
        EditText input = new EditText(this);
        TextView password = findViewById(R.id.contraseñaUser);

        AlertDialog.Builder popout = new AlertDialog.Builder(this);
        AlertDialog.Builder popoutConfirm = new AlertDialog.Builder(this);

        popout.setTitle("Cambiar contraseña");
        popoutConfirm.setTitle("Cambiar contraseña");
        popout.setMessage("Ingrese la nueva contraseña:");
        popoutConfirm.setMessage("¿Está seguro de que quiere cambiar su contraseña?");

        popout.setView(input);
        popout.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
        });
        popoutConfirm.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.cancel();}
        });

        popoutConfirm.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    String newPassword = input.getText().toString();
                    TextView err = findViewById(R.id.errPassword);
                    if(newPassword.isEmpty()){
                        err.setText("Debe de poner una contraseña");
                        err.setVisibility(View.VISIBLE);
                    }
                    else{
                        err.setVisibility(View.INVISIBLE);
                        PreparedStatement ps = SingletonSQL.insertar("UPDATE user SET password = ? WHERE email = ?");
                        ps.setString(1, newPassword);
                        ps.setString(2, email.getText().toString());
                        ps.executeUpdate();
                        password.setText(newPassword);
                        contraseña = password.getText().toString();
                        usuario.setPassword(newPassword);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        popout.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    String newPassword = input.getText().toString();
                    TextView err = findViewById(R.id.errPassword);
                    if(newPassword.isEmpty()) {
                        err.setText("Debe de poner una contraseña");
                        err.setVisibility(View.VISIBLE);
                    }
                    else{
                        err.setVisibility(View.INVISIBLE);
                        dialog.cancel();
                        popupPassword = newPassword;
                        AlertDialog dialogConfirm = popoutConfirm.create();
                        dialogConfirm.show();
                    }
            }
        });



        AlertDialog dialog = popout.create();
        dialog.show();
    }
    public void mostrarPassword(View view){
        TextView password = findViewById(R.id.contraseñaUser);
        if(visible) {
            password.setTransformationMethod(new PasswordTransformationMethod());
            visible = false;
        } else {
            password.setText(contraseña);
            password.setTransformationMethod(null);
            visible = true;
        }
    }
    public void volver (View view){
        Intent perfil = new Intent(this, JugarPartida.class);
        perfil.putExtra("user", usuario);
        finishAfterTransition();
        startActivity(perfil);
    }

    public void armario (View view){
        Intent intent = new Intent(this, Armario.class);
        intent.putExtra("user", usuario);
        startActivity(intent);
        this.finish();
    }

    public void buttonStats (View view){
        Intent intentStats = new Intent(this, Estadisticas.class);
        intentStats.putExtra("user", usuario);
        startActivity(intentStats);
        this.finish();
    }

    public void buttonRanking (View view){
        Intent intentRanking = new Intent(this, RankingUsuarios.class);
        intentRanking.putExtra("user", usuario);
        startActivity(intentRanking);
        this.finish();
    }

    @Override
    public void onBackPressed() {}
}
