package my.edu.utem.ftmk.dad.restorderapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.edu.utem.ftmk.dad.restorderapp.model.OrderType;
import my.edu.utem.ftmk.dad.restorderapp.repository.OrderTypeRepository;


/**
 * This class represents the REST controller for the OrderType entity.
 * It handles HTTP requests and responses for CRUD operations on the OrderType table.
 * The REST API is available at the "/api/ordertypes" endpoint.
 * 
 * It is annotated with @RestController to indicate that this class is a REST controller,
 * and with @RequestMapping("/api/ordertypes") to specify the base URL of the REST API.
 */
@RestController
@RequestMapping("/api/ordertypes")

public class OrderTypeRESTController {
	
	@Autowired
	private OrderTypeRepository orderTypeRepository;

    /**
     * This method retrieves all records from the OrderType table and returns them as a List of OrderType objects.
     * It handles HTTP GET requests to the "/api/ordertypes" endpoint.
     *
     * @return a List of OrderType objects representing all records in the OrderType table.
     */
    @GetMapping
	public List <OrderType> getOrderTypes() {							
		
		return orderTypeRepository.findAll();
		
	}
    
    /**

    This method retrieves the OrderType object associated with the given orderTypeId.

    @param orderTypeId the ID of the order type to be retrieved

    @return the OrderType object associated with the given orderTypeId
    */
    @GetMapping("{orderTypeId}")
    public OrderType getOrderType(@PathVariable long orderTypeId) {
    	OrderType orderType = orderTypeRepository.findById(orderTypeId).get();
    	
    	return orderType;
    }
    
    /**

    This method creates a new OrderType object by persisting the given OrderType data to the database.

    @param orderType the OrderType object to be persisted

    @return the newly created OrderType object with its generated ID
    */
    @PostMapping()
    public OrderType insertOrderType(@RequestBody OrderType orderType) {
    	
    	return orderTypeRepository.save(orderType);
    }

    /**

    This method updates an existing OrderType object in the database by persisting the modified OrderType data.

    @param orderType the modified OrderType object to be persisted

    @return the updated OrderType object with its modified data
    */
    @PutMapping()
    public OrderType updateOrderType(@RequestBody OrderType orderType) {
    	
    	return orderTypeRepository.save(orderType);
    }
    
    /**

	This method deletes an existing OrderType object from the database using the given orderTypeId.

	@param orderTypeId the ID of the OrderType object to be deleted

	@return the HTTP response status indicating the success of the operation
	*/
	@DeleteMapping("{orderTypeId}")
    public ResponseEntity<HttpStatus> deleteOrderType(@PathVariable long orderTypeId) {
    	
    	orderTypeRepository.deleteById(orderTypeId);
    	
    	return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
	

}
