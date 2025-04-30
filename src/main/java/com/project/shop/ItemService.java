package com.project.shop;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(String title, Integer price) {
        // 예외처리 1: 제목 길이
        if (title == null || title.length() > 50) {
            throw new IllegalArgumentException("상품명은 50자 이하만 가능합니다.");
        }

        // 예외처리 2: 가격 범위
        if (price == null || price < 0 || price > 100_000_000) {
            throw new IllegalArgumentException("가격은 0 이상 1억 이하만 가능합니다.");
        }

        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다."));
    }
}


//package com.project.shop;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class ItemService {
//
//    private final ItemRepository itemRepository;
//
//    public void saveItem(String title, Integer price) {
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
//        itemRepository.save(item);
//    }
//}
