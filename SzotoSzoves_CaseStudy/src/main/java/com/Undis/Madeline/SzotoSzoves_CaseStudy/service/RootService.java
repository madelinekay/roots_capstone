package com.Undis.Madeline.SzotoSzoves_CaseStudy.service;

import com.Undis.Madeline.SzotoSzoves_CaseStudy.model.Root;
import com.Undis.Madeline.SzotoSzoves_CaseStudy.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootService {
    private RootRepository rootRepository;

    @Autowired
    public RootService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }
    public Root getRootByName(String name) {
        Root root = rootRepository.findByName(name);
        System.out.println(root);
        return root;
    }
    public List<Root> getRootsInOrder(int wordId) {
        List<Root> roots = rootRepository.getRootsInOrder(wordId);
        System.out.println(roots);
        return roots;
    }


}
