
function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}


var phonesApi = Vue.resource('/phones{/id}');

Vue.component('phone-form', {
    props: ['phones', 'phoneAttr'],
    data: function () {
        return {
            name: '',
            id: '',
            number: ''
        }
    },
    watch: {
        phoneAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
            this.number = newVal.number
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write name" v-model="name" />' +
        '<input type="text" placeholder="Write number" v-model="number">' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var phone = { name: this.name, number: this.number };

            if (this.id) {
                phonesApi.update({id: this.id}, phone).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.phones, data.id);
                        this.phones.splice(index, 1, data);
                        this.id = '';
                        this.name = '';
                        this.number = '';
                    })
                )
            } else {
                phonesApi.save({}, phone).then(result =>
                    result.json().then(data => {
                        this.phones.push(data);
                        this.name = '';
                        this.number = '';
                    })
                )
            }
        }
    }
});

Vue.component('phone-row', {
    props: ['phone', 'editMethod', 'phones'],
    template: '<div>' +
        '<i>({{ phone.id }})</i> {{ phone.name }} +{{ phone.number }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.phone);
        },
        del: function () {
            phonesApi.remove({id: this.phone.id}).then(result => {
                this.phone.splice(this.phones.indexOf(this.phone), 1)
            })
        }
    }
});

Vue.component('phones-list', {
    props: ['phones'],
    data: function() {
        return {
            phone: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<phone-form :phones="phones" :phoneAttr="phone" />' +
        '<phone-row v-for="phone in phones" :key="phone.id" :phone="phone" :editMethod="editMethod" :phones="phones" />' +
        '</div>',
    created: function () {
        phonesApi.get().then(result =>
            result.json().then(data =>
                data.forEach(phone => this.phones.push(phone))
            )
        )
    },
    methods: {
        editMethod: function (phone) {
            this.phone = phone
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<phones-list :phones="phones" />',
    data: {
        phones: []
    }
});