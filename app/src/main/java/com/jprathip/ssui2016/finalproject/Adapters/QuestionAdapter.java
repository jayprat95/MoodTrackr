package com.jprathip.ssui2016.finalproject.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jprathip.ssui2016.finalproject.Adapters.RealmRecyclerViewAdapter;
import com.jprathip.ssui2016.finalproject.Database.Question;
import com.jprathip.ssui2016.finalproject.R;
import com.jprathip.ssui2016.finalproject.RealmController;

import io.realm.Realm;

/**
 * Created by jayanthprathipati on 11/16/16.
 */

public class QuestionAdapter extends RealmRecyclerViewAdapter<Question> {


    final Context context;
    private Realm realm;
    private LayoutInflater inflater;

    public QuestionAdapter(Context context) {

        this.context = context;
    }

    // create new views (invoked by the layout manager)
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflate a new card view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_card, parent, false);
        return new CardViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        realm = RealmController.getInstance().getRealm();

        // get the article
        final Question question = getItem(position);
        // cast the generic view holder to our specific one
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        // set the title and the snippet
        holder.textTitle.setText(question.getMood() + "");
        holder.locationTitle.setText(question.getPersonInContact());


        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "this is my Toast message!!! =)",
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });




    }

    // return the size of your data set (invoked by the layout manager)
    public int getItemCount() {

        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }





    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        public TextView textTitle;
        public TextView locationTitle;


        public CardViewHolder(View itemView) {
            // standard view holder pattern with Butterknife view injection
            super(itemView);

            card = (CardView) itemView.findViewById(R.id.card_view);
            textTitle = (TextView) itemView.findViewById(R.id.task_title);
            locationTitle = (TextView) itemView.findViewById(R.id.location_title);
        }
    }
}
