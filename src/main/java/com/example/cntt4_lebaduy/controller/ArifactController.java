package com.example.cntt4_lebaduy.controller;

import com.example.cntt4_lebaduy.model.Artifact;
import com.example.cntt4_lebaduy.service.IArtifactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/artifacts")
public class ArifactController {

    private static final int PAGE_SIZE = 5;

    private final IArtifactService artifactService;

    @Autowired
    public ArifactController(IArtifactService artifactService) {
        this.artifactService = artifactService;
    }

    @GetMapping
    public String listArtifacts(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("id").ascending());
        Page<Artifact> artifactPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            artifactPage = artifactService.searchArtifacts(keyword, pageable);
        } else {
            artifactPage = artifactService.getAllArtifacts(pageable);
        }

        model.addAttribute("artifactPage", artifactPage);
        model.addAttribute("artifacts", artifactPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", artifactPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "artifacts/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("artifact", new Artifact());
        model.addAttribute("formAction", "/artifacts/add");
        model.addAttribute("pageTitle", "Thêm Hiện Vật Mới");
        return "artifacts/form";
    }

    @PostMapping("/add")
    public String createArtifact(
            @Valid @ModelAttribute("artifact") Artifact artifact,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/artifacts/add");
            model.addAttribute("pageTitle", "Thêm Hiện Vật Mới");
            return "artifacts/form";
        }

        artifactService.save(artifact);
        redirectAttributes.addFlashAttribute("successMessage", "Thêm hiện vật thành công!");
        return "redirect:/artifacts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Artifact> artifactOpt = artifactService.findById(id);
        if (artifactOpt.isEmpty()) {
            return "redirect:/artifacts";
        }

        model.addAttribute("artifact", artifactOpt.get());
        model.addAttribute("formAction", "/artifacts/edit/" + id);
        model.addAttribute("pageTitle", "Cập Nhật Hiện Vật");
        return "artifacts/form";
    }

    @PostMapping("/edit/{id}")
    public String updateArtifact(
            @PathVariable Long id,
            @Valid @ModelAttribute("artifact") Artifact artifact,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/artifacts/edit/" + id);
            model.addAttribute("pageTitle", "Cập Nhật Hiện Vật");
            artifact.setId(id);
            return "artifacts/form";
        }

        artifact.setId(id);
        artifactService.save(artifact);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật hiện vật thành công!");
        return "redirect:/artifacts";
    }

    @PostMapping("/delete/{id}")
    public String deleteArtifact(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        artifactService.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa hiện vật thành công!");
        return "redirect:/artifacts";
    }
}
