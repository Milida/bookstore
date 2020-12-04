import axios from 'axios';

const AUTHOR_API_BASE_URL = "http://localhost:8080/authors";

class AuthorService {

    getAuthors(){
        return axios.get(AUTHOR_API_BASE_URL);
    }

    addAuthor(author){
     return axios.post(AUTHOR_API_BASE_URL, author);
    }
    
    getAuthorById(authorId){
        return axios.get(AUTHOR_API_BASE_URL +'/' + authorId);
    }
    
    updateAuthor(author, authorId){
        return axios.put(AUTHOR_API_BASE_URL + '/' + authorId, author);
    }
    removeAuthor(author) {
        return axios.delete(AUTHOR_API_BASE_URL + '/' + author.id);
    }
}

export default new AuthorService()