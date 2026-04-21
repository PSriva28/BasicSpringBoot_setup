package com.journal.journal_app.controllers;

import com.journal.journal_app.entity.JournalEntity;
import com.journal.journal_app.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController //--> Equivalent to express.Router() --> handling all http requests
@RequestMapping("/journal")
//@ResponseEntity //--->For Handling http codes and resp.
public class JournalEntryController {

    @Autowired //---> usage for dependency Injection mtlb hme objects create karne ki jarurt nhi, sb hme lake de
    private JournalEntryServices service;

    @GetMapping //---> router.get() in mern
    public List<JournalEntity> getAllEntries(){
        return service.getAllData();//getAllData method is coming from the logic written in services
    }

    @PostMapping //---> router.post() & @RequestBody --> req.body
    public JournalEntity createEntry(@RequestBody JournalEntity entry){
        return service.postData(entry);
    }

    @GetMapping("/{id}") //---> router.get() through specific id, && @PathVariable --> req.params.id
    public JournalEntity getEntry(@PathVariable ObjectId id) {
        return service.getData(id).orElse(null);
    }


    @PutMapping("/{id}") //---> router.put() updating any existing entry
    public JournalEntity updateEntries(@PathVariable ObjectId id, @RequestBody JournalEntity newEntry){
        return service.updateData(id, newEntry);
    }

    @DeleteMapping("/{id}") //---> router.delete(), for deleting entry
    public boolean deleteEntry(@PathVariable ObjectId id){
        return service.deleteData(id);
    }
}
