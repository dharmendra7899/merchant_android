import android.content.Context
import com.example.vendor_android.dataClient.ApiService
import com.example.vendor_android.dataClient.AuthInterceptor
import com.example.vendor_android.utils.TokenManager
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://api-dev.closetobuy.com/c2b"

    private lateinit var tokenManager: TokenManager

    // Initialize RetrofitClient with context
    fun init(context: Context) {
        tokenManager = TokenManager(context)
    }

    private val retrofit by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    // Common GET method
    suspend fun <T> get(endpoint: String): T {
        return apiService.get(endpoint)
    }

    // Common POST method
    suspend fun <T> post(endpoint: String, body: Any): T {
        return apiService.post(endpoint, body)
    }

    // Common PUT method
    suspend fun <T> put(endpoint: String, body: Any): T {
        return apiService.put(endpoint, body)
    }

    // Common DELETE method
    suspend fun <T> delete(endpoint: String): T {
        return apiService.delete(endpoint)
    }

    // Common PATCH method
    suspend fun <T> patch(endpoint: String, body: Any): T {
        return apiService.patch(endpoint, body)
    }

    // Common Multipart method
    suspend fun <T> uploadFile(endpoint: String, part: MultipartBody.Part): T {
        return apiService.uploadFile(endpoint, part)
    }
}
