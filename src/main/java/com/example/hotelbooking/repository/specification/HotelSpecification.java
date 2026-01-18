package com.example.hotelbooking.repository.specification;

import com.example.hotelbooking.entity.HotelEntity;
import com.example.hotelbooking.repository.specification.filter.HotelFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class HotelSpecification {

    public static Specification<HotelEntity> byFilter(final HotelFilter filter) {
        return byHotelId(filter.getHotelId())
                .and(byAddress(filter.getAddress()))
                .and(byCity(filter.getCity()))
                .and(byDistance(filter.getDistanceFromCenter()))
                .and(byRating(filter.getTotalRating()));
    }

    private static Specification<HotelEntity> byHotelId(final Long hotelId) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), hotelId);
    }


    private static Specification<HotelEntity> byAddress(String address) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("address"), address);
    }

    private static Specification<HotelEntity> byCity(String city) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("city"), city);
    }

    private static Specification<HotelEntity> byDistance(Double distanceFromCenter) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("distanceFromCenter"), distanceFromCenter);
    }

    private static Specification<HotelEntity> byRating(Double totalRating) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("totalRating"), totalRating);
    }
}