package com.qetch.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qetch.springmvc.domain.Product;
import com.qetch.springmvc.form.ProductForm;
import com.qetch.springmvc.service.ProductService;

@Controller
public class ProductController {

	private static Log log = LogFactory.getLog(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/product_input")
	public String inputProduct() {
		log.info("inputProduct called");
		return "ProductForm";
	}
	
	@RequestMapping(value = "/product_save", method = RequestMethod.POST)
	public String saveProduct(ProductForm productForm, RedirectAttributes redirectAttributes) {
		log.info("saveProduct called");
		
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		product.setPrice(Float.parseFloat(productForm.getPrice()));
		
		Product savedProduct = productService.add(product);
		
		redirectAttributes.addFlashAttribute("message", "The product was successfully added.");
		
		return "redirect:/product_view/" + savedProduct.getId();
	}
	
	@RequestMapping(value = "/product_view/{id}")
	public String viewProduct(@PathVariable Long id, Model model) {
		Product product = productService.get(id);
		model.addAttribute("product", product);
		return "ProductDetail";
	}
}
