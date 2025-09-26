package com.restapi.booklists.service;

import com.restapi.booklists.dto.CategoryRequestDTO;
import com.restapi.booklists.dto.CategoryResponseDTO;
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

    public List<CategoryResponseDTO> getAllCategory(){
            List <CategoryResponseDTO>  response =  categoryRepository.findAll().stream().map(CategoryResponseDTO::toDTO).toList();
        return response;
    }

    public CategoryResponseDTO getCategoryById(Long id){
        CategoryEntity category =  categoryRepository.findById(id).orElse(null);
        return CategoryResponseDTO.toDTO(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO category){
            CategoryEntity entity = categoryRepository.save(CategoryRequestDTO.toEntity(category));
        return CategoryResponseDTO.toDTO(entity);
    }

    public CategoryResponseDTO updateCategory(Long id,CategoryRequestDTO request){
         CategoryEntity category = categoryRepository.findById(id).orElse(null);

         if(category != null){
             category.setCategory(request.getCategory());
             category.setDescription(request.getDescription());
             CategoryEntity entity = categoryRepository.save(category);
             return CategoryResponseDTO.toDTO(entity);
         }
         return  null;
    }

    public void deleteById(Long id){
         categoryRepository.deleteById(id);
    }
}
