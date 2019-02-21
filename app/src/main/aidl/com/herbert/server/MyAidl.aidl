// MyAidl.aidl
package com.herbert.server;
// Declare any non-default types here with import statements
import com.herbert.server.Book;
interface MyAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String getString();

    List<Book> getBooks();

}
