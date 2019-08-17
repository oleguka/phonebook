<template>
    <v-layout align-space-around justify-start column>
        <phone-form :phones="phones" :phoneAttr="phone" />
        <phone-row v-for="phone in sortedPhones"
                   :key="phone.id"
                   :phone="phone"
                   :editPhone="editPhone"
                   :deletePhone="deletePhone"
                   :phones="phones" />
    </v-layout>
</template>

<script>
    import PhoneRow from "./PhoneRow.vue";
    import PhoneForm from "./PhoneForm.vue";
    export default {
        props: ['phones'],
        components: {
            PhoneRow,
            PhoneForm
        },
        data() {
            return {
                phone: null
            }
        },
        computed: {
            sortedPhones() {
                return this.phones.sort((a, b) => -(a.id - b.id))
            }
        },
        methods: {
            editPhone(phone) {
                this.phone = phone
            },
            deletePhone(phone) {
                this.$resource('/phones{/id}').remove({id: phone.id}).then(result => {
                    if (result.ok) {
                        this.phones.splice(this.phones.indexOf(phone), 1)
                    }
                })
            }
        }
    }

</script>

<style scoped>

</style>