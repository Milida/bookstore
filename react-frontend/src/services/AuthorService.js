import axios from 'axios';

const AUTHOR_API_BASE_URL = "http://localhost:8080/authors";

class AuthorService {

    getAuthors(){
        return axios.get(AUTHOR_API_BASE_URL);
    }

 addAuthor(author){
     return axios.post(AUTHOR_API_BASE_URL, author);
    }   
}

export default new AuthorService()