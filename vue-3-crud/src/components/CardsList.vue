<template>
    <div class="list row">
        <div class="col-md-8">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Search by word" v-model="word" />
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" @click="searchWord">
                        Search
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h4>Cards List</h4>
            <ul class="list-group">
                <li class="list-group-item" :class="{ active: index == currentIndex }" v-for="(card, index) in cards"
                    :key="index" @click="setActiveCard(card, index)">
                    {{ card.word }}
                </li>
            </ul>

            <button class="m-3 btn btn-danger" @click="removeAllCards"> Remove All </button>
            <router-link :to="'/cards/add'" class="btn btn-success"> Add </router-link>
        </div>
        <div class="col-md-6">
            <div v-if="currentCard">
                <h4>Card</h4>
                <div>
                    <label><strong>Word:</strong></label> {{ currentCard.word }}
                </div>
                <div>
                    <label><strong>Translation:</strong></label> {{ currentCard.translation }}
                </div>
                <div>
                    <label><strong>Category:</strong></label> <span class="badge" :class="getCategoryClass()"> {{
                        currentCard.category ? currentCard.category : "None" }}</span>
                </div>

                <router-link :to="'/cards/' + currentCard.id" class="btn btn-warning">Edit</router-link>
            </div>
            <div v-else>
                <br />
                <p>Please click on a Card...</p>
            </div>
        </div>
    </div>
</template>

<script>
import CardDataService from "../services/CardDataService";

export default {
    name: "cards-list",
    data() {
        return {
            cards: [],
            currentCard: null,
            currentIndex: -1,
            word: ""
        };
    },
    methods: {
        retrieveCards() {
            CardDataService.getAll()
                .then(response => {
                    this.cards = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },

        refreshList() {
            this.retrieveCards();
            this.currentCard = null;
            this.currentIndex = -1;
        },

        setActiveCard(card, index) {
            this.currentCard = card;
            this.currentIndex = card ? index : -1;
        },

        removeAllCards() {
            CardDataService.deleteAll()
                .then(response => {
                    console.log(response.data);
                    this.refreshList();
                })
                .catch(e => {
                    console.log(e);
                });
        },

        searchWord() {
            CardDataService.findByWord(this.word)
                .then(response => {
                    this.cards = response.data;
                    this.setActiveCard(null);
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        getCategoryClass() {
            console.log(this.currentCard.category)
            switch (this.currentCard.category) {
                case "Noun": return "badge-primary"
                case "Adjective": return "badge-warning"
                case "Verb": return "badge-success"
                default: return "badge-secondary"
            }
        }
    },
    mounted() {
        this.retrieveCards();
    }
};
</script>

<style>
.list {
    text-align: left;
    max-width: 1250px;
    margin: auto;
}
</style>
