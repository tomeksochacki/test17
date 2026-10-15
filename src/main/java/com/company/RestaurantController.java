package com.company;

import ch.qos.logback.classic.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.logging.Level;

@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {
protected Logger loger = Logger.getLogger(RestaurantController.class.getName());
protected RestaurantService restaurantService;

@Autowired
public RestaurantController(RestaurantService restaurantService){
    this.restaurantService = restaurantService;
}
//Pobiera restauracje o określonej nazwie. Niewrażliwość na wielkość znaków jest częściowo obsługiwana
    // Wywołanie <code>http://.../restaurants/rest</code> znajdzie więc dowlone restauracje zawierające człon 'rest' lub REST w nazwie
    //@param name
    //@return Niepusta kolekcja restauracji
@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Restaurtant>> findByName(@RequestParam("name") String name){
    logger.info(String.format("Wywołanie metody restaurant-service findByName();{} dla {} ", restaurantService.getClass().getName, name));

    name = name.trim().toLowerCase();
    Collection<Restaurant> restaurants;
    try{
        restaurants = restaurantService.findByName(name);
    }catch (Exception ex){
        logger.log(Level.WARNING, "Wyjątek metody findByNameREST", ex);
        return new ResponseEntity<Collection<Restaurant>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return restaurant.size() > 0 ? new RestaurantEntity<Collection<Restaurant>>(restaurants, HttpStatus.OK):new ResponseEntity<Collection<Restaurant>>(HttpStatus.NO_CONTENT);
}


}
