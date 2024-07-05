package org.example.dajava.Service;

import org.example.dajava.Model.Xe;
import org.example.dajava.Repository.XeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XeService {
    @Autowired
    private XeRepository xeRepository;
    public List<Xe> findAll() {
        return xeRepository.findAll();
    }
}
