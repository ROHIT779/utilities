import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mynotes.MyNotesUI;
import org.example.mynotes.models.MyNote;
import org.example.mynotes.MyNotesLogManager;
import org.example.mynotes.models.MyNotes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

    //public static MyNotesLogManager logManager = new MyNotesLogManager();

    public static void main(String[] args) {
        System.out.println("Welcome to MyNotes");
//        MyNote note1 = new MyNote("Heading 1","Note 1");
//        MyNote note2 = new MyNote("Heading 2","Note 2");
//        MyNote note3 = new MyNote("Heading Heading Heading","Notefdddvvvvdbbbsfdsfffdbbbbfbvvdvvdsfvfb");
//        List<MyNote> notes = new ArrayList<>();
//        notes.add(note1);
//        notes.add(note2);
//        notes.add(note3);
//        MyNotes myNotes = new MyNotes(notes);
        String directoryPath = "./mynotes";
        String path = "./mynotes/allnotes.json";
        File directory = new File(directoryPath);
        if(!directory.exists()){
            directory.mkdirs();
        }
        File notesFile = new File(path);
        if(!notesFile.exists()){
            try{
                MyNotes myNotes1 = new MyNotes();
                notesFile.createNewFile();
                FileWriter writer = new FileWriter(path);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.writeValue(writer, myNotes1);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        MyNotes myNotes = new MyNotes().fetchExistingNotes();
        MyNotesUI myNotesUI = new MyNotesUI(myNotes);
        myNotesUI.renderUI();
    }
}
