package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journal")

public class JournalController {

    @Autowired
    private JournalService journalService;


    @PostMapping("/add")
    public boolean  addList(@RequestBody JournalEntry myjournal){
        journalService.saveEntry(myjournal);
        return  true;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<JournalEntry> getListByIdt(@PathVariable ObjectId id){

        return journalService.findEntryById(id);
    }
    @PutMapping("/update/{id}")
    public JournalEntry  updateById(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){

        return journalService.updateEntryById(id,journalEntry);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean  deleteById(@PathVariable ObjectId id){

        return journalService.deleteEntryById(id);
    }
    @GetMapping("/get")
    public boolean getListById(@RequestParam String id){
        return false;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JournalEntry>> getAllList(){
        return journalService.getAllEntry();
    }
}
