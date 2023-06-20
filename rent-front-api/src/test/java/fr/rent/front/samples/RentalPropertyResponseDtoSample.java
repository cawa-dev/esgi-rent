package fr.rent.front.samples;

import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.rent.front.dto.response.RentalPropertyResponseDto;

import java.util.List;

public class RentalPropertyResponseDtoSample {
    public static List<RentalPropertyResponseDto> manyRentalPropertiesResponseDto() {
        return List.of(
                new RentalPropertyResponseDto(
                        "77 Rue des roses",
                        37.48,
                        "Appartement spacieux avec vue sur l'ESGI",
                        "Appartement",
                        750.90,
                        1200.90,
                        "Paris"
                ),
                new RentalPropertyResponseDto(
                        "123 Main St",
                        80.00,
                        "Beautiful house in the countryside",
                        "House",
                        1200.00,
                        2400.00,
                        "Lyon"
                )
        );

    }

    public static RentalPropertyResponseDto oneRentalPropertyResponseDto() {
        return new RentalPropertyResponseDto(
                "77 Rue des roses",
                37.48,
                "Appartement spacieux avec vue sur l'ESGI",
                "Appartement",
                750.90,
                1200.90,
                "Paris"
        );
    }

    public static RentalPropertyRequestDtoPatch oneRentalPropertyRequestDtoPatch() {
        return new RentalPropertyRequestDtoPatch(1200.0);
    }

    public static RentalPropertyRequestDto oneRentalPropertyRequestDto() {
        return new RentalPropertyRequestDto(
                "Beautiful, spacious, sunlit apartment in the heart of the city.",
                "New York",
                "10 Downing Street",
                "Apartment",
                1200.0,
                2400.0,
                1000.0,
                3,
                2,
                5,
                "1999",
                "A",
                true,
                true,
                true,
                true
        );
    }

    public static String oneRentalPropertyStringRequestDtoPatch() {
        return "{" +
                "\"rentAmount\":1200.0" +
                "}";
    }

    public static String oneValidRentalPropertyStringRequestDto() {
        return "{" +
                "\"description\":\"Beautiful, spacious, sunlit apartment in the heart of the city.\"," +
                "\"town\":\"New York\"," +
                "\"address\":\"10 Downing Street\"," +
                "\"propertyType\":\"Apartment\"," +
                "\"rentAmount\":1200.0," +
                "\"securityDepositAmount\":2400.0," +
                "\"area\":1000.0," +
                "\"numberOfBedrooms\":3," +
                "\"floorNumber\":2," +
                "\"numberOfFloors\":5," +
                "\"constructionYear\":\"1999\"," +
                "\"energyClassification\":\"A\"," +
                "\"hasElevator\":true," +
                "\"hasIntercom\":true," +
                "\"hasBalcony\":true," +
                "\"hasParkingSpace\":true" +
                "}";
    }

    public static String oneValidRentalPropertyStringResponseDto() {
        return "{\n" +
                "  \"address\": \"77 Rue des roses\",\n" +
                "  \"area\": 37.48,\n" +
                "  \"description\": \"Appartement spacieux avec vue sur l'ESGI\",\n" +
                "  \"propertyType\": \"Appartement\",\n" +
                "  \"rentAmount\": 750.90,\n" +
                "  \"securityDepositAmount\": 1200.90,\n" +
                "  \"town\": \"Paris\"\n" +
                "}";
    }


    public static String manyValidRentalPropertiesStringResponseDto() {
        return "[\n" +
                "  {\n" +
                "    \"address\": \"77 Rue des roses\",\n" +
                "    \"area\": 37.48,\n" +
                "    \"description\": \"Appartement spacieux avec vue sur l'ESGI\",\n" +
                "    \"propertyType\": \"Appartement\",\n" +
                "    \"rentAmount\": 750.90,\n" +
                "    \"securityDepositAmount\": 1200.90,\n" +
                "    \"town\": \"Paris\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"address\": \"123 Main St\",\n" +
                "    \"area\": 80.00,\n" +
                "    \"description\": \"Beautiful house in the countryside\",\n" +
                "    \"propertyType\": \"House\",\n" +
                "    \"rentAmount\": 1200.00,\n" +
                "    \"securityDepositAmount\": 2400.00,\n" +
                "    \"town\": \"Lyon\"\n" +
                "  }\n" +
                "]";
    }
}
