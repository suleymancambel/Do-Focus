package com.norfsoftware.dofocus;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.norfsoftware.dofocus.Activity.InsertNotesActivity;
import com.norfsoftware.dofocus.Adapter.NotesAdapter;
import com.norfsoftware.dofocus.Model.Notes;
import com.norfsoftware.dofocus.ViewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotesBtn;
    NotesViewModel notesViewModel;
NotesAdapter adapter;
    RecyclerView notesRecycler;
    TextView nofilter,hightolow,lowtohigh;
    List<Notes> filternoteslist;
//    SearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbarcenter);

//        SearchView searchView23 = (SearchView) findViewById(R.id.app_bar_search);
//
//        searchView23.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//                getSupportActionBar().setCustomView(R.layout.nul);
//            }
//        });



        newNotesBtn = findViewById(R.id.newNotesBtn);
        notesRecycler = findViewById(R.id.notesRecycler);

        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);
        nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        nofilter.setOnClickListener(v -> {
            loadData(0);
            hightolow.setBackgroundResource(R.drawable.filter_un_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
            nofilter.setBackgroundResource(R.drawable.filter_selected_shape);

        });

        hightolow.setOnClickListener(v -> {
            loadData(1);
            nofilter.setBackgroundResource(R.drawable.filter_un_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_un_shape);
            hightolow.setBackgroundResource(R.drawable.filter_selected_shape);

        });

        lowtohigh.setOnClickListener(v -> {
            loadData(2);
            hightolow.setBackgroundResource(R.drawable.filter_un_shape);
            nofilter.setBackgroundResource(R.drawable.filter_un_shape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected_shape);

        });

        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });


        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
                filternoteslist  = notes;
            }
        });

    }

    private void loadData(int i) {
        if (i ==0){
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternoteslist  = notes;
                }
            });

        } else if (i ==1){
            notesViewModel.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternoteslist  = notes;
                }
            });
        }else if (i == 2){
            notesViewModel.lowttohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternoteslist  = notes;
                }
            });
        }
    }

        public void onClick(View view){
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.non);

        }

    public void setAdapter(List<Notes> notes){
        notesRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new NotesAdapter(MainActivity.this,notes);
        notesRecycler.setAdapter(adapter);

    }




    public boolean onCreateOptionsMenu(Menu menu){


        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_notes,menu);

//        MenuItem.OnActionExpandListener onActionExpandListener= new MenuItem.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                Toast.makeText(MainActivity.this, "tıklandı", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        };
//        menu.findItem(R.id.app_bar_search).setOnActionExpandListener(onActionExpandListener);
//        SearchView searchView1 =(SearchView) menu.findItem(R.id.app_bar_search).getActionView();



        SearchView searchView =(SearchView)menuItem.getActionView();
        searchView.setQueryHint("Ara");


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {



            @Override
            public boolean onQueryTextSubmit(String query) {
                getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                getSupportActionBar().setCustomView(R.layout.non);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                getSupportActionBar().setCustomView(R.layout.non);
                NotesFilter(newText);
                return false;
            }
        });




        return super.onCreateOptionsMenu(menu);
    }

    private void NotesFilter(String newText) {


        ArrayList<Notes> FilterNames = new ArrayList<>();

        for (Notes notes:this.filternoteslist){

            if (notes.notesTitle.contains(newText) || notes.notesSubtitle.contains(newText) ){
                FilterNames.add(notes);

            }

        }

        this.adapter.searchNotes(FilterNames);


    }
}