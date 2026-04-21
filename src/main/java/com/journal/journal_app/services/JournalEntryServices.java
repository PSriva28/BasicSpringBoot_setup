package com.journal.journal_app.services;

import com.journal.journal_app.entity.JournalEntity;
import com.journal.journal_app.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepo repository;

    //1-Getting All data from the Repository(MongoDB)
    public List<JournalEntity> getAllData(){
        return repository.findAll();
    }

    //2-Posting any new entry in the Repository(MongoDB)
    public JournalEntity postData( JournalEntity entry){
        entry.setDate(LocalDateTime.now());
        return repository.save(entry);
    }

    //3- Get any specific entry from the repository(MongoDB)
    public Optional<JournalEntity> getData(ObjectId id){
        return repository.findById(id);
    }


    //4- Updating any specific entry after getting it through id from the repository(MongoDB)
    public JournalEntity updateData(ObjectId id, JournalEntity newEntry){
        JournalEntity existingCheck = repository.findById(id).orElseThrow(()-> new RuntimeException("Entry not found."));
        existingCheck.setContent(newEntry.getContent());
        existingCheck.setTitle(newEntry.getTitle());
        return repository.save(existingCheck);
    }

    //5- Deleting any specific entry after getting it through id from the repository(MongoDB)
    public boolean deleteData(@PathVariable ObjectId id){
        repository.deleteById(id);
        return true;
    }
}
