package fr.rent.front.dto.request;

public record RentalPropertyRequestDto(
        String description,
        String town,
        String address,
        String propertyType,
        Double rentAmount,
        Double securityDepositAmount,
        Double area,
        Integer numberOfBedrooms,
        Integer floorNumber,
        Integer numberOfFloors,
        String constructionYear,
        String energyClassification,
        Boolean hasElevator,
        Boolean hasIntercom,
        Boolean hasBalcony,
        Boolean hasParkingSpace
) {
}
