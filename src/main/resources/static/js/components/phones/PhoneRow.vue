<template>
    <v-card class="my-2">
        <v-card-text primary-title>
            <i>({{ phone.id }})</i> {{ phone.name }} + {{ phone.number }}
        </v-card-text>
        <v-card-actions>
            <v-btn value="Edit" @click="edit" small flat round>Edit</v-btn>
            <v-btn icon @click="del" small>
                <v-icon>
                    delete
                </v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ['phone', 'editPhone', 'deletePhone', 'phones'],
        methods: {
            edit() {
                this.editPhone(this.phone)
            },
            del() {
                this.deletePhone(this.phone)
            }
        }
    }
</script>

<style scoped>

</style>