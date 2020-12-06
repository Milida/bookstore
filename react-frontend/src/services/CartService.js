import axios from 'axios';

const CART_API_BASE_URL = "http://localhost:8080/cart";

class CartService {

    getCart(userId) {
        return axios.get(CART_API_BASE_URL + '/' + userId);
    }

    addToCart(item) {
        return axios.post(CART_API_BASE_URL, item);
    }

    removeItem(userId, itemId) {
        return axios.put(CART_API_BASE_URL + '/remove', {
            "id": {
                "userId": userId,
                "bookId": itemId
            },
            "user": {
                "id": userId
            },
            "book": {
                "id": itemId
            }
        });
    }

    removeAllUserItems(userId){
        return axios.delete(CART_API_BASE_URL + '/remove/' + userId);
    }
}

export default new CartService();