package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.entity.Product;
//import org.slf4j.LoggerFactory;
import net.javaguides.springboot.tutorial.service.FileService;
import net.javaguides.springboot.tutorial.service.FileServiceException;
import net.javaguides.springboot.tutorial.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

//import java.util.logging.Logger;

@RequestMapping("/add")
@Controller
public class AddProductController {

    private static Logger LOG = LoggerFactory.getLogger(AddProductController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ModelAndView add (){
        return getFormMAV(new Product());
    }

    @PostMapping
    public ModelAndView add(@RequestParam("file") MultipartFile file, @Valid Product product, BindingResult bindingResult){
        LOG.info("File received {}", file);
        LOG.info("Product received {}", product);

        if (bindingResult.hasErrors()){
            if (file.isEmpty()){
                bindingResult.addError(new FieldError("product", "imagePath", "Plik nie moze byc pusty!"));
            }
            return getFormMAV(product);
        } else {
            try {
                String uploadedFile = fileService.store(file);
                LOG.info("File received {}", uploadedFile);
                product.setImagePath(uploadedFile);
                //product.setTimestamp(LocalDateTime.now());
                productService.save(product);

                return new ModelAndView ("redirect:/");

            } catch (IOException | FileServiceException e) {
                e.printStackTrace();
                LOG.error("Error during file store", e);

                bindingResult.addError(new FieldError("product",
                        "imagePath",
                        "Problem z wysylka pliku"));

                return getFormMAV(product);
            }
        }

    }

    private ModelAndView getFormMAV (@Valid Product product){
        ModelAndView mav = new ModelAndView("product/add");
        mav.addObject("product", product);
        return mav;
    }
}
