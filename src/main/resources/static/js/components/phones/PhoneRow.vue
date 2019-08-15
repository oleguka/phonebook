<template>
    <div>
        <i>({{ phone.id }})</i> {{ phone.name }} + {{ phone.number }}
        <span style="position: absolute; right: 0">
        <input type="button" value="Edit" @click="edit" />
        <input type="button" value="X" @click="del" />
        </span>
    </div>
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