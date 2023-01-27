package com.rak_developer.haritmartkotlin.util

//check error or success process
//sealed class Response() {
//    class loading : Response()
//    class success(val quoteListModel: QuotesListModel) : Response()
//    class error(val errorMessage: String) : Response()
//}


//check error or success process without generics
//sealed class Response(val data: QuotesListModel? = null, val errorMessage: String? = null) {
//    class loading : Response()
//    class success(quoteListModel: QuotesListModel) : Response(data = quoteListModel)
//    class error(errorMessage: String) : Response(errorMessage = errorMessage)
//}

//check error or success process without generics (T? is dynamic type)
sealed class ResponseGenerics<T>(val data: T? = null, val errorMessage: String? = null) {
    class Loading<T> : ResponseGenerics<T>()
    class Success<T>(data: T? = null) : ResponseGenerics<T>(data = data)
    class Error<T>(errorMessage: String) : ResponseGenerics<T>(errorMessage = errorMessage)
}