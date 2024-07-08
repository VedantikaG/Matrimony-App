package com.yaksha.training.matrimony.service;

import com.yaksha.training.matrimony.entity.Matrimony;
import com.yaksha.training.matrimony.repository.MatrimonyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrimonyService {

    private final MatrimonyRepository matrimonyRepository;

    public MatrimonyService(MatrimonyRepository matrimonyRepository) {
        this.matrimonyRepository = matrimonyRepository;
    }

    public Page<Matrimony> getMatrimonys(Pageable pageable) {
        Page<Matrimony> matrimonys = matrimonyRepository.findAllMatrimony(pageable);
        return matrimonys;
    }

    public Matrimony saveMatrimony(Matrimony matrimony) {
        return matrimonyRepository.save(matrimony);
    }

    public Matrimony getMatrimony(Long id) {
        return matrimonyRepository.findById(id).get();
    }

    public boolean deleteMatrimony(Long id) {
        matrimonyRepository.deleteById(id);
        return true;
    }

    public Page<Matrimony> searchMatrimonys(String theSearchName, Pageable pageable) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return matrimonyRepository.findByNameOrReligionOrOccupation(theSearchName, pageable);
        } else {
            return matrimonyRepository.findAllMatrimony(pageable);
        }
    }

    public boolean updateMatrimonyMatchFound(Long id) {
        matrimonyRepository.updateMatchFound(id);
        return true;
    }
}
