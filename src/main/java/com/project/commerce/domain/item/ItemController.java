package com.project.commerce.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String getItems() {
        List<Item> items = itemService.getItems();
        return items.toString();
    }

    @GetMapping("/{id}")
    public String getItemDetail(@PathVariable Long itemId) {
        itemService.getItem(itemId);
        return "ok";
    }

    @PostMapping("/new")
    public String createItem(@RequestBody Item item) {
        itemService.save(item);
        return "ok";
    }

    @GetMapping("/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemService.getItem(itemId);
        model.addAttribute("item", item);
        return "ok";
    }

    @PatchMapping("/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("item") @RequestBody Item item) {
        itemService.update(itemId, item);
        return "ok";
    }

    @DeleteMapping("/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        itemService.delete(itemId);
        return "ok";
    }
}
