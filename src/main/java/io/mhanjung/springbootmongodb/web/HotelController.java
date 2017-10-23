package io.mhanjung.springbootmongodb.web;


import com.querydsl.core.types.dsl.BooleanExpression;
import io.mhanjung.springbootmongodb.domain.Hotel;
import io.mhanjung.springbootmongodb.domain.HotelRepository;
import io.mhanjung.springbootmongodb.domain.QHotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/all")
    public List<Hotel> getAll(){
        List<Hotel> hotels = this.hotelRepository.findAll();

        return hotels;
    }

    @PutMapping
    public void insert(@RequestBody Hotel hotel){
        // insert always!
        this.hotelRepository.insert(hotel);
    }

    @PostMapping
    public void update(@RequestBody Hotel hotel){
        // if the hotelId exist then update, otherwise insert.
        this.hotelRepository.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        this.hotelRepository.delete(id);
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable("id") String id){
        Hotel hotel = this.hotelRepository.findById(id);
        return hotel;
    }

    @GetMapping("/prices/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice){
        List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);
        return hotels;
    }

    @GetMapping("/address/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city){
        List<Hotel> hotels = this.hotelRepository.findByCity(city);
        return hotels;
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country){
        // create a query class (QHotel)
        QHotel qHotel = new QHotel("hotel");

        // using the query class, we can create the filters
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        // then we can pass the filters to the findAll() method
        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);

        return hotels;
    }


    @GetMapping("/recommended")
    public List<Hotel> getRecommended(){
        final int maxPrice = 100;
        final int minRating = 7;

        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));
        return hotels;
    }

}
