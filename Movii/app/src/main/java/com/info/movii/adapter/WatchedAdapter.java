package com.info.movii.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.info.movii.R;
import com.info.movii.activity.WatchedActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class WatchedAdapter extends RecyclerView.Adapter<WatchedAdapter.CardDesignHolder>{

    private Context mContext ;

    private FirebaseAuth firebaseAuth ;
    private FirebaseFirestore firebaseFirestore ;
    private StorageReference storageReference;

    private ArrayList<String> userEmailList;
    private ArrayList<String> watchedImageList;
    private ArrayList<String> watchedNameList;
    private ArrayList<String> watchedProducerList;
    private ArrayList<String> watchedCategoryList;
    private ArrayList<String> watchedTypeList;



    public WatchedAdapter(Context mContext, ArrayList<String> userEmailList, ArrayList<String> watchedImageList, ArrayList<String> watchedNameList, ArrayList<String> watchedProducerList, ArrayList<String> watchedCategoryList, ArrayList<String> watchedTypeList) {
        this.mContext = mContext;
        this.userEmailList = userEmailList;
        this.watchedImageList = watchedImageList;
        this.watchedNameList = watchedNameList;
        this.watchedProducerList = watchedProducerList;
        this.watchedCategoryList = watchedCategoryList;
        this.watchedTypeList = watchedTypeList;
    }

    @NonNull
    @Override
    public WatchedAdapter.CardDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.watched_card_design, parent,false);
        return new WatchedAdapter.CardDesignHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull WatchedAdapter.CardDesignHolder holder, int position) {


        Picasso.get().load(watchedImageList.get(position)).into(holder.imageWatched);
        holder.watchedName.setText(watchedNameList.get(position));
        holder.watchedProducer.setText(watchedProducerList.get(position));
        holder.watchedCategory.setText(watchedCategoryList.get(position));
        holder.imageViewPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu = new PopupMenu(mContext,holder.imageViewPoint);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.actionDelete:

                                Snackbar.make(holder.imageViewPoint,"Silmek istiyor musun ?", Snackbar.LENGTH_SHORT)
                                        .setAction("Evet", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

                                                firebaseFirestore.collection("deneme").document(watchedNameList.get(position))
                                                        .delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if (task.isSuccessful()){
                                                                    watchedNameList.remove(watchedNameList.get(position));
                                                                    notifyDataSetChanged();
                                                                    Toast.makeText(mContext,"Silindi !", Toast.LENGTH_SHORT).show();
                                                                }else{
                                                                    Toast.makeText(mContext,"Hata: " +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });


                                            }
                                        })
                                        .show();

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });


        holder.watchedCard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    /* Intent intent = new Intent(mContext, DetailActivity.class);
                    mContext.startActivity(intent); */


            }
        });

    }

    @Override
    public int getItemCount() {

        return userEmailList.size();
    }



    public class CardDesignHolder extends RecyclerView.ViewHolder{
        private CardView watchedCard;
        private ImageView imageWatched;
        private TextView watchedName;
        private TextView watchedProducer;
        private TextView watchedCategory;
        private ImageView imageViewPoint;



        public CardDesignHolder(@NonNull View itemView) {
            super(itemView);

            watchedCard = itemView.findViewById(R.id.watchedCard);
            imageWatched = itemView.findViewById(R.id.imageWatched);
            watchedName = itemView.findViewById(R.id.watchedName);
            watchedProducer = itemView.findViewById(R.id.watchedProducer);
            watchedCategory = itemView.findViewById(R.id.watchedCategory);
            imageViewPoint = itemView.findViewById(R.id.imageViewPoint);
        }
    }

}


