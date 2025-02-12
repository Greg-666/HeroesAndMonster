package com.example.demoex.correctionexorecap.bll.service.impl;

import com.example.demoex.correctionexorecap.bll.exception.ressourceAlreadyExists.RessourceAlreadyExistsException;
import com.example.demoex.correctionexorecap.bll.exception.ressourceNotFound.RessourceNotFoundException;
import com.example.demoex.correctionexorecap.bll.service.TypeService;
import com.example.demoex.correctionexorecap.dal.repository.TypeRepository;
import com.example.demoex.correctionexorecap.domain.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public Long create(Type type) {
        if(typeRepository.existsByDescription(type.getDescription())) {
            throw new RessourceAlreadyExistsException("Un type avec cette description existe déjà: " + type.getDescription());
        }
        return typeRepository.save(type).getId();
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Type non trouvé avec l'id: " + id));
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }
    @Override
    public void update(Long id, Type type) {
        Type typeToUpdate = findById(id);

        // On check si la description existe déjà
        if(!typeToUpdate.getDescription().equals(type.getDescription())
                && typeRepository.existsByDescription(type.getDescription())) {
            throw new RessourceAlreadyExistsException("Un type avec cette description existe déjà: " + type.getDescription());
        }

        typeToUpdate.setDescription(type.getDescription());
        typeRepository.save(typeToUpdate);
    }

    @Override
    public void delete(Long id) {
        if(!typeRepository.existsById(id)) {
            throw new RessourceNotFoundException("Type non trouvé avec l'id: " + id);
        }
        typeRepository.deleteById(id);
    }
    @Override
    public Set<Type> findAllByIds(Set<Long> ids) {
        List<Type> types = typeRepository.findAllById(ids);
        // On check si on a bien tous les types
        if(types.size() != ids.size()) {
            throw new RessourceNotFoundException("Certains types demandés n'existent pas");
        }
        return new HashSet<>(types);
    }
}
