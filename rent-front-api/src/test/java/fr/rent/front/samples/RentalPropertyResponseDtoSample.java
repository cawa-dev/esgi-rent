package fr.rent.front.samples;

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

    public static String manyInvalidRentalPropertiesStringResponseDto() {
        return "    \"address\": \"77 Rue des roses\",\n" +
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

    public static String oneInvalidRentalPropertyStringResponseDto() {
        return "  \"address\": \"77 Rue des roses\",\n" +
                "  \"area\": 37.48,\n" +
                "  \"description\": \"Appartement spacieux avec vue sur l'ESGI\",\n" +
                "  \"propertyType\": \"Appartement\",\n" +
                "  \"town\": \"Paris\"\n" +
                "}";
    }
}
