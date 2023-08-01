package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtraction {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    //1. Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the limit : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the total : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the name of 5th store : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> storeName = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the names of all the store: " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the storeId of all the store : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        int sizeOfTheData = response.extract().path("data.size()");

        //List<Integer> sizeOfTheData = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Print the size of the data list : " + sizeOfTheData);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> storevalue = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the store where store name = St Cloud : " + "\n" + storevalue);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<?> storeAddress = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the address of the store where store name = Rochester : " + storeAddress);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<?> allServices = response.extract().path("data[8].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store : " + "\n" + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<?> services = response.extract().path("data.services*.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get storeservices of the store where service name = Windows Store" + services);
        System.out.println("------------------End of Test---------------------------");

    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {
        List<Object> storeIdList = response.extract().path("data.services.storeservices.storeId");
        HashSet<Object> storeId = new HashSet<Object>(storeIdList);
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeId of all the store : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void test012() {
        List<?> storeId = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store : " + storeId);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<?> storeNameND = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store names Where state = ND : " + storeNameND);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<List<Integer>> totalNumberOfServices = response.extract().path("data.findAll{it.name == 'Rochester'}.services.id");
        int newList = totalNumberOfServices.get(0).size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the Total number of services for the store where store name = Rochester : " +newList);
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<String> createAtAllServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = 'Windows Store' : " + createAtAllServices);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<?> nameOfAllServices = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the name of all services Where store name = 'Fargo' : " + nameOfAllServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //  17. Find the zip of all the store
    @Test
    public void test017() {
        List<?> allDataZip = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store : " + allDataZip);
        System.out.println("------------------End of Test---------------------------");
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<?> zipOfRosevilleStore = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of store name = Roseville : " + zipOfRosevilleStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<?> storeServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the storeservices details of the service name = Magnolia Home Theater : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> allDatalat = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the lat of all the stores : " + allDatalat);
        System.out.println("------------------End of Test---------------------------");
    }

}
