package com.norfsoftware.dofocus.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.norfsoftware.dofocus.Model.Notes;
import com.norfsoftware.dofocus.R;
import com.norfsoftware.dofocus.ViewModel.NotesViewModel;
import com.norfsoftware.dofocus.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotesActivity extends AppCompatActivity {

    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    NotesViewModel notesViewModel;
    String priority ="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.notekle);

        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        //önem derecesi renkleri//
        binding.greenPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority ="1";

        });

        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            priority ="2";
        });


        binding.redPriority.setOnClickListener(v -> {

            binding.greenPriority.setImageResource(0 );
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            priority ="3";
        });


        //note onnay metin string tipi süreci
        binding.doneNotesBtn.setOnClickListener(v -> {
            title= binding.notesTitle.getText().toString();
            subtitle= binding.notesSubtitle.getText().toString();
            notes= binding.notesData.getText().toString();

            CreateNotes(title,subtitle,notes);

        });
    }
    //yazılan textlerin dataya gitmesi
    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("dd/MM/yy",date.getTime());

        Notes notes1 = new Notes();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtitle;
        notes1.notes = notes;
        notes1.notesPriority=priority;
        notes1.notesDate=sequence.toString();

        notesViewModel.insertNotes(notes1);


        Toast.makeText(this, "kayıt tamam", Toast.LENGTH_SHORT).show();
        finish();

    }
}