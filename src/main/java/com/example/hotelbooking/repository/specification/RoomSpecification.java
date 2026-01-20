package com.example.hotelbooking.repository.specification;

import com.example.hotelbooking.entity.BookingEntity;
import com.example.hotelbooking.entity.RoomEntity;
import com.example.hotelbooking.repository.specification.filter.RoomFilter;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class RoomSpecification {

    public static Specification<RoomEntity> byFilter(RoomFilter filter) {
        return byRoomId(filter.getRoomId())
                .and(byHotelId(filter.getHotelId()))
                .and(byDates(filter.getCheckIn(), filter.getCheckOut())
                .and(byName(filter.getRoomName())))
                .and(byPrice(filter.getMinPrice(), filter.getMaxPrice()))
                .and(byOccupancy(filter.getOccupancy()));
    }

    private static Specification<RoomEntity> byRoomId(Long id) {

        if (id == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), id);
    }

    private static Specification<RoomEntity> byPrice(BigDecimal minPrice,
                                                     BigDecimal maxPrice) {

        if (minPrice == null || maxPrice == null ) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    private static Specification<RoomEntity> byOccupancy(Integer maxOccupancy) {

        if (maxOccupancy == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("maxOccupancy"), maxOccupancy);
    }

    private static Specification<RoomEntity> byDates(Date in,
                                                     Date out) {

        if (in == null || out == null)
            return null;

        if (!in.before(out))
            return null;

        return (root, criteriaQuery, criteriaBuilder) -> {
            Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
            Root<BookingEntity> bookingRoot = subquery.from(BookingEntity.class);
            subquery.select(criteriaBuilder.literal(1L));
            subquery.where(
                    criteriaBuilder.equal(root.get("room"), bookingRoot.get("room")),
                    criteriaBuilder.lessThan(bookingRoot.get("checkInDate"), out),
                    criteriaBuilder.greaterThan(bookingRoot.get("checkOutDate"), in)
            );

            return criteriaBuilder.not(criteriaBuilder.exists(subquery));
        };

    }

    private static Specification<RoomEntity> byName(String name) {

        if (name == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    private static Specification<RoomEntity> byHotelId(Long hotelId) {

        if (hotelId == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("hotel").get("id"), hotelId);
    }

}