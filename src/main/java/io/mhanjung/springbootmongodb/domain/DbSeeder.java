package io.mhanjung.springbootmongodb.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class DbSeeder implements CommandLineRunner{

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public void run(String... strings) throws Exception {
        Hotel marriot = new Hotel(
                "Marriot",
                130,
                new Address("Paris", "France"),
                Arrays.asList(new Review("John", 8, false),
                        new Review("Maria", 2, false)
                )
        );

        Hotel hilton = new Hotel(
                "Hilton",
                290,
                new Address("Seoul", "Korea"),
                Arrays.asList(new Review("Teddy", 9, false),
                        new Review("Han", 3, true)
                )
        );

        Hotel holiday = new Hotel(
                "Holiday",
                90,
                new Address("Minnesota", "USA"),
                Arrays.asList(new Review("Syndra", 7, false),
                        new Review("Tristana", 8, true)
                )
        );

        // drop all hotels
        this.hotelRepository.deleteAll();

        // add our hotels to the database
        List<Hotel> hotels = Arrays.asList(marriot,hilton,holiday);
        this.hotelRepository.save(hotels);
    }
}
