<template>
    <phone-list :phones="phones" />
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