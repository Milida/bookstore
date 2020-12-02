import axios from 'axios';

const CATEGORY_API_BASE_URL = "http://localhost:8080/categories";

class CategoryService {

    getCategories(){
        return axios.get(CATEGORY_API_BASE_URL);
    }

    addCategory(category){
        return axios.post(CATEGORY_API_BASE_URL, category);
    }

    getCategoryById(categoryId){
        return axios.get(CATEGORY_API_BASE_URL +'/' + categoryId);
    }
    
    updateCategory(category, categoryId){
        return axios.put(CATEGORY_API_BASE_URL + '/' + categoryId, category);
    }
}

export default new CategoryService()