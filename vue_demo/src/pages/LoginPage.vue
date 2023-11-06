<script setup>
import LoginComponent from "@/components/LoginComponent.vue";
</script>

<template>
  <div class="wrapper">
      <h1 style="width: 100%;">请登录</h1>
      <LoginComponent></LoginComponent>
  </div>
</template>

<style>
.wrapper{
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  padding-bottom: 50px;
}
</style>