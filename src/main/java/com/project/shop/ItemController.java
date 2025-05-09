package com.project.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.AttributedString;
import java.util.Optional;

@Controller
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "list.html";
    }

    @GetMapping("/write")
    public String write() {
        return "write.html";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String title, @RequestParam Integer price, Model model
    ) {
        try {
            itemService.saveItem(title, price);
            return "redirect:/list";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error" ; // error.html에 예외 메시지 출력 처리도 가능
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        try {
            Item item = itemService.getItemById(id);
            model.addAttribute("data", item);
            return "detail.html";
        } catch (IllegalArgumentException e) {
            return "redirect:/list";
        }
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable Long id, Model model) {
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(Long id, String title, Integer price) {
       itemService.editItem(id, title, price);
        return "redirect:/list";
    }
}