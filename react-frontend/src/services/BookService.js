import axios from 'axios';

const BOOK_API_BASE_URL = "http://localhost:8080/books";

class BookService {

    getBooks(){
        return axios.get(BOOK_API_BASE_URL);
    }

    addBook(book){
        return axios.post(BOOK_API_BASE_URL, book);
    }
    removeBook(book) {
        return axios.delete(BOOK_API_BASE_URL + '/' + book.id);
    }
}

export default new BookService()