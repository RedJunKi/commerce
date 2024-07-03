package com.project.commerce.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        try {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "ok";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    @PostMapping
    public String joinUser(@RequestBody UserDto userDto) {
        userService.save(userDto);

        return "redirect:/";
    }

    @PatchMapping("/{id}")
    private String updateUser(@PathVariable Long id, @RequestBody UserDto userDto, Model model) {
        userService.update(id, userDto);

        model.addAttribute("user", userDto);
        return "ok";
    }

    @DeleteMapping("/{id}")
    private String deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return "redirect:/";
    }
}
