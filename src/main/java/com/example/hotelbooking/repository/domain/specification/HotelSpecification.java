package com.example.hotelbooking.repository.domain.specification;

import com.example.hotelbooking.entity.HotelEntity;
import com.example.hotelbooking.repository.domain.specification.filter.HotelFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class HotelSpecification {

    public static Specification<HotelEntity> byFilter(HotelFilter filter) {
        return byHotelId(filter.getHotelId())
                .and(byName(filter.getName()))
                .and(byAdTitle(filter.getAdTitle()))
                .and(byAddress(filter.getAddress()))
                .and(byCity(filter.getCity()))
                .and(byDistance(filter.getDistanceFromCenter()))
                .and(byRating(filter.getTotalRating()))
                .and(byNumberOfRatings(filter.getNumberOfRatings()));
    }

    private static Specification<HotelEntity> byHotelId(Long hotelId) {

        if (hotelId == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), hotelId);
    }

    private static Specification<HotelEntity> byName(String hotelName) {

        if (hotelName == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), hotelName);
    }

    private static Specification<HotelEntity> byAdTitle(String adTitle) {

        if (adTitle == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("adTitle"), adTitle);
    }

    private static Specification<HotelEntity> byAddress(String address) {

        if (address == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("address"), address);
    }

    private static Specification<HotelEntity> byCity(String city) {

        if (city == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("city"), city);
    }

    private static Specification<HotelEntity> byDistance(Double distanceFromCenter) {

        if (distanceFromCenter == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("distanceFromCenter"), distanceFromCenter);
    }

    private static Specification<HotelEntity> byRating(Double totalRating) {

        if (totalRating == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("totalRating"), totalRating);
    }

    private static Specification<HotelEntity> byNumberOfRatings(Double numberOfRatings) {

        if (numberOfRatings == null) return null;

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("numberOfRatings"), numberOfRatings);
    }
}