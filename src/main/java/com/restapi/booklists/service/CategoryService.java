package com.restapi.booklists.service;

import com.restapi.booklists.entity.BookEntity;
import com.restapi.booklists.entity.CategoryEntity;
import com.restapi.booklists.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategory(){
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public CategoryEntity createCategory(CategoryEntity categoryEntity){
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity updateCategory(Long id,CategoryEntity categoryEntity){
         CategoryEntity category = categoryRepository.findById(id).orElse(null);

         if(category != null){
             category.setCategory(categoryEntity.getCategory());
             category.setDescription(categoryEntity.getCategory());
             return categoryRepository.save(category);
         }
         return  null;
    }

    public void deleteById(Long id){
         categoryRepository.deleteById(id);
    }
}
