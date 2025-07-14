package group.fooddelivery.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.ToppingDTO;
import group.fooddelivery.main.entity.Topping;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.ToppingMapper;
import group.fooddelivery.main.repository.ToppingRepository;

@Service
public class ToppingService {
    @Autowired
    private ToppingRepository toppingRepository;
    
    @Autowired
    private ToppingMapper toppingMapper;
    
    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public ToppingDTO getTopping(int toppingId) {
        Topping existingTopping = toppingRepository.findById(toppingId).orElseThrow(
            () -> new NotFoundException("Topping does not exist!")
        );
        return toppingMapper.toToppingDTO(existingTopping);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<ToppingDTO> getCategories() {
        List<Topping> allExistingCategories = toppingRepository.findAll();
        if (allExistingCategories.isEmpty()) {
            throw new NotFoundException("No toppings!");
        }
        return toppingMapper.toToppingDTOs(allExistingCategories);
    }

    @Transactional // ensure db safely in case of error
    public void createTopping(ToppingDTO toppingDTO) {
        Topping topping = toppingMapper.toTopping(toppingDTO);
        toppingRepository.save(topping);
    }

    @Transactional
    public void updateTopping(int toppingId, ToppingDTO toppingDTO) {
        Topping existingTopping = toppingRepository.findById(toppingId).orElseThrow(
            () -> new NotFoundException("Topping does not exist!")
        );
        existingTopping.setName(toppingDTO.getName());
        existingTopping.setPrice(toppingDTO.getPrice());

        toppingRepository.save(existingTopping);
    }

    @Transactional
    public void deleteTopping(int toppingId) {
        Topping existingTopping = toppingRepository.findById(toppingId).orElseThrow(
            () -> new NotFoundException("Topping does not exist!")
        );
        toppingRepository.delete(existingTopping);
    }
}
