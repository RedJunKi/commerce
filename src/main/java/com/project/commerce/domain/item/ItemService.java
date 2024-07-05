package com.project.commerce.domain.item;

import com.project.commerce.domain.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(Long itemId) {
        return ObjectUtil.getObject(itemRepository.findById(itemId));
    }

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void delete(Long itemId) {
        itemRepository.deleteById(itemId);
    }

    public void update(Long itemId, Item item) {
        Item findItem = ObjectUtil.getObject(itemRepository.findById(itemId));
        System.out.println(item.getName());
        findItem.setName(item.getName());
        findItem.setPrice(item.getPrice());
        findItem.setStock(item.getStock());
        findItem.setManufacturer(item.getManufacturer());

    }
}
