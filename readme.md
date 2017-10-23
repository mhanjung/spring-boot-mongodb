# Spring Boot Application with MongoDB

A simple RESTful project with Spring Boot and MongoDB.

### Make Requests
For Hotel lists, update, create, delete:
```
GET http://localhost:8080/hotels/all
GET http://localhost:8080/hotels/{id}
POST http://localhost:8080/hotels/
PUT http://localhost:8080/hotels/
DELETE http://localhost:8080/hotels/{id}
```

Getting Hotel lists with some conditions(eq.maxPrice):
```
### URL ###
GET http://localhost:8080/hotels/prices/{maxPrice}
&
### HotelRepository ###
List<Hotel> findByPricePerNightLessThan(int maxPrice);
&
### HotelController ###
List<Hotel> findByPricePerNightLessThan(int maxPrice);
```

Getting Hotel lists Using mongo Query(eq.city):
```
### URL ###
GET http://localhost:8080/hotels/address/{city}
&
### HotelRepository ###
@Query(value = "{address.city:?0}")
List<Hotel> findByCity(String city);
&
### HotelController ###
List<Hotel> hotels = this.hotelRepository.findByCity(city);
```

Getting Hotel lists Using mongo QueryDsl(eq.country or recommended):
```
### URL ###
GET http://localhost:8080/hotels/country/{country}
GET http://localhost:8080/hotels/recommended
```

## Author

This example was created by [Han Jung](http://mhanjung.github.io).