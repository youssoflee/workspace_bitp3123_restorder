package my.edu.utem.ftmk.dad.restorderapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import my.edu.utem.ftmk.dad.restorderapp.model.ProductType;


@Controller
public class ProductTypeMenuController {
	
	//without initialization for defaultURI, there will be whitelabel error for adding new product type
	//hence, initialize variable defaultURI with URI Link
	private String defaultURI = "http://localhost:8080/orderapp/api/producttypes";
	
	@GetMapping("/producttype/list")
	public String getOrderTypes(Model model) {
		
		//The URI for GET product types
		String uri = "http://localhost:8080/orderapp/api/producttypes";
		
		//Get a list order types from the web service
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ProductType[]> response = restTemplate.getForEntity(uri, ProductType[].class);
		
		//Parse JSON data to array of object
		ProductType productTypes[] = response.getBody();
		
		//Parse an array to a list object
		List<ProductType> productTypeList = Arrays.asList(productTypes);
		
		//Attach list to a model as attribute
		model.addAttribute("productTypes",productTypeList);
		
		return "producttypes";
		
	}
	
	/**
	 * This method will update or add an product type
	 * @param productType
	 * @return
	 */
	@RequestMapping("/producttype/save")
	public String updateProductType (@ModelAttribute ProductType productType) {
		
		//create new RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		//create request body
		HttpEntity<ProductType> request = new HttpEntity<ProductType>(productType);
		
		String productTypeResponse = "";
		
		if (productType.getProductTypeId()> 0) {
			//this block update a new order type and
			
			//send request PUT
			restTemplate.put(defaultURI, request, ProductType.class);
			
		}else {
			//this block add a new order type 
			
			//send request as POST
			productTypeResponse = restTemplate.postForObject(defaultURI, request, String.class);
			
		}
		
		System.out.println(productTypeResponse);
		
		//redirect request to display a list of order type
		return "redirect:/producttype/list";
	}
	

	/**
	 * This method gets an product type
	 * @param productTypeId
	 * @param model
	 * @return
	 */
	@GetMapping("/producttype/{productTypeId}")
	public String getProductType (@PathVariable Integer productTypeId, Model model) {
		
		String pageTitle = "New Product Type";
		ProductType productType = new ProductType();
		
		//This block get an product type to be updated
		if (productTypeId > 0) {
			
			//generate new URI and append productTypeId to it
			String uri = defaultURI + "/" + productTypeId;
			
			//get an product type from web service
			RestTemplate restTemplate = new RestTemplate();
			productType = restTemplate.getForObject(uri, ProductType.class);
			
			//give a new title to the page
			pageTitle = "Edit Product Type";
		}
		
		//attach value to pass to front end
		model.addAttribute("productType", productType);
		model.addAttribute("pageTitle", pageTitle);
		
		return "producttypeinfo";
	}
	

	/**
	 * This method deletes an product types
	 * @param productTypeId
	 * @return
	 */
	@RequestMapping("/producttype/delete/{productTypeId}")
	public String deleteProductType(@PathVariable Integer productTypeId) {
		
		//generate a new URI, similar to the mapping in ProductTypeRESTController
		String uri = defaultURI + "/{productTypeId}";
		
		//send a DELETE request and attach the value of the productTypeId into URI
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri, Map.of("productTypeId", Integer.toString(productTypeId)));
		
		return "redirect:/producttype/list";
		
	}

}
