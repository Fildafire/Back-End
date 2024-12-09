package com.pereira.Services;

import com.pereira.Entities.Data;
import com.pereira.Repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    public List<Data> getAllData() {
        return dataRepository.findAll();
    }

    public Optional<Data> getDataById(Long id) {
        return dataRepository.findById(id);
    }

    public Data saveData(Data data) {
        return dataRepository.save(data);
    }

    public void deleteData(Long id) {
        dataRepository.deleteById(id);
    }
}
