package com.project.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();

        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(@ModelAttribute Item item) {
        itemRepository.save(item);
        /*
        1.
        String addPost(String title, Integer price) {
        Item item = new Item();
        item.title = "양말" / title;
        item.price = 5000 / price;
        itemRepository.save(item);
        }

        2.
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
         */
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        try {
            Optional<Item> result = itemRepository.findById(id);
            if (result.isPresent()) {
                model.addAttribute("data", result.get());
                return "detail"; // detail.html 템플릿 렌더링
            } else {
                return "redirect:/list"; // id가 없을 경우 리스트로 리디렉션
            }
        } catch (Exception e) {
            e.printStackTrace(); // 서버 로그에 출력 (또는 로깅 처리)
            return "error"; // error.html 같은 에러 템플릿을 만들어 두면 좋아요
        }
    }




//        Optional<Item> result = itemRepository.findById(id);
//        if (result.isPresent()) {
//            model.addAttribute("data", result.get());
//            return "detail.html";
//        } else {
//            return "redirect:/list";
//        }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handler() {
//        return ResponseEntity.status(400).body("error");
//    }
}