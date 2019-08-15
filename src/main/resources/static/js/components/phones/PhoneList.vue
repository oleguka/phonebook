<template>
    <div style="position: relative; width: 300px;">
        <phone-form :phones="phones" :phoneAttr="phone" />
        <phone-row v-for="phone in phones"
                   :key="phone.id"
                   :phone="phone"
                   :editPhone="editPhone"
                   :deletePhone="deletePhone"
                   :phones="phones" />
    </div>
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