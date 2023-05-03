package project.modeling.modeling.controllers;

import org.springframework.web.bind.annotation.*;
import project.modeling.modeling.services.PackService;

@RestController
@RequestMapping("/package/create")
public class PackageController {
    private PackService packService;

    public PackageController(PackService packService) {
        this.packService = packService;
    }
    @PostMapping
    public void savePack(@RequestParam("name") String name){
       this.packService.savePack(name);
    }
}
