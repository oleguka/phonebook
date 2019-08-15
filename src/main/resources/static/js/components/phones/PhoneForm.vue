<template>
    <div>
        <input type="text" placeholder="Write name" v-model="name" />
        <input type="text" placeholder="Write number" v-model="number">
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }

        return -1
    }
    export default {
        props: ['phones', 'phoneAttr'],
        data() {
            return {
                name: '',
                id: '',
                number: ''
            }
        },
        watch: {
            phoneAttr(newVal, oldVal) {
                this.name = newVal.name;
                this.id = newVal.id;
                this.number = newVal.number
            }
        },
        methods: {
            save(){
                const phone = { name: this.name, number: this.number };

                if (this.id) {
                    this.$resource('/phones{/id}').update({id: this.id}, phone).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.phones, data.id);
                            this.phones.splice(index, 1, data);
                            this.id = '';
                            this.name = '';
                            this.number = '';
                        })
                    )
                } else {
                    this.$resource('/phones{/id}').save({}, phone).then(result =>
                        result.json().then(data => {
                            this.phones.push(data);
                            this.name = '';
                            this.number = '';
                        })
                    )
                }
            }
        }
    }
</script>

<style scoped>

</style>