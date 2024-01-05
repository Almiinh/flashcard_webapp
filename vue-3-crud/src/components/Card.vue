<template>
    <div v-if="currentCard" class="edit-form">
        <h4>Card</h4>
        <form>
            <div class="form-group">
                <label for="word">Word</label>
                <input type="text" class="form-control" id="word" v-model="currentCard.word" />
            </div>
            <div class="form-group">
                <label for="translation">Translation</label>
                <input type="text" class="form-control" id="translation" v-model="currentCard.translation" />
            </div>
            <div class="form-group ">
                <label for="category">Category</label>
                <div class="dropright">
                    <button v-if="currentCard.category != null" class="btn btn-secondary dropdown-toggle" type="button"
                        data-toggle="dropdown" aria-expanded="false">
                        {{ currentCard.category }}
                    </button>
                    <button v-else class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown"
                        aria-expanded="false">
                        Select a category
                    </button>
                    <div class="dropdown-menu">
                        <button class="dropdown-item" type="button" @click.prevent="setCategory('Noun')">Noun</button>
                        <button class="dropdown-item" type="button" @click.prevent="setCategory('Adjective')"> Adjective </button>
                        <button class="dropdown-item" type="button" @click.prevent="setCategory('Verb')">Verb</button>
                    </div>
                </div>
            </div>

        </form>
        <router-link to="/" class="btn btn-secondary mr-2"> Back </router-link>
        <button class="btn btn-danger mr-2" @click="deleteCard"> Delete </button>
        <button type="submit" class="btn btn-success" @click="updateCard"> Update </button>
        <div class="alert alert-success mt-2">{{ message }}</div>
    </div>

    <div v-else>
        <br />
        <p>Please click on a Card...</p>
    </div>
</template>

<script>
import CardDataService from "../services/CardDataService";

export default {
    name: "card",
    data() {
        return {
            currentCard: null,
            message: ''
        };
    },
    methods: {
        getCard(id) {
            CardDataService.get(id)
                .then(response => {
                    this.currentCard = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        updateCard() {
            CardDataService.update(this.currentCard.id, this.currentCard)
                .then(response => {
                    console.log(response.data);
                    this.message = 'The card was updated successfully!';
                })
                .catch(e => {
                    console.log(e);
                });
        },
        deleteCard() {
            CardDataService.delete(this.currentCard.id)
                .then(response => {
                    console.log(response.data);
                    this.$router.push({ name: "cards" });
                })
                .catch(e => {
                    console.log(e);
                });
        },
        setCategory(category) {
            this.currentCard.category = category
        }
    },
    mounted() {
        this.message = '';
        this.getCard(this.$route.params.id);
    }
};
</script>

<style>
.edit-form {
    max-width: 300px;
    margin: auto;
}
</style>