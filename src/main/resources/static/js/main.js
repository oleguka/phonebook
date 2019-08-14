
var phonesApi = Vue.resource('/phones{/id}');

Vue.component('phone-row', {
    props: ['phone'],
    template: '<div><i>({{ phone.id }})</i> {{ phone.name }}</div>'
});

Vue.component('phones-list', {
    props: ['phones'],
    template: '<div><phone-row v-for="phone in phones" :key="phone.id" :phone="phone" /></div>',
    created: function () {
        phonesApi.get().then(result =>
            result.json().then(data =>
                data.forEach(phone => this.phones.push(phone))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<phones-list :phones="messages" />',
    data: {
        messages: []
    }
});