package com.info.movii.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.info.movii.MainTitles;
import com.info.movii.R;
import com.info.movii.activity.WatchedActivity;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CardDesignHolder>{

    private Context mContext ;
    private List<MainTitles> mainTitlesList;

    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firebaseFirestore ;

    public MainAdapter(Context mContext, List<MainTitles> mainTitlesList) {
        this.mContext = mContext;
        this.mainTitlesList = mainTitlesList;
    }

    @NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_card_design, parent,false);
        return new CardDesignHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHolder holder, int position) {

        MainTitles mainTitle = mainTitlesList.get(position);

        holder.textViewMainTitle.setText(mainTitle.getMainTitleName());

        holder.mainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                if(mainTitle.getMainTitleId() == 1){
                    Intent intent = new Intent(mContext, WatchedActivity.class);

                    CollectionReference collectionReference = firebaseFirestore.collection("deneme");
                    collectionReference.whereEqualTo("watchedType","Filmler").get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG,document.getId() + " => " + document.getData());

                                        }
                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                    }
                                }
                            });
                    mContext.startActivity(intent);

                }else if (mainTitle.getMainTitleId() == 2){
                    Intent intent = new Intent(mContext, WatchedActivity.class);

                    CollectionReference collectionReference = firebaseFirestore.collection("deneme");
                    collectionReference.whereEqualTo("watchedType","Diziler").get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d(TAG,document.getId() + " => " + document.getData());

                                        }
                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                    }
                                }
                            });
                    mContext.startActivity(intent);

                }else if (mainTitle.getMainTitleId() == 3){
                    Intent intent = new Intent(mContext, WatchedActivity.class);

                    CollectionReference collectionReference = firebaseFirestore.collection("deneme");
                    collectionReference.whereEqualTo("watchedType","Belgeseller").get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            collectionReference.whereEqualTo("watchedType","Diziler").get();
                                            Log.d(TAG,document.getId() + " => " + document.getData());
                                        }
                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                    }
                                }
                            });
                    mContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mainTitlesList.size();
    }


    public class CardDesignHolder extends RecyclerView.ViewHolder{
        private CardView mainCard;
        private TextView textViewMainTitle;



        public CardDesignHolder(@NonNull View itemView) {
            super(itemView);

            mainCard = itemView.findViewById(R.id.mainCard);
            textViewMainTitle = itemView.findViewById(R.id.textViewMainTitle);
        }
    }
}

