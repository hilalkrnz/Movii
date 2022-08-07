package com.info.movii.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.info.movii.MainTitles;
import com.info.movii.R;
import com.info.movii.Watched;
import com.info.movii.adapter.WatchedAdapter;

import java.util.ArrayList;
import java.util.Map;

public class WatchedActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firebaseFirestore ;
    private DocumentReference documentReference;
    private MainTitles mainTitles;

    private RecyclerView watchedRv ;
    public ArrayList<WatchedAdapter> watchedArrayList;

    ArrayList<String> userEmailFromFB;
    ArrayList<String> watchedImageFromFB;
    ArrayList<String> watchedNameFromFB;
    ArrayList<String> watchedProducerFromFB;
    ArrayList<String> watchedCategoryFromFB;
    ArrayList<String> watchedTypeFromFB;

    WatchedAdapter watchedAdapter;
    private String watchedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_color));
        actionBar.setTitle("Movii");


        userEmailFromFB = new ArrayList<>();
        watchedImageFromFB = new ArrayList<>();
        watchedNameFromFB = new ArrayList<>();
        watchedProducerFromFB = new ArrayList<>();
        watchedCategoryFromFB = new ArrayList<>();
        watchedTypeFromFB = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFromFirestore();
       // getDataFromFirestore2();
       // getDataFromFirestore3();

        watchedRv = findViewById(R.id.watchedRv);

        watchedRv.setHasFixedSize(true);
        watchedRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        watchedArrayList = new ArrayList<WatchedAdapter>();

        watchedAdapter = new WatchedAdapter(this,userEmailFromFB,watchedImageFromFB
                , watchedNameFromFB, watchedProducerFromFB, watchedCategoryFromFB, watchedTypeFromFB);
        watchedRv.setAdapter(watchedAdapter);

        watchedArrayList.add(watchedAdapter);


    }



    //Veri Okuma

    public void getDataFromFirestore(){

        CollectionReference collectionReference = firebaseFirestore.collection("deneme");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null){
                    Toast.makeText(WatchedActivity.this, error.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                }

                if (value != null){

                    for (DocumentSnapshot snapshot : value.getDocuments()){

                        Map<String, Object> data = snapshot.getData();

                        String userEmail = (String) data.get("userEmail");
                        String downloadUrl = (String) data.get("downloadurl");
                        String watchedName = (String) data.get("watchedName");
                        String watchedProducer = (String) data.get("watchedProducer");
                        String watchedCategory = (String) data.get("watchedCategory");
                        String watchedType = (String) data.get("watchedType");

                        userEmailFromFB.add(userEmail);
                        watchedImageFromFB.add(downloadUrl);
                        watchedNameFromFB.add(watchedName);
                        watchedProducerFromFB.add(watchedProducer);
                        watchedCategoryFromFB.add(watchedCategory);
                        watchedTypeFromFB.add(watchedType);
                    }
                }

            }
        });
    }


}