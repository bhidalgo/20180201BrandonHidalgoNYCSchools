package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Our RESTFul client for obtaining data from the NYC Schools Databases
 */
public interface NYCSchoolsClient {
    @Headers("Content-Type: application/json")
    @GET("97mf-9njv.json")
    Call<List<School>> getSchoolsData();

    @Headers("Content-Type: application/json")
    @GET("734v-jeq5.json")
    Call<List<Scores>> getSchoolsScores();
}
