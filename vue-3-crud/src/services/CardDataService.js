import http from "../http-common";

class CardDataService {
    getAll() {
        return http.get("/cards");
    }

    get(id) {
        return http.get(`/cards/${id}`);
    }

    create(data) {
        return http.post("/cards", data);
    }

    update(id, data) {
        return http.put(`/cards/${id}`, data);
    }

    delete(id) {
        return http.delete(`/cards/${id}`);
    }

    deleteAll() {
        return http.delete(`/cards`);
    }

    findByWord(word) {
        return http.get(`/cards?word=${word}`);
    }
}

export default new CardDataService();
