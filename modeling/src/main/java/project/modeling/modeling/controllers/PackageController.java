package project.modeling.modeling.controllers;

import org.springframework.web.bind.annotation.*;
import project.modeling.modeling.models.Packages;
import project.modeling.modeling.services.PackService;

@RestController
@RequestMapping("/package/create")
public class PackageController {
    private PackService packService;

    public PackageController(PackService packService) {
        this.packService = packService;
    }
    @PostMapping
    public Packages savePack(@RequestParam("name") String name){
        Packages pack =  this.packService.savePack(name);
        return pack;
    }
}
