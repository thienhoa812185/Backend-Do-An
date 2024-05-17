package com.example.clinic.service.impl;

import com.example.clinic.dto.SpecialityRequestDTO;
import com.example.clinic.entity.Speciality;
import com.example.clinic.repository.SpecialityRepository;
import com.example.clinic.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public List<Speciality> getAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Speciality addSpeciality(SpecialityRequestDTO specialityRequestDTO) {
        Speciality newSpeciality = Speciality.builder()
                .name(specialityRequestDTO.getName())
                .description(specialityRequestDTO.getDescription())
                .image(specialityRequestDTO.getImage()).build();

        return specialityRepository.save(newSpeciality);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality getSpecialityById(Integer id) {
        Optional<Speciality> speciality = specialityRepository.findById(id);
        return speciality.orElse(null);
    }

    @Override
    public Speciality getSpecialityByName(String name) {
        return specialityRepository.getSpecialityByName(name);
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepository.delete(speciality);
    }
}
