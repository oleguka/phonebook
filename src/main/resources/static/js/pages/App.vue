<template>
    <v-app>
        <v-toolbar app>
            <v-toolbar-title>Phonebook</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>

        <v-content>
            <v-container>
                <phone-list :phones="phones" />
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import PhoneList from "../components/phones/PhoneList.vue";
    export default {
        components: {
            PhoneList
        },
        data() {
            return {
                phones: frontendData.phones
            }
        }
    }

</script>

<style>

</style>