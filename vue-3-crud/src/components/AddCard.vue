<template>
    <div class="submit-form">
        <div v-if="!submitted">
            <div class="form-group">
                <label for="word">Word</label>
                <input type="text" class="form-control" id="word" required v-model="card.word" name="word" />
            </div>

            <div class="form-group">
                <label for="translation">Translation</label>
                <input class="form-control" id="translation" required v-model="card.translation" name="translation" />
            </div>

            <div class="form-group">
                <label for="category">Category</label>
                <div class="dropright">
                    
                    <button v-if="card.category != null" class="btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
                        {{ card.category }}
                    </button>
                    <button v-else class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-expanded="false">
                        Select a category
                    </button>
                    <div class="dropdown-menu">
                        <button class="dropdown-item" type="button" @click.prevent="setCategory('Noun')">Noun</button>
                        <button class="dropdown-item" type="button" @click.prevent="setCategory('Adjective')">Adjective</button>
                        <button class="dropdown-item" type="button" @click.prevent="setCategory('Verb')">Verb</button>
                    </div>
                </div>
            </div>

            <button @click="saveCard" class="btn btn-success">Submit</button>
        </div>
        

        <div v-else>
            <h4>You submitted successfully!</h4>
            <router-link to="/" class="btn btn-secondary mr-2"> Back </router-link>
            <button class="btn btn-success" @click="newCard">Add</button>
        </div>
    </div>
</template>

<script>
import CardDataService from "../services/CardDataService";

export default {
    name: "add-card",
    data() {
        return {
            card: {
                id: null,
                word: "",
                translation: "",
                category: null
            },
            submitted: false,
            emptyCategory: false
        };
    },
    methods: {
        saveCard() {
            var data = {
                word: this.card.word,
                translation: this.card.translation,
                category: this.card.category
            };

            if (data.category == null) {
                this.emptyCategory = true;
            } else {
            
                CardDataService.create(data)
                    .then(response => {
                        this.card.id = response.data.id;
                        console.log(response.data);
                        this.submitted = true;
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }

        },
        newCard() {
            this.submitted = false;
            this.card = {};
        },
        setCategory(category) {
            this.card.category = category;
        }
    }
};
</script>

<style>
.submit-form {
    max-width: 300px;
    margin: auto;
}
</style>
