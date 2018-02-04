package com.example.simon.dycard.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.simon.dycard.R;
import com.example.simon.dycard.model.Commande;
import com.example.simon.dycard.util.MySingleton;

import java.util.ArrayList;

public class Images_Activity extends AppCompatActivity implements OnClickListener {
    ListView list_view;
    ArrayList<String> list = new ArrayList<String>();
    //ArrayAdapter<String> adapter;
    Button mButton1;
    Button mButton2;
    Button mButton3;

    private ImageActivityAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Integer> formesArrayList;

    private Commande commande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_recycler_view);
        /*mButton1 =  findViewById(R.id.button1);
        mButton1.setOnClickListener(this);*/
        commande = MySingleton.getInstance(getApplicationContext()).getCommande();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        formesArrayList = new ArrayList<Integer>();
        formesArrayList.add(R.drawable.chat_v2);
        formesArrayList.add(R.drawable.coeur);
        formesArrayList.add(R.drawable.diamant);
        formesArrayList.add(R.drawable.etoile);

        adapter = new ImageActivityAdapter(this, formesArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new ImageActivityAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new ImageActivityAdapter.ClickListener() {

            Intent intent;

            @Override
            public void onClick(View view, int position) {

                for (int i=0; i<recyclerView.getChildCount();i++)
                {
                    recyclerView.getChildAt(i).setBackgroundColor(Color.WHITE);
                }

                Toast.makeText(Images_Activity.this, ""+position, Toast.LENGTH_SHORT).show();
                commande.setFormes(formesArrayList.get(position));
                view.setBackgroundColor(Color.BLUE);
            }
          /*  public void onLongPress(MotionEvent e) {

                View view =
                        recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (actionMode  != null) {
                    return;
                }
                actionMode =
                        startActionMode(ImageActivityAdapter.this);
                int idx = recyclerView.getChildPosition(view);
                myToggleSelection(idx);
                super.onLongPress(e);
            }

            private void myToggleSelection(int idx) {
                adapter.toggleSelection(idx);
                String title = getString(
                        R.string.selected_count,
                        adapter.getSelectedItemCount());
                actionMode.setTitle(title);
            }*/

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        // clear state
       // mButton1.setSelected(false);

        // change state
        button.setSelected(true);
        button.setPressed(false);
    }
    /*@SuppressWarnings("unchecked")
    private <T> T findViewByIdAndCast(int id) {
        return (T) findViewById(id);
    }*/


    public void diamant(View v){
        commande.setForme("diamant");
    }

    public void etoile(View v){
        commande.setForme("etoile");
    }

    public void coeur(View v){
        commande.setForme("coeur");
    }

    public void octo(View v){
        commande.setForme("octo");
    }

    public void cadreplantes(View v){
        commande.setForme("cadre_plantes");
    }

    public void chat(View v){
        commande.setForme("chat");
    }

    public void ovale(View v){
        commande.setForme("ovale");
    }

    public void imagePrecedent(View v) {
        startActivity(new Intent(Images_Activity.this, Etape1_Activity.class));
        finish();
    }

    public void imageSuivant(View v) {
        //donner la commande
        Intent intent = new Intent(Images_Activity.this, Choose_photo_activity.class);
        MySingleton.getInstance(getApplicationContext()).setCommande(commande);
        startActivity(intent);
        finish();
    }
}
