package com.example.bikeservice.backend.service;

import com.example.bikeservice.backend.entity.Job;
import com.example.bikeservice.backend.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class JobService implements CrudListener<Job> {
    private final JobRepository repository;

    @Override
    public Collection<Job> findAll() {
        return repository.findAll();
    }

    @Override
    public Job add(Job job) {
        return repository.save(job);
    }

    @Override
    public Job update(Job job) {
        return repository.save(job);
    }

    @Override
    public void delete(Job job) {
        repository.delete(job);
    }
}
