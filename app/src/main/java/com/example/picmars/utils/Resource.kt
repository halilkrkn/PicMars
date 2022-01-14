package com.example.picmars.utils

///Başarılı ve hata yanıtlarını almak için
// Sealed Class, Nesneye dayalı programlama yaklaşımında kalıtım (inheritence) özelliği sayesinde bir sınıftan başka sınıflar türetilebilir ve bu sınıflara yeni özellikler eklenerek daha zengin ve kullanışlı sınıflar yaratılabilir.
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}