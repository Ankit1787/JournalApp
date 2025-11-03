package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.JournalRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public ResponseEntity <List<JournalEntry>> getAllEntry (){
        List<JournalEntry>journalEntries = journalRepository.findAll();
        System.out.println(journalEntries+"entery");
        if(!journalEntries.isEmpty()){
            return new ResponseEntity<List<JournalEntry>>(journalEntries,HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public void saveEntry(JournalEntry journalEntry){
       journalRepository.save(journalEntry);
    }

   public ResponseEntity<JournalEntry> findEntryById(ObjectId id){
        Optional<JournalEntry> journalEntry =journalRepository.findById(id);
       return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
   }
    public boolean deleteEntryById(ObjectId id){
        journalRepository.deleteById(id);
        return true;
    }
    public JournalEntry updateEntryById(ObjectId id,JournalEntry newEntry){
    JournalEntry old=journalRepository.findById(id).orElse(null);
        System.out.println(newEntry.getTitle().equals("")+"  "+newEntry.getTitle());
    if(old!=null){
        old.setTitle(newEntry.getTitle()!=null&& !newEntry.getTitle().isEmpty()?newEntry.getTitle():old.getTitle());
        old.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty()?newEntry.getContent(): old.getContent());

    }
    journalRepository.save(old);
    return old;
    }

}
