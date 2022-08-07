package com.info.movii.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.info.movii.adapter.MainAdapter;
import com.info.movii.MainTitles;
import com.info.movii.R;
import com.info.movii.userLogin.SignUpActivity;
import com.info.movii.adapter.WatchedAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firebaseFirestore ;

    private RecyclerView mainRv ;
    private FloatingActionButton fabAdd;
    public ArrayList<MainTitles> mainTitlesArrayList;
    private MainAdapter mainAdapter;

    ArrayList<String> userEmailFromFB;
    ArrayList<String> watchedImageFromFB;
    ArrayList<String> watchedNameFromFB;
    ArrayList<String> watchedProducerFromFB;
    ArrayList<String> watchedCategoryFromFB;
    ArrayList<String> watchedTypeFromFB;
    WatchedAdapter watchedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_color));

        userEmailFromFB = new ArrayList<>();
        watchedImageFromFB = new ArrayList<>();
        watchedNameFromFB = new ArrayList<>();
        watchedProducerFromFB = new ArrayList<>();
        watchedCategoryFromFB = new ArrayList<>();
        watchedTypeFromFB = new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        mainRv = findViewById(R.id.mainRv);
        mainRv.setHasFixedSize(true);
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        watchedAdapter = new WatchedAdapter(this,userEmailFromFB,watchedImageFromFB
                , watchedNameFromFB, watchedProducerFromFB, watchedCategoryFromFB, watchedTypeFromFB);
        mainRv.setAdapter(watchedAdapter);


        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAdd = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intentAdd);
            }
        });

        mainTitlesArrayList = new ArrayList<>();


        MainTitles mainTitle1 = new MainTitles(1, "Filmler");
        MainTitles mainTitle2 = new MainTitles(2,"Diziler");
        MainTitles mainTitle3 = new MainTitles(3,"Belgeseller");

        mainTitlesArrayList.add(mainTitle1);
        mainTitlesArrayList.add(mainTitle2);
        mainTitlesArrayList.add(mainTitle3);

        mainAdapter = new MainAdapter(this, mainTitlesArrayList);
        mainRv.setAdapter(mainAdapter);


    }


    //Arama ekranı
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_search){

        }else if (item.getItemId() == R.id.sign_out){

            firebaseAuth.signOut();

            Intent intentToSignUp = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intentToSignUp);

        }else if (item.getItemId() == R.id.update_user){
            alertDisagn();
        }

        return super.onOptionsItemSelected(item);
    }

    //Parola güncelleme
    public void alertDisagn(){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View disagn = layoutInflater.inflate(R.layout.alert_design,null);

        EditText inputEmail = disagn.findViewById(R.id.inputEmail);

        AlertDialog.Builder update = new AlertDialog.Builder(this);
        update.setTitle("Parolayı Değiştir");
        update.setView(disagn);
        update.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String email = inputEmail.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplication(), "Email adresinizi giriniz.",Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Şifrenizi yenilemek için email gönderdik!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Email gönderilemedi !", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        update.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        update.create().show();



    }



}