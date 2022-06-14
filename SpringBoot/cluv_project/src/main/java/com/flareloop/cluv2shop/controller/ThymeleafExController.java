package com.flareloop.cluv2shop.controller;

import com.flareloop.cluv2shop.entity.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.flareloop.cluv2shop.dto.ItemDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {
    @GetMapping(value = "/ex1")
    public String thymeleafEx1(Model model) {
        model.addAttribute("data", "타임리프 예제입니다.");
        return "thymeleafEx/ex1";
    }

    @GetMapping(value = "/ex2")
    public String thymeleafEx2(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemNm("테스트상품");
        itemDto.setItemDetail("상품상세설명");
        itemDto.setPrice(1000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/ex2";
    }

    @GetMapping(value = "/ex3")
    public String thymeleafEx3(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setItemNm("테스트 상품" + i);
            itemDto.setPrice(10000 * i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/ex3";
    }

    @GetMapping(value = "/ex4")
    public String thymeleafEx4(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setItemNm("테스트 상품" + i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/ex4";
    }

    @GetMapping(value = "/ex5")
    public String thymeleafEx5(Model model) {
        return "thymeleafEx/ex5";
    }

    @GetMapping(value = "/ex6")
    public String thymeleafEx6(String param1, String param2, Model model) {
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleafEx/ex6";
    }

    @GetMapping(value = "/ex7")
    public String thymeleafEx7() {
        return "thymeleafEx/ex7";
    }
}


