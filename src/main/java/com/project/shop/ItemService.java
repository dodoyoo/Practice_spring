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

    public void editItem(Long id, String title, Integer price) {
        // 유효성 검사
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("상품명은 비어 있을 수 없습니다.");
        }

        if (title.length() > 100) {
            throw new IllegalArgumentException("상품명이 너무 깁니다. 100자 이하로 입력해주세요.");
        }

        if (price == null || price < 0) {
            throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        }

        if (price > 10_000_000) {
            throw new IllegalArgumentException("가격이 너무 큽니다. 1천만 원 이하로 입력해주세요.");
        }

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 아이템이 존재하지 않습니다."));

        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}
